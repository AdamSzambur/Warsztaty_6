package pl.coderslab.warsztat6.twitter.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.warsztat6.twitter.dto.MessageFormDTO;
import pl.coderslab.warsztat6.twitter.model.Message;
import pl.coderslab.warsztat6.twitter.model.User;
import pl.coderslab.warsztat6.twitter.services.CommentService;
import pl.coderslab.warsztat6.twitter.services.MessageService;
import pl.coderslab.warsztat6.twitter.services.TweetService;
import pl.coderslab.warsztat6.twitter.services.UserService;

import javax.validation.Valid;
import javax.validation.Validator;
import java.security.Principal;

@Controller
@RequestMapping("/message")
public class MessageController {
    private Validator validator;
    private UserService userService;
    private TweetService tweetService;
    private CommentService commentService;
    private MessageService messageService;


    public MessageController(Validator validator, UserService userService, TweetService tweetService, CommentService commentService, MessageService messageService) {
        this.validator = validator;
        this.userService = userService;
        this.tweetService = tweetService;
        this.commentService = commentService;
        this.messageService = messageService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }

    @ModelAttribute("unreadMessagesNumber")
    public Long unreadMessagesNumber() {
        return messageService.countOfUnreadMesseges(principalToUser().getId());
    }

    @GetMapping
    public String messageAll(@RequestParam String box, Model model, Principal principal) {
        model.addAttribute("box",box);

        if (box.equals("inbox")) {
            model.addAttribute("messages", messageService.getUserInbox(userService.getUserByEmail(principal.getName()).getId()));
        } else if (box.equals("outbox")) {
            model.addAttribute("messages", messageService.getUserOutbox(userService.getUserByEmail(principal.getName()).getId()));
        }
        return "messages";
    }


    @GetMapping("/add")
    public String messageAdd(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("message", new MessageFormDTO(userService.getUserByEmail(principal.getName()).getId(),
                userService.getUserById(id,false).getId()));
        model.addAttribute("senderName", userService.getUserByEmail(principal.getName()).getFullName());
        model.addAttribute("recipientName", userService.getUserById(id,false).getFullName());
        return "message";
    }

    @PostMapping("/add")
    public String processMessageAdd(@ModelAttribute("message") @Valid MessageFormDTO message,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getObjectName() + " "+error.getDefaultMessage());
            }
            model.addAttribute("senderName", userService.getUserById(message.getSenderId(),false).getFullName());
            model.addAttribute("recipientName", userService.getUserById(message.getRecipientId(),false).getFullName());

            return "message";
        } else {
            messageService.addMessage(message);
        }
        return "redirect:/message?box=inbox";
    }

    @GetMapping("/view")
    public String messageView(@RequestParam Long id, Model model, Principal principal) {
        Message message = messageService.getOne(id);
        message.setReaded(true);
        messageService.saveMessage(message);
        model.addAttribute("message", message);
        return "messageView";
    }



}

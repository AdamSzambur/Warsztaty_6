package pl.coderslab.warsztat6.twitter.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.warsztat6.twitter.dto.CommentFormDTO;
import pl.coderslab.warsztat6.twitter.dto.TweetFormDTO;
import pl.coderslab.warsztat6.twitter.model.User;
import pl.coderslab.warsztat6.twitter.services.CommentService;
import pl.coderslab.warsztat6.twitter.services.MessageService;
import pl.coderslab.warsztat6.twitter.services.TweetService;
import pl.coderslab.warsztat6.twitter.services.UserService;

import javax.validation.Valid;
import javax.validation.Validator;
import java.security.Principal;

@Controller
@RequestMapping("/tweet")
public class TweetController {
    private Validator validator;
    private UserService userService;
    private TweetService tweetService;
    private CommentService commentService;
    private MessageService messageService;

    public TweetController(UserService userService, TweetService tweetService, Validator validator,
                           CommentService commentService, MessageService messageService) {
        this.userService = userService;
        this.tweetService = tweetService;
        this.validator = validator;
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
    public String tweetPage(@RequestParam Long id,  Model model, Principal principal) {
        model.addAttribute("tweet", tweetService.getTweetById(id, true));
        model.addAttribute("newComment", new CommentFormDTO(id));
        return "tweet";
    }

    @PostMapping
    public String addTweet(@ModelAttribute("newComment") @Valid CommentFormDTO newComment, BindingResult result, Model model, Principal principal,@RequestParam Long id) {
        if (result.hasErrors()) {
            model.addAttribute("tweet", tweetService.getTweetById(id, true));
            if (newComment.getParentId() != null) {
                model.addAttribute("error", newComment.getParentId());
            } else {
                model.addAttribute("error",0);
            }
            return "tweet";
        } else {
            commentService.addComment(newComment,principal.getName());
            return "redirect:/tweet?id="+newComment.getTweetId();
        }
    }


}

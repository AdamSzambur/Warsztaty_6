package pl.coderslab.warsztat6.twitter.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.warsztat6.twitter.dto.TweetFormDTO;
import pl.coderslab.warsztat6.twitter.model.User;
import pl.coderslab.warsztat6.twitter.services.CommentService;
import pl.coderslab.warsztat6.twitter.services.MessageService;
import pl.coderslab.warsztat6.twitter.services.UserService;
import pl.coderslab.warsztat6.twitter.services.TweetService;

import javax.validation.Valid;
import javax.validation.Validator;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomePageController {
    private Validator validator;
    private UserService userService;
    private TweetService tweetService;
    private CommentService commentService;
    private MessageService messageService;

    public HomePageController(UserService userService, TweetService tweetService, Validator validator,
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
    public String homePage(Model model, Principal principal) {
        model.addAttribute("tweets", tweetService.getTweetsOrderByLastOne(null,true));
        model.addAttribute("tweet", new TweetFormDTO());
        return "index";
    }

    @PostMapping
    public String addTweet(@ModelAttribute("tweet") @Valid TweetFormDTO tweet, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("tweets", tweetService.getTweetsOrderByLastOne(null,true));
            return "index";
        } else {
            tweetService.addTweet(tweet, userService.getUserByEmail(principal.getName()));
            return "redirect:/";
        }
    }


}

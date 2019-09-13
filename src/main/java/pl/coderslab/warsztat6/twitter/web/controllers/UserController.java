package pl.coderslab.warsztat6.twitter.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.warsztat6.twitter.dto.RegistrationFormDTO;
import pl.coderslab.warsztat6.twitter.dto.TweetFormDTO;
import pl.coderslab.warsztat6.twitter.dto.UserEditFormDTO;
import pl.coderslab.warsztat6.twitter.model.User;
import pl.coderslab.warsztat6.twitter.services.MessageService;
import pl.coderslab.warsztat6.twitter.services.RegistrationService;
import pl.coderslab.warsztat6.twitter.services.TweetService;
import pl.coderslab.warsztat6.twitter.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private TweetService tweetService;
    private MessageService messageService;

    public UserController(UserService userService, TweetService tweetService, MessageService messageService) {
        this.userService = userService;
        this.tweetService = tweetService;
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


    @GetMapping("/userEdit")
    public String userEditPage(Model model, Principal principal) {
        // DTO - data transfer object (obiekt zawierajacy w tym wypadku pole formularza).
        UserEditFormDTO userEditFormDTO = new UserEditFormDTO(userService.getUserByEmail(principal.getName()));
        model.addAttribute("data",userEditFormDTO);
        return "userEdit";
    }

    @PostMapping("/userEdit")
    public  String processUserEditPage(@ModelAttribute("data") @Valid UserEditFormDTO data, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userEdit";
        }

        if (!data.getPassword().equals(data.getRePassword())) {
            result.rejectValue("repassword", null,"Hasła muszą być zgodne");
            return "userEdit";
        }
        userService.updateUser(data);
        model.addAttribute("msg", "Dane uzytkownika zostały zaktualizowane");
        return "userEdit";
    }

    @GetMapping("/userTweets")
    public String userTweetsPage(Model model, Principal principal) {
        model.addAttribute("tweets",
                tweetService.getTweetsOrderByLastOne(userService.getUserByEmail(principal.getName()),true));
        model.addAttribute("tweet", new TweetFormDTO());
        return "userTweets";
    }

}

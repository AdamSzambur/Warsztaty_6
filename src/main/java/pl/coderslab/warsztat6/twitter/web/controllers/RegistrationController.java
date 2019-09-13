package pl.coderslab.warsztat6.twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.warsztat6.twitter.dto.RegistrationFormDTO;
import pl.coderslab.warsztat6.twitter.services.RegistrationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String registrationPage(Model model) {
        // DTO - data transfer object (obiekt zawierajacy w tym wypadku pole formularza).
        model.addAttribute("data",new RegistrationFormDTO());
        return "registration";
    }

    @PostMapping
    public  String processRegistrationPage(@ModelAttribute("data") @Valid RegistrationFormDTO data, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        if (!data.getPassword().equals(data.getRePassword())) {
            result.rejectValue("repassword", null,"Hasła muszą być zgodne");
            return "registration";
        }

        if (!registrationService.isEmailAvailable(data.getEmail())) {
            result.rejectValue("email", null, "Email jest zajęty");
            return "registration";
        }

        registrationService.registerUser(data);


        return "redirect:/login";
    }
}

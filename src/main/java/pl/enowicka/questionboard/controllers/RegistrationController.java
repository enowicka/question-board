package pl.enowicka.questionboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.enowicka.questionboard.dtos.UserRegistration;
import pl.enowicka.questionboard.services.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        UserRegistration user = new UserRegistration();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("user") @Valid UserRegistration user, BindingResult result) {

        if (!user.getPassword().equals(user.getPasswordRepeat())) {
            result.rejectValue("password", "registration.unmatchedPasswords");
        } else if (userService.checkIfMailIsTaken(user.getEmail())) {
            result.rejectValue("email", "registration.emailExists");
        } else if (userService.checkIfNameIsTaken(user.getName())) {
            result.rejectValue("name", "registration.nameExists");
        } else if (!result.hasErrors()) {
            userService.createUser(user);
            //TODO przekazanie wiadomości do ekranu logowania
            return "redirect:/login";
        }
        return "registration";

    }
}

package pl.enowicka.questionboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.enowicka.questionboard.model.User;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome(@ModelAttribute("currentUser") User currentUser) {
        return "home";
    }

}

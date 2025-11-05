package net.ikwa.fraudalert.controllers;

import net.ikwa.fraudalert.model.UserModel;
import net.ikwa.fraudalert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel user, Model model) {
        try {
            userService.saveUser(user);
            // Add success message to send back to the view
            model.addAttribute("message", "Registration successful! Welcome, " + user.getFirstName() + "!");
            // Return a success page (or same page with message)
            return "redirect:/login";
        } catch (Exception e) {
            // Add error message to model
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            // Return the same form page to let user try again
            return "register";
        }
    }
}

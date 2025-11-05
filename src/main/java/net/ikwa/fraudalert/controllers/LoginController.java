package net.ikwa.fraudalert.controllers;

import net.ikwa.fraudalert.model.UserModel;
import net.ikwa.fraudalert.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @GetMapping("/login")
    public String login() {

        return "login";
    }
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserModel user) {
        try {
            UserModel loggedIn = loginService.login(user.getEmail(), user.getPassword());
            // ✅ return JSON, not object reference
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "email", loggedIn.getEmail()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", "error", "message", e.getMessage()));
        }
    }
}


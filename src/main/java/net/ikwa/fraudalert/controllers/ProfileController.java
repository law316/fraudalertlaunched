package net.ikwa.fraudalert.controllers;

import net.ikwa.fraudalert.model.UserModel;
import net.ikwa.fraudalert.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    // ✅ Display Profile Page
    @GetMapping("/profile")
    public String userProfile(Model model) {

        return "profile";
    }



}

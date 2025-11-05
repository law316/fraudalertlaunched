package net.ikwa.fraudalert.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisplayMadePostController {
    @GetMapping("/seepost")
    public String userPostShow () {
        return "postmade";
    }
}

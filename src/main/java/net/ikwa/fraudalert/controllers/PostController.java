package net.ikwa.fraudalert.controllers;

import net.ikwa.fraudalert.model.PostModel;
import net.ikwa.fraudalert.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<PostModel> userPost(@RequestBody PostModel postModel) {
        try {
            PostModel savedPost = postService.savePost(postModel);

            return ResponseEntity.ok(savedPost);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}

package net.ikwa.fraudalert.controllers;

import net.ikwa.fraudalert.model.PostModel;
import net.ikwa.fraudalert.service.MadePostService;
import net.ikwa.fraudalert.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/madeposts")
public class FindPostController {

    @Autowired
    private MadePostService madepostService;

    // ✅ 1. Fetch ALL posts (optionally filter by keyword)
    // Example: /api/posts  OR  /api/posts?search=phone
    @GetMapping
    public ResponseEntity<List<PostModel>> getAllPosts(@RequestParam(required = false) String search) {
        List<PostModel> posts = madepostService.getAllPosts(search);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    // ✅ 2. Get by subject name (e.g., /api/posts/subject?name=Oppo Reno 11)
    @GetMapping("/subject")
    public ResponseEntity<List<PostModel>> getBySubject(@RequestParam String name) {
        List<PostModel> posts = madepostService.getBySubject(name);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    // ✅ 3. Get by category (e.g., /api/posts/category?type=Product)
    @GetMapping("/category")
    public ResponseEntity<List<PostModel>> getByCategory(@RequestParam String type) {
        List<PostModel> posts = madepostService.getByCategory(type);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);


    }

}

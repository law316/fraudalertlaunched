package net.ikwa.fraudalert.controllers;



import net.ikwa.fraudalert.model.CommentModel;
import net.ikwa.fraudalert.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Save a new comment
    @PostMapping
    public CommentModel saveComment(@RequestBody CommentModel comment) {
        return commentService.saveComment(comment);
    }

    // Fetch comments for a post
    @GetMapping("/{postId}")
    public List<CommentModel> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }
}

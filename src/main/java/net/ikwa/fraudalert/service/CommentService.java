package net.ikwa.fraudalert.service;



import net.ikwa.fraudalert.model.CommentModel;
import net.ikwa.fraudalert.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentModel saveComment(CommentModel comment) {
        if (comment.getCommentText() == null || comment.getCommentText().isEmpty()
        || comment.getCommenterName() == null || comment.getCommenterName().isEmpty()
        ) {
            System.out.println("no fields should be empty");
        }

        return commentRepository.save(comment);
    }

    public List<CommentModel> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}

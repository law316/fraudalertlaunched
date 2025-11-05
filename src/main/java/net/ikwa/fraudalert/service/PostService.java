package net.ikwa.fraudalert.service;

import net.ikwa.fraudalert.model.PostModel;
import net.ikwa.fraudalert.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostModel savePost(PostModel post) {
        // Validate required fields
        if (post.getReportCategory() == null || post.getReportCategory().isEmpty() ||
                post.getSubjectName() == null || post.getSubjectName().isEmpty() ||
                post.getPosterName() == null || post.getPosterName().isEmpty() ||
                post.getRemarkType() == null || post.getRemarkType().isEmpty() ||
                post.getMessage() == null || post.getMessage().isEmpty()) {
            throw new IllegalArgumentException("All text fields are required");
        }

        // Optional: Validate URLs
        if (post.getImageUrl() != null && !post.getImageUrl().isEmpty() &&
                !post.getImageUrl().startsWith("http")) {
            throw new IllegalArgumentException("Image URL must start with http or https");
        }

        if (post.getVideoUrl() != null && !post.getVideoUrl().isEmpty() &&
                !post.getVideoUrl().startsWith("http")) {
            throw new IllegalArgumentException("Video URL must start with http or https");
        }

        return postRepository.save(post);
    }
}

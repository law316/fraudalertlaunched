package net.ikwa.fraudalert.service;

import net.ikwa.fraudalert.model.PostModel;
import net.ikwa.fraudalert.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MadePostService {

    @Autowired
    private PostRepository postRepository;

    // Fetch all posts, with optional search
    public List<PostModel> getAllPosts(String search) {
        List<PostModel> posts = postRepository.findAll();

        if (search != null && !search.isEmpty()) {
            String query = search.toLowerCase();
            posts = posts.stream()
                    .filter(p -> (p.getSubjectName() != null && p.getSubjectName().toLowerCase().contains(query))
                            || (p.getReportCategory() != null && p.getReportCategory().toLowerCase().contains(query))
                            || (p.getPosterName() != null && p.getPosterName().toLowerCase().contains(query)))
                    .collect(Collectors.toList());
        }

        return posts;
    }

    // Get by subject name
    public List<PostModel> getBySubject(String name) {
        return postRepository.findBySubjectNameIgnoreCase(name);
    }

    // Get by report category
    public List<PostModel> getByCategory(String type) {
        return postRepository.findByReportCategoryIgnoreCase(type);
    }
}

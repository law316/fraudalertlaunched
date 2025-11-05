package net.ikwa.fraudalert.repository;

import net.ikwa.fraudalert.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {

        List<PostModel> findBySubjectNameIgnoreCase(String subjectName);
        List<PostModel> findByReportCategoryIgnoreCase(String reportCategory);


    }

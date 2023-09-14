package com.serkansahin.repository;

import com.serkansahin.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUserId(Long id);

    List<Post> findPostsByCategoryId(Long id);

    List<Post> getPostsByOrderByPublishedAtDesc();

    List<Post> getPostsByContentContainingIgnoreCase(String word);
}



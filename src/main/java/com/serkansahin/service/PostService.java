package com.serkansahin.service;

import com.serkansahin.exception.ResourcesNotFoundException;
import com.serkansahin.model.Category;
import com.serkansahin.model.Post;
import com.serkansahin.model.User;
import com.serkansahin.repository.CategoryRepository;
import com.serkansahin.repository.PostRepository;
import com.serkansahin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;

  public List<Post> findAll() {
	List<Post> listPosts = postRepository.findAll();
	if (listPosts.size() > 0) {
	  return listPosts;
	} else {
	  return new ArrayList<>();
	}
  }

  public ResponseEntity<Post> findById(Long id)  {
	Post post = postRepository.findById(id)
							  .orElseThrow(() -> new ResourcesNotFoundException("Post not found ID: " + id));
	return ResponseEntity.ok().body(post);
  }

  public Post createPost(Post post) {
	return postRepository.save(post);
  }

  public ResponseEntity<Post> updateOne(Post postInfo)  {
	Post post = postRepository.findById(postInfo.getId()).orElseThrow(() -> new ResourcesNotFoundException("Post not found ID: " + postInfo.getId()));
	return ResponseEntity.ok(postRepository.save(post));
  }

  public String deleteOne(Long id)  {
	Post post = postRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Post not found ID: " + id));
	postRepository.deleteById(id);

	return "Silme başarılı";
  }

  public  List<Post> getOnesPosts(Long id)  {
	User user = userRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("User not found ID: " + id));


	return postRepository.findPostsByUserId(id);
  }

  public List<Post>  getOneCategoryPosts(Long id)  {
	Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("User not found ID: " + id));


	return postRepository.findPostsByCategoryId(id);
  }

  public List<Post> postOrderByPublish() {
	return postRepository.getPostsByOrderByPublishedAtDesc();
  }

  public List<Post> getPostsByContentContainingIgnoreCase(String word) {
	return postRepository.getPostsByContentContainingIgnoreCase(word);
  }
}

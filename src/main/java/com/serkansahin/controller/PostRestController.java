package com.serkansahin.controller;


import com.serkansahin.model.Post;
import com.serkansahin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.serkansahin.constant.RestApiUrl.*;

@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostRestController {

  private final PostService postService;

  //http://localhost:8080/posts/
  @GetMapping("/")
  public List<Post> findAll() {
	return postService.findAll();
  }

  //http://localhost:8080/posts/id
  @GetMapping(FINDBYID + "/{id}")
  public ResponseEntity<Post> findById(@PathVariable("id") Long id) {
	return postService.findById(id);
  }

  //POST - https://localhost:8080/posts
  @PostMapping
  public Post createPost(@RequestBody Post post) {
	return postService.createPost(post);
  }

  //UPDATE - https://localhost:8080/posts/1
  @PutMapping("/{id}")
  public ResponseEntity<Post> updateOne(@PathVariable("id") Long id,
										@RequestBody Post post) {

	Post postInfo = postService.findById(id).getBody();
	if (postInfo != null) {
	  postInfo.setId(id);
	  postInfo.setTitle(post.getTitle());
	  postInfo.setContent(post.getContent());
	  postInfo.setPublishedAt(post.getPublishedAt());
	  postInfo.setUserId(post.getUserId());
	  return postService.updateOne(postInfo);
	}
	return null;
  }

  //DELETE - https://localhost:8080/posts/1
  @DeleteMapping("/{id}")
  public String deleteOne(@PathVariable("id") Long id) {
	return postService.deleteOne(id);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<Post>> getOnesPosts(@PathVariable("id") Long id) {
	return ResponseEntity.ok(postService.getOnesPosts(id));
  }

  @GetMapping("/category/{id}")
  public ResponseEntity<List<Post>> getOneCategoryPosts(@PathVariable("id") Long id) {
	return ResponseEntity.ok(postService.getOneCategoryPosts(id));
  }

  @GetMapping("/orderpublish")
  public ResponseEntity<List<Post>> postOrderByPublish() {
	return ResponseEntity.ok(postService.postOrderByPublish());
  }

  @GetMapping("/search/{word}")
  public ResponseEntity<List<Post>> getPostsByContentContainingIgnoreCase(@PathVariable(name = "word") String word) {
	return ResponseEntity.ok(postService.getPostsByContentContainingIgnoreCase(word));
  }

}

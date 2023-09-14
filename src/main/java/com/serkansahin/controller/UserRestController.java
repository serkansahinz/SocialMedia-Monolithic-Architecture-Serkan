package com.serkansahin.controller;

import com.serkansahin.dto.response.UserResponseDto;
import com.serkansahin.mapper.IUserMapper;
import com.serkansahin.model.User;
import com.serkansahin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.serkansahin.constant.RestApiUrl.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;

  //http://localhost:8080/user/
  @GetMapping("/")
  public List<User> findAll() {
	return userService.findAll();
  }

  //http://localhost:8080/user/id
  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id) {
	return userService.findById(id);
  }

  //POST - https://localhost:8080/users
  @PostMapping("/")
  public ResponseEntity<UserResponseDto> createUser(@RequestBody User user) {
	return ResponseEntity.ok(userService.createUser(user));
  }

  //UPDATE - https://localhost:8080/users/1
  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDto> updateOne(@PathVariable("id") Long id,
												   @RequestBody User user) {

	User userInfo = IUserMapper.INSTANCE.toUser(userService.findById(id).getBody());
	if (userInfo != null) {
	  userInfo.setId(id);
	  userInfo.setFirstName(user.getFirstName());
	  userInfo.setLastName(user.getLastName());
	  userInfo.setEmail(user.getEmail());
	  return ResponseEntity.ok(IUserMapper.INSTANCE.toUserResponseDto(userService.updateOne(userInfo)));
	}
	return null;
  }

  //DELETE - https://localhost:8080/users/1
  @DeleteMapping("/{id}")
  public String deleteOne(@PathVariable("id") Long id) {
	return userService.deleteOne(id);
  }

}

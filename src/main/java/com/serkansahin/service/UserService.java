package com.serkansahin.service;

import com.serkansahin.dto.response.UserResponseDto;
import com.serkansahin.exception.ResourcesNotFoundException;
import com.serkansahin.mapper.IUserMapper;
import com.serkansahin.model.User;
import com.serkansahin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> findAll() {
	List<User> listUsers = userRepository.findAll();
	if (listUsers.size() > 0) {
	  return listUsers;
	} else {
	  return new ArrayList<User>();
	}
  }

  public ResponseEntity<UserResponseDto> findById(Long id) {
	User user = userRepository.findById(id)
							  .orElseThrow(() -> new ResourcesNotFoundException("User not found ID: " + id));
	return ResponseEntity.ok().body(IUserMapper.INSTANCE.toUserResponseDto(user));
  }

  public UserResponseDto createUser(User user) {
	UserResponseDto dto = IUserMapper.INSTANCE.toUserResponseDto(userRepository.save(user));
	return dto;
  }

  public User updateOne(User userInfo) {
	User user = userRepository.findById(userInfo.getId()).orElseThrow(() -> new ResourcesNotFoundException("User not found ID: " + userInfo.getId()));
	return userRepository.save(user);
  }

  public String deleteOne(Long id) {
	User user = userRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("User not found ID: " + id));
	userRepository.deleteById(id);


	return "Sİlme başarılı";
  }

}

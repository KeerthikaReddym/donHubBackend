
package com.donHub.donHub.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.donHub.donHub.model.UserRequest;

/**
	 * Interface defining methods to manage user operations.
	 */  
  public interface UserServiceI {
	  
	 public List<UserRequest> getAllUsers();
	 public UserRequest getUserById(Long id);
	 public UserRequest getUserByEmailId(String EmailId);
	 public UserRequest validateUser(String EmailId, String password);
	 public UserRequest createUser(UserRequest data);
	 public Boolean updateUser(Long id, UserRequest data);
	 public Boolean deleteUserById(Long id);

	 public Boolean deleteAll();
	public UserRequest updateUserDetails(Long userId, String name, MultipartFile profilePicture);

	   
	  
  }
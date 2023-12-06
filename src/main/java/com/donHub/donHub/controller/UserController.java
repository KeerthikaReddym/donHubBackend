
package com.donHub.donHub.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.donHub.donHub.model.ProductRequest;
import com.donHub.donHub.model.UserRequest;
import com.donHub.donHub.service.ProductService;
import com.donHub.donHub.service.ProductServiceI;
import com.donHub.donHub.service.UserServiceI;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private ProductServiceI productService;

	/**
	 * Retrieves all users from the database.
	 *
	 * @return ResponseEntity<Object> containing a list of users if found, otherwise
	 *         a message indicating no users found
	 */

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		List<UserRequest> users = userService.getAllUsers();
		return users != null ? ResponseEntity.status(HttpStatus.OK).body(users)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found!");
	}

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param userId ID of the user to retrieve
	 * @return ResponseEntity<Object> containing the user if found, otherwise a
	 *         message indicating no user found
	 */

	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
		UserRequest user = userService.getUserById(userId);
		return user != null ? ResponseEntity.status(HttpStatus.OK).body(user)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found!");

	}

	/**
	 * Retrieves a user by their Email.
	 *
	 * @param Email ID of the user to retrieve
	 * @return ResponseEntity<Object> containing the user if found, otherwise a
	 *         message indicating no user found
	 */

	@GetMapping("/getUserByEmailId/{EmailId}")
	public ResponseEntity<Object> getUserByEmailId(@PathVariable String EmailId) {
		UserRequest user = userService.getUserByEmailId(EmailId);
		return user != null ? ResponseEntity.status(HttpStatus.OK).body(user)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found!");

	}

	@GetMapping("/validateUser")
	public ResponseEntity<Object> validateUser(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		UserRequest user = userService.validateUser(email, password);
		return user != null ? ResponseEntity.status(HttpStatus.OK).body(user)
				: ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	}

	/**
	 * Adds user data to the database.
	 *
	 * @param data User object containing data to be added
	 * @return ResponseEntity<Object> containing the created user if successful,
	 *         otherwise a message indicating no user found
	 */

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> addUser(@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) throws IOException {
		UserRequest data = new UserRequest();
		data.setEmailId(email);
		data.setPassword(password);
		if (phoneNumber != null && !phoneNumber.isEmpty()) {
			data.setPhoneNo(phoneNumber);
		}

		if (profilePicture != null && !profilePicture.isEmpty()) {
			byte[] profilePicData = profilePicture.getBytes();
			data.setProfilePic(profilePicData);
		} // send the user a code to his mail id and
		UserRequest user = userService.createUser(data); // send the user a code to his mail id and
															// prompt him to
		// send the code back
		if (user != null && user.getEmailId() != null && user.getCustomId() == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Already registered! Sign in, please!");
		else if (user != null && user.getEmailId() == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Invalid email id! Please use your Purdue email id!");

		return ResponseEntity.status(HttpStatus.OK).body(user);

	}

	/**
	 * Updates user information by their ID.
	 *
	 * @param userId     ID of the user to update
	 * @param updateUser User object containing updated information
	 * @return ResponseEntity<Object> containing the updated user if successful,
	 *         otherwise a message indicating no user found to update
	 */

	@PutMapping(path="/updateUser/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> updateUser(@PathVariable Long userId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "profilePic", required = false) MultipartFile profilePicture) throws IOException {
		//System.out.println("Update request received for user ID: " + userId);
		UserRequest existingUser = userService.getUserById(userId);
		if (existingUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found to update!");
		}
		if (name != null && !name.isEmpty()) {
			existingUser.setName(name);
		}
		if (profilePicture != null && !profilePicture.isEmpty()) {
			byte[] profilePicData = profilePicture.getBytes();
			existingUser.setProfilePic(profilePicData);
		}
		//System.out.println(existingUser);
		Boolean updateResult = userService.updateUser(userId, existingUser);
		//getting all products
		
		if (updateResult) {
	        UserRequest updatedUser = userService.getUserById(userId);
	        productService.updateProductWhenUserUpdated(updatedUser);
	       
	        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed!");
	    }
				
	}
	/**
	 * Deletes all users from the database.
	 *
	 * @return ResponseEntity<Object> with a success message if users are deleted
	 *         successfully, otherwise a message indicating no users found for
	 *         deletion
	 */

	@DeleteMapping()
	public ResponseEntity<String> deleteAllUsers() {
		Boolean areDeleted = userService.deleteAll();
		return areDeleted ? ResponseEntity.status(HttpStatus.OK).body("All users deleted successfully!")
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No users found to delete!");
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */

	@DeleteMapping({ "/{userId}" })
	public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
		Boolean isDeleted = userService.deleteUserById(userId);
		return isDeleted ? ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!")
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user found to delete!");
	}

	/*
	 * @DeleteMapping({ "/deleteUserById/{userId}" }) public ResponseEntity<Object>
	 * deleteUserById(@PathVariable String userId) { Boolean isDeleted =
	 * userService.deleteUserById(userId); return isDeleted ?
	 * ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!") :
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user found to delete!"
	 * ); }
	 */

}

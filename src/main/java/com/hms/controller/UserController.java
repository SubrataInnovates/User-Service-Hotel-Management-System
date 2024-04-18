package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.entity.User;
import com.hms.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController
{

		@Autowired
		private UserService userService;
		
		@PostMapping
		public ResponseEntity<String> saveUser(@RequestBody User user) {
		    User savedUser = userService.saveUser(user);
		    
		    String message = "User with ID " + savedUser.getUserId() + " has been created successfully.";
		    return ResponseEntity.status(HttpStatus.CREATED).body(message);
		}

		@GetMapping("/{userId}")
		public ResponseEntity<User> getUser(@PathVariable String userId)
		{
			User user = userService.getUser(userId);
			
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		
		@GetMapping
		public ResponseEntity<List<User>> getAllUsers()
		{
			List<User> allUsers = userService.getAllUsers();
			return ResponseEntity.status(HttpStatus.OK).body(allUsers);
		}
		
		@PutMapping("/{userId}")
		public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable String userId) {
		    if (!userId.equals(user.getUserId())) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID in the path does not match the user ID in the request body.");
		    }
		    
		    try {
		        User updatedUser = userService.updateUser(user);
		        String message = "User with ID " + userId + " has been updated successfully.";
		        return ResponseEntity.status(HttpStatus.OK).body(message);
		    } catch (IllegalArgumentException ex) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
		    } catch (Exception ex) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user.");
		    }
		}


		
		@DeleteMapping("/{userId}")
		public ResponseEntity<String> deleteUser(@PathVariable String userId) {
		    try {
		        userService.deleteUser(userId);
		       
		        return ResponseEntity.status(HttpStatus.OK).body("User with ID " + userId + " has been deleted successfully.");
		    } catch (IllegalArgumentException ex) {
		        
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + userId + " not found.");
		    }
		}
		
}

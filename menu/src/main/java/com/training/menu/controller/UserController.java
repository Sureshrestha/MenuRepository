package com.training.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.training.menu.models.User;
import com.training.menu.models.UserException;
import com.training.menu.service.UserService;

@RestController
public class UserController {

	private UserService userService;
	
	public UserController(@Autowired UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping("/getUserByEmailId")
	ResponseEntity<?> getUserEmail(@RequestParam String emailId)
	{
		try {
		User user = userService.findUserByEmail(emailId);
		return ResponseEntity.ok(user);
		
		}
		catch (UserException e) {
			return ResponseEntity.ok(e.getMessage());
		}
		catch (JsonMappingException e)
		{
			return ResponseEntity.ok("Mapping error");
		}
		catch(JsonProcessingException e)
		{
			return ResponseEntity.ok("JSON Processing Exception");
		}
		
	}
	
}

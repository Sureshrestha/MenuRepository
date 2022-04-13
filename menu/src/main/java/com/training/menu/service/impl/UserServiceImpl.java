package com.training.menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.menu.models.User;
import com.training.menu.models.UserException;
import com.training.menu.models.UserResponse;
import com.training.menu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private RestTemplate restTemplate;

	private ObjectMapper objectMapper;

	public UserServiceImpl(@Autowired RestTemplate restTemplate, @Autowired ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	public User findUserByEmail(String emailId) throws UserException, JsonProcessingException {
		if (emailId.equals("")) {
			throw new UserException("Email ID cannot be empty", HttpStatus.BAD_REQUEST);
		}

		String response = restTemplate.getForObject("https://reqres.in/api/users?page=2", String.class);

		UserResponse userResponse = objectMapper.readValue(response, UserResponse.class);
		for (int i = 1; i <= userResponse.getTotal_pages(); i++) {
			String url = "https://reqres.in/api/users?page="+i;
			response = restTemplate.getForObject(url, String.class);
			userResponse = objectMapper.readValue(response, UserResponse.class);
			
			for (User u : userResponse.getData()) {
				if (u.getEmail().equals(emailId))
					return u;
			}
		}
	
		throw new UserException("User cannot be found", HttpStatus.BAD_REQUEST);

	}

}

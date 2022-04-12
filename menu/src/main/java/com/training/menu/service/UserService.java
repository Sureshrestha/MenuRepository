package com.training.menu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.training.menu.models.User;

public interface UserService {

	
public User findUserByEmail(String emailId) throws JsonMappingException, JsonProcessingException;
	
}

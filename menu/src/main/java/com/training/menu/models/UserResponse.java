package com.training.menu.models;

import java.util.List;

import lombok.Data;

@Data
public class UserResponse{
	int page, per_page, total, total_pages;
	List<User> data;
	UserSupport support;

}

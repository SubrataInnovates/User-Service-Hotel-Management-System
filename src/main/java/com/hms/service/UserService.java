package com.hms.service;

import java.util.List;

import com.hms.entity.User;

public interface UserService
{
	public User  saveUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUser(String userId);
	
	public User updateUser(User user);
	
	public void deleteUser(String userId);

}

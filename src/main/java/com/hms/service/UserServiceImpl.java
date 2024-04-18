package com.hms.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entity.User;
import com.hms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) 
	{
		String uuid = UUID.randomUUID().toString();
		user.setUserId(uuid);
		return userRepository.save(user);
		
		
	}

	@Override
	public List<User> getAllUsers() 
	{
		return userRepository.findAll();
		
	}

	@Override
	public User getUser(String userId) 
	{
		return userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("No User Found !! "+userId));
		
		
	}

	@Override
	public User updateUser(User user)
	{
	    Optional<User> userOptional = userRepository.findById(user.getUserId());
	    
	    if (userOptional.isPresent())
	    {
	        User presentUser = userOptional.get();
	        
	        presentUser.setName(user.getName());
	        presentUser.setEmail(user.getEmail());
	        presentUser.setAbout(user.getAbout());
	        
	        return userRepository.save(presentUser);
	        
	    }
	    else
	    {
	        throw new IllegalArgumentException("No User found !! " + user.getUserId());
	    }
	}

		
		
	

	@Override
	public void deleteUser(String userId)
	{
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent())
		{
			userRepository.delete(user.get());
			System.out.println("User with ID " + userId + " has been deleted successfully.");
		}
		else
		{
			throw new IllegalArgumentException("No User Found !! "+userId);
		}
		
		
	}

}

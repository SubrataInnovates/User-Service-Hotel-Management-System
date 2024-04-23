package com.hms.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.hms.external.service.HotelService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hms.entity.Hotel;
import com.hms.entity.Rating;
import com.hms.entity.User;
import com.hms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private org.slf4j.Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

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
		User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("No User Found !! "+userId));
		Rating[] userRating=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{} ",userRating);
		
		List<Rating> ratings = Arrays.stream(userRating).toList();
		
		List<Rating> ratingList=ratings.stream().map(rating->
		{
			
//			ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel=forEntity.getBody();
			
			Hotel hotel =hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating; 
			
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
		
		
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

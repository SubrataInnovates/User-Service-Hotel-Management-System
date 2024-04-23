package com.hms;

import com.hms.entity.Rating;
import com.hms.external.service.RatingService;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests
{
	@Auto
	private RatingService ratingService;

	@Test
	void contextLoads()
	{
	}
//	@Test
//	void createRating()
//	{
//		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("good").build();
//		Rating rating1=ratingService.createRating(rating);
//		System.out.println("new rating created !!");
//	}

}

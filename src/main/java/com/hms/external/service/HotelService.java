package com.hms.external.service;

import com.hms.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService
{
    @GetMapping("/hotels/{hotelId}")
   public Hotel getHotel(@PathVariable String hotelId);
}

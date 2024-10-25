package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController extends BaseController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public Hotel getHotel(@PathVariable Long id){
      return hotelService.getHotel(id);
  }

  @DeleteMapping(value = "/{id}", produces = "application/json")
  public void deleteHotel(@PathVariable Long id){
    hotelService.deleteHotel(id);
  }




}


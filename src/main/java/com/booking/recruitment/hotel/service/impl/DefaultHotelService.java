package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.Constants;
import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.HotelService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
class DefaultHotelService implements HotelService {
  private final HotelRepository hotelRepository;

  @Autowired
  DefaultHotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
  }

  @Override
  public List<Hotel> getHotelsByCity(Long cityId) {
    return hotelRepository.findAll().stream()
        .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Hotel createNewHotel(Hotel hotel) {
    if (hotel.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Hotel");
    }

    return hotelRepository.save(hotel);
  }

  public Optional<Hotel> findHotel(Long id) {
    return hotelRepository.findById(id);
  }

  @Override
  public Hotel getHotel(Long id) {
    Optional<Hotel> hotel = this.findHotel(id);

    if(!hotel.isPresent() || hotel.get().isDeleted()){
      throw new ElementNotFoundException(String.format(Constants.HOTEL_NOT_FOUND, id));
    }
    return hotel.get();
  }

  @Override
  public void deleteHotel(Long id) {
    Optional<Hotel> optionalHotel = this.findHotel(id);
    if(optionalHotel.isPresent()){
      Hotel hotel = optionalHotel.get();
      hotel.setDeleted(true);
      hotelRepository.save(hotel);
    } else {
      log.error("Error element not found delete hotel");
      throw new ElementNotFoundException(String.format(Constants.HOTEL_NOT_FOUND, id));
    }
  }
}

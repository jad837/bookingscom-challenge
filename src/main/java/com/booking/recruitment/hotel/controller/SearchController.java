package com.booking.recruitment.hotel.controller;


import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.City;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.CityService;
import com.booking.recruitment.hotel.service.HotelService;
import com.booking.recruitment.hotel.util.Haversine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {
    private final HotelService hotelService;
    private final CityService cityService;

    @Autowired
    public SearchController(HotelService hotelService, CityService cityService) {
        this.hotelService = hotelService;
        this.cityService = cityService;
    }

    @GetMapping("/{cityId}/")
    public List<Hotel> searchClosestToCityCenter(@PathVariable Long cityId, @RequestParam String sortBy){
        // request param sortby is assumed to be 'distance' every time & not actually a number or anything :)
        // as it is assumed to be a string I am not going to take it into consideration currently
        // brute force it for now, go through all hotels and find closest to city
        if(!sortBy.equals("distance")){
            throw new BadRequestException("Sort by parameter is invalid");
        }
        City city = cityService.getCityById(cityId);
        List<Hotel> hotels = hotelService.getHotelsByCity(cityId);
        PriorityQueue<Hotel> hotelsRankedByCityCenter = new PriorityQueue<>(hotels.size(), new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                double hotel1Dist = Haversine.distance(city.getCityCentreLatitude(), city.getCityCentreLongitude(), o1.getLatitude(), o1.getLongitude());
                double hotel2Dist = Haversine.distance(city.getCityCentreLatitude(), city.getCityCentreLongitude(), o2.getLatitude(), o2.getLongitude());
                return Double.compare(hotel1Dist, hotel2Dist);
            }
        });
        hotelsRankedByCityCenter.addAll(hotels);
        List<Hotel> top3Hotels = new ArrayList<>(3);

        for(int i=0; i<3; i++) {
            if(!hotelsRankedByCityCenter.isEmpty())
                top3Hotels.add(hotelsRankedByCityCenter.poll());
        }
        return top3Hotels;
    }
}

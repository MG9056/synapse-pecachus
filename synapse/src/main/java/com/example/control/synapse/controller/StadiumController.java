package com.example.control.synapse.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.control.synapse.dto.request.StadiumRequest;
import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.MerchandiseRepository;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.service.StadiumService;


@RestController
@RequestMapping("/stadiums")
public class StadiumController {

    private final RestaurantRepository restaurantRepository;
    private final StadiumRepository stadiumRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final StadiumService stadiumService;
    

    public StadiumController(RestaurantRepository restaurantRepository, StadiumRepository stadiumRepository,
            MerchandiseRepository merchandiseRepository, StadiumService stadiumService) {
        this.restaurantRepository = restaurantRepository;
        this.stadiumRepository = stadiumRepository;
        this.merchandiseRepository = merchandiseRepository;
        this.stadiumService=stadiumService;
    }

    @GetMapping
    public List<Stadium> getAllStadiums()
    {
        return stadiumRepository.findAll();

    }

    @GetMapping("/{id}")
    public Stadium getStadiumById(@PathVariable Long id)

    {
        return stadiumRepository.findById(id).orElseThrow(() -> new RuntimeException("Stadium not found with id " + id));


    }

    @GetMapping("/{id}/restaurants")
    public List<Restaurant> getRestaurantsByStadiumId(@PathVariable Long id)
    {
        return restaurantRepository.findByStadiumId(id);
    }

    @GetMapping("/id/merchandise")
    public List<Merchandise> getMerchandiseByStadiumId(@PathVariable Long id)
    {
        return merchandiseRepository.findByStadiumId(id);
    }

    @PostMapping("/upload")
    public String uploadStadium(@RequestBody StadiumRequest stadiumRequest)
    {
        return stadiumService.uploadStadium(
            stadiumRequest.getCity(),
            stadiumRequest.getState(),
            stadiumRequest.getCountry(),
            stadiumRequest.getCapacity()

        );
    }



    }



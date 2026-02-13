package com.example.control.synapse.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.MerchandiseRepository;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.StadiumRepository;


@RestController
@RequestMapping("/stadiums")
public class StadiumController {

    public final RestaurantRepository restaurantRepository;
    public final StadiumRepository stadiumRepository;
    public final MerchandiseRepository merchandiseRepository;
    public StadiumController(RestaurantRepository restaurantRepository, StadiumRepository stadiumRepository,
            MerchandiseRepository merchandiseRepository) {
        this.restaurantRepository = restaurantRepository;
        this.stadiumRepository = stadiumRepository;
        this.merchandiseRepository = merchandiseRepository;
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
        return merchandiseRepository.findAllByStadiumId(id);
    }

    }



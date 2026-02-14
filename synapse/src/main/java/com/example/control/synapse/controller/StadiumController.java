package com.example.control.synapse.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.StadiumRequest;
import com.example.control.synapse.dto.request.StadiumUpdateDto;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;
import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.repository.MerchandiseRepository;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.service.MerchandiseService;
import com.example.control.synapse.service.RestaurantService;
import com.example.control.synapse.service.StadiumService;




@RestController
@RequestMapping("/stadiums")
public class StadiumController {

    private final RestaurantRepository restaurantRepository;
    private final StadiumRepository stadiumRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final StadiumService stadiumService;
    private final RestaurantService restaurantService;
    private final MerchandiseService merchandiseService;
    

    public StadiumController(RestaurantRepository restaurantRepository, StadiumRepository stadiumRepository,
            MerchandiseRepository merchandiseRepository, StadiumService stadiumService, RestaurantService restaurantService, MerchandiseService merchandiseService) {
        this.restaurantRepository = restaurantRepository;
        this.stadiumRepository = stadiumRepository;
        this.merchandiseRepository = merchandiseRepository;
        this.stadiumService=stadiumService;
        this.restaurantService=restaurantService;
        this.merchandiseService= merchandiseService;
    }

    @GetMapping
    public List<StadiumResponseDto> getAllStadiums()
    {
        return stadiumService.getAllStadiums();

    }

    @GetMapping("/{id}")
    public StadiumResponseDto getStadiumById(@PathVariable Long id)

    {
        return stadiumService.getStadiumById(id);


    }

    @GetMapping("/{id}/restaurants")
    public List<RestaurantResponseDto> getRestaurantsByStadiumId(@PathVariable Long stadiumId)
    {
        return restaurantService.getRestaurantByStadiumId(stadiumId);
    }

    @GetMapping("/id/merchandise")
    public List<MerchandiseResponseDto> getMerchandiseByStadiumId(@PathVariable Long id)
    {
        return merchandiseService.getMerchandiseByStadiumId(id);
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

    @PatchMapping("/{id}/update")
    public String updateStadium(@PathVariable Long id,
        @RequestBody StadiumUpdateDto stadiumUpdateDto )
        {
            
            return stadiumService.updateStadium(
             stadiumUpdateDto.getStadiumId(),
             stadiumUpdateDto.getCity(),
             stadiumUpdateDto.getState(),
             stadiumUpdateDto.getCountry(),
             stadiumUpdateDto.getCapacity()

            );


        }
    
        @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String,String> deleteStadium(@RequestBody String password,@PathVariable Long id, @RequestBody Long stadiumId) {
        return stadiumService.deleteStadium(id,password,stadiumId);
    }
    



    }



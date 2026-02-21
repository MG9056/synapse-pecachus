package com.example.control.synapse.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.StadiumRequest;
import com.example.control.synapse.dto.request.StadiumUpdateDto;
import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;
import com.example.control.synapse.dto.response.RestaurantResponseDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;


import com.example.control.synapse.service.MerchandiseService;
import com.example.control.synapse.service.RestaurantService;
import com.example.control.synapse.service.StadiumService;




@RestController
@RequestMapping("/stadiums")
public class StadiumController {


    private final StadiumService stadiumService;
    private final RestaurantService restaurantService;
    private final MerchandiseService merchandiseService;
    

    public StadiumController(
           StadiumService stadiumService, RestaurantService restaurantService, MerchandiseService merchandiseService) {
       
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
    public List<RestaurantResponseDto> getRestaurantsByStadiumId(@PathVariable Long id)
    {
        return restaurantService.getRestaurantByStadiumId(id);
    }

    @GetMapping("/{id}/merchandise")
    public List<MerchandiseResponseDto> getMerchandiseByStadiumId(@PathVariable Long id)
    {
        return merchandiseService.getMerchandiseByStadiumId(id);
    }

    @PostMapping("/upload")
    public  Map<String,String> uploadStadium(@RequestBody StadiumRequest stadiumRequest)
    {
        return stadiumService.uploadStadium(
            stadiumRequest.getCity(),
            stadiumRequest.getState(),
            stadiumRequest.getCountry(),
            stadiumRequest.getCapacity()

        );
    }

    @PatchMapping("/{id}/update")
    public  Map<String,String> updateStadium(@PathVariable Long id,
        @RequestBody StadiumUpdateDto stadiumUpdateDto )
        {
            
            return stadiumService.updateStadium(
             id,
             stadiumUpdateDto.getCity(),
             stadiumUpdateDto.getState(),
             stadiumUpdateDto.getCountry(),
             stadiumUpdateDto.getCapacity()

            );


        }
    
        @DeleteMapping("{stadiumiId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String,String> deleteStadium(@PathVariable Long id, @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        
        
        return stadiumService.deleteStadium(id,deleteCredentialsDto);
    }
    



    }



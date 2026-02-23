package com.example.control.synapse.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.StadiumRequest;
import com.example.control.synapse.dto.request.StadiumUpdateDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;
import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.Restaurant;
import com.example.control.synapse.repository.MerchandiseRepository;
import com.example.control.synapse.repository.RestaurantRepository;
import com.example.control.synapse.service.StadiumService;

@RestController
@RequestMapping("/stadium")
public class StadiumController {

    private final MerchandiseRepository merchandiseRepository;
    private final RestaurantRepository restaurantRepository;
    private final StadiumService stadiumService;

    public StadiumController(
            StadiumService stadiumService,
            RestaurantRepository restaurantRepository,
            MerchandiseRepository merchandiseRepository) {
        this.stadiumService = stadiumService;
        this.restaurantRepository = restaurantRepository;
        this.merchandiseRepository = merchandiseRepository;
    }

    @GetMapping("/allStadiums")
    public List<StadiumResponseDto> getAllStadiums() {
        return stadiumService.getAllStadiums();
    }

    @GetMapping("/{id}")
    public StadiumResponseDto getStadiumById(@PathVariable Long id) {
        return stadiumService.getStadiumById(id);
    }

    @GetMapping("/{id}/restaurants")
    public List<Restaurant> getRestaurantsByStadiumId(@PathVariable Long id) {
        return restaurantRepository.findByStadium_Id(id);
    }

    @GetMapping("/{id}/merchandise")
    public List<Merchandise> getMerchandiseByStadiumId(@PathVariable Long id) {
        return merchandiseRepository.findByStadium_Id(id);
    }

    @PostMapping("/upload")
    public Map<String, String> uploadStadium(@RequestBody StadiumRequest stadiumRequest) {
        return stadiumService.uploadStadium(
                stadiumRequest.getCity(),
                stadiumRequest.getState(),
                stadiumRequest.getCountry(),
                stadiumRequest.getCapacity(),
                stadiumRequest.getName(),
                stadiumRequest.getAdminEmail());
    }

    @PatchMapping("/{id}")
    public Map<String, String> updateStadium(@PathVariable Long id, @RequestBody StadiumUpdateDto stadiumUpdateDto) {
        return stadiumService.updateStadium(
                id,
                stadiumUpdateDto.getCity(),
                stadiumUpdateDto.getState(),
                stadiumUpdateDto.getCountry(),
                stadiumUpdateDto.getCapacity(),
                stadiumUpdateDto.getName(),
                stadiumUpdateDto.getAdminEmail());
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteStadium(@PathVariable Long id,
            @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        return stadiumService.deleteStadium(id, deleteCredentialsDto);
    }
}

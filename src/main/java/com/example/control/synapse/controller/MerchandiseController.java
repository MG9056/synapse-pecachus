package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.request.MerchandiseRequest;
import com.example.control.synapse.dto.request.MerchandiseUpdateDto;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;
import com.example.control.synapse.service.interfaces.IMerchandiseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/merchandise")
@RequiredArgsConstructor
public class MerchandiseController {

    private final IMerchandiseService merchandiseService;

    @GetMapping("/allMerchandise")
    public List<MerchandiseResponseDto> getAllMerchandise() {
        return merchandiseService.getAllMerchandise();
    }

    @GetMapping("/{id}")
    public MerchandiseResponseDto getMerchandiseById(@PathVariable Long id) {
        return merchandiseService.getMerchandiseById(id);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<MerchandiseResponseDto> getMerchandiseByStadiumId(@PathVariable Long stadiumId) {
        return merchandiseService.getMerchandiseByStadiumId(stadiumId);
    }

    @PostMapping("/upload")
    public Map<String, String> uploadMerchandise(@RequestBody MerchandiseRequest merchandiseRequest) {
        return merchandiseService.uploadMerchandise(
                merchandiseRequest.getName(),
                merchandiseRequest.getDescription(),
                merchandiseRequest.getPrice(),
                merchandiseRequest.getRating(),
                merchandiseRequest.getStadiumId(),
                merchandiseRequest.getSize(),
                merchandiseRequest.getType(),
                merchandiseRequest.getStock());
    }

    @PatchMapping("/{id}")
    public Map<String, String> updateMerchandise(@PathVariable Long id,
            @RequestBody MerchandiseUpdateDto merchandiseUpdateDto) {
        return merchandiseService.updateMerchandise(
                id,
                merchandiseUpdateDto.getName(),
                merchandiseUpdateDto.getDescription(),
                merchandiseUpdateDto.getPrice(),
                merchandiseUpdateDto.getRating(),
                merchandiseUpdateDto.getStadiumId(),
                merchandiseUpdateDto.getType(),
                merchandiseUpdateDto.getSize(),
                merchandiseUpdateDto.getStock());
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteMerchandise(@PathVariable Long id,
            @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        return merchandiseService.deleteMerchandise(id, deleteCredentialsDto);
    }

}

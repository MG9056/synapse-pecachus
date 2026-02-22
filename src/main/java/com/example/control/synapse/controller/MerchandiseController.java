package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.request.MerchandiseRequest;
import com.example.control.synapse.dto.request.MerchandiseUpdateDto;

import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.repository.MerchandiseRepository;
import com.example.control.synapse.service.MerchandiseService;

import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {

    private final MerchandiseRepository merchandiseRepository;

    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService, MerchandiseRepository merchandiseRepository)
    {
        this.merchandiseService= merchandiseService;
        this.merchandiseRepository = merchandiseRepository;
    }

    @GetMapping("/allMerchandise")
    public List<Merchandise> getAllMerchandise()
    {
        return merchandiseRepository.findAll();
        
    }
    
    

    
    

        @GetMapping("/{id}")
        public Merchandise getMerchandiseById(@PathVariable Long id)
        {
            return merchandiseRepository.findById(id).orElseThrow();
        }

        @GetMapping("/stadium/{stadiumId}")
        public List<Merchandise> getMerchandiseByStadiumId(@PathVariable Long stadiumId)
        {
            return merchandiseRepository.findByStadium_Id(stadiumId);
        }

        @PostMapping("/upload")
        public Map<String,String> uploadMerchandise(MerchandiseRequest merchandiseRequest)
        {
            return merchandiseService.uploadMerchandise(
                merchandiseRequest.getName(),
                merchandiseRequest.getDescription(),
                merchandiseRequest.getPrice(),
                merchandiseRequest.getRating(),
                merchandiseRequest.getStadiumId(),
                merchandiseRequest.getType(),
                merchandiseRequest.getSize(),
                merchandiseRequest.getStock()



            );
            
        }


        @PatchMapping("/{id}")
    public Map<String,String> updateMerchandise(@PathVariable Long id, @RequestBody MerchandiseUpdateDto merchandiseUpdateDto)
    {


        return merchandiseService.updateMerchandise(
            id,
            merchandiseUpdateDto.getName(),
            merchandiseUpdateDto.getDescription(),
            merchandiseUpdateDto.getPrice(),
            merchandiseUpdateDto.getRating(),
            merchandiseUpdateDto.getStadiumId(),
            merchandiseUpdateDto.getType(),
            merchandiseUpdateDto.getSize(),
            merchandiseUpdateDto.getStock()


        );
    }

     @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String,String> deleteMerchandise(@PathVariable Long id, @RequestBody DeleteCredentialsDto deleteCredentialsDto) {
        return merchandiseService.deleteMerchandise(id,deleteCredentialsDto);
    }







    
    
    
}

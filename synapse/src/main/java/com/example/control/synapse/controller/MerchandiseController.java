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


import com.example.control.synapse.dto.request.MerchandiseRequest;
import com.example.control.synapse.dto.request.MerchandiseUpdateDto;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;
import com.example.control.synapse.service.MerchandiseService;

import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService)
    {
        this.merchandiseService= merchandiseService;
    }

    @GetMapping
    public List<MerchandiseResponseDto> getAllMerchandise()
    {
        return merchandiseService.getAllMerchandise();
        
    }
    
    

    
    

        @GetMapping("/{id}")
        public MerchandiseResponseDto getMerchandiseById(@PathVariable Long id)
        {
            return merchandiseService.getMerchandiseById(id);
        }

        @PostMapping("/upload")
        public Map<String,String> uploadMerchandise(MerchandiseRequest merchandiseRequest)
        {
            return merchandiseService.uploadMerchandise(
                merchandiseRequest.getName(),
                merchandiseRequest.getDescription(),
                merchandiseRequest.getPrice(),
                merchandiseRequest.getRating(),
                merchandiseRequest.getStadiumId()



            );
            
        }


        @PatchMapping("/{id}/update")
    public Map<String,String> updateMerchandise(@PathVariable Long id, @RequestBody MerchandiseUpdateDto merchandiseUpdateDto)
    {


        return merchandiseService.updateMerchandise(
            id,
            merchandiseUpdateDto.getName(),
            merchandiseUpdateDto.getDescription(),
            merchandiseUpdateDto.getPrice(),
            merchandiseUpdateDto.getRating(),
            merchandiseUpdateDto.getStadiumId()


        );
    }

     @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String,String> deleteMerchandise(@RequestBody String password,@PathVariable Long id, @RequestBody Long merchandiseId) {
        return merchandiseService.deleteMerchandise(id,password,merchandiseId);
    }







    
    
    
}

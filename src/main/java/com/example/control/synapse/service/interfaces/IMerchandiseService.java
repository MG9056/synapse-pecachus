package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;

public interface IMerchandiseService {
    List<MerchandiseResponseDto> getAllMerchandise();

    MerchandiseResponseDto getMerchandiseById(Long id);

    List<MerchandiseResponseDto> getMerchandiseByStadiumId(Long stadiumId);

    Map<String, String> uploadMerchandise(String name, String description, Double price, Double rating,
            Long stadiumId, String size, String type, Integer stock);

    Map<String, String> updateMerchandise(Long id, String name, String description, Double price, Double rating,
            Long stadiumId, String type, String size, Integer stock);

    Map<String, String> deleteMerchandise(Long id, DeleteCredentialsDto deleteCredentialsDto);
}

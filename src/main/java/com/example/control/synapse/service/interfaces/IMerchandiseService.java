package com.example.control.synapse.service.interfaces;

import java.util.List;
import java.util.Map;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;

public interface IMerchandiseService {
        MerchandiseResponseDto getMerchandiseById(Long id);

        List<MerchandiseResponseDto> getAllMerchandise();

        List<MerchandiseResponseDto> getMerchandiseByStadiumId(Long stadiumId);

        Map<String, String> uploadMerchandise(String name, String description, double price, double rating,
                        Long stadiumId, String size, String type, Integer stock);

        Map<String, String> updateMerchandise(Long merchandiseId, String name, String description, Double price,
                        Double rating, Long stadiumId, String type, String size, Integer stock);

        Map<String, String> deleteMerchandise(Long merchandiseId, DeleteCredentialsDto deleteCredentialsDto);
}

package com.example.control.synapse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseResponseDto {

    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private Long stadiumId;
    private String type;
    private String size;
    private Integer stock;
}

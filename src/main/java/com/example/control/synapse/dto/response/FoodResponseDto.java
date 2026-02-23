package com.example.control.synapse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponseDto {

    private Long id;
    private String name;
    private Long restaurantId;
    private float price;
    private float rating;
    private String type;
    private String diet;
    private Integer stock;
}

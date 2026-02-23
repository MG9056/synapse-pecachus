package com.example.control.synapse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StadiumResponseDto {

    private Long id;
    private String name;
    private String city;
    private String state;
    private String country;
    private Integer capacity;
    private String adminEmail;
}

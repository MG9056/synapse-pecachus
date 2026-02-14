package com.example.control.synapse.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponseDto {

    private Long id;

    private String row;
    private Integer seatNo;

    private String category;
    private Long stadiumId;

    private Boolean available;

    private Boolean isWomen;
    private Boolean isAccessible;

    private Boolean closeToWc;
    private Boolean closeToFoodStall;
    private Boolean closeToExit;
}

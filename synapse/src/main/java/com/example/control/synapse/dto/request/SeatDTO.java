package com.example.control.synapse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    
    private Long id;
    
    @NotBlank(message = "Row is required")
    private String row;
    
    @NotNull(message = "Seat number is required")
    private Integer seatNo;
    
    private Boolean closeToWc;
    private Boolean closeToFoodStall;
    private Boolean closeToExit;
    private Boolean isWomen;
    private Boolean isAccessible;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotNull(message = "Stadium ID is required")
    private Long stadiumId;
}
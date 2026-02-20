package com.example.control.synapse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String row;
    private Integer seatNo;
    private Boolean closeToWc;
    private Boolean closeToFoodStall;
    private Boolean closeToExit;
    private Boolean isWomen;
    private Boolean isAccessible;
    private String category;
    private Stadium stadiumId;
    public Seat() {}
    public Seat(String row, Integer seatNo, Boolean closeToWc, Boolean closeToFoodStall, Boolean closeToExit,
            Boolean isWomen, Boolean isAccessible, String category,Stadium stadiumId) {
        this.row = row;
        this.seatNo = seatNo;
        this.closeToWc = closeToWc;
        this.closeToFoodStall = closeToFoodStall;
        this.closeToExit = closeToExit;
        this.isWomen = isWomen;
        this.isAccessible = isAccessible;
        this.category = category;
        this.stadiumId=stadiumId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRow() {
        return row;
    }
    public void setRow(String row) {
        this.row = row;
    }
    public Integer getSeatNo() {
        return seatNo;
    }
    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }
    public Boolean getCloseToWc() {
        return closeToWc;
    }
    public void setCloseToWc(Boolean closeToWc) {
        this.closeToWc = closeToWc;
    }
    public Boolean getCloseToFoodStall() {
        return closeToFoodStall;
    }
    public void setCloseToFoodStall(Boolean closeToFoodStall) {
        this.closeToFoodStall = closeToFoodStall;
    }
    public Boolean getCloseToExit() {
        return closeToExit;
    }
    public void setCloseToExit(Boolean closeToExit) {
        this.closeToExit = closeToExit;
    }
    public Boolean getIsWomen() {
        return isWomen;
    }
    public void setIsWomen(Boolean isWomen) {
        this.isWomen = isWomen;
    }
    public Boolean getIsAccessible() {
        return isAccessible;
    }
    public void setIsAccessible(Boolean isAccessible) {
        this.isAccessible = isAccessible;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Stadium getStadiumId() {
        return stadiumId;
    }
    public void setStadiumId(Stadium stadiumId) {
        this.stadiumId = stadiumId;
    }
    
    


}

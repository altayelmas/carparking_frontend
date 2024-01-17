package com.aquadrat.parkplatzfrontend.model.dto;

public class ParkingLotUpdateRequest {
    private String name;
    private String street;
    private String city;
    private String postCode;
    private String country;
    private Integer numberOfSlots;

    public ParkingLotUpdateRequest(String name, String street, String city, String postCode, String country, Integer numberOfSlots) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.numberOfSlots = numberOfSlots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(Integer numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }
}

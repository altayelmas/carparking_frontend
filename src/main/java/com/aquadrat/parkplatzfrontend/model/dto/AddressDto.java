package com.aquadrat.parkplatzfrontend.model.dto;

public class AddressDto {
    private Integer addressID;
    private String street;
    private String city;
    private String postCode;
    private String country;
    private Integer lotID;

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
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

    public Integer getLotID() {
        return lotID;
    }

    public void setLotID(Integer lotID) {
        this.lotID = lotID;
    }


}

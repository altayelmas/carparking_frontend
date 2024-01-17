package com.aquadrat.parkplatzfrontend.model.dto;

import java.util.List;

public class ParkingLotDto {
    private Integer lotID;
    private String name;
    private AddressDto addressDto;
    private List<ParkSlotDto> parkSlotDtoList;

    public Integer getLotID() {
        return lotID;
    }

    public void setLotID(Integer lotID) {
        this.lotID = lotID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public List<ParkSlotDto> getParkSlotDtoList() {
        return parkSlotDtoList;
    }

    public void setParkSlotDtoList(List<ParkSlotDto> parkSlotDtoList) {
        this.parkSlotDtoList = parkSlotDtoList;
    }
}

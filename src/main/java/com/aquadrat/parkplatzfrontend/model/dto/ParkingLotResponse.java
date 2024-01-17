package com.aquadrat.parkplatzfrontend.model.dto;

public class ParkingLotResponse {
    private ParkingLotDto parkingLotDto;
    private boolean isSuccess;
    private String message;

    public ParkingLotDto getParkingLotDto() {
        return parkingLotDto;
    }

    public void setParkingLotDto(ParkingLotDto parkingLotDto) {
        this.parkingLotDto = parkingLotDto;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

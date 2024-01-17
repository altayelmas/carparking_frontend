package com.aquadrat.parkplatzfrontend.model.dto;

public class ParkSlotDto {

    private Integer slotID;
    private boolean isAvailable;
    private Integer lotID;

    public Integer getSlotID() {
        return slotID;
    }

    public void setSlotID(Integer slotID) {
        this.slotID = slotID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Integer getLotID() {
        return lotID;
    }

    public void setLotID(Integer lotID) {
        this.lotID = lotID;
    }
}

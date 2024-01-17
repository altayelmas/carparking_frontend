package com.aquadrat.parkplatzfrontend.model.dto;

import com.aquadrat.parkplatzfrontend.model.enums.VehicleType;

public class TicketCreateRequest {
    private String licencePlate;
    private VehicleType vehicleType;
    private Integer lotID;
    private Integer slotID;

    public TicketCreateRequest(String licencePlate, VehicleType vehicleType, Integer lotID, Integer slotID) {
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.lotID = lotID;
        this.slotID = slotID;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getLotID() {
        return lotID;
    }

    public void setLotID(Integer lotID) {
        this.lotID = lotID;
    }

    public Integer getSlotID() {
        return slotID;
    }

    public void setSlotID(Integer slotID) {
        this.slotID = slotID;
    }
}

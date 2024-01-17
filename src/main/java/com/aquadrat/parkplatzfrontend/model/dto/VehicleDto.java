package com.aquadrat.parkplatzfrontend.model.dto;

import com.aquadrat.parkplatzfrontend.model.enums.VehicleType;

public class VehicleDto {
    private String licencePlate;
    private VehicleType vehicleType;
    private Integer ticketID;

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

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }
}

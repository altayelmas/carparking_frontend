package com.aquadrat.parkplatzfrontend.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class VehicleTableData {
    private SimpleStringProperty licencePlate;
    private SimpleStringProperty vehicleType;
    private SimpleIntegerProperty ticketID;

    public VehicleTableData(String licencePlate, String vehicleType, Integer ticketID) {
        this.licencePlate = new SimpleStringProperty(licencePlate);
        this.vehicleType = new SimpleStringProperty(vehicleType);
        this.ticketID = new SimpleIntegerProperty(ticketID);
    }

    public String getLicencePlate() {
        return licencePlate.get();
    }


    public void setLicencePlate(String licencePlate) {
        this.licencePlate.set(licencePlate);
    }

    public String getVehicleType() {
        return vehicleType.get();
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType.set(vehicleType);
    }

    public int getTicketID() {
        return ticketID.get();
    }

    public void setTicketID(int ticketID) {
        this.ticketID.set(ticketID);
    }
}

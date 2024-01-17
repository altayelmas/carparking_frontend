package com.aquadrat.parkplatzfrontend.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TicketTableData {
    SimpleIntegerProperty ticketID;
    SimpleStringProperty entryDate;
    SimpleStringProperty exitDate;
    SimpleBooleanProperty isValid;
    SimpleStringProperty licencePlate;
    SimpleIntegerProperty slotID;
    SimpleIntegerProperty lotID;

    public TicketTableData(Integer ticketID, String entryDate, String exitDate, Boolean isValid, String licencePlate, Integer slotID, Integer lotID) {
        this.ticketID = new SimpleIntegerProperty(ticketID);
        this.entryDate = new SimpleStringProperty(entryDate);
        this.exitDate = new SimpleStringProperty(exitDate);
        this.isValid = new SimpleBooleanProperty(isValid);
        this.licencePlate = new SimpleStringProperty(licencePlate);
        this.slotID = new SimpleIntegerProperty(slotID);
        this.lotID = new SimpleIntegerProperty(lotID);
    }

    public int getTicketID() {
        return ticketID.get();
    }

    public void setTicketID(int ticketID) {
        this.ticketID.set(ticketID);
    }

    public String getEntryDate() {
        return entryDate.get();
    }

    public void setEntryDate(String entryDate) {
        this.entryDate.set(entryDate);
    }

    public String getExitDate() {
        return exitDate.get();
    }

    public void setExitDate(String exitDate) {
        this.exitDate.set(exitDate);
    }

    public boolean isIsValid() {
        return isValid.get();
    }

    public void setIsValid(boolean isValid) {
        this.isValid.set(isValid);
    }

    public String getLicencePlate() {
        return licencePlate.get();
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate.set(licencePlate);
    }

    public int getSlotID() {
        return slotID.get();
    }

    public void setSlotID(int slotID) {
        this.slotID.set(slotID);
    }

    public int getLotID() {
        return lotID.get();
    }

    public void setLotID(int lotID) {
        this.lotID.set(lotID);
    }
}

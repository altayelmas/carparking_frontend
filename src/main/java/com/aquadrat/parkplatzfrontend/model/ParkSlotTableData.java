package com.aquadrat.parkplatzfrontend.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParkSlotTableData {
    SimpleIntegerProperty slotID;
    SimpleBooleanProperty isAvailable;
    SimpleIntegerProperty lotID;

    public ParkSlotTableData(Integer slotID, Boolean isAvailable, Integer lotID) {
        this.slotID = new SimpleIntegerProperty(slotID);
        this.isAvailable = new SimpleBooleanProperty(isAvailable);
        this.lotID = new SimpleIntegerProperty(lotID);
    }

    public int getSlotID() {
        return slotID.get();
    }

    public void setSlotID(int slotID) {
        this.slotID.set(slotID);
    }

    public boolean isIsAvailable() {
        return isAvailable.get();
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable.set(isAvailable);
    }

    public int getLotID() {
        return lotID.get();
    }

    public void setLotID(int lotID) {
        this.lotID.set(lotID);
    }
}

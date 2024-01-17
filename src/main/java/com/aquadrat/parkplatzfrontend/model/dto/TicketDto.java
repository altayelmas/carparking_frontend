package com.aquadrat.parkplatzfrontend.model.dto;

import java.util.Date;
public class TicketDto {

    private Integer ticketID;
    private Date entryDate;
    private Date exitDate;
    private boolean isValid;
    private String licencePlate;
    private Integer slotID;
    private Integer lotID;

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Integer getSlotID() {
        return slotID;
    }

    public void setSlotID(Integer slotID) {
        this.slotID = slotID;
    }

    public Integer getLotID() {
        return lotID;
    }

    public void setLotID(Integer lotID) {
        this.lotID = lotID;
    }
}

package com.aquadrat.parkplatzfrontend.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ParkingLotTableData {
    SimpleIntegerProperty lotID;
    SimpleStringProperty name;
    SimpleIntegerProperty addressID;
    SimpleStringProperty street;
    SimpleStringProperty city;
    SimpleStringProperty postCode;
    SimpleStringProperty country;

    public ParkingLotTableData(Integer lotID, String name, Integer addressID, String street, String city, String postCode, String country) {
        this.lotID = new SimpleIntegerProperty(lotID);
        this.name = new SimpleStringProperty(name);
        this.addressID = new SimpleIntegerProperty(addressID);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.postCode = new SimpleStringProperty(postCode);
        this.country = new SimpleStringProperty(country);
    }

    public Integer getLotID() {
        return this.lotID.get();
    }

    public void setLotID(Integer lotID) {
        this.lotID.set(lotID);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Integer getAddressID() {
        return this.addressID.get();
    }

    public void setAddressID(Integer addressID) {
        this.addressID.set(addressID);
    }

    public String getStreet() {
        return this.street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getCity() {
        return this.city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getPostCode() {
        return this.postCode.get();
    }

    public void setPostCode(String postCode) {
        this.postCode.set(postCode);
    }

    public String getCountry() {
        return this.country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }
}

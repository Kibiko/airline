package com.bnta.airline.models;

import java.util.List;

public class PassengerDTO {

    private String name;
    private String phoneNumber;
    private String email;
    private List<Long> flightIds;

    public PassengerDTO(String name, String phoneNumber, String email, List<Long> flightIds){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.flightIds = flightIds;
    }

    public PassengerDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(List<Long> flightIds) {
        this.flightIds = flightIds;
    }
}

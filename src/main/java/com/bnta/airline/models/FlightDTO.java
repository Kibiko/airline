package com.bnta.airline.models;

public class FlightDTO {

    private String destination;
    private int capacity;
    private String dateAndTime; //"2018-12-10T13:45:00.000Z"

    public FlightDTO(String destination, int capacity, String dateAndTime){
        this.destination = destination;
        this.capacity = capacity;
        this.dateAndTime = dateAndTime;
    }

    public FlightDTO(){

    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}

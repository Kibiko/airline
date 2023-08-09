package com.bnta.airline.services;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.models.PassengerDTO;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerServices {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public Optional<Passenger> findPassenger(Long id){
        return passengerRepository.findById(id);
    }

    public List<Passenger> findAllPassengers(){
        return passengerRepository.findAll();
    }

    public Passenger addPassenger(PassengerDTO passengerDTO){ //new passenger starts with no flights
        Passenger passenger = new Passenger(
                passengerDTO.getName(),
                passengerDTO.getPhoneNumber(),
                passengerDTO.getEmail()
        );
        List<Long> flightIds = passengerDTO.getFlightIds();
        for (Long flightId : flightIds){
            Flight flight = flightRepository.findById(flightId).get();
            passenger.addFlight(flight);
        }
        passengerRepository.save(passenger);
        return passenger;
    }

    public Passenger addFlightToPassenger(PassengerDTO passengerDTO, Long id){
        Passenger passengerToUpdate = passengerRepository.findById(id).get();

        for (Long flightId : passengerDTO.getFlightIds()){
            Flight flight = flightRepository.findById(flightId).get();
            passengerToUpdate.addFlight(flight);
        }
        return passengerRepository.save(passengerToUpdate);
    }

    public Passenger removeFlightFromPassenger(Passenger passenger, Long flightId){
        passenger.removeFlight(flightRepository.findById(flightId).get());
        return passengerRepository.save(passenger);
    }

    public Passenger updateAllPassengerDetails(PassengerDTO passengerDTO, Long id){
        Passenger passengerToUpdate = passengerRepository.findById(id).get();
        passengerToUpdate.setName(passengerDTO.getName());
        passengerToUpdate.setEmail(passengerDTO.getEmail());
        passengerToUpdate.setPhoneNumber(passengerDTO.getPhoneNumber());
        passengerToUpdate.setFlights(new ArrayList<Flight>());
        if(passengerDTO.getFlightIds() != null) {
            for (Long flightId : passengerDTO.getFlightIds()) {
                Flight flight = flightRepository.findById(flightId).get();
                passengerToUpdate.addFlight(flight);
            }
        }
        return passengerRepository.save(passengerToUpdate);
    }

}

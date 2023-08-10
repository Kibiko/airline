package com.bnta.airline.controllers;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.models.PassengerDTO;
import com.bnta.airline.services.FlightServices;
import com.bnta.airline.services.PassengerServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/passengers")
public class PassengerController {

    @Autowired
    PassengerServices passengerServices;

    @Autowired
    FlightServices flightServices;

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        return new ResponseEntity<>(passengerServices.findAllPassengers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable Long id){
        Optional<Passenger> passenger = passengerServices.findPassenger(id);
        if(passenger.isPresent()){
            return new ResponseEntity<>(passenger.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Passenger> addPassenger(@RequestBody PassengerDTO passengerDTO){
        Passenger savedPassenger = passengerServices.addPassenger(passengerDTO);
        return new ResponseEntity<>(savedPassenger,HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Passenger> addFlightToPassenger(@RequestBody PassengerDTO passengerDTO, @PathVariable Long id) {
        Optional<Passenger> bookedPassenger = passengerServices.findPassenger(id); //finds if passenger exists
        if(bookedPassenger.isPresent()){
            for (Long flightId : passengerDTO.getFlightIds()) { //if present then loop through List<Long> flightIds
                Flight flight = flightServices.findFlight(flightId).get();
                if (flight.getPassengers().size() == flight.getCapacity() || bookedPassenger.get().getFlights().contains(flight)) { //check capacity and if passenger is already on flight
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT); //fail if capacity full or already booked
                } else {
                    passengerServices.addFlightToPassenger(passengerDTO, id); //else add flight to passenger
                }
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); //fail if no passenger found by id
        }
        return new ResponseEntity<>(bookedPassenger.get(),HttpStatus.OK); //pass if everything goes well
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Passenger> updateAllPassengerDetails(@RequestBody PassengerDTO passengerDTO, @PathVariable Long id){
        Passenger updatedPassenger = passengerServices.updateAllPassengerDetails(passengerDTO, id);
        return new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
    }


}

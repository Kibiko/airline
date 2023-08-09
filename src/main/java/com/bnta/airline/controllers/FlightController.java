package com.bnta.airline.controllers;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.FlightDTO;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.services.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/flights")
public class FlightController {

    @Autowired
    FlightServices flightServices;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam Optional<String> destination){
        List<Flight> flights = new ArrayList<>();
        if (destination.isPresent()){
            if(flightServices.findFlightByDestination(destination.get()).isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            } else {
                flights = flightServices.findFlightByDestination(destination.get());//filters flights by destination
            }
        } else{
            flights = flightServices.findAllFlights();
        }
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable long id){
        Optional<Flight> flight = flightServices.findFlight(id);
        if(flight.isPresent()){
            return new ResponseEntity<>(flight.get(), HttpStatus.FOUND);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody FlightDTO flightDTO){
        return new ResponseEntity<>(flightServices.addFlight(flightDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Flight> cancelFlight(@PathVariable long id){
        Optional<Flight> flightToCancel = flightServices.findFlight(id);
        if(flightToCancel.isPresent()){
            flightServices.cancelFlight(flightToCancel.get());
            return new ResponseEntity<>(flightToCancel.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}

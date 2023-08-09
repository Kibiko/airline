package com.bnta.airline.services;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.FlightDTO;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.valueOf;

@Service
public class FlightServices {

    @Autowired
    FlightRepository flightRepository;

    public Optional<Flight> findFlight(Long id){
        return flightRepository.findById(id);
    }

    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    public List<Flight> findFlightByDestination(String destination){
        return flightRepository.findByDestination(destination);
    }

    public Flight addFlight(FlightDTO flightDTO){
        String[] datesAndTimes = flightDTO.getDateAndTime().split("[-T:.Z]"); //"2018-12-10T13:45:00.000Z"
        int dateYear = valueOf(datesAndTimes[0]);
        int dateMonth = valueOf(datesAndTimes[1]);
        int dateDay = valueOf(datesAndTimes[2]);
        int timeHour = valueOf(datesAndTimes[3]);
        int timeMinute = valueOf(datesAndTimes[4]);
        int timeSecond = valueOf(datesAndTimes[5]);

        Flight newFlight = new Flight(
                flightDTO.getDestination(),
                flightDTO.getCapacity(),
                LocalDate.of(dateYear,dateMonth,dateDay),
                LocalTime.of(timeHour,timeMinute,timeSecond));

        return flightRepository.save(newFlight);
    }

    public void cancelFlight(Flight flight){
        List<Passenger> passengersAffected = flight.getPassengers();
        for (Passenger passenger : passengersAffected){
            passenger.removeFlight(flight);
        }
        flightRepository.delete(flight);
    }

}

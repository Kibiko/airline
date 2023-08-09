package com.bnta.airline.components;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Component
public class Dataloader implements ApplicationRunner {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public Dataloader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //FLIGHTS

        Flight flight1 = new Flight("Hong Kong",
                150, LocalDate.of(2023,9,21),
                LocalTime.of(17,25));
        Flight flight2 = new Flight("Billund",
                150, LocalDate.of(2023,11,9),
                LocalTime.of(10,15));
        Flight flight3 = new Flight("Munich",
                150, LocalDate.of(2023,12,28),
                LocalTime.of(14,34));
        Flight flight4 = new Flight("Barcelona",
                150, LocalDate.of(2024,1,5),
                LocalTime.of(8,32));
        Flight flight5 = new Flight("Gliwice",
                150, LocalDate.of(2024,3,14),
                LocalTime.of(4,15));

        flightRepository.saveAll(Arrays.asList(
                flight1,
                flight2,
                flight3,
                flight4,
                flight5
        ));

        Passenger passenger1 = new Passenger("Kevin",
                "07745727833",
                "kevin@gmail.com");
        passenger1.addFlight(flight1);
        passenger1.addFlight(flight3);

        Passenger passenger2 = new Passenger("Lance",
                "07134345634",
                "lance@gmail.com");
        passenger2.addFlight(flight5);
        passenger2.addFlight(flight4);

        Passenger passenger3 = new Passenger("Jess",
                "07768566465",
                "jess@gmail.com");
        passenger3.addFlight(flight2);
        passenger3.addFlight(flight3);

        Passenger passenger4 = new Passenger("Louise",
                "07123143252",
                "louise@gmail.com");
        passenger4.addFlight(flight3);
        passenger4.addFlight(flight5);

        Passenger passenger5 = new Passenger("Pelly",
                "07252446777",
                "pelly@gmail.com");
        passenger5.addFlight(flight2);
        passenger5.addFlight(flight3);

        passengerRepository.saveAll(Arrays.asList(
                passenger1,
                passenger2,
                passenger3,
                passenger4,
                passenger5
        ));
    }
}

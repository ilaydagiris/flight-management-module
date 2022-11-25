package com.example.demo.Controller;

import com.example.demo.Entity.FlightEntity;
import com.example.demo.Repository.FlightRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path = "flight")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping (path = "flights")
    public ResponseEntity<?> getFlights(@RequestParam(required = false) String flightNo ){
        try{
            List<FlightEntity> flightEntityList= new ArrayList<>();
            if (flightNo == null)
                flightRepository.findAll().forEach((flightEntityList::add));
            else
                flightRepository.findByFlightNo(flightNo).forEach((flightEntityList::add));
            if(flightEntityList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(flightEntityList, HttpStatus.OK);

        } catch (Exception hata){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (path = "flights/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable("id")Long ID){
        Optional<FlightEntity> flightVeri= flightRepository.findById(ID);
        if (flightVeri.isPresent()) {
            return new ResponseEntity<>(flightVeri.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping (path = "flights")
    public ResponseEntity<FlightEntity> createFlight(@RequestBody FlightEntity flight) {
        try {
            FlightEntity _flight = flightRepository.save(new FlightEntity(flight.getFlightID(), flight.getAirline(),
                    flight.getFlightNo(), flight.getAircraft(), flight.getFlightLeg(), flight.getFlightDate(),
                    flight.getSystemAirport(), flight.getOriginStation(), LocalDateTime.now(),
                    LocalDateTime.now(), flight.getFlightUpdateUser()));
            return new ResponseEntity<>(_flight, HttpStatus.CREATED);
        } catch (Exception hata) {
            System.out.println(hata.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (path = "flights/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable("id")Long ID, @RequestBody FlightEntity flight){
        Optional <FlightEntity> flightVeri = flightRepository.findById(ID);
        if (flightVeri.isPresent()){
            FlightEntity _flight = flightVeri.get();
            _flight.setAirline(flight.getAirline());
            _flight.setFlightNo(flight.getFlightNo());
            _flight.setAircraft(flight.getAircraft());
            _flight.setFlightLeg(flight.getFlightLeg());
            _flight.setFlightDate(flight.getFlightDate());
            _flight.setSystemAirport(flight.getSystemAirport());
            _flight.setOriginStation(flight.getOriginStation());
            _flight.setFlightUpdateTime(LocalDateTime.now());
            _flight.setFlightUpdateUser(flight.getFlightUpdateUser());
            return new ResponseEntity<>(flightRepository.save(_flight),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (path = "flight/{id}")
    public ResponseEntity<HttpStatus> deleteFlight(@PathVariable("id")Long ID){
        try {
            flightRepository.deleteById(ID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception hata){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "flights")
    public ResponseEntity<HttpStatus> deleteAllFlights() {
        try {
            flightRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception hata) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}



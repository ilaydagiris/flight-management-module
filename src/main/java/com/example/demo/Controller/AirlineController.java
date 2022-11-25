package com.example.demo.Controller;

import com.example.demo.Entity.AirlineEntity;
//import com.example.demo.Entity.FlightEntity;
import com.example.demo.Repository.AirlineRepository;
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
@RequestMapping (path = "airline")

public class AirlineController {

    @Autowired
    private AirlineRepository airlineRepository;

    @GetMapping (path = "airlines")
    public ResponseEntity<?> getAirlines(@RequestParam(required = false) String airlineCode){
        try{
            List<AirlineEntity> airlineEntityList= new ArrayList<>();
            if (airlineCode == null)
                airlineEntityList = airlineRepository.findAll();
            else
                airlineRepository.findByAirlineCode(airlineCode).forEach((airlineEntityList::add));
            if(airlineEntityList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(airlineEntityList, HttpStatus.OK);

        } catch (Exception hata){
            System.out.println(hata.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (path = "airlines/{id}")
    public ResponseEntity<?> getAirlineById(@PathVariable("id")Long ID){
        Optional<AirlineEntity> airlineVeri= airlineRepository.findById(ID);
        if (airlineVeri.isPresent()) {
            return new ResponseEntity<>(airlineVeri.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping (path = "airlines")
    public ResponseEntity<AirlineEntity> createUser(@RequestBody AirlineEntity airline) {
        try {
            AirlineEntity _airline = airlineRepository.save(new AirlineEntity( airline.getAirlineCode(),
                    airline.getAirlineDescription(), LocalDateTime.now(), airline.getAirlineID(),
                    LocalDateTime.now(), airline.getAirlineUpdateUser()));

            return new ResponseEntity<>(_airline, HttpStatus.CREATED);
        } catch (Exception hata) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (path = "airlines/{id}")
    public ResponseEntity<?> updateAirline(@PathVariable("id")Long ID, @RequestBody AirlineEntity airline){
        Optional <AirlineEntity> airlineVeri = airlineRepository.findById(ID);
        if (airlineVeri.isPresent()){
            AirlineEntity _airline = airlineVeri.get(); // id yi aşağıdan kaldırdık çünkü tekrar yazsak sistem yeni bir id vericem diye algılayacak, halbuki yukarıdan alması lazım. aksi halde postmanda json a id vermeliyiz
            _airline.setAirlineCode(airline.getAirlineCode());
            _airline.setAirlineDescription(airline.getAirlineDescription());
            _airline.setAirlineUpdateTime(LocalDateTime.now());
            _airline.setAirlineUpdateUser(airline.getAirlineUpdateUser());
            return new ResponseEntity<>(airlineRepository.save(_airline),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (path = "airline/{id}")
    public ResponseEntity<HttpStatus> deleteAirline(@PathVariable("id")Long ID){
        try {
            airlineRepository.deleteById(ID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception hata){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "airlines")
    public ResponseEntity<HttpStatus> deleteAllAirlines() {
        try {
            airlineRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception hata) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



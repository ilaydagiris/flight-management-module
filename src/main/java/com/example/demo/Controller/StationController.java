package com.example.demo.Controller;

import com.example.demo.Entity.AircraftEntity;
import com.example.demo.Entity.FlightEntity;
import com.example.demo.Entity.StationEntity;
import com.example.demo.Entity.UserSinifi;
import com.example.demo.Repository.StationRepository;
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
@RequestMapping (path = "station")

public class StationController {

    @Autowired
    private StationRepository stationRepository;

    @GetMapping (path = "stations")
    public ResponseEntity<?> getStations(@RequestParam(required = false) String stationCode){
        try{
            //List<StationEntity> stationEntityList= new ArrayList<>();
            var stationEntityList = new ArrayList<>();
            //if (stationCode == null && stationDescription == null)
            if (stationCode == null)
                stationRepository.findAll().forEach((stationEntityList::add));
                //if (stationCode == null || stationDescription == null)
                //   stationRepository.findAll().forEach((stationEntityList::add));
            else
                stationRepository.findByStationCode(stationCode).forEach((stationEntityList::add));
            if(stationEntityList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(stationEntityList, HttpStatus.OK);
        } catch (Exception hata){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (path = "stations/{id}")
    public ResponseEntity<?> getStationById(@PathVariable("id")Long ID){
        Optional<StationEntity> stationVeri= stationRepository.findById(ID);
        if (stationVeri.isPresent()) {
            return new ResponseEntity<>(stationVeri.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping (path = "stations")
    public ResponseEntity<StationEntity> createStation(@RequestBody StationEntity station) {
        try {
            StationEntity _station = stationRepository.save(new StationEntity(station.getStationID(),
                    station.getStationCode(), station.getStationDescription(), LocalDateTime.now(),
                    LocalDateTime.now(), station.getStationUpdateUser()));
            return new ResponseEntity<>(_station, HttpStatus.CREATED);
        } catch (Exception hata) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (path = "stations/{id}")
    public ResponseEntity<?> updateStation(@PathVariable("id")Long ID, @RequestBody StationEntity station){
        Optional <StationEntity> stationVeri = stationRepository.findById(ID);
        if (stationVeri.isPresent()){
            StationEntity _station = stationVeri.get();
            _station.setStationCode(station.getStationCode());
            _station.setStationDescription(station.getStationDescription());
           // _station.setStationCreTime(LocalDateTime.now()); bir kere veriliyor sisteme
            _station.setStationUpdateTime(LocalDateTime.now());
            _station.setStationUpdateUser(station.getStationUpdateUser());
            return new ResponseEntity<>(stationRepository.save(_station),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (path = "users/{id}")
    public ResponseEntity<HttpStatus> deleteStation(@PathVariable("id")Long ID){
        try {
            stationRepository.deleteById(ID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception hata){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "stations")
    public ResponseEntity<HttpStatus> deleteAllStations() {
        try {
            stationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception hata) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



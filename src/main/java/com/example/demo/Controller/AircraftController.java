package com.example.demo.Controller;

import com.example.demo.Entity.AircraftEntity;
import com.example.demo.Repository.AircraftRepository;
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
@RequestMapping (path = "aircraft")

public class AircraftController {

    @Autowired
    private AircraftRepository aircraftRepository;

    @GetMapping (path = "aircrafts")
    public ResponseEntity<?> getAircrafts(@RequestParam(required = false) String aircraftCode){
        try{
            List<AircraftEntity> aircraftEntityList= new ArrayList<>();
            if (aircraftCode == null)
                aircraftRepository.findAll().forEach((aircraftEntityList::add));
            else
                aircraftRepository.findByAircraftCode(aircraftCode).forEach((aircraftEntityList::add));
            if(aircraftEntityList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(aircraftEntityList, HttpStatus.OK);

        } catch (Exception hata){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (path = "aircrafts/{id}")
    public ResponseEntity<?> getAircraftById(@PathVariable("id")Long ID){
        Optional<AircraftEntity> aircraftVeri= aircraftRepository.findById(ID);
        if (aircraftVeri.isPresent()) {
            return new ResponseEntity<>(aircraftVeri.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping (path = "aircrafts")
    public ResponseEntity<AircraftEntity> createUser(@RequestBody AircraftEntity aircraft) {
        try {
            AircraftEntity _aircraft = aircraftRepository.save(new AircraftEntity(aircraft.getAircraftID(),
                    aircraft.getAircraftCode(), aircraft.getAircraftDescription(), LocalDateTime.now(), LocalDateTime.now(),
                    aircraft.getAircraftUpdateUser()));
            return new ResponseEntity<>(_aircraft, HttpStatus.CREATED);
        } catch (Exception hata) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (path = "aircrafts/{id}")
    public ResponseEntity<?> updateAicraft(@PathVariable("id")Long ID, @RequestBody AircraftEntity aircraft){
        Optional <AircraftEntity> aircraftVeri = aircraftRepository.findById(ID);
        if (aircraftVeri.isPresent()){
            AircraftEntity _aircraft = aircraftVeri.get();
            _aircraft.setAircraftCode(aircraft.getAircraftCode());
            _aircraft.setAircraftDescription(aircraft.getAircraftDescription());
            _aircraft.setAircraftUpdateTime(LocalDateTime.now());
            _aircraft.setAircraftUpdateUser(aircraft.getAircraftUpdateUser());
            return new ResponseEntity<>(aircraftRepository.save(_aircraft),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (path = "aircrafts/{id}")
    public ResponseEntity<HttpStatus> deleteAircraft(@PathVariable("id")Long ID){
        try {
            aircraftRepository.deleteById(ID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception hata){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "aircrafts")
    public ResponseEntity<HttpStatus> deleteAllAircrafts() {
        try {
            aircraftRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception hata) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    @GetMapping(path = "aircrafts/published")   //publish dışarıya bir şey göndermek
    public ResponseEntity<List<AircraftEntity>> findByPublished() {
        try {
            List<AircraftEntity> aircraftEntityList = aircraftRepository.findByPublished(true);
            if (aircraftEntityList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(aircraftEntityList, HttpStatus.OK);
        } catch (Exception hata) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

}



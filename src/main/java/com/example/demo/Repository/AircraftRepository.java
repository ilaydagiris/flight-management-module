package com.example.demo.Repository;

import com.example.demo.Entity.AircraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AircraftRepository extends JpaRepository<AircraftEntity,Long> {

    List<AircraftEntity> findByAircraftCode(String aircraftCode);


}




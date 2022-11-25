package com.example.demo.Repository;

import com.example.demo.Entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity,Long> {

    List<FlightEntity> findByFlightNo(String flightNo);


}




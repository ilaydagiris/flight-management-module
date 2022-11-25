package com.example.demo.Repository;

import com.example.demo.Entity.AirlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AirlineRepository extends JpaRepository<AirlineEntity,Long> {

    List<AirlineEntity> findByAirlineCode(String airlineCode);
    //List<AirlineEntity> findByAirlineCodeAndDescription(String airlineCode, String airlineDescription);
}

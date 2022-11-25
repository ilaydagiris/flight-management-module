package com.example.demo.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="Flight_Entity")
//@Data

public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long flightID;
    //@Column (name = "airline") yazmaya gerek yok manytoone yazınca
    @ManyToOne // bir sürü airline ım var ama ben bir tanesini kullanacağım.
    private AirlineEntity airline;
    @Column(name = "flightNo")
    private String flightNo;
    @ManyToOne
    private AircraftEntity aircraft;
    @Enumerated(EnumType.STRING)
    @Column(name = "flightLeg")
    private ArrDepEnum flightLeg;
    @Column(name = "flightDate")
    private LocalDate flightDate;
    @ManyToOne
    private StationEntity systemAirport;
    @ManyToOne
    private StationEntity originStation;
    @Column(name = "flightCreTime")
    private LocalDateTime flightCreTime;
    @Column(name = "flightUpdateTime")
    private LocalDateTime flightUpdateTime;
    @Column(name = "flightUpdateUser")
    private String flightUpdateUser;

    public FlightEntity() {
    }

    public FlightEntity(long flightID, AirlineEntity airline, String flightNo, AircraftEntity aircraft,
                        ArrDepEnum flightLeg, LocalDate flightDate, StationEntity systemAirport,
                        StationEntity originStation, LocalDateTime flightCreTime, LocalDateTime flightUpdateTime,
                        String flightUpdateUser){
        this.flightID = flightID;
        this.airline = airline;
        this.flightNo = flightNo;
        this.aircraft = aircraft;
        this.flightLeg = flightLeg;
        this.flightDate = flightDate;
        this.systemAirport = systemAirport;
        this.originStation = originStation;
        this.flightCreTime = flightCreTime;
        this.flightUpdateTime = flightUpdateTime;
        this.flightUpdateUser = flightUpdateUser;
    }

    public long getFlightID(){ //getter olduğu için parametreye ihtiyaç yok, sadece var olanı getirecek
        return this.flightID;
    }
    public void setFlightID(long flightID){ this.flightID = flightID; }
    public AirlineEntity getAirline(){ return this.airline; }
    public void setAirline(AirlineEntity airline){ this.airline = airline; }
    public String getFlightNo() { return flightNo; }
    public void setFlightNo(String flightNo) { this.flightNo = flightNo; }
    public AircraftEntity getAircraft() { return aircraft; }
    public void setAircraft(AircraftEntity aircraft) { this.aircraft = aircraft; }
    public ArrDepEnum getFlightLeg(){ return flightLeg; }
    public void setFlightLeg(ArrDepEnum flightLeg) { this.flightLeg = flightLeg; }
    public LocalDate getFlightDate() { return flightDate; }
    public void setFlightDate(LocalDate flightDate) { this.flightDate = flightDate; }
    public StationEntity getSystemAirport() { return systemAirport; }
    public void setSystemAirport(StationEntity systemAirport) { this.systemAirport = systemAirport; }
    public StationEntity getOriginStation() { return originStation; }
    public void setOriginStation(StationEntity originStation) { this.originStation = originStation; }
    public LocalDateTime getFlightCreTime() { return flightCreTime; }
    public void setFlightCreTime(LocalDateTime flightCreTime) { this.flightCreTime = flightCreTime; }
    public LocalDateTime getFlightUpdateTime() { return flightUpdateTime; }
    public void setFlightUpdateTime(LocalDateTime flightUpdateTime) { this.flightUpdateTime = flightUpdateTime; }
    public String getFlightUpdateUser() { return flightUpdateUser; }
    public void setFlightUpdateUser(String flightUpdateUser) { this.flightUpdateUser = flightUpdateUser; }

}
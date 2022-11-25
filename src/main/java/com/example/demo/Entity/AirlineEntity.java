package com.example.demo.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Airline_Entity")
public class AirlineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long airlineID;
    @Column(name = "airlineCode")
    private String airlineCode;
    @Column(name = "airlineDescription")
    private String airlineDescription;
    @Column(name = "airlineCreTime")
    private LocalDateTime airlineCreTime;
    @Column(name = "airlineUpdateTime")
    private LocalDateTime airlineUpdateTime;
    @Column(name = "airlineUpdateUser")
    private String airlineUpdateUser;

    public AirlineEntity() {
    }

    public AirlineEntity(String airlineCode, String airlineDescription, LocalDateTime airlineCreTime,
                         long airlineID, LocalDateTime airlineUpdateTime, String airlineUpdateUser) {
        this.airlineID = airlineID;
        this.airlineCode = airlineCode;
        this.airlineDescription = airlineDescription;
        this.airlineCreTime = airlineCreTime;
        this.airlineUpdateTime = airlineUpdateTime;
        this.airlineUpdateUser = airlineUpdateUser;
    }
    public long getAirlineID() {
        return airlineID;
    }
    public void setAirlineID(long airlineID) {
        this.airlineID = airlineID;
    }
    public String getAirlineCode() {
        return airlineCode;
    }
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }
    public String getAirlineDescription() {
        return airlineDescription;
    }
    public void setAirlineDescription(String airlineDescription) {
        this.airlineDescription = airlineDescription;
    }
    public LocalDateTime getAirlineCreTime() {
        return airlineCreTime;
    }
    public void setAirlineCreTime(LocalDateTime now) {
        this.airlineCreTime = now;
    }
    public LocalDateTime getAirlineUpdateTime() {
        return airlineUpdateTime;
    }
    public void setAirlineUpdateTime(LocalDateTime now) {
        this.airlineUpdateTime = now;
    }
    public String getAirlineUpdateUser() {
        return airlineUpdateUser;
    }
    public void setAirlineUpdateUser(String airlineUpdateUser) {
        this.airlineUpdateUser = airlineUpdateUser;
    }
    @Override
    public String toString() {
        return "Airline [airlineID=" + airlineID + ", airlineCode=" + airlineCode + ", airlineDescription=" +
                airlineDescription + ", airlineCreTime=" + airlineCreTime + ", airlineUpdateTime=" + airlineUpdateTime +
                ", airlineUpdateUser" + airlineUpdateUser + "]";
    }
}

package com.example.demo.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Aircraft_Entity")
public class AircraftEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long aircraftID;
    @Column(name = "aircraftCode")
    private String aircraftCode;
    @Column(name = "aircraftDescription")
    private String aircraftDescription;
    @Column(name = "aircraftCreTime")
    private LocalDateTime aircraftCreTime;
    @Column(name = "aircraftUpdateTime")
    private LocalDateTime aircraftUpdateTime;
    @Column(name = "aircraftUpdateUser")
    private String aircraftUpdateUser;

    public AircraftEntity() {
    }

    public AircraftEntity(long aircraftID, String aircraftCode, String aircraftDescription,
                          LocalDateTime aircraftCreTime, LocalDateTime aircraftUpdateTime,
                          String aircraftUpdateUser) {
        this.aircraftID = aircraftID;
        this.aircraftCode = aircraftCode;
        this.aircraftDescription = aircraftDescription;
        this.aircraftCreTime = aircraftCreTime;
        this.aircraftUpdateTime = aircraftUpdateTime;
        this.aircraftUpdateUser = aircraftUpdateUser;
    }
    public long getAircraftID() {
        return aircraftID;
    }
    public void setAircraftID(long aircraftID) { this.aircraftID = aircraftID; }
    public String getAircraftCode() {
        return aircraftCode;
    }
    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode= aircraftCode;
    }
    public String getAircraftDescription() {
        return aircraftDescription;
    }
    public void setAircraftDescription(String aircraftDescription) { this.aircraftDescription = aircraftDescription; }
    public LocalDateTime getAircraftCreTime() {
        return aircraftCreTime;
    }
    public void setAircraftCreTime(LocalDateTime aircraftCreTime) {
        this.aircraftCreTime = aircraftCreTime;
    }
    public LocalDateTime getAircraftUpdateTime() {
        return aircraftUpdateTime;
    }
    public void setAircraftUpdateTime(LocalDateTime aircraftUpdateTime) { this.aircraftUpdateTime = aircraftUpdateTime; }
    public String getAircraftUpdateUser() {
        return aircraftUpdateUser;
    }
    public void setAircraftUpdateUser(String aircraftUpdateUser) {
        this.aircraftUpdateUser = aircraftUpdateUser;
    }

    @Override
    public String toString() {
        return "Aircraft [aircraftID=" + aircraftID + ", aircraftCode=" + aircraftCode + ", aircraftDescription=" +
                aircraftDescription + ", aircraftCreTime=" + aircraftCreTime + ", aircraftUpdateTime=" + aircraftUpdateTime +
                ", aircraftUpdateUser" + aircraftUpdateUser + "]";
    }
}

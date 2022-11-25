package com.example.demo.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Station_Entity")
public class StationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stationID;
    @Column(name = "stationCode")
    private String stationCode;
    @Column(name = "stationDescription")
    private String stationDescription;
    @Column(name = "stationCreTime")
    private LocalDateTime stationCreTime;
    @Column(name = "stationUpdateTime")
    private LocalDateTime stationUpdateTime;
    @Column(name = "stationUpdateUser")
    private String stationUpdateUser;

    public StationEntity() {
    }

    public StationEntity(long stationID, String stationCode, String stationDescription,
                         LocalDateTime stationCreTime, LocalDateTime stationUpdateTime,
                         String stationUpdateUser) {
        this.stationID = stationID;
        this.stationCode = stationCode;
        this.stationDescription = stationDescription;
        this.stationCreTime = stationCreTime;
        this.stationUpdateTime = stationUpdateTime;
        this.stationUpdateUser = stationUpdateUser;
    }
    public long getStationID() {
        return stationID;
    }
    public void setStationID(long stationID) { this.stationID = stationID; }
    public String getStationCode() {
        return stationCode;
    }
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }
    public String getStationDescription() {
        return stationDescription;
    }
    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }
    public LocalDateTime getStationCreTime() {
        return stationCreTime;
    }
    public void setStationCreTime(LocalDateTime now) {
        this.stationCreTime = now;
    }
    public LocalDateTime getStationUpdateTime() {
        return stationUpdateTime;
    }
    public void setStationUpdateTime(LocalDateTime now) {
        this.stationUpdateTime = now;
    }
    public String getStationUpdateUser() {
        return stationUpdateUser;
    }
    public void setStationUpdateUser(String stationUpdateUser) {
        this.stationUpdateUser = stationUpdateUser;
    }
    @Override
    public String toString() {
        return "Station [stationID=" + stationID + ", stationCode=" + stationCode + ", stationDescription=" +
                stationDescription + ", stationCreTime=" + stationCreTime + ", stationUpdateTime=" +
                stationUpdateTime + ", stationUpdateUser" + stationUpdateUser + "]";
    }
}



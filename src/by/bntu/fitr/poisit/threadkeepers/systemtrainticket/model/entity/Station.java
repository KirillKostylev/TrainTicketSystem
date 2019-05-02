package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Station implements Serializable {
    private String nameStation;
    private String arriveTime;
    private String departTime;

    public Station(String nameStation, String arriveDate, String departDate) {
        this.nameStation = nameStation;
        this.arriveTime = arriveDate;
        this.departTime = departDate;
    }
//сделать рейсы(поезд, станции)
    public String getStationName() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    @Override
    public String toString() {
        return "Station{" +
                "nameStation='" + nameStation + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", departTime='" + departTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(nameStation, station.nameStation) &&
                Objects.equals(arriveTime, station.arriveTime) &&
                Objects.equals(departTime, station.departTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameStation, arriveTime, departTime);
    }
}
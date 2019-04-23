package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.WrongTitleException;

import java.io.Serializable;

public class Station implements Serializable {
    private String nameStation;
    private String arriveTime;
    private String departTime;

    public Station(String nameStation, String arriveDate, String departDate) {
        this.nameStation = nameStation;
        this.arriveTime = arriveDate;
        this.departTime = departDate;
    }

    public String getNameStation() {
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
}
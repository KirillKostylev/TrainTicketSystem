package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

public class Station {
    private String nameStation;
    private String arriveTime;
    private String departTime;

    public Station(String nameStation, String arriveTime, String departTime) {
        this.nameStation = nameStation;
        this.arriveTime = arriveTime;
        this.departTime = departTime;
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

}
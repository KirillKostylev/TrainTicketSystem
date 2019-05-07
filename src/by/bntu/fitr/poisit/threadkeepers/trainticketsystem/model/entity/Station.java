package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Station implements Serializable {
    public static final String TIME_FORMAT = "dd.MM.yyyy HH:mm";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private String nameStation;
    private String arriveTime;
    private Calendar departTime;

    public Station(String nameStation, String arriveDate, String departDate) throws ParseException {
        this.nameStation = nameStation;
        this.arriveTime = arriveDate;
        Calendar calendarDepartTime = Calendar.getInstance();
        calendarDepartTime.setTime(new SimpleDateFormat(TIME_FORMAT).parse(departDate));
        this.departTime = calendarDepartTime;
    }

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

    public Calendar getDepartTime() {
        return departTime;
    }

//    public void setDepartTime(String departTime) {
//        try {
//            this.departTime = new SimpleDateFormat(TIME_FORMAT).parse(departTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

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
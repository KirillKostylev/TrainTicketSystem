package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.LogicCashier;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Station implements Serializable {
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String TIME_FORMAT = "dd.MM.yyyy HH:mm";
    private StringProperty stationName;
    private StringProperty arriveTime;
    private StringProperty departureTime;

    public Station(String stationName, String departureTime, String arriveTime) {
        this.stationName = new SimpleStringProperty(stationName);
        this.arriveTime = new SimpleStringProperty(arriveTime);
        this.departureTime = new SimpleStringProperty(departureTime);
    }

    public String getStationName() {
        return stationName.get();
    }

    public StringProperty getStationNameProperty() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName.set(stationName);
    }

    public String getArriveTime() {
        return arriveTime.get();
    }

    public StringProperty getArriveTimeProperty() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime.set(arriveTime);
    }

    public String getDepartureTime() {
        return departureTime.get();
    }

    public StringProperty getDepartureTimeProperty() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime.set(departureTime);
    }

//    public Calendar getCalendarDepartureTime() throws ParseException {
//        Calendar calendarDepartTime = Calendar.getInstance();
//        calendarDepartTime.setTime(LogicCashier.convertStringToDate(departureTime.get(), TIME_FORMAT));
//        return calendarDepartTime;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(stationName, station.stationName) &&
                Objects.equals(arriveTime, station.arriveTime) &&
                Objects.equals(departureTime, station.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, arriveTime, departureTime);
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationName='" + stationName + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", departTime='" + departureTime + '\'' +
                '}';
    }

}
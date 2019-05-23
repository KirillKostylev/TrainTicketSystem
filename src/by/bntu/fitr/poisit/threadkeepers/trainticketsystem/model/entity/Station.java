package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.LogicCashier;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Objects;

public class Station implements Serializable{
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String TIME_FORMAT = "dd.MM.yyyy HH:mm";

    private static final Logger LOG = Logger.getLogger(Station.class);

    private StringProperty stationName;
    private StringProperty arriveTime;
    private StringProperty departureTime;

    public Station(String stationName, String arriveTime, String departureTime) {
        this.stationName = new SimpleStringProperty(stationName);
        this.arriveTime = new SimpleStringProperty(arriveTime);
        this.departureTime = new SimpleStringProperty(departureTime);
        LOG.trace("Station has been created");
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(stationName.get(), station.stationName.get()) &&
                Objects.equals(arriveTime.get(), station.arriveTime.get()) &&
                Objects.equals(departureTime.get(), station.departureTime.get());
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
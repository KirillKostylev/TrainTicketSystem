package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Route implements Serializable, Comparable<Route>{
    public static final String EMPTY_FIELD_EXCEPTION = "One of the fields is empty";
    public static final String NULL_INPUT_FIELD_EXCEPTION = "Value can't be null";
    private List<Station> stations;
    private Train train;

    public Route(List<Station> stations, Train train) {
        this.stations = stations;
        this.train = train;
    }

    public List<String> getStationsName() {
        List<String> stationsName = new ArrayList<>();
        for (Station station : stations) {
            stationsName.add(station.getStationName());
        }
        return stationsName;
    }

    public List<Station> getStations() {
        return stations;
    }

    public Station getStation(String field) {
        Station suitableStation = null;
        for (Station station : stations) {
            if (station.getStationName().equals(field)){
                suitableStation = station;
                break;
            }
        }
        return suitableStation;
    }

    public Train getTrain() {
        return train;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(stations, route.stations) &&
                Objects.equals(train, route.train);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stations, train);
    }

    @Override
    public int compareTo(Route o) {
        return this.getStations().get(0).getDepartTime().compareTo(o.stations.get(0).getDepartTime());
    }
}

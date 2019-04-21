package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Seat implements Serializable {
    private int carriageNumber;
    private int seatNumber;
    private List<Station> busyStations;

    public Seat(int carriageNumber, int seatNumber, List<Station> busyStations) {
        this.carriageNumber = carriageNumber;
        this.seatNumber = seatNumber;
        this.busyStations = busyStations;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public List<Station> getBusyStations() {
        return busyStations;
    }

    public void setBusyStations(List<Station> busyStations) {
        this.busyStations = busyStations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return carriageNumber == seat.carriageNumber &&
                seatNumber == seat.seatNumber &&
                Objects.equals(busyStations, seat.busyStations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carriageNumber, seatNumber, busyStations);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "carriageNumber=" + carriageNumber +
                ", seatNumber=" + seatNumber +
                ", busyStations=" + busyStations +
                '}';
    }
}

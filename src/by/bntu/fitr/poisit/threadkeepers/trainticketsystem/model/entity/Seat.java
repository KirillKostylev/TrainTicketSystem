package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.Checker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Seat implements Serializable {
    public static final String WRONG_CARRIAGE_NUMBER = "Wrong Carriage number!";
    public static final String WRONG_SEAT_NUMBER = "Wrong Seat Number";

    private static int carriageCounter = 1;
    private static int seatCounter = 1;

    private int carriageNumber;
    private int seatNumber;
    private List<Station> busyStations;

    public Seat(int seatNumberInCarriage) {
        if (seatCounter > seatNumberInCarriage) {
            carriageCounter++;
            seatCounter = 1;
        }
        carriageNumber = carriageCounter;
        seatNumber = seatCounter;
        this.busyStations = new ArrayList<>();
        seatCounter++;
    }

    public Seat() {
        this(Train.DEFAULT_SEATS_NUMBER_IN_CARRIAGE);
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) throws NonPositiveException {
        if (carriageNumber <= 0) {
            throw new NonPositiveException(WRONG_CARRIAGE_NUMBER);
        }
        this.carriageNumber = carriageNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) throws NonPositiveException {
        Checker.checkForPositiveWithException(SeatContainer.INVALID_VALUE_EXCEPTION,seatNumber);
        this.seatNumber = seatNumber;
    }

    public List<Station> getBusyStations() {
        return busyStations;
    }

    public void addBusyStations(List<Station> busyStations) {
        busyStations.remove(busyStations.size()-1);
        this.busyStations.addAll(busyStations);

    }

    public static void clearCounters() {
        seatCounter = 1;
        carriageCounter = 1;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "carriageNumber=" + carriageNumber +
                ", seatNumber=" + seatNumber +
                ", busyStations=" + busyStations +
                '}';
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
}

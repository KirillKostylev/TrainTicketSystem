package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeatContainer implements Serializable{
    public static final String NULL_SEAT_CONTAINER = "SeatContainer can't be null value";
    public static final String INVALID_VALUE_EXCEPTION = "Value must be positive";
    public static final String WRONG_CARRIAGE_NUMBER = "Such carriage number is not in the train";
    public static final String WRONG_SEAT_NUMBER = "Such seat number is not in the carriage";


    private List<List<Seat>> seatList;

    public SeatContainer() {
        seatList = new ArrayList<>();
    }

    public SeatContainer(List<List<Seat>> seatList) {
        if (seatList == null) {
            seatList = new ArrayList<>();
        }
        this.seatList = seatList;
    }

    public void addCarriageInContainer(int carriageIndex, int seatsNumberInCarriage) {
        seatList.add(new ArrayList<>());
        for (int i = 0; i < seatsNumberInCarriage; i++) {
            addSeatInCarriage(carriageIndex, new Seat(seatsNumberInCarriage));
        }
    }

    private void addSeatInCarriage(int carriageIndex, Seat seat) {
        seatList.get(carriageIndex).add(seat);
    }

    public int getCarriageCount() {
        return seatList.size();
    }

    public List<List<Seat>> getSeatList() {
        return seatList;
    }

    public int getSeatCount() {
        return seatList.get(0).size();
    }

    public Seat getSeat(int carriageNumber, int seatNumber) {
        return seatList.get(carriageNumber).get(seatNumber);
    }


    @Override
    public String toString() {
        return "SeatContainer{" +
                "seatList=" + seatList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatContainer that = (SeatContainer) o;
        return Objects.equals(seatList, that.seatList);
    }


    @Override
    public int hashCode() {
        return Objects.hash(seatList);
    }
}

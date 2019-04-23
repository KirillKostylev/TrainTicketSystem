package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.NonPositiveException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeatContainer implements Serializable {

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
        seatList.add(carriageIndex, new ArrayList<>());
        for (int i = 0; i < seatsNumberInCarriage; i++) {
            addSeatInCarriage(carriageIndex, i, new Seat(carriageIndex, i, null));
        }
    }

    private void addSeatInCarriage(int carriageIndex, int seatIndex, Seat seat) {
        seatList.get(carriageIndex).add(seatIndex, seat);
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

    @Override
    public String toString() {
        return "SeatContainer{" +
                "seatList=" + seatList +
                '}';
    }
}

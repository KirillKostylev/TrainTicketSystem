package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeatContainer {

    public static final int DEFAULT_SIZE = 5;

    private List<ArrayList<Seat>> seatList;

    public SeatContainer() {
        seatList = new ArrayList<>(DEFAULT_SIZE);
    }

    public SeatContainer(int carriageNumber) {
        if (carriageNumber < 0) {
            seatList = new ArrayList<>(DEFAULT_SIZE);
        } else {
            seatList = new ArrayList<>(carriageNumber);
        }
    }

    public SeatContainer(List<ArrayList<Seat>> seatList) {
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

    public List<ArrayList<Seat>> getSeatList() {
        return seatList;
    }

    public int getSeatCount() {
        return seatList.get(0).size();
    }

    public Seat getElement(int i, int j) {
        return seatList.get(i).get(j);
    }

    public void setElement(int i, int j, Seat seat) {
        seatList.get(i).set(j, seat);
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

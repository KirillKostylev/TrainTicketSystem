package model.entity;

import java.util.ArrayList;
import java.util.List;

public class SeatContainer {

    public static final int DEFAULT_SIZE = 5;

    private List<ArrayList<Seat>> seatList;

    public SeatContainer() {
        seatList = new ArrayList<ArrayList<Seat>>();
    }

    public SeatContainer(List<ArrayList<Seat>> seatList) {
        if (seatList == null) {
            seatList = new ArrayList<ArrayList<Seat>>();
        }
        this.seatList = seatList;
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
        seatList.get(i).set(j,seat);
    }

    @Override
    public String toString() {
        return "SeatContainer{" +
                "seatList=" + seatList +
                '}';
    }
}

package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import java.io.Serializable;
import java.util.List;

public class Train implements Serializable {
    public final static int DEFAULT_SEAT_NUMBER_IN_CARRIAGE = 2;
    public final static int DEFAULT_CARRIAGES_NUMBER = 5;


    private int trainNumber;
    private int carriagesNumber;
    private List<Station> stationsInTransit;
    private SeatContainer seatContainer;

    public Train(int trainNumber, int carriagesNumber, List<Station> stationsInTransit) {
        this.trainNumber = trainNumber;
        this.stationsInTransit = stationsInTransit;
        if (carriagesNumber < 0) {
            this.carriagesNumber = DEFAULT_CARRIAGES_NUMBER;
        } else {
            this.carriagesNumber = carriagesNumber;
        }
        seatContainer = new SeatContainer(carriagesNumber);
        fillSeatContainer();
    }

//    public void fillSeatContainer(int carriageCount) {
//        for (int i = 0; i < carriageCount; i++) {
//            seatContainer.getSeatList().add(i, new ArrayList<>());
//            for (int j = 0; j < DEFAULT_SEAT_NUMBER_IN_CARRIAGE; j++) {
//                seatContainer.getSeatList().get(i).add(j, new Seat(i, j, null));
//            }
//        }
//    }

    private void fillSeatContainer() {
        for (int i = 0; i < carriagesNumber; i++) {
            seatContainer.addCarriageInContainer(i, DEFAULT_SEAT_NUMBER_IN_CARRIAGE);
        }
    }

    public List<Station> getStationsInTransit() {
        return stationsInTransit;
    }

    public SeatContainer getSeatContainer() {
        return seatContainer;
    }

    public void setSeatContainer(SeatContainer seatContainer) {
        this.seatContainer = seatContainer;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setStationsInTransit(List<Station> stationsInTransit) {
        this.stationsInTransit = stationsInTransit;
    }

    public int getCarriagesNumber() {
        return carriagesNumber;
    }

    public void setCarriagesNumber(int carriagesNumber) {
        this.carriagesNumber = carriagesNumber;
    }

    @Override
    public String toString() {
        return "\nTrain{" +
                "trainNumber=" + trainNumber +
                ", carriagesNumber=" + carriagesNumber +
                ", stationsInTransit=" + stationsInTransit +
                ", seatContainer=" + seatContainer +
                '}';
    }
}
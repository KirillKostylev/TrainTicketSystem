package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic.Checker;

import java.io.Serializable;
import java.util.List;

public class Train implements Serializable {
    public final static int DEFAULT_SEAT_NUMBER_IN_CARRIAGE = 2;
    public final static int DEFAULT_CARRIAGES_NUMBER = 5;
    public static final String WRONG_TRAIN_NUMBER= "Wrong train number!";
    public static final String WRONG_CARRIAGES_AMOUNT = "Wrong carriages amount!";

    private int trainNumber;
    private int carriagesNumber;
    private List<Station> stationsInTransit;
    private SeatContainer seatContainer;


    public Train(int trainNumber, int carriagesNumber, List<Station> stationsInTransit)
            throws NonPositiveException {
        this.trainNumber = trainNumber;
        this.stationsInTransit = stationsInTransit;
        if (carriagesNumber < 0) {
            this.carriagesNumber = DEFAULT_CARRIAGES_NUMBER;
        } else {
            this.carriagesNumber = carriagesNumber;
        }
        seatContainer = new SeatContainer();
        fillSeatContainer();
    }

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

    public void setSeatContainer(SeatContainer seatContainer) throws NullException {
        Checker.checkForNullWithException(seatContainer);
        this.seatContainer = seatContainer;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) throws NonPositiveException {
        Checker.checkForPositiveWithException(trainNumber);
        this.trainNumber = trainNumber;
    }

    public void setStationsInTransit(List<Station> stationsInTransit) throws NullException {
        Checker.checkForNullWithException(stationsInTransit);
        this.stationsInTransit = stationsInTransit;
    }

    public int getCarriagesNumber() {
        return carriagesNumber;
    }

    public void setCarriagesNumber(int carriagesNumber) throws NonPositiveException {
        Checker.checkForPositiveWithException(carriagesNumber);
        this.carriagesNumber = carriagesNumber;
    }

    @Override
    public String toString() {
        return "\nTrain{" +
                "trainNumber=" + trainNumber +
                ", carriagesNumber=" + carriagesNumber +
                ", stationsInTransit=" + stationsInTransit +
                ", seatContainer=" + seatContainer +
                "}";
    }
}
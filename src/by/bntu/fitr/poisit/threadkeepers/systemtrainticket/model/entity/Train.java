package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic.Checker;

import java.io.Serializable;
import java.util.Objects;

public class Train implements Serializable {
    public final static int DEFAULT_SEAT_NUMBER_IN_CARRIAGE = 20;
    public final static int DEFAULT_CARRIAGES_NUMBER = 5;
    public static final String WRONG_TRAIN_NUMBER = "Wrong train number!";
    public static final String WRONG_CARRIAGES_AMOUNT = "Wrong carriages amount!";

    private int trainNumber;
    private int carriagesNumber;
    private int seatsNumberInCarriage;
    private SeatContainer seatContainer;


    public Train(int trainNumber, int carriagesNumber, int seatsNumberInCarriage) {
        this.trainNumber = trainNumber;
        if (carriagesNumber < 0) {
            this.carriagesNumber = DEFAULT_CARRIAGES_NUMBER;
        } else {
            this.carriagesNumber = carriagesNumber;
        }
        if (seatsNumberInCarriage < 0) {
            this.seatsNumberInCarriage = DEFAULT_SEAT_NUMBER_IN_CARRIAGE;
        } else {
            this.seatsNumberInCarriage = seatsNumberInCarriage;
        }
        seatContainer = new SeatContainer();
        fillSeatContainer();
        Seat.clearCounters();
    }

    private void fillSeatContainer() {
        for (int i = 0; i < carriagesNumber; i++) {
            seatContainer.addCarriageInContainer(i, seatsNumberInCarriage);
        }
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

    public int getCarriagesNumber() {
        return carriagesNumber;
    }

    public void setCarriagesNumber(int carriagesNumber) throws NonPositiveException {
        Checker.checkForPositiveWithException(carriagesNumber);
        this.carriagesNumber = carriagesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return trainNumber == train.trainNumber &&
                carriagesNumber == train.carriagesNumber &&
                seatsNumberInCarriage == train.seatsNumberInCarriage &&
                Objects.equals(seatContainer, train.seatContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber, carriagesNumber, seatsNumberInCarriage, seatContainer);
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainNumber=" + trainNumber +
                ", carriagesNumber=" + carriagesNumber +
                ", seatsNumberInCarriage=" + seatsNumberInCarriage +
                ", seatContainer=" + seatContainer +
                '}';
    }
}
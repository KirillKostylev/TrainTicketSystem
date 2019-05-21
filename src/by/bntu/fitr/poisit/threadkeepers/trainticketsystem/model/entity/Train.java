package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.Checker;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.util.Objects;

public class Train implements Serializable{
    public final static int DEFAULT_SEATS_NUMBER_IN_CARRIAGE = 20;
    public final static int DEFAULT_CARRIAGES_NUMBER = 5;
    public static final String WRONG_TRAIN_NUMBER = "Wrong train number!";
    public static final String WRONG_CARRIAGES_AMOUNT = "Wrong carriages amount!";

    private IntegerProperty trainNumber;
    private IntegerProperty carriagesNumber;
    private IntegerProperty seatsNumberInCarriage;
    private SeatContainer seatContainer;


    public Train(int trainNumber, int carriagesNumber, int seatsNumberInCarriage) {
        if (trainNumber < 0) {
            this.trainNumber = new SimpleIntegerProperty(-trainNumber);
        } else {
            this.trainNumber = new SimpleIntegerProperty(trainNumber);
        }
        if (carriagesNumber < 0) {
            this.carriagesNumber = new SimpleIntegerProperty(DEFAULT_CARRIAGES_NUMBER);
        } else {
            this.carriagesNumber = new SimpleIntegerProperty(carriagesNumber);
        }
        if (seatsNumberInCarriage < 0) {
            this.seatsNumberInCarriage = new SimpleIntegerProperty(DEFAULT_SEATS_NUMBER_IN_CARRIAGE);
        } else {
            this.seatsNumberInCarriage = new SimpleIntegerProperty(seatsNumberInCarriage);
        }
        seatContainer = new SeatContainer();
        fillSeatContainer();
        Seat.clearCounters();
    }

    private void fillSeatContainer() {
        for (int i = 0; i < getCarriagesNumber(); i++) {
            seatContainer.addCarriageInContainer(i, getSeatsNumberInCarriage());
        }
    }

    public SeatContainer getSeatContainer() {
        return seatContainer;
    }

    public void setSeatContainer(SeatContainer seatContainer) throws NullException {
        Checker.checkForNullWithException(SeatContainer.NULL_SEAT_CONTAINER, seatContainer);
        this.seatContainer = seatContainer;
    }

    public int getTrainNumber() {
        return trainNumber.get();
    }

    public IntegerProperty getTrainNumberProperty() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) throws NonPositiveException {
        Checker.checkForPositiveWithException(SeatContainer.INVALID_VALUE_EXCEPTION,trainNumber);
        this.trainNumber.set(trainNumber);
    }

    public int getCarriagesNumber() {
        return carriagesNumber.get();
    }

    public IntegerProperty getCarriagesNumberProperty() {
        return carriagesNumber;
    }

    public void setCarriagesNumber(int carriagesNumber) throws NonPositiveException {
        Checker.checkForPositiveWithException(SeatContainer.INVALID_VALUE_EXCEPTION,carriagesNumber);
        this.carriagesNumber.set(carriagesNumber);
    }

    public int getSeatsNumberInCarriage() {
        return seatsNumberInCarriage.get();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(trainNumber.get(), train.trainNumber.get()) &&
                Objects.equals(carriagesNumber.get(), train.carriagesNumber.get()) &&
                Objects.equals(seatsNumberInCarriage.get(), train.seatsNumberInCarriage.get()) &&
                Objects.equals(seatContainer, train.seatContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber, carriagesNumber, seatsNumberInCarriage, seatContainer);
    }

}
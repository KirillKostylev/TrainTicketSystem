package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TrainTest {
    @Test
    public void fillSeatContainer() {
        SeatContainer seatList = new SeatContainer();

        for (int i = 0; i < 2; i++) {
            seatList.getSeatList().add(new ArrayList<>());
            for (int j = 0; j < 2; j++) {
                seatList.getSeatList().get(i).add(new Seat(2));
            }
        }
        Seat.clearCounters();

        Train train = new Train(1, 2, 2);
        Assert.assertEquals(seatList, train.getSeatContainer());
    }

    @Test
    public void fillSeatContainerWrongValue() {
        SeatContainer seatList = new SeatContainer();

        for (int i = 0; i < Train.DEFAULT_CARRIAGES_NUMBER; i++) {
            seatList.getSeatList().add(new ArrayList<>());
            for (int j = 0; j < 30; j++) {
                seatList.getSeatList().get(i).add(new Seat(30));
            }
        }
        Seat.clearCounters();


        Train train = new Train(1, -1, 30);
        Assert.assertEquals(seatList, train.getSeatContainer());
    }

    @Test
    public void fillSeatContainerWrongValue2() {
        SeatContainer seatList = new SeatContainer();

        for (int i = 0; i < 5; i++) {
            seatList.getSeatList().add(new ArrayList<>());
            for (int j = 0; j < Train.DEFAULT_SEATS_NUMBER_IN_CARRIAGE; j++) {
                seatList.getSeatList().get(i).add(new Seat());
            }
        }
        Seat.clearCounters();

        Train train = new Train(1, 5, -1);
        Assert.assertEquals(seatList, train.getSeatContainer());
    }
    @Test
    public void fillSeatContainerNullValue() {
        SeatContainer seatList = new SeatContainer();

        for (int i = 0; i < Train.DEFAULT_CARRIAGES_NUMBER; i++) {
            seatList.getSeatList().add(new ArrayList<>());
            for (int j = 0; j < 30; j++) {
                seatList.getSeatList().get(i).add(new Seat(30));
            }
        }
        Seat.clearCounters();


        Train train = new Train(1, -1, 30);
        Assert.assertEquals(seatList, train.getSeatContainer());
    }
}
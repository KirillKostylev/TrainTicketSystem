package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TrainTest {
    @Test
    public void fillSeatContainer() {
        SeatContainer seatList = new SeatContainer();
        seatList.getSeatList().add(new ArrayList<>());
        seatList.getSeatList().add(new ArrayList<>());
        seatList.getSeatList().get(0).add(new Seat(0,0,null));
        seatList.getSeatList().get(0).add(new Seat(0,1,null));
        seatList.getSeatList().get(1).add(new Seat(1,0,null));
        seatList.getSeatList().get(1).add(new Seat(1,1,null));

//        SeatContainer seatList1 = new SeatContainer(2);
        ArrayList<Station> stationList1 = new ArrayList<>();
        Train train = new Train(1, 2,stationList1);
        Assert.assertEquals(seatList, train.getSeatContainer());
    }
}
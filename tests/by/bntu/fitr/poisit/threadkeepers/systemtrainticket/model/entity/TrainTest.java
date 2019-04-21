package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TrainTest {
    @Test
    public void fillSeatContainer() {
        SeatContainer seatList = new SeatContainer(2);
        seatList.getSeatList().add(0,new ArrayList<>(2));
        seatList.getSeatList().add(1,new ArrayList<>(2));
        seatList.getSeatList().get(0).add(0,new Seat(0,0,null));
        seatList.getSeatList().get(0).add(0,new Seat(0,1,null));
        seatList.getSeatList().get(0).add(1,new Seat(1,0,null));
        seatList.getSeatList().get(0).add(1,new Seat(1,1,null));

//        ArrayList<Station> stationList = new ArrayList<>();
//        stationList.add(0, new Station("brest", "12.10.2019 10:15", "12.10.2019 10:30"));
//        stationList.add(1, new Station("ivatsevichi", "12.10.2019 12:30", "12.10.2019 12:40"));
//        stationList.add(1, new Station("minsk", "12.10.2019 14:30", "12.10.2019 14:40"));
//        for (int i = 0; i < 2; i++) {
//            seatList.getSeatList().add(i, new ArrayList<Seat>(5));
//            for (int j = 0; j < 5; j++) {
//                seatList.setElement(i,j,new Seat(i,j,null));
//            }
//        }





//        SeatContainer seatList1 = new SeatContainer(2);
        ArrayList<Station> stationList1 = new ArrayList<>();
        Train train = new Train(1, 2,stationList1);
//        stationList1.add(0, new Station("brest", "12.10.2019 10:15", "12.10.2019 10:30"));
//        stationList1.add(1, new Station("ivatsevichi", "12.10.2019 12:30", "12.10.2019 12:40"));
//        stationList1.add(1, new Station("minsk", "12.10.2019 14:30", "12.10.2019 14:40"));
        Assert.assertEquals(seatList, train.getSeatContainer());
    }

}
package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.*;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.*;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LogicCashierTest {
    private static Schedule schedule;
    private static List<Station> stations;
    private static List<Station> stations2;
    private static List<Station> stations3;
    private static List<Station> stations4;
    private static List<Station> stations5;
    private static List<Station> stations6;
    private static List<Station> stations7;

    @BeforeClass
    public static void createSchedule() throws ParseException {
        schedule = new Schedule();
        stations = Arrays.asList(
                new Station("Brest", "12.10.2019 18:00", "12.10.2019 18:05"),
                new Station("Baranovichi", "12.10.2019 20:00", "12.10.2019 20:05"),
                new Station("Minsk", "12.10.2019 22:00", "12.10.2019 22:05"));
        Train train = new Train(151, 2, 3);
        schedule.addRoute(stations, train);

        stations2 = Arrays.asList(
                new Station("Minsk", "17.08.2019 05:40", "17.08.2019 06:00"),
                new Station("Baranovichi", "17.08.2019 07:40", "17.08.2019 7:50"),
                new Station("Ivatsevichi", "17.08.2019 08:35", "17.08.2019 08:45"),
                new Station("Brest", "17.08.2019 09:00", "17.08.2019 09:20"));
        Train train2 = new Train(345, 10, 30);
        schedule.addRoute(stations2, train2);

        stations3 = Arrays.asList(
                new Station("Brest", "17.08.2019 05:40", "17.08.2019 06:00"),
                new Station("Ivatsevichi", "17.08.2019 07:40", "17.08.2019 7:50"),
                new Station("Baranovichi", "17.08.2019 08:35", "17.08.2019 08:45"),
                new Station("Minsk", "17.08.2019 09:00", "17.08.2019 09:20"));
        Train train3 = new Train(124, 10, 20);
        schedule.addRoute(stations3, train3);

        stations4 = Arrays.asList(
                new Station("Brest", "03.05.2019 18:30", "03.05.2019 18:58"),
                new Station("Zhabinka", "03.05.2019 19:16", "03.05.2019 19:17"),
                new Station("Ivatsevichi", "03.05.2019 20:23", "03.05.2019 20:24"),
                new Station("Baranovichi", "03.05.2019 20:59", "03.05.2019 21:00"),
                new Station("Minsk", "03.05.2019 22:30", "03.05.2019 22:50"));
        Train train4 = new Train(784, 10, 20);
        schedule.addRoute(stations4, train4);

        stations5 = Arrays.asList(
                new Station("Brest", "03.05.2019 17:00", "03.05.2019 17:30"),
                new Station("Baranovichi", "03.05.2019 19:27", "03.05.2019 19:30"),
                new Station("Minsk", "03.05.2019 21:12", "03.05.2019 21:30"));
        Train train5 = new Train(464, 10, 20);
        schedule.addRoute(stations5, train5);

        stations6 = Arrays.asList(
                new Station("Terespol", "03.05.2019 15:00", "03.05.2019 15:30"),
                new Station("Brest", "03.05.2019 18:50", "03.05.2019 18:00"),
                new Station("Ivatsevichi", "03.05.2019 20:00", "03.05.2019 20:05"),
                new Station("Baranovichi", "03.05.2019 21:00", "03.05.2019 21:05"),
                new Station("Minsk", "03.05.2019 22:00", "03.05.2019 22:30"));
        Train train6 = new Train(145, 10, 20);
        schedule.addRoute(stations6, train6);

        stations7 = Arrays.asList(
                new Station("Helsinki", "03.05.2019 18:30", "03.05.2019 18:44"),
                new Station("Saint Petersburg", "04.05.2019 01:18", "04.05.2019 01:53"),
                new Station("Tver", "04.05.2019 06:15", "04.05.2019 06:20"),
                new Station("Moscow", "04.05.2019 09:19", "04.05.2019 10:00"));
        Train train7 = new Train(132, 2, 2);
        schedule.addRoute(stations7, train7);
    }

    @Test
    public void findRoute() throws ParseException {

        List<Route> expectedRoutesList = new LinkedList<>();
        expectedRoutesList.add(new Route(stations3, new Train(124, 10, 20)));

        try {


            Assert.assertEquals(expectedRoutesList,
                    LogicCashier.findRoutes(schedule, "Brest", "Ivatsevichi",
                            "17.08.2019"));
        } catch (EmptyFieldException | NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findRoute2() throws ParseException, NullException, EmptyFieldException {
        List<Route> expectedRoutesList = Arrays.asList(
                new Route(stations5, new Train(464, 10, 20)),
                new Route(stations4, new Train(784, 10, 20)),
                new Route(stations6, new Train(145, 10, 20)));

        Assert.assertEquals(expectedRoutesList,
                LogicCashier.findRoutes(schedule, "Brest", "Minsk",
                        "03.05.2019"));
    }

    @Test
    public void findRoute3() throws ParseException {
        List<Route> expectedRoutesList = Arrays.asList(
                new Route(stations5, new Train(464, 10, 20)),
                new Route(stations4, new Train(784, 10, 20)),
                new Route(stations6, new Train(145, 10, 20)));

        try {
            Assert.assertEquals(expectedRoutesList,
                    LogicCashier.findRoutes(schedule, "Baranovichi", "Minsk",
                            "03.05.2019"));
        } catch (EmptyFieldException | NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findRouteWithNonExistentStation() throws ParseException {
        List<Route> expectedRoutesList = new ArrayList<>();
        try {
            Assert.assertEquals(expectedRoutesList,
                    LogicCashier.findRoutes(schedule, "Mogilev", "Moscow", "03.05.2019"));
        } catch (EmptyFieldException | NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findRouteWithNullSchedule() throws ParseException {
        try {
            LogicCashier.findRoutes(null, "Brest", "Minsk", "03.05.2019");
        } catch (EmptyFieldException | NullException e) {
            e.getMessage();
            Assert.assertEquals(Schedule.NULL_SCHEDULE_EXCEPTION, e.getMessage());
        }
    }

    @Test
    public void findRouteWithNullField() throws ParseException {
        try {
            LogicCashier.findRoutes(schedule, null, "Minsk", "03.05.2019");
        } catch (EmptyFieldException | NullException e) {
            Assert.assertEquals(Route.NULL_INPUT_FIELD_EXCEPTION, e.getMessage());
        }

    }

    @Test
    public void findRouteWithEmptyInputField() throws ParseException {
        try {
            LogicCashier.findRoutes(schedule, "Minsk", "", "03.05.2019");
        } catch (EmptyFieldException | NullException e) {
            Assert.assertEquals(Route.EMPTY_FIELD_EXCEPTION, e.getMessage());
        }
    }



    @Test
    public void buyTicketCheckNullRoute() {
        Station departureStation = schedule.getRoute(0).getStation("Minsk");
        Station arriveStation = schedule.getRoute(0).getStation("Brest");
        try {
            LogicCashier.buyTicket(null, 2, 3, departureStation, arriveStation,
                    0.03);
        } catch (NullException e) {
            Assert.assertEquals(Route.NULL_ROUTE_EXCEPTION, e.getMessage());
        }
    }


    @Test
    public void buyTicketCheckNullValue() {
        Station arriveStation = schedule.getRoute(0).getStation("Brest");
        try {
            LogicCashier.buyTicket(schedule.getRoute(0), 1, 1,
                    null, arriveStation, 0.03);
        } catch (NullException e) {
            Assert.assertEquals(Route.NULL_INPUT_FIELD_EXCEPTION, e.getMessage());
        }
    }

    @Test
    public void tryBuyTicketWithBusySeat() {
        Route route = new Route(stations7, new Train(132, 2, 2));

        Station helsinki = route.getStation("Helsinki");
        Station saintPetersburg = route.getStation("Saint Petersburg");
        Station tver = route.getStation("Tver");
        Station moscow = route.getStation("Moscow");
        try {
            LogicCashier.buyTicket(route, 2, 1,
                    helsinki, saintPetersburg, 0.03);
            LogicCashier.buyTicket(route, 2, 1,
                    saintPetersburg, tver, 0.03);
            Assert.assertNull(LogicCashier.buyTicket(route, 2, 1,
                    saintPetersburg, moscow, 0.03));
//            Assert.assertEquals(train, schedule.getRoute(6).getTrain());
        } catch (NullException e) {
            e.getMessage();
        }
    }

    @Test
    public void buyTicket() {
        Route route = new Route(stations7, new Train(132, 2, 2));
        Station helsinki = route.getStation("Helsinki");
        Station saintPetersburg = route.getStation("Saint Petersburg");

        try {
            Ticket expectedTicket = new Ticket(132, "Helsinki", "Saint Petersburg",
                    "03.05.2019 18:30", "04.05.2019 01:53", 2, 1,
                    Math.round(LogicCashier.COST_PER_KM *
                            DistanceCalculator.distanceCalculate("Helsinki",
                                    "Saint Petersburg") * 100.0) / 100.0);

            Assert.assertEquals(expectedTicket, LogicCashier.buyTicket(route, 2,
                    1, helsinki, saintPetersburg, 0.03));
        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buyTicket2() {
        Route route = new Route(stations7, new Train(132, 2, 2));
        Station helsinki = route.getStation("Helsinki");
        Station tver = route.getStation("Tver");
        Station moscow = route.getStation("Moscow");

        try {
            LogicCashier.buyTicket(route, 1, 1,
                    helsinki, tver, 0.03);

            Ticket expectedTicket = new Ticket(132, "Tver", "Moscow",
                    "04.05.2019 06:15", "04.05.2019 10:00", 1, 1,
                    Math.round(LogicCashier.COST_PER_KM *
                            DistanceCalculator.distanceCalculate("Tver",
                                    "Moscow") * 100.0) / 100.0);

            Assert.assertEquals(expectedTicket, LogicCashier.buyTicket(route, 1,
                    1, tver, moscow, 0.03));
        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tryBuyTicketWithBusySeat2() {
        Route route = new Route(stations7, new Train(132, 2, 2));
        Station helsinki = route.getStation("Helsinki");
        Station tver = route.getStation("Tver");

        Station saintPetersburg = route.getStation("Saint Petersburg");
        Station moscow = route.getStation("Moscow");

        try {
            LogicCashier.buyTicket(route, 1, 1,
                    helsinki, tver, 0.03);

            Assert.assertNull(LogicCashier.buyTicket(route, 1,
                    1, saintPetersburg, moscow, 0.03));
        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findCarriagesNumberWithFreeSeats() {
        List<String> carriagesNumbers = Arrays.asList("1", "2");

        Route route = new Route(stations7, new Train(132, 2, 2));
        Station helsinki = route.getStation("Helsinki");
        Station moscow = route.getStation("Moscow");
        Station saintPetersburg = route.getStation("Saint Petersburg");
        try {
            LogicCashier.buyTicket(route, 2, 2,
                    helsinki, moscow, 0.03);

            Assert.assertEquals(carriagesNumbers, LogicCashier.findCarriagesNumberWithFreeSeats(
                    route, helsinki, saintPetersburg));

        } catch (NullException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findCarriagesNumberWithFreeSeats2() {
        List<String> carriagesNumbers = Arrays.asList("2");
        Route route = new Route(stations7, new Train(132, 2, 2));
        Station helsinki = route.getStation("Helsinki");
        Station tver = route.getStation("Tver");
        Station moscow = route.getStation("Moscow");
        try {
//            LogicCashier.buyTicket(schedule.getRoute(6), 2, 2,
//                    "Tver", "Moscow");
            LogicCashier.buyTicket(route, 1, 2,
                    helsinki, tver, 0.03);
            LogicCashier.buyTicket(route, 1, 1,
                    helsinki, tver, 0.03);
            LogicCashier.buyTicket(route, 2, 1,
                    helsinki, tver, 0.03);
            Assert.assertEquals(carriagesNumbers, LogicCashier.findCarriagesNumberWithFreeSeats(
                    route, helsinki, moscow));

        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findCarriagesNumberWithFreeSeatsExpectEmptyList() {
        List<Integer> carriagesNumbers = new ArrayList<>();
        Route route = new Route(stations7, new Train(132, 2, 2));

        Station tver = route.getStation("Tver");
        Station moscow = route.getStation("Moscow");
        Station helsinki = route.getStation("Helsinki");
        Station saintPetersburg = route.getStation("Saint Petersburg");
        try {
            LogicCashier.buyTicket(route, 2, 1,
                    tver, moscow, 0.03);
            LogicCashier.buyTicket(route, 2, 2,
                    tver, moscow, 0.03);
            LogicCashier.buyTicket(route, 1, 2,
                    helsinki, moscow, 0.03);
            LogicCashier.buyTicket(route, 1, 1,
                    helsinki, moscow, 0.03);
            Assert.assertEquals(carriagesNumbers, LogicCashier.findCarriagesNumberWithFreeSeats(
                    route, saintPetersburg, moscow));


        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findFreeSeatsInCarriageTest_FreeSeats() {
        List<String> freeSeats = Arrays.asList("1", "2");
        Route route = new Route(stations7, new Train(132, 2, 2));

        Station tver = route.getStation("Tver");
        Station moscow = route.getStation("Moscow");
        Station helsinki = route.getStation("Helsinki");
        try {
            LogicCashier.buyTicket(route, 2, 2,
                    tver, moscow, 0.03);
            Assert.assertEquals(freeSeats,
                    LogicCashier.findFreeSeatsInCarriage(route, helsinki,
                            moscow, 1));
        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findFreeSeatsInCarriageTest_NoFreeSeats() {
        List<String> freeSeats = Arrays.asList();
        Train train = new Train(132, 2, 2);
        Route route = new Route(stations7, train);

        Station tver = route.getStation("Tver");
        Station moscow = route.getStation("Moscow");
        Station helsinki = route.getStation("Helsinki");
        List<List<Seat>> seats = train.getSeatContainer().getSeatList();
        for (List<Seat> seatList: seats) {
            for (Seat seat: seatList) {
                seat.addBusyStations(new ArrayList<>(Arrays.asList(helsinki, moscow)));
            }
        }
        try {
            LogicCashier.buyTicket(route, 2, 2,
                    tver, moscow, 0.03);
            Assert.assertEquals(freeSeats,
                    LogicCashier.findFreeSeatsInCarriage(route, helsinki,
                            moscow, 1));
        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findFreeSeatsInCarriageTest_EmptyList() {
        List<String> freeSeats = Arrays.asList("2");
        Route route = new Route(stations7, new Train(132, 2, 2));

        Station tver = route.getStation("Tver");
        Station moscow = route.getStation("Moscow");
        Station saintPetersburg = route.getStation("Saint Petersburg");
        Station helsinki = route.getStation("Helsinki");

        try {
            LogicCashier.buyTicket(route, 1, 1,
                    saintPetersburg, moscow, 0.03);
            LogicCashier.buyTicket(route, 1, 2,
                    tver, moscow, 0.03);
            Assert.assertEquals(freeSeats,
                    LogicCashier.findFreeSeatsInCarriage(route, helsinki,
                            saintPetersburg, 1));
        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkSeatForFreeTest_Busy() {
        List<Station> stations = new ArrayList<>(Arrays.asList(
                new Station("Brest", "03.05.2019 18:30", "03.05.2019 18:58"),
                new Station("Zhabinka", "03.05.2019 19:16", "03.05.2019 19:17"),
                new Station("Ivatsevichi", "03.05.2019 20:23", "03.05.2019 20:24")));
        Seat seat = new Seat(4);
        seat.addBusyStations(stations);
        Assert.assertEquals(false, LogicCashier.checkSeatForFree(seat, stations));
    }

    @Test
    public void checkSeatForFreeTest_Free() {
        List<Station> stations = new ArrayList<>(Arrays.asList(
                new Station("Brest", "03.05.2019 18:30", "03.05.2019 18:58"),
                new Station("Zhabinka", "03.05.2019 19:16", "03.05.2019 19:17"),
                new Station("Ivatsevichi", "03.05.2019 20:23", "03.05.2019 20:24")));
        Seat seat = new Seat(4);
        Assert.assertEquals(true, LogicCashier.checkSeatForFree(seat, stations));
    }

    @Test
    public void checkSeatForFreeTest_EmptyStationsArray() {
        List<Station> stations = new ArrayList<>();
        Seat seat = new Seat(4);
        Assert.assertEquals(false, LogicCashier.checkSeatForFree(seat, stations));
    }
}
package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Schedule;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Train;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.LogicCashier;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogicCashierTest {
    private static Schedule schedule;
    private static List<Station> stations;
    private static List<Station> stations2;
    private static List<Station> stations3;
    private static List<Station> stations4;
    private static List<Station> stations5;
    private static List<Station> stations6;

    @BeforeClass
    public static void creatSchedule() throws ParseException {
        schedule = new Schedule();
        stations = new ArrayList<>(Arrays.asList(
                new Station("Brest", "12.10.2019 18:00", "12.10.2019 18:05"),
                new Station("Baranovichi", "12.10.2019 20:00", "12.10.2019 20:05"),
                new Station("Minsk", "12.10.2019 22:00", "12.10.2019 22:05")));
        Train train = new Train(151, 10, 20);
        schedule.addRoute(stations, train);

        stations2 = new ArrayList<>(Arrays.asList(
                new Station("Minsk", "17.08.2019 05:40", "17.08.2019 06:00"),
                new Station("Baranovichi", "17.08.2019 07:40", "17.08.2019 7:50"),
                new Station("Ivatsevichi", "17.08.2019 08:35", "17.08.2019 08:45"),
                new Station("Brest", "17.08.2019 09:00", "17.08.2019 09:20")));
        Train train2 = new Train(345, 10, 30);
        schedule.addRoute(stations2, train2);

        stations3 = new ArrayList<>(Arrays.asList(
                new Station("Brest", "17.08.2019 05:40", "17.08.2019 06:00"),
                new Station("Ivatsevichi", "17.08.2019 07:40", "17.08.2019 7:50"),
                new Station("Baranovichi", "17.08.2019 08:35", "17.08.2019 08:45"),
                new Station("Minsk", "17.08.2019 09:00", "17.08.2019 09:20")));
        Train train3 = new Train(124, 10, 20);
        schedule.addRoute(stations3, train3);

        stations4 = new ArrayList<>(Arrays.asList(
                new Station("Brest", "03.05.2019", "03.05.2019 18:58"),
                new Station("Zhabinka", "03.05.2019 19:16", "03.05.2019 19:17"),
                new Station("Ivatsevichi", "03.05.2019 20:23", "03.05.2019 20:24"),
                new Station("Baranovichi", "03.05.2019 20:59", "03.05.2019 21:00"),
                new Station("Minsk", "03.05.2019 22:30", "03.05.2019 22:50")));
        Train train4 = new Train(784, 10, 20);
        schedule.addRoute(stations4, train4);

        stations5 = new ArrayList<>(Arrays.asList(
                new Station("Brest", "03.05.2019 17:00", "03.05.2019 17:30"),
                new Station("Baranovichi", "03.05.2019 19:27", "03.05.2019 19:30"),
                new Station("Minsk", "03.05.2019 21:12", "03.05.2019 21:30")));
        Train train5 = new Train(464, 10, 20);
        schedule.addRoute(stations5, train5);

        stations6 = new ArrayList<>(Arrays.asList(
                new Station("Terespol", "03.05.2019", "03.05.2019 15:30"),
                new Station("Brest", "03.05.2019 18.50", "03.05.2019 18:00"),
                new Station("Ivatsevichi", "03.05.2019 20:00", "03.05.2019 20:05"),
                new Station("Baranovichi", "03.05.2019 21:00", "03.05.2019 21:05"),
                new Station("Minsk", "03.05.2019 22:00", "03.05.2019 22:30")));
        Train train6 = new Train(145, 10, 20);
        schedule.addRoute(stations6, train6);


    }

    @Test
    public void findRoute() throws ParseException {

        List<Route> expectedRoutesList = Arrays.asList(
                new Route(stations3, new Train(124, 10, 20)));

        try {
            Assert.assertEquals(expectedRoutesList,
                    LogicCashier.findRoute(schedule, "Brest", "Baranovichi",
                            "17.08.2019"));
        } catch (EmptyFieldException | NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findRoute2() throws ParseException, NullException, EmptyFieldException {
        List<Route> expectedRoutesList = Arrays.asList(
                new Route(stations5, new Train(464, 10, 20)),
                new Route(stations6, new Train(145, 10, 20)),
                new Route(stations4, new Train(784, 10, 20)));

        Assert.assertEquals(expectedRoutesList,
                LogicCashier.findRoute(schedule, "Brest", "Minsk",
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
                    LogicCashier.findRoute(schedule, "Baranovichi", "Minsk",
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
                    LogicCashier.findRoute(schedule, "Mogilev", "Moskva", "03.05.2019"));
        } catch (EmptyFieldException | NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findRouteWithNullSchedule() throws ParseException {
        try {
            LogicCashier.findRoute(null, "Brest", "Minsk", "03.05.2019");
        } catch (EmptyFieldException | NullException e) {
            Assert.assertEquals(Schedule.NULL_SCHEDULE_EXCEPTION, e.getMessage());
        }
    }

    @Test
    public void findRouteWithNullField() throws ParseException {
        try {
            LogicCashier.findRoute(schedule, null, "Minsk", "03.05.2019");
        }catch (EmptyFieldException  | NullException e){
            Assert.assertEquals(Route.NULL_INPUT_FIELD_EXCEPTION, e.getMessage());
        }

    }

    @Test
    public void findRouteWithEmptyInputField() throws ParseException {
        try {
            LogicCashier.findRoute(schedule, "", "Minsk", "03.05.2019");
        }catch ( EmptyFieldException | NullException e){
            Assert.assertEquals(Route.EMPTY_FIELD_EXCEPTION, e.getMessage());
        }
    }
}
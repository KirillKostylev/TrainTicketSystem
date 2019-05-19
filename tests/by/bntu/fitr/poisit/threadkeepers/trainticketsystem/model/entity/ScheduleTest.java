package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.ActionWithData;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.ScheduleDataWorker;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleTest {
    private static Schedule schedule;
    private static Schedule newSchedule;

    @BeforeClass
    public static void addTrain() throws NonPositiveException, ParseException {

        String fileName = "scheduleInfo.json";

        schedule = new Schedule();
        List<Station> stations = new ArrayList<>(Arrays.asList(
                new Station("Brest", "12.10.2019 18:00", "12.10.2019 18:05"),
                new Station("Baranovichi", "12.10.2019 20:00", "12.10.2019 20:05"),
                new Station("Minsk", "12.10.2019 22:00", "12.10.2019 22:05")));
        Train train1 = new Train(151, 2, -1);
        schedule.addRoute(stations, train1);

        List<Station> stations2 = new ArrayList<>(Arrays.asList(
                new Station("Minsk", "17.08.2019 05:40", "17.08.2019 06:00"),
                new Station("Baranovichi", "17.08.2019 07:40", "17.08.2019 7:50"),
                new Station("Ivatsevichi", "17.08.2019 08:35", "17.08.2019 08:45"),
                new Station("Brest", "17.08.2019 09:00", "17.08.2019 09:20")));
        Train train2 = new Train(412, 2, -1);
        schedule.addRoute(stations2, train2);

        try {
            ScheduleDataWorker.writeSchedule(schedule, fileName);
            newSchedule = ScheduleDataWorker.readSchedule(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkWriteAndReadFile() {
        Assert.assertEquals(schedule, newSchedule);
    }

    @Test
    public void checkWriteAndReadFileFalse() {

        List<Station> stations = new ArrayList<>(Arrays.asList(
                new Station("Brest", "12.10.2019 18:00", "12.10.2019 18:05"),
                new Station("Baranovichi", "12.10.2019 20:00", "12.10.2019 20:05"),
                new Station("Minsk", "12.10.2019 22:00", "12.10.2019 22:05")));
        Train train = new Train(134, 4, 10);
        schedule.addRoute(stations, train);

        Assert.assertNotEquals(schedule, newSchedule);
    }
}
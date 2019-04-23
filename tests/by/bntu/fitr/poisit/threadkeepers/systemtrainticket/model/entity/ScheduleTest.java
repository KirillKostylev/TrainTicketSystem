package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic.ReaderSchedule;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic.WriterSchedule;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTest {

    @Test
    public void addTrain() {

        String fileName = "scheduleInfo.txt";

        Schedule schedule = new Schedule();
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("Brest", "12.10.2019 18:00", "12.10.2019 18:05"));
        stations.add(new Station("Baranovichi", "12.10.2019 20:00", "12.10.2019 20:05"));
        stations.add(new Station("Minsk", "12.10.2019 22:00", "12.10.2019 22:05"));

        schedule.addNewTrain(151, 2, stations);

        List<Station> stations2 = new ArrayList<>();
        stations2.add(new Station("Minsk", "17.08.2019 05:40", "17.08.2019 06:00"));
        stations2.add(new Station("Baranovichi", "17.08.2019 07:40", "17.08.2019 7:50"));
        stations2.add(new Station("Ivatsevichi", "17.08.2019 08:35", "17.08.2019 08:45"));
        stations2.add(new Station("Brest", "17.08.2019 09:00", "17.08.2019 09:20"));

        schedule.addNewTrain(412, 2, stations2);

        WriterSchedule.saveData(schedule, fileName);

        Schedule newSchedule = ReaderSchedule.readData(fileName);

        Assert.assertEquals(schedule, newSchedule);
    }
}
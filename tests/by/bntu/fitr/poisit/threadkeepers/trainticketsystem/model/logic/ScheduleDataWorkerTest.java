package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ScheduleDataWorkerTest {

    @Test
    public void readScheduleTest_EmptyFile() throws IOException {
        Assert.assertTrue(ScheduleDataWorker.readSchedule("file.json") == null);
    }

    @Test
    public void readScheduleTest_NotEmptyFile() throws IOException {
        Assert.assertFalse(ScheduleDataWorker.readSchedule("schedule.json") == null);
    }
}

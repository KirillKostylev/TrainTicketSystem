package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity.Schedule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriterSchedule {

    public static void saveData(Schedule schedule, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            oos.writeObject(schedule);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

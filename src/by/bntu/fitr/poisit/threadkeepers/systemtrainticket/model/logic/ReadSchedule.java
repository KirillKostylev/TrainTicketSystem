package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity.Schedule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadSchedule {
    public static Schedule readData(String fileName) {
        Schedule schedule = null;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName))) {
            schedule = (Schedule) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
        }
        return schedule;
    }
}

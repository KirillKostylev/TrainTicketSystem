package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import java.io.*;

public class ActionWithData {
    public static Object readObject(String fileName) {
        Object newObj = null;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName))) {
            newObj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
        }
        return newObj;
    }

    public static void writeObject(Object object, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            oos.writeObject(object);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

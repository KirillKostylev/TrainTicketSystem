package model.logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriterData {
    public static final String NAME_FILE = "info.txt";

    public static void saveData(Object o) {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(NAME_FILE))) {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

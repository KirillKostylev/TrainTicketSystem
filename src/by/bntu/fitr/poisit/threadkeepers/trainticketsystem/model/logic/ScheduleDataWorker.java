package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Schedule;
import com.google.gson.Gson;
import org.hildan.fxgson.FxGson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScheduleDataWorker {

    public static void writeSchedule(Object object, String filename) throws IOException {
        Gson fxGson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String jsonString = fxGson.toJson(object);
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(jsonString);
        fileWriter.close();
    }

    public static Schedule readSchedule(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);
        StringBuilder jsonString = new StringBuilder();
        while(scanner.hasNextLine()) {
            jsonString.append(scanner.nextLine());
            jsonString.append("\n");
        }
        fileReader.close();
        Gson fxGson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        return fxGson.fromJson(jsonString.toString(), Schedule.class);
    }

}

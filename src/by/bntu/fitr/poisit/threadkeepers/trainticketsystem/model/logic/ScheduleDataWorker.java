package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Schedule;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.hildan.fxgson.FxGson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScheduleDataWorker {
    private static final Logger LOG = Logger.getLogger(ScheduleDataWorker.class);

    public static void writeSchedule(Object object, String filename) throws IOException {
        Gson fxGson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String jsonString = fxGson.toJson(object);
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(jsonString);
        fileWriter.close();
        LOG.debug("Object has been wrote in json file");
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
        Schedule schedule = fxGson.fromJson(jsonString.toString(), Schedule.class);
        LOG.debug("Schedule has been read from json file");
        return schedule;
    }

}

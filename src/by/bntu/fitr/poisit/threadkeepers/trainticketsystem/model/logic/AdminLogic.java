package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Admin;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.Logger;

import java.io.*;

public class AdminLogic {
    public static String FILE_NAME_WITH_ADMINS_INFO = "adminsInfo.json";
    private static final Logger LOG = Logger.getLogger(AdminLogic.class);


    public static boolean checkLoginAndPassword(String login, String password)
            throws FileNotFoundException, NullException {
        Checker.checkForNullWithException(LOG,Admin.EMPTY_STRING_EXCEPTION, login, password);
        boolean answer = false;
        Admin admin = readAdminsList(FILE_NAME_WITH_ADMINS_INFO);
        if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
            answer = true;
        }
        LOG.debug("Method checkLoginAndPassword has been finished");
        return answer;
    }

    public static void write(Object object, String fileName) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(fileName);
        String string = gson.toJson(object);
        writer.write(string);
        writer.close();
        LOG.debug("Object has been wrote");
    }

    public static Admin readAdminsList(String fileName) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(fileName));
        Admin admin = new Gson().fromJson(reader, Admin.class);
        LOG.debug("Object admin has been read from json file");
        return admin;
    }


}

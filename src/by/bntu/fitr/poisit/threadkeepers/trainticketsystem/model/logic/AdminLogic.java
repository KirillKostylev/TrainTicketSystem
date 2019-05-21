package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Admin;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.AdminsList;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.LinkedList;

public class AdminLogic {
    public static String FILE_NAME_WITH_ADMINS_INFO = "adminsInfo.txt";

//    public static boolean checkLoginAndPassword(String login, String password) throws FileNotFoundException {
//        boolean answer = false;
//        for (Admin admin : AdminsList.getStaticAdmins()) {
//            if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
//                answer = true;
//            }
//        }
//        return answer;
//    }

    public static boolean checkLoginAndPassword(String login, String password)
            throws FileNotFoundException, NullException {
        Checker.checkForNullWithException(Admin.EMTPTY_STRING_EXCEPTION,login, password);
        boolean answer = false;
        Admin admin = readAdminsList(FILE_NAME_WITH_ADMINS_INFO);
        if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
            answer = true;
        }
        return answer;
    }


//    public static Admin createAdmin(String login, String password)
//            throws NullException, EmptyFieldException, IOException {
//
//        Checker.checkForNullWithException(login, password);
//        Checker.checkForEmptyFieldException(login, password);
//
//        Admin admin = new Admin(login, password);
//        write(admin, FILE_NAME_WITH_ADMINS_INFO);
//        return admin;
//    }

    public static void write(Object object, String fileName) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(fileName);
        String string = gson.toJson(object);
        writer.write(string);
        writer.close();
    }

    public static Admin readAdminsList(String fileName) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(fileName));
        return new Gson().fromJson(reader, Admin.class);
    }


}

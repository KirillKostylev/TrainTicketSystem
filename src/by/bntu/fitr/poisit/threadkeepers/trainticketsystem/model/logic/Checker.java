package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;

public class Checker {

    public static void checkForPositiveWithException(int value) throws NonPositiveException {
        if (value <= 0) {
            throw new NonPositiveException();
        }
    }

    public static void checkForNullWithException(String msg, Object... objects) throws NullException {
        for (Object o : objects) {
            if (o == null) {
                throw new NullException(msg);
            }
        }
    }

    public static void checkForEmptyFieldException(String msg, String... fields) throws EmptyFieldException {
        for (String field : fields) {
            if (field.equals("")) {
                throw new EmptyFieldException(msg);
            }
        }
    }
}

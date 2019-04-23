package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.exception.NullException;

public class Checker {

    public static void checkForPositiveWithException(int value) throws NonPositiveException {
        if (value <= 0) {
            throw new NonPositiveException();
        }
    }

    public static void checkForNullWithException(Object object) throws NullException {
        if (object == null) {
            throw new NullException();
        }
    }
}

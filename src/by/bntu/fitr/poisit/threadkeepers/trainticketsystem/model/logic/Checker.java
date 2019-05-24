package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.WrongStationInRouteException;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.regex.Pattern;

public class Checker {

    private static final Logger LOG = Logger.getLogger(Checker.class);

    public static void checkForPositiveWithException(Logger log, String msg, int... values) throws NonPositiveException {
        for (int value : values) {
            if (value <= 0) {
                log.error(msg);
                throw new NonPositiveException(msg);
            }
        }

    }

    public static void checkForNullWithException(Logger log, String msg, Object... objects) throws NullException {
        for (Object o : objects) {
            if (o == null) {
                log.error(msg);
                throw new NullException(msg);
            }
        }
    }

    public static void checkForEmptyFieldException(Logger log, String msg, String... fields) throws EmptyFieldException {
        for (String field : fields) {
            if (field.equals("")) {
                log.error(msg);
                throw new EmptyFieldException(msg);
            }
        }
    }

    public static void checkForSuitableRoute(Logger log, Route route, String departureStation, String arriveStation, String msg)
            throws WrongStationInRouteException {
        for (Station station : route.getStations()) {
            if (route.getStation(departureStation).equals(station) || route.getStation(arriveStation).equals(station)) {
                log.error(msg);
                throw new WrongStationInRouteException(msg);
            }
        }
    }

    public static boolean isRepeatedNameStation(ObservableList<Station> stations, String checkedStationName) {
        for (Station station : stations) {
            if (station.getStationName().equals(checkedStationName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRepeatedTrainNumber(ObservableList<Route> routes, int trainNumber) {
        for (Route route : routes) {
            if (route.getTrain().getTrainNumber() == trainNumber){
                return true;
            }
        }
        return false;
    }

    public static boolean isEmptyString(String ... strings) {
        for(String string: strings) {
            if(string.equals("")) {
                LOG.warn("Empty String!");
                return true;
            }
        }
        LOG.trace("Not empty String!");
        return false;
    }

    public static boolean isIntegerString(String ... strings) {
        for (String string: strings) {
            if(Pattern.matches("\\d*", string)) {
                LOG.trace("String is integer!");
                return true;
            }
        }
        LOG.warn("String is not integer!");
        return false;
    }

    public static boolean isDoubleString(String ... strings) {
        for (String string: strings) {
            if(Pattern.matches("\\d*.\\d*", string) || isIntegerString(string)) {
                LOG.trace("String is double!");
                return true;
            }
        }
        LOG.warn("String is nod double!");
        return false;
    }

    public static boolean isPositiveNumberString(String ... numbers) {
        for (String number: numbers) {
            if(Double.parseDouble(number) > 0) {
                LOG.trace("Number is positive!");
                return true;
            }
        }
        LOG.warn("Number is not positive!");
        return false;
    }

    public static boolean isPositiveIntString(String ... strings) {
        return !isEmptyString(strings) && isIntegerString(strings)
                && isPositiveNumberString(strings);
    }

    public static boolean isPositiveDoubleString(String ... strings) {
        return !isEmptyString(strings) && isDoubleString(strings)
                && isPositiveNumberString(strings);
    }
}

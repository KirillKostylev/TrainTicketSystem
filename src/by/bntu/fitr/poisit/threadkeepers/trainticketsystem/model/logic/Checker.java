package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NonPositiveException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.WrongStationInRouteException;
import org.apache.log4j.Logger;

public class Checker {

    public static void checkForPositiveWithException(Logger log, String msg, int... values) throws NonPositiveException {
        for (int value : values) {
            if (value <= 0) {
                log.error(msg);
                throw new NonPositiveException(msg);
            }
        }

    }

    public static void checkForNullWithException(Logger log,String msg, Object... objects) throws NullException {
        for (Object o : objects) {
            if (o == null) {
                log.error(msg);
                throw new NullException(msg);
            }
        }
    }

    public static void checkForEmptyFieldException(Logger log,String msg, String... fields) throws EmptyFieldException {
        for (String field : fields) {
            if (field.equals("")) {
                log.error(msg);
                throw new EmptyFieldException(msg);
            }
        }
    }

    public static void checkForSuitableRoute(Logger log,Route route, String departureStation, String arriveStation, String msg)
            throws WrongStationInRouteException {
        for (Station station : route.getStations()) {
            if (route.getStation(departureStation).equals(station) || route.getStation(arriveStation).equals(station)) {
                log.error(msg);
                throw new WrongStationInRouteException(msg);
            }
        }
    }
}

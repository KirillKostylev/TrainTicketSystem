package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

//import by.bntu.fitr.poisit.threadkeepers.by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic.domain.*;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Schedule;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogicCashier {


    public static void buyTicket(Route route, int carriageNuumber, int seatNumber) {

    }

    public static List<Route> findRoute(Schedule schedule, String departureStation, String arriveStation,
                                        String departureTime) throws ParseException,
            EmptyFieldException, NullException {

        Checker.checkForNullWithException(Schedule.NULL_SCHEDULE_EXCEPTION, schedule);
        Checker.checkForNullWithException(Route.NULL_INPUT_FIELD_EXCEPTION,
                departureStation, arriveStation, departureTime);
        Checker.checkForEmptyFieldException(Route.EMPTY_FIELD_EXCEPTION, departureStation, arriveStation, departureTime);

        Calendar calendarDepartTime = Calendar.getInstance();
        Date newDepartureTime = new SimpleDateFormat(Station.DATE_FORMAT).parse(departureTime);
        calendarDepartTime.setTime(newDepartureTime);

        List<Route> suitableRoutes = new LinkedList<>();
        for (Route route : schedule.getRoutes()) {
            List<String> stationsName = route.getStationsName();
            Calendar date;
            if (stationsName.contains(departureStation) && stationsName.contains(arriveStation)) {
                date = route.getStation(departureStation).getDepartTime();
            } else {
                break;
            }
            if (LogicCashier.compareDate(calendarDepartTime, date) &&
                    // compare departure time and customer departure time
                    stationsName.indexOf(departureStation) < stationsName.indexOf(arriveStation)) {
                //checking the correct direction of the train
                suitableRoutes.add(route);
            }
        }

        Comparator<Route> comparator = Comparator.comparing(obj -> obj.getStation(departureStation).getDepartTime());
        suitableRoutes.sort(comparator);
        return suitableRoutes;
    }

    private static boolean compareDate(Calendar calendar1, Calendar calendar2) {
        return
                calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH) &&
                        calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                        calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);

    }
}

package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.*;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogicCashier {


    public static void buyTicket(Route route, int carriageNumber, int seatNumber, String departureStation,
                                 String arriveStation)
            throws NullException, NonPositiveException, WrongCarriageNumber, WrongSeatNumber, EmptyFieldException,
            WrongStationInRouteException {

        Checker.checkForNullWithException(Route.NULL_ROUTE_EXCEPTION, route);
        Checker.checkForPositiveWithException(SeatContainer.INVALID_VALUE_EXCEPTION, seatNumber);
        Checker.checkForPositiveWithException(SeatContainer.INVALID_VALUE_EXCEPTION, carriageNumber);
        Checker.checkForNullWithException(Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation);
        Checker.checkForEmptyFieldException(Route.EMPTY_FIELD_EXCEPTION, departureStation, arriveStation);

        SeatContainer seatContainer = route.getTrain().getSeatContainer();

        if (seatContainer.getCarriageCount() < carriageNumber) {
            throw new WrongCarriageNumber(SeatContainer.WRONG_CARRIAGE_NUMBER);
        }
        if (seatContainer.getSeatCount() < seatNumber) {
            throw new WrongSeatNumber(SeatContainer.WRONG_SEAT_NUMBER);
        }

//        Checker.checkForSuitableRoute(route, departureStation,arriveStation,"as");


        seatContainer.getSeat(carriageNumber - 1, seatNumber - 1).addBusyStations(
                getSuitableStations(route, departureStation, arriveStation));

    }

    public static List<Route> findRoute(Schedule schedule, String departureStation, String arriveStation,
                                        String departureTime) throws ParseException,
            EmptyFieldException, NullException {

        Checker.checkForNullWithException(Schedule.NULL_SCHEDULE_EXCEPTION, schedule);
        Checker.checkForNullWithException(Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation,
                departureTime);
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

    public static List<Integer> findCarriagesNumberWithFreeSeats(
            Route route, String departureStation, String arriveStation) throws NullException, EmptyFieldException {
        List<Integer> carriagesNumberWithFreeSeats = new ArrayList<>();

        Checker.checkForNullWithException(Route.NULL_ROUTE_EXCEPTION, route);
        Checker.checkForNullWithException(Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation);
        Checker.checkForEmptyFieldException(Route.EMPTY_FIELD_EXCEPTION, departureStation, arriveStation);

        List<Station> suitableStations = getSuitableStations(route, departureStation, arriveStation);

        SeatContainer seatContainer = route.getTrain().getSeatContainer();

        for (int i = 0; i < seatContainer.getCarriageCount(); i++) {
            for (int j = 0; j < seatContainer.getSeatCount(); j++) {
                if (checkSeatForFree(seatContainer.getSeat(i, j), suitableStations)) {
                    carriagesNumberWithFreeSeats.add(i + 1);
                    break;
                }
            }
        }


        return carriagesNumberWithFreeSeats;
    }

    private static boolean checkSeatForFree(Seat seat, List<Station> suitableStations) {
        boolean seatIsFree = true;
        for (Station station : suitableStations) {
            if (seat.getBusyStations().contains(station)) {
                seatIsFree = false;
            }
        }
        return seatIsFree;
    }

    private static boolean compareDate(Calendar calendar1, Calendar calendar2) {
        return
                calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH) &&
                        calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                        calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);

    }

    private static List<Station> getSuitableStations(Route route, String departureStation, String arriveStation) {

        List<Station> suitableStations = new LinkedList<>();

        boolean flag = false; //if flag == true we add station in suitableStations
        for (Station station : route.getStations()) {
            if (route.getStation(departureStation).equals(station)) {
                flag = true;
            }
            if (flag) {
                suitableStations.add(station);
            }
            if (route.getStation(arriveStation).equals(station)) {
                flag = false;
            }
        }

//        int tempCount = 0;
//        for (Station station : route.getStations()) {
//            if (route.getStation(departureStation).equals(station) || route.getStation(arriveStation).equals(station)) {
//                tempCount++;
//                suitableStations.add(station);
//                continue;
//            }
//            if (tempCount == 1) {
//                suitableStations.add(station);
//            }
//        }

        return suitableStations;
    }
}

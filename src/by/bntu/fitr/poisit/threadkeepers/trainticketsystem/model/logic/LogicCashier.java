package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.*;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogicCashier {
    public static final double COST_PER_KM = 0.03;

    public static Ticket buyTicket(Route route, int carriageNumber, int seatNumber, String departureStation,
                                   String arriveStation)
            //TODO Переделать станции под объект Station
            throws NullException, NonPositiveException, WrongCarriageNumber, WrongSeatNumber, EmptyFieldException {

        Ticket ticket = null;

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

        if (checkSeatForFree(route.getTrain().getSeatContainer().getSeat(carriageNumber - 1,
                seatNumber - 1),
                getSuitableStations(route, departureStation, arriveStation))) {
            seatContainer.getSeat(carriageNumber - 1, seatNumber - 1).addBusyStations(
                    getSuitableStations(route, departureStation, arriveStation));
            Train train = route.getTrain();

            ticket = new Ticket(train.getTrainNumber(), departureStation, arriveStation,
                    route.getStation(departureStation).getDepartureTime(),
                    route.getStation(arriveStation).getArriveTime(), carriageNumber, seatNumber,
                    calculateTicketCost(departureStation, arriveStation));
        }
        return ticket;
    }


    public static List<Route> findRoutes(Schedule schedule, String departureStation, String arriveStation,
                                         String departureTime) throws ParseException,
            EmptyFieldException, NullException {

        Checker.checkForNullWithException(Schedule.NULL_SCHEDULE_EXCEPTION, schedule);
        Checker.checkForNullWithException(Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation,
                departureTime);
        Checker.checkForEmptyFieldException(Route.EMPTY_FIELD_EXCEPTION, departureStation, arriveStation, departureTime);

        Calendar calendarDepartDate = Calendar.getInstance();
        Date newDepartureDate = new SimpleDateFormat(Station.DATE_FORMAT).parse(departureTime);
        calendarDepartDate.setTime(newDepartureDate);

        List<Route> suitableRoutes = new LinkedList<>();
        for (Route route : schedule.getRoutes()) {
            List<String> stationsName = route.getStationsName();
            Calendar date;
            if (stationsName.contains(departureStation) && stationsName.contains(arriveStation)) {
                date = route.getStation(departureStation).getCalendarDepartureTime();
            } else {
                break;
            }
            if (LogicCashier.compareDate(calendarDepartDate, date) &&
                    // compare departure time and customer departure time
                    stationsName.indexOf(departureStation) < stationsName.indexOf(arriveStation)) {
                //checking the correct direction of the train
                suitableRoutes.add(route);
            }
        }
        Comparator<Route> comparator = Comparator.comparing(obj ->
        {
            try {
                return obj.getStation(departureStation).getCalendarDepartureTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        });
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


    public static List<Integer> findFreeSeatsInCarriage(
            Route route, String departureStation, String arriveStation, int carriageNumber) throws NullException,
            EmptyFieldException {

        List<Integer> freeSeatNumbers = new ArrayList<>();

        Checker.checkForNullWithException(Route.NULL_ROUTE_EXCEPTION, route);
        Checker.checkForNullWithException(Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation);
        Checker.checkForEmptyFieldException(Route.EMPTY_FIELD_EXCEPTION, departureStation, arriveStation);

        List<Station> suitableStations = getSuitableStations(route, departureStation, arriveStation);
        SeatContainer seatContainer = route.getTrain().getSeatContainer();

        for (int j = 0; j < seatContainer.getSeatCount(); j++) {
            if (checkSeatForFree(seatContainer.getSeat(carriageNumber - 1, j), suitableStations)) {
                freeSeatNumbers.add(j + 1);
            }
        }
        return freeSeatNumbers;
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
        return suitableStations;
    }

    private static double calculateTicketCost(String departureStation, String arriveStation) {
        return Math.round(COST_PER_KM *
                DistanceCalculator.distanceCalculate(departureStation, arriveStation) * 100.0) / 100.0;
    }
}

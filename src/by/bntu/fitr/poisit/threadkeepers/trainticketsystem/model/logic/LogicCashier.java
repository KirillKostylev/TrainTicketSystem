package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.*;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogicCashier {
    public static final double COST_PER_KM = 0.03;

    private static final Logger LOG = Logger.getLogger(LogicCashier.class);

    public static Ticket buyTicket(Route route, int carriageNumber, int seatNumber, Station departureStation,
                                   Station arriveStation, double costPerKm)
            throws NullException {

        Ticket ticket = null;

        Checker.checkForNullWithException(LOG, Route.NULL_ROUTE_EXCEPTION, route);
        Checker.checkForNullWithException(LOG, Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation);

        SeatContainer seatContainer = route.getTrain().getSeatContainer();


        if (checkSeatForFree(seatContainer.getSeat(carriageNumber - 1,
                seatNumber - 1),
                getSuitableStations(route, departureStation, arriveStation))) {
            seatContainer.getSeat(carriageNumber - 1, seatNumber - 1).addBusyStations(
                    getSuitableStations(route, departureStation, arriveStation));
            Train train = route.getTrain();

            ticket = new Ticket(train.getTrainNumber(), departureStation.getStationName(), arriveStation.getStationName(),
                    departureStation.getDepartureTime(),
                    arriveStation.getArriveTime(), carriageNumber, seatNumber,
                    calculateTicketCost(departureStation, arriveStation, costPerKm));
        }
        LOG.debug("Ticket has been bought");
        return ticket;
    }


    public static List<Route> findRoutes(Schedule schedule, String departureStation, String arriveStation,
                                         String departureTime) throws ParseException,
            EmptyFieldException, NullException {

        Checker.checkForNullWithException(LOG, Schedule.NULL_SCHEDULE_EXCEPTION, schedule);
        Checker.checkForNullWithException(LOG, Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation,
                departureTime);

        Checker.checkForEmptyFieldException(LOG, Route.EMPTY_FIELD_EXCEPTION, departureStation, arriveStation, departureTime);

        Calendar calendarDepartDate = Calendar.getInstance();
        calendarDepartDate.setTime(convertStringToDate(departureTime, Station.DATE_FORMAT));

        List<Route> suitableRoutes = new LinkedList<>();
        for (Route route : schedule.getRoutes()) {
            List<String> stationsName = route.getStationsName();
            Date date;
            if (stationsName.contains(departureStation) && stationsName.contains(arriveStation)) {
                date = convertStringToDate(route.getStation(departureStation).getDepartureTime(), Station.DATE_FORMAT);
            } else {
                continue;
            }
            if (date.compareTo(convertStringToDate(departureTime, Station.DATE_FORMAT)) == 0 &&
                    // compare departure time and customer departure time
                    stationsName.indexOf(departureStation) < stationsName.indexOf(arriveStation)) {
                //checking the correct direction of the train
                suitableRoutes.add(route);
            }
        }
        Comparator<Route> comparator = Comparator.comparing(obj ->
        {
            try {
                return convertStringToDate(obj.getStation(departureStation).getDepartureTime(), Station.TIME_FORMAT);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        });
        suitableRoutes.sort(comparator);
        LOG.debug("Searching was finished");
        return suitableRoutes;
    }

    public static ObservableList<String> findCarriagesNumberWithFreeSeats(
            Route route, Station departureStation, Station arriveStation) throws NullException {
        ObservableList<String> carriagesNumberWithFreeSeats = FXCollections.observableArrayList();

        Checker.checkForNullWithException(LOG, Route.NULL_ROUTE_EXCEPTION, route);
        Checker.checkForNullWithException(LOG, Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation);

        List<Station> suitableStations = getSuitableStations(route, departureStation, arriveStation);

        SeatContainer seatContainer = route.getTrain().getSeatContainer();

        for (int i = 0; i < seatContainer.getCarriageCount(); i++) {
            for (int j = 0; j < seatContainer.getSeatCount(); j++) {
                if (checkSeatForFree(seatContainer.getSeat(i, j), suitableStations)) {
                    carriagesNumberWithFreeSeats.add(i + 1 + "");
                    break;
                }
            }
        }
        LOG.debug("Searching carriages number with free seats was finished ");
        return carriagesNumberWithFreeSeats;
    }


    public static ObservableList<String> findFreeSeatsInCarriage(
            Route route, Station departureStation, Station arriveStation, int carriageNumber) throws NullException {

        ObservableList<String> freeSeatNumbers = FXCollections.observableArrayList();

        Checker.checkForNullWithException(LOG, Route.NULL_ROUTE_EXCEPTION, route);
        Checker.checkForNullWithException(LOG, Route.NULL_INPUT_FIELD_EXCEPTION, departureStation, arriveStation);

        List<Station> suitableStations = getSuitableStations(route, departureStation, arriveStation);
        SeatContainer seatContainer = route.getTrain().getSeatContainer();

        for (int j = 0; j < seatContainer.getSeatCount(); j++) {
            if (checkSeatForFree(seatContainer.getSeat(carriageNumber - 1, j), suitableStations)) {
                freeSeatNumbers.add(j + 1 + "");
            }
        }
        LOG.debug("Searching free seats number in carriage was finished");
        return freeSeatNumbers;
    }

    public static Date convertStringToDate(String date, String formatDate) throws ParseException {
        Date dt=  new SimpleDateFormat(formatDate).parse(date);
        LOG.info("Convert string date to Date was finished");
        return dt;


    }

    public static boolean checkSeatForFree(Seat seat, List<Station> suitableStations) {
        boolean seatIsFree = false;
        for (Station station : suitableStations) {
            if (!seat.getBusyStations().contains(station)) {
                seatIsFree = true;
                break;
            }
        }
        LOG.debug("Seat has been checked for free");
        return seatIsFree;
    }

    private static List<Station> getSuitableStations(Route route, Station departureStation, Station arriveStation) {

        List<Station> suitableStations = new LinkedList<>();

        boolean flag = false; //if flag == true we add station in suitableStations
        for (Station station : route.getStations()) {
            if (departureStation.equals(station)) {
                flag = true;
            }
            if (flag) {
                suitableStations.add(station);
            }
            if (arriveStation.equals(station)) {
                flag = false;
            }
        }
        return suitableStations;
    }

    private static double calculateTicketCost(Station departureStation, Station arriveStation, double costPerKm) {
        double ans = Math.round(costPerKm *
                DistanceCalculator.distanceCalculate(departureStation.getStationName(),
                        arriveStation.getStationName()) * 100.0) / 100.0;
        LOG.debug("Ticket cost was calculated");
        return ans;
    }


}

package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic;

//import by.bntu.fitr.poisit.threadkeepers.by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic.domain.*;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity.Schedule;

import java.util.ArrayList;
import java.util.List;

public class LogicCashier {

    public static void buyTicket(Route route, int carriageNuumber, int seatNumber) {

    }

    public static List<Route> findRoute(Schedule schedule, String departureStation, String arriveStation,
                                        String departureTime) {
        List<Route> suitableRoutes = new ArrayList<>();
        for (Route route : schedule.getRoutes()) {
            List<String> stationsName = route.getStationsName();
            if (stationsName.contains(departureStation) && stationsName.contains(arriveStation) &&
                    route.getStation(departureStation).getDepartTime().startsWith(departureTime) &&
                    // compare departure time and customer departure time

                    stationsName.indexOf(departureStation) < stationsName.indexOf(arriveStation)) {
                //checking the correct direction of the train

                suitableRoutes.add(route);
            }
        }
        return suitableRoutes;
    }
}

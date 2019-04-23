package by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic;

//import by.bntu.fitr.poisit.threadkeepers.by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.logic.domain.*;

import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity.Schedule;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.systemtrainticket.model.entity.Train;

import java.util.ArrayList;
import java.util.List;

public class LogicCashier {

    public static void buyTicket() {
    }

    // todo ДОБАВИТЬ ПОИСК ПО ДАТЕ
    public static List<Train> findTrain(Schedule schedule, String departureStation, String arriveStation) {
        List<Train> suitableTrains = new ArrayList<>();
        for (Train train : schedule.getTrains()) {
            List<String> stationsList = getListOfStationNames(train.getStationsInTransit());
            if (stationsList.contains(departureStation) && stationsList.contains(arriveStation) &&
                    stationsList.indexOf(departureStation) < stationsList.indexOf(arriveStation)) {
                //checking the correct direction of the train
                suitableTrains.add(train);
            }
        }
        return suitableTrains;
    }

    private static List<String> getListOfStationNames(List<Station> listStations) {
        List<String> listStationNames = new ArrayList<String>();
        for (Station listStation : listStations) {
            listStationNames.add(listStation.getNameStation());
        }
        return listStationNames;
    }
}

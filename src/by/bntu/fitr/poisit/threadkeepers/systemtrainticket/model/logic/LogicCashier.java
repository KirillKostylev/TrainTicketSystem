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
            List<String> stationsList = getListFromStationsFields(train.getStationsInTransit(), "name");
            if (stationsList.contains(departureStation) && stationsList.contains(arriveStation) &&
                    stationsList.indexOf(departureStation) < stationsList.indexOf(arriveStation)) {
                //checking the correct direction of the train
                suitableTrains.add(train);
            }
        }
        return suitableTrains;
    }



    // field = "name" - getting list of stations names
    // field = "date" - getting list of stations departure date
    private static List<String> getListFromStationsFields(List<Station> listStations, String field) {
        List<String> listFromStationsFields = new ArrayList<>();
        for (Station listStation : listStations) {
            if (field.equals("name")) {
                listFromStationsFields.add(listStation.getNameStation());
            } else  if(field.equals("date")){
                listFromStationsFields.add(listStation.getDepartTime());
            }
        }
        return listFromStationsFields;
    }
}

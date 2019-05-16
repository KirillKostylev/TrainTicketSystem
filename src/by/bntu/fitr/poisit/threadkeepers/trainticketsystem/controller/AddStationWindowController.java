package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class AddStationWindowController {

    private AddRouteWindowController addRouteWindowController;

    @FXML
    private TextField stationName;

    @FXML
    private DatePicker arriveDate;

    @FXML
    private DatePicker departureDate;

    @FXML
    private ComboBox<String> departureHours;

    @FXML
    private ComboBox<String> departureMinutes;

    @FXML
    private ComboBox<String> arriveHours;

    @FXML
    private ComboBox<String> arriveMinutes;

    @FXML
    void addStation(ActionEvent event) {
        String departureTime = departureDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))
                + " " + departureHours.getValue() + ":"
                + departureMinutes.getValue();
        String arriveTime = arriveDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))
                + " " + arriveHours.getValue()+ ":"
                + arriveMinutes.getValue();
        Station station = new Station(stationName.getText(), departureTime, arriveTime);
        addRouteWindowController.addStation(station);
        Util.closeWindow(event);
        //TODO сделать что нибудь чтобы нельзя было ввести дату отправления позже чем дату
        // приезда тоже самое со временем (сделай методы сравнения дат и времени время в формате hh:mm)
    }

    @FXML
    void cancelAction(ActionEvent event) {
        Util.closeWindow(event);
    }

    @FXML
    void initialize() {
        ObservableList<String> hourList = FXCollections.observableArrayList();
        ObservableList<String> minuteList = FXCollections.observableArrayList();
        String number = "";
        for (int i = 0; i < 61; i++) {
            if (i < 10) {
                number = "0" + i;
            } else {
                number = i + "";
            }
            if (i < 24) {
                hourList.add(number);
            }
            minuteList.add(number);
        }
        departureHours.setItems(hourList);
        departureMinutes.setItems(minuteList);
        arriveHours.setItems(hourList);
        arriveMinutes.setItems(minuteList);
    }

    void setParent(AddRouteWindowController addRouteWindowController) {
        this.addRouteWindowController = addRouteWindowController;
    }

}

package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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
        if (isFieldsNotFilled()) {
            Util.showError("You must fill all fields!");
            return;
        }
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
        departureHours.setItems(Util.getObservableListWithNumbers(0, 60));
        departureMinutes.setItems(Util.getObservableListWithNumbers(0, 24));
        arriveHours.setItems(Util.getObservableListWithNumbers(0, 24));
        arriveMinutes.setItems(Util.getObservableListWithNumbers(0, 24));
    }

    void setParent(AddRouteWindowController addRouteWindowController) {
        this.addRouteWindowController = addRouteWindowController;
    }


    private boolean isFieldsNotFilled() {
        return departureDate.getValue() == null || departureHours.getValue() == null
                || departureMinutes.getValue() == null || arriveDate.getValue() == null
                || arriveHours.getValue() == null || arriveMinutes.getValue() == null
                || stationName.getText().equals("");
    }

}

package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.LogicCashier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    void addStationAction(ActionEvent event) {
        if (isFieldsNotFilled()) {
            Util.showError("You must fill all fields!");
            return;
        }
        String departureTime = departureDate.getValue().format(DateTimeFormatter
                .ofPattern(Station.DATE_FORMAT))
                + " " + departureHours.getValue() + ":"
                + departureMinutes.getValue();
        String arriveTime = arriveDate.getValue().format(DateTimeFormatter
                .ofPattern(Station.DATE_FORMAT))
                + " " + arriveHours.getValue()+ ":"
                + arriveMinutes.getValue();
        try {
            Date departureDateTime = LogicCashier.convertStringToDate(departureTime,
                    Station.DATE_FORMAT + " " + Station.TIME_FORMAT);
            Date arriveDateTime = LogicCashier.convertStringToDate(arriveTime,
                    Station.DATE_FORMAT + " " + Station.TIME_FORMAT);
            if (departureDateTime.compareTo(arriveDateTime) > 0) {
                Util.showError("Date and time of arrival more than departure!");
                return;
            }
        } catch (ParseException e) {
            Util.showError("Wrong data format!");
            e.printStackTrace();
        }
        Station station = new Station(stationName.getText(), departureTime, arriveTime);
        addRouteWindowController.addStation(station);
        Util.closeWindow(event);
    }

    @FXML
    void cancelAction(ActionEvent event) {
        Util.closeWindow(event);
    }

    @FXML
    void initialize() {
        departureHours.setItems(Util.getObservableListWithNumbers(0, 24));
        departureMinutes.setItems(Util.getObservableListWithNumbers(0, 60));
        arriveHours.setItems(Util.getObservableListWithNumbers(0, 24));
        arriveMinutes.setItems(Util.getObservableListWithNumbers(0, 60));
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

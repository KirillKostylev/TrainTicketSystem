package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.Checker;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.LogicCashier;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

public class AddStationWindowController {

    private AddRouteWindowController addRouteWindowController;

    @FXML
    private TextField stationName;

    @FXML
    private DatePicker arriveDatePicker;

    @FXML
    private DatePicker departureDatePicker;

    @FXML
    private ComboBox<String> departureHoursComboBox;

    @FXML
    private ComboBox<String> departureMinutesComboBox;

    @FXML
    private ComboBox<String> arriveHoursComboBox;

    @FXML
    private ComboBox<String> arriveMinutesComboBox;

    @FXML
    void addStationAction(ActionEvent event) {
        if (isFieldsNotFilled()) {
            ControllerUtil.showError("You must fill all fields!");
            return;
        }
        addStation();
        ControllerUtil.closeWindow(event);
    }

    private void addStation() {
        String departureTime = ControllerUtil.getDateTimeString(departureDatePicker, departureHoursComboBox,
                departureMinutesComboBox);
        String arriveTime = ControllerUtil.getDateTimeString(arriveDatePicker, arriveHoursComboBox,
                arriveMinutesComboBox);
        if (isValidData(departureTime, arriveTime)) {
            Station station = new Station(stationName.getText(), departureTime, arriveTime);
            addRouteWindowController.addStation(station);
        }
    }

    private boolean isValidData(String departureTime, String arriveTime){
        boolean isValidData = true;
        if (Pattern.matches("\\W*", stationName.getText())) {
            ControllerUtil.showError("Station name may contain only letters");
            isValidData = false;
        }
        if(!isDatesValid(departureTime, arriveTime)) {
            isValidData = false;
        } else if(isDateValidWithPreviousStation(departureTime)) {
            isValidData = false;
        } else if (Checker.isRepeatedNameStation(addRouteWindowController.getStationList(),
                stationName.getText())) {
            ControllerUtil.showError("Already have station with this name");
            isValidData = false;
        }
        return isValidData;
    }

    private boolean isDatesValid(String departureTime, String arriveTime) {
        try {
            Date departureDateTime = LogicCashier.convertStringToDate(departureTime, Station.TIME_FORMAT);
            Date arriveDateTime = LogicCashier.convertStringToDate(arriveTime, Station.TIME_FORMAT);
            if (departureDateTime.compareTo(arriveDateTime) < 0) {
                ControllerUtil.showError("Date and time of arrival more than departure!" +
                        " (Maybe problem in previous station)");
                return false;
            }
        } catch (ParseException e) {
            ControllerUtil.showError("Wrong data format!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean isDateValidWithPreviousStation(String departureTime) {
        Station lastStation = addRouteWindowController.getLastStation();
        String arriveTime = lastStation.getArriveTime();
        return isDatesValid(departureTime, arriveTime);
    }

    @FXML
    void cancelAction(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }

    @FXML
    void initialize() {
        departureHoursComboBox.setItems(ControllerUtil.getObservableListWithNumbers(0, 24));
        departureMinutesComboBox.setItems(ControllerUtil.getObservableListWithNumbers(0, 60));
        arriveHoursComboBox.setItems(ControllerUtil.getObservableListWithNumbers(0, 24));
        arriveMinutesComboBox.setItems(ControllerUtil.getObservableListWithNumbers(0, 60));
        ControllerUtil.setFocus(stationName);
    }

    void setParent(AddRouteWindowController addRouteWindowController) {
        this.addRouteWindowController = addRouteWindowController;
    }


    private boolean isFieldsNotFilled() {
        return departureDatePicker.getValue() == null || departureHoursComboBox.getValue() == null
                || departureMinutesComboBox.getValue() == null || arriveDatePicker.getValue() == null
                || arriveHoursComboBox.getValue() == null || arriveMinutesComboBox.getValue() == null
                || stationName.getText().equals("");
    }

}

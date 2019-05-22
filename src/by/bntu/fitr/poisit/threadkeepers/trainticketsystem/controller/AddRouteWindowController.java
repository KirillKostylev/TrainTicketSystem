package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Train;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.regex.Pattern;

public class AddRouteWindowController {

    private boolean isEdit;
    private RouteListWindowController routeListWindowController;
    private ObservableList<Station> stationList;

    @FXML
    private TextField trainNumberTextField;

    @FXML
    private TextField carriagesNumberTextField;

    @FXML
    private TextField seatNumberTextField;

    @FXML
    private TableView<Station> stationTableView;

    @FXML
    private TableColumn<Station, String> stationNameColumn;

    @FXML
    private TableColumn<Station, String> departureTimeColumn;

    @FXML
    private TableColumn<Station, String> arriveTimeColumn;

    @FXML
    void addRouteAction(ActionEvent event) {
        if(isValidData()) {
            Route route = formRoute();
            if (isEdit) {
                routeListWindowController.editRoute(route);
            } else {
                routeListWindowController.addRoute(route);
            }
            ControllerUtil.closeWindow(event);
        }
    }

    @FXML
    void addStationAction(ActionEvent event){
        try {
            FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/addStationWindow.fxml");
            Parent root = loader.load();
            AddStationWindowController addStationWindowController = loader.getController();
            addStationWindowController.setParent(this);
            ControllerUtil.openChildModalWindow("Add Station", root, event);
        } catch (IOException e) {
            ControllerUtil.showError("Loading resource error!");
            e.printStackTrace();
        }
    }

    @FXML
    void cancelAction(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }

    @FXML
    void removeStationAction(ActionEvent event) {
        Station selectedStation = stationTableView.getSelectionModel().getSelectedItem();
        stationList.remove(selectedStation);
    }

    @FXML
    void initialize() {
        stationList = FXCollections.observableArrayList();
        isEdit = false;
        ControllerUtil.setFactoryForStationTable(stationNameColumn, departureTimeColumn, arriveTimeColumn);
        stationTableView.setItems(stationList);
    }

    void addStation(Station station) {
        stationList.add(station);
    }

    void setParent(RouteListWindowController routeListWindowController) {
        this.routeListWindowController = routeListWindowController;
    }

    void editInit(Route route) {
        isEdit = true;
        trainNumberTextField.setText(route.getTrain().getTrainNumber() + "");
        carriagesNumberTextField.setText(route.getTrain().getCarriagesNumber() + "");
        seatNumberTextField.setText(route.getTrain().getCarriagesNumber() + "");
        stationList.addAll(FXCollections.observableArrayList(route.getStations()));
    }

    Station getLastStation() {
        return stationTableView.getItems().get(stationTableView.getItems().size() - 1);
    }

    private boolean isValidData() {
        //TODO сделать проверку на одинаковые номера поездов
        boolean isValidData = true;
        if (trainNumberTextField.getText().equals("") || carriagesNumberTextField.getText().equals("")
                || seatNumberTextField.getText().equals("") || stationList.size() < 2) {
            ControllerUtil.showError("Fill All Fields! Route must have at least 2 stations.");
            isValidData = false;
        } else if (Pattern.matches("\\D*", trainNumberTextField.getText())
                && Pattern.matches("\\D*", carriagesNumberTextField.getText())
                && Pattern.matches("\\D*", seatNumberTextField.getText())) {
            ControllerUtil.showError("Number fields must be number!!!");
            isValidData = false;
        } else if (Integer.parseInt(trainNumberTextField.getText()) < 0) {
            ControllerUtil.showError("Train number mus be positive number!");
            isValidData = false;
        }
        return isValidData;
    }

    private Route formRoute() {
        Train train = new Train(Integer.parseInt(trainNumberTextField.getText()),
                Integer.parseInt(carriagesNumberTextField.getText()),
                Integer.parseInt(seatNumberTextField.getText()));
        return new Route(stationList, train);
    }
}

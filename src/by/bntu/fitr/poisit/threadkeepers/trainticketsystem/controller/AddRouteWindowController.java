package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Train;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.Checker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.io.IOException;

public class AddRouteWindowController {

    private static final Logger LOG = Logger.getLogger(AddRouteWindowController.class);
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
    private TableColumn<Station, String> arriveTimeColumn;

    @FXML
    private TableColumn<Station, String> departureTimeColumn;

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
            ControllerUtil.openModalWindow("Add Station", root, event);
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
        ControllerUtil.setFactoryForStationTable(stationNameColumn, arriveTimeColumn, departureTimeColumn);
        stationTableView.setItems(stationList);
        ControllerUtil.setFocus(trainNumberTextField);
    }

    void addStation(Station station) {
        LOG.trace("Station " + station.getStationName() + " added");
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
        if (stationTableView.getItems().size() != 0) {
            return stationTableView.getItems().get(stationTableView.getItems().size() - 1);
        } else {
            return null;
        }
    }

    private boolean isValidData() {
        boolean isValidData = true;
        ObservableList<Route> routeObservableList = FXCollections
                .observableArrayList(routeListWindowController.getRouteList());
        if(!Checker.isPositiveIntString(trainNumberTextField.getText() ,
                carriagesNumberTextField.getText(),
                seatNumberTextField.getText())) {
            ControllerUtil.showError("Number fields must be positive integer numbers!");
            isValidData = false;
        } else if (Checker.isRepeatedTrainNumber(
                routeObservableList, Integer.parseInt(trainNumberTextField.getText()))
            && !isEdit) {
            ControllerUtil.showError("Already have route with this number");
            isValidData = false;
        } else if (isEdit){
            routeObservableList.remove(routeListWindowController.getSelectedRoute());
            if(Checker.isRepeatedTrainNumber(
                    routeObservableList, Integer.parseInt(trainNumberTextField.getText()))) {
                ControllerUtil.showError("Already have route with this number");
                isValidData = false;
            }
        }
        return isValidData;
    }

    private Route formRoute() {
        Train train = new Train(Integer.parseInt(trainNumberTextField.getText()),
                Integer.parseInt(carriagesNumberTextField.getText()),
                Integer.parseInt(seatNumberTextField.getText()));
        return new Route(stationList, train);
    }

    ObservableList<Station> getStationList() {
        return stationTableView.getItems();
    }
}

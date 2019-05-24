package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Schedule;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.Checker;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.LogicCashier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.ScheduleDataWorker;
import org.apache.log4j.Logger;

public class RouteListWindowController {

    private static final Logger LOG = Logger.getLogger(RouteListWindowController.class);

    private Schedule schedule;
    private double costForKm = 0.03;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField departureStation;

    @FXML
    private TextField arriveStation;

    @FXML
    private DatePicker departureDate;

    @FXML
    private TableView<Route> routesTable;

    @FXML
    private TableColumn<Route, Integer> numberColumn;

    @FXML
    private TableColumn<Route, String> departureStationColumn;

    @FXML
    private TableColumn<Route, String> arriveStationColumn;

    @FXML
    private TableColumn<Route, String> departureTimeColumn;

    @FXML
    private TableColumn<Route, String> arriveTimeColumn;

    @FXML
    private Button removeRouteBtn;

    @FXML
    private Button editRouteBtn;

    @FXML
    private Button addRouteBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private TextField costTextField;

    @FXML
    private Label costLabel;

    @FXML
    private Button setCostBtn;


    @FXML
    void loginAction(ActionEvent event) throws IOException {
        FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/loginWindow.fxml");
        Parent root = loader.load();
        LoginWindowController loginWindowController = loader.getController();
        loginWindowController.setParent(this);
        ControllerUtil.openModalWindow("Log In", root, event);
    }

    @FXML
    void searchAction(ActionEvent event) {
        searchRoutes();
    }

    @FXML
    void initialize() {
        try {
            schedule = ScheduleDataWorker.readSchedule("schedule.json");
            fillScheduleTable();
        } catch (IOException e) {
            ControllerUtil.showError("Error loading data!");
            e.printStackTrace();
        }
    }

    private void searchRoutes() {
        try {
            if (departureDate.getValue() == null) {
                throw new EmptyFieldException();
            }
            List<Route> filteredRoutes = LogicCashier.findRoutes(
                    schedule,
                    departureStation.getText(),
                    arriveStation.getText(),
                    departureDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
            fillFilteredScheduleTable(filteredRoutes, departureStation.getText(), arriveStation.getText());
        } catch (ParseException | NullException e) {
            errorLabel.setText("Program error!");
            e.printStackTrace();
        } catch (EmptyFieldException e) {
            errorLabel.setText("Fill in all fields!");
            e.printStackTrace();
        }
    }

    @FXML
    void resetTable(ActionEvent event) {
        fillScheduleTable();
    }

    @FXML
    void logOutAction(ActionEvent event) {
        logOut();
    }

    private void logOut() {
        logOutBtn.setDisable(true);
        addRouteBtn.setDisable(true);
        editRouteBtn.setDisable(true);
        removeRouteBtn.setDisable(true);
        costTextField.setDisable(true);
        costLabel.setDisable(true);
        setCostBtn.setDisable(true);
        LOG.trace("Admin logged out!");
    }

    void logIn() {
        logOutBtn.setDisable(false);
        addRouteBtn.setDisable(false);
        editRouteBtn.setDisable(false);
        removeRouteBtn.setDisable(false);
        costTextField.setDisable(false);
        costLabel.setDisable(false);
        setCostBtn.setDisable(false);
    }

    @FXML
    void addRouteAction(ActionEvent event) {
        try {
            FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/addRouteWindow.fxml");
            Parent root = loader.load();
            AddRouteWindowController addRouteWindowController = loader.getController();
            addRouteWindowController.setParent(this);
            ControllerUtil.openModalWindow("Add Route",root , event);
        } catch (IOException e) {
            ControllerUtil.showError("Loading resource Error!");
            e.printStackTrace();
        }

    }

    @FXML
    void removeRouteAction(ActionEvent event) {
        if(ControllerUtil.isSelectedItemInTable(routesTable)){
            Route selectedRoute = getSelectedRoute();
            removeRoute(selectedRoute);
        } else {
            ControllerUtil.showError("Choose Route to edit!");
        }

    }

    private void removeRoute(Route route) {
        schedule.getRoutes().remove(route);
        writeSchedule();
        LOG.trace("Route number " + route.getTrain().getTrainNumber() + " removed");
    }

    @FXML
    void editRouteAction(ActionEvent event) {
        if (ControllerUtil.isSelectedItemInTable(routesTable)){
            try {
                FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/addRouteWindow.fxml");
                Parent root = loader.load();
                AddRouteWindowController addRouteWindowController = loader.getController();
                addRouteWindowController.setParent(this);
                addRouteWindowController.editInit(getSelectedRoute());
                ControllerUtil.openModalWindow("Edit Route", root, event);
            } catch (IOException e) {
                ControllerUtil.showError("Loading resource Error!");
                e.printStackTrace();
            }
        } else {
            ControllerUtil.showError("Choose Route to edit!");
        }

    }

    @FXML
    void openDetails(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/routeInfoWindow.fxml");
                Parent root = loader.load();
                RouteInfoWindowController routeInfoWindowController = loader.getController();
                routeInfoWindowController.init(getSelectedRoute());
                routeInfoWindowController.setParent(this);
                ControllerUtil.openModalWindow("Route Info", root, event);
            } catch (IOException e) {
                ControllerUtil.showError("Loading resource Error!");
                e.printStackTrace();
            }

        }
    }

    @FXML
    void setCostAction(ActionEvent event) {
        if (Checker.isPositiveDoubleString(costTextField.getText())) {
            costForKm = Double.parseDouble(costTextField.getText());
            LOG.trace("Changed cost per Km");
        } else {
            ControllerUtil.showError("Cost must be positive double number!");
        }
    }

    private void fillScheduleTable() {
        numberColumn.setCellValueFactory(cellData ->
                cellData.getValue().getTrain().getTrainNumberProperty().asObject());
        departureStationColumn.setCellValueFactory(cellData ->
                cellData.getValue().getStations().get(0).getStationNameProperty());
        arriveStationColumn.setCellValueFactory(cellData -> {
            List<Station> stationList = cellData.getValue().getStations();
            return stationList.get(stationList.size() - 1).getStationNameProperty();
        });
        departureTimeColumn.setCellValueFactory(cellData ->
                cellData.getValue().getStations().get(0).getDepartureTimeProperty());
        arriveTimeColumn.setCellValueFactory(cellData -> {
            List<Station> stationList = cellData.getValue().getStations();
            return stationList.get(stationList.size() - 1).getArriveTimeProperty();
        });
        routesTable.setItems(schedule.getRoutes());
        LOG.trace("Default route table formed");
    }

    private void fillFilteredScheduleTable(List<Route> filteredRouteList, String departureStation,
                                           String arriveStation) {
        ObservableList<Route> filteredRouteObservableList = FXCollections.observableArrayList(filteredRouteList);
        numberColumn.setCellValueFactory(cellData ->
                cellData.getValue().getTrain().getTrainNumberProperty().asObject());
        departureStationColumn.setCellValueFactory(cellData ->
                cellData.getValue().getStation(departureStation).getStationNameProperty());
        arriveStationColumn.setCellValueFactory(cellData ->
                cellData.getValue().getStation(arriveStation).getStationNameProperty());
        departureTimeColumn.setCellValueFactory(cellData ->
                cellData.getValue().getStation(departureStation).getDepartureTimeProperty());
        arriveTimeColumn.setCellValueFactory(cellData ->
                cellData.getValue().getStation(arriveStation).getArriveTimeProperty());
        routesTable.setItems(filteredRouteObservableList);
        LOG.trace("Filtered route table formed");
    }

    void addRoute(Route route) {
        schedule.getRoutes().add(route);
        writeSchedule();
        LOG.trace("Route number " + route.getTrain().getTrainNumber() + " added!");
    }

    void editRoute(Route route) {
        Route selectedRoute = getSelectedRoute();
        schedule.getRoutes().set(schedule.getRoutes().indexOf(selectedRoute), route);
        writeSchedule();
        LOG.trace("Route number " + route.getTrain().getTrainNumber() + " edited!");
    }

    void writeSchedule() {
        try {
            ScheduleDataWorker.writeSchedule(schedule, "schedule.json");
            LOG.trace("Schedule wrote!");
        } catch (IOException e) {
            ControllerUtil.showError("Error writing file!");
            e.printStackTrace();
        }
    }

    ObservableList<Route> getRouteList() {
        return schedule.getRoutes();
    }

    double  getCostForKm() {
        return costForKm;
    }

    Route getSelectedRoute() {
        return routesTable.getSelectionModel().getSelectedItem();
    }
}
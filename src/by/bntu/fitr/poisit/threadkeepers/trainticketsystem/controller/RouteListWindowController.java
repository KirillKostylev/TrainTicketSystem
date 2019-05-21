package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Schedule;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.LogicCashier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.ScheduleDataWorker;

public class RouteListWindowController {

    private Schedule schedule;

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
    void loginAsAdminAction(ActionEvent event) throws IOException {
        FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/loginWindow.fxml");
        Parent root = loader.load();
        ControllerUtil.openChildModalWindow("Log In", root, event);
        LoginWindowController loginWindowController = loader.getController();
        loginWindowController.setParent(this);
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
    }

    void logIn() {
        logOutBtn.setDisable(false);
        addRouteBtn.setDisable(false);
        editRouteBtn.setDisable(false);
        removeRouteBtn.setDisable(false);
    }

    @FXML
    void addRouteAction(ActionEvent event) {
        try {
            FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/addRouteWindow.fxml");
            Parent root = loader.load();
            AddRouteWindowController addRouteWindowController = loader.getController();
            addRouteWindowController.setParent(this);
            ControllerUtil.openChildModalWindow("Add Route",root , event);
        } catch (IOException e) {
            ControllerUtil.showError("Loading resource Error!");
            e.printStackTrace();
        }

    }

    @FXML
    void removeRouteAction(ActionEvent event) {
        if(ControllerUtil.isSelectedItemInTable(routesTable)){
            Route selectedRoute = routesTable.getSelectionModel().getSelectedItem();
            removeRoute(selectedRoute);
        } else {
            ControllerUtil.showError("Choose Route to edit!");
        }

    }

    private void removeRoute(Route route) {
        schedule.getRoutes().remove(route);
        writeSchedule();;
    }

    @FXML
    void editRouteAction(ActionEvent event) {
        if (ControllerUtil.isSelectedItemInTable(routesTable)){
            try {
                FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/addRouteWindow.fxml");
                Parent root = loader.load();
                AddRouteWindowController addRouteWindowController = loader.getController();
                addRouteWindowController.setParent(this);
                addRouteWindowController.editInit(routesTable.getSelectionModel().getSelectedItem());
                ControllerUtil.openChildModalWindow("Add Route", root, event);
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
                routeInfoWindowController.init(routesTable.getSelectionModel().getSelectedItem());
                routeInfoWindowController.setParent(this);
                ControllerUtil.openChildModalWindow("Route Info", root, event);
            } catch (IOException e) {
                ControllerUtil.showError("Loading resource Error!");
                e.printStackTrace();
            }

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
    }

    void addRoute(Route route) {
        schedule.getRoutes().add(route);
        writeSchedule();
    }

    void editRoute(Route route) {
        Route selectedRoute = routesTable.getSelectionModel().getSelectedItem();
        schedule.getRoutes().set(schedule.getRoutes().indexOf(selectedRoute), route);
        writeSchedule();
    }

    void writeSchedule() {
        try {
            ScheduleDataWorker.writeSchedule(schedule, "schedule.json");
        } catch (IOException e) {
            ControllerUtil.showError("Error writing file!");
            e.printStackTrace();
        }
    }
}
package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Schedule;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Train;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class RouteListWindowController {

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
    void enterAsAdmin(ActionEvent event) throws IOException {
        Stage loginStage = new Stage();
        loginStage.setTitle("Log In");
        loginStage.initModality(Modality.APPLICATION_MODAL);
        Node source = (Node) event.getSource();
        loginStage.initOwner(source.getScene().getWindow());
        Parent loginRoot = FXMLLoader.load(getClass().getResource("../view/XMLForms/loginWindow.fxml"));
        loginStage.setScene(new Scene(loginRoot, 354, 201));
        loginStage.showAndWait();
    }

    @FXML
    void searchAction(ActionEvent event) {
        searchRoutes();
    }

    @FXML
    void initialize() {
        try {
            Schedule schedule = createSchedule();
            List<Route> routesList = schedule.getRoutes();
            ObservableList<Route> routesObservableList = FXCollections.observableArrayList(routesList);
            numberColumn.setCellValueFactory(cellData ->
                    cellData.getValue().getTrain().getTrainNumberProperty().asObject());
            departureStationColumn.setCellValueFactory(cellData ->
                    cellData.getValue().getStations().get(0).getStationNameProperty());
            arriveStationColumn.setCellValueFactory(cellData ->
                    cellData.getValue().getStations().get(cellData.getValue().
                            getStations().size() - 1).getStationNameProperty());
            departureTimeColumn.setCellValueFactory(cellData ->
                    cellData.getValue().getStations().get(0).getDepartureTimeProperty());
            arriveTimeColumn.setCellValueFactory(cellData ->
                    cellData.getValue().getStations().get(cellData.getValue().
                            getStations().size() - 1).getArriveTimeProperty());
            routesTable.setItems(routesObservableList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void searchRoutes() {

    }

    private Schedule createSchedule() throws ParseException {
        Schedule schedule = new Schedule();
        List<Station> stations = Arrays.asList(
                new Station("Brest", "12.10.2019 18:00", "12.10.2019 18:05"),
                new Station("Baranovichi", "12.10.2019 20:00", "12.10.2019 20:05"),
                new Station("Minsk", "12.10.2019 22:00", "12.10.2019 22:05"));
        Train train = new Train(151, 2, 3);
        schedule.addRoute(stations, train);

        List<Station> stations2 = Arrays.asList(
                new Station("Minsk", "17.08.2019 05:40", "17.08.2019 06:00"),
                new Station("Baranovichi", "17.08.2019 07:40", "17.08.2019 7:50"),
                new Station("Ivatsevichi", "17.08.2019 08:35", "17.08.2019 08:45"),
                new Station("Brest", "17.08.2019 09:00", "17.08.2019 09:20"));
        Train train2 = new Train(345, 10, 30);
        schedule.addRoute(stations2, train2);

        List<Station> stations3 = Arrays.asList(
                new Station("Brest", "17.08.2019 05:40", "17.08.2019 06:00"),
                new Station("Ivatsevichi", "17.08.2019 07:40", "17.08.2019 7:50"),
                new Station("Baranovichi", "17.08.2019 08:35", "17.08.2019 08:45"),
                new Station("Minsk", "17.08.2019 09:00", "17.08.2019 09:20"));
        Train train3 = new Train(124, 10, 20);
        schedule.addRoute(stations3, train3);

        List<Station> stations4 = Arrays.asList(
                new Station("Brest", "03.05.2019 18:30", "03.05.2019 18:58"),
                new Station("Zhabinka", "03.05.2019 19:16", "03.05.2019 19:17"),
                new Station("Ivatsevichi", "03.05.2019 20:23", "03.05.2019 20:24"),
                new Station("Baranovichi", "03.05.2019 20:59", "03.05.2019 21:00"),
                new Station("Minsk", "03.05.2019 22:30", "03.05.2019 22:50"));
        Train train4 = new Train(784, 10, 20);
        schedule.addRoute(stations4, train4);

        List<Station> stations5 = Arrays.asList(
                new Station("Brest", "03.05.2019 17:00", "03.05.2019 17:30"),
                new Station("Baranovichi", "03.05.2019 19:27", "03.05.2019 19:30"),
                new Station("Minsk", "03.05.2019 21:12", "03.05.2019 21:30"));
        Train train5 = new Train(464, 10, 20);
        schedule.addRoute(stations5, train5);

        List<Station> stations6 = Arrays.asList(
                new Station("Terespol", "03.05.2019 15:00", "03.05.2019 15:30"),
                new Station("Brest", "03.05.2019 18:50", "03.05.2019 18:00"),
                new Station("Ivatsevichi", "03.05.2019 20:00", "03.05.2019 20:05"),
                new Station("Baranovichi", "03.05.2019 21:00", "03.05.2019 21:05"),
                new Station("Minsk", "03.05.2019 22:00", "03.05.2019 22:30"));
        Train train6 = new Train(145, 10, 20);
        schedule.addRoute(stations6, train6);

        List<Station> stations7 = Arrays.asList(
                new Station("Helsinki", "03.05.2019 18:30", "03.05.2019 18:44"),
                new Station("Saint Petersburg", "04.05.2019 01:18", "04.05.2019 01:53"),
                new Station("Tver", "04.05.2019 06:15", "04.05.2019 06:20"),
                new Station("Moscow", "04.05.2019 09:19", "04.05.2019 10:00"));
        Train train7 = new Train(132, 2, 2);
        schedule.addRoute(stations7, train7);
        return schedule;
    }
}
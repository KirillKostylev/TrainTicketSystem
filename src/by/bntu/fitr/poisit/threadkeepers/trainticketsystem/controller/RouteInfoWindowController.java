package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class RouteInfoWindowController {

    @FXML
    private Label trainNumberLabel;

    @FXML
    private Label trainNameLabel;

    @FXML
    private Label carriagesNumber;

    @FXML
    private Label seatsNumberInCarriage;

    @FXML
    private Tooltip butTicketBtnTip;


    @FXML
    private Button buyTicketBtn;

    @FXML
    private TableView<Station> stationTableView;

    @FXML
    private TableColumn<Station, String> stationNameColumn;

    @FXML
    private TableColumn<Station, String> departureTimeColumn;

    @FXML
    private TableColumn<Station, String> arriveTimeColumn;

    @FXML
    void backAction(ActionEvent event) {
        Util.closeWindow(event);
    }

    @FXML
    void buyTicketAction(ActionEvent event) {
        //TODO Если будем делать возвращение билетов нужен будет список билетов в маршруте
        // и метод для их возвращения
        //TODO нужно чтобы метод buyTicket вносил изменения в Route
        if(!Util.isSelectedItemInTable(stationTableView)){
            Util.showError("Select Stations");
        }
    }

    void init(Route route) {
        trainNumberLabel.setText(trainNumberLabel.getText() + route.getTrain().getTrainNumber());
        ObservableList<Station> stationList = FXCollections.observableArrayList(route.getStations());
        trainNameLabel.setText(trainNameLabel.getText() + stationList.get(0).getStationName() + " - "
            + stationList.get(stationList.size() - 1).getStationName());
        carriagesNumber.setText(carriagesNumber.getText() + route.getTrain().getCarriagesNumber() + "");
        seatsNumberInCarriage.setText(seatsNumberInCarriage.getText()
                + route.getTrain().getSeatsNumberInCarriage() + "");
        Util.setFactoryForStationTable(stationNameColumn, departureTimeColumn, arriveTimeColumn);
        stationTableView.setItems(stationList);
    }

}

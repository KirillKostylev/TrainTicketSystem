package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Ticket;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.*;
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

public class RouteInfoWindowController {

    private Route route;

    @FXML
    private Label trainNumberLabel;

    @FXML
    private Label trainNameLabel;

    @FXML
    private Label carriagesNumber;

    @FXML
    private Label seatsNumberInCarriage;

    @FXML
    private TableView<Station> stationTableView;

    @FXML
    private TableColumn<Station, String> stationNameColumn;

    @FXML
    private TableColumn<Station, String> departureTimeColumn;

    @FXML
    private TableColumn<Station, String> arriveTimeColumn;

    @FXML
    private ComboBox<String> carriageNumberComboBox;

    @FXML
    private ComboBox<String> seatNumberComboBox;

    @FXML
    void backAction(ActionEvent event) {
        Util.closeWindow(event);
    }

    @FXML
    void buyTicketAction(ActionEvent event) {
        ObservableList<Station> stationObservableList = stationTableView.getItems();
        try {
            if (LogicCashier.findCarriagesNumberWithFreeSeats(route,
                    stationObservableList.get(0),
                    stationObservableList.get(stationObservableList.size() - 1)).size() == 0) {
                Util.showError("There are no vacancies in the train!");
                return;
            }
        } catch (NullException e) {
            Util.showError("Null Exception!");
            e.printStackTrace();
        }
        if(!Util.isSelectedItemsInTable(stationTableView, 2)){
            Util.showError("Select 2 Stations!");
            return;
        }
        if(!isSeatSelected()) {
            Util.showError("Select seat!");
            return;
        }
        ObservableList<Station> selectedStationList = stationTableView.getSelectionModel()
                .getSelectedItems();
        Ticket ticket = null;
        try {
            ticket = LogicCashier.buyTicket(route, Integer.parseInt(carriageNumberComboBox.getValue()),
                    Integer.parseInt(seatNumberComboBox.getValue()),
                    selectedStationList.get(0),
                    selectedStationList.get(1));
        } catch (NullException e) {
            Util.showError("Null exception!");
            e.printStackTrace();
        }
        if (ticket == null) {
            Util.showError("Ticket is not formed!");
        }
        try {
            FXMLLoader loader = Util.getFXMLLoaderFromResource("../view/XMLForms/printTicketWindow.fxml");
            Parent root = loader.load();
            PrintTicketWindowController printTicketWindowController = loader.getController();
            assert ticket != null;
            printTicketWindowController.init(ticket.toString());
            Util.openChildModalWindow("Print Ticket", root, event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void init(Route route) {
        this.route = route;
        trainNumberLabel.setText(trainNumberLabel.getText() + route.getTrain().getTrainNumber());
        ObservableList<Station> stationList = FXCollections.observableArrayList(route.getStations());
        trainNameLabel.setText(trainNameLabel.getText() + stationList.get(0).getStationName() + " - "
            + stationList.get(stationList.size() - 1).getStationName());
        carriagesNumber.setText(carriagesNumber.getText() + route.getTrain().getCarriagesNumber() + "");
        seatsNumberInCarriage.setText(seatsNumberInCarriage.getText()
                + route.getTrain().getSeatsNumberInCarriage() + "");
        Util.setFactoryForStationTable(stationNameColumn, departureTimeColumn, arriveTimeColumn);
        stationTableView.setItems(stationList);
        stationTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        carriageNumberComboBox.setItems(Util.getObservableListWithNumbers(1,
                route.getTrain().getCarriagesNumber()));
        seatNumberComboBox.setItems(Util.getObservableListWithNumbers(1,
                route.getTrain().getSeatsNumberInCarriage()));
    }

    private boolean isSeatSelected() {
        return carriageNumberComboBox.getValue() != null && seatNumberComboBox.getValue() != null;
    }

    @FXML
    void showFreeSeatsAction(MouseEvent event) {
        if (stationTableView.getSelectionModel().getSelectedItems().size() == 2) {
            //carriageNumberComboBox.setItems();
        }
    }

}

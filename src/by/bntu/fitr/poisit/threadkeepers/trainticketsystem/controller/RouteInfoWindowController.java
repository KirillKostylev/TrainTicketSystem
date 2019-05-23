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
    private RouteListWindowController routeListWindowController;

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
        ControllerUtil.closeWindow(event);
    }

    @FXML
    void buyTicketAction(ActionEvent event) {
        if (isBuySuccessful()) {
            Ticket ticket = formTicket();
            try {
                FXMLLoader loader = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/printTicketWindow.fxml");
                Parent root = loader.load();
                PrintTicketWindowController printTicketWindowController = loader.getController();
                assert ticket != null;
                printTicketWindowController.init(ticket.toString());
                ControllerUtil.openModalWindow("Print Ticket", root, event);
                disableComboBoxes();
            } catch (IOException e) {
                ControllerUtil.showError("Error loading resource!");
                e.printStackTrace();
            }
            routeListWindowController.writeSchedule();
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
        ControllerUtil.setFactoryForStationTable(stationNameColumn, departureTimeColumn, arriveTimeColumn);
        stationTableView.setItems(stationList);
        stationTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private boolean isSeatSelected() {
        return carriageNumberComboBox.getValue() != null && seatNumberComboBox.getValue() != null;
    }

    @FXML
    void showFreeCarriagesAction(MouseEvent event) {
        if (stationTableView.getSelectionModel().getSelectedItems().size() == 2) {
            carriageNumberComboBox.setDisable(false);
            try {
                carriageNumberComboBox.setItems(LogicCashier.findCarriagesNumberWithFreeSeats(
                        route, getSelectedStations().get(0), getSelectedStations().get(1)
                ));
            } catch (NullException e) {
                ControllerUtil.showError("Null Exception Error!");
                e.printStackTrace();
            }
            seatNumberComboBox.setItems(null);
        } else {
            disableComboBoxes();
        }
    }

    @FXML
    void showFreeSeatsAction(ActionEvent event) {
        if (carriageNumberComboBox.getValue() != null ) {
            seatNumberComboBox.setDisable(false);
            try {
                seatNumberComboBox.setItems(LogicCashier.findFreeSeatsInCarriage(
                        route, getSelectedStations().get(0), getSelectedStations().get(1),
                        Integer.parseInt(carriageNumberComboBox.getValue())));
            } catch (NullException e) {
                ControllerUtil.showError("Null Exception Error!");
                e.printStackTrace();
            }
        }
    }

    private boolean isBuySuccessful() {
        boolean isBuySuccessful = true;
        ObservableList<Station> stationObservableList = stationTableView.getItems();
        if(!ControllerUtil.isSelectedItemsInTable(stationTableView, 2)){
            ControllerUtil.showError("Select 2 Stations!");
            isBuySuccessful = false;
        } else if (!isSeatSelected()) {
            ControllerUtil.showError("Select seat!");
            isBuySuccessful = false;
        } else {
            try {
                if (LogicCashier.findCarriagesNumberWithFreeSeats(route,
                        stationObservableList.get(0),
                        stationObservableList.get(stationObservableList.size() - 1)).size() == 0) {
                    ControllerUtil.showError("There are no vacancies in the train!");
                    isBuySuccessful = false;
                }
            } catch (NullException e) {
                ControllerUtil.showError("Null Exception!");
                e.printStackTrace();
                isBuySuccessful = false;
            }
        }
        return isBuySuccessful;
    }

    private Ticket formTicket() {
        ObservableList<Station> selectedStationList = getSelectedStations();
        Ticket ticket = null;
        try {
            ticket = LogicCashier.buyTicket(route, Integer.parseInt(carriageNumberComboBox.getValue()),
                    Integer.parseInt(seatNumberComboBox.getValue()),
                    selectedStationList.get(0),
                    selectedStationList.get(1),
                    routeListWindowController.getCostForKm());
        } catch (NullException e) {
            ControllerUtil.showError("Null exception!");
            e.printStackTrace();
        }
        if (ticket == null) {
            ControllerUtil.showError("Ticket is not formed!");
        }
        return ticket;
    }

    private ObservableList<Station> getSelectedStations() {
        return stationTableView.getSelectionModel().getSelectedItems();
    }

    private void disableComboBoxes() {
        carriageNumberComboBox.setDisable(true);
        seatNumberComboBox.setDisable(true);
        carriageNumberComboBox.setValue(null);
        seatNumberComboBox.setValue(null);
    }

    void setParent(RouteListWindowController routeListWindowController) {
        this.routeListWindowController = routeListWindowController;
    }

}

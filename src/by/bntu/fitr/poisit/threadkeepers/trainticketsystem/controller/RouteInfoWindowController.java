package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Ticket;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.*;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.LogicCashier;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.ScheduleDataWorker;
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
        Util.closeWindow(event);
    }

    @FXML
    void buyTicketAction(ActionEvent event) {
        if (checkBuyExceptions()) {
            return;
        }
        Ticket ticket = formTicket();
        try {
            FXMLLoader loader = Util.getFXMLLoaderFromResource("../view/XMLForms/printTicketWindow.fxml");
            Parent root = loader.load();
            PrintTicketWindowController printTicketWindowController = loader.getController();
            assert ticket != null;
            printTicketWindowController.init(ticket.toString());
            Util.openChildModalWindow("Print Ticket", root, event);
            disableComboBoxes();
        } catch (IOException e) {
            Util.showError("Error loading resource!");
            e.printStackTrace();
        }
        try {
            routeListWindowController.writeSchedule();
        } catch (IOException e) {
            Util.showError("Error writing data!");
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
                Util.showError("Null Exception Error!");
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
                Util.showError("Null Exception Error!");
                e.printStackTrace();
            }
        }
    }

    private boolean checkBuyExceptions() {
        ObservableList<Station> stationObservableList = stationTableView.getItems();
        try {
            if (LogicCashier.findCarriagesNumberWithFreeSeats(route,
                    stationObservableList.get(0),
                    stationObservableList.get(stationObservableList.size() - 1)).size() == 0) {
                Util.showError("There are no vacancies in the train!");
                return true;
            }
        } catch (NullException e) {
            Util.showError("Null Exception!");
            e.printStackTrace();
            return true;
        }
        if(!Util.isSelectedItemsInTable(stationTableView, 2)){
            Util.showError("Select 2 Stations!");
            return true;
        }
        if(!isSeatSelected()) {
            Util.showError("Select seat!");
            return true;
        }
        return false;
    }

    private Ticket formTicket() {
        ObservableList<Station> selectedStationList = getSelectedStations();
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

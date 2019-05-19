package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Util {

    static void openWindow(String windowName, Parent root, Event event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle(windowName);
        stage.initModality(Modality.WINDOW_MODAL);
        Node source = (Node) event.getSource();
        stage.initOwner(source.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    static void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }

    static FXMLLoader getFXMLLoaderFromResource(String path) {
        return new FXMLLoader(Util.class.getResource(path));
    }

    static void setFactoryForStationTable(TableColumn<Station, String> stationNameColumn,
                                         TableColumn<Station, String> departureTimeColumn,
                                         TableColumn<Station, String> arriveTimeColumn) {
        stationNameColumn.setCellValueFactory(cellData ->
                cellData.getValue().getStationNameProperty());
        departureTimeColumn.setCellValueFactory(cellData ->
                cellData.getValue().getDepartureTimeProperty());
        arriveTimeColumn.setCellValueFactory(cellData ->
                cellData.getValue().getArriveTimeProperty());
    }

    static ObservableList<String> getObservableListWithNumbers(int firstNumber, int lastNumber) {
        ObservableList<String> numberList = FXCollections.observableArrayList();
        String number;
        for (int i = firstNumber; i < lastNumber + 1; i++) {
            if (i < 10) {
                number = "0" + i;
            } else {
                number = i + "";
            }
            numberList.add(number);
        }
        return numberList;
    }

    static boolean isSelectedItemInTable(TableView<?> tableView) {
        return tableView.getSelectionModel().getSelectedItem() != null;
    }

    static boolean isSelectedItemsInTable(TableView<?> tableView, int itemsNumber) {
        return tableView.getSelectionModel().getSelectedItems().size() == itemsNumber;
    }
}

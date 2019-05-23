package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Station;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

class ControllerUtil {

    private static final Logger LOG = Logger.getLogger(ControllerUtil.class);

    static void openModalWindow(String windowName, Parent root, Event event) {
        Stage stage = createStage(windowName, root);
        stage.initModality(Modality.WINDOW_MODAL);
        Node source = (Node) event.getSource();
        stage.initOwner(source.getScene().getWindow());
        LOG.trace("Opened modal window \"" + stage.getTitle() +  "\"!");
        stage.showAndWait();
    }

    static void openWindow(String windowName, Parent root) {
        Stage stage = createStage(windowName, root);
        LOG.trace("Opened window \"" + stage.getTitle() + "\"!");
        stage.show();
    }

    private static Stage createStage(String windowName, Parent root) {
        Stage stage = new Stage();
        stage.setTitle(windowName);
        stage.setScene(new Scene(root));
        LOG.trace("Stage created!");
        return stage;
    }

    static void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        LOG.trace("Window \""+ stage.getTitle() +  "\" closed!");
    }

    static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
        LOG.error(msg);
    }

    static FXMLLoader getFXMLLoaderFromResource(String path) {
        return new FXMLLoader(ControllerUtil.class.getResource(path));
    }

    static void setFactoryForStationTable(TableColumn<Station, String> stationNameColumn,
                                         TableColumn<Station, String> arriveTimeColumn,
                                         TableColumn<Station, String> departureTimeColumn) {
        stationNameColumn.setCellValueFactory(cellData ->
                cellData.getValue().getStationNameProperty());
        departureTimeColumn.setCellValueFactory(cellData ->
                cellData.getValue().getDepartureTimeProperty());
        arriveTimeColumn.setCellValueFactory(cellData ->
                cellData.getValue().getArriveTimeProperty());
        LOG.trace("Formed station table!");
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

    static String getDateTimeString(DatePicker datePicker, ComboBox hourComboBox,
                             ComboBox minuteComboBox) {
        return datePicker.getValue().format(DateTimeFormatter
                .ofPattern(Station.DATE_FORMAT))
                + " " + hourComboBox.getValue() + ":"
                + minuteComboBox.getValue();
    }

    static void setFocus(Node node) {
        Platform.runLater(node::requestFocus);
        LOG.trace("focus given to " + node.getId());
    }
}

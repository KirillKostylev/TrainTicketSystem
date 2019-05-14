package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Route;
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

public class RouteListWindowController {

    @FXML
    private TableView<Route> routesTable;

    @FXML
    private TableColumn<Route, Integer> numberColumn;

    @FXML
    private TableColumn<Route, String> routeColumn;

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

    }

    private void searchRoutes() {

    }
}
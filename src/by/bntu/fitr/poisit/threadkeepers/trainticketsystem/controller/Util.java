package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Util {

    static void openWindow(String windowName, Parent root, ActionEvent event) throws IOException {
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
}

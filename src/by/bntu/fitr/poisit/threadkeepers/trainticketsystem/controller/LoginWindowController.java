package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    void cancelAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginAction(ActionEvent event) {

    }

}
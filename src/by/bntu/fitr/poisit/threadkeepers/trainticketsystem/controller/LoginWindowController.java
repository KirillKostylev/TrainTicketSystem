package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindowController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    void cancelAction(ActionEvent event) {
        Util.closeWindow(event);
    }

    @FXML
    void loginAction(ActionEvent event) {
        //TODO Сделать базу данных ников и паролей и метод для их проверки
    }

}
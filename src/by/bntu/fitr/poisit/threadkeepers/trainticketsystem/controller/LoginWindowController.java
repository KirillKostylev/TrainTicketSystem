package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic.AdminLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

public class LoginWindowController {

    private RouteListWindowController routeListWindowController;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    void cancelAction(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }

    @FXML
    void loginAction(ActionEvent event) {
        try {
            if (AdminLogic.checkLoginAndPassword(loginField.getText(), passwordField.getText())) {
                routeListWindowController.logIn();
                ControllerUtil.closeWindow(event);
            } else {
                ControllerUtil.showError("Wrong login or password!");
            }
        } catch (FileNotFoundException e) {
            ControllerUtil.showError("Error loading admins file!");
        } catch (NullException e) {
            ControllerUtil.showError("Null Exception!");
        }
    }

    void setParent(RouteListWindowController routeListWindowController) {
        this.routeListWindowController = routeListWindowController;
    }

}
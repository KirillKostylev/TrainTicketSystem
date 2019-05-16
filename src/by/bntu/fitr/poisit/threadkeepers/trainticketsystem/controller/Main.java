package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/XMLForms/routeListWindow.fxml"));
            primaryStage.setTitle("Routes");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            Util.showError("Loading resource Error!");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

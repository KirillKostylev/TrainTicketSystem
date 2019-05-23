package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = ControllerUtil.getFXMLLoaderFromResource("../view/XMLForms/routeListWindow.fxml")
                    .load();
            ControllerUtil.openWindow("Route List", root);
        } catch (IOException e) {
            ControllerUtil.showError("Loading resource Error!");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = Util.getFXMLLoaderFromResource("../view/XMLForms/routeListWindow.fxml")
                    .load();
            Util.openWindow("Route List", root);
        } catch (IOException e) {
            Util.showError("Loading resource Error!");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

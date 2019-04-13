package solar_system;

import javafx.application.Application;
import javafx.stage.Stage;

import solar_system.controller.Controller;

public class SolarSystem extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Controller.planetInitialize(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

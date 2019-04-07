package solar_system;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;


import solar_system.model.PositionEvaluator;
import solar_system.model.SkyBody;
import solar_system.model.Position;
import solar_system.controller.Controller;

public class Solar_system extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        
        VBox left = new VBox();
        VBox right = new VBox();

        grid.add(left, 0, 0);
        grid.add(right, 1, 0);
        

        // CANVAS
        // ==============================================
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setFill(Color.GREY.brighter());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        // first - left upper corner of the oval:  - (diameter / 2)
        gc.strokeOval(canvas.getWidth() / 2 - 40, canvas.getHeight() / 2 - 40, 80, 80);
        // ===============================================
      
        // LEFT VBOX
        left.getChildren().add(canvas);
        
        // RIGHT VBOX
        Button btn = new Button("Test");
        right.getChildren().add(btn);
        
        
        Scene scene = new Scene(grid, 1100, 600);
        
        primaryStage.setTitle("Solar system");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

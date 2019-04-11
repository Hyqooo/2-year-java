package solar_system.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import solar_system.model.Position;
import solar_system.model.SkyBody;

public class View {

    private Canvas canvas;
    private GraphicsContext gc;

    public View(Stage primaryStage) {
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.FLORALWHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        setStaticSceneElements(primaryStage);
    }

    private void setStaticSceneElements(Stage primaryStage) {
        // CONTROL
        Label header = new Label("Solar system control");
        header.setFont(new Font(20));

        Button btn = new Button("Test");
        Button btn2 = new Button("Test2");

        // control
        VBox right = new VBox(10);
        right.setAlignment(Pos.TOP_RIGHT);
        right.setPadding(new Insets(5));
        right.getChildren().addAll(header, btn, btn2);

        HBox holder = new HBox(5);
        holder.setPadding(new Insets(5));
        holder.getChildren().addAll(canvas, right);

        HBox pane = new HBox();
        pane.setPadding(new Insets(2));
        pane.setStyle("-fx-background-color: white");
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.getChildren().addAll(holder);

        Scene scene = new Scene(pane, 1100, 600);

        primaryStage.setTitle("Solar system");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void redrawOrbit(SkyBody body, double scale) {
        double radius = body.getOrbitRadius() * 400 * scale;
        System.out.println(radius);
        gc.strokeOval(
                (canvas.getWidth() / 2) - (radius / 2),
                (canvas.getHeight() / 2) - (radius / 2),
                radius, radius);
    }

    public void redrawPlanet(SkyBody body, double scale, Position pos) {
        double diameter = 2 * body.getRadius() * scale;
        System.out.println(body.getName() + ":" + pos);
        
        gc.strokeOval(pos.x() + canvas.getWidth() / 2 - diameter / 2, 
                      pos.y() + canvas.getHeight() / 2 - diameter / 2, 
                      diameter, diameter);
    }
    
    public void redrawName(SkyBody body, Position pos, double scale){
        double diameter = 2 * body.getRadius() * scale;
//        System.out.println(body.getName() + ": " + pos);
        gc.strokeText(body.getName(), pos.x(), pos.y());
    }
}

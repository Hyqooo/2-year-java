package solar_system.view;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import solar_system.controller.Controller;
import solar_system.model.Position;
import solar_system.model.SkyBody;

public class View {

    private final Canvas canvas;
    private final GraphicsContext gc;

    public View(Stage primaryStage) {
        canvas = new Canvas(900, 700);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.FLORALWHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        setStaticSceneElements(primaryStage);
    }

    private void setStaticSceneElements(Stage primaryStage) {
        // CONTROL
        Label header = new Label("Solar system control");
        header.setFont(new Font(20));

        // checkboxes
        CheckBox showOrbits = new CheckBox("Orbits");
        showOrbits.setSelected(true);
        CheckBox showPlanets = new CheckBox("Planets");
        showPlanets.setSelected(true);
        CheckBox showSatellites = new CheckBox("Satellites");
        showSatellites.setSelected(true);
        CheckBox showNames = new CheckBox("Names");
        showNames.setSelected(true);
        Button niceSpeed = new Button("Nice speed");

        showOrbits.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            Controller.changeShowOrbits(new_val);
        });

        showPlanets.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            Controller.changeShowPlanets(new_val);
        });

        showSatellites.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            Controller.changeShowSatellites(new_val);
            Controller.changeShowSatellitesNames(new_val);
        });

        showNames.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            Controller.changeShowNames(new_val);
        });

        // time scale
        Slider timeSlider = setSlider();
        timeSlider.setValue(0.0001);
        timeSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            Controller.changeTimeScale(newValue);
        });
        
        niceSpeed.setOnAction((ActionEvent e) -> {
            Controller.changeTimeScale(0.05);
            timeSlider.setValue(0.05);
        });
        
        // size scale 
        Slider sizeSlider = setSlider();
        sizeSlider.valueProperty().addListener((ObservableValue<? extends Number> ovservable, Number oldValue, Number newValue) -> {
            Controller.changeSizeScale(newValue);
        });

        // control
        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.TOP_LEFT);
        
        // checkboxes
        grid.add(header, 0, 0);
        grid.add(showOrbits, 0, 1);
        grid.add(showPlanets, 0, 2);
        grid.add(showSatellites, 0, 3);
        grid.add(showNames, 0, 4);
        
        // scales
        Label scaleHeader = new Label("Scale control");
        scaleHeader.setFont(new Font(18));
        grid.add(scaleHeader, 0, 6);
        
        grid.add(timeSlider, 0, 7);
        Label timeScale = new Label("Time scale");
        timeScale.setFont(new Font(15));
        grid.add(timeScale, 1, 7);
        
        grid.add(sizeSlider, 0, 8);
        Label sizeScale = new Label("Size scale");
        sizeScale.setFont(new Font(15));
        grid.add(sizeScale, 1, 8);
        grid.add(niceSpeed, 0, 9);
        
        right.setAlignment(Pos.TOP_RIGHT);
        right.getChildren().add(grid);

        HBox holder = new HBox(5);
        holder.setPadding(new Insets(5));
        holder.getChildren().addAll(canvas, right);

        HBox pane = new HBox();
        pane.setPadding(new Insets(2));
        pane.setStyle("-fx-background-color: white");
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.getChildren().addAll(holder);

        Scene scene = new Scene(pane, 1200, 700);

        primaryStage.setTitle("Solar system");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider setSlider() {
        Slider slider = new Slider(0, 5, 1);
        slider.setMajorTickUnit(0.05);
        slider.setMinorTickCount(1);
        slider.setBlockIncrement(0.025);
        return slider;
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, 900, 700);
        gc.setFill(Color.PALETURQUOISE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void redrawOrbit(SkyBody body, double scale, boolean show) {
        if (!show) {
            return;
        }

        gc.setStroke(Color.CORNFLOWERBLUE);
       
        double radius = body.getOrbitRadius() * 400 * scale;
        gc.strokeOval(
                // @cleanup there's problem with (radius / 2) it is not how it should work
                // i don't get it how to fix this but it works
                body.getAnchorPoint().x() + (canvas.getWidth() / 2) - (radius / 2),
                body.getAnchorPoint().y() + (canvas.getHeight() / 2) - (radius / 2),
                radius, radius);
    }

    public void redrawPlanet(SkyBody body, double scale, Position pos, boolean show) {
        if (!show) {
            return;
        }
        
        gc.setFill(body.getColor());

        double radius = body.getRadius() * scale;
        double redraw_position_x = pos.x() + canvas.getWidth() / 2 - radius;
        double redraw_position_y = pos.y() + canvas.getHeight() / 2 - radius;

        gc.fillOval(redraw_position_x, redraw_position_y, radius * 2, radius * 2);
    }

    public void redrawName(SkyBody body, Position pos, double scale, boolean show) {
        if (!show) {
            return;
        }
        
        gc.setStroke(Color.BLACK);

        double radius = body.getRadius() * scale;
        double redraw_position_x = pos.x() + canvas.getWidth() / 2 - radius;
        double redraw_position_y = pos.y() + canvas.getHeight() / 2 - radius;

        gc.strokeText(body.getName(), redraw_position_x + radius * 2, redraw_position_y + radius * 2);
    }
}

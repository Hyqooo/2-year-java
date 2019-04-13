package solar_system.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import solar_system.controller.Controller;
import solar_system.model.Position;
import solar_system.model.SkyBody;

public class View {

    private Canvas canvas;
    private GraphicsContext gc;

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
       

        showOrbits.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                Controller.changeShowOrbits(new_val);
            }
        });

        showPlanets.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                Controller.changeShowPlanets(new_val);
            }
        });

        showSatellites.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                Controller.changeShowSatellites(new_val);
            }
        });

        showNames.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                Controller.changeShowNames(new_val);
            }
        });

        // time scale
        Slider timeSlider = setSlider();
        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Controller.changeTimeScale(newValue);
            }
        });

        // control
        VBox right = new VBox(10);
        right.setAlignment(Pos.TOP_RIGHT);
        right.setPadding(new Insets(5));
        right.getChildren().addAll(header, showOrbits, showPlanets, showSatellites,
                showNames, timeSlider, niceSpeed);

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
        Slider slider = new Slider(0, 2, 1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(0.1);
        slider.setMinorTickCount(1);
        slider.setBlockIncrement(0.1);
        return slider;
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, 900, 700);
    }

    public void redrawOrbit(SkyBody body, double scale, boolean show) {
        if (!show) {
            return;
        }

        double radius = body.getOrbitRadius() * 400 * scale;
        System.out.println(radius);
        gc.strokeOval(
                // @cleanup there's problem with (radius / 2) it is not how it should work
                body.getAnchorPoint().x() + (canvas.getWidth() / 2) - (radius / 2),
                body.getAnchorPoint().y() + (canvas.getHeight() / 2) - (radius / 2),
                radius, radius);
    }

    public void redrawPlanet(SkyBody body, double scale, Position pos, boolean show) {
        if (!show) {
            return;
        }

        double radius = body.getRadius() * scale;
        System.out.println("Planet redraw - " + body.getName() + ":" + pos);
        double redraw_position_x = pos.x() + canvas.getWidth() / 2 - radius;
        double redraw_position_y = pos.y() + canvas.getHeight() / 2 - radius;

        System.out.println("Actual planet coords: " + "\nx: " + redraw_position_x + "\ny: " + redraw_position_y);

        gc.strokeOval(redraw_position_x, redraw_position_y, radius * 2, radius * 2);
    }

    public void redrawName(SkyBody body, Position pos, double scale, boolean show) {
        if (!show) {
            return;
        }

        double radius = body.getRadius() * scale;
        System.out.println("name redraw - " + body.getName() + ": " + pos);
        double redraw_position_x = pos.x() + canvas.getWidth() / 2 - radius;
        double redraw_position_y = pos.y() + canvas.getHeight() / 2 - radius;

        gc.strokeText(body.getName(), redraw_position_x + radius * 2, redraw_position_y + radius * 2);
    }
}

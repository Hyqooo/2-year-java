package solar_system.controller;

import solar_system.model.SkyBody;
import solar_system.model.Position;
import solar_system.model.PositionEvaluator;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import solar_system.view.View;

public class Controller {
    private static ArrayList<SkyBody> bodies;

    // earth radius / earth orbit radius
    private static final double EARTH_RADIUS_COEFF = 2;

    static View view;

    public static void planetInitialize(Stage primaryStage) {
        Position zeroPosition = new Position(0, 0);
        
        SkyBody sun = new SkyBody(new Position(0, 0), 0, EARTH_RADIUS_COEFF * 15, 0, null, "Sun");
        SkyBody mercury = new SkyBody(zeroPosition, 0.3871, EARTH_RADIUS_COEFF * 0.5, 88, null, "Mercury");
        SkyBody venus = new SkyBody(zeroPosition, 0.7233, EARTH_RADIUS_COEFF * 0.7, 225, null, "Venus");

        // Earth
        SkyBody moon = new SkyBody(new Position(0, 0), 0.1, EARTH_RADIUS_COEFF * 0.5, 27, null, "Moon");
        ArrayList<SkyBody> earth_satellites = new ArrayList<>();
        earth_satellites.add(moon);
        SkyBody earth = new SkyBody(zeroPosition, 1, EARTH_RADIUS_COEFF, 365, earth_satellites, "Earth");
        
        // Mars
        SkyBody phobos = new SkyBody(new Position(0, 0), 0.1, EARTH_RADIUS_COEFF * 0.1, 0.0318, null, "Phobos");
        SkyBody deimos = new SkyBody(new Position(0, 0), 0.07, EARTH_RADIUS_COEFF * 0.1, -1.263, null, "Deimos");
        ArrayList<SkyBody> mars_satellites = new ArrayList<>();
        mars_satellites.add(phobos);
        mars_satellites.add(deimos);
        SkyBody mars = new SkyBody(zeroPosition, 1.5237, EARTH_RADIUS_COEFF * 0.7, 687, mars_satellites, "Mars");
        
        bodies = new ArrayList<>();
        bodies.add(sun);
        bodies.add(mercury);
        bodies.add(venus);
        bodies.add(earth);
        bodies.add(mars);
        
        // Set scene
        view = new View(primaryStage);

        Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        // canvas update
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                display();
            }
        };
        
        timer.start();
        timeline.play();
    }
    
    public static void display(){
        view.clearCanvas();
        for (SkyBody body : bodies){
            view.redrawOrbit(body, 1);
            view.redrawPlanet(body, 1, PositionEvaluator.evaluatePosition(body, 1, System.currentTimeMillis() / 80));
            view.redrawName(body, PositionEvaluator.evaluatePosition(body, 1, System.currentTimeMillis() / 80), 1);
            if (body.getSatellites() != null){
                for (SkyBody sat : body.getSatellites()){
                    Position bodyPos = PositionEvaluator.evaluatePosition(body, 1, System.currentTimeMillis() / 80);
                    sat.setAnchorPoint(bodyPos.x(), bodyPos.y());
                    view.redrawOrbit(sat, 1);
                    view.redrawPlanet(sat, 1, PositionEvaluator.evaluatePosition(sat, 1, System.currentTimeMillis() / 80));
                    view.redrawName(sat, PositionEvaluator.evaluatePosition(sat, 1, System.currentTimeMillis() / 80), 1);
                }
            }
        }
    }
}

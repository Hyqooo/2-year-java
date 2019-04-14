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
    private static double SCALE_OF_THE_ORBIT = 1;
    private static double TIME_SCALE = 0.0001;

    private static boolean showOrbits = true;
    private static boolean showPlanets = true;
    private static boolean showSatellites = true;
    private static boolean showNames = true;
    private static boolean showSatellitesNames = true;

    static View view;

    public static void planetInitialize(Stage primaryStage) {
        Position zeroPosition = new Position(0, 0);

        SkyBody sun = new SkyBody(new Position(0, 0), 0, EARTH_RADIUS_COEFF * 15, 0, null, "Sun");
        SkyBody mercury = new SkyBody(zeroPosition, SCALE_OF_THE_ORBIT * 0.3871, EARTH_RADIUS_COEFF * 0.5, 88, null, "Mercury");
        SkyBody venus = new SkyBody(zeroPosition, SCALE_OF_THE_ORBIT * 0.7233, EARTH_RADIUS_COEFF * 0.7, 225, null, "Venus");

        // Earth
        SkyBody moon = new SkyBody(new Position(0, 0), SCALE_OF_THE_ORBIT * 0.07, EARTH_RADIUS_COEFF * 0.5, 27, null, "Moon");
        ArrayList<SkyBody> earth_satellites = new ArrayList<>();
        earth_satellites.add(moon);
        SkyBody earth = new SkyBody(zeroPosition, SCALE_OF_THE_ORBIT, EARTH_RADIUS_COEFF, 365, earth_satellites, "Earth");

        // Mars
        SkyBody phobos = new SkyBody(new Position(0, 0), SCALE_OF_THE_ORBIT * 0.04, EARTH_RADIUS_COEFF * 0.1, 0.318, null, "Phobos");
        SkyBody deimos = new SkyBody(new Position(0, 0), SCALE_OF_THE_ORBIT * 0.07, EARTH_RADIUS_COEFF * 0.1, 1.263, null, "Deimos");
        ArrayList<SkyBody> mars_satellites = new ArrayList<>();
        mars_satellites.add(phobos);
        mars_satellites.add(deimos);
        SkyBody mars = new SkyBody(zeroPosition, SCALE_OF_THE_ORBIT * 1.5237, EARTH_RADIUS_COEFF * 0.7, 687, mars_satellites, "Mars");

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

    public static void display() {
        long time = System.currentTimeMillis();

        view.clearCanvas();
        PositionEvaluator.setScale(SCALE_OF_THE_ORBIT);
        for (SkyBody body : bodies) {
            long offset = System.currentTimeMillis();
            view.redrawOrbit(body, SCALE_OF_THE_ORBIT, showOrbits);
            // WARNING: SCALE_OF_THE_ORBIT applied to the size of the planets
            view.redrawPlanet(body, SCALE_OF_THE_ORBIT, PositionEvaluator.evaluatePosition(body, TIME_SCALE, 2 * offset - time), showPlanets);
            view.redrawName(body, PositionEvaluator.evaluatePosition(body, TIME_SCALE, 2 * offset - time), SCALE_OF_THE_ORBIT, showNames);
            if (body.getSatellites() != null) {
                for (SkyBody sat : body.getSatellites()) {
                    Position bodyPos = PositionEvaluator.evaluatePosition(body, TIME_SCALE, 2 * offset - time);
                    sat.setAnchorPoint(bodyPos.x(), bodyPos.y());
                    view.redrawOrbit(sat, SCALE_OF_THE_ORBIT, showOrbits);
                    // WARNING: SCALE_OF_THE_ORBIT applied to the size of the planets
                    view.redrawPlanet(sat, SCALE_OF_THE_ORBIT, PositionEvaluator.evaluatePosition(sat, TIME_SCALE, 2 * offset - time), showSatellites);
                    view.redrawName(sat, PositionEvaluator.evaluatePosition(sat, TIME_SCALE, 2 * offset - time), SCALE_OF_THE_ORBIT, showSatellitesNames);
                }
            }
        }
    }

    public static void changeShowOrbits(boolean value) {
        showOrbits = value;
    }

    public static void changeShowPlanets(boolean value) {
        showPlanets = value;
    }

    public static void changeShowSatellites(boolean value) {
        showSatellites = value;
    }

    public static void changeShowNames(boolean value) {
        showNames = value;
        showSatellitesNames = value;
    }
    
    public static void changeShowSatellitesNames(boolean value){
        showSatellitesNames = value;
    }
    
    public static void changeTimeScale(Number value){
        TIME_SCALE = value.doubleValue() * 0.002;
    }
    
    public static void changeSizeScale(Number value){
        SCALE_OF_THE_ORBIT = value.doubleValue();
    }
}

package solar_system.controller;

// @cleanup
import java.time.Clock;
import java.time.Duration;
import solar_system.model.SkyBody;
import solar_system.model.Position;
import solar_system.model.PositionEvaluator;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.stage.Stage;
import solar_system.view.View;

public class Controller {

    private static SkyBody sun;
    private static SkyBody mercury;
    private static SkyBody venus;
    private static SkyBody earth;
    private static SkyBody moon;
    private static SkyBody mars;
    private static SkyBody phobos;
    private static SkyBody deimos;
    private static SkyBody jupiter;
    private static SkyBody saturn;
    private static SkyBody uranus;
    private static SkyBody neptune;

    // @cleanup
    // This means that earth do one complete spin around sun for one natural minute
    private static final double EARTH_ANGULAR_VELOCITY = Math.PI / 30;
    // earth radius / earth orbit radius
    private static final double EARTH_RADIUS_COEFF = 2;

    static View view;

    // @cleanup : I just need to add one arrayList to easy add and display planets
    public static void planetInitialize(Stage primaryStage) {
        // we apply only coefficients relative to the earth characteristics
        // @cleanup I can add only one position and do not create each a new one
        sun = new SkyBody(new Position(0, 0), 0, EARTH_RADIUS_COEFF * 15, 0, null, "Sun");
        mercury = new SkyBody(new Position(0, 0), 0.3871, EARTH_RADIUS_COEFF * 0.5, 88, null, "Mercury");
        venus = new SkyBody(new Position(0, 0), 0.7233, EARTH_RADIUS_COEFF * 0.7, 225, null, "Venus");
        // @cleanup how do I can easily add all satellites to the planes 
        moon = new SkyBody(new Position(0, 0), 0.1, EARTH_RADIUS_COEFF * 0.5, 27, null, "Moon");
        ArrayList<SkyBody> earth_satellites = new ArrayList<>();
        earth_satellites.add(moon);
        earth = new SkyBody(new Position(0, 0), 1, EARTH_RADIUS_COEFF, 365, earth_satellites, "Earth");
        
        
        mars = new SkyBody(new Position(0, 0), 1.5237, EARTH_RADIUS_COEFF * 0.7, 687, null, "Mars");
        phobos = new SkyBody(new Position(0, 0), 0.1, EARTH_RADIUS_COEFF * 0.1, 0.0318, null, "Phobos");
        deimos = new SkyBody(new Position(0, 0), 0.07, EARTH_RADIUS_COEFF * 0.1, -1.263, null, "Deimos");

        // Set scene
        view = new View(primaryStage);

        Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        // ===================
        // Speed of the animation easy use for the speed limiting
//         timeline.setRate(2);
        // ===================
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                display();
            }
        };
        
        timer.start();
        timeline.play();
//        display();
//        run();
    }

    // @cleanup this method is depricated
    public static void run() {
        boolean running = true;

        final int TICKS_PER_SECOND = 30;
        final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
        final int MAX_FRAMESKIP = 10;
        int loops;

        long next_tick = System.currentTimeMillis();

        int fps = 0;

        while (running) {
            loops = 0;
            while (System.currentTimeMillis() > next_tick && loops < MAX_FRAMESKIP) {
                updatePlanets();

                next_tick += SKIP_TICKS;
                loops++;
            }

            display();
        }
    }

    // I don't remember how come I need this method
    public static void updatePlanets() {

    }

    public static void display() {
        view.clearCanvas();
        // ORBITS
        view.redrawOrbit(mercury, 1);
        view.redrawOrbit(venus, 1);
        
        Position earthPos = PositionEvaluator.evaluatePosition(earth, 1, System.currentTimeMillis() / 80);
        moon.setAnchorPoint(earthPos.x(), earthPos.y());
        view.redrawOrbit(moon, 1);
        
        view.redrawOrbit(earth, 1);
        view.redrawOrbit(mars, 1);
        
        Position marsPos = PositionEvaluator.evaluatePosition(mars, 1, System.currentTimeMillis() / 80);
        phobos.setAnchorPoint(marsPos.x(), marsPos.y());
        deimos.setAnchorPoint(marsPos.x(), marsPos.y());
//        view.redrawOrbit(phobos, 1);
        view.redrawOrbit(deimos, 1);

        // PLANETS
        // Getting everytime current milliseconds first of all could add overhead
        // second it is absolutely horrible way to control speed
        System.out.println("what " + PositionEvaluator.evaluatePosition(mercury, 1, System.currentTimeMillis() / 80));
        view.redrawPlanet(sun, 1, PositionEvaluator.evaluatePosition(sun, 1, System.currentTimeMillis() / 80));
        view.redrawPlanet(mercury, 1, PositionEvaluator.evaluatePosition(mercury, 1, System.currentTimeMillis() / 80));
        view.redrawPlanet(venus, 1, PositionEvaluator.evaluatePosition(venus, 1, System.currentTimeMillis() / 80));
        view.redrawPlanet(moon, 1, PositionEvaluator.evaluatePosition(moon, 1, System.currentTimeMillis() / 80));
        view.redrawPlanet(earth, 1, PositionEvaluator.evaluatePosition(earth, 1, System.currentTimeMillis() / 80));
        view.redrawPlanet(mars, 1, PositionEvaluator.evaluatePosition(mars, 1, System.currentTimeMillis() / 80));
//        view.redrawPlanet(phobos, 1, PositionEvaluator.evaluatePosition(phobos, 1, System.currentTimeMillis() / 80));
        view.redrawPlanet(deimos, 1, PositionEvaluator.evaluatePosition(deimos, 1, System.currentTimeMillis() / 80));

        // TEXT
        view.redrawName(sun, PositionEvaluator.evaluatePosition(sun, 1, System.currentTimeMillis() / 80), 1);
        view.redrawName(mercury, PositionEvaluator.evaluatePosition(mercury, 1, System.currentTimeMillis() / 80), 1);
        view.redrawName(venus, PositionEvaluator.evaluatePosition(venus, 1, System.currentTimeMillis() / 80), 1);
        view.redrawName(moon, PositionEvaluator.evaluatePosition(moon, 1, System.currentTimeMillis() / 80), 1);
        view.redrawName(earth, PositionEvaluator.evaluatePosition(earth, 1, System.currentTimeMillis() / 80), 1);
        view.redrawName(mars, PositionEvaluator.evaluatePosition(mars, 1, System.currentTimeMillis() / 80), 1);
//        view.redrawName(phobos, PositionEvaluator.evaluatePosition(phobos, 1, System.currentTimeMillis() / 80), 1);
        view.redrawName(deimos, PositionEvaluator.evaluatePosition(deimos, 1, System.currentTimeMillis() / 80), 1);
    }
}

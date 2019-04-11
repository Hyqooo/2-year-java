package solar_system.controller;

import java.time.Clock;
import java.time.Duration;
import solar_system.model.SkyBody;
import solar_system.model.Position;
import solar_system.model.PositionEvaluator;

import java.util.ArrayList;
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
    private static SkyBody demios;
    private static SkyBody jupiter;
    private static SkyBody saturn;
    private static SkyBody uranus;
    private static SkyBody neptune;

    // This means that earth do one complete spin around sun for one natural minute
    private static final double EARTH_ANGULAR_VELOCITY = Math.PI / 30;
    // earth radius / earth orbit radius
    private static final double EARTH_RADIUS_COEFF = 2;

    
    static View view;
    
    public static void planetInitialize(Stage primaryStage) {
        // we apply only coefficients relative to the earth characteristics
        sun = new SkyBody(new Position(0, 0), 0, EARTH_RADIUS_COEFF * 109, 0, null, "Sun");
        earth = new SkyBody(new Position(0, 0), 1, EARTH_RADIUS_COEFF, 365, null, "Earth");
        
        // Set scene
        view = new View(primaryStage);
        display();
    }

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

    public static void updatePlanets() {

    }

    public static void display() {
        // ORBITS
        // Earth
        view.redrawOrbit(earth, 1);
    
       // PLANETS
       System.out.println("what " + PositionEvaluator.evaluatePosition(sun, 1));
       view.redrawPlanet(sun, 1, PositionEvaluator.evaluatePosition(sun, 1));
       view.redrawPlanet(earth, 1, PositionEvaluator.evaluatePosition(earth, 1));
       
       // TEXT
       view.redrawName(sun, PositionEvaluator.evaluatePosition(sun, 1), 1);
       view.redrawName(earth, PositionEvaluator.evaluatePosition(earth, 1), 1);
    }
}

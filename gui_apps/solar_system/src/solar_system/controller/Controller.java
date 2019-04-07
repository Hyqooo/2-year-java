package solar_system.controller;

import java.time.Clock;
import java.time.Duration;
import solar_system.model.SkyBody;
import solar_system.model.Position;
import solar_system.model.PositionEvaluator;

import java.util.ArrayList;



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
    
    
    public static void planetInitialize(){
        sun = new SkyBody(new Position(0, 0), 0, 0, 0, null, "Sun");
        mercury = new SkyBody(new Position(0, 0), 100, 0.000004, Math.PI / 30, null, "Mercury");
        venus = new SkyBody(new Position(0, 0), 100, 0.000004, Math.PI / 30, null, "Venus");
        moon = new SkyBody(new Position(0, 0), 100, 0.000004, Math.PI / 30, null, "Moon");
        ArrayList<SkyBody> earth_satellites = new ArrayList<>();
        earth_satellites.add(moon);
        earth = new SkyBody(new Position(0, 0), 100, 0.000004, Math.PI / 30, earth_satellites, "Earth");
        mars = new SkyBody(new Position(0, 0), 100, 0.000004, Math.PI / 30, null, "Mars");
    }
    
    public static void run(){
        boolean running = true;
        
        final int TICKS_PER_SECOND = 30;
        final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
        final int MAX_FRAMESKIP = 10;
        int loops;
        
        long next_tick = System.currentTimeMillis();
        
        int fps = 0;
        
        while(running){
            loops = 0;
            while(System.currentTimeMillis() > next_tick && loops < MAX_FRAMESKIP){
                updatePlanets();
                
                next_tick += SKIP_TICKS;
                loops++;
            }
            
            display();
        }
    }
    
    public static void updatePlanets(){
        
    }
    
    public static void display(){
        
    }
}

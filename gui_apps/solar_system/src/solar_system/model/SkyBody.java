package solar_system.model;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class SkyBody {
    private final Position anchorPoint;
    private final double orbitRadius;
    private final double bodyRadius;
    private final double period;
    private final ArrayList<SkyBody> satellites;
    private final String name;
    private final Color color;

    public SkyBody(Position anchorPoint, double orbitRadius, double bodyRadius, double period,
                   ArrayList<SkyBody> satellites, String name, Color color) {
        this.anchorPoint = anchorPoint;
        this.orbitRadius = orbitRadius;
        this.bodyRadius = bodyRadius;
        this.period = period;
        this.satellites = satellites;
        this.name = name;
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }

    public double getPeriod() {
        return period;
    }

    public Position getAnchorPoint() {
        return anchorPoint;
    }

    public double getOrbitRadius() {
        return orbitRadius;
    }
    
    public double getRadius(){
        return bodyRadius;
    }

    public String getName() {
        return name;
    }
    
    public void setAnchorPoint(double x, double y){
        anchorPoint.setX(x);
        anchorPoint.setY(y);
    }

    public ArrayList<SkyBody> getSatellites() {
        return satellites;
    }
}

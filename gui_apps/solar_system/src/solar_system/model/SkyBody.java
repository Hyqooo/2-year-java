package solar_system.model;

import java.util.ArrayList;

public class SkyBody {
    private Position anchorPoint;
    private double orbitRadius;
    private double bodyRadius;
    private double period;
    private ArrayList<SkyBody> satellites;
    private String name;

    public SkyBody(Position anchorPoint, double orbitRadius, double bodyRadius, double period,
                   ArrayList<SkyBody> satellites, String name) {
        this.anchorPoint = anchorPoint;
        this.orbitRadius = orbitRadius;
        this.bodyRadius = bodyRadius;
        this.period = period;
        this.satellites = satellites;
        this.name = name;
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
}

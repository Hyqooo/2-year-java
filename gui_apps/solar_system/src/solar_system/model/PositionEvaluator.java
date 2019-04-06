package solar_system.model;


public class PositionEvaluator {
    
    private static double earth_orbit_scale = 1;
    
    public static Position evaluatePosition(SkyBody body, double time_scale){
        double x, y;
        // All calculations done relative to the Earth's angular velocity
        double angular_velocity = (2 * Math.PI * 365) / (60 * body.getPeriod());
        
        x = body.getAnchorPoint().x() + time_scale * body.getOrbitRadius() * Math.cos(angular_velocity);
        y = body.getAnchorPoint().y() + time_scale * body.getOrbitRadius() * Math.sin(angular_velocity);
        
        return new Position(x, y);
    }
    
    public static void setScale(double scale){
        earth_orbit_scale = scale;
    }
}

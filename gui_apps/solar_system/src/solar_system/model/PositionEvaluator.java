package solar_system.model;

public class PositionEvaluator {

    private static double earth_orbit_scale = 1;

    public static Position evaluatePosition(SkyBody body, double time_scale, long time) {
        double x = 0;
        double y = 0;
        double angular_velocity;
        // All calculations done relative to the Earth's angular velocity
        if (body.getPeriod() != 0) {
            angular_velocity = (2 * Math.PI * 365) / (60 * body.getPeriod());
            
            x = body.getAnchorPoint().x() + body.getOrbitRadius() * earth_orbit_scale * 200 * Math.cos(angular_velocity * time * time_scale);
            y = body.getAnchorPoint().y() + body.getOrbitRadius() * earth_orbit_scale * 200 * Math.sin(angular_velocity * time * time_scale);
        }
        return new Position(x, y);
    }

    public static void setScale(double scale) {
        earth_orbit_scale = scale;
    }
}

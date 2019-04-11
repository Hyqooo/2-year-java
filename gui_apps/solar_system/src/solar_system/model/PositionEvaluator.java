package solar_system.model;

public class PositionEvaluator {

    private static double earth_orbit_scale = 1;

    public static Position evaluatePosition(SkyBody body, double time_scale) {
        double x = 0;
        double y = 0;
        double angular_velocity;
        // All calculations done relative to the Earth's angular velocity
        if (body.getPeriod() != 0) {
            angular_velocity = (2 * Math.PI * 365) / (60 * body.getPeriod());

            x = body.getAnchorPoint().x() + body.getOrbitRadius() * Math.cos(angular_velocity);
            y = body.getAnchorPoint().y() + body.getOrbitRadius() * Math.sin(angular_velocity);
        }
        return new Position(x, y);
    }

    public static void setScale(double scale) {
        earth_orbit_scale = scale;
    }
}

package solar_system.model;

public class Position {
    private double x;
    private double y;
    
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double x(){
        return x;
    }
    
    public double y(){
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

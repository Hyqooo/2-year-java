package figures;

import java.util.ArrayList;


public class Figure {
    ArrayList<Point> vertices;
    
    public Figure(ArrayList<Point> vertices){
        this.vertices = vertices;
    }
    
    @Override
    public String toString(){
        return vertices.toString();
    }
}

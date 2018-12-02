package figures;

import java.util.ArrayList;


public class Triangulation {
    
    static enum turn_t {left, right, collinear};
    
    public static ArrayList<Polygon> triangulate(Polygon p){
        ArrayList<Polygon> triangles = new ArrayList<>();
        Polygon triangle;
        ArrayList<Point> vertices = new ArrayList<>(p.getVertices());
        ArrayList<Point> trVertices;
        
        turn_t state;
        
        for (int i = 1; vertices.size() != 2;){
            
            if (i + 1 > vertices.size() - 1)
                i = 1;
            if (i < 0)
                i = vertices.size() - 2;
            
            if ((state = turn(vertices.get(i - 1), vertices.get(i + 1), vertices.get(i))) == turn_t.right){
                // Ухо
                trVertices = new ArrayList<>();
                trVertices.add(vertices.get(i - 1));
                trVertices.add(vertices.get(i));
                trVertices.add(vertices.get(i + 1));
                
                triangle = new Polygon(trVertices);
                triangles.add(triangle);
                vertices.remove(i);
            }else{
                if (state != turn_t.left)
                    vertices.remove(i);
                else
                    i++;
            }
        }
 
      
        return triangles;
    }
    
    public static double cross(Point a, Point b, Point c) {
        double baX, baY, caX, caY;
        
        baX = b.getX() - a.getX();
        baY = b.getY() - a.getY();
        caX = c.getX() - a.getX();
        caY = c.getY() - a.getY();
        return baX * caY - baY * caX;
    } 

    private static turn_t turn(Point a, Point b, Point c) {
        double det = cross(a, b, c);
        if (det > 0) {
            return turn_t.left;
        }
        if (det < 0) {
            return turn_t.right;
        }

        return turn_t.collinear;
    }
    
}

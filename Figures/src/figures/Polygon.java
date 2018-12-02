package figures;

import java.util.ArrayList;

/*
    Vertices - вершины
    isConvex - выпуклый ли многогранник
 */
public class Polygon {

    private ArrayList<Point> vertices;
    
    public Polygon(ArrayList<Point> vertices) {
        this.vertices = vertices;
    }

    public double square() {
        double square = 0;
        ArrayList<Polygon> triangles = Triangulation.triangulate(this);

        for (int i = 0; i < triangles.size(); i++) {
            square += triSquare(triangles.get(i));
        }

        return square;
    }
    
    public boolean isConvex(){
        for (int i = 1; i < vertices.size() - 1; i++){
            if (Triangulation.cross(vertices.get(i - 1), vertices.get(i), vertices.get(i + 1)) < 0)
                return false;
        }
        
        if (Triangulation.cross(vertices.get(vertices.size() - 2), vertices.get(0), vertices.get(vertices.size() - 1)) > 0)
            return false;
        
        return true;
    }
   
    public double perimeter(){
        double perimeter = 0;
        
        for (int i = 0; i < vertices.size() - 1; i++)
            perimeter += lengthOfSection(vertices.get(i), vertices.get(i + 1));
        
        perimeter += lengthOfSection(vertices.get(0), vertices.get(vertices.size() - 1));
        
        return perimeter;
    }

    private double triSquare(Polygon triangle) {
        double square = 0;
        double x_1, y_1, x_2, y_2;

        x_1 = triangle.vertices.get(0).getX() - triangle.vertices.get(1).getX();
        y_1 = triangle.vertices.get(0).getY() - triangle.vertices.get(1).getY();

        x_2 = triangle.vertices.get(0).getX() - triangle.vertices.get(2).getX();
        y_2 = triangle.vertices.get(0).getY() - triangle.vertices.get(2).getY();
        
        square = Math.abs(x_1 * y_2 - x_2 * y_1);
        
        return square / 2;
    }
    
    private double lengthOfSection(Point a, Point b){
        double x, y;
        
        x = a.getX() - b.getX();
        y = a.getY() - b.getY();
        
        return Math.sqrt(x * x + y * y);
    }

    public ArrayList<Point> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        String str = "\n";

        for (int i = 0; i < vertices.size(); i++) {
            str += (i + 1) + " (" + vertices.get(i).toString() + ")\n";
        }
        
        str += "Perimeter: " + perimeter() + "\n";
        str += "Square: " + square() + "\n";

        return str;
    }
}

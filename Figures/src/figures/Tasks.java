package figures;

import Comparators.SquaresComparator;
import java.util.ArrayList;
import java.util.Collections;


public class Tasks {
    
    public static Polygon minPerimeter(ArrayList<Polygon> polygons){
        double minPerimeter = polygons.get(0).perimeter();
        int polygonNumber = 0;
        double perimeter;
        
        for (int i = 0; i < polygons.size(); i++){
            perimeter = polygons.get(i).perimeter();
            if (minPerimeter > perimeter){
                minPerimeter = perimeter;
                polygonNumber = i;
            }
        }
        
        return polygons.get(polygonNumber);
    }
    
    public static ArrayList<Polygon> sortSq(ArrayList<Polygon> polygons){
        SquaresComparator c = new SquaresComparator();
        Collections.sort(polygons, c);
        return polygons;
    }
    
    public static ArrayList<Polygon> certainFigures(ArrayList<Polygon> polygons){
        ArrayList<Polygon> certFigures = new ArrayList<>();
        Polygon temp;
        
        for (int i = 0; i < polygons.size(); i++){
            temp = polygons.get(i);
            if (isCertFigure(temp))
                certFigures.add(temp);
        }
        
        return certFigures;
    }
    
    private static boolean isCertFigure(Polygon pol){
        if (pol.getVertices().size() != 4)
            return false;
        
        for (int i = 1; i < pol.getVertices().size() - 1; i++){
            ArrayList<Point> points = pol.getVertices();
            if (scalarProduct(points.get(i), points.get(i - 1), points.get(i + 1)) != 0)
                return false;
        }
        return true;
    }
    
    private static double scalarProduct(Point p_1, Point p_2, Point p_3){
        double x_1, y_1;
        double x_2, y_2;
        
        x_1 = p_2.getX() - p_1.getX();
        y_1 = p_2.getY() - p_1.getY();
        x_2 = p_3.getX() - p_1.getX();
        y_2 = p_3.getY() - p_1.getY();
        
        return x_1 * x_2 + y_1 * y_2;
    }
}
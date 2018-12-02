package figures;

import Comparators.SquaresComparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Figures {
    
    public static void main(String[] args) {
        fileRead();
    }
    
    private static void fileRead(){ 
        String source = "D:\\polygons.txt";
        String bufferedStr;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String str;
            String points[];
            String coord[] = new String[2];

            ArrayList<Polygon> polygons = new ArrayList<>();

            while ((bufferedStr = reader.readLine()) != null) {
                points = bufferedStr.split(" ");

                ArrayList<Point> vertices = new ArrayList<>();

                for (int i = 0; i < points.length; i++) {
                    coord = points[i].split(",");
                    vertices.add(new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1])));
                }

                Polygon pol = new Polygon(vertices);
                polygons.add(pol);
            }
            
            // Tasks
            System.out.println("Min perimeter:\n" + Tasks.minPerimeter(polygons));
            System.out.println("Sorted by squares:\n" + Tasks.sortSq(polygons));
            System.out.println("Certain figures:\n" + Tasks.certainFigures(polygons));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

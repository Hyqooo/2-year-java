package figures;

import java.util.ArrayList;
import java.util.Scanner;

public class Figures {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        String str;
        String points[];
        String coord[] = new String[2];
        
        str = in.nextLine();
        
        points = str.split(" ");
        
        for (int i = 0; i < points.length; i++){
            System.out.println(points[i]);
        }
        
        ArrayList <Point> temp = new ArrayList<>();
        
        for (int i = 0; i < points.length; i++){
            coord = points[i].split(",");
            temp.add(new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1])));
        }
        
        for (int i = 0; i < temp.size(); i++){
            System.out.println(temp.get(i));
        }
        
        Figure fg = new Figure(temp);
        
        System.out.println(fg);

    }
    
}

package Comparators;

import figures.Polygon;
import java.util.Comparator;

public class SquaresComparator implements Comparator<Polygon> {
    
    @Override
    public int compare(Polygon pol_1, Polygon pol_2){
        if (pol_1.square() - pol_2.square() < 0)
            return -1;
        else 
            return 1;
    }
}

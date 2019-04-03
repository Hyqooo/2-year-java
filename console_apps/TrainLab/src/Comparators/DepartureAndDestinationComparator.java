package Comparators;

import java.util.Comparator;
import trains_lab.Train;


public class DepartureAndDestinationComparator implements Comparator<Train> {
    
    public int compare(Train tr_1, Train tr_2){
        return tr_1.getDepPoint().compareTo(tr_2.getDepPoint()) | tr_1.getDest().compareTo(tr_2.getDest());
    }
    
}

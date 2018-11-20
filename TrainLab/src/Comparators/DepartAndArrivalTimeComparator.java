package Comparators;

import java.util.Comparator;
import trains_lab.Train;

public class DepartAndArrivalTimeComparator implements Comparator<Train> {
    
    public int compare(Train tr_1, Train tr_2){
        // Проверяет равенство времени прибытия двух поездов
        int time = tr_1.getArrTime().equals(tr_2.getArrTime()) ? 0 : 1;
        return tr_1.getDepPoint().compareTo(tr_2.getDepPoint()) | time;
    }
}

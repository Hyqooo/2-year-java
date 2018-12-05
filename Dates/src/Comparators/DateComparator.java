package Comparators;

import dates.Date;
import java.util.Comparator;


public class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date dt_1, Date dt_2){
        if (dt_1.getYear() == dt_2.getYear()){
            if (dt_1.getMonth() == dt_2.getMonth()){
                if (dt_1.getYear() == dt_2.getYear())
                    return 0;
                else 
                    return dt_1.getMonth() - dt_2.getMonth();
            }else{
                return dt_1.getMonth() - dt_2.getMonth();
            }   
        }else{
            return dt_1.getYear() - dt_2.getYear();
        }
    }
}

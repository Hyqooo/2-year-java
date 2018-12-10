package dates;

import Comparators.DateComparator;
import Exceptions.InvalidDateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Date {
    private int day;
    private int month;
    private int year;
    static int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    public Date(int day, int month, int year) throws InvalidDateException{
        validateDate(day, month, year);
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public int getDay(){
        return day;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getYear(){
        return year;
    }
    
    private void validateDate(int day, int month, int year) throws InvalidDateException {
        if (isLeapYear(year)) daysInMonths[1] = 29;
        if (day < 0 || day > 31)
            throw new InvalidDateException("Day is out of boundaries");
        if (month < 1 || month > 12)
            throw new InvalidDateException("Month is out of boundaries");
        if (year < 2001 || year > 3000)
            throw new InvalidDateException("Year is out of boundaries");
        if (month == 2 && day > 29 && isLeapYear(year))
            throw new InvalidDateException("In leap year in february maximum 29 days");
        if (month == 2 && day > 28 && !isLeapYear(year))
            throw new InvalidDateException("In this year in february maximum 28 days");
        if (daysInMonths[month - 1] < day)
            throw new InvalidDateException("In this month 30 days");
    }
    
    private boolean isLeapYear(int year){
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        else
            return false;
    }
    
    public String weekday() {
        String days[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int a = (14 - month) / 12, y = year - a, m = month + 12 * a - 2;
        return days[(day + y + y / 4 - y / 100 + y / 400 + (31 * m) / 12) % 7];
    }
    
    public boolean isDateBefore(Date date){
        DateComparator c = new DateComparator(); 
        if (c.compare(date, this) > 0)
            return true;
        
        return false;
    }
    
    public Date offsetDate(int offset) throws InvalidDateException{
        int day = this.day; 
        int month = this.month;
        int year = this.year;
        int absOffset = Math.abs(offset);
        
        if (offset > 0){
            for (; absOffset != 0; absOffset--){
                if (day < daysInMonths[month - 1]){
                    day++;
                }else{
                    day = 1;
                    if (month < 12){
                        month++;
                    }else{
                        month = 1;        
                        year++;
                        if (isLeapYear(year))
                            daysInMonths[1] = 29;
                        else
                            daysInMonths[1] = 28;
                    }
                }
            }
        }else{
            for (; absOffset != 0; absOffset--) {
               if (day > 1){
                    day--;
                }else{
                    month--;
                    if (month > 0){
                        day = daysInMonths[month - 1];
                    }else{
                        month = 12;
                        day = daysInMonths[month - 1];
                        year--;
                        if (isLeapYear(year))
                            daysInMonths[1] = 29;
                        else
                            daysInMonths[1] = 28;
                    }
                }
            }
        }
        
        return new Date(day, month, year);
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null)
            return false;
        if (!(obj instanceof Date))
            return false;
        
        Date date = (Date)obj;        
        
        return day == date.day && month == date.month && year == date.year;
    }
    
    @Override
    public String toString(){
        return day + "." + month + "." + year;
    }
}

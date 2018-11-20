package trains_lab;

import Exceptions.InvalidTimeFormatException;

public class Time {
    
    private int hours;
    private int minutes;
    
    public Time(int hours, int minutes) throws InvalidTimeFormatException{
        if (hours < 0 || hours > 24 || minutes < 0 || minutes > 59)
            throw new InvalidTimeFormatException();
        
        
        this.hours = hours;
        this.minutes = minutes;
    }
    
    public static int[] parseTime (String timeStr) throws InvalidTimeFormatException{
        int hours, minutes;
        
        String [] str = timeStr.split(":");
        int []time = new int[2];
        
        hours = Integer.parseInt(str[0]);
        minutes = Integer.parseInt(str[1]);
        
        time[0] = hours;
        time[1] = minutes;
        
        return time;
    }
    
    @Override 
    public boolean equals(Object obj){
        
        if (obj == null)
            return false;
        
        if (!(obj instanceof Time))
            return false;
         
        Time a = (Time)obj;
        
        return a.hours == hours && a.minutes == minutes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.hours;
        hash = 37 * hash + this.minutes;
        return hash;
    }
    
    @Override
    public String toString(){
        String formatHours = "" + hours;
        String formatMinutes = "" + minutes;
        
        if (hours < 10)
            formatHours = "0" + hours;
        
        if (minutes < 10)
            formatMinutes = "0" + minutes;
        
        return formatHours + ":" + formatMinutes;
    }
}

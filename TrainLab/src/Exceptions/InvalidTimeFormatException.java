package Exceptions;


public class InvalidTimeFormatException extends Exception {

    public InvalidTimeFormatException(){ }
    
    @Override
    public String toString(){
        return "Invalid time format, check your input";
    }
    
}

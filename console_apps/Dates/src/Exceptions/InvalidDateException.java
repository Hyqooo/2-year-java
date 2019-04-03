package Exceptions;


public class InvalidDateException extends Exception {
    
    String errorMessage;
    
    public InvalidDateException(String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    @Override
    public String toString(){
        return errorMessage;
    }
}

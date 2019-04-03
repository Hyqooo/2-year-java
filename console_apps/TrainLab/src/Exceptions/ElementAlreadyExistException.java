package Exceptions;


public class ElementAlreadyExistException extends Exception {

    private String errorMessage;
    
    public ElementAlreadyExistException (String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    @Override
    public String toString(){
        return "train #" + errorMessage + " already in the list.";
    }
    
}

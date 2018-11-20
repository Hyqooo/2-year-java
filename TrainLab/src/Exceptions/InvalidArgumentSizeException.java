package Exceptions;

public class InvalidArgumentSizeException extends Exception {
    
    private int numberOfArgs;
    private String functionName;
    
    public InvalidArgumentSizeException (int numberOfArgs, String functionName){
        this.numberOfArgs = numberOfArgs;
        this.functionName = functionName;
    }
    
    @Override
    public String toString(){
        return "Invalid number of parameters - " + numberOfArgs + " in command " + functionName;
    }
}

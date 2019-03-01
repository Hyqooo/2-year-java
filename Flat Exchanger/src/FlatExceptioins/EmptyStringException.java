package FlatExceptioins;


public class EmptyStringException extends Exception {

    String parameter;

    public EmptyStringException(String parameter) {
        this.parameter = parameter;
    }
    
    @Override
    public String toString() {
        return  parameter + " cannot be empty!\n";
    }
}

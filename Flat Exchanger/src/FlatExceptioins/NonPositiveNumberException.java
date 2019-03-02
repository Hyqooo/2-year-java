package FlatExceptioins;

public class NonPositiveNumberException extends Exception {

    String parameter;

    public NonPositiveNumberException(String parameter) {
        this.parameter = parameter;
    }
    
    @Override
    public String toString() {
        return  parameter + " cannot be non-positive!\n";
    }
}

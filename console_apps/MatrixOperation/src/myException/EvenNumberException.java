package myException;

public class EvenNumberException extends Exception {

    String strToOutput = "Size cannot be even!";

    public EvenNumberException() { }

    @Override
    public String toString() {
        return strToOutput;
    }

    @Override
    public String getMessage() {
        return this.toString();
    }
}

package myException;

public class nonPositiveSizeException extends Exception {

    String strToOutput = "Size cannot be non-positive number!";

    public nonPositiveSizeException() { }

    @Override
    public String toString() {
        return strToOutput;
    }

    @Override
    public String getMessage() {
        return this.toString();
    }
}

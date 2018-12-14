package testwork2larina;


public class MissPeriodException extends Exception {
    
    public MissPeriodException(){ }
    
    @Override
    public String toString(){
        return "Missed '.'";
    }
}

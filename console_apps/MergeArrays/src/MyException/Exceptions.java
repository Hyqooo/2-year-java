package MyException;

public class Exceptions extends Exception{
    
    
    String s = "Try again";
    int size;
    public Exceptions(int size)
    {
        this.size = size;
    }
    @Override
    public String toString(){
        return s+size;
    }
    @Override
    public String getMessage(){
        return this.toString();
    }
}

package maintest;

class sum{
    int a;
    int b;
    
    public sum(int a, int b){
        this.a = a;
        this.b = b;
    }
    
    public int plus(){
        int c = a + b;
        return c;
    }
    
}


public class Maintest {

    public static void main(String[] args) {
        sum s = new sum(1, 2);
        int d = s.plus();
        System.out.println(d);
    }
    
}

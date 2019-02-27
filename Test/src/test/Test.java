package test;

public class Test {
    static Fraction array[] = new Fraction[5];
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++){
            array[i] = new Fraction(1,1);
            System.out.println(array[i]);
        }
        
        
    }
    
}

package lambda_exlosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lambda_exlosition {

    interface Action<R,T>{
        R action(T first, T second);
    }
    
    interface Test <T>{
        boolean test(T t);
    }
    
    public static void main(String[] args) {
        Test<Integer> nonZero = (number) -> { return number > 0; };
        
        Polygon pol = new Polygon(0, 22);
        Action<Double, Double> op = (a, b) -> a * b;
        Action<Integer, Integer> div = (first, second)->{
            int count = 0;
            if (nonZero.test(second) == false)
                return count;
            while (first % second == 0){ 
                first /= second;
                count++;
            }
            return count;
        };
        Action<Integer, Integer> gcd = (first, second) -> {
            int c;
            while (second != 0){
                c = first % second;
                first = second;
                second = c;
            }
            
            return Math.abs(first);
        };
        
        System.out.println(op.action(pol.getHeight(), pol.getWidth()));
        System.out.println(div.action(9, 2));
        System.out.println(gcd.action(48, 36));
        
        // additional
        ArrayList<String> str = new ArrayList<>();
        str.add("aas");
        str.add("aslfjkasjdfajsf asl djflasjdfl ");
        str.add("sadfsdffffff");
        Action<Integer, String> length = (str_1, str_2) -> {
            return str_1.compareTo(str_2);
        };
        
    }
}

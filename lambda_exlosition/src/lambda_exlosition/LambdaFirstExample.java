/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdafirstexample;

/**
 *
 * @author nadezhda
 */
interface MyNumber {

    double getNumber();
}

interface NumberTest {

    boolean test(int n);
}
interface Test <T> {

    boolean test(T n);
}

public class LambdaFirstExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyNumber mn1 = () -> 2.5;
        System.out.println(mn1.getNumber());
        NumberTest nt1 = (int n) -> (n % 2) == 0;
        //NumberTest nt1 = (n) -> (n%2)==0;
        if (nt1.test(11)) {
            System.out.println("even number");
        } else {
            System.out.println("odd number");
        }
        NumberTest nt2 = (n) -> n > 0;
        if (nt2.test(11)) {
            System.out.println("positive number");
        } else {
            System.out.println("non-positive number");
        }
        NumberTest nt3 = (n) -> {
            int st = 0, x = n, a = 0;
            while (x > 0) {
                x = x / 10;
                st++;
            }
            x = n;
            while (x > 0) {
                a += (int) Math.pow(x % 10, st);
                x = x / 10;
            }
            return a == n;
        };

        if (nt3.test(153)) {
            System.out.println("pluperfect digital invariant");
        } else {
            System.out.println("non-pluperfect digital invariant");
        }
        Test <Double> nt = (n) -> n > 0;
        if (nt.test(-11.0)) {
            System.out.println("positive number");
        } else {
            System.out.println("non-positive number");
        }

    }

}

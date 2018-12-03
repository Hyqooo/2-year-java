package polynoms;

import java.util.Scanner;

public class Polynoms {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        double polynom[];
        
        String pol = in.nextLine();
        String coef[];
        
        coef = pol.split(" ");
        int power = Integer.parseInt(coef[0]);
        polynom = new double[power + 1];
        for(int i = 0; i < power + 1; i++){
            polynom[i] = Double.parseDouble(coef[i + 1]);
        }
        
        Polynom p = new Polynom(power, polynom);
        System.out.println(p);
        
        pol = in.nextLine();
        
        coef = pol.split(" ");
        power = Integer.parseInt(coef[0]);
        polynom = new double[power + 1];
        for(int i = 0; i < power + 1; i++){
            polynom[i] = Double.parseDouble(coef[i + 1]);
        }
        
        Polynom p2 = new Polynom(power, polynom);
        System.out.println(p2);
        
        System.out.println("Sum: " + Cases.sum(p, p2));
        System.out.println("Sub: " + Cases.subtraction(p, p2));
        System.out.println("Product: " + Cases.product(p, p2));
    }
    
}

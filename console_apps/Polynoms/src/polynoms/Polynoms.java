package polynoms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Polynoms {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double polynom[];
        String source = "D:\\polynoms.txt";

        String bufferedStr;
        ArrayList<Polynom> polList = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String coef[] = null;

            double a, b, step;

            while ((bufferedStr = reader.readLine()) != null) {
                coef = bufferedStr.split(" ");
                int power = Integer.parseInt(coef[0]);
                polynom = new double[power + 1];
                for (int i = 0; i < power + 1; i++) {
                    polynom[i] = Double.parseDouble(coef[i + 1]);
                }
                Polynom p = new Polynom(power, polynom);
                polList.add(p);
            }

            System.out.println("Sum: " + Cases.sum(polList.get(0), polList.get(1)));
            System.out.println("Sub: " + Cases.subtraction(polList.get(0), polList.get(1)));
            System.out.println("Product: " + Cases.product(polList.get(0), polList.get(1)));

            System.out.println("Input a:");
            a = in.nextDouble();
            System.out.println("Input b:");
            b = in.nextDouble();
            System.out.println("Input step:");
            step = in.nextDouble();

            Cases.ValuesBeetwenAB(polList.get(0), a, b, step);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

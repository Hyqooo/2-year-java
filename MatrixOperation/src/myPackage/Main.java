/*
    Лабораторная 1. Классы.
*/
package myPackage;

import myException.nonPositiveSizeException;
import myException.EvenNumberException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Matrix b;
        int size = 0;
        Scanner in = new Scanner(System.in);

        // Size input
        try {
            System.out.println("Input size of the matrix");
            size = in.nextInt();
        }catch(InputMismatchException e){
            System.out.println(e);
            return;
        }

        // Matrix initialization
        try {
            b = new Matrix(size);
        }catch (nonPositiveSizeException e) {
            System.out.println(e);
            return;
        }catch (EvenNumberException e){
            System.out.println(e);
            return;
        }

        // Matrix input
        try {
            int value;
            System.out.println("Fill up the matrix:");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    value = in.nextInt();
                    b.set(i, j, value);
                }
            }
        }catch(InputMismatchException e){
            System.out.println(e);
            return;
        }

        // Before exchange
        System.out.println("Before exchange");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(b.get(i, j) + " ");
            }
            System.out.println();
        }

        b.ExchangeWithMiddle();

        // After exchange
        System.out.println("After exchange");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(b.get(i, j) + " ");
            }
            System.out.println();
        }
    }
}

package testwork2larina;

import java.util.Scanner;

public class TestWork2Larina {

    public static void main(String[] args) {
       System.out.println("Input text of the telegram");
       Scanner sc = new Scanner(System.in);
       String text = sc.nextLine();
       
       Telegram tel = new Telegram(text);
       System.out.println(tel);
       System.out.println("Price of the telegram: " + tel.evaluatePrice());
    }
    
}

package testwork2larina;

import java.util.Scanner;

public class TestWork2Larina {

    public static void main(String[] args) {
        System.out.println("Input text of the telegram");
        Scanner sc = new Scanner(System.in, "Cp1251");
        String text = sc.nextLine();
        
        try{
            System.out.println(new Telegram(text));
            System.out.println("Letter: " + new Letter(10));
        }catch(MissPeriodException e){
           System.out.println(e);
        }
    }
    
}

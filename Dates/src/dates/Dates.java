package dates;

import Exceptions.InvalidDateException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dates {

    public static void main(String[] args) {
        String source = "D:\\dates.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(source))){
            Scanner in = new Scanner(System.in);
            String date[] = new String[3];

            System.out.print("Input date (format: <day>.<month>.<year>): ");
            date = in.nextLine().split("\\.");

            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);

            // Days of week
            Date originalDate = new Date(day, month, year);
            System.out.println(originalDate.weekday());
            
            // +- offset
            System.out.println("Input offset '+<number>' after or '-<number>' before original date");
            int offset = in.nextInt();
            System.out.println(originalDate.offsetDate(offset) + " it's " + originalDate.offsetDate(offset).weekday());
            
            // File
            ArrayList<Date> dates = new ArrayList<>();
            String bufferedStr;
            while ((bufferedStr = reader.readLine()) != null){
                String strdate[] = bufferedStr.split(" ");
                for (int i = 0; i < strdate.length; i++) {
                    date = strdate[i].split("\\.");
                    day = Integer.parseInt(date[0]);
                    month = Integer.parseInt(date[1]);
                    year = Integer.parseInt(date[2]);
                    Date newDate = new Date(day, month, year);
                    dates.add(newDate);
                }
            }
            System.out.println("Dates before original date: " + originalDate.datesBefore(dates));
            System.out.println("Dates after original date: " + originalDate.datesAfter(dates));    
        } catch(InvalidDateException | ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}

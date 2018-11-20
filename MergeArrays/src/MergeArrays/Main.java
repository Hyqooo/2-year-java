package MergeArrays;
import MyException.Exceptions;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int size;
        int generalSize = 0;
        int value;
        
        Array a;
        Array b;
        
        try{
            System.out.print("Input the first array size: ");
            size = in.nextInt();
            a = new Array(size);
            
            System.out.println("Fill the first array: ");
            for (int i = 0; i < size; i++){
                value = in.nextInt();
                a.set(value, i);
            }
            
            generalSize += size;
            
            System.out.print("Input the second array size: ");
            size = in.nextInt();
            b = new Array(size);
            
            System.out.println("Fill the second array: ");
            for (int i = 0; i < size; i++){
                value = in.nextInt();
                b.set(value, i);
            }
            
            generalSize += size;
        }catch (InputMismatchException e){
            System.out.println("Incorrect input");
            return;
        }catch (Exceptions e){
            System.out.println("Wrong order");
            return;
        }catch (NegativeArraySizeException e){
            System.out.println("Negative size!");
            return;
        }
        
        a.merge(b);
        
        System.out.println("Output: ");
        for (int i = 0; i < generalSize; i++){
            System.out.print(a.get(i) + " ");
        }
    }
}
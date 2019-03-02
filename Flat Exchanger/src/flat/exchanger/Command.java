package flat.exchanger;

import FlatExceptioins.NonPositiveNumberException;
import FlatExceptioins.EmptyStringException;
import java.util.ArrayList;
import java.util.Scanner;

public class Command {

    private static Scanner sc = new Scanner(System.in);
    
    // Input is interface because we can return any type we want to
    private interface input <R>{
        R input(String parameter);
    }
    
    private static input<Double> numberInput = (parameter) -> {
        double input;
        
        while (true){
            System.out.print(parameter);
            try {
                // We need to use wrapper because method nextDouble() doesn't cosume '\n' symbol, contrast to method nextLine().
                // So to avoid any misses of '\n' we use the wrapper.
                input = Double.valueOf(sc.nextLine());
                if (input <= 0)
                    throw new NonPositiveNumberException("Your input");
                
                break;
            } catch (NonPositiveNumberException | NumberFormatException ex) {
                System.out.println(ex + "try again!");
            }
        }
        
        return input;
    };
    
    private static input<String> stringInput = (parameter) -> {
        String input = "";
        
        while (true){
            System.out.print(parameter);
            try{
                input = sc.nextLine();
                if (input.isEmpty())
                    throw new EmptyStringException(parameter);
                
                break;
            } catch (EmptyStringException ex){
                System.out.println(ex);
            }
        }
        
        return input;
    };
    
    private static input<Address> addressInput = (parameter) -> {
        String area;
        String street;
        int numberOfHouse;
        int floor;
        
        System.out.println(parameter);
        
        area = stringInput.input("Input area: ");
        street = stringInput.input("Input street: ");
        numberOfHouse = numberInput.input("Input number of the house: ").intValue();
        floor = numberInput.input("Input floor: ").intValue();
        
        return new Address(area, street, numberOfHouse, floor);
    };
    
    private static input<Flat.typeOfH> typeInput = (parameters) -> {
        String input;
        
        while (true){
            input = stringInput.input("Input type of house<panel/bricks>: ");
            
            if (input.equals(Flat.typeOfH.BRICKS.toString().toLowerCase())){
                return Flat.typeOfH.BRICKS;
            }else if (input.equals(Flat.typeOfH.PANEL.toString().toLowerCase())){
                return Flat.typeOfH.PANEL;
            }else{
                System.out.println("Unknown type of house, try again");
            }
        }
    }; 
    
    public static void add(FlatDatabase flatDB){
        double footage;
        int numberOfRooms;
        Address address;
        Flat.typeOfH typeOfHouse;
        double price;
        Address paramToExch;
        
        System.out.println("Adding of flat, input all info");
        footage = numberInput.input("Input footage of the flat: ");
        numberOfRooms = numberInput.input("Input number of rooms: ").intValue();
        address = addressInput.input("Input address of the flat");
        typeOfHouse = typeInput.input("");
        price = numberInput.input("Input price of the flat: ");
        paramToExch = addressInput.input("Input address parameters to exchange");
        
        Flat flat = new Flat(footage, numberOfRooms, address, typeOfHouse, price, paramToExch);
        
        flatDB.add(flat);
        
        System.out.println(flatDB);
    }
    
    public static void remove(FlatDatabase flatDB){
        int indexOfFlat = 0;
        
        // Show all address what database has got
        String show = flatDB.show();
        if (!show.isEmpty()){
            System.out.println(show);
            indexOfFlat = numberInput.input("Input index of the flat to delete: ").intValue();
        }else{
            System.out.println("There're no flats in the database");
            return;
        }
        
        try{
            flatDB.remove(indexOfFlat - 1);
        } catch (IndexOutOfBoundsException ex){
            System.out.println("There's no flat with specified index");
        }
    }
    
    public static void search(FlatDatabase flatDB){
        
    }
    
    public static void sortByRooms(FlatDatabase flatDB){
        System.out.println(flatDB.displayByRooms());
    }
    
    public static void sortByAreas(FlatDatabase flatDB){
        System.out.println(flatDB.displayByAreas());
    }
    
    public static void withinRange(FlatDatabase flatDB){
        double minimum, maximum;
        
        minimum = numberInput.input("Input minimum of the price: ");
        maximum = numberInput.input("Input maximum of the price: ");
        
        System.out.println(flatDB.displayWithinRange(minimum, maximum));
    }
}

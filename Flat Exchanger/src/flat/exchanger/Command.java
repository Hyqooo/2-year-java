package flat.exchanger;

import java.util.Scanner;


public class Command {
    
    // Input is interface because we can return any type we want to
    
    interface input <R, String>{
        R input(String message);
    }
    
    static input<Integer, String> numberInput = (message) -> {
        
        System.out.println(message);
        
        return 1;
    };
    
//        enum typeOfH {
//        PANEL,
//        BRICKS
//        };
//    
//    private int footage;
//    private int numberOfRooms;
//    private Address address;
//    private typeOfH typeOfHouse;
//    private int floorNumber;
//    private int price;
//    private Address paramOfExchange;
    
    
    public static void add(FlatDatabase flatDB){
        int footage;
        
        System.out.println("Adding of flat, input all info");
        
    }
    
    public static void remove(FlatDatabase flatDB){
        
    }
    
    public static void search(FlatDatabase flatDB){
        
    }
    
    public static void sortByRooms(FlatDatabase flatDB){
        
    }
    
    public static void sortByAreas(FlatDatabase flatDB){
        
    }
    
    public static void withinRange(FlatDatabase flatDB){
        
    }
}

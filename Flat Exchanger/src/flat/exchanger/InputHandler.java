package flat.exchanger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class InputHandler {
    
    private static Scanner stdinput = new Scanner(System.in);
    
    // Original database
    static FlatDatabase DB = new FlatDatabase();

    // Help
    static HashMap<String, String> help = new HashMap<>();

    public static void commandHandler() {
        String command;

        System.out.println("Input command or help to display all commands");
        while (true) {
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            if (!sc.hasNextLine()) {
                break;
            }

            command = sc.nextLine();

            switch (command) {
                case "add":
                    Command.add(DB, stdinput,false);
                    break;
                case "remove":
                    Command.remove(DB, stdinput,false);
                    break;
                case "search":
                    Command.search(DB);
                    break;
                case "areas":
                    Command.sortByAreas(DB);
                    break;
                case "rooms":
                    Command.sortByRooms(DB);
                    break;
                case "range":
                    Command.withinRange(DB, stdinput,false);
                    break;
                case "help":
                    help.forEach((k, v) -> System.out.println(k + " - " + v));
                    break;
                case "exit":
                    return;
                default:
                    System.out.format("Command %s is unknown,try again\n", command);
                    break;
            }

        }
    }

    // Reads data from file and adds them to collection
    public static void readFile() {
        File path = new File("source.txt");
        Scanner readFile = null;

        try {
            readFile = new Scanner(path);
            while (readFile.hasNextLine()) {
                Command.add(DB, readFile, true);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } finally {
            if (readFile != null) {
                readFile.close();
            }
        }
    }

    public static void initHelp() {
        help.put("add", "Adds a new flat to database");
        help.put("remove", "Removes specified flat from database");
        help.put("search", "Searches specified flat to exchange");
        help.put("areas", "Sort database according to the names of the areas where flats are located");
        help.put("rooms", "Sort database according to the amount of rooms at flats");
        help.put("range", "Search flats suitable within specified range");
        help.put("exit", "Exits the program");
    }
}

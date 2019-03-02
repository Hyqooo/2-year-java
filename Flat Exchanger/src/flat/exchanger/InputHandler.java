package flat.exchanger;

import java.util.HashMap;
import java.util.Scanner;

public class InputHandler {

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
                    Command.add(DB);
                    break;
                case "remove":
                    Command.remove(DB);
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
                    Command.withinRange(DB);
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

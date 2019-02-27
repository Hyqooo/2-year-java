package flat.exchanger;

import java.util.Scanner;

public class InputHandler {

    static FlatDatabase DB = new FlatDatabase();

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
                case "exit":
                    return;
                default:
                    System.out.format("Command %s is unknown,try again", command);
                    break;
            }

        }
    }

    public static void readFile() {
        // Reads data from file and adds them to collection
    }
}

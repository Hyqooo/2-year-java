package flat.exchanger;

import FlatExceptioins.NonPositiveNumberException;
import FlatExceptioins.EmptyStringException;
import java.util.Scanner;

public class Command {

    // Input is interface because we can return any type we want to
    private interface input<R> {

        R input(String parameter, Scanner sc, boolean suppress);
    }

    private static input<Double> numberInput = (parameter, sc, suppress) -> {
        double input;

        while (true) {
            if (suppress == false) {
                System.out.print(parameter);
            }
            try {
                // We need to use wrapper because method nextDouble() doesn't cosume '\n' symbol, contrast to method nextLine().
                // So to avoid any misses of '\n' we use the wrapper.
                input = Double.valueOf(sc.nextLine());
                if (input <= 0) {
                    throw new NonPositiveNumberException("Your input");
                }

                break;
            } catch (NonPositiveNumberException | NumberFormatException ex) {
                System.out.println(ex + "try again!");
            }
        }

        return input;
    };

    private static input<String> stringInput = (parameter, sc, suppress) -> {
        String input = "";

        while (true) {
            if (suppress == false) {
                System.out.print(parameter);
            }
            try {
                input = sc.nextLine();
                if (input.isEmpty()) {
                    throw new EmptyStringException(parameter);
                }

                break;
            } catch (EmptyStringException ex) {
                System.out.println(ex);
            }
        }

        return input;
    };

    private static input<Address> addressInput = (parameter, sc, suppress) -> {
        String area;
        String street;
        int numberOfHouse;
        int floor;
        if (suppress == false) {
            System.out.println(parameter);
        }

        area = stringInput.input("Input area: ", sc, suppress);
        street = stringInput.input("Input street: ", sc, suppress);
        numberOfHouse = numberInput.input("Input number of the house: ", sc, suppress).intValue();
        floor = numberInput.input("Input floor: ", sc, suppress).intValue();

        return new Address(area, street, numberOfHouse, floor);
    };

    private static input<Flat.typeOfH> typeInput = (parameters, sc, suppress) -> {
        String input;

        while (true) {
            input = stringInput.input("Input type of house<panel/bricks>: ", sc, suppress);

            if (input.equals(Flat.typeOfH.BRICKS.toString().toLowerCase())) {
                return Flat.typeOfH.BRICKS;
            } else if (input.equals(Flat.typeOfH.PANEL.toString().toLowerCase())) {
                return Flat.typeOfH.PANEL;
            } else {
                System.out.println("Unknown type of house, try again");
            }
        }
    };

    public static void add(FlatDatabase flatDB, Scanner input, boolean suppress) {
        double footage;
        int numberOfRooms;
        Address address;
        Flat.typeOfH typeOfHouse;
        double price;
        Address paramToExch;

        if (suppress == false) {
            System.out.println("Adding of flat, input all info");
        }
        footage = numberInput.input("Input footage of the flat: ", input, suppress);
        numberOfRooms = numberInput.input("Input number of rooms: ", input, suppress).intValue();
        address = addressInput.input("Input address of the flat", input, suppress);
        typeOfHouse = typeInput.input("", input, suppress);
        price = numberInput.input("Input price of the flat: ", input, suppress);
        paramToExch = addressInput.input("Input address parameters to exchange", input, suppress);

        Flat flat = new Flat(footage, numberOfRooms, address, typeOfHouse, price, paramToExch);

        flatDB.add(flat);

        // To remove
        System.out.println(flatDB.toString());
    }

    public static void remove(FlatDatabase flatDB, Scanner input, boolean suppress) {
        int indexOfFlat = 0;

        // Show all address what database has got
        String show = flatDB.show();
        if (!show.isEmpty()) {
            System.out.println(show);
            indexOfFlat = numberInput.input("Input index of the flat to delete: ", input, suppress).intValue();
        } else {
            System.out.println("There're no flats in the database");
            return;
        }

        try {
            flatDB.remove(indexOfFlat - 1);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("There's no flat with specified index");
        }
    }

    public static void search(FlatDatabase flatDB, Scanner toFile) {
        
    }

    public static void sortByRooms(FlatDatabase flatDB) {
        System.out.println(flatDB.displayByRooms());
    }

    public static void sortByAreas(FlatDatabase flatDB) {
        System.out.println(flatDB.displayByAreas());
    }

    public static void withinRange(FlatDatabase flatDB, Scanner input, boolean suppress) {
        double minimum, maximum;

        minimum = numberInput.input("Input minimum of the price: ", input, suppress);
        maximum = numberInput.input("Input maximum of the price: ", input, suppress);

        System.out.println(flatDB.displayWithinRange(minimum, maximum));
    }
}

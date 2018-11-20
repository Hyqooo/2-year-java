package trains_lab;

import Exceptions.ElementAlreadyExistException;
import Exceptions.InvalidArgumentSizeException;
import Exceptions.InvalidTimeFormatException;
import SearchAlgorithm.algorithm.DijkstraAlgorithm;

import SearchAlgorithm.graph.Edge;
import SearchAlgorithm.graph.Graph;
import SearchAlgorithm.graph.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Lab_2 {

    static List<Vertex> nodes = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Input command to start.");
        ArrayList<Train> aL = new ArrayList();

        String source = "C:\\Users\\Андрей\\Documents\\NetBeansProjects\\Lab_2.2.3\\src\\source.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(source));) {
            fileInput(reader, aL);
        } catch (IOException | InvalidArgumentSizeException
                | ElementAlreadyExistException | InvalidTimeFormatException e) {
            System.out.println(e);
        }

        while (true) {
            Scanner sc = new Scanner(System.in);
            String command;

            System.out.print(":");
            command = sc.nextLine();
            try {
                if (commandParser(command.split(" "), aL)) {
                    break;
                }
            } catch (InvalidArgumentSizeException | ElementAlreadyExistException | InvalidTimeFormatException e) {
                System.out.println(e);
            }
        }
    }

    private static void fileInput(BufferedReader reader, ArrayList<Train> a) throws IOException,
            InvalidArgumentSizeException, ElementAlreadyExistException, InvalidTimeFormatException {
        String bufferedStr;
        String[] arg;

        while ((bufferedStr = reader.readLine()) != null) {
            arg = bufferedStr.split(" ");
            add(arg, a);
        }

    }

    private static boolean commandParser(String[] command, ArrayList<Train> a) throws InvalidArgumentSizeException, ElementAlreadyExistException, InvalidTimeFormatException {
        String[] commandArg = new String[command.length - 1];

        for (int i = 0; i < command.length - 1; i++) {
            commandArg[i] = command[i + 1];
        }

        switch (command[0]) {
            case "quit":
                return true;
            case "add":
                add(commandArg, a);
                break;
            case "remove":
                remove(commandArg, a);
                break;
            case "deptime":
                deptime(commandArg, a);
                break;
            case "deparr":
                deparr(commandArg, a);
                break;
            case "route":
                route(commandArg, a);
                break;
            default:
                break;
        }

        return false;
    }

    private static void add(String[] arg, ArrayList<Train> a) throws InvalidArgumentSizeException, ElementAlreadyExistException, InvalidTimeFormatException {
        if (arg.length < 6) {
            throw new InvalidArgumentSizeException(arg.length, "add");
        }

        if (!a.isEmpty()) {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i).getNumber() == Integer.parseInt(arg[0])) {
                    throw new ElementAlreadyExistException(arg[0]);
                }
            }
        }

        int number = Integer.parseInt(arg[0]);
        int price = Integer.parseInt(arg[5]);

        int[] depTimeParsed = Time.parseTime(arg[3]);
        int[] arrTimeParsed = Time.parseTime(arg[4]);

        Time depTime = new Time(depTimeParsed[0], depTimeParsed[1]);
        Time arrTime = new Time(arrTimeParsed[0], arrTimeParsed[1]);

        Train temp = new Train(number, arg[1], arg[2], depTime, arrTime, price);

        a.add(temp);
    }

    private static void remove(String[] arg, ArrayList<Train> a) throws InvalidArgumentSizeException {
        if (arg.length < 1) {
            throw new InvalidArgumentSizeException(arg.length, "remove");
        }

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getNumber() == Integer.parseInt(arg[0])) {
                a.remove(i);
            }
        }
    }

    private static void deptime(String[] arg, ArrayList<Train> a) throws InvalidArgumentSizeException, InvalidTimeFormatException {
        if (arg.length < 2) {
            throw new InvalidArgumentSizeException(arg.length, "deptime");
        }

        Train train;
        int[] parsedTime = Time.parseTime(arg[1]);
        Time compTime = new Time(parsedTime[0], parsedTime[1]);

        for (int i = 0; i < a.size(); i++) {
            train = a.get(i);
            if (train.getDepPoint().equals(arg[0]) && train.getArrTime().equals(compTime)) {
                System.out.println(train);
            }
        }
    }

    private static void deparr(String[] arg, ArrayList<Train> a) throws InvalidArgumentSizeException {
        if (arg.length < 2) {
            throw new InvalidArgumentSizeException(arg.length, "deparr");
        }

        Train train;

        for (int i = 0; i < a.size(); i++) {
            train = a.get(i);
            if (train.getDepPoint().equals(arg[0]) && train.getDest().equals(arg[1])) {
                System.out.println(train);
            }
        }
    }

    private static void route(String[] arg, ArrayList<Train> a) throws InvalidArgumentSizeException {
        if (arg.length < 2) {
            throw new InvalidArgumentSizeException(arg.length, "route");
        }

        List<String> cities;

        cities = getCities(a);

        for (int i = 0; i < cities.size(); i++) {
            Vertex location = new Vertex("vertex_" + i, cities.get(i));
            nodes.add(location);
        }

        for (int i = 0; i < a.size(); i++) {
            Vertex vr_dep = new Vertex("1", a.get(i).getDepPoint());
            Vertex vr_dest = new Vertex("1", a.get(i).getDest());
            addLane("Edge_" + i, nodes.indexOf(vr_dep), nodes.indexOf(vr_dest), a.get(i).getPrice());
        }

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijsktra = new DijkstraAlgorithm(graph);
        try {
            dijsktra.execute(nodes.get(nodes.indexOf(new Vertex("1", arg[0]))));
            LinkedList<Vertex> path = dijsktra.getPath(nodes.get(nodes.indexOf(new Vertex("1", arg[1]))));

            if (path != null) {
                Object[] pathArray = (path.toArray());

                for (int i = 1; i < path.size(); i++) {
                    Vertex pA = (Vertex) pathArray[i - 1];
                    Vertex pNA = (Vertex) pathArray[i];
                    String str = pA.getName() + " " + pNA.getName();
                    deparr(str.split(" "), a);
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    private static void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }

    // Находит все города и составляет из них список
    private static ArrayList<String> getCities(ArrayList<Train> a) {
        ArrayList<String> cities = new ArrayList<>();
        String temp;

        for (int i = 0; i < a.size(); i++) {
            temp = a.get(i).getDepPoint();
            if (!cities.contains(temp)) {
                cities.add(temp);
            }
            temp = a.get(i).getDest();
            if (!cities.contains(temp)) {
                cities.add(temp);
            }
        }

        return cities;
    }
}

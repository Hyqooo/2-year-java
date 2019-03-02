package flat.exchanger;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FlatDatabase {

    private ArrayList<Flat> flats;

    public FlatDatabase() {
        flats = new ArrayList<>();
    }

    public void add(Flat flat) {
        flats.add(flat);
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        flats.remove(index);
    }

    public ArrayList<Flat> search(Flat flat) {
        return (ArrayList<Flat>) flats.stream()
                .filter((each) -> each.getAddress().equals(flat.getParamOfExchange()))
                .collect(Collectors.toList());
    }

    public String show() {
        String result = "";

        for (int i = 0; i < flats.size(); i++) {
            result += (i + 1) + ") " + flats.get(i).getAddress().toString() + "\n";
        }

        return result;
    }

    public ArrayList<Flat> displayByAreas() {
        flats.sort((a, b) -> a.getAddress().getArea().compareTo(b.getAddress().getArea()));
        return flats;
    }

    public ArrayList<Flat> displayByRooms() {
        flats.sort((a, b) -> a.getNumberOfRooms() - b.getNumberOfRooms());
        return flats;
    }

    public ArrayList<Flat> displayWithinRange(double minimum, double maximum) {
        return (ArrayList<Flat>) flats.stream()
                .filter(each -> each.getPrice() > minimum && each.getPrice() < maximum)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "" + flats.toString();
    }
}

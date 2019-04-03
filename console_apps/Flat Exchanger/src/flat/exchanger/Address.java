package flat.exchanger;

public class Address {

    private String area;
    private String street;
    private int numberOfHouse;
    private int floor;

    public Address(String area, String street, int numberOfHouse, int floor) {
        this.area = area;
        this.street = street;
        this.numberOfHouse = numberOfHouse;
        this.floor = floor;
    }

    public String getArea() {
        return area;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Address)) {
            return false;
        }

        Address addr = (Address) obj;
        if (addr.area.equals(area)
                && addr.street.equals(street)
                && addr.numberOfHouse == numberOfHouse) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return ""
                + "Area: " + area
                + "\nStreet: " + street
                + "\nNumber of house: " + numberOfHouse
                + "\nFloor: " + floor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < toString().length(); i++) {
            hash = hash * 31 + toString().charAt(i);
        }

        return hash;
    }
}

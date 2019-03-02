package flat.exchanger;


public class Flat {
    enum typeOfH {
        PANEL,
        BRICKS
        };
    
    private double footage;
    private int numberOfRooms;
    private Address address;
    private typeOfH typeOfHouse;
    private double price;
    private Address paramOfExchange;

    public Flat(double footage, int numberOfRooms, Address address, typeOfH typeOfHouse, double price, Address paramOfExchange) {
        this.footage = footage;
        this.numberOfRooms = numberOfRooms;
        this.address = address;
        this.typeOfHouse = typeOfHouse;
        this.price = price;
        this.paramOfExchange = paramOfExchange;
    }

    public Address getAddress() {
        return address;
    }
    
    public double getPrice() {
        return price;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        
        if (!(obj instanceof Flat)) return false;
        
        Flat fl = (Flat)obj;
        if (fl.address.equals(address))
            return true;
        
        return false;
    }

    @Override
    public String toString() {
        return ""
                + "Footage: " + footage 
                + "\nNumberOfRooms: " + numberOfRooms
                + "\nAddress\n" + address.toString()
                + "\nType of house: " + typeOfHouse.toString()
                + "\nPrice: " + price
                + "\nParameters of exchange\n" + paramOfExchange.toString();
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }
}

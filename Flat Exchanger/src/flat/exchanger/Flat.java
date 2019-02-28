package flat.exchanger;


public class Flat {
    enum typeOfH {
        PANEL,
        BRICKS
        };
    
    private int footage;
    private int numberOfRooms;
    private Address address;
    private typeOfH typeOfHouse;
    private int floorNumber;
    private int price;
    private Address paramOfExchange;

    public Flat(int footage, int numberOfRooms, Address address, typeOfH typeOfHouse, int floorNumber, int price, Address paramOfExchange) {
        this.footage = footage;
        this.numberOfRooms = numberOfRooms;
        this.address = address;
        this.typeOfHouse = typeOfHouse;
        this.floorNumber = floorNumber;
        this.price = price;
        this.paramOfExchange = paramOfExchange;
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
                + "\nAddress:" + address.toString()
                + "\nType of house: " + typeOfHouse.toString()
                + "\nNumber of floors: " + floorNumber
                + "\nPrice: " + price
                + "\nParameters of exchange: " + paramOfExchange.toString();
    }
}

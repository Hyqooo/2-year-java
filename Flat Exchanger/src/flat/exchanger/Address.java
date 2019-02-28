package flat.exchanger;


class Address {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        
        if (!(obj instanceof Flat)) return false;
        
        Address addr = (Address)obj;
        if (
                   addr.area.equals(area)
                && addr.street.equals(street)
                && addr.numberOfHouse == numberOfHouse
                && addr.floor == floor
            )
            return true;
        
        return false;
    }

    @Override
    public String toString() {
        return "" 
                + "Area: " + area 
                + "Street: " + street
                + "Number of house: " + numberOfHouse 
                + "Floor: " + floor;
    } 
}

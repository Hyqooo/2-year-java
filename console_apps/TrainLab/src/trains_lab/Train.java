package trains_lab;

import java.util.Objects;

public class Train {
    
    private int number;
    private String departurePoint;
    private String destination;
    private Time departureTime;
    private Time arrivalTime;
    private int price;
    
    
    public Train(int number, String departurePoint, String destination,
                 Time departureTime, Time arrivalTime, int price){
        
        this.number = number;
        this.departurePoint = departurePoint;
        this.destination = destination;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    public Time getArrTime(){
        return arrivalTime;
    }
    
    public Time getDepTime(){
        return departureTime;
    }
    
    public String getDepPoint(){
        return departurePoint;
    }
    
    public String getDest(){
        return destination;
    }
    
    public int getNumber(){
        return number;
    }
    
    public int getPrice(){
        return price;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null)
            return false;
        
        if (!(obj instanceof Train))
            return false;
        
        Train a = (Train)obj;
        
        return a.number == number;
    }
    
    @Override
    public String toString(){
        String str = "\n#" + number + "\n" + "Departure point: " + departurePoint + 
                "\n" + "Destination: " + destination + "\n" + "Departure time: " + 
                departureTime + "\n" + "Arrival time: " + arrivalTime + 
                "\n" + "Ticket price: " + price;
        
        return str;
    }
}

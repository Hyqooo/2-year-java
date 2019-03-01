package flat.exchanger;

import java.util.ArrayList;

public class FlatDatabase {
    ArrayList<Flat> flats;
    
    public FlatDatabase(){
        flats = new ArrayList<>();
    }
    
    public void add(Flat flat){
        flats.add(flat);
    }
    
    public void remove(Flat flat){
        flats.remove(flat);
    }
    
    public void search(Flat flat){
        
    }
    
    public ArrayList<Flat> displayByAreas(){
        return null;
    }
    
    public ArrayList<Flat> displayByRooms(){
        return null;
    }
    
    public ArrayList<Integer> displayWithinRange(int minimun, int maximum){
        return null;
    }

    @Override
    public String toString() {
        return "" + flats.toString();
    }
}

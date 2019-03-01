package flat.exchanger;

import java.util.ArrayList;
import java.util.stream.Stream;

public class FlatDatabase {
    private ArrayList<Flat> flats;
    
    public FlatDatabase(){
        flats = new ArrayList<>();
    }
    
    public void add(Flat flat){
        flats.add(flat);
    }
    
    public void remove(int index){
        flats.remove(index);
    }

    public void search(Flat flat){
        
    }
    
    public Stream toStream(){
        return flats.stream();
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

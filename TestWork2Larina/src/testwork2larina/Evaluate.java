package testwork2larina;


public abstract class Evaluate<T> {
    public T fullUnit;
    
    public Evaluate(T t){
        fullUnit = t;
    }
    
    public int evaluatePrice(){
        return amountOfUnits() * costOfUnit() + additionalCosts();
    }
    
    public abstract int amountOfUnits();
    public abstract int additionalCosts();
    public abstract int costOfUnit();
    
    @Override
    public String toString(){
        return "Amount of units: " + amountOfUnits() + "\nFull cost: " + evaluatePrice(); 
    }
}

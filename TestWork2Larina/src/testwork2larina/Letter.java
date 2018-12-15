package testwork2larina;


public class Letter extends Evaluate<Integer> {
    
    private final static int ENVELOPE_PRICE = 10;
    private final static int STAMP_PRICE = 3;
    private final static int DISPATCH_PRICE = 20;
    
    public Letter(Integer amountOfLetters){
        super(amountOfLetters);
    }

    @Override
    public int amountOfUnits() {
        return fullUnit;
    }

    @Override
    public int additionalCosts() {
        return ENVELOPE_PRICE + STAMP_PRICE;
    }

    @Override
    public int costOfUnit() {
        return DISPATCH_PRICE;
    }
}

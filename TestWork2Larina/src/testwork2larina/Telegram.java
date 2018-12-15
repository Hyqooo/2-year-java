package testwork2larina;


public class Telegram extends Evaluate<String> {
    
    private StringBuilder textOfTelegram;
    private final static int BLANK_PRICE = 5;
    
    public Telegram(String text) throws MissPeriodException{
        super(text);
        if (fullUnit.charAt(fullUnit.length() - 1) != '.')
            throw new MissPeriodException();
        parseToTelegram();
    }
    
    private void parseToTelegram(){
        textOfTelegram = new StringBuilder(fullUnit);
        
        for (int i = 0; i < textOfTelegram.length(); i++){
            if (textOfTelegram.charAt(i) == ','){
                textOfTelegram.setCharAt(i, ' ');
                textOfTelegram.insert(i + 1, "зпт ");
            }
            if (textOfTelegram.charAt(i) == '.'){
                textOfTelegram.setCharAt(i, ' ');
                textOfTelegram.insert(i + 1, "тчк");
            }
        }
    }

    @Override
    public int amountOfUnits() {
        return textOfTelegram.toString().split(" ").length;
    }

    @Override
    public int additionalCosts() {
        if (fullUnit.split(" ")[0].equals("Artistic")) 
            return BLANK_PRICE;
        return 0;
    }

    @Override
    public int costOfUnit() {
        switch (fullUnit.toString().split(" ")[0]){
            case "Urgent":
                return 4;
            case "Govermental":
                return 6;
            case "International":
                return 8;
            case "Artistic":
                return 4;
            default:
                return 2;
        }
    }
    
    @Override
    public String toString(){
        return "Telegram\n" + "Text of the telegram: " + textOfTelegram.toString() + "\nFullCost: " + evaluatePrice();
    }
}

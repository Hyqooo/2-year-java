package testwork2larina;

import java.util.ArrayList;


public class Telegram extends Evaluate<String> {
    
    private StringBuilder textOfTelegram;
    private final static int BLANK_PRICE = 5;
    private final static String[] prepositions = {"b", "a"};
    
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
        
        deletePrepositions();
    }
    
    private void deletePrepositions(){
        String str[] = textOfTelegram.toString().split(" ");
        int indexToStart = 0;
        int indexToEnd = 0;
        for (int i = 0; i < str.length; i++){
            if (isPreposition(str[i])){
                indexToStart = textOfTelegram.indexOf(str[i], indexToEnd);
                indexToEnd = indexToStart + str[i].length();
                textOfTelegram.delete(indexToStart, indexToEnd);
            }
            indexToEnd += str[i].length();
        }
    }
    
    private boolean isPreposition(String str){
        for (int i = 0; i < prepositions.length; i++){
            if (prepositions[i].equals(str))
                return true;
        }
        
        return false;
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

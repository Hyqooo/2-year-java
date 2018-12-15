package testwork2larina;


public class Telegram {
    private StringBuilder text;
    private int pricePerWord;
    private final static int BLANKPRICE = 5; 
    
    public Telegram(String text) throws MissPeriodException{
        if (text.charAt(text.length() - 1) != '.')
            throw new MissPeriodException();
        
        this.text = new StringBuilder(text);
        parseToTelegram();
    }
    
    private void parseToTelegram(){
        switch (text.toString().split(" ")[0]){
            case "Urgent":
                pricePerWord = 4;
                break;
            case "Govermental":
                pricePerWord = 6;
                break;
            case "International":
                pricePerWord = 8;
                break;
            case "Artistic":
                pricePerWord = 4;
                break;
            default:
                pricePerWord = 2;
                break;
        }
        
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == ','){
                text.setCharAt(i, ' ');
                text.insert(i + 1, "зпт ");
            }
            if (text.charAt(i) == '.'){
                text.setCharAt(i, ' ');
                text.insert(i + 1, "тчк");
            }
        }
    }
    
    public int evaluatePrice(){
        return lengthOfTelegram() * pricePerWord + blankPrice();
    }
    
    public int lengthOfTelegram(){
        return text.toString().split(" ").length;
    }
    
    private int blankPrice(){
        if (text.toString().split(" ")[0].equals("Urgent"))
            return BLANKPRICE;
        return 0;
    }
    
    @Override
    public String toString(){
        return text.toString();
    }
}

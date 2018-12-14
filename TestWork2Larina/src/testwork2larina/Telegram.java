package testwork2larina;


public class Telegram {
    StringBuilder text;
    int pricePerWord;
    
    public Telegram(String text){
        if (text.charAt(text.length() - 1) != '.')
            // Throws miss '.'
            System.out.println("Tochka");
        
        this.text = new StringBuilder(text);
        parseToTelegram();
    }
    
    private void parseToTelegram(){
        if (text.toString().split(" ")[0].equals("Sroch"))
            pricePerWord = 4;
        else
            pricePerWord = 2;
        
        
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
        return lengthOfTelegram() * pricePerWord;
    }
    
    public int lengthOfTelegram(){
        return text.toString().split(" ").length;
    }
    
    @Override
    public String toString(){
        return text.toString();
    }
}

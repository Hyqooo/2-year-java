package testwork2larina;


public class Telegram {
    StringBuilder text;
    
    public Telegram(String text){
        if (text.charAt(text.length() - 1) != '.')
            // Throws miss '.'
            System.out.println("Tochka");
        
        this.text = new StringBuilder(text);
        parseToTelegram();
    }
    
    private void parseToTelegram(){
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
    
    @Override
    public String toString(){
        return text.toString();
    }
}

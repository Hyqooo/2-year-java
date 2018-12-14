package testwork2larina;


public class Telegram {
    StringBuilder text;
    
    public Telegram(String text){
        if (text.charAt(text.length() - 1) != '.')
            // Throws miss '.'
            System.out.println("Tochka");
        
        this.text = new StringBuilder(text);
    }
    
    @Override
    public String toString(){
        return text.toString();
    }
}

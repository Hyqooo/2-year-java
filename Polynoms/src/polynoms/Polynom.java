package polynoms;

import java.util.ArrayList;

public class Polynom {
    double coef[];
    int power;
    
    public Polynom(int power, double coef[]){
        this.coef = new double[power + 1];
        for (int i = 0; i < power + 1; i++)
            this.coef[i] = coef[power - i];
        this.power = power;
    }
    
    public int getPower(){
        return power;
    }
    
    public double getCoeff(int index){
        return coef[index];
    }
    
    @Override
    public String toString(){
        String str = "";
        if (coef[power] != 0)
            str += coef[power] + "x^" + (power);
        
        for (int i = power - 1; i != 0; i--){
            if (coef[i] != 0)
                str += "+" + coef[i] + "x^" + (i);
        }
        
       
        str += "+" + coef[0];
        
        return str;
    }
}

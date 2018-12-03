package polynoms;


public class Cases {
    public static Polynom sum(Polynom pol_1, Polynom pol_2){
        Polynom biggerPolynom;
        Polynom lessPolynom;
        if (pol_1.getPower() > pol_2.getPower()){
            biggerPolynom = pol_1;
            lessPolynom = pol_2;
        }else{
            biggerPolynom = pol_2;
            lessPolynom = pol_1;
        }

        double []sumPolynom = new double[biggerPolynom.getPower() + 1];
        for (int i = 0; i < biggerPolynom.getPower() + 1; i++)
            sumPolynom[i] = 0;
        
        for (int i = 0; i < lessPolynom.getPower() + 1; i++){
            sumPolynom[i] += biggerPolynom.getCoeff(i) + lessPolynom.getCoeff(i);
        }
        
        for (int i = lessPolynom.getPower() + 1; i < biggerPolynom.getPower() + 1; i++){
            sumPolynom[i] += biggerPolynom.getCoeff(i);
        }
        
        for (int i = 0; i < biggerPolynom.getPower() / 2; i++){
            double temp = sumPolynom[i];
            sumPolynom[i] = sumPolynom[biggerPolynom.getPower() - i];
            sumPolynom[biggerPolynom.getPower() - i] = temp;
        }
        
        return new Polynom(biggerPolynom.getPower(), sumPolynom);
    }
    
    // Pol_1 - Pol_2
    public static Polynom subtraction(Polynom pol_1, Polynom pol_2){
        Polynom biggerPolynom;
        Polynom lessPolynom;
        if (pol_1.getPower() > pol_2.getPower()){
            biggerPolynom = pol_1;
            lessPolynom = pol_2;
        }else{
            biggerPolynom = pol_2;
            lessPolynom = pol_1;
        }

        double []sumPolynom = new double[biggerPolynom.getPower() + 1];
        for (int i = 0; i < biggerPolynom.getPower() + 1; i++)
            sumPolynom[i] = 0;
        
        for (int i = 0; i < lessPolynom.getPower() + 1; i++){
            sumPolynom[i] += pol_1.getCoeff(i) - pol_2.getCoeff(i);
        }
        
        if (biggerPolynom.equals(pol_1)){
            for (int i = lessPolynom.getPower() + 1; i < biggerPolynom.getPower() + 1; i++)
                sumPolynom[i] += biggerPolynom.getCoeff(i);
        }else{
            for (int i = lessPolynom.getPower() + 1; i < biggerPolynom.getPower() + 1; i++)
                sumPolynom[i] -= biggerPolynom.getCoeff(i);
        }
        
        for (int i = 0; i < (biggerPolynom.getPower() / 2) + 1; i++){
            double temp = sumPolynom[i];
            sumPolynom[i] = sumPolynom[biggerPolynom.getPower() - i];
            sumPolynom[biggerPolynom.getPower() - i] = temp;
        }
        
        return new Polynom(biggerPolynom.getPower(), sumPolynom);
    }
    
    public static Polynom product(Polynom pol_1, Polynom pol_2){
        int newPower = pol_1.getPower() + pol_2.getPower();
        double []productPolynom = new double[newPower + 1];
        
        for (int i = 0; i < newPower + 1; i++)
            productPolynom[i] = 0;
        
        for (int i = 0; i < pol_1.getPower() + 1; i++){
            for (int j = 0; j < pol_2.getPower() + 1; j++){
                int index = (pol_1.getPower() - i) + (pol_2.getPower() - j);
                productPolynom[index] += pol_1.getCoeff(i) * pol_2.getCoeff(j);
            }
        }
        
        return new Polynom(newPower, productPolynom);
    }
    
    public static void ValuesBeetwenAB(Polynom polynom, double a, double b, double step){
        System.out.println("Table from " + a + " to " + b);
        for (double i = a; i < b; i += step){
            System.out.println("At value " + i + ": " + polynom.valueAtPoint(i));
        }
    }
}

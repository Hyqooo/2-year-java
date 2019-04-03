package classes;

public class Classes {
    public static void main(String[] args) {
//        Person person_1 = new Person("Дима", "Мужской", 18);
//        Person person_2 = new Person("Андрей", "Мужской", 19);
//        System.out.println("1." + person_1);
//        System.out.println("2." + person_2);
    }
}

class Person {
    String name;
    String gender;
    int age;
    
    public Person(String name_c, String gender_c, int age_c){
        name = name_c;
        gender = gender_c;
        age = age_c;
    }
    
    @Override
    public String toString(){
        return "Имя: " + name + "\nПол: " + gender + "\nВозраст: " + age;
    }
}

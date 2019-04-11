package maintest;

public class Maintest {

    public static void main(String[] args) {
        int arr[][] = {{1, 2}, {3, 4}};
        
        
        System.out.println(arr.length);
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}

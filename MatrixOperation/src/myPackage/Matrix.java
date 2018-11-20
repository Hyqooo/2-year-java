package myPackage;

import myException.nonPositiveSizeException;
import myException.EvenNumberException;

public class Matrix {

    private int matrix[][];
    private int size;

    public Matrix(int size) throws  nonPositiveSizeException, EvenNumberException {
        if (size <= 0)
            throw new nonPositiveSizeException();
        if (size % 2 != 1)
            throw new EvenNumberException();

        this.size = size;
        matrix = new int[size][size];
    }

    public Matrix() throws Exception {
        this (1);
    }

    public Matrix(Matrix a){
        size = a.size;

        matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                matrix[i][j] = a.get(i, j);
            }
        }
    }

    // It manages all things
    public void ExchangeWithMiddle(){
        findMax();
    }

    // Swaps max value with middle value in the matrix
    private void swap(int i, int j){
        int middleIndex = size / 2;
        int temp;

        temp = matrix[i][j];
        matrix[i][j] = matrix[middleIndex][middleIndex];
        matrix[middleIndex][middleIndex] = temp;
    }

    // Search for maximum value in the matrix
    private void findMax(){
        int maxValue = matrix[0][0];
        int row = 0, column = 0;
        for (int i = 0; i < size; i++){
            if (maxValue < matrix[i][i]) {
                maxValue = matrix[i][i];
                row = i;
                column = i;
            }

            if (maxValue < matrix[i][size - i - 1]){
                maxValue = matrix[i][size - i - 1];
                row = i;
                column = size - i - 1;
            }
        }

        swap(row, column);
    }

    public int get(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, int value) {
        matrix[i][j] = value;
    }

    @Override
    public String toString() {
        String str = new String();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                str += matrix[i][j] + " ";
            }
            str += '\n';
        }

        return str;
    }

    @Override
    public int hashCode() {
        int sum = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += matrix[i][j];
            }
        }

        return sum + size * size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Matrix))
            return false;

        Matrix a = (Matrix)obj;
        if (size != a.size)
            return false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != a.get(i, j))
                    return false;
            }
        }

        return true;
    }
}

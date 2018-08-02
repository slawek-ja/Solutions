package pl.coderstrust.multiplication;

public class MultiplicationTable {
    public static int[][] getMultiplicationTable(int size) {
        if (size < 1){
            return new int[0][0];
        }
        int row = 1;
        int column = 1;
        int[][] result = new int[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                result[row-1][column-1] = row*column;
                column++;
            }
            row++;
            column = 1;
        }
        return result;
    }
}

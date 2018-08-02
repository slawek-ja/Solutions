package pl.coderstrust.multiplication;

public class MultiplicationTable {
    public static int[][] getMultiplicationTable(int size) {
        if (size < 1){
            return new int[0][0];
        }
        int row = 1;
        int column = 1;
        int[][] result = new int[size][size];
        //Printing given table
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(String.format("%6s", i));
        }
        System.out.print("\n\n");
        for (int i = 1; i <= size; i++) {
            System.out.print(String.format("%2s", i));
            for (int j = 1; j <= size; j++) {
                System.out.print(String.format("%6s", row * column));
                result[row-1][column-1] = row*column;
                column++;
            }
            row++;
            column = 1;
            System.out.print("\n\n");
        }
        return result;
    }
}

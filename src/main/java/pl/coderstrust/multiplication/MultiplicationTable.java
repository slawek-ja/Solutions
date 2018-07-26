package pl.coderstrust.multiplication;

public class MultiplicationTable
{
    public static void printMultiplicationTable(int size)
    {
        int row = 1;
        int column = 1;
        System.out.print("  ");
        for (int i = 1; i <= size; i++)
        {
            System.out.print(String.format("%6s", i));
        }
        System.out.print("\n\n");
        for (int i = 1; i <= size; i++)
        {
            System.out.print(String.format("%2s", i));
            for (int j = 1; j <= size; j++)
            {
                System.out.print(String.format("%6s", row * column));
                column++;
            }
            row++;
            column = 1;
            System.out.print("\n\n");
        }
    }
}

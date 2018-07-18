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
            System.out.print(makeSpaces(i) + i);
        }
        System.out.println();
        System.out.println();
        for (int i = 1; i <= size; i++)
        {
            if (i >= 10)
            {
                System.out.print(i);
            }
            else
            {
                System.out.print(" " + i);
            }
            for (int j = 1; j <= size; j++)
            {
                System.out.print(makeSpaces(row * column) + row * column);
                column++;
            }
            row++;
            column = 1;
            System.out.println();
            System.out.println();
        }
    }

    public static String makeSpaces(int number)
    {
        String spaces = " ";
        int howManySpaces = 5;
        if (number >= 10)
        {
            if (number >= 100)
            {
                howManySpaces -= 2;
            }
            else
            {
                howManySpaces -= 1;
            }
        }
        for (int i = 1; i < howManySpaces; i++)
        {
            spaces += " ";
        }
        return spaces;
    }
}

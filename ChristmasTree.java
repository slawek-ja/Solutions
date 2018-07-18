package pl.coderstrust.christmas;

public class ChristmasTree
{
    public static void printChristmasTree(int size)
    {
        int spaces = size;
        int stars = 1;
        for (int i = 0; i < size; i++)
        {
            for (int j = spaces; j > 0; j--)
            {
                System.out.print(" ");
            }
            for (int j = 0; j < stars; j++)
            {
                System.out.print("*");
            }
            System.out.println();
            stars += 2;
            spaces -= 1;
        }
        for (int i = 0; i < size - 1; i++)
        {
            System.out.print(" ");
        }
        System.out.print("**");
    }
}

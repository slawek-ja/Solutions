package pl.coderstrust.pascal;

public class PascalTriangle
{
    public static void printPascalTriangle(int howManyRows)
    {
        int result;
        for(int i = 0; i < howManyRows; i++)
        {
            for(int j = i; j <= howManyRows; j++)
            {
                System.out.print(String.format("%3s",""));
            }
            for(int k = 0; k <= i; k++)
            {
                result = factorial(i) / (factorial(k) * factorial(i-k));
                System.out.print(String.format("%6s",result));
            }
            System.out.println();
        }
    }
    private static int factorial(int number)
    {
        int factorial = 1;
        while(number >= 1)
        {
            factorial *= number;
            number--;
        }
        return factorial;
    }
}

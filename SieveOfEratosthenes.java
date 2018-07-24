package pl.coderstrust.sieve;

public class SieveOfEratosthenes
{
    public static int[] sieve(int maximumNumber)
    {
        int size = 0;
        for (int i = 2; i < maximumNumber; i++)
        {
            if (isPrime(i))
            {
                size++;
            }
        }
        int [] array = new int[size];
        int array_index = 0;
        for (int i = 2; i < maximumNumber; i++)
        {
            if (isPrime(i))
            {
                array[array_index] = i;
                array_index++;
            }
        }
        return array;
    }
    private static boolean isPrime(int num)
    {
        for (int i = 2; i < num; i++)
        {
            if (num % i == 0)
            {
                return false;
            }
        }
        return true;
    }
}

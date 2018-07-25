package pl.coderstrust.sieve;

public class SieveOfEratosthenes
{
    public static int[] sieve(int maximumNumber)
    {
        if(maximumNumber <= 2) System.out.print(0);

        int tab[] = new int[maximumNumber-2];
        int next_num = 2;
        int find_index = 1;

        for (int i = 0; i < tab.length; i++)
        {
            tab[i] = i+2;
        }

        while (next_num < maximumNumber)
        {
            for (int i = find_index+1; i < tab.length; i++)
            {
                if(tab[i] % next_num == 0)
                {
                    tab[i] = 0;
                }
            }
            next_num++;
            for (int i = 0; i < tab.length; i++)
            {
                if (tab[i] >= next_num)
                {
                    next_num = tab[i];
                    find_index = i;
                    break;
                }
            }
        }
        int newTableLenght = 0;
        for (int i = 0; i < tab.length; i++)
        {
            if (tab[i] != 0)
            {
                newTableLenght++;
            }
        }
        int resultTab[] = new int[newTableLenght];
        find_index = 0;
        for (int i = 0; i < tab.length; i++)
        {
            if(tab[i] != 0)
            {
                resultTab[find_index] = tab[i];
                find_index++;
            }
        }
        return resultTab;
    }
}

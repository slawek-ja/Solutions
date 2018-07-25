package pl.coderstrust.sort;

public class SelectionSort
{
    public static int[] sort(int[] array)
    {
        int tab [] = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            tab[i] = array[i];
        }
        int min_val = 0;
        int last_index = 0;
        int buffor = 0;
        for (int i = 0; i < tab.length; i++)
        {
            min_val = tab[i];
            for (int j = i; j < tab.length; j++)
            {
                if (tab[j] < min_val)
                {
                    min_val = tab[j];
                    last_index = j;
                }
            }
            if(tab[i] != min_val)
            {
                buffor = tab[i];
                tab[i] = min_val;
                tab[last_index] = buffor;
            }
        }
        return tab;
    }
}

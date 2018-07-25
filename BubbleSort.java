package pl.coderstrust.sort;

public class BubbleSort
{
    public static int[] sort(int[] array)
    {
        int [] tab = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            tab[i] = array[i];
        }
        int buff = 0;
        for (int i = 0; i < tab.length; i++)
        {
            for (int j = 0; j < tab.length-1; j++)
            {
                if(tab[j] > tab[j+1])
                {
                    buff = tab[j];
                    tab[j] = tab[j+1];
                    tab[j+1] = buff;
                }
            }
        }
        return tab;
    }
}

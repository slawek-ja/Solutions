package pl.coderstrust.sort;
import java.util.Arrays;

public class SelectionSort implements SortingMethod {
    public int[] sort(int[] array) {
        int tab[] = Arrays.copyOf(array, array.length);
        int minVal = 0;
        int lastIndex = 0;
        int buffor = 0;
        for (int i = 0; i < tab.length; i++) {
            minVal = tab[i];
            for (int j = i; j < tab.length; j++) {
                if (tab[j] < minVal) {
                    minVal = tab[j];
                    lastIndex = j;
                }
            }
            if (tab[i] != minVal) {
                buffor = tab[i];
                tab[i] = minVal;
                tab[lastIndex] = buffor;
            }
        }
        return tab;
    }
}

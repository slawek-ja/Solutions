package pl.coderstrust.sort;
import java.util.Arrays;

public class BubbleSort {
    public static int[] sort(int[] array) {
        int[] tab = Arrays.copyOf(array, array.length);
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length - 1; j++) {
                if (tab[j] > tab[j + 1]) {
                    swapElemntsInArray(tab, j, j + 1);
                }
            }
        }
        return tab;
    }

    private static int[] swapElemntsInArray(int[] arrayToSwap, int indexOfFirstElement, int indexOfSecondElement) {
        int buff = 0;
        buff = arrayToSwap[indexOfFirstElement];
        arrayToSwap[indexOfFirstElement] = arrayToSwap[indexOfSecondElement];
        arrayToSwap[indexOfSecondElement] = buff;
        return arrayToSwap;
    }
}

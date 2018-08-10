package pl.coderstrust.sort;

import java.util.Arrays;

public class InsertionSort implements SortingMethod {
    public int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        for (int i = 1; i < array.length; i++) {
            int key = result[i];
            int j = i-1;
            while (j>=0 && result[j] > key)
            {
                result[j+1] = result[j];
                j = j-1;
            }
            result[j+1] = key;
        }
        return result;
    }
}

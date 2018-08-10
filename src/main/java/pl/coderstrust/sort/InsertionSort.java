package pl.coderstrust.sort;

import java.util.Arrays;

public class InsertionSort implements SortingMethod {
    public int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        for (int i = 1; i < array.length; i++) {
            int key = result[i];
            int pushNum = i-1;
            while (pushNum >= 0 && result[pushNum] > key) {
                result[pushNum+1] = result[pushNum];
                pushNum = pushNum-1;
            }
            result[pushNum+1] = key;
        }
        return result;
    }
}

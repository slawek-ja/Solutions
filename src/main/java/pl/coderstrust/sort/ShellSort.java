package pl.coderstrust.sort;

import java.util.Arrays;

public class ShellSort implements SortingMethod {
    public int[] sort(int array[]) {
        int[] result = Arrays.copyOf(array, array.length);
        int arrLength = result.length;
        int index = 0;
        int buff = 0;
        for (int gap = arrLength / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arrLength; i += 1) {
                buff = result[i];
                for (index = i; index >= gap && result[index - gap] > buff; index -= gap) {
                    result[index] = result[index - gap];
                }
                result[index] = buff;
            }
        }
        return result;
    }
}

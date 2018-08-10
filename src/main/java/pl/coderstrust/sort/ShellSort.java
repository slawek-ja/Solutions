package pl.coderstrust.sort;

import java.util.Arrays;

public class ShellSort implements SortingMethod {
    public int[] sort(int array[]) {
        int[] result = Arrays.copyOf(array, array.length);
        int n = result.length;
        int a = 0;
        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int buff = result[i];
                for (a = i; a >= gap && result[a - gap] > buff; a -= gap) {
                    result[a] = result[a - gap];
                }
                result[a] = buff;
            }
        }
        return result;
    }
}

package pl.coderstrust.search;

public class LinearSearch implements SearchingMethod {
    public int search(int[] array, int element) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty. Cannot use searching method");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }
}

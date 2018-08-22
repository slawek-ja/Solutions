package pl.coderstrust.search;

public class BinarySearch implements SearchingMethod {
    public int search(int[] array, int element) {
        if (array.length == 0) {
            throw new IllegalStateException("Array is empty. Cannot use searching method");
        }
        if (array == null) {
            throw new NullPointerException();
        }
        int leftAnchor = 0;
        int rightAnchor = array.length - 1;
        int pointer;
        while (leftAnchor <= rightAnchor) {
            pointer = (leftAnchor + rightAnchor) / 2;
            if (array[pointer] == element) {
                while (pointer > 0 && array[pointer] == array[pointer - 1]) {
                    pointer--;
                }
                return pointer;
            }
            if (array[pointer] < element) {
                leftAnchor = pointer + 1;
            } else {
                rightAnchor = pointer - 1;
            }
        }
        return -1;
    }
}

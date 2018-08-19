package pl.coderstrust.search;

import java.util.Arrays;

public class BinarySearch {
    public static int search(int[] array, int element) {
        Arrays.sort(array);
        int leftAnchor = 0;
        int rightAnchor = array.length-1;
        int pointer;
        while (leftAnchor <= rightAnchor) {
            pointer = (leftAnchor + rightAnchor) / 2;
            if (array[pointer] == element) {
                return pointer;
            }
            if (array[pointer] < element) {
                leftAnchor = pointer + 1;
            }
            else if (rightAnchor > element) {
                rightAnchor = pointer - 1;
            }
        }
        return -1;
    }
}

package pl.coderstrust.sort;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionSort implements SortingMethod {
    public int[] sort(int[] array) {
        ArrayList<Integer> storage = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            storage.add(i, array[i]);
        }
        Collections.sort(storage);
        int[] result = new int[storage.size()];
        for (int i = 0; i < storage.size(); i++) {
            result[i] = storage.get(i);
        }
        return result;
    }
}

package pl.coderstrust.christmas;

import java.util.ArrayList;
import java.util.Collections;

public class ChristmasTree {
    public static ArrayList<String> getChristmasTree(int size) {
        if (size < 3) {
            return new ArrayList<String>(Collections.singletonList("Invalid Value"));
        }
        int spaces = size;
        int stars = 1;
        StringBuilder storage = new StringBuilder();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            for (int j = spaces; j > 0; j--) {
                storage.append(" ");
            }
            for (int j = 0; j < stars; j++) {
                storage.append("*");
            }
            stars += 2;
            spaces -= 1;
            result.add(storage.toString());
            storage.delete(0, storage.length());
        }
        for (int i = 0; i < size - 1; i++) {
            storage.append(" ");
        }
        storage.append("**");
        result.add(storage.toString());
        return result;
    }
}

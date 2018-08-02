package pl.coderstrust.pascal;

import java.util.ArrayList;
import java.util.Collections;

public class PascalTriangle {
    public static ArrayList<String> getPascalTriangle(int howManyRows) {
        if (howManyRows <= 1) {
            if (howManyRows == 1) {
                return new ArrayList<String>(Collections.singletonList("1"));
            }
            else {
                return new ArrayList<String>(Collections.singletonList("Invalid Value"));
            }
        }
        int resultFactorial;
        StringBuilder storage = new StringBuilder();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < howManyRows; i++) {
            for (int k = 0; k <= i; k++) {
                resultFactorial = factorial(i) / (factorial(k) * factorial(i-k));
                storage.append(resultFactorial + " ");
            }
            result.add(storage.toString());
            storage.delete(0, storage.length());
        }
        return result;
    }

    private static int factorial(int number) {
        int factorial = 1;
        while(number >= 1) {
            factorial *= number;
            number--;
        }
        return factorial;
    }
}

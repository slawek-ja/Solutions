package pl.coderstrust.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciChecker {
    private static Map<Long, Boolean> storage = new HashMap<>();

    public static boolean isFibonacciNumber(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Numbers below 0 are not allowed");
        }
        if (storage.containsKey(number)) {
            return storage.get(number);
        }
        boolean isNumberPerfectSquare = isPerfectSquare(number);
        storage.put(number, isNumberPerfectSquare);
        return isNumberPerfectSquare;
    }

    private static boolean isPerfectSquare(long number) {
        int value = checkValue(number);
        long sqrt = (long) Math.sqrt(5 * number * number + value);
        return (sqrt * sqrt == 5 * number * number + value);
    }

    private static int checkValue(long number) {
        long sqrt = (long) Math.sqrt(5 * number * number + 4);
        if (sqrt * sqrt == 5 * number * number + 4){
            return 4;
        } else {
            return -4;
        }
    }
}

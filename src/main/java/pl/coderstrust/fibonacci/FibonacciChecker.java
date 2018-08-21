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
        long formula1 = calculateFibonacciNumberFormula(number, false);
        long formula2 = calculateFibonacciNumberFormula(number, true);
        boolean isNumberPerfectSquare = isPerfectSquare(formula1) || isPerfectSquare(formula2);
        storage.put(number, isNumberPerfectSquare);
        return isNumberPerfectSquare;
    }

    private static boolean isPerfectSquare(long number) {
        int value = (int) Math.sqrt(number);
        return value * value == number;
    }

    private static long calculateFibonacciNumberFormula(long number, boolean plus) {
        long value = number * number * 5;
        long offset = 4;
        if (plus) {
            return value + offset;
        }
        return value - offset;
    }
}

package pl.coderstrust.javaIO;

import java.util.Scanner;

public class NumbersProcessor {
    public String processLine(String line) {
        if (!isLineValid(line)) {
            return "";
        }
        int sumOfNumbers = 0;
        int nextNumber = 0;
        Scanner scanner = new Scanner(line);
        StringBuilder result = new StringBuilder();
        while (scanner.hasNextInt()) {
            nextNumber = scanner.nextInt();
            sumOfNumbers += nextNumber;
            if (nextNumber < 0) {
                result.append(String.format("%(d", nextNumber));
            } else {
                result.append(nextNumber);
            }
            if (scanner.hasNextInt()) {
                result.append("+");
            }
        }
        return result.toString() + "=" + sumOfNumbers;
    }

    private boolean isLineValid(String line) {
        if (line.equals("") || !line.matches("[\\d+\\s-*]*")) {
            return false;
        }
        Scanner scanner = new Scanner(line);
        boolean negativeNumbers;
        while (scanner.hasNext()) {
            negativeNumbers = scanner.hasNextInt();
            scanner.next();
            if (!negativeNumbers) {
                return false;
            }
        }
        return true;
    }
}

package pl.coderstrust.io;

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
                result.append(String.format("(%d)", nextNumber));
            } else {
                result.append(nextNumber);
            }
            if (scanner.hasNextInt()) {
                result.append("+");
            }
        }
        return String.format("%s=%s", result.toString(), sumOfNumbers);
    }

    private boolean isLineValid(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        if (line.trim().isEmpty()) {
            return false;
        }
        Scanner scanner = new Scanner(line);
        while (scanner.hasNext()) {
            if (!scanner.hasNextInt()) {
                return false;
            }
            scanner.next();
        }
        return true;
    }
}

package pl.coderstrust.javaIO;

import java.util.Scanner;

public class NumbersProcessor {
    public String processLine(String line) {
        if (!lineValidationChecker(line)) {
            return line;
        }
        int sum = 0;
        int buffor = 0;
        Scanner lineProcess = new Scanner(line);
        StringBuilder result = new StringBuilder();
        while (lineProcess.hasNextInt()) {
            buffor = lineProcess.nextInt();
            sum += buffor;
            if (buffor < 0) {
                result.append("(" + buffor + ")+");
                continue;
            }
            result.append(buffor + "+");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString() + "=" + sum;
    }

    private boolean lineValidationChecker(String line) {
        Scanner lineChecker = new Scanner(line);
        Scanner intChecker = new Scanner(line);
        if (!lineChecker.hasNext()) {
            return lineChecker.hasNext();
        }
        while (lineChecker.hasNext()) {
            if (!intChecker.hasNextInt() && lineChecker.hasNext()) {
                return intChecker.hasNextInt();
            }
            lineChecker.next();
            intChecker.next();
        }
        return true;
    }
}

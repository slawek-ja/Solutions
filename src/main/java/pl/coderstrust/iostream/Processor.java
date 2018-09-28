package pl.coderstrust.iostream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Processor {

    private List<Integer> lines = new ArrayList<>();
    private int emptyLineNumber = 0;

    public void process(String fileName, String resultFileName) throws IOException {
        validateStringParameter(fileName, "Input");
        validateStringParameter(resultFileName, "Output");
        List<String> resultToWrite = new ArrayList<>();
        Files.lines(Paths.get(fileName))
                .filter(line -> {
                    emptyLineNumber++;
                    if (!line.matches("^(-[\\d]+|[\\d\\s]+)+") || line.trim().isEmpty()) {
                        lines.add(emptyLineNumber);
                        return false;
                    }
                    return true;
                })
                .map(line -> line.trim().split("[\\s]+"))
                .forEach(arrayOfNumber -> {
                    String numbers = Arrays.stream(arrayOfNumber)
                            .reduce((num1, num2) -> {
                                if (num1.matches("-[\\d]+")) {
                                    return String.format("(%s)+%s", num1, num2);
                                }
                                if (num2.matches("-[\\d]+")) {
                                    return String.format("%s+(%s)", num1, num2);
                                }
                                if (num1.matches("-[\\d]+") && num2.matches("-[\\d]+")) {
                                    return String.format("(%s)+(%s)", num1, num2);
                                }
                                return String.format("%s+%s", num1, num2);
                            })
                            .get();
                    long sumOfNumbers = Arrays.stream(arrayOfNumber)
                            .mapToLong(Long::parseLong)
                            .sum();
                    resultToWrite.add(String.format("%s=%s", numbers, sumOfNumbers));
                });
        lines.forEach(lineNumber -> resultToWrite.add(lineNumber - 1, ""));
        writeLinesToFile(resultToWrite, resultFileName);
    }

    private void validateStringParameter(String paramValue, String paramName) {
        if (paramValue == null) {
            throw new IllegalArgumentException(String.format("%s file path cannot be null", paramName));
        }
        if (paramValue.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s file path is empty", paramName));
        }
    }

    private void writeLinesToFile(List<String> lines, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < lines.size(); i++) {
            writer.write(lines.get(i));
            writer.newLine();
        }
        writer.close();
    }
}

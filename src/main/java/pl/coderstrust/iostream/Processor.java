package pl.coderstrust.iostream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Processor {

    private FileProcessor fileProcessor;

    public Processor(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    public void process(String fileName, String resultFileName) throws IOException {
        if (!isPathValid(fileName, resultFileName)) {
            throw new IllegalArgumentException("Invalid file path");
        }
        List<String> resultToWrite = new ArrayList<>();
        Stream<String> linesFromFile = fileProcessor.readLinesFromFile(fileName);
        linesFromFile
                .filter(w -> w.matches("^[1-9\\s]+") && !w.isEmpty())
                .map(String::trim)
                .map(w -> w.split("[\\s]+"))
                .forEach(arrayNumbers -> {
                    String numbers = processLine(arrayNumbers);
                    long sum = calculateSum(arrayNumbers);
                    resultToWrite.add(String.format("%s=%s", numbers, sum));
                });
        fileProcessor.writeLinesToFile(resultToWrite, resultFileName);
    }

    private boolean isPathValid(String fileName, String resultFileName) {
        if (fileName == null || resultFileName == null || fileName.isEmpty() || resultFileName.isEmpty()) {
            return false;
        }
        return true;
    }

    private String processLine(String[] arrayOfStrings) {
        return Arrays.stream(arrayOfStrings)
                .reduce((num1, num2) -> String.format("%s+%s", num1, num2))
                .get();
    }

    private long calculateSum(String[] arrayOfStrings) {
        return Arrays.stream(arrayOfStrings)
                .mapToLong(Long::parseLong)
                .sum();
    }
}

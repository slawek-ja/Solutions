package pl.coderstrust.iostream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Processor {
    public void process(String fileName, String resultFileName) throws IOException {
        List<String> resultToWrite = new ArrayList<>();
        Stream<String> linesFromFile = readLinesFromFile(fileName);
        linesFromFile
                .filter(w -> w.matches("^[1-9\\s]+"))
                .map(String::trim)
                .filter(w -> !w.isEmpty())
                .map(w -> w.split("[\\s]+"))
                .forEach(arrayNumbers -> {
                    String numbers = processLine(arrayNumbers);
                    long sum = calculateSum(arrayNumbers);
                    resultToWrite.add(String.format("%s=%s", numbers, sum));
                });
        try {
            writeLinesToFile(resultToWrite, resultFileName);
        } catch (Exception exception) {
            exception.getCause();
        }

    }

    private Stream<String> readLinesFromFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath));
    }

    private String processLine(String[] arrayOfStrings) {
        return Arrays.stream(arrayOfStrings)
                .reduce((num1, num2) -> String.format("%s+%s", num1, num2))
                .get();
    }

    private long calculateSum(String[] arrayOfStrings) {
        return Arrays.stream(arrayOfStrings)
                .mapToLong(Long::parseLong).sum();
    }

    private void writeLinesToFile(List<String> givenLines, String outputFilePath) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        givenLines.forEach(lines -> {
            try {
                writer.write(lines);
                writer.newLine();
            } catch (IOException exception) {
                exception.getCause();
            }
        });
        writer.close();
    }
}

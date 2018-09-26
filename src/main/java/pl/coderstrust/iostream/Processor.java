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

    private List<Integer> collectEmptyLines = new ArrayList<>();
    private int emptyLineNumber = 0;

    public void process(String fileName, String resultFileName) throws IOException {
        isInputPathValid(fileName);
        isOutputPathValid(resultFileName);
        List<String> resultToWrite = new ArrayList<>();
        Stream<String> linesFromFile = Files.lines(Paths.get(fileName));
        linesFromFile
                .filter(w -> !lineIsEmpty(w) && w.matches("^[\\d\\s]+"))
                .map(w -> w.trim().split("[\\s]+"))
                .forEach(arrayNumbers -> {
                    String numbers = Arrays.stream(arrayNumbers)
                            .reduce((num1, num2) -> String.format("%s+%s", num1, num2))
                            .get();
                    long sum = Arrays.stream(arrayNumbers)
                            .mapToLong(Long::parseLong)
                            .sum();
                    resultToWrite.add(String.format("%s=%s", numbers, sum));
                });
        writeLinesToFile(resultToWrite, resultFileName);
    }

    private boolean lineIsEmpty(String line){
        emptyLineNumber ++;
        if (line.trim().isEmpty()) {
            System.out.println(emptyLineNumber+" tu");
            collectEmptyLines.add(emptyLineNumber);
            return false;
        }
        return false;
    }

    private boolean isInputPathValid(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("Input file path cannot be null");
        }
        if (fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Input file path is empty");
        }
        return true;
    }

    private boolean isOutputPathValid(String resultFileName) {
        if (resultFileName == null) {
            throw new IllegalArgumentException("Output file path cannot be null");
        }
        if (resultFileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Output file path is empty");
        }
        return true;
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

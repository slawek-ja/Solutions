package pl.coderstrust.iostream;

import pl.coderstrust.utils.ParameterValidator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Processor {

    private ParameterValidator parameterValidator;

    public Processor(ParameterValidator parameterValidator) {
        if (parameterValidator == null) {
            throw new NullPointerException("ParameterValidator cannot be null");
        }
        this.parameterValidator = parameterValidator;
    }
    public void process(String fileName, String resultFileName) throws IOException {
        parameterValidator.validateStringParameter(fileName, "fileName");
        parameterValidator.validateStringParameter(resultFileName, "resultFileName");
        List<String> lines = new ArrayList<>();
        Files.lines(Paths.get(fileName))
                .filter(line -> line.matches("^(-[\\d]+|[\\d\\s]+)+"))
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
                    lines.add(String.format("%s=%s", numbers, sumOfNumbers));
                });
        writeLinesToFile(lines, resultFileName);
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

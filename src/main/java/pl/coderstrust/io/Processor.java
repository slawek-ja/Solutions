package pl.coderstrust.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Processor {
    private NumbersProcessor numbersProcessor;
    private FileProcessor fileProcessor;

    public Processor(NumbersProcessor numbersProcessor, FileProcessor fileProcessor) {
        this.numbersProcessor = numbersProcessor;
        this.fileProcessor = fileProcessor;
    }

    public void process(String fileName, String resultFileName) throws IOException {
        validateStringParameter(fileName, "Input");
        validateStringParameter(resultFileName, "Output");
        List<String> linesFromFile = fileProcessor.readLinesFromFile(fileName);
        List<String> resultLines = new ArrayList<>();
        for (String line : linesFromFile) {
            resultLines.add(numbersProcessor.processLine(line));
        }
        fileProcessor.writeLinesToFile(resultLines, resultFileName);
    }

    private void validateStringParameter(String paramValue, String paramName) {
        if (paramValue == null) {
            throw new IllegalArgumentException(String.format("%s file path cannot be null", paramName));
        }
        if (paramValue.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s file path is empty", paramName));
        }
    }
}

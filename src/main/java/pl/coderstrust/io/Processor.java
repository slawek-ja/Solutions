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
        isInputPathValid(fileName);
        isOutputPathValid(resultFileName);
        List<String> linesFromFile = fileProcessor.readLinesFromFile(fileName);
        List<String> resultLines = new ArrayList<>();
        for (String line : linesFromFile) {
            resultLines.add(numbersProcessor.processLine(line));
        }
        fileProcessor.writeLinesToFile(resultLines, resultFileName);
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
}

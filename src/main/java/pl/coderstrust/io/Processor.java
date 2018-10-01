package pl.coderstrust.io;

import pl.coderstrust.utils.ParameterValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Processor {
    private NumbersProcessor numbersProcessor;
    private FileProcessor fileProcessor;
    private ParameterValidator parameterValidator;

    public Processor(NumbersProcessor numbersProcessor, FileProcessor fileProcessor, ParameterValidator parameterValidator) {
        if (numbersProcessor == null) {
            throw new NullPointerException("NumbersProcessor cannot be null");
        }
        if (fileProcessor == null) {
            throw new NullPointerException("FileProcessor cannot be null");
        }
        if (parameterValidator == null) {
            throw new NullPointerException("ParameterValidator cannot be null");
        }
        this.numbersProcessor = numbersProcessor;
        this.fileProcessor = fileProcessor;
        this.parameterValidator = parameterValidator;
    }

    public void process(String fileName, String resultFileName) throws IOException {
        parameterValidator.validateStringParameter(fileName, "fileName");
        parameterValidator.validateStringParameter(resultFileName, "resultFileName");
        List<String> linesFromFile = fileProcessor.readLinesFromFile(fileName);
        List<String> resultLines = new ArrayList<>();
        for (String line : linesFromFile) {
            resultLines.add(numbersProcessor.processLine(line));
        }
        fileProcessor.writeLinesToFile(resultLines, resultFileName);
    }
}

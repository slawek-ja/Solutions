package pl.coderstrust.io;

import pl.coderstrust.utils.ParameterValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Processor {
    private NumbersProcessor numbersProcessor;
    private FileProcessor fileProcessor;
    private ParameterValidator parameterValidator;

    public Processor(NumbersProcessor numbersProcessor, FileProcessor fileProcessor) {
        this.numbersProcessor = numbersProcessor;
        this.fileProcessor = fileProcessor;
        this.parameterValidator = new ParameterValidator();
    }

    public void process(String fileName, String resultFileName) throws IOException {
        parameterValidator.validateStringParameter(fileName, "File name");
        parameterValidator.validateStringParameter(resultFileName, "Result file name");
        List<String> linesFromFile = fileProcessor.readLinesFromFile(fileName);
        List<String> resultLines = new ArrayList<>();
        for (String line : linesFromFile) {
            resultLines.add(numbersProcessor.processLine(line));
        }
        fileProcessor.writeLinesToFile(resultLines, resultFileName);
    }
}

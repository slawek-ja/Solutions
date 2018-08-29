package pl.coderstrust.javaIO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

public class IntegrationTests {

    @Test
    public void testForCorrectBehaviour() throws Exception {
        //given
        NumbersProcessor numbersProcessor = new NumbersProcessor();
        FileProcessor fileProcessor = new FileProcessor();
        Processor processor = new Processor(numbersProcessor, fileProcessor);
        String inputFilePath = "...\\solutions-7-slawek\\src\\test\\java\\resources\\inputTest";
        String outputFilePath = "...\\solutions-7-slawek\\src\\test\\java\\resources\\outputTest";
        List<String> givenInputResult = fileProcessor.readLinesFromFile(inputFilePath);
        List<String> givenOutputResult = fileProcessor.readLinesFromFile(outputFilePath);
        String bufferLine;

        //when
        processor.process(inputFilePath, outputFilePath);
        for (int i = 0; i < givenInputResult.size(); i++) {
            bufferLine = numbersProcessor.processLine(givenInputResult.get(i));
            givenInputResult.set(i, bufferLine);
        }

        //then
        assertEquals(givenOutputResult, givenInputResult);
    }
}

package pl.coderstrust.io;

import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import pl.coderstrust.iostream.StreamProcessor;
import pl.coderstrust.utils.File;
import java.io.IOException;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class IntegrationTests {

    private static final String outputFilePath = "src\\test\\resources\\test_output.txt";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void removeOutputFile() {
        java.io.File file = new java.io.File(outputFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testForIoProcessorCorrectBehaviour() throws IOException {
        //given
        File readFile = new File();
        NumbersProcessor numbersProcessor = new NumbersProcessor();
        FileProcessor fileProcessor = new FileProcessor();
        Processor processor = new Processor(numbersProcessor, fileProcessor);
        List<String> expected = readFile.read("src\\test\\resources\\test_expected_output.txt");
        String sampleInputFilePath = "src\\test\\resources\\test_input.txt";
        //when
        processor.process(sampleInputFilePath, outputFilePath);
        List<String> processorOutputFile = readFile.read(outputFilePath);

        //then
        assertEquals(expected, processorOutputFile);
    }

    @Test
    public void testForStreamProcessorCorrectBehaviour() throws IOException {
        //given
        File readFile = new File();
        StreamProcessor streamProcessor = new StreamProcessor();
        List<String> expected = readFile.read("src\\test\\resources\\test_expected_output.txt");
        String sampleInputFilePath = "src\\test\\resources\\test_input.txt";
        //when
        streamProcessor.process(sampleInputFilePath, outputFilePath);
        List<String> processorOutputFile = readFile.read(outputFilePath);

        //then
        assertEquals(expected, processorOutputFile);
    }

    @Test
    @Parameters(method = "addValuesForIoProcessorWhenGivenFilePathIsEmptyOrNull")
    public void testForIoProcessorWhenGivenFilePathIsEmpty(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor(new NumbersProcessor(), new FileProcessor());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process(inputFilePath, outputFilePath);
    }

    private Object[] addValuesForIoProcessorWhenGivenFilePathIsEmptyOrNull() {
        return new Object[]{
                new Object[]{"src\\test\\resources\\test_input.txt", "", "Output file path is empty"},
                new Object[]{"", "src\\test\\resources\\test_expected_output.txt", "Input file path is empty"},
                new Object[]{"", "", "Input file path is empty"}
        };
    }

    @Test
    public void testForIoProcessorWhenGivenFilePathIsNull() throws IOException {
        //given
        Processor processor = new Processor(new NumbersProcessor(), new FileProcessor());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Input file path cannot be null");
        processor.process(null, null);
    }

    @Test
    @Parameters(method = "addValuesForIoProcessorWhenGivenFilePathIsEmptyOrNull")
    public void testForStreamProcessorWhenGivenFilePathIsEmpty(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        StreamProcessor streamProcessor = new StreamProcessor();

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        streamProcessor.process(inputFilePath, outputFilePath);
    }

    private Object[] addValuesForStreamProcessorWhenGivenFilePathIsEmptyOrNull() {
        return new Object[]{
                new Object[]{"src\\test\\resources\\test_input.txt", "", "Output file path is empty"},
                new Object[]{"", "src\\test\\resources\\test_expected_output.txt", "Input file path is empty"},
                new Object[]{"", "", "Input file path is empty"}
        };
    }

    @Test
    public void testForStreamProcessorWhenGivenFilePathIsNull() throws IOException {
        //given
        StreamProcessor streamProcessor = new StreamProcessor();

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Input file path cannot be null");
        streamProcessor.process(null, null);
    }
}

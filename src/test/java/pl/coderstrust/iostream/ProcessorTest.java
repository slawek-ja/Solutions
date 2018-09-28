package pl.coderstrust.iostream;

import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import pl.coderstrust.utils.File;
import java.io.IOException;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class ProcessorTest {

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
    public void testCorrectBehaviour() throws IOException {
        //given
        File file = new File();
        Processor processor = new Processor();
        List<String> expected = file.read("src\\test\\resources\\test_expected_output.txt");
        String sampleInputFilePath = "src\\test\\resources\\test_input.txt";
        //when
        processor.process(sampleInputFilePath, outputFilePath);
        List<String> processorOutputFile = file.read(outputFilePath);

        //then
        assertEquals(expected, processorOutputFile);
    }


    @Test
    @Parameters(method = "addValuesForGivenFilePathIsEmpty")
    public void testForGivenFilePathIsEmpty(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor();

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process(inputFilePath, outputFilePath);
    }

    private Object[] addValuesForGivenFilePathIsEmpty() {
        return new Object[]{
                new Object[]{"src\\test\\resources\\test_input.txt", "", "Output file path is empty"},
                new Object[]{"", "src\\test\\resources\\test_expected_output.txt", "Input file path is empty"},
                new Object[]{"", "", "Input file path is empty"}
        };
    }

    @Test
    public void testForGivenFilePathIsNull() throws IOException {
        //given
        Processor processor = new Processor();

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Input file path cannot be null");
        processor.process(null, null);
    }
}

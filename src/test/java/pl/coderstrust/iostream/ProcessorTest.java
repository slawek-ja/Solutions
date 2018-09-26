package pl.coderstrust.iostream;

import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
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

    private final String outputFilePath = "src\\test\\resources\\test_io_stream_output.txt";

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
    public void testForCorrectBehaviour() throws IOException {
        //given
        Processor processor = new Processor();
        File readFile = new File();
        String inputFilePath = "src\\test\\resources\\test_io_stream_input.txt";
        String expectedOutputFilePath = "src\\test\\resources\\test_expected_stream_output.txt";
        List<String> expectedOutput = readFile.read(expectedOutputFilePath);

        //when
        processor.process(inputFilePath, outputFilePath);
        List<String> resultOutput = readFile.read(outputFilePath);

        //then
        assertEquals(expectedOutput, resultOutput);
    }
}

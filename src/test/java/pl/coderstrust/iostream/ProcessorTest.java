package pl.coderstrust.iostream;

import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import pl.coderstrust.supporttestclasses.ReadFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class ProcessorTest {

    private final String outputFilePath = "src\\test\\resources\\test_io_stream_output.txt";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void removeOutputFile() {
        File file = new File(outputFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testForCorrectBehaviour() throws IOException {
        //given
        FileProcessor fileProcessor = new FileProcessor();
        Processor processor = new Processor(fileProcessor);
        ReadFile readFile = new ReadFile();
        String inputFilePath = "src\\test\\resources\\test_io_stream_input.txt";
        String expectedOutputFilePath = "src\\test\\resources\\test_expected_stream_output.txt";
        List<String> expectedOutput = readFile.readFile(expectedOutputFilePath);

        //when
        processor.process(inputFilePath, outputFilePath);
        List<String> resultOutput = readFile.readFile(outputFilePath);

        //then
        assertEquals(expectedOutput, resultOutput);
    }

    @Test
    @Parameters({
            ",src\\test\\resources\\test_expected_stream_output.txt",
            "src\\test\\resources\\test_io_stream_input.txt,",
            ","})
    public void testForInvalidPathToFile(String inputPath, String resultPath) throws IOException {
        //given
        Processor processor = new Processor(new FileProcessor());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid file path");
        processor.process(inputPath, resultPath);
    }
}

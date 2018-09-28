package pl.coderstrust.io;

import static org.junit.Assert.assertEquals;

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
    public void testForCorrectBehaviour() throws IOException {
        //given
        File file = new File();
        NumbersProcessor numbersProcessor = new NumbersProcessor();
        FileProcessor fileProcessor = new FileProcessor();
        Processor processor = new Processor(numbersProcessor, fileProcessor);
        List<String> expected = file.read("src\\test\\resources\\test_expected_output.txt");
        String sampleInputFilePath = "src\\test\\resources\\test_input.txt";
        //when
        processor.process(sampleInputFilePath, outputFilePath);
        List<String> processorOutputFile = file.read(outputFilePath);

        //then
        assertEquals(expected, processorOutputFile);
    }
}

package pl.coderstrust.iostream;

import static org.junit.Assert.assertTrue;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.utils.ParameterValidator;
import java.io.File;
import java.io.IOException;

public class IntegrationTest {

    private static final String outputFilePath = "src\\test\\resources\\test_stream_output.txt";

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
        ParameterValidator parameterValidator = new ParameterValidator();
        Processor processor = new Processor(parameterValidator);
        File expectedFile = new File("src\\test\\resources\\test_stream_expected_output.txt");
        String inputFilePath = "src\\test\\resources\\test_input.txt";

        //when
        processor.process(inputFilePath, outputFilePath);
        File outputFile = new File(outputFilePath);

        //then
        assertTrue(FileUtils.contentEquals(expectedFile, outputFile));
    }
}

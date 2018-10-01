package pl.coderstrust.iostream;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import java.io.IOException;

@RunWith(JUnitParamsRunner.class)
public class ProcessorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Parameters(method = "addValuesForGivenFilePathIsEmptyOrNull")
    public void testForGivenFilePathIsEmptyOrNull(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor();

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process(inputFilePath, outputFilePath);
    }

    private Object[] addValuesForGivenFilePathIsEmptyOrNull() {
        return new Object[]{
                new Object[]{"src\\test\\resources\\test_input.txt", "", "Result file name cannot be empty"},
                new Object[]{"", "src\\test\\resources\\test_expected_output.txt", "File name cannot be empty"},
                new Object[]{"", "", "File name cannot be empty"},
                new Object[]{null, "src\\test\\resources\\test_expected_output.txt", "File name cannot be null"},
                new Object[]{"src\\test\\resources\\test_input.txt", null, "Result file name cannot be null"}
        };
    }
}

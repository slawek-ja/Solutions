package pl.coderstrust.iostream;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import pl.coderstrust.utils.ParameterValidator;
import java.io.IOException;

@RunWith(JUnitParamsRunner.class)
public class ProcessorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Parameters(method = "valuesForGivenFilePathIsEmptyOrNull")
    public void testForGivenFilePathIsEmptyOrNull(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor(new ParameterValidator());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process(inputFilePath, outputFilePath);
    }

    private Object[] valuesForGivenFilePathIsEmptyOrNull() {
        return new Object[]{
                new Object[]{"", "src\\test\\resources\\test_expected_output.txt", "fileName cannot be empty"},
                new Object[]{"", "", "fileName cannot be empty"},
                new Object[]{null, "src\\test\\resources\\test_expected_output.txt", "fileName cannot be null"}
        };
    }

    @Test
    @Parameters(method = "valuesForGivenResultPathIsEmptyOrNull")
    public void testForGivenResultPathIsEmptyOrNull(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor(new ParameterValidator());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process(inputFilePath, outputFilePath);
    }

    private Object[] valuesForGivenResultPathIsEmptyOrNull() {
        return new Object[]{
                new Object[]{"src\\test\\resources\\test_input.txt", "", "resultFileName cannot be empty"},
                new Object[]{"src\\test\\resources\\test_input.txt", null, "resultFileName cannot be null"}
        };
    }

    @Test
    public void testForParameterValidatorIsNull() {
        //when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("ParameterValidator cannot be null");
        Processor processor = new Processor(null);
    }
}

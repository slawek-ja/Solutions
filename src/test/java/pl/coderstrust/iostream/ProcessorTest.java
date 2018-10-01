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
    @Parameters(method = "valuesForValidationOfInputFilePath")
    public void testForValidationOfInputFilePath(String inputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor(new ParameterValidator());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process(inputFilePath, "src\\test\\resources\\test_expected_output.txt");
    }

    private Object[] valuesForValidationOfInputFilePath() {
        return new Object[]{
                new Object[]{"", "fileName cannot be empty"},
                new Object[]{null, "fileName cannot be null"}
        };
    }

    @Test
    @Parameters(method = "valuesForValidationOfOutputFilePath")
    public void testForValidationOfOutputFilePath(String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor(new ParameterValidator());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process("src\\test\\resources\\test_input.txt", outputFilePath);
    }

    private Object[] valuesForValidationOfOutputFilePath() {
        return new Object[]{
                new Object[]{"", "resultFileName cannot be empty"},
                new Object[]{null, "resultFileName cannot be null"}
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

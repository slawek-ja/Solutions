package pl.coderstrust.io;

import static org.mockito.Mockito.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.coderstrust.utils.ParameterValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class ProcessorTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private NumbersProcessor numbersProcessor;

    @Mock
    private FileProcessor fileProcessor;

    @Mock
    private ParameterValidator parameterValidator;

    @InjectMocks
    private Processor processor;

    @Test
    public void testForCorrectBehaviour() throws IOException {
        //given
        String inputFilePath = "src\\test\\resources\\test_input.txt";
        String outputFilePath = "src\\test\\resources\\test_output.txt";
        List<String> dummyOutputArray = new ArrayList<>();
        dummyOutputArray.add("1+2+3+4=10");
        dummyOutputArray.add("5+6+7+8=26");
        when(fileProcessor.readLinesFromFile(inputFilePath)).thenReturn(Arrays.asList("1 2 3 4", "5 6 7 8"));
        when(numbersProcessor.processLine("1 2 3 4")).thenReturn("1+2+3+4=10");
        when(numbersProcessor.processLine("5 6 7 8")).thenReturn("5+6+7+8=26");
        doNothing().when(fileProcessor).writeLinesToFile(anyList(), anyString());

        //when
        processor.process(inputFilePath, outputFilePath);

        //then
        verify(fileProcessor).readLinesFromFile(inputFilePath);
        verify(numbersProcessor).processLine("1 2 3 4");
        verify(numbersProcessor).processLine("5 6 7 8");
        verify(fileProcessor).writeLinesToFile(dummyOutputArray, outputFilePath);
    }

    @Test
    @Parameters(method = "valuesForGivenFileNameIsEmptyOrNull")
    public void testForGivenFilePathIsEmptyOrNull(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor(new NumbersProcessor(), new FileProcessor(), new ParameterValidator());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process(inputFilePath, outputFilePath);
    }

    private Object[] valuesForGivenFileNameIsEmptyOrNull() {
        return new Object[]{
                new Object[]{"", "src\\test\\resources\\test_expected_output.txt", "fileName cannot be empty"},
                new Object[]{"", "", "fileName cannot be empty"},
                new Object[]{null, "src\\test\\resources\\test_expected_output.txt", "fileName cannot be null"}
        };
    }

    @Test
    @Parameters(method = "valuesForGivenResultFileNameIsEmptyOrNull")
    public void testForGivenResultPathIsEmptyOrNull(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor(new NumbersProcessor(), new FileProcessor(), new ParameterValidator());

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(exceptionMessage);
        processor.process(inputFilePath, outputFilePath);
    }

    private Object[] valuesForGivenResultFileNameIsEmptyOrNull() {
        return new Object[]{
                new Object[]{"src\\test\\resources\\test_input.txt", "", "resultFileName cannot be empty"},
                new Object[]{"src\\test\\resources\\test_input.txt", null, "resultFileName cannot be null"}
        };
    }

    @Test
    public void testForNumbersProcessorIsNull() {
        //when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("NumbersProcessor cannot be null");
        Processor processor = new Processor(null, new FileProcessor(), new ParameterValidator());
    }

    @Test
    public void testForFileProcessorIsNull() {
        //when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("FileProcessor cannot be null");
        Processor processor = new Processor(new NumbersProcessor(), null, new ParameterValidator());
    }

    @Test
    public void testForParameterValidatorIsNull() {
        //when
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("ParameterValidator cannot be null");
        Processor processor = new Processor(new NumbersProcessor(), new FileProcessor(), null);
    }
}

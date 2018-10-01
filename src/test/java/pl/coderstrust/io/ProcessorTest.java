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
    @Parameters(method = "addValuesForGivenFilePathIsEmptyOrNull")
    public void testForGivenFilePathIsEmptyOrNull(String inputFilePath, String outputFilePath, String exceptionMessage) throws IOException {
        //given
        Processor processor = new Processor(new NumbersProcessor(), new FileProcessor());

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

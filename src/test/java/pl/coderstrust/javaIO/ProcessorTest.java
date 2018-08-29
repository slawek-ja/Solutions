package pl.coderstrust.javaIO;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorTest {

    @Mock
    private NumbersProcessor numbersProcessor;

    @Mock
    private FileProcessor fileProcessor;

    @InjectMocks
    private Processor processor;

    @Test
    public void testForSimpleExample() throws IOException {
        //given
        List<String> dummyOutputArray = new ArrayList<>();
        dummyOutputArray.add("1+2+3+4=10");
        dummyOutputArray.add("5+6+7+8=26");
        when(fileProcessor.readLinesFromFile("src\\test\\java\\resources\\test_input.txt")).thenReturn(Arrays.asList("1 2 3 4", "5 6 7 8"));
        when(numbersProcessor.processLine("1 2 3 4")).thenReturn("1+2+3+4=10");
        when(numbersProcessor.processLine("5 6 7 8")).thenReturn("5+6+7+8=26");
        doNothing().when(fileProcessor).writeLinesToFile(anyList(), anyString());

        //when
        processor.process("src\\test\\java\\resources\\test_input.txt", "src\\test\\java\\resources\\test_output.txt");

        //then
        verify(fileProcessor).readLinesFromFile("src\\test\\java\\resources\\test_input.txt");
        verify(numbersProcessor, times(1)).processLine("1 2 3 4");
        verify(numbersProcessor, times(1)).processLine("5 6 7 8");
        verify(fileProcessor, times(1)).writeLinesToFile(dummyOutputArray, "src\\test\\java\\resources\\test_output.txt");
    }
}

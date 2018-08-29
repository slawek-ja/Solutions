package pl.coderstrust.javaIO;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
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
    public void testForSimpleExample() throws Exception {
        //given
        List<String> dummyInputArray = new ArrayList<>();
        dummyInputArray.add("1 2 3 4");
        dummyInputArray.add("5 6 7 8");
        List<String> dummyOutputArray = new ArrayList<>();
        dummyOutputArray.add("1+2+3+4=10");
        dummyOutputArray.add("5+6+7+8=26");
        when(fileProcessor.readLinesFromFile("")).thenReturn(dummyInputArray);
        when(numbersProcessor.processLine("1 2 3 4")).thenReturn("1+2+3+4=10");
        when(numbersProcessor.processLine("5 6 7 8")).thenReturn("5+6+7+8=26");
        doNothing().when(fileProcessor).writeLinesToFile(anyList(), anyString());

        //when
        processor.process("", "");

        //then
        verify(fileProcessor, times(1)).readLinesFromFile(any(String.class));
        verify(numbersProcessor, times(1)).processLine(dummyInputArray.get(0));
        verify(numbersProcessor, times(1)).processLine(dummyInputArray.get(1));
        verify(fileProcessor, times(1)).writeLinesToFile(dummyOutputArray, "");
    }
}

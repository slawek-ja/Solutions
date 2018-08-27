package pl.coderstrust.javaIO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import java.util.ArrayList;
import java.util.List;

public class ProcessorTest {

    public FileProcessor fileProcessorMock = mock(FileProcessor.class);
    public Processor processorClassTest = new Processor(new NumbersProcessor(), fileProcessorMock);

    @Test
    public void testForSimpleExample() throws Exception {
        //given
        List<String> givenArray = new ArrayList<>();
        givenArray.add("1 5 7 2");
        givenArray.add("1 2 3 4");
        List<String> expected = new ArrayList<>();
        expected.add("1+5+7+2=15");
        expected.add("1+2+3+4=10");

        //when
        when(fileProcessorMock.readLinesFromFile("")).thenReturn(givenArray);
        ArgumentCaptor<List> resultCapture = ArgumentCaptor.forClass(List.class);
        doNothing().when(fileProcessorMock).writeLinesToFile(resultCapture.capture(), any(String.class));
        processorClassTest.process("", "");

        //then
        assertEquals(expected, resultCapture.getValue());
    }

    @Test
    public void testForMoreThenOneEmptyLine() throws Exception {
        //given
        List<String> givenArray = new ArrayList<>();
        givenArray.add("");
        givenArray.add("");
        List<String> expected = new ArrayList<>();
        expected.add("");
        expected.add("");

        //when
        when(fileProcessorMock.readLinesFromFile("")).thenReturn(givenArray);
        ArgumentCaptor<List> resultCapture = ArgumentCaptor.forClass(List.class);
        doNothing().when(fileProcessorMock).writeLinesToFile(resultCapture.capture(), any(String.class));
        processorClassTest.process("", "");

        //then
        assertEquals(expected, resultCapture.getValue());
    }

    @Test
    public void testForInvalidLines() throws Exception {
        //given
        List<String> givenArray = new ArrayList<>();
        givenArray.add("1 2 5 5 p");
        givenArray.add("ky& ;[] ' g");
        givenArray.add("- 4 - 7");
        List<String> expected = new ArrayList<>();
        expected.add("1 2 5 5 p");
        expected.add("ky& ;[] ' g");
        expected.add("- 4 - 7");

        //when
        when(fileProcessorMock.readLinesFromFile("")).thenReturn(givenArray);
        ArgumentCaptor<List> resultCapture = ArgumentCaptor.forClass(List.class);
        doNothing().when(fileProcessorMock).writeLinesToFile(resultCapture.capture(), any(String.class));
        processorClassTest.process("", "");

        //then
        assertEquals(expected, resultCapture.getValue());
    }

    @Test
    public void testForMixedLines() throws Exception {
        //given
        List<String> givenArray = new ArrayList<>();
        givenArray.add("1 2 3 4");
        givenArray.add("! 9 8 5");
        givenArray.add("-3 4 2 0");
        givenArray.add("4 -9 -3");
        List<String> expected = new ArrayList<>();
        expected.add("1+2+3+4=10");
        expected.add("! 9 8 5");
        expected.add("(-3)+4+2+0=3");
        expected.add("4+(-9)+(-3)=-8");

        //when
        when(fileProcessorMock.readLinesFromFile("")).thenReturn(givenArray);
        ArgumentCaptor<List> resultCapture = ArgumentCaptor.forClass(List.class);
        doNothing().when(fileProcessorMock).writeLinesToFile(resultCapture.capture(), any(String.class));
        processorClassTest.process("", "");

        //then
        assertEquals(expected, resultCapture.getValue());
    }
}

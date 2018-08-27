package pl.coderstrust.javaIO;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import java.util.List;

public class IntegrationTests {

    @Test
    public void integrationTest() throws Exception {
        NumbersProcessor numbersProcessorClassTest = new NumbersProcessor();
        FileProcessor fileProcessorClassTestSpy = spy(FileProcessor.class);
        Processor processorClassTest = new Processor(numbersProcessorClassTest, fileProcessorClassTestSpy);

        ArgumentCaptor<List> catchWriteToFileList = ArgumentCaptor.forClass(List.class);
        doNothing().when(fileProcessorClassTestSpy).writeLinesToFile(catchWriteToFileList.capture(), any(String.class));

        processorClassTest.process("...\\solutions-7-slawek\\src\\test\\java\\resources\\inputTest", "");
        List<String> processorInputResult = catchWriteToFileList.getValue();

        List<String> fromFileOutputResult = fileProcessorClassTestSpy.readLinesFromFile("...\\solutions-7-slawek\\src\\test\\java\\resources\\outputTest");

        assertEquals(fromFileOutputResult, processorInputResult);
    }
}

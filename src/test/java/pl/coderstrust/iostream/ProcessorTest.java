package pl.coderstrust.iostream;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessorTest {

    private final String outputFilePath = "src\\test\\resources\\test_io_stream_output.txt";

    private List<String> readFile(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String currentLine;
        List<String> result = new ArrayList<>();
        while ((currentLine = bufferedReader.readLine()) != null) {
            result.add(currentLine);
        }
        return result;
    }

    @Before
    public void removeOutputFile() {
        File file = new File(outputFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testForCorrectBehaviour() throws IOException {
        //given
        String inputFilePath = "src\\test\\resources\\test_io_stream_input.txt";
        String expectedOutputFilePath = "src\\test\\resources\\test_expected_stream_output.txt";
        Processor processor = new Processor();
        List<String> expectedOutput = readFile(expectedOutputFilePath);

        //when
        processor.process(inputFilePath, outputFilePath);
        List<String> resultOutput = readFile(outputFilePath);

        //then
        assertEquals(expectedOutput, resultOutput);
    }
}

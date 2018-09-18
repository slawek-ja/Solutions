package pl.coderstrust.io;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegrationTests {

    private static final String outputFilePath = "src\\test\\resources\\test_output.txt";

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
        NumbersProcessor numbersProcessor = new NumbersProcessor();
        FileProcessor fileProcessor = new FileProcessor();
        Processor processor = new Processor(numbersProcessor, fileProcessor);
        List<String> expected = readFile("src\\test\\resources\\test_expected_output.txt");
        String sampleInputFilePath = "src\\test\\resources\\test_input.txt";

        //when
        processor.process(sampleInputFilePath, outputFilePath);
        List<String> processorOutputFile = readFile(outputFilePath);

        //then
        assertEquals(expected, processorOutputFile);
    }
}

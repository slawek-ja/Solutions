package pl.coderstrust.javaIO;

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

    private static final String inputFilePath = "src\\test\\java\\resources\\test_input.txt";
    private static final String outputFilePath = "src\\test\\java\\resources\\test_output.txt";

    private List<String> readFile(String fileInputPath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInputPath));
        String currentLine;
        List<String> result = new ArrayList<>();
        while ((currentLine = bufferedReader.readLine()) != null) {
            result.add(currentLine);
        }
        return result;
    }

    @Before
    public void removeOutputFileBeforeTest() {
        File file = new File(outputFilePath);
        file.delete();
    }

    @Test
    public void testForCorrectBehaviour() throws IOException {
        //given
        NumbersProcessor numbersProcessor = new NumbersProcessor();
        FileProcessor fileProcessor = new FileProcessor();
        Processor processor = new Processor(numbersProcessor, fileProcessor);
        List<String> expected = readFile("src\\test\\java\\resources\\test_expected_output.txt");

        //when
        processor.process(inputFilePath, outputFilePath);
        List<String> processorOutputFile = readFile(outputFilePath);

        //then
        assertEquals(expected, processorOutputFile);
    }
}

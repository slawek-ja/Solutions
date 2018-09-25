package pl.coderstrust.supporttestclasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public List<String> readFile(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String currentLine;
        List<String> result = new ArrayList<>();
        while ((currentLine = bufferedReader.readLine()) != null) {
            result.add(currentLine);
        }
        return result;
    }
}

package pl.coderstrust.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class File {
    public List<String> read(String filePath) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }
        if (filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty file path");
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String currentLine;
        List<String> result = new ArrayList<>();
        while ((currentLine = bufferedReader.readLine()) != null) {
            result.add(currentLine);
        }
        return result;
    }
}

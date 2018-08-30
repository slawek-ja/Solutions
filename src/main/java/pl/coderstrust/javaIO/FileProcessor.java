package pl.coderstrust.javaIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    public List<String> readLinesFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader bufferedReader  = new BufferedReader(new FileReader(filePath));
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            lines.add(currentLine);
        }
        return lines;
    }

    public void writeLinesToFile(List<String> lines, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < lines.size(); i++) {
            writer.write(lines.get(i));
            writer.newLine();
        }
        writer.close();
    }
}

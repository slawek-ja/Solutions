package pl.coderstrust.javaIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    public List<String> readLinesFromFile(String filePatch) throws IOException {
        List<String> lines = new ArrayList<>();
        FileReader fileReader = new FileReader(filePatch);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            lines.add(currentLine);
        }
        return lines;
    }

    public void writeLinesToFile(List<String> lines, String resultFileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(resultFileName));
        for (int i = 0; i < lines.size(); i++) {
            writer.write(lines.get(i));
            writer.newLine();
        }
        writer.close();
    }
}

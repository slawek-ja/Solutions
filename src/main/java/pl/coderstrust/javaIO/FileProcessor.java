package pl.coderstrust.javaIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileProcessor {
    public List<String> readLinesFromFile(String filePatch) throws Exception {
        List<String> lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get(filePatch), StandardCharsets.UTF_8);
        return lines;
    }

    public void writeLinesToFile(List<String> lines, String resultFileName) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(resultFileName));
        for (int i = 0; i < lines.size(); i++) {
            writer.write(lines.get(i));
            writer.newLine();
        }
        writer.close();
    }
}

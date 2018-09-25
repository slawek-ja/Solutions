package pl.coderstrust.iostream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileProcessor {
    public Stream<String> readLinesFromFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath));
    }

    public void writeLinesToFile(List<String> givenLines, String outputFilePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        givenLines.forEach(lines -> {
            try {
                writer.write(lines);
                writer.newLine();
            } catch (IOException exception) {
                System.exit(1);
            }
        });
        writer.close();
    }
}

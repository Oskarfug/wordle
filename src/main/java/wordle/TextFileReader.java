package wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextFileReader {
    public static List<String> readFile(Path filePath) {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

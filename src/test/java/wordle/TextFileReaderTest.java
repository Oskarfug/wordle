package wordle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextFileReaderTest {
    @Test
    void canReadAllWordsFromTestFile(@TempDir Path tempDir) throws IOException {
        // Arrange
        Path tempFile = tempDir.resolve("testFile.txt");
        List<String> linesToWrite = List.of("WATER", "STORY", "TRAIN");

        Files.write(tempFile, linesToWrite);

        // Act
        List<String> words = TextFileReader.readFile(tempFile);


        // Assert
        assertEquals(3, words.size());
        assertEquals("WATER", words.get(0));
        assertEquals("STORY", words.get(1));
        assertEquals("TRAIN", words.get(2));
    }

    @Test
    void handlesEmptyFile(@TempDir Path tempDir) throws IOException {
        // Arrange
        Path emptyFile = tempDir.resolve("emptyFile.txt");
        Files.createFile(emptyFile);

        // Act
        List<String> words = TextFileReader.readFile(emptyFile);

        // Assert
        assertEquals(0, words.size());
    }

    @Test
    void handlesNonExistingFile() {
        // Arrange
        Path nonExistingPath = Path.of("nonExistingFile.txt");

        // Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            TextFileReader.readFile(nonExistingPath);
        });

        assertInstanceOf(IOException.class, exception.getCause());
    }
}

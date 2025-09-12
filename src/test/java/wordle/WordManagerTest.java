package wordle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordManagerTest {

    @Test
    void wordManagerFiltersOutInvalidWords(@TempDir Path tempDir) throws IOException {
        // Arrange
        Path tempFile = tempDir.resolve("testFile.txt");
        List<String> testWords = List.of(
                "APPLE",
                "PROGRAMMING",
                "HOUSE",
                "BOOK",
                "TRAIN"
        );
        Files.write(tempFile, testWords);

        // Act
        WordManager wordManager = new WordManager(tempFile);
        List<String> words = wordManager.getWords();

        // Assert
        assertEquals(3, words.size());
        assertTrue(words.contains("APPLE"));
        assertTrue(words.contains("HOUSE"));
        assertTrue(words.contains("TRAIN"));
    }
}

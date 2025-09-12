package wordle;

import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordManager {

    private final List<String> words;
    private final Random random;


    public WordManager(Path wordsFilePath) {
        this.words = TextFileReader.readFile(wordsFilePath).stream()
                .filter(word -> word.length() == 5)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        if (this.words.isEmpty()) {
            throw new IllegalStateException("No valid words found in file");
        }
        this.random = new Random();
    }

    public List<String> getWords() {
        return words;
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }
}

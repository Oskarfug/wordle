package wordle;

import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Wordle {

    private final String secretWord;
    private int guessesRemaining = 5;
    private boolean gameWon = false;

    public static class GuessResult {
        public enum Status {
            CORRECT, MISPLACED, WRONG
        }
        public final char letter;
        public final Status status;

        public GuessResult(char letter, Status status) {
            this.letter = letter;
            this.status = status;
        }
    }

    public Wordle(Path wordsFilePath) {
        List<String> words = TextFileReader.readFile(wordsFilePath);
        if (words.isEmpty()) {
            throw new IllegalStateException("Words list is empty");
        }

        this.secretWord = words.get(new Random().nextInt(words.size())).toUpperCase();
    }

    public List<GuessResult> submitGuess(String guess) {
        if (guessesRemaining <= 0 || gameWon) {
            return null;
        }

        guess = guess.toUpperCase();

        List<GuessResult> results = new java.util.ArrayList<>();
        for (int i = 0; i < guess.length(); i++) {
            char guessedChar = guess.charAt(i);

            if (guessedChar == secretWord.charAt(i)) {
                results.add(new GuessResult(guessedChar, GuessResult.Status.CORRECT));
            } else if (secretWord.indexOf(guessedChar) != -1) {
                results.add(new GuessResult(guessedChar, GuessResult.Status.MISPLACED));
            } else {
                results.add(new GuessResult(guessedChar, GuessResult.Status.WRONG));
            }
        }

        guessesRemaining--;

        if (guess.equals(secretWord)) {
            gameWon = true;
        }

        return results;
    }

    public int getGuessesRemaining() {
        return guessesRemaining;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() {
        return gameWon || guessesRemaining == 0;
    }

    public String getSecretWord() {
        return secretWord;
    }
}

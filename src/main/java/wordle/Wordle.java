package wordle;

import java.nio.file.Path;
import java.util.List;

public class Wordle {

    private final String secretWord;
    private int guessesRemaining = 5;
    private boolean gameWon = false;

    public Wordle(Path wordsFilePath) {
        WordManager wordManager = new WordManager(wordsFilePath);
        this.secretWord = wordManager.getRandomWord();
    }

    public List<GuessEvaluator.GuessResult> submitGuess(String guess) {
        if (isGameOver()) {
            throw new IllegalStateException("Game is already over");
        }

        String formattedGuess = guess.toUpperCase();

        if (formattedGuess.length() != 5) {
            return null;
        }

        List<GuessEvaluator.GuessResult> results = GuessEvaluator.evaluate(formattedGuess, secretWord);

        guessesRemaining--;

        if (formattedGuess.equals(secretWord)) {
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

    public boolean isGameOver() {
        return gameWon || guessesRemaining == 0;
    }

    public String getSecretWord() {
        return secretWord;
    }
}

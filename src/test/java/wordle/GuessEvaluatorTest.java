package wordle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessEvaluatorTest {
    @Test
    void canHandleDuplicateLetters() throws IOException {
        // Arrange
        String guess = "ATARI";
        String secretWord = "WATER";

        // Act
        List<GuessEvaluator.GuessResult> results = GuessEvaluator.evaluate(guess, secretWord);

        // Assert
        assertEquals(guess.length(), results.size());
        assertEquals(GuessEvaluator.GuessResult.Status.MISPLACED, results.get(0).status);
        assertEquals(GuessEvaluator.GuessResult.Status.MISPLACED, results.get(1).status);
        assertEquals(GuessEvaluator.GuessResult.Status.WRONG, results.get(2).status);
        assertEquals(GuessEvaluator.GuessResult.Status.MISPLACED, results.get(3).status);
        assertEquals(GuessEvaluator.GuessResult.Status.WRONG, results.get(4).status);
    }

    @Test
    void canHandleCorrectGuess() throws IOException {
        // Arrange
        String guess = "HELLO";
        String secretWord = "HELLO";

        // Act
        List<GuessEvaluator.GuessResult> results = GuessEvaluator.evaluate(guess, secretWord);

        // Assert
        assertEquals(guess.length(), results.size());
        for (GuessEvaluator.GuessResult result : results) {
            assertEquals(GuessEvaluator.GuessResult.Status.CORRECT, result.status,
                    "All letters in a correct guess should be CORRECT");
        }
    }

    @Test
    void secretWordWithDuplicateLetters() {
        // Arrange
        String guess = "LEVEL";
        String secretWord = "HELLE";

        // Act
        List<GuessEvaluator.GuessResult> results = GuessEvaluator.evaluate(guess, secretWord);

        // Assert
        assertEquals(GuessEvaluator.GuessResult.Status.MISPLACED, results.get(0).status);
        assertEquals(GuessEvaluator.GuessResult.Status.CORRECT, results.get(1).status);
        assertEquals(GuessEvaluator.GuessResult.Status.WRONG, results.get(2).status);
        assertEquals(GuessEvaluator.GuessResult.Status.MISPLACED, results.get(3).status);
        assertEquals(GuessEvaluator.GuessResult.Status.MISPLACED, results.get(4).status);
    }

}

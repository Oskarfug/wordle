package wordle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuessEvaluator {

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

    public static List<GuessResult> evaluate(String guess, String secretWord) {
        List<GuessResult> results = new java.util.ArrayList<>();
        char[] guessChars = guess.toCharArray();
        char[] secretWordChars = secretWord.toCharArray();

        Map<Character, Integer> guessMap = new HashMap<>();

        for (int i = 0; i < guessChars.length; i++) {
            if (guessChars[i] == secretWordChars[i]) {
                results.add(new GuessResult(guessChars[i], GuessResult.Status.CORRECT));
                guessChars[i] = '-';
            } else {
                guessMap.put(secretWordChars[i], guessMap.getOrDefault(secretWordChars[i], 0) + 1);
                results.add(null);
            }
        }

        for (int i = 0; i < guessChars.length; i++) {
            if (guessChars[i] == '-') {
                continue;
            }

            char guessedChar = guessChars[i];

            if (guessMap.getOrDefault(guessedChar, 0) > 0) {
                results.set(i, new GuessResult(guessedChar, GuessResult.Status.MISPLACED));
                guessMap.put(guessedChar, guessMap.get(guessedChar) - 1);
            } else {
                results.set(i, new GuessResult(guessedChar, GuessResult.Status.WRONG));
            }
        }

        return results;
    }
}

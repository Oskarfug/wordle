package wordle;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Path WORDS_FILE_PATH = Path.of("src/main/resources/words.txt");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Wordle wordle = new Wordle(WORDS_FILE_PATH);

            while (!wordle.isGameOver()) {
                System.out.println("Guesses reamining: " + wordle.getGuessesRemaining());
                System.out.println("Enter your 5-letter guess: ");
                String guess = scanner.nextLine();

                List<GuessEvaluator.GuessResult> results = wordle.submitGuess(guess);

                if (results != null) {
                    printGuessResults(results);
                }
            }

            if (wordle.isGameWon()) {
                System.out.println("\nYou won!");
            } else {
                System.out.println("\n You lost. The secret word was: " + wordle.getSecretWord());
            }
        }
    }

    private static void printGuessResults(List<GuessEvaluator.GuessResult> results) {
        for (GuessEvaluator.GuessResult result : results) {
            String colorCode = switch (result.status) {
                case CORRECT -> TextBackground.GREEN.getEscapeCode();
                case MISPLACED -> TextBackground.YELLOW.getEscapeCode();
                case WRONG -> TextBackground.GRAY.getEscapeCode();
            };
            System.out.printf("%s%c%s", colorCode, result.letter, TextBackground.RESET.getEscapeCode());
        }
        System.out.println();
    }
}

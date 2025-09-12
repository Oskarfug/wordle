package wordle;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Path WORDS_FILE_PATH = Path.of("src/main/resources/words.txt");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Wordle wordle = new Wordle(WORDS_FILE_PATH);

            while (!wordle.isGameLost()) {
                System.out.println("Guesses reamining: " + wordle.getGuessesRemaining());
                System.out.println("Enter your 5-letter guess: ");
                String guess = scanner.nextLine();

                if (guess.length() != 5) {
                    System.out.println("Invalid guess. Guess must be 5 letters long.");
                    continue;
                }

                List<Wordle.GuessResult> results = wordle.submitGuess(guess);

                printGuessResults(results);
            }

            if (wordle.isGameWon()) {
                System.out.println("\nYou won!");
            } else {
                System.out.println("\n You lost. The secret word was: " + wordle.getSecretWord());
            }
        }
    }

    private static void printGuessResults(List<Wordle.GuessResult> results) {
        for (Wordle.GuessResult result : results) {
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

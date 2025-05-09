package NumberGuessGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//Graham Michael 11/18/24
import java.util.ArrayList;
import java.util.Scanner; // Import Scanner class for user input

import Game.Game;
import Game.GameWriteable;

import java.util.Random; // Import Random class for generating random numbers

public class NumberGuessGame implements GameWriteable {
    // Declare variables for the player's guess and number of tries
    int guess = 0;
    int tries = 0;

    public void play() {
        // Create a Scanner object to read user input
        ArrayList<Integer> pastGuesses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Declare variables for the lower and upper bounds of the range
        int upperBound, lowerBound;

        // Print a welcome message to the user
        System.out.println("Welcome to Graham's Number Guessing Game!");

        // Ask the user to input the lower and upper bounds for the game
        System.out.println("Please choose a range of integers:");
        lowerBound = getValidBound("Enter the lower bound number:", scanner);
        upperBound = getValidBound("Enter the upper bound number:", scanner);

        if (lowerBound >= upperBound) {
            // If bounds are invalid, notify the user and restart the game
            System.out.println("That is an invalid bound, try again.");
            play(); // Recursive call to restart the game
            return; // Stop further execution after calling playGame again
        }

        // Create an instance of Random to generate a random target number
        Random randomGenerator = new Random();

        // generates the number
        int target = randomGenerator.nextInt(upperBound - lowerBound + 1) + lowerBound;

        // Game loop: Continue asking the player for guesses until they guess correctly
        while (guess != target) {
            // Prompt the user for their guess and validate the input
            guess = getValidBound("Guess the number: ", scanner);

            tries = tries + 1;
            if (pastGuesses.contains(guess)) {
                System.out.println("You already guessed that, try again");
                continue;
            }
            pastGuesses.add(guess);
            if (guess < target) {
                // If the guess is too low
                System.out.println("Too low, try again.");
            } else if (guess > target) {
                // If the guess is too high
                System.out.println("Too high, try again.");
            } else {
                // If the guess is correct
                System.out.println("Good job! You've guessed the number in " + getScore() + " tries.");
            }
        }
    }

    // Method to get a valid integer input from the user
    public int getValidBound(String prompt, Scanner scanner) {
        // Variable to hold the user's input
        int bound = 0;

        // Loop to repeatedly prompt the user until a valid integer is entered
        while (true) {
            // show the prompt to the user
            System.out.println(prompt);

            // Check if the input is a valid integer
            if (scanner.hasNextInt()) {
                bound = scanner.nextInt();
                break; // Exit the loop if the input is valid
            } else {

                System.out.println("This is not a valid number, please try again");

                // Discard the invalid input
                scanner.next();
            }
        }
        return bound;
    }

    @Override
    public String getGameName() {
        return "Number Guessing Game"; // Return the name of the game
    }

    public boolean isHighScore(String score, String currentHighScore) {
        if (currentHighScore == null || score == null) {
            return true; // If there is no current high score, any score is a high score
        }
        try {
            return Integer.parseInt(score) < Integer.parseInt(currentHighScore);
        } catch (NumberFormatException e) {
            System.err.println("Invalid score format.");
            return false;
        } // copilot
    }

    @Override
    public String getScore() {
        return Integer.toString(tries); // Return the number of tries as a string
    }
}
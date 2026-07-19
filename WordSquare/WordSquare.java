package WordSquare;

import java.util.Scanner;
import java.util.List;

public class WordSquare {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner sca = new Scanner(System.in);
        System.out.println("Enter the number and characters: ");
        String input = sca.nextLine();
        sca.close();
        // Split the input into the grid size and the characters
        try {
            // Parse the input to extract the grid size and characters
            GridInput gridInput = parseInput(input);
            // Create a LetterGroup object to manage the available characters
            LetterGroup pool = new LetterGroup(gridInput.chars());
            // Generate candidate words of length n from the available letters in the pool
            List<String> candidateWords = WordGeneration.wordGeneration(gridInput.n(), pool);
            String[] square = SquareBacktracker.validateSquare(gridInput.n(), pool, candidateWords);
            // Print the resulting word square or a message if no valid square was found
            if (square == null) {
                System.out.println("No valid word square found.");
            } else {
                System.out.println("Valid word square:");
                for (String word : square) {
                    System.out.println(word);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
    // Parses the input string to extract the grid size and characters
    static GridInput parseInput(String input) {
        // Split the input string into parts based on whitespace
        String[] parts = input.trim().split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Expected input in the form '<n> <letters>'");
        }
        // Parse the grid size from the first part of the input
        int n;
        try {
            n = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid grid size: " + parts[0]);
        }
        char[] chars = parts[1].toCharArray();
        if (chars.length != n * n) {
            throw new IllegalArgumentException("Expected " + (n * n) + " letters, got " + chars.length);
        }
        return new GridInput(n, chars);
    }
    // Record to hold the grid size and characters
    record GridInput(int n, char[] chars) {
    }
}

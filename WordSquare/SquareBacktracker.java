package WordSquare;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SquareBacktracker {
    // Backtracking method to validate the word square
    public static String[] validateSquare(int n, LetterGroup pool, List<String> candidateWords) {
        // Initialize an array to hold the current state of the word square
        String[] square = new String[n];
        // Start the backtracking process from the first row
        if (backtrack(0,n,square,pool,candidateWords)){
            // Return the valid word square if found
            return square;
        } else {
            return null;// No valid word square found
        }
    }
    // Recursive backtracking method to fill the word square
    private static boolean backtrack(int row, int n, String[] square, LetterGroup pool, List<String> candidateWords) {
        // Base case: If all rows are filled, return true
        if (row == n) {
            return true; // All rows filled successfully
        }
        // Build the pattern for the current row based on the existing square
        char[] pattern = buildPattern(row, n, square);
        // Iterate through the candidate words to find a match for the current row
        for (String word : candidateWords) {
            if (!matchesPattern(word, pattern)) {
                continue; // Skip words that don't match the current pattern
            }
            // Count the letters in the current word to check if they can be consumed from the pool
            Map<Character, Integer> wordLetters = LetterGroup.countLetters(word.toCharArray());// Count the letters in the current word
            if (!pool.canConsume(wordLetters)) {// Check if the pool has enough letters to consume the current word
                continue; // Skip words that require more characters than available
            }
            pool.consume(wordLetters); // Consume the full word's letters
            square[row] = word; // Place the word in the current row
            // Recursively attempt to fill the next row
            if (backtrack(row+1, n, square, pool, candidateWords)) {
                return true; // Continue to the next row
            }
            pool.release(wordLetters); // Release the consumed characters for backtracking
            square[row] = null; // Backtrack: Remove the word from the current row
        }
        return false; // No valid word found for the current row
    }
    // === Helper Methods ===
    // Checks if word square symetry is maintained
    private static char[] buildPattern(int row, int n, String[] square) {
        char[] pattern = new char[n];
        // Fill the pattern with '.' to indicate any character is allowed
        Arrays.fill(pattern, '.');
        // Build the pattern based on the characters in the current column of the square
        for (int r = 0; r<row;r++){
            // Set the character in the pattern to match the character in the square at the current row and column
            pattern[r] = square[r].charAt(row);
        }
        // Return the constructed pattern for the current row
        return pattern;
    }
    // Checks if the word matches the given pattern
    private static boolean matchesPattern(String word, char[] pattern) {
        for (int i = 0; i < pattern.length; i++){
            if (pattern[i] != '.' && word.charAt(i) != pattern[i]){
                return false; // Word does not match the pattern
            }
        }
        return true; // Word matches the pattern
    }
}
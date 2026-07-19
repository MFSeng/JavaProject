package WordSquare;

import java.io.IOException;
//for reading the dictionary file
import java.nio.file.Files;
import java.nio.file.Path;
//for storing the dictionary words
import java.util.ArrayList;
import java.util.List;

public class WordGeneration {
    private static final Path DEFAULT_DICTIONARY = Path.of("WordSquare/enable1.txt");// Default dictionary file path
    // Generate candidate words of length n from the available letters in the pool
    public static List<String> wordGeneration(int n, LetterGroup pool){
        return wordGeneration(n, pool, DEFAULT_DICTIONARY);
    }
    // Overload exists so tests can point at a small temp dictionary
    // instead of depending on the real enable1.txt on disk.
    public static List<String> wordGeneration(int n, LetterGroup pool, Path dictionaryPath) {
        List<String> dictionary;
        try {
            // Read all lines from the dictionary file into a list
            dictionary = Files.readAllLines(dictionaryPath);
        } catch (IOException e) {
            // Handle the exception if the dictionary file cannot be read
            System.out.println("Error reading dictionary file: " + e.getMessage());
            return new ArrayList<>();
        }
        // Filter the dictionary to find words of length n that can be formed with the available letters in the pool
        List<String> candidateWords = new ArrayList<>();
        for (String word : dictionary) {
            if (word.length() == n && pool.canConsume(word)) {
                candidateWords.add(word);// Add the word to the candidate list if it meets the criteria
            }
        }
        // Return the list of candidate words that can be used to form the word square
        return candidateWords;
    }
}

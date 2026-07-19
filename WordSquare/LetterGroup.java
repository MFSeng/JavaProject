package WordSquare;

import java.util.HashMap;
import java.util.Map;

public class LetterGroup {
    // A map to store the frequency of each character in the group
    private final Map<Character, Integer> letterCount;
    // Constructor that initializes the letter count map based on the provided characters
    public LetterGroup(char[] chars){
        this.letterCount = countLetters(chars);
    }
    // Method to count the frequency of each character in the given array
    public static Map<Character, Integer> countLetters(char[] chars){
        Map<Character, Integer> letterCount = new HashMap<>();
        for (char c : chars){
            // Convert character to lowercase and update its count in the map
            letterCount.merge(Character.toLowerCase(c), 1, Integer::sum);
        }
        return letterCount;
    }
    // Check if the word can be formed with the available characters
    public boolean canConsume(String word){
        return canConsume(countLetters(word.toCharArray()));
    }
    // Check if the needed characters can be consumed from the available characters
    public boolean canConsume(Map<Character, Integer> neededChars){
        for (Map.Entry<Character, Integer> entry : neededChars.entrySet()){
            if (letterCount.getOrDefault(entry.getKey(), 0) < entry.getValue()){
                return false; // Not enough characters available to consume
            }
        }
        return true; // All needed characters can be consumed
    }
    // Consumes the needed characters from the available characters
    public void consume(Map<Character, Integer> neededChars) {
        for (Map.Entry<Character, Integer> entry : neededChars.entrySet()){
            letterCount.merge(entry.getKey(), -entry.getValue(), Integer::sum); // Decrease the count of available characters
        }
    }
    // Releases the consumed characters back to the available characters
    public void release(Map<Character, Integer> neededChars){
        for (Map.Entry<Character, Integer> entry : neededChars.entrySet()){
            letterCount.merge(entry.getKey(), entry.getValue(), Integer::sum); // Increase the count of available characters
        }
    }
    // Returns the count of available characters for the given character
    public int availableCount(char c){
        return letterCount.getOrDefault(Character.toLowerCase(c), 0);
    }
}

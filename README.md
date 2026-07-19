# Word Square Generator

A Java program that generates valid word squares using backtracking algorithms. A word square is an n×n grid of letters where all rows and columns form valid English words.

## Project Overview

This program reads a grid size and a set of characters as input, then uses a dictionary file (enable1.txt) to find valid words. It employs a backtracking algorithm to construct an n×n word square where:
- Each row is a valid English word
- Each column is a valid English word
- The same set of input characters is used to form all words

## Project Structure

- **WordSquare.java** - Main entry point; handles user input parsing and orchestration
- **WordGeneration.java** - Generates candidate words of the required length from available letters
- **SquareBacktracker.java** - Implements backtracking algorithm to find valid word squares
- **LetterGroup.java** - Manages available characters and validates character usage
- **enable1.txt** - Dictionary file containing valid English words

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- The `enable1.txt` dictionary file must be present in the `WordSquare/` directory

## How to Compile

Navigate to the project root directory and compile all Java files:

```bash
javac WordSquare/*.java
```

## How to Run

Execute the program from the project root directory:

```bash
java WordSquare.WordSquare
```

## Usage

The program prompts you to enter:
1. **Grid size (n)**: An integer representing the dimensions of the word square (e.g., 3 for a 3×3 square)
2. **Characters**: Exactly n² characters to form the word square

### Input Format

```
<n> <characters>
```

**Example for a 3×3 word square:**
```
3 abeceaeslp
```

This creates a 3×3 grid using the characters: a, b, e, c, e, a, e, s, l, p

### Output

If a valid word square is found, the program displays all n words that form the square:

```
Valid word square:
abc
bea
ace
```

If no valid word square can be formed with the given characters, the program outputs:

```
No valid word square found.
```

## Example Usage

**Session Example:**

```
$ java WordSquare.WordSquare
Enter the number and characters: 
3 bestealest
Valid word square:
best
else
steal
test
```

## Error Handling

The program validates input and displays error messages for:
- Invalid grid size (non-integer values)
- Incorrect number of characters (must equal n²)
- Improperly formatted input

**Example error:**
```
$ java WordSquare.WordSquare
Enter the number and characters: 
3 abcde
Invalid input: Expected 9 characters for a 3x3 grid, but got 5
```

## Algorithm Details

The program uses a **backtracking algorithm** to search for valid word squares:

1. Loads all valid dictionary words from enable1.txt
2. Filters words that match the required length and available characters
3. For each row, finds candidate words that match the column constraints
4. Recursively attempts to place words while respecting character availability
5. Backtracks when no valid word can be placed and tries alternative paths
6. Returns the first valid solution found, or indicates no solution exists

## Performance Notes

- The program may take some time to run for larger grid sizes
- Character availability is strictly enforced; each input character can only be used once
- The backtracking algorithm prunes invalid branches early for efficiency

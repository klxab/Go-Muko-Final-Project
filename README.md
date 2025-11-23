# Go-Moku Game

A simple 6x6 Go-Moku game where you play against the computer.

## How to Run

1. Make sure you have Java 11+ and Maven installed
2. Navigate to the project directory:
   ```
   cd gomoku-game
   ```
3. Run the game:
   ```
   mvn compile exec:java -Dexec.mainClass="com.gomoku.GoMokuGame"
   ```

## How to Play

- Enter your moves as "row col" (e.g., "3 4" for row 3, column 4)
- Get 5 in a row (horizontally, vertically, or diagonally) to win
- The computer will make random moves

## Running Tests

To run the tests:
```
mvn test
```

## Project Structure

- `src/main/java/com/gomoku/GoMokuGame.java` - Main game class
- `src/main/java/com/gomoku/GameService.java` - Game logic
- `src/test/java/com/gomoku/GameServiceTest.java` - Unit tests

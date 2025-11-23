package com.gomoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GameServiceTest {
    private GameService gameService;
    @BeforeEach
    public void setUp() {
        gameService = new GameService(6);
    }
    @Test
    public void testInitialBoardIsEmpty() {
        char[][] board = gameService.getBoard();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals('.', board[i][j]);
            }
        }
    }
    @Test
    public void testValidMove() {
        assertTrue(gameService.makeMove(0, 0, 'X'));
        assertFalse(gameService.makeMove(0, 0, 'O'));
    }
    @Test
    public void testCheckWinHorizontal() {
        gameService.makeMove(0, 0, 'X');
        gameService.makeMove(1, 0, 'O');
        gameService.makeMove(0, 1, 'X');
        gameService.makeMove(1, 1, 'O');
        gameService.makeMove(0, 2, 'X');
        gameService.makeMove(1, 2, 'O');
        gameService.makeMove(0, 3, 'X');
        gameService.makeMove(1, 3, 'O');
        assertTrue(gameService.makeMove(0, 4, 'X'));
        assertTrue(gameService.isGameOver());
        assertEquals("You win!", gameService.getGameResult());
    }
}

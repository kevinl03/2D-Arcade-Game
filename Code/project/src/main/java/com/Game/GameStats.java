package com.Game;

/**
 * Represents game loss and game won statistics
 */
public class GameStats {
    /**
     * game over value
     */
    private boolean gameOver = false;

    /**
     * game won value
     */
    private boolean gameWon = false;

    /**
     * Sets the game over value
     * @param gameOver new game over value
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Gets game over value
     */
    public boolean getGameOver() {
        return gameOver;
    }


    /**
     * Gets game won value
     * @return com.Game won value
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Sets game won value
     * @param gameWon New game won value
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}

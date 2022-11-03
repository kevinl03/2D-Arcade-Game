package Game;

import Helpers.Stopwatch;

public class GameStats {
    private boolean gameOver = false;

    private boolean gameWon = false;
    private Stopwatch time = new Stopwatch();


    public Stopwatch getTime() {
        return time;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}

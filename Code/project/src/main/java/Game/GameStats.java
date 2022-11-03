package Game;

import Helpers.Stopwatch;

public class GameStats {
    private boolean gameOver = false;
    private Stopwatch time = new Stopwatch();


    public Stopwatch getTime() {
        return time;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}

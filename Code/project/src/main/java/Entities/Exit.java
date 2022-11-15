package Entities;

import Board.Difficulty;

/**
 * Represents the exit for the win condition
 */
public class Exit extends Position{
    /**
     * Exit's closed status, always starts closed
     */
    private boolean closed = true;

    /**
     * amount of rewards collected thus far
     */
    private int rewardCount = 0;

    /**
     * Default constructor
     */
    public Exit() {

    }

    /**
     * Increments the reward count when a reward is collected
     * If the reward count is sufficient for difficulty, the exit opens
     * @param dif current Difficulty
     */
    public void rewardCollected(Difficulty dif){
        rewardCount++;
        switch(dif){
            case EASY: if (rewardCount == 5){ closed = false;} break;
            case MEDIUM: if (rewardCount == 10){ closed = false;} break;
            case HARD:
            case INFINITE:
                if (rewardCount == 15){ closed = false;} break;
        }
    }

    /**
     * Gets the closed status
     * @return Closed status
     */
    public boolean isClosed() {
        return closed;
    }
}

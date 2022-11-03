package Entities;

import Board.Difficulty;

public class Exit extends Position{
    private boolean closed = true;
    private int rewardCount = 0;


    public Exit(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Exit() {

    }

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

    public boolean isClosed() {
        return closed;
    }
}

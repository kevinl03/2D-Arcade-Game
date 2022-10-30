package Entities;

import Board.Difficulty;

public class Exit {
    private boolean closed = true;
    private int rewardCount = 0;


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

}

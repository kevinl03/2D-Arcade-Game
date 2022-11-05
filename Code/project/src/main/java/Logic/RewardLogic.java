package Logic;

import Board.*;
import Display.DisplayLayout;
import Game.ObjectData;
import Entities.Bonus;
import java.util.ArrayList;
import java.util.Random;


public class RewardLogic {
    public void updateRewards(ObjectData gameobjectData, int ticks){
        BoardData boardData = gameobjectData.getBoard();
        ArrayList<Bonus> bonuses = gameobjectData.getBonus();
        Random rand = new Random(); //instance of random class

        Objects[][] objectMap = boardData.getBoardData();
        for (Bonus bonusObj : bonuses){
            //randomize despawns
            int max = 15000; //15 seconds
            int min = 8000; //8 seconds

            int lifetime = rand.nextInt(max-min+1) + min;
            if (ticks - bonusObj.getStartTime() > lifetime){

                switch(objectMap[bonusObj.getX()][bonusObj.getY()]){
                    case BONUS:
                        int[] newpos = getRandomXY(boardData);
                        int x = newpos[0];
                        int y = newpos[1];

                        int oldX = bonusObj.getX();
                        int oldY = bonusObj.getY();
                        //set object position
                        bonusObj.setX(x);
                        bonusObj.setY(y);
                        objectMap[x][y] = Objects.BONUS;
                        objectMap[oldX][oldY] = Objects.EMPTY;
                        bonusObj.setStartTime(ticks);
                        break;

                    default:
                        //do not worry if the position is not a bonus reward
                        //cases for if bear ontop of Bonus will simply despawn
                        //when enemy moves from object
                        System.out.printf("bonusObj despawning");
                        break;
                }


            }
        }



    }

    private int[] getRandomXY(BoardData boardData) {

        Random rand = new Random(); //instance of random class
        boolean posfound = false;
        //initialize vars
        int x = -1;
        int y = -1;
        while (!posfound) {
            //-2 + 1 in order to not consider the border
            x = rand.nextInt(boardData.getboardwidth() - 2) + 1;
            y = rand.nextInt(boardData.getboardheight() - 2) + 1;
            //find a suitable position
            if (boardData.getBoardData()[x][y] == Objects.EMPTY){
                posfound = true;
            }
        }

        int[] xy = {x,y};

        return xy;
    }
}

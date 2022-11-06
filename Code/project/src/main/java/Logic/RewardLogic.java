package Logic;

import Board.*;
import Display.DisplayLayout;
import Display.myGame;
import Entities.Position;
import Game.ObjectData;
import Entities.Bonus;
import java.util.ArrayList;
import java.util.Random;

/**
 * Moves Bonus rewards to new locations after a certain amount of ticks
 * Is called after {@link HeroLogic#processPlayerMovement(Position, ObjectData)} so that rewards that are picked up
 * do not need to be updated inside of {@link myGame#updates()}
 */
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

            //choose a random int between max and min so that the object despawns
            int lifetime = rand.nextInt(max-min+1) + min;
            if (ticks - bonusObj.getStartTime() > lifetime){

                switch(objectMap[bonusObj.getX()][bonusObj.getY()]){
                    case BONUS:
                        //chooses a new empty tile
                        int[] newpos = getRandomXY(boardData);
                        int x = newpos[0];
                        int y = newpos[1];

                        int oldX = bonusObj.getX();
                        int oldY = bonusObj.getY();
                        //set object position
                        bonusObj.setX(x);
                        bonusObj.setY(y);

                        //replace objects and set new positions
                        objectMap[x][y] = Objects.BONUS;
                        objectMap[oldX][oldY] = Objects.EMPTY;
                        bonusObj.setStartTime(ticks);
                        break;

                    default:
                        //cases for when bear ontop of REWARD do not despawn the object. Will
                        //instead wait for other ticks
                        break;
                }


            }
        }



    }

    /**
     *
     * @param boardData 2D array of Objects, see {@link BoardData}
     * @return int[]   a random position on the board that satisfied the position BoardData[int[0]][int[1]] == EMPTY
     */
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

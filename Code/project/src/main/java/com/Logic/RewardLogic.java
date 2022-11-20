package com.Logic;

import com.Board.*;
import com.Display.myGame;
import com.Entities.Position;
import com.Game.ObjectData;
import com.Entities.Bonus;
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

            //if the object is spawned, we must know to despawn it here
            if (bonusObj.getisSpawned()) {


                //randomize despawns
                int max = 100000; //15 seconds
                int min = 5000; //8 seconds

                //choose a random int between max and min so that the object despawns
                int lifetime = rand.nextInt(max - min + 1) + min;
                //if its been spawned for long enough
                if (ticks - bonusObj.getStartTime() > lifetime) {

                    //if the tile is just a reward

                    switch (objectMap[bonusObj.getX()][bonusObj.getY()]) {
                        case BONUS:
                            //chooses a new empty tile


                            int X = bonusObj.getX();
                            int Y = bonusObj.getY();
                            //set object position

                            //replace objects and set new positions
                            objectMap[X][Y] = Objects.EMPTY;
                            bonusObj.setdespawnedTime(ticks);
                            bonusObj.setisSpawned(false);
                            break;

                        default:
                            //cases for when bear ontop of REWARD do not despawn the object. Will
                            //instead wait for other ticks
                            break;
                    }


                }
            }
            //if the object is despawned, we must know if we have to respawn it here
            else {
                int [] newcoords = getRandomXY(boardData);

                Position newpos = new Position(newcoords[0], newcoords[1]);

                //if its been between 5 - 10 seconds then we can respawn the object
                int respawntime = rand.nextInt(100000-5000+1) +5000;
                if (ticks - bonusObj.getdespawnTime() > respawntime){

                    //show the object on the map again only if the tile is empty
                    if (boardData.getTypeAt(newpos) == Objects.EMPTY){
                        boardData.setTypeAt(newpos, Objects.BONUS);

                        //set new coordinates for the object
                        bonusObj.setX(newpos.getX());
                        bonusObj.setY(newpos.getY());

                        bonusObj.setisSpawned(true);
                        bonusObj.setStartTime(ticks);
                        bonusObj.setdespawnedTime(ticks);
                    }
                }
                //used to hide object if the map just generated when game is early
                if (ticks < 6000) {
                    //System.out.printf("Setting");
                    Position curcoords = new Position(bonusObj.getX(), bonusObj.getY());

                    if (boardData.getTypeAt(curcoords) == Objects.BONUS){
                        boardData.setTypeAt(curcoords, Objects.EMPTY);
                        //set the position to false just in case
                        bonusObj.setisSpawned(false);
                    }
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

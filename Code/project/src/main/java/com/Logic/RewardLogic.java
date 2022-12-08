package com.Logic;

import com.Board.*;
import com.Helpers.RandomXY;
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
    int maxLifeTime = 100000;
    int maxDespawnTime = 100000;
    int minLifeTime = 7000;
    int minDespawnTime = 7000;
    public static final int NUMBER_OF_TICKS = 6000;
    public void updateRewards(ObjectData gameObjectData, int ticks){
        BoardData boardData = gameObjectData.getBoard();
        ArrayList<Bonus> bonuses = gameObjectData.getBonus();
        Random rand = new Random(); //instance of random class

        Objects[][] objectMap = boardData.getBoardData();
        for (Bonus bonusObj : bonuses){
            //if the object is spawned, we must know to despawn it here
            if (bonusObj.getisSpawned() ) {

                int bonusObjX = bonusObj.getX();
                int bonusObjY = bonusObj.getY();

                Position bonusPosition = new Position(bonusObjX,bonusObjY);

                int despawnTime = rand.nextInt(maxLifeTime - minLifeTime + 1) + minLifeTime;
                int totalTimeSpawned = ticks - bonusObj.getStartTime();

                if (totalTimeSpawned > despawnTime &&  boardData.getTypeAt(bonusPosition) == Objects.BONUS) {
                    //chooses a new empty tile
                    //set object position
                    //replace objects and set new positions
                    objectMap[bonusObjX][bonusObjY] = Objects.EMPTY;
                    bonusObj.setdespawnedTime(ticks);
                    bonusObj.setisSpawned(false);
                }
            }
            //if the object is despawned, we must know if we have to respawn it here
            else {
                int [] randomXY = new RandomXY().getRandomXY(gameObjectData.getBoard());

                Position newPos = new Position(randomXY[0], randomXY[1]);
                //if it's been between 5 - 10 seconds then we can respawn the object
                int respawnTime = rand.nextInt(maxDespawnTime-minDespawnTime+1) + minDespawnTime;
                int totalTimeDespawned = ticks - bonusObj.getdespawnTime();


                if (totalTimeDespawned > respawnTime && boardData.getTypeAt(newPos) == Objects.EMPTY){
                    //show the object on the map again only if the tile is empty
                        boardData.setTypeAt(newPos, Objects.BONUS);
                        swap(bonusObj,newPos);
                        bonusObj.setisSpawned(true);
                        bonusObj.setStartTime(ticks);
                        bonusObj.setdespawnedTime(ticks);
                }
            }
        }
    }

    private void swap(Position firstPos, Position secondPos){
        firstPos.setX(secondPos.getX());
        firstPos.setY(secondPos.getY());

    }
    public int getMaxDespawnTime() {
        return maxDespawnTime;
    }
    public int getMaxLifeTime() {
        return maxLifeTime;
    }
    public int getMinDespawnTime() {
        return minDespawnTime;
    }
}

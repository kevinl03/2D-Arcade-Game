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
    private int maxLifeTime = 100000;
    private int maxDespawnTime = 100000;
    private int minLifeTime = 7000;
    private int minDespawnTime = 7000;

    public void updateRewards(ObjectData gameObjectData, int ticks){
        BoardData boardData = gameObjectData.getBoard();
        ArrayList<Bonus> bonuses = gameObjectData.getBonus();
        Random rand = new Random(); //instance of random class

        Objects[][] objectMap = boardData.getBoardData();
        for (Bonus bonusObj : bonuses){
            //if the object is spawned, we must know to despawn it here
            if (bonusObj.getisSpawned() ) {
                Position bonusPosition = new Position();
                setToNewPos(bonusPosition,bonusObj);

                //chooses a random number based on the lifetime which will despawn
                //the range is from minLifetime until maxLifetime


                int despawnTime = rand.nextInt(maxLifeTime - minLifeTime + 1) + minLifeTime;
                int totalTimeSpawned = ticks - bonusObj.getStartTime();

                //as ticks gets reset for every call of updateRewards, the time period in
                //which the ticks is greater than despawn time decreases. Maxlifetime is made
                //quite large so that there is a wide range of possible despawn times.
                // The longer ticks, the higher probability to despawn.
                // As a result the distribution to despawn is randomized

                //Range :   0 <------- minLifeTime <------------------------------> MaxLifeTime --> Error
                // Progression of ticks:              ^   despawnTime anywhere here   ^
                // totalTimeSpawned += 525
                //            totalTimeSpawned
                //                 totalTimeSpawned
                //                      totalTimeSpawned ....
                //                                                       totalTimeSpawned
                //                                            if(randint < totalTimeSpawned) -> condition for despawn
                //
                //ensures that rates are randomized
                //allows for possibility (although unlikely) for having very long times to respawn

                if (totalTimeSpawned > despawnTime &&  boardData.getTypeAt(bonusPosition) == Objects.BONUS) {
                    //chooses a new empty tile
                    //set object position
                    //replace objects and set new positions
                    objectMap[bonusObj.getX()][bonusObj.getY()] = Objects.EMPTY;
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

                //as ticks gets increased for every call of updateRewards, the time period in
                //which the ticks are greater than respawn time decreases. Maxlifetime is made
                //quite large but realistically the randint will choose a number somewhere in the middle which
                //will be the average time it will respawn. The longer ticks, the higher probability
                //to respawn. As a result the distribution to respawn is randomized

                //Range :   0 <------- minDespawnTime <------------------------------> MaxDespawn time --> Error
                // Progression of ticks:              ^      respawnTime anywhere here   ^
                // totalTimeDespawned +=525
                //            totalTimeDespawned
                //                 totalTimeDespawned
                //                      totalTimeDespawned ....
                //                                                      totalTimeDespawned
                //                                            if(randint < totalTimeDespawned) -> condition for respawn
                //
                //ensures that rates are randomized
                //allows for possibility (although unlikely) for having very long times to despawn


                if (totalTimeDespawned > respawnTime && boardData.getTypeAt(newPos) == Objects.EMPTY){
                    //show the object on the map again only if the tile is empty
                        boardData.setTypeAt(newPos, Objects.BONUS);
                        setToNewPos(bonusObj,newPos);
                        bonusObj.setisSpawned(true);
                        bonusObj.setStartTime(ticks);
                        bonusObj.setdespawnedTime(ticks);
                }
            }
        }
    }

    private void setToNewPos(Position firstPos, Position secondPos){
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

    public void setMaxSpawnTime(int ticks){maxLifeTime = ticks;}

    public void setMaxDespawnTime(int ticks){maxDespawnTime = ticks;}

    public void setMinSpawnTime(int ticks){minLifeTime = ticks;}

    public void setMinDespawnTime(int ticks){minDespawnTime = ticks;}
}

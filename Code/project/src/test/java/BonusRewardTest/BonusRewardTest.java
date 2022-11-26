package BonusRewardTest;

import PlayerMovementTests.MovementTestInfo;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.Bonus;
import com.Entities.Position;
import com.Game.ObjectData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class BonusRewardTest extends MovementTestInfo{

    private ArrayList<Bonus> bonuslist;
    private Bonus testbonus;
    void createbonus(){
        //setup the objects for the map
        setup(Objects.BONUS, Difficulty.HARD);
        bonuslist = objectData.getBonusArray();
        //note time doesn't matter for testbonus
        testbonus = new Bonus(Uppos.getX(), Uppos.getY(), 0, rewardValue * 2);
        //remove all generated bonuses because during respawning, one object
        //may despawn from a test tile but leave it open for another object to spawn
        //back into it. This case is quite rare, but still possible when RewardLogic.updateRewards()
        //despawns an object and respawns another on the exact same tick in the now empty tile.
        //as a result, empty the objects generated such that we don't encounter this problem
        for(int i = 0; i < bonuslist.size(); i++){
            bonuslist.remove(i);
        }
        bonuslist.add(testbonus);

    }

    @Test
    void DespawnBonus() {

        createbonus();

        Position oldbonuspos = new Position(testbonus.getX(), testbonus.getY());

        objectData.getRewardLogic().updateRewards(objectData, objectData.getRewardLogic().getMaxDespawnTime());

        Assertions.assertEquals(bonuslist.get(0).getisSpawned(),true);

        Position bonusrewardpos = new Position(testbonus.getX(), testbonus.getY());

        //only one of the coordinates must be different because
        //it can technically spawn on a new position that is on the same veritcal line as old position
        //or on the same horizontal line as the old

        Assertions.assertEquals(bonusrewardpos.getX() != oldbonuspos.getX() || bonusrewardpos.getY() != oldbonuspos.getY(), true);

        //move the hero to the new generated tile
        objectData.getHeroLogic().processPlayerMovement(bonusrewardpos, objectData);

        Assertions.assertEquals(hero.getScore(),100);
    }

    @Test
    void InitialBonusDoNotSpawnOnMapTest() {
        setup(Objects.EMPTY, Difficulty.HARD);
        bonuslist = objectData.getBonusArray();


        for (int rewardbonus = 0; rewardbonus < bonuslist.size(); rewardbonus++) {

            Bonus curBonus = bonuslist.get(rewardbonus);

            Assertions.assertEquals(curBonus.getisSpawned(), false);

            Position curBonusPos = new Position(curBonus.getX(), curBonus.getY());

            //the tile on which the Bonus Object should be empty because the object
            //is not spawned in yet
            Assertions.assertEquals(objectData.getBoard().getTypeAt(curBonusPos), Objects.EMPTY);

            //player gets moved to every position where the bonus is
            objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

            //non of the objects get picked up because they haven't spawned yet
            Assertions.assertEquals(hero.getScore(), 0);
        }
    }
    @Test
    void SpawnWhenNotEnoughTimePassed(){

        setup(Objects.EMPTY, Difficulty.HARD);
        bonuslist = objectData.getBonusArray();


        for (int rewardbonus = 0; rewardbonus < bonuslist.size(); rewardbonus++) {

            Bonus curBonus = bonuslist.get(rewardbonus);

            curBonus.setisSpawned(false);
            curBonus.setdespawnedTime(0);

            Position curBonusPos = new Position(curBonus.getX(), curBonus.getY());


            objectData.getRewardLogic().updateRewards(objectData, objectData.getRewardLogic().getMinDespawnTime()-1);

            //Assertions.assertEquals(curBonus.getisSpawned(), false);

            int newXpos = curBonus.getX();
            int newYpos = curBonus.getY();

            //update rewards should not have changed anything if the timer doesn't change
            Assertions.assertEquals(newXpos == curBonusPos.getX(), true);
            Assertions.assertEquals((newYpos == curBonusPos.getY()),true);

        }
    }

    @Test
    void SpawnWhenEnoughTimePassed(){

        setup(Objects.EMPTY, Difficulty.HARD);
        bonuslist = objectData.getBonusArray();


        for (int rewardbonus = 0; rewardbonus < bonuslist.size(); rewardbonus++) {

            Bonus curBonus = bonuslist.get(rewardbonus);

            curBonus.setisSpawned(false);
            curBonus.setdespawnedTime(0);
            curBonus.setStartTime(0);

            Position curBonusPos = new Position(curBonus.getX(), curBonus.getY());


            objectData.getRewardLogic().updateRewards(objectData, objectData.getRewardLogic().getMaxDespawnTime()+1);

            Assertions.assertEquals(curBonus.getisSpawned(), true);

            int newXpos = curBonus.getX();
            int newYpos = curBonus.getY();

            //update rewards should not have changed anything if the timer doesn't change
            Assertions.assertEquals(newXpos == curBonusPos.getX() && newYpos == curBonusPos.getY(), false);


            objectData.getHeroLogic().processPlayerMovement(new Position(newXpos,newYpos), objectData);

            //objects must be picked up sine they spawned
            Assertions.assertNotEquals(hero.getScore(), 0);
        }
    }

    @Test
    void DespawnWhenEnemyOnBonusTileTest(){
        setup(Objects.EMPTY, Difficulty.EASY);
        bonuslist = objectData.getBonusArray();


        for (int rewardbonus = 0; rewardbonus < bonuslist.size(); rewardbonus++) {
            Bonus curBonus = bonuslist.get(rewardbonus);

            curBonus.setisSpawned(false);
            curBonus.setStartTime(0);
            curBonus.setdespawnedTime(0);
            //spawnrewards
            objectData.getRewardLogic().updateRewards(objectData, objectData.getRewardLogic().getMaxDespawnTime()+1);

            Position newBonusPos = new Position(curBonus.getX(),curBonus.getY());
            objectData.getBoard().setTypeAt(newBonusPos, Objects.ENEMYANDBONUS);

            curBonus.setisSpawned(true);
            curBonus.setStartTime(0);

            objectData.getRewardLogic().updateRewards(objectData, objectData.getRewardLogic().getMaxLifeTime()+1);

            Assertions.assertEquals(boardData.getTypeAt(newBonusPos), Objects.ENEMYANDBONUS);

        }
    }


    @Test
    void DespawnWhenHeroOnTileTest(){
        setup(Objects.EMPTY, Difficulty.EASY);
        bonuslist = objectData.getBonusArray();


        for (int rewardbonus = 0; rewardbonus < bonuslist.size(); rewardbonus++) {
            Bonus curBonus = bonuslist.get(rewardbonus);

            curBonus.setisSpawned(false);
            curBonus.setStartTime(0);
            curBonus.setdespawnedTime(0);
            //spawnrewards
            objectData.getRewardLogic().updateRewards(objectData, objectData.getRewardLogic().getMaxDespawnTime() + 1);

            Position newBonusPos = new Position(curBonus.getX(), curBonus.getY());
            objectData.getBoard().setTypeAt(newBonusPos, Objects.HERO);

            curBonus.setisSpawned(true);
            curBonus.setStartTime(0);

            objectData.getRewardLogic().updateRewards(objectData, objectData.getRewardLogic().getMaxLifeTime() + 1);

            Assertions.assertEquals(boardData.getTypeAt(newBonusPos), Objects.HERO);
        }
      }
    }


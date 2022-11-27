package PlayerMovementTests;

import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.*;
import com.Game.ObjectData;
import com.Helpers.HeroColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
public class PlayerMovementTest extends MovementTestInfo {

    @Test
    public void testNonConstraintPlayerMovementUp() {
        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY, Difficulty.EASY);
        //movements are same for all difficulties => testing just one
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }
        //set the previous position to empty
        if (boardData.getTypeAt(heropos) != Objects.EMPTY) {
            assert (false);
        }
        //hero position class must also be updated
        if (hero.getX() != Uppos.getX() || hero.getY() != Uppos.getY()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testNonConstraintPlayerMovementDown() {
        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY, Difficulty.EASY);
        //movements are same for all difficulties => testing just one
        objectData.getHeroLogic().processPlayerMovement(Downpos, objectData);

        if (boardData.getTypeAt(Downpos) != Objects.HERO) {
            assert (false);
        }
        //set the previous position to empty
        if (boardData.getTypeAt(heropos) != Objects.EMPTY) {
            assert (false);
        }
        //hero position class must also be updated
        if (hero.getX() != Downpos.getX() || hero.getY() != Downpos.getY()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testNonConstraintPlayerMovementLeft() {
        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY, Difficulty.EASY);
        //movements are same for all difficulties => testing just one
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);

        if (boardData.getTypeAt(Leftpos) != Objects.HERO) {
            assert (false);
        }
        //set the previous position to empty
        if (boardData.getTypeAt(heropos) != Objects.EMPTY) {
            assert (false);
        }
        //hero position class must also be updated
        if (hero.getX() != Leftpos.getX() || hero.getY() != Leftpos.getY()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testNonConstraintPlayerMovementRight() {
        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY, Difficulty.EASY);
        //movements are same for all difficulties => testing just one
        objectData.getHeroLogic().processPlayerMovement(Rightpos, objectData);

        if (boardData.getTypeAt(Rightpos) != Objects.HERO) {
            assert (false);
        }
        //set the previous position to empty
        if (boardData.getTypeAt(heropos) != Objects.EMPTY) {
            assert (false);
        }
        //hero position class must also be updated
        if (hero.getX() != Rightpos.getX() || hero.getY() != Rightpos.getY()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testpickUpRewardEasyDif() {
        setup(Objects.REWARD, Difficulty.EASY);
        ArrayList<RegularReward> rewardlist = objectData.getRewardArray();
        RegularReward testreward = new RegularReward(Uppos.getX(), Uppos.getY(), 0, rewardValue);
        rewardlist.add(testreward);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a reward object to reward ArrayList in order for score
        //to get incremented for hero within processPlayerMovement
        //use 50 because we are testing for difficulty easy
        if (hero.getScore() != rewardValue) {
            assert (false);
        }
        //check that the tile is now a HERO object, not a REWARD object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testpickUpRewardMediumDif() {
        setup(Objects.REWARD, Difficulty.MEDIUM);
        ArrayList<RegularReward> rewardlist = objectData.getRewardArray();
        RegularReward testreward = new RegularReward(Uppos.getX(), Uppos.getY(), 0, rewardValue);
        rewardlist.add(testreward);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a reward object to reward ArrayList in order for score
        //to get incremented for hero within processPlayerMovement
        //use 50 because we are testing for difficulty easy
        if (hero.getScore() != rewardValue) {
            assert (false);
        }
        //check that the tile is now a HERO object, not a REWARD object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testpickUpRewardHardDif() {
        setup(Objects.REWARD, Difficulty.HARD);
        ArrayList<RegularReward> rewardlist = objectData.getRewardArray();
        RegularReward testreward = new RegularReward(Uppos.getX(), Uppos.getY(), 0, rewardValue);
        rewardlist.add(testreward);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a reward object to reward ArrayList in order for score
        //to get incremented for hero within processPlayerMovement
        //use 50 because we are testing for difficulty easy
        if (hero.getScore() != rewardValue) {
            assert (false);
        }
        //check that the tile is now a HERO object, not a REWARD object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }


    @Test
    public void testpickUpBonusEasyDif() {
        setup(Objects.BONUS, Difficulty.EASY);
        ArrayList<Bonus> bonuslist = objectData.getBonusArray();
        Bonus testbonus = new Bonus(Uppos.getX(), Uppos.getY(), 0, rewardValue * 2);
        bonuslist.add(testbonus);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //use 100 because difficulty easy is always 100
        if (hero.getScore() != rewardValue * 2) {
            assert (false);
        }


        //check that the tile is now a HERO object, not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testpickUpBonusMediumDif() {
        setup(Objects.BONUS, Difficulty.MEDIUM);
        ArrayList<Bonus> bonuslist = objectData.getBonusArray();
        Bonus testbonus = new Bonus(Uppos.getX(), Uppos.getY(), 0, rewardValue * 2);
        bonuslist.add(testbonus);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //use 100 because difficulty easy is always 100
        if (hero.getScore() != rewardValue * 2) {
            assert (false);
        }


        //check that the tile is now a HERO object, not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testpickUpBonusHardDif() {
        setup(Objects.BONUS, Difficulty.HARD);
        ArrayList<Bonus> bonuslist = objectData.getBonusArray();
        Bonus testbonus = new Bonus(Uppos.getX(), Uppos.getY(), 0, rewardValue * 2);
        bonuslist.add(testbonus);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //use 100 because difficulty easy is always 100
        if (hero.getScore() != rewardValue * 2) {
            assert (false);
        }


        //check that the tile is now a HERO object, not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }

    //only test for one direction because previous tests already cover
    //for hero moving in different directions
    @Test
    public void testpickUpTrapEasyDif() {
        setup(Objects.TRAP, Difficulty.EASY);
        ArrayList<Trap> traplist = objectData.getTrapArray();
        Trap testtrap = new Trap(Uppos.getX(), Uppos.getY(), 0, trapValue);
        traplist.add(testtrap);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //-50 for easy difficulty, the same test will cover for all difficulties
        if (hero.getScore() != -(trapValue)) {
            assert (false);
        }


        //check that the tile is now a HERO object, not a TRAP object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testpickUpTrapMediumDif() {
        setup(Objects.TRAP, Difficulty.MEDIUM);
        ArrayList<Trap> traplist = objectData.getTrapArray();
        Trap testtrap = new Trap(Uppos.getX(), Uppos.getY(), 0, trapValue);
        traplist.add(testtrap);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //-50 for easy difficulty, the same test will cover for all difficulties
        if (hero.getScore() != -(trapValue)) {
            assert (false);
        }


        //check that the tile is now a HERO object, not a TRAP object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testpickUpTrapHardDif() {
        setup(Objects.TRAP, Difficulty.HARD);
        ArrayList<Trap> traplist = objectData.getTrapArray();
        Trap testtrap = new Trap(Uppos.getX(), Uppos.getY(), 0, trapValue);
        traplist.add(testtrap);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //-50 for easy difficulty, the same test will cover for all difficulties
        if (hero.getScore() != -(trapValue)) {
            assert (false);
        }


        //check that the tile is now a HERO object, not a TRAP object
        if (boardData.getTypeAt(Uppos) != Objects.HERO) {
            assert (false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testMoveIntoTree() {
        setup(Objects.TREE, Difficulty.EASY);

        //check to move into any position
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);
        objectData.getHeroLogic().processPlayerMovement(Rightpos, objectData);
        objectData.getHeroLogic().processPlayerMovement(Downpos, objectData);
        //move into the same direction twice even when
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);


        //this would mean character moved but that's impossible since there are trees
        //in every direction
        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()) {
            assert (false);
        }
        //check that the surrounding tiles are all still TREE objects
        if (boardData.getTypeAt(Uppos) != Objects.TREE ||
                boardData.getTypeAt(Downpos) != Objects.TREE ||
                boardData.getTypeAt(Leftpos) != Objects.TREE ||
                boardData.getTypeAt(Rightpos) != Objects.TREE) {
            assert (false);
        }
        assert (true);

    }

    @Test
    public void testEnemyCollision() {
        setup(Objects.ENEMY, Difficulty.EASY);
        ArrayList<Enemy> enemylist = objectData.getEnemyArray();
        Enemy testenemy = new Enemy(Uppos.getX(), Uppos.getY());
        enemylist.add(testenemy);


        //case for when enemy moves towards enemy
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);
        //objectData.getEnemyLogic().processEnemyMovement(objectData);

        if (!objectData.getGameStats().getGameOver()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testMoveIntoDoorEasyNotWin() {
        setup(Objects.EXIT, Difficulty.EASY);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        exitDoor.setRewardCount(2);
        exitDoor.rewardCollected(Difficulty.EASY);
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);
        if (!exitDoor.isClosed()) {
            assert (false);
        }
        if (objectData.getGameStats().isGameWon()) {
            assert (false);
        }
        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testMoveIntoDoorMediumNotWin() {
        setup(Objects.EXIT, Difficulty.MEDIUM);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        exitDoor.setRewardCount(8);
        exitDoor.rewardCollected(Difficulty.MEDIUM);
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);
        if (!exitDoor.isClosed()) {
            assert (false);
        }
        if (objectData.getGameStats().isGameWon()) {
            assert (false);
        }
        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()) {
            assert (false);
        }
        assert (true);

    }

    @Test
    public void testMoveIntoDoorHardNotWin() {
        setup(Objects.EXIT, Difficulty.HARD);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        exitDoor.setRewardCount(13);
        exitDoor.rewardCollected(Difficulty.HARD);
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);
        if (!exitDoor.isClosed()) {
            assert (false);
        }
        if (objectData.getGameStats().isGameWon()) {
            assert (false);
        }

        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testWinEasy() {
        setup(Objects.EXIT, Difficulty.EASY);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        exitDoor.setRewardCount(4);
        exitDoor.rewardCollected(Difficulty.EASY);
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);
        if (exitDoor.isClosed()) {
            assert (false);
        }
        if (!objectData.getGameStats().isGameWon()) {
            assert (false);
        }
        if (hero.getX() != Leftpos.getX() || hero.getY() != Leftpos.getY()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testWinMedium() {
        setup(Objects.EXIT, Difficulty.MEDIUM);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        exitDoor.setRewardCount(9);
        exitDoor.rewardCollected(Difficulty.MEDIUM);
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);
        if (exitDoor.isClosed()) {
            assert (false);
        }
        if (hero.getX() != Leftpos.getX() || hero.getY() != Leftpos.getY()) {
            assert (false);
        }
        if (!objectData.getGameStats().isGameWon()) {
            assert (false);
        }
        assert (true);
    }

    @Test
    public void testWinHard() {
        setup(Objects.EXIT, Difficulty.HARD);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        exitDoor.setRewardCount(14);
        exitDoor.rewardCollected(Difficulty.HARD);
        objectData.getHeroLogic().processPlayerMovement(Leftpos, objectData);
        if (exitDoor.isClosed()) {
            assert (false);
        }
        if (!objectData.getGameStats().isGameWon()) {
            assert (false);
        }
        if (hero.getX() != Leftpos.getX() || hero.getY() != Leftpos.getY()) {
            assert (false);
        }
        assert (true);
    }


    @Test
    public void testHeroMoveIntoBush() {
        setup(Objects.BUSH, Difficulty.EASY);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);

        //will need to add a reward object to reward ArrayList in order for score
        //to get incremented for hero within processPlayerMovement
        //use 50 because we are testing for difficulty easy
        Assertions.assertEquals(objectData.getBoard().getTypeAt(Uppos), Objects.HEROHIDDEN);
        //check that the tile is now a HERO object, not a REWARD object
        int heroXpos = objectData.getHero().getX();
        int heroYpos = objectData.getHero().getY();
        Assertions.assertEquals(heroXpos == Uppos.getX() && heroYpos == Uppos.getY(), true);

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

        //check that the bush still exists in that position
        Assertions.assertEquals(boardData.getTypeAt(Uppos),Objects.BUSH);
        Assertions.assertEquals(boardData.getTypeAt(heropos),Objects.HERO);

    }

    @Test
    void NegativePointsTriggersGameLoss(){
        setup(Objects.TRAP, Difficulty.EASY);
        ArrayList<Trap> traplist = objectData.getTrapArray();
        Trap testtrap = new Trap(Uppos.getX(), Uppos.getY(), 0, trapValue);
        traplist.add(testtrap);
        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);
        System.out.print(hero.getScore());


        Assertions.assertEquals(objectData.getGameStats().getGameOver(), true);
    }

    @Test
    void CollectingAllRewardsOpensExit() {

        for (Difficulty dif : Difficulty.values()) {
            objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
            ArrayList<RegularReward> rewardlist = objectData.getRewardArray();
            for (int i = 0; i < rewardlist.size(); i++) {
                //pick up all rewards
                Position newpos = new Position(rewardlist.get(i).getX(), rewardlist.get(i).getY());
                objectData.getHeroLogic().processPlayerMovement(newpos, objectData);
            }
            Assertions.assertEquals(objectData.getExit().isClosed(), false);
        }
    }


}

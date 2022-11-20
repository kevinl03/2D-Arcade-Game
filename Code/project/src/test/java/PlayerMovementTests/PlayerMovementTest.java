package PlayerMovementTests;

import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.*;
import com.Game.ObjectData;
import com.Helpers.HeroColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
public class PlayerMovementTest {
    ObjectData objectData;
    BoardData boardData;

    Hero hero;

    Position heropos;

    Position Uppos;
    Position Downpos;
    Position Leftpos;
    Position Rightpos;

    int rewardValue;
    //int bonusValue;
    int trapValue;



    void setup(Objects object,Difficulty difficulty) {
        rewardValue=50;
    if(difficulty== Difficulty.EASY) {
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        trapValue=50;
    }
    else if(difficulty== Difficulty.MEDIUM) {
        objectData = new ObjectData(Difficulty.MEDIUM, HeroColor.BROWN);
        trapValue=100;
    }
    else{
        objectData = new ObjectData(Difficulty.HARD, HeroColor.BROWN);
        trapValue=200;
    }
        boardData = objectData.getBoard();
        hero = objectData.getHero();


        heropos = new Position(hero.getX(), hero.getY());

        Rightpos = new Position(heropos.getX() + 1, heropos.getY());
        Leftpos = new Position(heropos.getX() -1, heropos.getY());
        Uppos = new Position(heropos.getX(), heropos.getY() -1);
        Downpos = new Position(heropos.getX(), heropos.getY()+ 1);

        //set adjecent tiles to a certain object so that character movement can be tested
        boardData.setTypeAt(Rightpos, object);
        boardData.setTypeAt(Leftpos, object);
        boardData.setTypeAt(Uppos, object);
        boardData.setTypeAt(Downpos, object);

    }

    @Test
    void NonConstraintPlayerMovementUp(){
        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY,Difficulty.EASY);
        //movements are same for all difficulties => testing just one
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);

        if (boardData.getTypeAt(Uppos) != Objects.HERO ) {
            assert (false);
        }
        //set the previous position to empty
        if(boardData.getTypeAt(heropos) != Objects.EMPTY){
            assert(false);
        }
        //hero position class must also be updated
        if (hero.getX() != Uppos.getX() || hero.getY() != Uppos.getY()){
            assert(false);
        }
        assert(true);
    }

    @Test
    void NonConstraintPlayerMovementDown(){
        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY,Difficulty.EASY);
        //movements are same for all difficulties => testing just one
        objectData.getHeroLogic().processPlayerMovement(Downpos,objectData);

        if (boardData.getTypeAt(Downpos) != Objects.HERO ){
            assert(false);
        }
        //set the previous position to empty
        if(boardData.getTypeAt(heropos) != Objects.EMPTY){
            assert(false);
        }
        //hero position class must also be updated
        if (hero.getX() != Downpos.getX() || hero.getY() != Downpos.getY()){
            assert(false);
        }
        assert(true);
    }
    @Test
    void NonConstraintPlayerMovementLeft(){
        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY,Difficulty.EASY);
        //movements are same for all difficulties => testing just one
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);

        if (boardData.getTypeAt(Leftpos) != Objects.HERO ){
            assert(false);
        }
        //set the previous position to empty
        if(boardData.getTypeAt(heropos) != Objects.EMPTY){
            assert(false);
        }
        //hero position class must also be updated
        if (hero.getX() != Leftpos.getX() || hero.getY() != Leftpos.getY()){
            assert(false);
        }
        assert(true);
    }
    @Test
    void NonConstraintPlayerMovementRight(){
        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY,Difficulty.EASY);
        //movements are same for all difficulties => testing just one
        objectData.getHeroLogic().processPlayerMovement(Rightpos,objectData);

        if (boardData.getTypeAt(Rightpos) != Objects.HERO ){
            assert(false);
        }
        //set the previous position to empty
        if(boardData.getTypeAt(heropos) != Objects.EMPTY){
            assert(false);
        }
        //hero position class must also be updated
        if (hero.getX() != Rightpos.getX() || hero.getY() != Rightpos.getY()){
            assert(false);
        }
        assert(true);
    }

    @Test
    void pickUpRewardEasyDif(){
        setup(Objects.REWARD,Difficulty.EASY);
        ArrayList<RegularReward> rewardlist = objectData.getRewardArray();
        RegularReward testreward = new RegularReward(Uppos.getX(), Uppos.getY(), 0, rewardValue);
        rewardlist.add(testreward);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);

        //will need to add a reward object to reward ArrayList in order for score
        //to get incremented for hero within processPlayerMovement
        //use 50 because we are testing for difficulty easy
        if (hero.getScore() != rewardValue){
            assert(false);
        }
        //check that the tile is now a HERO object, not a REWARD object
        if (boardData.getTypeAt(Uppos) != Objects.HERO){
            assert(false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos,objectData);

        //check that reward is picked up and replaced with an empty tile
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY){
          assert(false);
        }
        assert(true);
    }

    @Test
    void pickUpRewardMediumDif(){
        setup(Objects.REWARD,Difficulty.MEDIUM);
        ArrayList<RegularReward> rewardlist = objectData.getRewardArray();
        RegularReward testreward = new RegularReward(Uppos.getX(), Uppos.getY(), 0, rewardValue);
        rewardlist.add(testreward);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);

        //will need to add a reward object to reward ArrayList in order for score
        //to get incremented for hero within processPlayerMovement
        //use 50 because we are testing for difficulty easy
        if (hero.getScore() != rewardValue){
            assert(false);
        }
        //check that the tile is now a HERO object, not a REWARD object
        if (boardData.getTypeAt(Uppos) != Objects.HERO){
            assert(false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos,objectData);

        //check that reward is picked up and replaced with an empty tile
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY){
            assert(false);
        }
        assert(true);
    }

    @Test
    void pickUpRewardHardDif(){
        setup(Objects.REWARD,Difficulty.HARD);
        ArrayList<RegularReward> rewardlist = objectData.getRewardArray();
        RegularReward testreward = new RegularReward(Uppos.getX(), Uppos.getY(), 0, rewardValue);
        rewardlist.add(testreward);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);

        //will need to add a reward object to reward ArrayList in order for score
        //to get incremented for hero within processPlayerMovement
        //use 50 because we are testing for difficulty easy
        if (hero.getScore() != rewardValue){
            assert(false);
        }
        //check that the tile is now a HERO object, not a REWARD object
        if (boardData.getTypeAt(Uppos) != Objects.HERO){
            assert(false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos,objectData);

        //check that reward is picked up and replaced with an empty tile
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY){
            assert(false);
        }
        assert(true);
    }


    @Test
    void pickUpBonusEasyDif(){
        setup(Objects.BONUS,Difficulty.EASY);
        ArrayList<Bonus> bonuslist = objectData.getBonusArray();
        Bonus testbonus = new Bonus(Uppos.getX(), Uppos.getY(), 0, rewardValue * 2);
        bonuslist.add(testbonus);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //use 100 because difficulty easy is always 100
        if (hero.getScore() != rewardValue * 2){
            assert(false);
        }


        //check that the tile is now a HERO object, not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.HERO){
            assert(false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos,objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY){
            assert(false);
        }
        assert(true);
    }

    @Test
    void pickUpBonusMediumDif(){
        setup(Objects.BONUS,Difficulty.MEDIUM);
        ArrayList<Bonus> bonuslist = objectData.getBonusArray();
        Bonus testbonus = new Bonus(Uppos.getX(), Uppos.getY(), 0, rewardValue * 2);
        bonuslist.add(testbonus);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //use 100 because difficulty easy is always 100
        if (hero.getScore() != rewardValue * 2){
            assert(false);
        }


        //check that the tile is now a HERO object, not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.HERO){
            assert(false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos,objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY){
            assert(false);
        }
        assert(true);
    }

    @Test
    void pickUpBonusHardDif(){
        setup(Objects.BONUS,Difficulty.HARD);
        ArrayList<Bonus> bonuslist = objectData.getBonusArray();
        Bonus testbonus = new Bonus(Uppos.getX(), Uppos.getY(), 0, rewardValue * 2);
        bonuslist.add(testbonus);

        //step over reward
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);

        //will need to add a Bonus object to Bonus ArrayList in order for score
        //to get incremented for hero within processPlayerMovement

        //use 100 because difficulty easy is always 100
        if (hero.getScore() != rewardValue * 2){
            assert(false);
        }


        //check that the tile is now a HERO object, not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.HERO){
            assert(false);
        }

        //step back to initiale position
        objectData.getHeroLogic().processPlayerMovement(heropos,objectData);

        //check that reward is picked up and replaced with an empty tile and that
        //the tile is not a BONUS object
        if (boardData.getTypeAt(Uppos) != Objects.EMPTY){
            assert(false);
        }
        assert(true);
    }

    //only test for one direction because previous tests already cover
    //for hero moving in different directions
    @Test
    void pickUpTrapEasyDif() {
        setup(Objects.TRAP,Difficulty.EASY);
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
    void pickUpTrapMediumDif() {
        setup(Objects.TRAP,Difficulty.MEDIUM);
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
    void pickUpTrapHardDif() {
        setup(Objects.TRAP,Difficulty.HARD);
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
    void MoveIntoTree(){
        setup(Objects.TREE,Difficulty.EASY);

        //check to move into any position
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);
        objectData.getHeroLogic().processPlayerMovement(Rightpos,objectData);
        objectData.getHeroLogic().processPlayerMovement(Downpos,objectData);
        //move into the same direction twice even when
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);



        //this would mean character moved but that's impossible since there are trees
        //in every direction
        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()){
            assert(false);
        }
        //check that the surrounding tiles are all still TREE objects
        if (boardData.getTypeAt(Uppos) != Objects.TREE ||
            boardData.getTypeAt(Downpos) != Objects.TREE ||
                boardData.getTypeAt(Leftpos) != Objects.TREE ||
                boardData.getTypeAt(Rightpos) != Objects.TREE){
            assert(false);
        }
        assert(true);

    }
    @Test
    void EnemyCollision(){
        setup(Objects.ENEMY,Difficulty.EASY);
        ArrayList<Enemy> enemylist = objectData.getEnemyArray();
        Enemy testenemy = new Enemy(Uppos.getX(), Uppos.getY());
        enemylist.add(testenemy);


        //case for when enemy moves towards enemy
        objectData.getHeroLogic().processPlayerMovement(Uppos, objectData);
        //objectData.getEnemyLogic().processEnemyMovement(objectData);

        if (!objectData.getGameStats().getGameOver()){
            assert(false);
        }
        assert(true);
    }

    @Test
    void MoveIntoDoorEasyNotWin(){
        setup(Objects.EXIT, Difficulty.EASY);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);
        exitDoor.setRewardCount(2);
        exitDoor.rewardCollected(Difficulty.EASY);
        if( ! exitDoor.isClosed()){
            assert(false);
        }
        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()){
            assert(false);
        }
        assert(true);
    }

    @Test
    void MoveIntoDoorMediumNotWin(){
        setup(Objects.EXIT, Difficulty.MEDIUM);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);
        exitDoor.setRewardCount(8);
        exitDoor.rewardCollected(Difficulty.MEDIUM);
        if( ! exitDoor.isClosed()){
            assert(false);
        }
        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()){
            assert(false);
        }
        assert(true);

    }

    @Test
    void MoveIntoDoorHardNotWin(){
        setup(Objects.EXIT, Difficulty.HARD);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);
        exitDoor.setRewardCount(13);
        exitDoor.rewardCollected(Difficulty.HARD);
        if( ! exitDoor.isClosed()){
            assert(false);
        }
        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()){
            assert(false);
        }
        assert(true);
    }

    @Test
    void WinEasy(){
        setup(Objects.EXIT, Difficulty.EASY);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);
        exitDoor.setRewardCount(4);
        exitDoor.rewardCollected(Difficulty.EASY);
        if(exitDoor.isClosed()){
            assert(false);
        }

        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()){
            assert(false);
        }
        assert(true);
    }

    @Test
    void WinMedium(){
        setup(Objects.EXIT, Difficulty.MEDIUM);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);
        exitDoor.setRewardCount(9);
        exitDoor.rewardCollected(Difficulty.MEDIUM);
        if(exitDoor.isClosed()){
            assert(false);
        }

        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()){
            assert(false);
        }
        assert(true);
    }

    @Test
    void WinHard(){
        setup(Objects.EXIT, Difficulty.HARD);
        Exit exitDoor = objectData.getExit();
        exitDoor.setX(Leftpos.getX());
        exitDoor.setY(Leftpos.getY());
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);
        exitDoor.setRewardCount(14);
        exitDoor.rewardCollected(Difficulty.HARD);
        if(exitDoor.isClosed()){
            assert(false);
        }

        if (hero.getX() != heropos.getX() || heropos.getY() != heropos.getY()){
            assert(false);
        }
        assert(true);
    }

}

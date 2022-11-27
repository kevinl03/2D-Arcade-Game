package EnemiesCountTest;

import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.Enemy;
import com.Entities.Hero;
import com.Entities.Position;
import com.Game.ObjectData;
import com.Helpers.HeroColor;
import org.junit.jupiter.api.*;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemiesCountTest {

    ObjectData objectData;
    BoardData boardData;
    Objects objectArray[][];
    ArrayList<Enemy> enemies;


    @BeforeEach
    void cleanTest(){
        objectData = null;
        boardData = null;
        objectArray = null;
        ArrayList<Enemy> enemies = null;
    }
    ////////////////////// Testing correct amount of enemies in map boundary //////////////////////////////////////////
    @Test
    public void testEnemyEasyCount() {
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();

        int result = 0;
        for (int i = 1; i < 24; i++) {
            for (int j = 1; j < 14; j++) {
                if (objectArray[i][j] == Objects.ENEMY) {
                    result++;
                }
            }
        }
        assertEquals(1, result);
    }

    @Test
    public void testEnemyMediumCount() {
        objectData = new ObjectData(Difficulty.MEDIUM, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();

        int result = 0;
        for (int i = 1; i < 24; i++) {
            for (int j = 1; j < 14; j++) {
                if (objectArray[i][j] == Objects.ENEMY) {
                    result++;
                }
            }
        }
        assertEquals(2, result);
    }

    @Test
    public void testEnemyHardCount() {
        objectData = new ObjectData(Difficulty.HARD, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();

        int result = 0;
        for (int i = 1; i < 24; i++) {
            for (int j = 1; j < 14; j++) {
                if (objectArray[i][j] == Objects.ENEMY) {
                    result++;
                }
            }
        }
        assertEquals(3, result);
    }

    @Test
    public void testTrapEasyCount() {
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();

        int result = 0;
        for (int i = 1; i < 24; i++) {
            for (int j = 1; j < 14; j++) {
                if (objectArray[i][j] == Objects.TRAP) {
                    result++;
                }
            }
        }
        assertEquals(4, result);
    }

    @Test
    public void testTrapMediumCount() {
        objectData = new ObjectData(Difficulty.MEDIUM, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();

        int result = 0;
        for (int i = 1; i < 24; i++) {
            for (int j = 1; j < 14; j++) {
                if (objectArray[i][j] == Objects.TRAP) {
                    result++;
                }
            }
        }
        assertEquals(7, result);
    }

    @Test
    public void testTrapHardCount() {
        objectData = new ObjectData(Difficulty.HARD, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();

        int result = 0;
        for (int i = 1; i < 24; i++) {
            for (int j = 1; j < 14; j++) {
                if (objectArray[i][j] == Objects.TRAP) {
                    result++;
                }
            }
        }
        assertEquals(11, result);
    }

    /////////////////////////////////Enemy moving across reward/////////////////////////////////////
    @Test
    public void testEnemyonReward(){
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        boardData = objectData.getBoard();
        Hero hero = objectData.getHero();
        Position heropos = new Position(hero.getX(), hero.getY());
        boolean tester = true;
        //                             WEST
        //               y0   y1   y2   y3   y4   y5   y6 ...
        /*     x0  */
        /*  N  x1  */                                           //  S
        /*  O  x2  */                                           //  O
        /*  R  x3  */                                           //  U
        /*  T  x4  */                                           //  T
        /*  H  x5  */                                           //  H
        /*     ..  */
        //                             EAST

        // Set enemy -> reward -> space -> hero
        if(hero.getY()>12) {
            Position Uppos = new Position(heropos.getX(), heropos.getY() - 1);
            Position Upuppos = new Position(heropos.getX(), heropos.getY() - 2);
            Position Upupuppos = new Position(heropos.getX(), heropos.getY() - 3);
            boardData.setTypeAt(Uppos, Objects.EMPTY);
            boardData.setTypeAt(Upuppos, Objects.REWARD);
            ArrayList<Enemy> enemylist = objectData.getEnemyArray();
            Enemy testenemy = new Enemy(Upupuppos.getX(), Upupuppos.getY());
            enemylist.add(testenemy);
            //let enemy move once onto reward
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Upuppos) != Objects.ENEMYANDREWARD) || (boardData.getTypeAt(Upupuppos) != Objects.EMPTY) ){
                tester = false;
                assert(tester);
            }
            //let enemy move once off of reward
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Upuppos) != Objects.REWARD) || (boardData.getTypeAt(Uppos) != Objects.ENEMY) ){
                tester = false;
                assert(tester);
            }
        }
        else{
            Position Downpos = new Position(heropos.getX(), heropos.getY()+ 1);
            Position Downdownpos = new Position(heropos.getX(), heropos.getY()+ 2);
            Position Downdowndownpos = new Position(heropos.getX(), heropos.getY()+ 3);
            boardData.setTypeAt(Downpos, Objects.EMPTY);
            boardData.setTypeAt(Downdownpos, Objects.REWARD);
            ArrayList<Enemy> enemylist = objectData.getEnemyArray();
            Enemy testenemy = new Enemy(Downdowndownpos.getX(), Downdowndownpos.getY());
            enemylist.add(testenemy);
            //let enemy move once onto reward
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Downdownpos) != Objects.ENEMYANDREWARD) || (boardData.getTypeAt(Downdowndownpos) != Objects.EMPTY) ){
                tester = false;
                assert(tester);
            }
            //let enemy move once off of reward
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Downdownpos) != Objects.REWARD) || (boardData.getTypeAt(Downpos) != Objects.ENEMY) ){
                tester = false;
                assert(tester);
            }
        }

        assert(tester);
    }

    @Test
    public void testEnemyonBonusReward(){
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        boardData = objectData.getBoard();
        Hero hero = objectData.getHero();
        Position heropos = new Position(hero.getX(), hero.getY());
        boolean tester = true;
        //                             WEST
        //               y0   y1   y2   y3   y4   y5   y6 ...
        /*     x0  */
        /*  N  x1  */                                           //  S
        /*  O  x2  */                                           //  O
        /*  R  x3  */                                           //  U
        /*  T  x4  */                                           //  T
        /*  H  x5  */                                           //  H
        /*     ..  */
        //                             EAST

        // Set enemy -> Bonus reward -> space -> hero
        if(hero.getY()>12) {
            Position Uppos = new Position(heropos.getX(), heropos.getY() - 1);
            Position Upuppos = new Position(heropos.getX(), heropos.getY() - 2);
            Position Upupuppos = new Position(heropos.getX(), heropos.getY() - 3);
            boardData.setTypeAt(Uppos, Objects.EMPTY);
            boardData.setTypeAt(Upuppos, Objects.BONUS);
            ArrayList<Enemy> enemylist = objectData.getEnemyArray();
            Enemy testenemy = new Enemy(Upupuppos.getX(), Upupuppos.getY());
            enemylist.add(testenemy);
            //let enemy move once onto bonus reward
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Upuppos) != Objects.ENEMYANDBONUS) || (boardData.getTypeAt(Upupuppos) != Objects.EMPTY) ){
                tester = false;
                assert(tester);
            }
            //let enemy move once off of bonus reward
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Upuppos) != Objects.BONUS) || (boardData.getTypeAt(Uppos) != Objects.ENEMY) ){
                tester = false;
                assert(tester);
            }
        }
        else{
            Position Downpos = new Position(heropos.getX(), heropos.getY()+ 1);
            Position Downdownpos = new Position(heropos.getX(), heropos.getY()+ 2);
            Position Downdowndownpos = new Position(heropos.getX(), heropos.getY()+ 3);
            boardData.setTypeAt(Downpos, Objects.EMPTY);
            boardData.setTypeAt(Downdownpos, Objects.BONUS);
            ArrayList<Enemy> enemylist = objectData.getEnemyArray();
            Enemy testenemy = new Enemy(Downdowndownpos.getX(), Downdowndownpos.getY());
            enemylist.add(testenemy);
            //let enemy move once onto bonus reward
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Downdownpos) != Objects.ENEMYANDBONUS) || (boardData.getTypeAt(Downdowndownpos) != Objects.EMPTY) ){
                tester = false;
                assert(tester);
            }
            //let enemy move once off of bonus reward
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Downdownpos) != Objects.BONUS) || (boardData.getTypeAt(Downpos) != Objects.ENEMY) ){
                tester = false;
                assert(tester);
            }
        }

        assert(tester);
    }

    @Test
    public void testEnemyonBush(){
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        boardData = objectData.getBoard();
        Hero hero = objectData.getHero();
        Position heropos = new Position(hero.getX(), hero.getY());
        boolean tester = true;
        //                             WEST
        //               y0   y1   y2   y3   y4   y5   y6 ...
        /*     x0  */
        /*  N  x1  */                                           //  S
        /*  O  x2  */                                           //  O
        /*  R  x3  */                                           //  U
        /*  T  x4  */                                           //  T
        /*  H  x5  */                                           //  H
        /*     ..  */
        //                             EAST

        // Set enemy -> bush -> space -> hero
        if(hero.getY()>12) {
            Position Uppos = new Position(heropos.getX(), heropos.getY() - 1);
            Position Upuppos = new Position(heropos.getX(), heropos.getY() - 2);
            Position Upupuppos = new Position(heropos.getX(), heropos.getY() - 3);
            boardData.setTypeAt(Uppos, Objects.EMPTY);
            boardData.setTypeAt(Upuppos, Objects.BUSH);
            ArrayList<Enemy> enemylist = objectData.getEnemyArray();
            Enemy testenemy = new Enemy(Upupuppos.getX(), Upupuppos.getY());
            enemylist.add(testenemy);
            //let enemy move once onto bush
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Upuppos) != Objects.ENEMYANDBUSH) || (boardData.getTypeAt(Upupuppos) != Objects.EMPTY) ){
                tester = false;
                assert(tester);
            }
            //let enemy move once off of bush
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Upuppos) != Objects.BUSH) || (boardData.getTypeAt(Uppos) != Objects.ENEMY) ){
                tester = false;
                assert(tester);
            }
        }
        else{
            Position Downpos = new Position(heropos.getX(), heropos.getY()+ 1);
            Position Downdownpos = new Position(heropos.getX(), heropos.getY()+ 2);
            Position Downdowndownpos = new Position(heropos.getX(), heropos.getY()+ 3);
            boardData.setTypeAt(Downpos, Objects.EMPTY);
            boardData.setTypeAt(Downdownpos, Objects.BUSH);
            ArrayList<Enemy> enemylist = objectData.getEnemyArray();
            Enemy testenemy = new Enemy(Downdowndownpos.getX(), Downdowndownpos.getY());
            enemylist.add(testenemy);
            //let enemy move once onto bush
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Downdownpos) != Objects.ENEMYANDBUSH) || (boardData.getTypeAt(Downdowndownpos) != Objects.EMPTY) ){
                tester = false;
                assert(tester);
            }
            //let enemy move once off of bush
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Downdownpos) != Objects.BUSH) || (boardData.getTypeAt(Downpos) != Objects.ENEMY) ){
                tester = false;
                assert(tester);
            }
        }

        assert(tester);
    }

    @Test
    public void testEnemyonTrap(){
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        boardData = objectData.getBoard();
        Hero hero = objectData.getHero();
        Position heropos = new Position(hero.getX(), hero.getY());
        boolean tester = true;
        //                             WEST
        //               y0   y1   y2   y3   y4   y5   y6 ...
        /*     x0  */
        /*  N  x1  */                                           //  S
        /*  O  x2  */                                           //  O
        /*  R  x3  */                                           //  U
        /*  T  x4  */                                           //  T
        /*  H  x5  */                                           //  H
        /*     ..  */
        //                             EAST

        // Set enemy -> trap -> space -> hero
        if(hero.getY()>12) {
            Position Uppos = new Position(heropos.getX(), heropos.getY() - 1);
            Position Upuppos = new Position(heropos.getX(), heropos.getY() - 2);
            Position Upupuppos = new Position(heropos.getX(), heropos.getY() - 3);
            boardData.setTypeAt(Uppos, Objects.EMPTY);
            boardData.setTypeAt(Upuppos, Objects.TRAP);
            ArrayList<Enemy> enemylist = objectData.getEnemyArray();
            Enemy testenemy = new Enemy(Upupuppos.getX(), Upupuppos.getY());
            enemylist.add(testenemy);
            //let enemy move once onto bush
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Upuppos) != Objects.ENEMYANDTRAP) || (boardData.getTypeAt(Upupuppos) != Objects.EMPTY) ){
                tester = false;
                assert(tester);
            }
            //let enemy move once off of bush
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Upuppos) != Objects.TRAP) || (boardData.getTypeAt(Uppos) != Objects.ENEMY) ){
                tester = false;
                assert(tester);
            }
        }
        else{
            Position Downpos = new Position(heropos.getX(), heropos.getY()+ 1);
            Position Downdownpos = new Position(heropos.getX(), heropos.getY()+ 2);
            Position Downdowndownpos = new Position(heropos.getX(), heropos.getY()+ 3);
            boardData.setTypeAt(Downpos, Objects.EMPTY);
            boardData.setTypeAt(Downdownpos, Objects.TRAP);
            ArrayList<Enemy> enemylist = objectData.getEnemyArray();
            Enemy testenemy = new Enemy(Downdowndownpos.getX(), Downdowndownpos.getY());
            enemylist.add(testenemy);
            //let enemy move once onto bush
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Downdownpos) != Objects.ENEMYANDTRAP) || (boardData.getTypeAt(Downdowndownpos) != Objects.EMPTY) ){
                tester = false;
                assert(tester);
            }
            //let enemy move once off of bush
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            if( (boardData.getTypeAt(Downdownpos) != Objects.TRAP) || (boardData.getTypeAt(Downpos) != Objects.ENEMY) ){
                tester = false;
                assert(tester);
            }
        }

        assert(tester);
    }

}

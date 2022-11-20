package EnemiesCountTest;

import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.Enemy;
import com.Entities.Hero;
import com.Entities.Position;
import com.Game.ObjectData;
import com.Helpers.HeroColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemiesCountTest {

    ObjectData objectData;
    BoardData boardData;
    Objects objectArray[][];


    ////////////////////// Testing correct amount of enemies in map boundary //////////////////////////////////////////
    @Test
    void EnemyEasyCount() {
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
    void EnemyMediumCount() {
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
    void EnemyHardCount() {
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
    void TrapEasyCount() {
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
    void TrapMediumCount() {
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
    void TrapHardCount() {
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

    //////////////////Enemy moving at each tick///////////////////////////////////
    @Test
    void IsEnemyMovingEasy(){
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();
        ArrayList<Enemy> enemies = objectData.getEnemyArray();
        Enemy oneEnemy = enemies.get(0);
        Hero hero = objectData.getHero();
        Position heropos = new Position(hero.getX(), hero.getY());

        int oldX;
        int oldY;
        int newX = oneEnemy.getX();
        int newY = oneEnemy.getY();

        boolean result = true;

        // Hero not moving, only enemy is moving
        while (!objectData.getGameStats().getGameOver()){
            objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

            //Old values passed
            oldX = newX;
            oldY = newY;
            //Compare new enemy movement to old one
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            newX = oneEnemy.getX();
            newY = oneEnemy.getY();
            if( (oldX == newX) && (oldY == newY) ){
                result = false;
            }
        }
        assert(result);
    }

    @Test
    void IsEnemyMovingMedium(){
        objectData = new ObjectData(Difficulty.MEDIUM, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();
        ArrayList<Enemy> enemies = objectData.getEnemyArray();
        Enemy oneEnemy = enemies.get(0);
        Enemy twoEnemy = enemies.get(1);
        Hero hero = objectData.getHero();
        Position heropos = new Position(hero.getX(), hero.getY());

        int oldX1;
        int oldY1;
        int newX1 = oneEnemy.getX();
        int newY1 = oneEnemy.getY();
        int oldX2;
        int oldY2;
        int newX2 = twoEnemy.getX();
        int newY2 = twoEnemy.getY();

        boolean result = true;

        // Hero not moving, only enemy is moving
        while (!objectData.getGameStats().getGameOver()) {
            objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

            //Old values passed
            oldX1 = newX1;
            oldY1 = newY1;
            oldX2 = newX2;
            oldY2 = newY2;

            //Compare new enemy movement to old one
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            newX1 = oneEnemy.getX();
            newY1 = oneEnemy.getY();
            newX2 = twoEnemy.getX();
            newY2 = twoEnemy.getY();
            if ((oldX1 == newX1) && (oldY1 == newY1)) {
                result = false;
            }
            if ((oldX2 == newX2) && (oldY2 == newY2)) {
                result = false;
            }
        }
        assert(result);
    }

    @Test
    void IsEnemyMovingHard(){
        objectData = new ObjectData(Difficulty.HARD, HeroColor.BROWN);
        boardData = objectData.getBoard();
        objectArray = boardData.getBoardData();
        ArrayList<Enemy> enemies = objectData.getEnemyArray();
        Enemy oneEnemy = enemies.get(0);
        Enemy twoEnemy = enemies.get(1);
        Enemy thirdEnemy = enemies.get(2);
        Hero hero = objectData.getHero();
        Position heropos = new Position(hero.getX(), hero.getY());

        int oldX1;
        int oldY1;
        int newX1 = oneEnemy.getX();
        int newY1 = oneEnemy.getY();
        int oldX2;
        int oldY2;
        int newX2 = twoEnemy.getX();
        int newY2 = twoEnemy.getY();
        int oldX3;
        int oldY3;
        int newX3 = thirdEnemy.getX();
        int newY3 = thirdEnemy.getY();

        boolean result = true;

        // Hero not moving, only enemy is moving
        while (!objectData.getGameStats().getGameOver()) {
            objectData.getHeroLogic().processPlayerMovement(heropos, objectData);

            //Old values passed
            oldX1 = newX1;
            oldY1 = newY1;
            oldX2 = newX2;
            oldY2 = newY2;
            oldX3 = newX3;
            oldY3 = newY3;

            //Compare new enemy movement to old one
            objectData.getEnemyLogic().processEnemyMovement(objectData);
            newX1 = oneEnemy.getX();
            newY1 = oneEnemy.getY();
            newX2 = twoEnemy.getX();
            newY2 = twoEnemy.getY();
            newX3 = thirdEnemy.getX();
            newY3 = thirdEnemy.getY();
            if ((oldX1 == newX1) && (oldY1 == newY1)) {
                result = false;
            }
            if ((oldX2 == newX2) && (oldY2 == newY2)) {
                result = false;
            }
            if ((oldX3 == newX3) && (oldY3 == newY3)) {
                result = false;
            }
        }
        assert(result);
    }

}

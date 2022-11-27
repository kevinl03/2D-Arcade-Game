package EnemyMovementTests;

import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.*;
import com.Game.ObjectData;
import com.Helpers.Direction;
import com.Helpers.HeroColor;
import com.Logic.EnemyLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class EnemyMovementTest
{
    @Test
    public void testUnblockedShortestPathSouth(){
        EnemyLogic enemyLogic = new EnemyLogic();
        Objects [][] fakeMap= {
                //                             WEST
                //               y0   y1   y2   y3   y4   y5   y6   y7
                /*     x0  */  {Objects.TREE, Objects.TREE ,  Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                /*  N  x1  */  {Objects.TREE, Objects.EMPTY,  Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  S
                /*  O  x2  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  O
                /*  R  x3  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.ENEMY, Objects.EMPTY, Objects.HERO , Objects.TREE},  //  U
                /*  T  x4  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.TREE},  //  T
                /*  H  x5  */  {Objects.TREE, Objects.EMPTY, Objects.EMPTY , Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
                /*     x6  */  {Objects.TREE, Objects.TREE ,  Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                //                             EAST

        };

        Direction dir = enemyLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.SOUTH, dir);
    }

    @Test
    public void testUnblockedShortestPathEast(){
        EnemyLogic enemyLogic = new EnemyLogic();
        Objects [][] fakeMap= {
                //                             WEST
                //               y0   y1   y2   y3   y4   y5   y6   y7
                /*     x0  */  {Objects.TREE, Objects.TREE ,  Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                /*  N  x1  */  {Objects.TREE, Objects.EMPTY,  Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  S
                /*  O  x2  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  O
                /*  R  x3  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.ENEMY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  U
                /*  T  x4  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.EMPTY , Objects.TREE , Objects.EMPTY, Objects.TREE},  // T
                /*  H  x5  */  {Objects.TREE, Objects.EMPTY, Objects.EMPTY , Objects.EMPTY, Objects.HERO , Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
                /*     x6  */  {Objects.TREE, Objects.TREE ,  Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                //                             EAST

        };

        Direction dir = enemyLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.EAST, dir);
    }

    @Test
    public void testUnblockedShortestPathNorth(){
        EnemyLogic enemyLogic = new EnemyLogic();
        Objects [][] fakeMap= {
                //                             WEST
                //               y0   y1   y2   y3   y4   y5   y6   y7
                /*     x0  */  {Objects.TREE, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                /*  N  x1  */  {Objects.TREE, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  S
                /*  O  x2  */  {Objects.TREE, Objects.EMPTY, Objects.TREE , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  O
                /*  R  x3  */  {Objects.TREE, Objects.EMPTY, Objects.HERO , Objects.EMPTY, Objects.ENEMY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  U
                /*  T  x4  */  {Objects.TREE, Objects.EMPTY, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.TREE},  //  T
                /*  H  x5  */  {Objects.TREE, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
                /*     x6  */  {Objects.TREE, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                //                             EAST

        };

        Direction dir = enemyLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.NORTH, dir);
    }

    @Test
    public void testUnblockedShortestPathWest(){
        EnemyLogic enemyLogic = new EnemyLogic();
        Objects [][] fakeMap= {
                //                             WEST
                //               y0   y1   y2   y3   y4   y5   y6   y7
                /*     x0  */  {Objects.TREE, Objects.TREE ,  Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                /*  N  x1  */  {Objects.TREE, Objects.EMPTY,  Objects.EMPTY, Objects.EMPTY, Objects.HERO, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //   S
                /*  O  x2  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.EMPTY , Objects.EMPTY, Objects.EMPTY, Objects.TREE},  // O
                /*  R  x3  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.ENEMY, Objects.EMPTY, Objects.EMPTY , Objects.TREE},  // U
                /*  T  x4  */  {Objects.TREE, Objects.EMPTY, Objects.TREE  , Objects.TREE , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.TREE},  //  T
                /*  H  x5  */  {Objects.TREE, Objects.EMPTY, Objects.EMPTY , Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
                /*     x6  */  {Objects.TREE, Objects.TREE ,  Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                //                             EAST

        };

        Direction dir = enemyLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.WEST, dir);
    }

    @Test
    public void testblockedShortestPathBasic(){
        EnemyLogic enemyLogic = new EnemyLogic();
        Objects [][] fakeMap= {
                //                             WEST
                //               y0   y1   y2   y3   y4   y5   y6   y7
                /*     x0  */  {Objects.TREE, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                /*  N  x1  */  {Objects.TREE, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  S
                /*  O  x2  */  {Objects.TREE, Objects.EMPTY, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.TREE},  //  O
                /*  R  x3  */  {Objects.TREE, Objects.EMPTY, Objects.HERO , Objects.EMPTY, Objects.ENEMY, Objects.TREE , Objects.HERO , Objects.TREE},  //  U
                /*  T  x4  */  {Objects.TREE, Objects.EMPTY, Objects.TREE , Objects.EMPTY , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.TREE},  //  T
                /*  H  x5  */  {Objects.TREE, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
                /*     x6  */  {Objects.TREE, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                //                             EAST
        };

        Direction dir = enemyLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.NORTH, dir);
    }

    @Test
    public void testblockedShortestPathAdvanced(){
        EnemyLogic enemyLogic = new EnemyLogic();
        Objects [][] fakeMap= {
                //                             WEST
                //               y0   y1   y2   y3   y4   y5   y6   y7
                /*     x0  */  {Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                /*  N  x1  */  {Objects.EMPTY, Objects.HERO , Objects.TREE , Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  S
                /*  O  x2  */  {Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE},  //  O
                /*  R  x3  */  {Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.EMPTY, Objects.ENEMY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  U
                /*  T  x4  */  {Objects.EMPTY, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.TREE},  //  T
                /*  H  x5  */  {Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
                /*     x6  */  {Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                //                             EAST
        };

        Direction dir = enemyLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.SOUTH, dir);
    }

    @Test
    public void testRandomMovementWhenHeroHiddenAndFar(){
        EnemyLogic enemyLogic = new EnemyLogic();
        Objects [][] fakeMap= {
                //                             WEST
                //               y0   y1   y2   y3   y4   y5   y6   y7
                /*     x0  */  {Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                /*  N  x1  */  {Objects.EMPTY, Objects.HEROHIDDEN , Objects.TREE , Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  S
                /*  O  x2  */  {Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE},  //  O
                /*  R  x3  */  {Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.EMPTY, Objects.ENEMY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  U
                /*  T  x4  */  {Objects.EMPTY, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.TREE},  //  T
                /*  H  x5  */  {Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
                /*     x6  */  {Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                //                             EAST
        };

        Direction dir = enemyLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.RANDOM, dir);


    }

    @Test
    public void testRegularMovementWhenHeroHiddenAndClose(){
        EnemyLogic enemyLogic = new EnemyLogic();
        Objects [][] fakeMap= {
                //                             WEST
                //               y0   y1   y2   y3   y4   y5   y6   y7
                /*     x0  */  {Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                /*  N  x1  */  {Objects.EMPTY, Objects.EMPTY , Objects.TREE , Objects.EMPTY, Objects.HEROHIDDEN, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  S
                /*  O  x2  */  {Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.TREE},  //  O
                /*  R  x3  */  {Objects.EMPTY, Objects.TREE , Objects.EMPTY, Objects.EMPTY, Objects.ENEMY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  U
                /*  T  x4  */  {Objects.EMPTY, Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.EMPTY, Objects.TREE},  //  T
                /*  H  x5  */  {Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
                /*     x6  */  {Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE , Objects.TREE},
                //                             EAST
        };

        Direction dir = enemyLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.WEST, dir);
    }


    @Test
    public void testConfirmEnemyMoved(){

        ObjectData gameObjectData = new ObjectData(Difficulty.HARD, HeroColor.BROWN);

        EnemyLogic enemyLogic = gameObjectData.getEnemyLogic();

        ArrayList<Enemy> enemies = gameObjectData.getEnemies();

        ArrayList<Position> enemyPositions = new ArrayList<>();

        for (Position enemy: enemies) {
            Position tempPos = new Position(enemy.getX(), enemy.getY());
            enemyPositions.add(tempPos);

        }

        enemyLogic.processEnemyMovement(gameObjectData);

        Assertions.assertFalse(enemies.equals( enemyPositions));

    }


    @Test
    public void testGenerateRandomDirection(){

        ObjectData gameObjectData = new ObjectData(Difficulty.HARD, HeroColor.BROWN);

        EnemyLogic enemyLogic = gameObjectData.getEnemyLogic();

        Hero hero = gameObjectData.getHero();

        ArrayList<Enemy> enemies = gameObjectData.getEnemies();

        BoardData board = gameObjectData.getBoard();

        board.setTypeAt(hero, Objects.HEROHIDDEN);

        hero.setAtBush(true);
        hero.setHidden(true);

        ArrayList<Position> enemyPositions = new ArrayList<>();

        for (Position enemy: enemies) {
            Position tempPos = new Position(enemy.getX(), enemy.getY());
            enemyPositions.add(tempPos);

        }

        enemyLogic.processEnemyMovement(gameObjectData);

        Assertions.assertFalse(enemies.equals( enemyPositions));

    }

}

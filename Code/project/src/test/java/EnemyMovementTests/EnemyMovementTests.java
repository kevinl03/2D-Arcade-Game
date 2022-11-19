package EnemyMovementTests;

import Board.BoardData;
import Board.Difficulty;
import Board.Objects;
import Entities.*;
import Game.ObjectData;
import Helpers.Direction;
import Helpers.HeroColor;
import Logic.EnemyLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class EnemyMovementTests
{
    @Test
    void UnblockedShortestPathSouth(){
        EnemyLogic gameLogic = new EnemyLogic();
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

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.SOUTH, dir);
    }

    @Test
    void UnblockedShortestPathEast(){
        EnemyLogic gameLogic = new EnemyLogic();
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

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.EAST, dir);
    }

    @Test
    void UnblockedShortestPathNorth(){
        EnemyLogic gameLogic = new EnemyLogic();
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

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.NORTH, dir);
    }

    @Test
    void UnblockedShortestPathWest(){
        EnemyLogic gameLogic = new EnemyLogic();
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

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.WEST, dir);
    }

    @Test
    void blockedShortestPathBasic(){
        EnemyLogic gameLogic = new EnemyLogic();
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

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.NORTH, dir);
    }

    @Test
    void blockedShortestPathAdvanced(){
        EnemyLogic gameLogic = new EnemyLogic();
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

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.SOUTH, dir);
    }

    @Test
    void RandomMovementWhenHeroHiddenAndFar(){
        EnemyLogic gameLogic = new EnemyLogic();
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

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.RANDOM, dir);
    }

    @Test
    void RegularMovementWhenHeroHiddenAndClose(){
        EnemyLogic gameLogic = new EnemyLogic();
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

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 4));

        Assertions.assertEquals(Direction.WEST, dir);
    }


    @Test
    void ConfirmEnemyMoved(){

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


}

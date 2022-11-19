package EnemyMovementTests;

import Board.Objects;
import Entities.*;
import Helpers.Direction;
import Logic.EnemyLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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


}

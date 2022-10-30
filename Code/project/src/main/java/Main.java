import Board.BoardData;
import Game.ObjectData;
import Helpers.Stopwatch;
import Logic.EnemyLogic;
import Entities.Position;
import Helpers.Direction;
import Board.Objects;

import java.util.Arrays;

public class Main {

    public static void testShortestPath(){
        EnemyLogic gameLogic = new EnemyLogic();
        Objects [][] fakeMap= {
        //                             WEST
        //               y0   y1   y2   y3   y4   y5   y6   y7
        /*     x0  */  {Objects.TREE, Objects.TREE,  Objects.TREE, Objects.TREE, Objects.TREE, Objects.TREE, Objects.TREE, Objects.TREE},
        /*  S  x1  */  {Objects.TREE, Objects.HERO,  Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  N
        /*  O  x2  */  {Objects.TREE, Objects.EMPTY, Objects.TREE, Objects.TREE, Objects.TREE, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  O
        /*  U  x3  */  {Objects.TREE, Objects.EMPTY, Objects.TREE, Objects.TREE, Objects.EMPTY, Objects.ENEMY, Objects.EMPTY, Objects.TREE},  //  R
        /*  T  x4  */  {Objects.TREE, Objects.EMPTY, Objects.TREE, Objects.TREE, Objects.TREE, Objects.TREE, Objects.EMPTY, Objects.TREE},  //  T
        /*  H  x5  */  {Objects.TREE, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.EMPTY, Objects.TREE},  //  H
        /*     x6  */  {Objects.TREE, Objects.TREE,  Objects.TREE, Objects.TREE, Objects.TREE, Objects.TREE, Objects.TREE, Objects.TREE},
        //                             EAST

        };

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 5));

        System.out.println(dir);
    }

    public static void testStopwatch() throws InterruptedException {
        ObjectData data = new ObjectData();

        Stopwatch time = data.getTime();

        time.startTime();
        int counter = 0;
        while(true){
            Thread.sleep(1000);
            counter++;
            System.out.println(time.getElapsedTime().getSeconds());
            if(counter > 10) System.out.println(time.getFinalTime().getSeconds());
            if(counter == 10){
                time.stopTime();
            }
        }
    }

    public static void testBoardGen(){

        BoardData board = new BoardData();

        board.initialiseBoard();

//        System.out.println(Arrays.deepToString(board.getBoardData()));
        System.out.println(Arrays.deepToString(board.getBoardData()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));





    }

    public static void main (String[] args) throws InterruptedException {


//        testShortestPath();
//        testStopwatch();
        for (int i = 0; i < 100000; i++) {
            testBoardGen();
        }


    }
}

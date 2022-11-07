import Board.BoardData;
import Board.Difficulty;
import Display.DisplayLayout;
import Game.GameStats;
import Game.ObjectData;

import Logic.EnemyLogic;
import Entities.Position;
import Helpers.Direction;
import Board.Objects;

import javax.swing.*;
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

    public static void testBoardGen(){

        BoardData board = new BoardData();

        board.initialiseBoard(Difficulty.HARD);

        System.out.println(Arrays.deepToString(board.getBoardData()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }

    /**
     * This is the main method to create and display the game.
     * Creates this JFrame object that holds all the JPanels and
     * displays them on executable window.
     * @param args Command line arguments
     */
    public static void main(String[] args)
    {
        // Creating Object of CardLayoutDemo class.
        DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set visibility of JFrame.
        display.setVisible(true);
    }
}

package com.Helpers;

import com.Board.BoardData;
import com.Board.Objects;

import java.util.Random;


public class RandomXY {
    /**
     * @param boardData 2D array of Objects, see {@link BoardData}
     * @return int[] a random position on the board that satisfied the position BoardData[int[0]][int[1]] == EMPTY
     */
    public int[] getRandomXY(BoardData boardData) {

        Random rand = new Random(); //instance of random class
        boolean posFound = false;
        //initialize vars
        int x = -1;
        int y = -1;
        while (!posFound) {
            //-2 + 1 in order to not consider the border
            x = rand.nextInt(boardData.getboardwidth() - 2) + 1;
            y = rand.nextInt(boardData.getboardheight() - 2) + 1;
            //find a suitable position
            if (boardData.getBoardData()[x][y] == Objects.EMPTY){
                posFound = true;
            }
        }
        return new int[]{x, y};
    }
}

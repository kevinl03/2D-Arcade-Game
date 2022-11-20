package com.Helpers;

import com.Entities.Position;

/**
 * Helper class to perform Breadth First Search on 2d array
 * Contains position in array, path length and initial movement direction of path
 */
public class Node  extends Position{

    /**
     * initial movement direction of path
     */
    public Direction initialDirection;

    /**
     * the pathLength up to this node
     */
    public int pathLength;

    /**
     * Constructor for node at pos, with dir and len
     * @param pos Position of node
     * @param dir initial direction of path that met node
     * @param len length of path that met node
     */

    public Node(Position pos, Direction dir, int len){
        this.setPosition(pos);
        this.initialDirection = dir;
        this.pathLength = len;
    }
}

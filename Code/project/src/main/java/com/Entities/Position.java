package com.Entities;

import com.Helpers.Direction;

/**
 * Represents a position on the board using an x and y coordinate
 */
public class Position {

    /**
     * x and y coordinates
     */
    protected int x, y;

    /**
     * Default constructor at (0,0)
     */
    public Position(){x = 0; y = 0;}

    /**
     * Constructs a position at (x,y)
     * @param x x coordinate
     * @param y y coordinate
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }


    /**
     * Gets x coordinate
     * @return X coordinate
     */
    public int getX() {
        return x;
    }


    /**
     * Gets y coordinate
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * Increments x coordinate by 1
     */
    public void incrementX() { x++;}

    /**
     * Increments y coordinate by 1
     */
    public void incrementY() { y++;}

    /**
     * Decrements x coordinate by 1
     */
    public void decrementX() { x--;}

    /**
     * Decrements y coordinate by 1
     */
    public void decrementY() { y--;}


    /**
     * sets the X coordinate to x;
     * @param x new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * sets the Y coordinate to y
     * @param y new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the position to another positions x and y
     * @param pos New position
     */
    public void setPosition(Position pos){
        this.y = pos.y;
        this.x = pos.x;
    }

    /**
     * Finds the direction to travel to get from the before position to the after position
     * @param before initial position
     * @param after final position
     * @return Direction to move
     */
    public Direction getDirection(Position before, Position after){
        if(before.getY() > after.getY()){
            return Direction.NORTH;
        } else if (before.getY() < after.getY()) {
            return Direction.SOUTH;

        }else if (before.getX() < after.getX()) {
            return Direction.EAST;

        }else if(before.getX() > after.getX()){
            return Direction.WEST;

        }else{
            return Direction.NULL;
        }
    }
}

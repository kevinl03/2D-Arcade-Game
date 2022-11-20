package com.Entities;

import com.Helpers.Direction;


/**
 * Represents all non-static entities in game
 */
public class MovingEntity extends Position {
    /**
     * Direction that the moving entitiy is facing
     */
    private Direction dir;

    /**
     * Moving status of the entity (stationary or moving)
     */
    private boolean moving;

    /**
     * The current frame in the moving animation of the entity
     */
    private int animationFrame;

    /**
     * Default Constructor
     */
    public MovingEntity(){
        dir = Direction.NORTH;
        animationFrame = 1;
        moving = false;

    }


    /**
     * Constructs a moving entity at x,y
     * @param x x coordinate
     * @param y y coordinate
     */
    public MovingEntity(int x, int y){
        this.x = x;
        this.y = y;
        dir = Direction.NORTH;
        animationFrame = 1;
        moving = false;
    }


    /**
     * Gets the direction
     * @return The dir
     */
    public Direction getDir() {
        return dir;
    }

    /**
     * Gets the current animation frame
     * @return The animation frame
     */
    public int getAnimationFrame() {
        return animationFrame;
    }

    /**
     * Increments the animation frame by 1
     */
    public void incrementAnimationFrame(){
        this.animationFrame++;
        if(animationFrame > 3){
            animationFrame = 1;
        }
    }


    /**
     * sets the entities dir if not null
     * @param dir New direction
     */
    public void setDir(Direction dir) {
        if(dir != Direction.NULL){
            this.dir = dir;
        }
    }

    /**
     * Sets the moving status
     * @param moving New moving status
     */
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    /**
     * Gets the moving status of entity
     * @return Moving status
     */
    public boolean isMoving() {
        return moving;
    }
}


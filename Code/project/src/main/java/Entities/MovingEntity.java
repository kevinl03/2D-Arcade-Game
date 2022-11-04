package Entities;

import Helpers.Direction;

public class MovingEntity extends Position {
    protected int currentZone;

    private Direction dir;
    private boolean moving;

    private int animationFrame;
    public MovingEntity(){
        dir = Direction.NORTH;
        animationFrame = 1;
        moving = false;

    }

    public MovingEntity(int x, int y){
        this.x = x;
        this.y = y;
        dir = Direction.NORTH;
        animationFrame = 1;
        moving = false;
    }

    public int getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(int currentZone) {
        this.currentZone = currentZone;
    }

    public Direction getDir() {
        return dir;
    }

    public int getAnimationFrame() {
        return animationFrame;
    }

    public void setAnimationFrame(int animationFrame) {
        this.animationFrame = animationFrame;
    }

    public void incrementAnimationFrame(){
        this.animationFrame++;
        if(animationFrame > 3){
            animationFrame = 1;
        }
    }

    public void setDir(Direction dir) {
        if(dir != Direction.NULL){
            this.dir = dir;
        }
    }
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }
}


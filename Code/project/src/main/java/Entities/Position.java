package Entities;

import Helpers.Direction;

public class Position {
    protected int x, y;

    public Position(){x = 0; y = 0;}

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void incrementX() { x++;}
    public void incrementY() { y++;}

    public void decrementX() { x--;}
    public void decrementY() { y--;}

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setPosition(Position pos){
        this.y = pos.y;
        this.x = pos.x;
    }

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

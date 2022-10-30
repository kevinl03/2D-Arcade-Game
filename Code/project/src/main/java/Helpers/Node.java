package Helpers;

import Entities.Position;

public class Node  extends Position{
    public Direction initialDirection;

    public int pathLength;

    public Node(Position pos, Direction dir, int len){
        this.setPosition(pos);
        this.initialDirection = dir;
        this.pathLength = len;
    }
}

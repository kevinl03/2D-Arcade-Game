package Entities;

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
}

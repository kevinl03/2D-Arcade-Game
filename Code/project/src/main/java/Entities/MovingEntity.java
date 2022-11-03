package Entities;

public class MovingEntity extends Position {
    protected int currentZone;
    public MovingEntity(){

    }

    public MovingEntity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(int currentZone) {
        this.currentZone = currentZone;
    }
}

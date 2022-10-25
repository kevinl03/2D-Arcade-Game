package Entities;

public class MovingEntity extends Position {
    protected int currentZone;
    public MovingEntity(){
        currentZone = 0;

    }

    public MovingEntity(int x, int y, int zone){
        this.x = x;
        this.y = y;
        currentZone = zone;
    }

    public int getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(int currentZone) {
        this.currentZone = currentZone;
    }
}

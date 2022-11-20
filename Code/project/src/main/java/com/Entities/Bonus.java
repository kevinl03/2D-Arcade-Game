package com.Entities;

/**
* Bonus reward which is spawned and despawned at different location on the map at different times
*
*/

public class Bonus extends Reward {
    public Bonus() {
        super();
    }

    private boolean isSpawned = false;

    protected int despawnedTime;

    public int getdespawnTime(){
        return despawnedTime;
    }
    public void setdespawnedTime(int despawnT){
        despawnedTime = despawnT;
    }

    public boolean getisSpawned(){
        return isSpawned;
    }
    public void setisSpawned(boolean bool){
        isSpawned = bool;
    }


    public Bonus(int x, int y, int time, int p) {

        super(x, y, time, p);
        despawnedTime = 0;
    }
}

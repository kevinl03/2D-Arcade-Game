package Entities;

/**
* Regular reward entity for the game
*/

public class Reward extends StaticEntity{
    protected int point;

    public Reward (){
        super();
        point = 0;
    }
    public Reward(int x, int y, int time, int p ){
        super(x,y,time);
        point=p;
    }

    public int getPoint(){
        return point;
    }

    public void setPoint(int p){
        point = p;
    }

}

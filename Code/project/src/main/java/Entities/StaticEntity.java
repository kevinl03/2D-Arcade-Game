package Entities;

public class StaticEntity extends Position {
    protected int start_time; //reads the timer 

    public StaticEntity(){
        super();
        start_time = 0;
    }

    public StaticEntity(int x, int y, int time) {
        super(x,y);
        this.start_time = time;
    }

    public int getStartTime(){
        return start_time;
    }

    public void setStartTime(int t){
        this.start_time = t;
    }

}


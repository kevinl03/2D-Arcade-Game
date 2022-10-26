
public class StaticEntity extends Position {
    private int start_time; //reads the timer 

    public StaticEntity(){
        start_time = 0;
    }

    public StaticEntity(int x, int y, int time) {
        this.x = x;
        this.y=y;
        this.start_time = time;
    }

    public int getStartTime(){
        return start_time;
    }
    
    public void setStartTime(int t){
        this.start_time = t;
    }

}


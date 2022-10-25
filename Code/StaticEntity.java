public class StaticEntity extends Entity {
    private int points;
    //timer? 
    /*
    new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
                // your code here
            }
        }, 
        5000 
); */

    public StaticEntity(int x, int y, int p) {
        super(x, y);
        this.points = p;
        //TODO 
    }

    public int getPoints(){
        return points;
    }
    public void setPoints(int p){
        this.points = p;
    }
    
    public void loadInNewPosition(){
        //to hide the existing static entity and create a new one at random x and y
        // continues to generate random x and y until there is not a 
        //barrier in that position and loads it ther
    }

}


//Do we really need to have different classes for Bonus reward, Regular reward and trap?
//can't we just create the object of staticEntity class and 
//setPoint on the initiation of the object for them
// (negative num for creating trap, small positive num for reg reward, big pos num for bonus)
// StaticEntity(int x, int y, int point)
// trap = new StaticEntity(2, 3, -10)
//regReward = new StaticEntity(2, 3, 5)
//bonus = new StaticEntity(2, 3, 10)
//we can do them in BoardData.java setTraps(),setRegRewards(),...
package project.src.main.java.Entities;

public class Trap extends StaticEntity{
    private int damage;

    //constructor
    public Trap(){
        super();
        damage = 0;
    }
    public Trap(int x, int y, int time, int dmg){
        super(x,y,time);
        damage = dmg;
    }
    public int getDamage(){
        return damage;
    }

    public void setDamage(int dmg){
        damage = dmg;
    }
}

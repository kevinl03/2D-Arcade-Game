package Entities;

public class Hero extends MovingEntity {
    protected int score;

    Hero(){
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score){this.score += score;}

    public void deductScore(int damage){this.score -= damage;}
}

package Entities;

import Helpers.HeroColor;

public class Hero extends MovingEntity {
    protected int score;

    private boolean hidden = false;
    private boolean atBush = false;


    private HeroColor heroColor;

    public Hero(){
        score = 0;
        heroColor = HeroColor.BROWN;
    }

    public int getScore() {
        return score;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isAtBush() {
        return atBush;
    }

    public HeroColor getHeroColor() {
        return heroColor;
    }

    public void setHeroColor(HeroColor heroColor) {
        this.heroColor = heroColor;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setAtBush(boolean atBush){
        this.atBush = atBush;
    }

    public void addScore(int score){this.score += score;}

    public void deductScore(int damage){this.score -= damage;}
}

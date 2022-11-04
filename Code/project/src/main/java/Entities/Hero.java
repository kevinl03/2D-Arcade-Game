package Entities;

import Helpers.HeroColor;

public class Hero extends MovingEntity {
    protected int score;

    private HeroColor heroColor;

    public Hero(){
        score = 0;
        heroColor = HeroColor.BROWN;
    }

    public int getScore() {
        return score;
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

    public void addScore(int score){this.score += score;}

    public void deductScore(int damage){this.score -= damage;}
}

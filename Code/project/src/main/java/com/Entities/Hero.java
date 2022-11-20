package com.Entities;

import com.Helpers.HeroColor;

/**
 * Represents the playable squirrel in game
 */
public class Hero extends MovingEntity {

    /**
     * com.Game score
     */
    protected int score;

    /**
     * Is the hero hidden in a hide able tile
     */
    private boolean hidden = false;

    /**
     * represents if the hero rendering is inside the bush or approaching it
     */
    private boolean atBush = false;


    /**
     * Heros color
     */
    private HeroColor heroColor;

    /**
     * Default Constructor
     */
    public Hero(){
        score = 0;
        heroColor = HeroColor.BROWN;
    }

    /**
     * Gets the current score
     * @return The score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the heros hiding state
     * @return Hiding state
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Gets the heros animation state for hiding
     * @return hiding animation state
     */
    public boolean isAtBush() {
        return atBush;
    }

    /**
     * Gets the heros current color
     * @return The color
     */
    public HeroColor getHeroColor() {
        return heroColor;
    }

    /**
     * Sets the hero color
     */
    public void setHeroColor(HeroColor heroColor) {
        this.heroColor = heroColor;
    }

    /**
     * Sets the heros hidden status
     * @param hidden new hidden status
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Sets the hiding animation type
     * @param atBush new hiding animation type
     */
    public void setAtBush(boolean atBush){
        this.atBush = atBush;
    }


    /**
     * Adds score to the current score
     * @param score Amount of score to add
     */
    public void addScore(int score){this.score += score;}

    /**
     * Deducts score from the current score
     * @param damage Amount of score to deduct
     */
    public void deductScore(int damage){this.score -= damage;}
}

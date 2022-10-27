package Game;

import Board.BoardData;
import Entities.*;

public class ObjectData {
    private Hero hero;
    private Enemy[] enemies;
    private Trap[] traps;
    private RegularReward[] rewards;
    private BoardData board;
    private Bonus[] bonus;


    //getters

    public Hero getHero() {
        return hero;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public Enemy getEnemyAt(Position pos){
        for (int i = 0; i < enemies.length; i++) {
            if(enemies[i] == pos) {
                return enemies[i];
            }
        }
        return null;
    }

    public Trap[] getTraps() {
        return traps;
    }

    public Trap getTrapAt(Position pos){
        for (int i = 0; i < traps.length; i++) {
            if(traps[i] == pos) {
                return traps[i];
            }
        }
        return null;
    }


    public Reward[] getRewards() {
        return rewards;
    }

    public RegularReward getRewardAt(Position pos){
        for (int i = 0; i < rewards.length; i++) {
            if(rewards[i] == pos) {
                return rewards[i];
            }
        }
        return null;
    }


    public BoardData getBoard() {
        return board;
    }

    public Bonus[] getBonus() {
        return bonus;
    }

    public Bonus getBonusAt(Position pos){
        for (int i = 0; i < bonus.length; i++) {
            if(bonus[i] == pos) {
                return bonus[i];
            }
        }
        return null;
    }





}

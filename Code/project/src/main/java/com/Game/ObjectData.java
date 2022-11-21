package com.Game;

import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.*;
import com.Helpers.HeroColor;
import com.Logic.EnemyLogic;
import com.Logic.HeroLogic;
import com.Logic.RewardLogic;

import java.util.ArrayList;

/**
 * Container for instantiations of all game objects
 */
public class ObjectData {
    /**
     * {@link Hero} object
     */
    private final Hero hero;

    /**
     * ArrayList of all {@link Enemy} objects
     */
    private final ArrayList<Enemy> enemies;

    /**
     * ArrayList of all {@link Trap} objects
     */
    private final ArrayList<Trap> traps;

    /**
     * ArrayList of all {@link RegularReward} objects
     */
    private final ArrayList<RegularReward> rewards;

    /**
     * {@link BoardData} object
     */
    private final BoardData board;

    /**
     * ArrayList of all {@link Bonus} objects
     */
    private final ArrayList<Bonus> bonus;

    /**
     * Exit Object {@link Exit}
     */
    private final Exit exit;

    /**
     * GameStats Object {@link GameStats}
     */

    private final GameStats gameStats;

    /**
     * EnemyLogic Object {@link EnemyLogic}
     */
    private final EnemyLogic enemyLogic;

    /**
     * {@link HeroColor} Object
     */
    private final HeroLogic heroLogic;

    /**
     * {@link RewardLogic} Object
     */
    private final RewardLogic rewardLogic;

    /**
     * {@link Difficulty} Object
     */
    private final Difficulty dif;


    /**
     * Constructor for object data with difficulty and color
     * @param dif game difficulty
     * @param heroColor color
     */
    public ObjectData(Difficulty dif, HeroColor heroColor){
        this.dif = dif;
        hero = new Hero();
        board = new BoardData();
        gameStats = new GameStats();
        enemyLogic = new EnemyLogic();
        heroLogic = new HeroLogic();
        rewardLogic = new RewardLogic();
        enemies = new ArrayList<>();
        traps = new ArrayList<>();
        rewards = new ArrayList<>();
        bonus = new ArrayList<>();
        exit = new Exit();

        board.initialiseBoard(dif);
        int trapDamage = 50;
        int rewardPoints = 50;

        switch(dif){
            case MEDIUM ->  trapDamage = 100;
            case HARD, INFINITE -> trapDamage = 200;
        }

        //iterate through board to create objects
        for(int x = 0; x < board.getboardwidth() - 1; x++){
            for(int y = 0; y < board.getboardheight() - 1; y++){

                Position currentTile = new Position(x,y);
                switch(board.getTypeAt(currentTile)){
                    case HERO -> hero.setPosition(currentTile);
                    case ENEMY -> enemies.add(new Enemy(x,y));
                    case TRAP -> traps.add(new Trap(x,y, 0,trapDamage));
                    case REWARD -> rewards.add(new RegularReward(x, y, 0, rewardPoints));
                    case EXIT -> exit.setPosition(currentTile);
                    case BONUS -> {
                        bonus.add(new Bonus(x, y, 0, rewardPoints * 2));
                        board.setTypeAt(new Position(x, y), Objects.EMPTY);
                    }

                }
            }
        }
        hero.setHeroColor(heroColor);
    }


    /**
     * gets Hero
     * @return hero
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * gets arrayList of enemies
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * returns Trap at position
     * @param pos Trap position
     * @return Trap at position
     */
    public Trap getTrapAt(Position pos) {
        for (Trap trap : traps) {
            if ( (trap.getX() == pos.getX()) && (trap.getY() == pos.getY())) {
                return trap;
            }
        }
        return null;
    }

    /**
     * returns RegularReward at position
     * @param pos RegularReward position
     * @return RegularReward at position
     */
    public RegularReward getRewardAt(Position pos) {
        for (RegularReward reward : rewards) {
            if ( (reward.getX() == pos.getX()) && (reward.getY() == pos.getY())) {
                return reward;
            }
        }
        return null;
    }

    /**
     * returns board
     * @return board
     */
    public BoardData getBoard() {
        return board;
    }

    /**
     * returns arraylist of bonus's
     * @return all bonus's
     */
    public ArrayList<Bonus> getBonus() {
        return bonus;
    }

    /**
     * returns Bonus at position
     * @param pos Bonus position
     * @return Bonus at position
     */
    public Bonus getBonusAt(Position pos) {
        for (Bonus value : bonus) {
            if ( (value.getX() == pos.getX()) && (value.getY() == pos.getY()) ) {
                return value;
            }
        }
        return null;
    }

    /**
     * gets exit
     * @return exit
     */
    public Exit getExit() {
        return exit;
    }

    /**
     * gets game statistics
     * @return gamestats
     */
    public GameStats getGameStats() {
        return gameStats;
    }

    /**
     * gets the game logic
     * @return gameLogic
     */
    public HeroLogic getHeroLogic() {
        return heroLogic;
    }

    /**
     * Gets the enemy logic
     * @return EnemyLogic
     */
    public EnemyLogic getEnemyLogic() {
        return enemyLogic;
    }

    /**
     * Gets the difficulty
     *@return dif
     */
    public Difficulty getDif() {
        return dif;
    }

    /**
     * Gets the reward logic
     * @return rewardLogic
     */
    public RewardLogic getRewardLogic() { return rewardLogic; }

    public ArrayList<Bonus> getBonusArray(){ return bonus; }

    public ArrayList<RegularReward> getRewardArray() { return rewards;}

    public ArrayList<Trap> getTrapArray(){ return traps;}

    public ArrayList<Enemy> getEnemyArray() { return enemies;}
}

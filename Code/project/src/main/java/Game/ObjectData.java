package Game;

import Board.BoardData;
import Entities.*;
import Helpers.Stopwatch;

public class ObjectData {
    private Hero hero;
    private Enemy[] enemies;
    private Trap[] traps;
    private RegularReward[] rewards;
    private BoardData board;
    private Bonus[] bonus;
    private Exit exit;

    private GameStats gameStats;


    //getters

    public Hero getHero() {
        return hero;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public Enemy getEnemyAt(Position pos) {
        for (Enemy enemy : enemies) {
            if (enemy == pos) {
                return enemy;
            }
        }
        return null;
    }

    public Trap[] getTraps() {
        return traps;
    }

    public Trap getTrapAt(Position pos) {
        for (Trap trap : traps) {
            if (trap == pos) {
                return trap;
            }
        }
        return null;
    }


    public Reward[] getRewards() {
        return rewards;
    }

    public RegularReward getRewardAt(Position pos) {
        for (RegularReward reward : rewards) {
            if (reward == pos) {
                return reward;
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

    public Bonus getBonusAt(Position pos) {
        for (Bonus value : bonus) {
            if (value == pos) {
                return value;
            }
        }
        return null;
    }

    public Exit getExit() {
        return exit;
    }

    public GameStats getGameStats() {
        return gameStats;
    }
}
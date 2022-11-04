package Game;

import Board.BoardData;
import Board.Difficulty;
import Entities.*;
import Helpers.HeroColor;
import Logic.EnemyLogic;
import Logic.HeroLogic;

import java.util.ArrayList;

public class ObjectData {
    private Hero hero;
    private ArrayList<Enemy> enemies;
    private ArrayList<Trap> traps;
    private ArrayList<RegularReward> rewards;
    private BoardData board;
    private ArrayList<Bonus> bonus;
    private Exit exit;

    private GameStats gameStats;

    private EnemyLogic enemyLogic;

    private HeroLogic heroLogic;

    private Difficulty dif;



    public ObjectData(Difficulty dif, HeroColor heroColor){
        this.dif = dif;
        hero = new Hero();
        board = new BoardData();
        gameStats = new GameStats();
        enemyLogic = new EnemyLogic();
        heroLogic = new HeroLogic();
        enemies = new ArrayList<>();
        traps = new ArrayList<>();
        rewards = new ArrayList<>();
        bonus = new ArrayList<>();
        exit = new Exit();

        board.initialiseBoard(dif);
        int trapDamage = 50;
        int rewardPoints = 50;

        switch(dif){
            case EASY -> trapDamage = 50;
            case MEDIUM ->  trapDamage = 100;
            case HARD, INFINITE -> trapDamage = 200;
        }

        for(int x = 0; x < board.getboardwidth() - 1; x++){
            for(int y = 0; y < board.getboardheight() - 1; y++){

                Position currentTile = new Position(x,y);

//                    System.out.println(board.getTypeAt(currentTile));
                switch(board.getTypeAt(currentTile)){
                    case HERO -> hero.setPosition(currentTile);
                    case ENEMY -> enemies.add(new Enemy(x,y));
                    case TRAP -> traps.add(new Trap(x,y, 0,trapDamage));
                    case REWARD -> rewards.add(new RegularReward(x, y, 0, rewardPoints));
                    case EXIT -> exit.setPosition(currentTile);
                    case BONUS -> bonus.add(new Bonus (x,y,0,rewardPoints*2));

                }
            }
        }
        hero.setHeroColor(heroColor);


    }


    //getters

    public Hero getHero() {
        return hero;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getEnemyAt(Position pos) {
        for (Enemy enemy : enemies) {
            if ( (enemy.getX() == pos.getX()) && (enemy.getY() == pos.getY()) ) {
                return enemy;
            }
        }
        return null;
    }

    public ArrayList<Trap> getTraps() {
        return traps;
    }

    public Trap getTrapAt(Position pos) {
        for (Trap trap : traps) {
            if ( (trap.getX() == pos.getX()) && (trap.getY() == pos.getY())) {
                return trap;
            }
        }
        return null;
    }


    public ArrayList<RegularReward> getRewards() {
        return rewards;
    }

    public RegularReward getRewardAt(Position pos) {
        for (RegularReward reward : rewards) {
            if ( (reward.getX() == pos.getX()) && (reward.getY() == pos.getY())) {
                return reward;
            }
        }
        return null;
    }


    public BoardData getBoard() {
        return board;
    }

    public ArrayList<Bonus> getBonus() {
        return bonus;
    }

    public Bonus getBonusAt(Position pos) {
        for (Bonus value : bonus) {
            if ( (value.getX() == pos.getX()) && (value.getY() == pos.getY()) ) {
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

    public HeroLogic getHeroLogic() {
        return heroLogic;
    }

    public EnemyLogic getEnemyLogic() {
        return enemyLogic;
    }

    public Difficulty getDif() {
        return dif;
    }
}


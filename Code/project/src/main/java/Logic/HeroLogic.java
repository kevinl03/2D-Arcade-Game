package Logic;

import Board.Difficulty;
import Board.Objects;
import Board.BoardData;
import Display.Sound;
import Entities.*;
import Game.ObjectData;


/**
 * Handles all logic regarding the hero's movement
 */
public class HeroLogic {

    /**
     * Handles the players movement to a new position, various cases based on
     * what type of tile the hero moves to (reward, exit, enemy etc...)
     * @param pos the new {@link Position}
     * @param gameObjectData all game objects data, board and hero are used {@link ObjectData}
     */
    public void processPlayerMovement(Position pos, ObjectData gameObjectData){

        BoardData board = gameObjectData.getBoard();

        Hero hero = gameObjectData.getHero();
        if(pos.getX() == hero.getX() && pos.getY() == hero.getY()){
            return;
        }

        hero.setHidden(false);
        boolean heroMoved = true;

        Objects tileType = board.getTypeAt(pos);
        Sound sound = new Sound();

        switch (tileType){
            case TREE:
                heroMoved = false;
                break;
            case REWARD:
                collectReward(pos, gameObjectData);
                break;
            case TRAP:
                sound.lostSound();
                activateTrap(pos, gameObjectData);
                break;
            case ENEMY:
                sound.lostSound();
                gameObjectData.getGameStats().setGameOver(true);
                break;
            case EMPTY:
                break;
            case BONUS:
                collectBonus(pos, gameObjectData);
                break;
            case BUSH:
                hero.setHidden(true);
                break;
            case EXIT:
                Exit exit = gameObjectData.getExit();

                if(!exit.isClosed()){
                    gameObjectData.getGameStats().setGameWon(true);
                }else{
                    heroMoved = false;
                }
        }


        if(heroMoved){
            hero.setMoving(true);
            hero.setDir(hero.getDirection(hero, pos));
            if(board.getTypeAt(hero) == Objects.HEROHIDDEN){
                board.setTypeAt(hero, Objects.BUSH);
            }else{
                board.setTypeAt(hero, Objects.EMPTY);
            }
            hero.setPosition(pos);
            if(board.getTypeAt(hero) == Objects.BUSH){
                board.setTypeAt(hero, Objects.HEROHIDDEN);
            }else{
                board.setTypeAt(hero, Objects.HERO);
            }
        }


    }

    /**
     * Handles hero collecting a reward at pos, score from the reward is added
     * to hero's score and {@link Exit#rewardCollected(Difficulty) RewardCollected} is called
     * @param pos position of reward
     * @param gameObjectData Object data
     */
    private void collectReward(Position pos, ObjectData gameObjectData){
        Sound sound = new Sound();
        sound.playClick();
        RegularReward reward = gameObjectData.getRewardAt(pos);
        int score;

        //if reward does not exist don't add score
        if(reward != null){
            score = reward.getPoint();
        }else{
            score = 0;
        }

        Hero hero = gameObjectData.getHero();

        hero.addScore(score);

        Exit exit = gameObjectData.getExit();

        exit.rewardCollected(gameObjectData.getDif());
    }

    /**
     * Adds the points form bonus reward to hero's score
     * @param pos position of the bonus reward
     * @param gameObjectData game data
     */
    private void collectBonus(Position pos, ObjectData gameObjectData){
        Sound sound = new Sound();
        sound.playClick();
        Bonus bonus = gameObjectData.getBonusAt(pos);
        int score;
        //if reward does not exist don't add score
        if(bonus != null){
            score = bonus.getPoint();
            bonus.setisSpawned(false);
        }else{
            score = 0;
        }

        Hero hero = gameObjectData.getHero();

        hero.addScore(score);
    }

    /**
     * deducts the trap damage from the hero's score. if the score turns negative game over
     * @param pos position of trap
     * @param gameObjectData game data
     */
    private void activateTrap(Position pos, ObjectData gameObjectData){
        Trap trap = gameObjectData.getTrapAt(pos);
        int damage;

        //if reward does not exist don't add score
        if(trap != null){
            damage = trap.getDamage();
        }else{
            damage = 0;
        }

        Hero hero = gameObjectData.getHero();

        hero.deductScore(damage);

        if(hero.getScore() < 0){
            gameObjectData.getGameStats().setGameOver(true);
        }
    }

}

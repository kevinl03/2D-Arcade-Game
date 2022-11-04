package Logic;

import Board.Difficulty;
import Board.Objects;
import Board.BoardData;
import Entities.*;
import Game.ObjectData;

public class HeroLogic {
    public void processPlayerMovement(Position pos, ObjectData gameObjectData){

        BoardData board = gameObjectData.getBoard();



        Hero hero = gameObjectData.getHero();
        if(pos.getX() == hero.getX() && pos.getY() == hero.getY()){
            return;
        }

        boolean heroMoved = true;

        Objects tileType = board.getTypeAt(pos);
        switch (tileType){
            case TREE:
                heroMoved = false;
                break;
            case REWARD:
                collectReward(pos, gameObjectData);
                break;
            case TRAP:
                activateTrap(pos, gameObjectData);
                break;
            case ENEMY:
                gameObjectData.getGameStats().setGameOver(true);
                break;
            case EMPTY:
                break;
            case BONUS:
                collectBonus(pos, gameObjectData);
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
            board.setTypeAt(hero, Objects.EMPTY);
            hero.setPosition(pos);
            board.setTypeAt(hero, Objects.HERO);
        }


    }

    private void collectReward(Position pos, ObjectData gameObjectData){
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

    private void collectBonus(Position pos, ObjectData gameObjectData){
        Bonus bonus = gameObjectData.getBonusAt(pos);
        int score;

        //if reward does not exist don't add score
        if(bonus != null){
            score = bonus.getPoint();
        }else{
            score = 0;
        }

        Hero hero = gameObjectData.getHero();

        hero.addScore(score);
    }

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

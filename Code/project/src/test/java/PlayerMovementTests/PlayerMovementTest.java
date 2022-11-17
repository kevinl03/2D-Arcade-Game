package PlayerMovementTests;

import Board.Difficulty;
import Board.Objects;
import Board.BoardData;
import Entities.Hero;
import Entities.Position;
import Game.ObjectData;
import Helpers.HeroColor;
import org.junit.jupiter.api.Test;

public class PlayerMovementTest {
    ObjectData objectData;
    BoardData boardData;

    Hero hero;

    Position heropos;

    Position Uppos;
    Position Downpos;
    Position Leftpos;
    Position Rightpos;



    void setup(Objects object) {
        objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
        boardData = objectData.getBoard();
        hero = objectData.getHero();


        heropos = new Position(hero.getX(), hero.getY());

        Rightpos = new Position(heropos.getX() + 1, heropos.getY());
        Leftpos = new Position(heropos.getX() -1, heropos.getY());
        Uppos = new Position(heropos.getX(), heropos.getY() -1);
        Downpos = new Position(heropos.getX(), heropos.getY()+ 1);

        //set adjecent tiles to empty so that character movement can be tested
        boardData.setTypeAt(Rightpos, object);
        boardData.setTypeAt(Leftpos, object);
        boardData.setTypeAt(Uppos, object);
        boardData.setTypeAt(Downpos, object);

    }

    @Test
    void NonConstraintPlayerMovementUp(){


        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY);
        objectData.getHeroLogic().processPlayerMovement(Uppos,objectData);

        if (boardData.getTypeAt(Uppos) != Objects.HERO ) {
            assert (false);
        }
        //set the previous position to empty
        if(boardData.getTypeAt(heropos) != Objects.EMPTY){
            assert(false);
        }
        //hero position class must also be updated
        if (hero.getX() != Uppos.getX() || hero.getY() != Uppos.getY()){
            assert(false);
        }
        assert(true);
    }

    @Test
    void NonConstraintPlayerMovementDown(){


        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY);
        objectData.getHeroLogic().processPlayerMovement(Downpos,objectData);

        if (boardData.getTypeAt(Downpos) != Objects.HERO ){
            assert(false);
        }
        //set the previous position to empty
        if(boardData.getTypeAt(heropos) != Objects.EMPTY){
            assert(false);
        }
        //hero position class must also be updated
        if (hero.getX() != Downpos.getX() || hero.getY() != Downpos.getY()){
            assert(false);
        }
        assert(true);
    }
    @Test
    void NonConstraintPlayerMovementLeft(){


        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY);
        objectData.getHeroLogic().processPlayerMovement(Leftpos,objectData);

        if (boardData.getTypeAt(Leftpos) != Objects.HERO ){
            assert(false);
        }
        //set the previous position to empty
        if(boardData.getTypeAt(heropos) != Objects.EMPTY){
            assert(false);
        }
        //hero position class must also be updated
        if (hero.getX() != Leftpos.getX() || hero.getY() != Leftpos.getY()){
            assert(false);
        }
        assert(true);
    }
    @Test
    void NonConstraintPlayerMovementRight(){


        //create new gameobj to call processPlayerMovement
        setup(Objects.EMPTY);
        objectData.getHeroLogic().processPlayerMovement(Rightpos,objectData);

        if (boardData.getTypeAt(Rightpos) != Objects.HERO ){
            assert(false);
        }
        //set the previous position to empty
        if(boardData.getTypeAt(heropos) != Objects.EMPTY){
            assert(false);
        }
        //hero position class must also be updated
        if (hero.getX() != Rightpos.getX() || hero.getY() != Rightpos.getY()){
            assert(false);
        }
        assert(true);
    }


}

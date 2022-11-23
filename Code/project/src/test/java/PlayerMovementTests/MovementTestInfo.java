package PlayerMovementTests;

import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.Hero;
import com.Entities.Position;
import com.Game.ObjectData;
import com.Helpers.HeroColor;

public class MovementTestInfo {
    protected ObjectData objectData;
    protected BoardData boardData;

    protected Hero hero;

    protected Position heropos;

    protected Position Uppos;
    protected Position Downpos;
    protected Position Leftpos;
    protected Position Rightpos;

    protected int rewardValue;
    //int bonusValue;
    protected int trapValue;



    protected void setup(Objects object, Difficulty difficulty) {
        rewardValue=50;
        if(difficulty== Difficulty.EASY) {
            objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
            trapValue=50;
        }
        else if(difficulty== Difficulty.MEDIUM) {
            objectData = new ObjectData(Difficulty.MEDIUM, HeroColor.BROWN);
            trapValue=100;
        }
        else{
            objectData = new ObjectData(Difficulty.HARD, HeroColor.BROWN);
            trapValue=200;
        }
        boardData = objectData.getBoard();
        hero = objectData.getHero();

        Position oldheropos = new Position(hero.getX(),hero.getY());
        boardData.setTypeAt(oldheropos, Objects.EMPTY);


        //always start character on tile 1,1; guarantees that there are tiles around
        heropos = new Position(1,1);
        hero.setX(1);hero.setY(1);
        boardData.setTypeAt(heropos,Objects.HERO);


        Rightpos = new Position(heropos.getX() + 1, heropos.getY());
        Leftpos = new Position(heropos.getX() -1, heropos.getY());
        Uppos = new Position(heropos.getX(), heropos.getY() -1);
        Downpos = new Position(heropos.getX(), heropos.getY()+ 1);

        //set adjecent tiles to a certain object so that character movement can be tested
        boardData.setTypeAt(Rightpos, object);
        boardData.setTypeAt(Leftpos, object);
        boardData.setTypeAt(Uppos, object);
        boardData.setTypeAt(Downpos, object);

    }
}

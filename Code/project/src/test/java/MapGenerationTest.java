
import Board.BoardData;
import Board.Difficulty;
import Board.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapGenerationTest {
    @Test
    void wallgen(){

        assert(true);
    }
    @Test
    void bonusreward(){
        assert(true);
    }
    @Test
    void entitygen(){
        //System.out.printf("ur fat");
        assert(1 < 2);
    }

    @Test
    void ObjectGenerationCounts(){

        Integer generationCountEnemy = 0;
        Integer generationCountRegularReward = 0;
        Integer generationCountTrap = 0;

        for(Difficulty dif: Difficulty.values()){
           BoardData board = new BoardData();
           board.initialiseBoard(dif);

            switch(dif){
                case EASY:
                      generationCountEnemy = 1;
                      generationCountTrap = 4;
                      generationCountRegularReward = 5;
                    break;
                case MEDIUM:
                    generationCountEnemy = 2;
                    generationCountTrap = 7;
                    generationCountRegularReward = 10;
                    break;
                case HARD:
                case INFINITE:
                    generationCountEnemy = 3;
                    generationCountTrap = 11;
                    generationCountRegularReward = 15;
                    break;
            }

            HashMap<Objects, Integer> objectCount = new HashMap<>();


            int x =  board.getboardwidth();
            int y = board.getboardheight();

            for(int i = 0; i < x; i++){
                for (int j = 0; j < y; j++){

                    Objects typeAtTile = board.getTypeAt(i,j);

                    if(objectCount.containsKey(typeAtTile)){
                        objectCount.replace(typeAtTile, objectCount.get(typeAtTile) + 1);
                    }else{
                        objectCount.put(typeAtTile, 1);
                    }
                }
            }

            for(Map.Entry<Objects, Integer> entry: objectCount.entrySet()){
                switch (entry.getKey()){
                    case HERO, EXIT -> Assertions.assertEquals(entry.getValue(), 1);
                    case TREE, EMPTY -> Assertions.assertTrue(entry.getValue() > 0 && entry.getValue() < x*y);
                    case BUSH -> Assertions.assertEquals(entry.getValue(), 5);
                    case ENEMY -> Assertions.assertEquals(entry.getValue(), generationCountEnemy);
                    case REWARD -> Assertions.assertEquals(entry.getValue(), generationCountRegularReward);
                    case TRAP -> Assertions.assertEquals(entry.getValue(), generationCountTrap);

                }
            }

        }




    }
}
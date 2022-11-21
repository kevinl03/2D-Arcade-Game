
import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapGenerationTest {
    @Test
    void wallgen() {

        assert (true);
    }

    @Test
    void bonusreward() {
        assert (true);
    }

    @Test
    void entitygen() {
        assert (1 < 2);
    }
    @Test
    void HeroGenerationCount() {
        for (Difficulty dif : Difficulty.values()) {
            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            int x = board.getboardwidth();
            int y = board.getboardheight();
            int heroCount = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    Objects typeAtTile = board.getTypeAt(i, j);
                    if (typeAtTile == Objects.HERO) {
                        heroCount = heroCount + 1;
                    }
                }
            }
            Assertions.assertEquals(1, heroCount);
        }
    }

    @Test
    void ExitGenerationCount() {
        for (Difficulty dif : Difficulty.values()) {
            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            int x = board.getboardwidth();
            int y = board.getboardheight();
            int exitCount = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    Objects typeAtTile = board.getTypeAt(i, j);
                    if (typeAtTile == Objects.EXIT) {
                        exitCount = exitCount + 1;
                    }
                }
            }
            Assertions.assertEquals(1, exitCount);
        }
    }

    @Test
    void TreeGenerationCount() {
        for (Difficulty dif : Difficulty.values()) {
            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            int x = board.getboardwidth();
            int y = board.getboardheight();
            int treeCount = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    Objects typeAtTile = board.getTypeAt(i, j);
                    if (typeAtTile == Objects.EXIT) {
                        treeCount = treeCount + 1;
                    }
                }
            }
            Assertions.assertTrue(treeCount > 0 && treeCount < x * y);
        }
    }

    @Test
    void BushGenerationCount() {
        for (Difficulty dif : Difficulty.values()) {
            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            int x = board.getboardwidth();
            int y = board.getboardheight();
            int bushCount = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    Objects typeAtTile = board.getTypeAt(i, j);
                    if (typeAtTile == Objects.BUSH) {
                        bushCount = bushCount + 1;
                    }
                }
            }
            Assertions.assertEquals(5, bushCount);
        }
    }

    @Test
    void EnemyGenerationCount() {
        for (Difficulty dif : Difficulty.values()) {
            Integer generationCountEnemy = 0;
            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            switch (dif) {
                case EASY -> generationCountEnemy = 1;
                case MEDIUM -> generationCountEnemy = 2;
                case INFINITE, HARD -> generationCountEnemy = 3;
            }
            int x = board.getboardwidth();
            int y = board.getboardheight();
            int enemyCount = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    Objects typeAtTile = board.getTypeAt(i, j);
                    if (typeAtTile == Objects.ENEMY) {
                        enemyCount = enemyCount + 1;
                    }
                }
            }
            Assertions.assertEquals(generationCountEnemy, enemyCount);
        }
    }

    @Test
    void TrapGenerationCount() {
        for (Difficulty dif : Difficulty.values()) {
            Integer generationCountTrap = 0;
            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            switch (dif) {
                case EASY -> generationCountTrap = 4;
                case MEDIUM -> generationCountTrap = 7;
                case INFINITE, HARD -> generationCountTrap = 11;
            }
            int x = board.getboardwidth();
            int y = board.getboardheight();
            int trapCount = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    Objects typeAtTile = board.getTypeAt(i, j);
                    if (typeAtTile == Objects.TRAP) {
                        trapCount = trapCount + 1;
                    }
                }
            }
            Assertions.assertEquals(generationCountTrap, trapCount);
        }
    }

    @Test
    void RewardGenerationCount() {
        for (Difficulty dif : Difficulty.values()) {
            Integer generationCountReward = 0;
            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            switch (dif) {
                case EASY -> generationCountReward = 5;
                case MEDIUM -> generationCountReward = 10;
                case INFINITE, HARD -> generationCountReward = 15;
            }
            int x = board.getboardwidth();
            int y = board.getboardheight();
            int rewardCount = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    Objects typeAtTile = board.getTypeAt(i, j);
                    if (typeAtTile == Objects.REWARD) {
                        rewardCount = rewardCount + 1;
                    }
                }
            }
            Assertions.assertEquals(generationCountReward, rewardCount);
        }
    }
}
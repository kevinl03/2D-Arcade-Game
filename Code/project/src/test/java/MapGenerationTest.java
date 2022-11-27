import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.Position;
import com.Entities.Bonus;
import com.Game.ObjectData;
import com.Helpers.HeroColor;
import com.Logic.RewardLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.Helpers.Node;
import com.Entities.Position;
import com.Helpers.Direction;

import java.lang.reflect.Array;
import java.util.*;

import java.util.ArrayList;

public class MapGenerationTest {
    //starting position 1,1; runs a breadthfirst search in order to
    //count all nontree tiles. If the number of non tree tiles counted from
    //the position(1,1) is the same as the total amount of nontree tiles,
    //then this guarantees that there are no sections blocked off which
    //is indicative of a succesful random wall generation
    private int parsethroughMap(Objects[][] board) {
        int width = board.length;
        int height = board[0].length;

        int numberNonTreeTiles = 0;
        Queue<Node> q = new LinkedList<>();
        Position startPos = new Position(1,1);
        board[1][1] = Objects.TREE;
        Node start = new Node(startPos, Direction.NULL, 0);
        q.add(start);

        while (!q.isEmpty()) {
            Node head = q.poll();


            //Objects.TREE represents tree on the board, I swap a visited node to a tree
            //so that the bfs knows not to search the head node again.
            board[head.getX()][head.getY()] = Objects.TREE;
            numberNonTreeTiles++;
            q.addAll(getNeighbours(head, board, width, height));
            }

        return numberNonTreeTiles;
        }


    //return the number of tiles found
    //number of tiles found should be equal to mapsize - trees
    private List<Node> getNeighbours(Node head, Objects[][] board, int width, int height) {
        int x = head.getX();
        int y = head.getY();

        List<Node> neighbours = new LinkedList<>();
        //check if there is a non tree tile in each direction (North, East, South, West), if so, add it to neighbours list
        //only change the initialDirection if it is the initial node from where the search started (the enemy)
        if (x + 1 >= 0 && x + 1 < width && board[x + 1][y] != Objects.TREE) {
            neighbours.add(new Node(new Position(x + 1, y), Direction.EAST, head.pathLength + 1));
            board[x+1][y] = Objects.TREE;
        }

        if (x - 1 >= 0 && x - 1 < width && board[x - 1][y] != Objects.TREE) {
            neighbours.add(new Node(new Position(x - 1, y), Direction.WEST, head.pathLength + 1));
            board[x-1][y] = Objects.TREE;
        }

        if (y + 1 >= 0 && y + 1 < height && board[x][y + 1] != Objects.TREE) {
            neighbours.add(new Node(new Position(x, y + 1), Direction.SOUTH, head.pathLength + 1));
            board[x][y+1] = Objects.TREE;
        }

        if (y - 1 >= 0 && y - 1 < height && board[x][y - 1] != Objects.TREE) {
            neighbours.add(new Node(new Position(x, y - 1), Direction.NORTH, head.pathLength + 1));
            board[x][y-1] = Objects.TREE;
        }

        return neighbours;
    }

    //Makes sure that wall generation successful creates
    @Test
    public void testWallGenerationAllDifficulties(){


        for (Difficulty dif: Difficulty.values()) {
            BoardData board = new BoardData();
            board.initialiseBoard(dif);

            int treecount = 0;

            int boardWidth =  board.getboardwidth();
            int boardHeight = board.getboardheight();
            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardHeight; j++) {
                    if (board.getTypeAt(i,j) == Objects.EXIT)
                    {
                        //replace EXIT with a tree to help depth first search alogirthm
                        board.setTypeAt(new Position(i,j),Objects.TREE);
                    }

                    if (board.getTypeAt(i,j) == Objects.TREE)
                    {
                        treecount++;
                    }
                }

            }
            //atleast a quarter of the map should be walls
            //this guarantees that the map is full enough
            //to be complex so that replay ability is maintained

            assert(treecount > boardHeight*boardWidth/4);

            //there should never be a tree right next to the boarder
            //therefore board at pos 1,1 should be a free space to be moved
            //since tree generation should always spawn one tile away from
            //edge walls
            assert(board.getTypeAt(1,1) != Objects.TREE);

            int numNonTreeTiles = boardHeight*boardWidth - treecount;

            //to see that there are no blocked off secitons of the map
            //run a breadth first search which counts the number of non tile
            //
            int tilesfound = parsethroughMap(board.getBoardData());

            assert(tilesfound == numNonTreeTiles);



        }
    }
    int checkTileSimilarity(BoardData compareboard, BoardData newBoard) {
        int numIdenticalTiles = 0;
        for (int xcoord = 1; xcoord < newBoard.getboardwidth()-2; xcoord++) {
            for(int ycoord = 1; ycoord < newBoard.getboardheight()-2; ycoord++){
                Position curpos = new Position(xcoord,ycoord);

                if(newBoard.getTypeAt(curpos) == compareboard.getTypeAt(curpos)){
                    if (newBoard.getTypeAt(curpos) != Objects.EMPTY){
                        numIdenticalTiles++;
                   }
                }
            }
        }
        return numIdenticalTiles;
    }
    @Test
    public void testMapSimilarity(){

        for(Difficulty dif: Difficulty.values()) {
            BoardData compareboard = new BoardData();
            compareboard.initialiseBoard(dif);


            int numtestcomparisons = 100;
            float sumTileSimilaritypercentage = 0;
            for (int comparisons = 0; comparisons < numtestcomparisons; comparisons++) {
                BoardData newBoard = new BoardData();
                newBoard.initialiseBoard(dif);

                float numIdenticalTiles = checkTileSimilarity(compareboard,newBoard);
                float totalgeneratedtiles = (newBoard.getboardwidth()-2)* (newBoard.getboardheight()-2);
                float percentage = (numIdenticalTiles/totalgeneratedtiles)*100;
                sumTileSimilaritypercentage+=percentage;
            }
            float AverageSimilarityPercentage = sumTileSimilaritypercentage/numtestcomparisons;

            assert(AverageSimilarityPercentage < 35);



        }
    }
    @Test
    public void testMassMapGeneration(){
        int upperbound = 50;
        for (int totalgenerations = 0; totalgenerations < upperbound; totalgenerations++){


            testWallGenerationAllDifficulties();

        }
        //upperbound number of map generations succeeded
        assert(true);
    }
    @Test
    public void testOuterBoarderGenerationTest() {
        for (Difficulty dif : Difficulty.values()) {
            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            int x = board.getboardwidth();
            int y = board.getboardheight();
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if(board.getTypeAt(i,j) != Objects.EXIT && board.getTypeAt(i,j) != Objects.HERO){
                        if( i == 0 || i == x-1){
                            Assertions.assertEquals(Objects.TREE, board.getTypeAt(i,j));
                        }else if (j == 0 || j == y-1){
                            Assertions.assertEquals(Objects.TREE, board.getTypeAt(i,j));
                        }
                    }
                }
            }
        }
    }
    @Test
    public void testMinDistanceBetweenEnemyAndHero() {
        for (Difficulty dif : Difficulty.values()) {
            int minProximity = 0;
            switch(dif){
                case EASY: minProximity = 10;
                    break;
                case MEDIUM: minProximity = 7;
                    break;
                case HARD: case INFINITE: minProximity = 5;
                    break;
            }

            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            int x = board.getboardwidth();
            int y = board.getboardheight();

            ArrayList<Position> enemyPositions = new ArrayList<>();
            int heroX = 0;
            int heroY = 0;

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    Objects typeAtTile = board.getTypeAt(i,j);

                    if(typeAtTile == Objects.HERO){
                        heroX = i;
                        heroY = j;
                    } else if (typeAtTile == Objects.ENEMY) {
                        enemyPositions.add(new Position(i,j));
                    }
                }
            }

            for(Position enemyPos : enemyPositions){
                int enemyX = enemyPos.getX();
                int enemyY = enemyPos.getY();

                double distance = Math.sqrt(Math.pow(Math.abs(heroX -enemyX),2) + Math.pow(Math.abs(heroY - enemyY), 2));

                Assertions.assertTrue(distance >= minProximity);
            }
        }

    }

    @Test
    public void testMinDistanceBetweenRewards() {
        for (Difficulty dif : Difficulty.values()) {
            int minProximity = 0;
            switch(dif){
                case EASY: minProximity = 5;
                    break;
                case MEDIUM: minProximity = 3;
                    break;
                case HARD: case INFINITE: minProximity = 0;
                    break;
            }

            BoardData board = new BoardData();
            board.initialiseBoard(dif);
            int x = board.getboardwidth();
            int y = board.getboardheight();

            ArrayList<Position> rewardPositions = new ArrayList<>();

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    Objects typeAtTile = board.getTypeAt(i,j);

                    if (typeAtTile == Objects.REWARD) {
                        rewardPositions.add(new Position(i,j));
                    }
                }
            }

            for(Position reward : rewardPositions){
                for(Position reward2 : rewardPositions){
                    int rewardX = reward.getX();
                    int rewardY = reward.getY();
                    int reward2X = reward2.getX();
                    int reward2Y = reward2.getY();
                    double distance = Math.sqrt(Math.pow(Math.abs(rewardX -reward2X),2) + Math.pow(Math.abs(rewardY - reward2Y), 2));
                    Assertions.assertTrue(distance >= minProximity || distance == 0);
                }


            }
        }

        assert (true);
    }
    @Test
    public void testHeroGenerationCount() {
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
    public void testExitGenerationCount() {
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
    public void testTreeGenerationCount() {
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
    public void testBushGenerationCount() {
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
    public void testEnemyGenerationCount() {
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
    public void testTrapGenerationCount() {
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
    public void testRewardGenerationCount() {
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

    @Test
    public void testBonusRewardDespawnInitially(){
        for (Difficulty dif : Difficulty.values()) {
            ObjectData objectData = new ObjectData(dif, HeroColor.BROWN);
            ArrayList<Bonus> bonusList = objectData.getBonusArray();
            RewardLogic bonusLogic = objectData.getRewardLogic();
            bonusLogic.updateRewards(objectData, 0);
            boolean result = true;
            for(Bonus b : bonusList) {
                if (b.getisSpawned()) {
                    result = false;
                }
            }
            assert(result);
        }
    }

    @Test
    public void testBonusRewardSpawnHard() {
        int loop = 0;
        while (loop < 100) {
            ObjectData objectData = new ObjectData(Difficulty.MEDIUM, HeroColor.BROWN);
            ArrayList<Bonus> bonusList = objectData.getBonusArray();
            RewardLogic bonusLogic = objectData.getRewardLogic();
            int switched0 = 0;
            boolean result0 = false;
            boolean spawndespawn0 = false;
            Bonus b0 = bonusList.get(0);
            //milliseconds
            int count = 0;
            //loop for 120 seconds, incrementing by 1 second
            while (count <= 120000) {
                bonusLogic.updateRewards(objectData, count);
                if (b0.getisSpawned() && !result0) {
                    result0 = true;
                    switched0++;
                }
                //Despawn a spawned bonus
                if (!b0.getisSpawned() && result0 && !spawndespawn0) {
                    switched0++;
                    spawndespawn0 = true;
                }
                count += 1000;
            }
            loop++;
            assert (switched0 == 2);
        }
    }

    @Test
    public void testBonusRewardDespawnMedium() {
        int loop = 0;
        while (loop < 100) {
            ObjectData objectData = new ObjectData(Difficulty.MEDIUM, HeroColor.BROWN);
            ArrayList<Bonus> bonusList = objectData.getBonusArray();
            RewardLogic bonusLogic = objectData.getRewardLogic();
            int switched0 = 0;
            int switched1 = 0;
            boolean result0 = false;
            boolean result1 = false;
            boolean spawndespawn0 = false;
            boolean spawndespawn1 = false;
            Bonus b0 = bonusList.get(0);
            Bonus b1 = bonusList.get(1);
            //milliseconds
            int count = 0;
            //loop for 120 seconds, incrementing by 1 second
            while (count <= 120000) {
                bonusLogic.updateRewards(objectData, count);
                if (b0.getisSpawned() && !result0) {
                    result0 = true;
                    switched0++;
                }
                //Despawn a spawned bonus
                if (!b0.getisSpawned() && result0 && !spawndespawn0) {
                    switched0++;
                    spawndespawn0 = true;
                }
                if (b1.getisSpawned() && !result1) {
                    result1 = true;
                    switched1++;
                }
                //Despawn a spawned bonus
                if (!b1.getisSpawned() && result1 && !spawndespawn1) {
                    switched1++;
                    spawndespawn1 = true;
                }
                count += 1000;
            }
            loop++;
            assert ((switched0 == 2) && (switched1 == 2));
        }
    }

    @Test
    public void testBonusRewardDespawnEasy() {
        int loop = 0;
        while (loop < 100) {
            ObjectData objectData = new ObjectData(Difficulty.EASY, HeroColor.BROWN);
            ArrayList<Bonus> bonusList = objectData.getBonusArray();
            RewardLogic bonusLogic = objectData.getRewardLogic();
            int switched0 = 0;
            int switched1 = 0;
            int switched2 = 0;
            boolean result0 = false;
            boolean result1 = false;
            boolean result2 = false;
            boolean spawndespawn0 = false;
            boolean spawndespawn1 = false;
            boolean spawndespawn2 = false;
            Bonus b0 = bonusList.get(0);
            Bonus b1 = bonusList.get(1);
            Bonus b2 = bonusList.get(2);
            //milliseconds
            int count = 0;
            //loop for 120 seconds, incrementing by 1 second
            while (count <= 120000) {
                bonusLogic.updateRewards(objectData, count);
                if (b0.getisSpawned() && !result0) {
                    result0 = true;
                    switched0++;
                }
                //Despawn a spawned bonus
                if (!b0.getisSpawned() && result0 && !spawndespawn0) {
                    switched0++;
                    spawndespawn0 = true;
                }
                if (b1.getisSpawned() && !result1) {
                    result1 = true;
                    switched1++;
                }
                //Despawn a spawned bonus
                if (!b1.getisSpawned() && result1 && !spawndespawn1) {
                    switched1++;
                    spawndespawn1 = true;
                }
                if (b2.getisSpawned() && !result2) {
                    result2 = true;
                    switched2++;
                }
                //Despawn a spawned bonus
                if (!b2.getisSpawned() && result2 && !spawndespawn2) {
                    switched2++;
                    spawndespawn2 = true;
                }
                count += 1000;
            }
            loop++;
            assert ((switched0 == 2) && (switched1 == 2) && (switched2 == 2));
        }
    }

}

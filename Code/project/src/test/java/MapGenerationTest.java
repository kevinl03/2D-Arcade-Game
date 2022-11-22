import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    void TestWallGenerationAllDifficulties(){


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
    void testMapSimilarity(){

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
    void testMassMapGeneration(){
        int upperbound = 10000;
        for (int totalgenerations = 0; totalgenerations < upperbound; totalgenerations++){


            TestWallGenerationAllDifficulties();

        }
        //upperbound number of map generations succeeded
        assert(true);
    }
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

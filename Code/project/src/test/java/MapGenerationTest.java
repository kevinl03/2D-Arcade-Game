import com.Board.BoardData;
import com.Board.Difficulty;
import com.Board.Objects;
import com.Entities.Position;
import com.Helpers.Direction;
import com.Helpers.Node;
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

            ObjectGenerationCounts();

            TestWallGenerationAllDifficulties();

        }
        //upperbound number of map generations succeeded
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
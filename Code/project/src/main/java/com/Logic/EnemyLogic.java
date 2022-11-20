package com.Logic;

import com.Board.BoardData;
import com.Entities.Enemy;
import com.Entities.Position;
import com.Game.GameStats;
import com.Game.ObjectData;
import com.Helpers.Direction;
import com.Helpers.Node;
import com.Board.Objects;

import java.util.*;


/**
 * Handles all logic regarding enemy movement
 */
public class EnemyLogic {


    /**
     * Handles the movement of all enemies using a "shortest path algorithm" towards the hero
     * Once a movement direction is found, checks for enemy collision and hidden hero are done
     * When moving the board tile data is updated for the enemy tile prior and post movement.
     * @param gameObjectData Contains all game data. BoardData, GameStats, enemies list are used/updated
     */
    public void processEnemyMovement(ObjectData gameObjectData) {

        BoardData board = gameObjectData.getBoard();
        GameStats gameStats = gameObjectData.getGameStats();
        ArrayList<Enemy> enemies = gameObjectData.getEnemies();

        Objects[][] boardArr = board.getBoardData();

        int width = boardArr.length;
        int height = boardArr[0].length;

        //process movement for each enemy on board
        for (Enemy enemy : enemies) {

            Objects[][] tempArr = new Objects[width][height];
            for(int i = 0; i < width; i++){
                System.arraycopy(boardArr[i], 0, tempArr[i], 0, height);
            }

            Direction nextMove = findShortestPath(tempArr, enemy);

            //get  random direction and make sure it's not going into a tree or exit
            if(nextMove == Direction.RANDOM){
                nextMove = getRandomDirection(board, enemy);
            }

            //check for two enemies colliding, if so, don't move
            Objects nextTile = Objects.TMP;
            switch(nextMove){
                case NORTH -> nextTile = board.getTypeAt(enemy.getX(), enemy.getY() -1);
                case EAST ->  nextTile = board.getTypeAt(enemy.getX() + 1, enemy.getY());
                case SOUTH -> nextTile = board.getTypeAt(enemy.getX(), enemy.getY() +1);
                case WEST ->  nextTile = board.getTypeAt(enemy.getX() - 1, enemy.getY());
            }
            if(nextTile == Objects.ENEMY){
                nextMove = Direction.NULL;
            }

            //if Moving, update board tiles accordingly
            if (nextMove != Direction.NULL) {
                Objects currentTile = board.getTypeAt(enemy);
                if (currentTile == Objects.ENEMYANDREWARD) {
                    board.setTypeAt(enemy, Objects.REWARD);
                } else if (currentTile == Objects.ENEMYANDTRAP) {
                    board.setTypeAt(enemy, Objects.TRAP);
                } else if (currentTile == Objects.ENEMYANDBUSH){
                    board.setTypeAt(enemy, Objects.BUSH);
                } else if (currentTile == Objects.ENEMYANDBONUS){
                    board.setTypeAt(enemy, Objects.BONUS);
                } else {
                    board.setTypeAt(enemy, Objects.EMPTY);
                }
            }

            //Adjust enemies location and the direction it is facing
            switch (nextMove) {
                case NORTH:
                    enemy.decrementY();
                    enemy.setDir(Direction.NORTH);
                    break;
                case EAST:
                    enemy.incrementX();
                    enemy.setDir(Direction.EAST);

                    break;
                case SOUTH:
                    enemy.incrementY();
                    enemy.setDir(Direction.SOUTH);

                    break;
                case WEST:
                    enemy.decrementX();
                    enemy.setDir(Direction.WEST);

                    break;
                case NULL:
                    break;

            }

            //if enemy moved, update new tiles information
            if (nextMove != Direction.NULL) {
                Objects currentTile = board.getTypeAt(enemy);
                if (currentTile == Objects.REWARD) {
                    board.setTypeAt(enemy, Objects.ENEMYANDREWARD);
                } else if (currentTile == Objects.TRAP) {
                    board.setTypeAt(enemy, Objects.ENEMYANDTRAP);
                } else if (currentTile == Objects.BUSH){
                    board.setTypeAt(enemy, Objects.ENEMYANDBUSH);
                } else if (currentTile == Objects.BONUS){
                    board.setTypeAt(enemy, Objects.ENEMYANDBONUS);
                } else if (currentTile == Objects.HERO || currentTile == Objects.HEROHIDDEN) {
                    gameStats.setGameOver(true);
                } else {
                    board.setTypeAt(enemy, Objects.ENEMY);
                }
            }
        }

    }

    /**
     * Returns a random direction for enemy to move to,
     * that is not towards a tree, exit or enemy tile
     * @param board com.Board data containing all Objects positions
     * @param enemy Enemy that is moving
     * @return A valid Direction for enemy to move to
     */
    private Direction getRandomDirection(BoardData board, Enemy enemy){
        Random rand = new Random();
        boolean validDirection = false;
        Direction nextMove = Direction.NULL;
        while(!validDirection){
            int dir = rand.nextInt(4);
            nextMove = Direction.values()[dir];
            Objects nextTile = Objects.TREE;
            switch(nextMove){
                case NORTH -> nextTile = board.getTypeAt(enemy.getX(), enemy.getY() -1);
                case EAST ->  nextTile = board.getTypeAt(enemy.getX() + 1, enemy.getY());
                case SOUTH -> nextTile = board.getTypeAt(enemy.getX(), enemy.getY() +1);
                case WEST ->  nextTile = board.getTypeAt(enemy.getX() - 1, enemy.getY());
            }

            if(nextTile!= Objects.TREE && nextTile != Objects.EXIT && nextTile != Objects.ENEMY){
                validDirection = true;
            }

        }
        return nextMove;
    }

    /**
     * A Breadth first search is performed on a 2d array starting at the enemy position
     * to find the shortest path to the hero and which direction the first step is.
     * the shortest path may not contain any tree object tiles.
     * If the hero is hidden, and the shortest path is more than 3, the enemy cant "find"
     * the hero and will walk in a random direction
     *
     * @param board com.Board data containing all Objects positions
     * @param enemyPos Enemy that the search is start at
     * @return Direction that the enemy should move (random for hidden case)
     */
    public Direction findShortestPath(Objects[][] board, Position enemyPos) {
        int width = board.length;
        int height = board[0].length;


        Queue<Node> q = new LinkedList<>();
        Node start = new Node(enemyPos, Direction.NULL, 0);
        q.add(start);

        while (!q.isEmpty()) {
            Node head = q.poll();

            if (board[head.getX()][head.getY()] == Objects.HERO) {
                return head.initialDirection;
            } else if (board[head.getX()][head.getY()] == Objects.HEROHIDDEN){
                if (head.pathLength > 3){
                    //squirrel is hidden so enemy moves randomly
                    return Direction.RANDOM;
                }else{
                    return head.initialDirection;
                }

            }
            else {
                //Objects.TREE represents tree on the board, I swap a visited node to a tree
                //so that the bfs knows not to search the head node again.
                board[head.getX()][head.getY()] = Objects.TREE;

                q.addAll(getNeighbours(head, board, width, height));
            }


        }
        return Direction.NULL;
    }

    //getNeighbours returns a list containing all the direct neighbours to head

    /**
     * Gets all the neighbour nodes of the head node, helper function for {@link #findShortestPath(Objects[][], Position) FindShortestPath}
     * @param head node to get neighbours for
     * @param board board data for all objects
     * @param width max width to search in
     * @param height max height to search in
     * @return List of nodes that are neighbouring the head node
     */
    private List<Node> getNeighbours(Node head, Objects[][] board, int width, int height) {
        int x = head.getX();
        int y = head.getY();

        List<Node> neighbours = new LinkedList<>();
        //check if there is a non tree tile in each direction (North, East, South, West), if so, add it to neighbours list
        //only change the initialDirection if it is the initial node from where the search started (the enemy)
        if (x + 1 >= 0 && x + 1 < width && board[x + 1][y] != Objects.TREE) {
            if (head.initialDirection == Direction.NULL) {
                neighbours.add(new Node(new Position(x + 1, y), Direction.EAST, head.pathLength + 1));
            } else {
                neighbours.add(new Node(new Position(x + 1, y), head.initialDirection, head.pathLength + 1));
            }
        }

        if (x - 1 >= 0 && x - 1 < width && board[x - 1][y] != Objects.TREE) {
            if (head.initialDirection == Direction.NULL) {
                neighbours.add(new Node(new Position(x - 1, y), Direction.WEST, head.pathLength + 1));
            } else {
                neighbours.add(new Node(new Position(x - 1, y), head.initialDirection, head.pathLength + 1));
            }
        }

        if (y + 1 >= 0 && y + 1 < height && board[x][y + 1] != Objects.TREE) {
            if (head.initialDirection == Direction.NULL) {
                neighbours.add(new Node(new Position(x, y + 1), Direction.SOUTH, head.pathLength + 1));
            } else {
                neighbours.add(new Node(new Position(x, y + 1), head.initialDirection, head.pathLength + 1));
            }
        }

        if (y - 1 >= 0 && y - 1 < height && board[x][y - 1] != Objects.TREE) {
            if (head.initialDirection == Direction.NULL) {
                neighbours.add(new Node(new Position(x, y - 1), Direction.NORTH, head.pathLength + 1));
            } else {
                neighbours.add(new Node(new Position(x, y - 1), head.initialDirection, head.pathLength + 1));
            }
        }

        return neighbours;
    }
}

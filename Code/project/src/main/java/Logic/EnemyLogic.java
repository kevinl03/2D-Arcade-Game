package Logic;

import Board.BoardData;
import Entities.Enemy;
import Entities.Position;
import Game.GameStats;
import Game.ObjectData;
import Helpers.Direction;
import Helpers.Node;
import Board.Objects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EnemyLogic {

    public void processEnemyMovement(ObjectData gameObjectData){

        BoardData board = gameObjectData.getBoard();
        GameStats gameStats = gameObjectData.getGameStats();
        ArrayList<Enemy> enemies = gameObjectData.getEnemies();

        Objects[][] boardArr = board.getBoardData();

        int width = boardArr.length;
        int height = boardArr[0].length;

        Objects[][] tempArr = new Objects[width][height];

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                tempArr[i][j] = boardArr[i][j];
            }
        }
        System.out.println("\n\n");
        for (Enemy enemy : enemies) {
            Direction nextMove = findShortestPath(tempArr, enemy);

//            System.out.println(nextMove);

            if(nextMove != Direction.NULL){
                Objects currentTile = board.getTypeAt(enemy);
                if(currentTile == Objects.ENEMYANDREWARD){
                    board.setTypeAt(enemy, Objects.REWARD);
                }else if(currentTile == Objects.ENEMYANDTRAP){
                    board.setTypeAt(enemy, Objects.TRAP);
                }else{
                    board.setTypeAt(enemy, Objects.EMPTY);
                }
            }

            switch (nextMove) {
                case NORTH:
                    enemy.incrementY();
                    break;
                case EAST:
                    enemy.incrementX();
                    break;
                case SOUTH:
                    enemy.decrementY();
                    break;
                case WEST:
                    enemy.decrementX();
                    break;
                case NULL:
                    break;

            }

            if(nextMove != Direction.NULL){
                Objects currentTile = board.getTypeAt(enemy);
                if(currentTile == Objects.REWARD){
                    board.setTypeAt(enemy, Objects.ENEMYANDREWARD);
                }else if(currentTile == Objects.TRAP){
                    board.setTypeAt(enemy, Objects.ENEMYANDTRAP);
                }else if(currentTile == Objects.HERO){
                    gameStats.setGameOver(true);
                }else{
                    board.setTypeAt(enemy, Objects.ENEMY);
                }
            }
        }

    }

    //findShortestPath runs a bfs on a 2d char array to find the direction the enemy at enemyPos
    //needs to move in order to get to the hero with the shortest distance
    public Direction findShortestPath(Objects[][] board, Position enemyPos){
        int width = board.length;
        int height = board[0].length;


        Queue<Node> q = new LinkedList<>();
        Node start = new Node(enemyPos, Direction.NULL, 0);
        q.add(start);

        while(!q.isEmpty()){
            Node head = q.poll();

            //'h' represents hero on the board
            if(board[head.getX()][head.getY()] == Objects.HERO){
                return head.initialDirection;
            }
            else{
                //Objects.TREE represents tree on the board, I swap a visited node to a tree
                //so that the bfs knows not to search the head node again.
                board[head.getX()][head.getY()] = Objects.TREE;

                q.addAll(getNeighbours(head, board, width, height));
            }


        }
        return Direction.NULL;
    }

    //getNeighbours returns a list containing all the direct neighbours to head
    private List<Node> getNeighbours(Node head, Objects[][] board, int width, int height){
        int x = head.getX();
        int y = head.getY();

        List<Node> neighbours = new LinkedList<>();
        //check if there is a non tree tile in each direction (North, East, South, West), if so, add it to neighbours list
        //only change the initialDirection if it is the initial node from where the search started (the enemy)
dddd            if(head.initialDirection == Direction.NULL){
                neighbours.add(new Node(new Position(x + 1, y), Direction.EAST, head.pathLength+1));
            }else{
                neighbours.add(new Node(new Position(x + 1, y), head.initialDirection, head.pathLength+1));
            }
        }

        if(x - 1 >= 0 && x - 1 < width && board[x - 1][y] != Objects.TREE){
            if(head.initialDirection == Direction.NULL){
                neighbours.add(new Node(new Position(x - 1, y), Direction.WEST, head.pathLength+1));
            }else{
                neighbours.add(new Node(new Position(x - 1, y), head.initialDirection, head.pathLength+1));
            }
        }

        if(y + 1 >= 0 && y + 1 < height && board[x][y + 1] != Objects.TREE){
            if(head.initialDirection == Direction.NULL){
                neighbours.add(new Node(new Position(x, y + 1), Direction.NORTH, head.pathLength+1));
            }else{
                neighbours.add(new Node(new Position(x, y + 1), head.initialDirection, head.pathLength+1));
            }
        }

        if(y - 1 >= 0 && y - 1 < height && board[x][y - 1] != Objects.TREE){
            if(head.initialDirection == Direction.NULL){
                neighbours.add(new Node(new Position(x, y - 1), Direction.SOUTH, head.pathLength+1));
            }else{
                neighbours.add(new Node(new Position(x, y - 1), head.initialDirection, head.pathLength+1));
            }
        }

        return neighbours;
    }
}

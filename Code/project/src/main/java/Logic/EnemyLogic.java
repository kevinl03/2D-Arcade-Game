package Logic;

import Entities.Enemy;
import Entities.Position;
import Helpers.Direction;
import Helpers.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EnemyLogic {

    private void processEnemyMovement(Enemy[] enemies){
        for (int i = 0; i < enemies.length; i++) {
            char[][] temp = {{'c'}};
            Direction nextMove = findShortestPath(temp , enemies[i]);
            switch(nextMove){
                case NORTH:
                    enemies[i].incrementY();
                    break;
                case EAST:
                    enemies[i].incrementX();
                    break;
                case SOUTH:
                    enemies[i].decrementY();
                    break;
                case WEST:
                    enemies[i].decrementX();
                    break;
                case NULL:
                    break;

            }
            //TODO complete this step
//            if(enemies[i].getX() == hero.x && enemies[i].getY() == hero.y){
//                game over
//            }
        }

    };

    //findShortestPath runs a bfs on a 2d char array to find the direction the enemy at enemyPos
    //needs to move in order to get to the hero with the shortest distance
    public Direction findShortestPath(char[][] board, Position enemyPos){
        int width = board.length;
        int height = board[0].length;

        Queue<Node> q = new LinkedList<Node>();
        Node start = new Node(enemyPos, Direction.NULL, 0);
        q.add(start);

        while(!q.isEmpty()){
            Node head = q.poll();

            //'h' represents hero on the board
            if(board[head.getX()][head.getY()] == 'h'){
                return head.initialDirection;
            }
            else{
                //'t' represents tree on the board, I swap a visited node to a tree
                //so that the bfs knows not to search the head node again.
                board[head.getX()][head.getY()] = 't';

                q.addAll(getNeighbours(head, board, width, height));
            }


        }
        return Direction.NULL;
    };

    //getNeighbours returns a list containing all the direct neighbours to head
    private List<Node> getNeighbours(Node head, char[][] board, int width, int height){
        int x = head.getX();
        int y = head.getY();
        Direction dir;

        List<Node> neighbours = new LinkedList<Node>();
        //check if there is a non tree tile in each direction (North, East, South, West), if so, add it to neighbours list
        //only change the initialDirection if it is the initial node from where the search started (the enemy)
        if(x + 1 >= 0 && x + 1 < width && board[x + 1][y] != 't'){
            if(head.initialDirection == Direction.NULL){
                neighbours.add(new Node(new Position(x + 1, y), Direction.EAST, head.pathLength+1));
            }else{
                neighbours.add(new Node(new Position(x + 1, y), head.initialDirection, head.pathLength+1));
            }
        }

        if(x - 1 >= 0 && x - 1 < width && board[x - 1][y] != 't'){
            if(head.initialDirection == Direction.NULL){
                neighbours.add(new Node(new Position(x - 1, y), Direction.WEST, head.pathLength+1));
            }else{
                neighbours.add(new Node(new Position(x - 1, y), head.initialDirection, head.pathLength+1));
            }
        }

        if(y + 1 >= 0 && y + 1 < height && board[x][y + 1] != 't'){
            if(head.initialDirection == Direction.NULL){
                neighbours.add(new Node(new Position(x, y + 1), Direction.NORTH, head.pathLength+1));
            }else{
                neighbours.add(new Node(new Position(x, y + 1), head.initialDirection, head.pathLength+1));
            }
        }

        if(y - 1 >= 0 && y - 1 < height && board[x][y - 1] != 't'){
            if(head.initialDirection == Direction.NULL){
                neighbours.add(new Node(new Position(x, y - 1), Direction.SOUTH, head.pathLength+1));
            }else{
                neighbours.add(new Node(new Position(x, y - 1), head.initialDirection, head.pathLength+1));
            }
        }

        return neighbours;
    }
}

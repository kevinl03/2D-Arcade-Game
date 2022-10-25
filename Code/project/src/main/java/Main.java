import Logic.EnemyLogic;
import Entities.Position;
import Helpers.Direction;

public class Main {

    public static void testShortestPath(){
        EnemyLogic gameLogic = new EnemyLogic();
        char [][] fakeMap= {
        //                             WEST
        //               y0   y1   y2   y3   y4   y5   y6   y7
        /*     x0  */  {'t', 't', 't', 't', 't', 't', 't', 't'},
        /*  S  x1  */  {'t', 'h', 't', 'o', 'o', 'o', 'o', 't'},  //  N
        /*  O  x2  */  {'t', 'o', 't', 't', 't', 'o', 'o', 't'},  //  O
        /*  U  x3  */  {'t', 'o', 't', 't', 'o', 'e', 'o', 't'},  //  R
        /*  T  x4  */  {'t', 'o', 't', 't', 't', 't', 'o', 't'},  //  T
        /*  H  x5  */  {'t', 'o', 'o', 'o', 'o', 'o', 'o', 't'},  //  H
        /*     x6  */  {'t', 't', 't', 't', 't', 't', 't', 't'},
        //                             EAST

        };

        Direction dir = gameLogic.findShortestPath(fakeMap, new Position(3, 5));

        System.out.println(dir);
    }

    public static void main (String[] args){
        testShortestPath();
    }
}

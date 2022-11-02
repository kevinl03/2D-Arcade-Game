package Board;

import Entities.Position;

import java.util.ArrayList;
import java.util.Random;

public class BoardData {
    private static int columns = 25;
    private static int rows = 15;

    private static Objects[][] ObjectMap = new Objects[columns][rows];

    //TODO REPLACE HARD CODE
//    private Difficulty dif = Difficulty.HARD;

    private void replaceTMPTrees() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (ObjectMap[i][j] == Objects.TMP) {
                    ObjectMap[i][j] = Objects.TREE;
                }
                ;
            }
        }
    }

    private void removeTMPTrees() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (ObjectMap[i][j] == Objects.TMP) {
                    ObjectMap[i][j] = Objects.EMPTY;
                }
                ;
            }
        }
    }

    public void setOuterWalls() {
        for (int i = 0; i < rows; i++) {
            ObjectMap[0][i] = Objects.TREE;
        }
        for (int i = 0; i < rows; i++) {
            ObjectMap[columns - 1][i] = Objects.TREE;
        }
        for (int i = 0; i < columns; i++) {
            ObjectMap[i][0] = Objects.TREE;
        }
        for (int i = 0; i < columns; i++) {
            ObjectMap[i][rows - 1] = Objects.TREE;
        }
    }

    static void setEmptyTiles() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                ObjectMap[i][j] = Objects.EMPTY;
            }
        }
    }

    //helper function
    static boolean foundAdjacentTrees(int xcoord, int ycoord) {
        boolean isadjecent = false;
        //check north west east west
        if (ObjectMap[xcoord + 1][ycoord] == Objects.TREE) {
            isadjecent = true;
        }
        if (ObjectMap[xcoord][ycoord + 1] == Objects.TREE) {
            isadjecent = true;
        }
        if (ObjectMap[xcoord - 1][ycoord] == Objects.TREE) {
            isadjecent = true;
        }
        if (ObjectMap[xcoord + 1][ycoord - 1] == Objects.TREE) {
            isadjecent = true;
        }
        //check diagonals because those can cause loops too
        if (ObjectMap[xcoord + 1][ycoord + 1] == Objects.TREE) {
            isadjecent = true;
        }
        if (ObjectMap[xcoord - 1][ycoord + 1] == Objects.TREE) {
            isadjecent = true;
        }
        if (ObjectMap[xcoord - 1][ycoord - 1] == Objects.TREE) {
            isadjecent = true;
        }
        if (ObjectMap[xcoord + 1][ycoord - 1] == Objects.TREE) {
            isadjecent = true;
        }

        return isadjecent;
    }

    //helper functions for generating map
    private void setInnerWalls() {

        Random rand = new Random(); //instance of random class

        //leave a gap between outter walls
        int maxwallheight = rows - 2;
        int minwallheight = 2;

        int maxwallwidth = columns - 2;
        int minwallwidth = 2;

        //int totalwalls = (int)Math.floor(Math.random()*(max-min+1)+min);


        boolean createdwall = false;
        while (!createdwall) {

            int startx = (int) Math.floor(Math.random() * (maxwallwidth - minwallwidth + 1) + minwallwidth);
            int starty = (int) Math.floor(Math.random() * (maxwallheight - minwallheight + 1) + minwallheight);

            //how many walls we make 

            int totalwalls = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);


            //if collision ever occurs, we go back to the top while to try again
            boolean failedgeneration = false;


            for (int wallnumber = 1; wallnumber <= totalwalls; wallnumber++) {

                int walllenght = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);


                //in order to rotate between vertical and horizontal wall gen
                if (wallnumber % 2 == 0) {
                    //start vertical 
                    int north = rand.nextInt(2);  //either 0 or 1, 
                    //draw walls 
                    for (int j = 0; j <= walllenght; j++) {
                        //out of bounds up 
                        if ((starty + j * (int) (Math.pow(-1, north))) > maxwallheight) {
                            failedgeneration = true;
                        } else if ((starty + j * (int) (Math.pow(-1, north))) < minwallheight) {
                            failedgeneration = true;
                        }
                        //check adjecency trees
                        else if (foundAdjacentTrees(startx, starty + j * (int) (Math.pow(-1, north)))) {
                            failedgeneration = true;
                        }
                        //generate tmp tree which we will replace only if full generation succeeds
                        else {
                            ObjectMap[startx][starty + j * (int) (Math.pow(-1, north))] = Objects.TMP;
                        }
                        //

                        if (failedgeneration) {
                            break;
                        }

                    }
                    //readjust starty after loop comples
                    starty += walllenght * (int) (Math.pow(-1, north));

                }
                //check if we got any errors in order to restart loop
                if (failedgeneration) {
                    break;
                } else {
                    int west = rand.nextInt(2);   //from line, we east or west. Walls will be L shapes
                    for (int j = 0; j <= walllenght; j++) {
                        //out of bounds up 
                        if (startx + j * (int) (Math.pow(-1, west)) > maxwallwidth) {
                            failedgeneration = true;
                        } else if (startx + j * (int) (Math.pow(-1, west)) < minwallwidth) {
                            failedgeneration = true;
                        }
                        //check adjecency trees
                        else if (foundAdjacentTrees(startx + j * (int) (Math.pow(-1, west)), starty)) {
                            failedgeneration = true;
                        }
                        //generate tmp tree which we will replace only if full generation succeeds
                        else {
                            ObjectMap[startx + j * (int) (Math.pow(-1, west))][starty] = Objects.TMP;
                        }
                        //

                        if (failedgeneration) {
                            break;
                        }
                    }
                    startx += walllenght * (int) (Math.pow(-1, west));

                }
                if (failedgeneration) {
                    break;
                }

            }
            //for successful wall segment generation
            if (failedgeneration == false) {
                //System.out.printf("successful generation");
                //in order to exit while loop

                createdwall = true;
                replaceTMPTrees();
            } else {
                //System.out.printf("fail generation");
                //get rid of unusable TMP trees which failed generation
                removeTMPTrees();
            }

        }
        //end of while loop

    }

    private void setRegRewards(Difficulty dif) {
        int rewardCount = 0;
        ArrayList<int[]> rewardLocations = new ArrayList<>();

        switch(dif){
            case EASY: rewardCount = 5;
                break;
            case MEDIUM: rewardCount = 10;
                break;
            case HARD:
            case INFINITE: rewardCount = 15;
                break;
        }

        for (int i = 0; i < rewardCount; i++) {
            int[] xy = getRandomXY();
            int x = xy[0];
            int y = xy[1];
//            System.out.println("X - " + x + "  Y - " + y);

            if(ObjectMap[x][y] == Objects.EMPTY){
                int[] loc = {x,y};
                boolean valid = checkValidRewardProximity(rewardLocations, loc, dif);
//                System.out.println(test);
//                    System.out.println(valid);
                if(valid){
                    ObjectMap[x][y] = Objects.REWARD;
                    rewardLocations.add(loc);
                }else{
                    i--;
                }
            }else{
                i--;
            }
        }
    }

    private boolean checkValidRewardProximity(ArrayList<int[]> rewards, int[] newReward, Difficulty dif){
//        System.out.println(rewards.size());

        int minProximity = 0;

        for (int[] reward : rewards) {
            int x = reward[0];
            int y = reward[1];
            int newX = newReward[0];
            int newY = newReward[1];

            switch(dif){
                case EASY: minProximity = 5;
                    break;
                case MEDIUM: minProximity = 3;
                    break;
                case HARD:
                case INFINITE: minProximity = 0;
                    break;
            }
            //using pythagorean theorem to make sure reward is atleast minProximity away from all other rewards
//            if(Math.sqrt(Math.pow(Math.abs(x-newX),2) + Math.pow(Math.abs(y-newY), 2)) < minProximity){
//                return false;
//            }
        }
        return true;
    }

    private void setBonusRewards() {


    }

    private void setEnemies(Difficulty dif) {
        int enemyCount = 0;
        switch(dif){
            case EASY:
                enemyCount = 2;
                break;
            case MEDIUM:
                enemyCount = 3;
                break;
            case HARD:
            case INFINITE:
                enemyCount = 5;
                break;
        }

        for(int i = 0; i < enemyCount; i++){
            int[] xy = getRandomXY();
            int x = xy[0];
            int y = xy[1];
            if(ObjectMap[x][y] == Objects.EMPTY) {
                ObjectMap[x][y] = Objects.ENEMY;
            }else{
                i--;
            }
        }
    }

    public void generateTraps(int count){

        for (int trapcount = 1; trapcount <= count; trapcount++){
            boolean posfound = false;
            while(!posfound){
                int[] coords = getRandomXY();
                //check to see if generation is a valid
                if (ObjectMap[coords[0]][coords[1]] == Objects.EMPTY){

                    ObjectMap[coords[0]][coords[1]] = Objects.TRAP;
                    posfound = true;
                }
                //else case means we need a new XY so we go back to while loop
                //and get another randomXY
            }
        }

    }
    private void setTraps(Difficulty dif) {
        //does not matter if traps are in close to eachother
        //so we don't check for proximity when generating
        switch(dif){
            case EASY:
                generateTraps(4);
            case MEDIUM:
                generateTraps(7);
            case HARD:
                generateTraps(11);
            case INFINITE:
                generateTraps(11);
        }




    }

    private void setHeroLocation() {

        boolean heroNotSpawned = true;

        while(heroNotSpawned){
            int[] xy = getRandomXY();
            int x = xy[0];
            int y = xy[1];
            if(ObjectMap[x][y] == Objects.EMPTY) {
                ObjectMap[x][y] = Objects.HERO;
                heroNotSpawned = false;
            }
        }
    }

    public void setDoor() {
        Random rand = new Random(); //instance of random class

        //0 = north, 1 = east, 2 = south, 3 = west;
        int side = rand.nextInt(3);
        int x = 0;
        int y = 0;

        switch(side){
            case(0):
                 x = rand.nextInt(columns-1);
                 y = rows-1;
                break;
            case(1):
                y = rand.nextInt(rows-1);
                x = columns-1;
                break;
            case(2):
                x = rand.nextInt(columns-1);
                y = 0;
                break;
            case(3):
                y = rand.nextInt(rows-1) ;
                x = 0;
                break;
        }

        ObjectMap[x][y] = Objects.EXIT;
    }


    //call initalise board at the begginning of every game
    public void initialiseBoard(Difficulty dif) {

        //may need to change the ordering
        setEmptyTiles();
        setOuterWalls();
        //harder difficulty means more wall segments so user has less space to move around
        int wallsegments = 1;
        switch(dif){
            case EASY: wallsegments = 5;
                break;
            case MEDIUM: wallsegments = 10;
                break;
            case HARD:
            case INFINITE: wallsegments = 12;
                break;
        }
//        System.out.println(-2);


        for (int i = 1; i < wallsegments; i++) {
            setInnerWalls();
        }
//        System.out.println(-1);
//
        setBonusRewards();
//        System.out.println(1);
        setRegRewards(dif);
//        System.out.println(2);
        setTraps(dif);
//        System.out.println(3);
        setEnemies(dif);
//        System.out.println(4);
        setHeroLocation();
//        System.out.println(5);
        setDoor();
//        System.out.println(6);
    }

    //following will be used in game logic

    public Objects getString(int x, int y) {
        return ObjectMap[x][y];
    }


    //currently throwing error until array type is switched to Object instead of string
    public Objects getTypeAt(Position pos) {
        return ObjectMap[pos.getX()][pos.getY()];
    }

    public void setTypeAt(Position pos, Objects type) {
        ObjectMap[pos.getX()][pos.getY()] = type;
    }

    public void updateBoard(int x, int y, Objects data) {
        ObjectMap[x][y] = data;
    }

    public int getboardheight() {
        return rows;
    }

    public int getboardwidth() {
        return columns;
    }

    private int[] getRandomXY() {

        Random rand = new Random(); //instance of random class

        //-2 + 1 in order to not consider the border
        int x = rand.nextInt(columns-2) + 1;
        int y = rand.nextInt(rows-2) + 1;

        int[] xy = {x,y};

        return xy;
    }

    // might be redunant
    public Objects[][] getBoardData(){
        return ObjectMap;
    }





    /* might be redunant
    public String[][] getBoardData(){
        return string2DArray;
    }
    */

}

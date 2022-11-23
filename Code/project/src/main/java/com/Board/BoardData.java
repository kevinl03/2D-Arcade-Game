package com.Board;

import com.Entities.Position;

import java.util.ArrayList;
import java.util.Random;

public class BoardData {

    //if ever need to change map size, this is the location
    private static int columns = 25;
    private static int rows = 15;

    //2Darray of objects containing all of the information to be processed from
    //during the game runtime
    private static Objects[][] ObjectMap = new Objects[columns][rows];


//    private Difficulty dif = Difficulty.HARD;

    /**
     * Used for replacing a TMP object wall segments with real TREE objects
     * for succesful segment generations
     */
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

    /**
     * helper function for removing TMP tree objects
     * during for innerWall generation
     */
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

    /**
     * set the perimiter of the map to TREE objects
     */
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

    /**
     * used for initilazing board to all EMPTY object tiles
     */
    static void setEmptyTiles() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                ObjectMap[i][j] = Objects.EMPTY;
            }
        }
    }

    //helper function

    /**
     * helper function for checking if any adjencent tiles to given coordinate contains a TREE
     *
     * @param xcoord
     * @param ycoord
     * @return boolean
     *
     */
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
        //check diagonals because those can cause closed off sections
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

    /**
     *
     * @param difficulty calls {@link #setSegment()} 15 times regardless of the difficulty
     *                   to create 15 individual wall segments
     */
    private void setInnerWalls(Difficulty difficulty){
        int wallsegments = 0;
        switch(difficulty){
            case EASY: wallsegments = 15;
                break;
            case MEDIUM: wallsegments = 15;
                break;
            case HARD:
            case INFINITE: wallsegments = 15;
                break;
        }

        for (int i = 1; i < wallsegments; i++) {
            setSegment();
        }

    }

    /**
     * Helper function for generating WallSegments
     *generate shapes of either 1,2, or 3 lines with changing directions
     *going north/south -> west/east -> north/south all of varying lenghts
     */
    private void setSegment() {

        Random rand = new Random(); //instance of random class

        //leave a gap between outter walls
        int maxwallheight = rows - 2;
        int minwallheight = 2;

        int maxwallwidth = columns - 2;
        int minwallwidth = 2;


        //variable holder used for if a wallsegment fails to generate
        //a certain number of times
        int attempts = 0;
        //keep looping if wall segment is created or not
        boolean createdwall = false;
        while (!createdwall) {

            //start position for the wall segment
            int startx = (int) Math.floor(Math.random() * (maxwallwidth - minwallwidth + 1) + minwallwidth);
            int starty = (int) Math.floor(Math.random() * (maxwallheight - minwallheight + 1) + minwallheight);

            //how many walls lines we make
            //can be either 1, or 3
            //so a zigzag or straight line
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
                        //when if edge cases pass
                        else {
                            ObjectMap[startx][starty + j * (int) (Math.pow(-1, north))] = Objects.TMP;
                        }
                        //
                        //exist loop
                        if (failedgeneration) {
                            break;
                        }

                    }
                    //readjust starty after loop completes
                    //will start drawing from new y position
                    starty += walllenght * (int) (Math.pow(-1, north));

                }
                //check if we got any errors in order to restart loop
                if (failedgeneration) {
                    break;
                }
                //case for drawing horizontal lines wall segments
                else {
                    //check if we got any errors in order to restart loop

                    //used to generate 1 or -1 so that we can raise -1 to power of it
                    //to indicate west or east
                    int west = rand.nextInt(2);   //from line,  east or west. Walls will be L shapes
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
                    //start position will now be the x coord
                    startx += walllenght * (int) (Math.pow(-1, west));

                }
                //exit loop
                if (failedgeneration) {
                    break;
                }

            }
            //for successful wall segment generation
            if (failedgeneration == false) {//in order to exit while loop

                createdwall = true;
                replaceTMPTrees();
            } else {
                //get rid of unusable TMP trees which failed generation
                removeTMPTrees();
            }
            //this creates a check so that a wall segment doesn't get stuck endlessly
            //in the while loop
            attempts++;
            if (attempts > 100){
                createdwall = true;
            }

        }
        //end of while loop

    }

    /**
     *
     * @param dif
     * exact same functionility as {@link #setEnemies(int[],Difficulty)} except for generating REWARD objects
     *
     */
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

            if(ObjectMap[x][y] == Objects.EMPTY){
                int[] loc = {x,y};
                boolean valid = checkValidRewardProximity(rewardLocations, loc, dif);
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

    /**
     *
     * @param rewards
     * @param newReward
     * @param dif
     * @return true if a reward generation is atleast atleast a certain distance
     *         away from the hero position
     *         else return false
     *
     */
    private boolean checkValidRewardProximity(ArrayList<int[]> rewards, int[] newReward, Difficulty dif){
//

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
            if(Math.sqrt(Math.pow(Math.abs(x-newX),2) + Math.pow(Math.abs(y-newY), 2)) < minProximity){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param hero
     * @param newEnemy
     * @param dif
     * @return true if an enemy position is too close to a player
     *              else return false
     */
    private boolean checkValidEnemyProximity(int[] hero, int[] newEnemy, Difficulty dif){

        int minProximity = 0;

        //variables holders
        int x = hero[0];
        int y = hero[1];
        int newX = newEnemy[0];
        int newY = newEnemy[1];

        switch(dif){
            case EASY: minProximity = 10;
            break;
            case MEDIUM: minProximity = 7;
            break;
            case HARD: case INFINITE: minProximity = 5;
            break;
        }
//            using pythagorean theorem to make sure reward is atleast minProximity away from all other rewards
        if(Math.sqrt(Math.pow(Math.abs(x-newX),2) + Math.pow(Math.abs(y-newY), 2)) < minProximity){
            return false;
        }
        return true;
    }

    /**
     * sets count number of BONUSREWARDS into the ObjectMap at randomly placed positions
     * @param count the number of BONUS objects to be randomly generated
     *
     *
     */
    public void generateBonusrewards(int count){
        //how many rewards
        for (int BonusCnt = 1; BonusCnt <= count; BonusCnt++){
            //need to stay in while loop while randomXY finds a position
            //that has an empty tile
            boolean posfound = false;
            while(!posfound){
                //generate an array {x,y} where both variables are within bounds of map
                int[] coords = getRandomXY();
                //check to see if generation is a valid
                if (ObjectMap[coords[0]][coords[1]] == Objects.EMPTY){

                    ObjectMap[coords[0]][coords[1]] = Objects.BONUS;
                    posfound = true;
                }
                //else case means we need a new XY so we go back to while loop
                //and get another randomXY
            }
        }

    }

    /**
     *
     * based on the difficulty, calls helper function {@link #generateBonusrewards(int)} method. See definition
     * @param dif Difficulty of the game
     *
     */
    private void setBonusRewards(Difficulty dif) {
        switch (dif){
            case EASY:
                generateBonusrewards(3);
                break;
            case MEDIUM:
                generateBonusrewards(2);
                break;
            case HARD:
            case INFINITE:
                generateBonusrewards(1);
                break;
        }
    }

    /**
     * based on the difficulty, calls helper function {@link #generateBonusrewards(int)} method. See definition
     * @param heroLoc heroes locations. See {@link #setHeroLocation()}
     * @param dif game difficulty
     *
     *
     */
    private void setEnemies(int[] heroLoc, Difficulty dif) {
        //initialize variable to hold
        int enemyCount = 0;
        switch(dif){
            case EASY:
                enemyCount = 1;
                break;
            case MEDIUM:
                enemyCount = 2;
                break;
            case HARD:
            case INFINITE:
                enemyCount = 3;
                break;
        }
        //loop for the number of enemies
        for(int i = 0; i < enemyCount; i++){
            int[] xy = getRandomXY();
            int x = xy[0];
            int y = xy[1];
            //must pass
            //1)checkValidEnemy Proximity
            //2)be on empty tile
            //3)tile below cannot be a tree (creates bug when generating bear initially
            if(!checkValidEnemyProximity(heroLoc, xy, dif) || ObjectMap[x][y] != Objects.EMPTY || ObjectMap[x][y+1] == Objects.TREE){
                //the loop did create a succesful generation so decrement by one
                i--;
            }else{
                //checkValidEnemyProximity return true
                ObjectMap[x][y] = Objects.ENEMY;
            }

        }
    }

    /**
     * Generate 5 randomly located BUSH objects on ObjectMap
     */
    private void setBushes() {
        int bushCount = 5;
        for(int i = 0; i < bushCount; i++){
            //get random xy coordinate
            int[] xy = getRandomXY();
            int x = xy[0];
            int y = xy[1];
            //must be an empty tile
            if(ObjectMap[x][y] == Objects.EMPTY) {
                ObjectMap[x][y] = Objects.BUSH;
            }else{
                //generation did not succesful place a bush
                //so decrement i for one more iteration of loop
                i--;
            }
        }
    }

    /**
     * Generate count number of randomly placed traps on ObjectMap
     * @param count number of traps
     *
     */
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

    /**
     *
     * @param dif based on the difficulty, call {@link #generateTraps(int)} method()
     *            with a different number of traps to generate
     *
     */
    private void setTraps(Difficulty dif) {
        //does not matter if traps are in close to eachother
        //so we don't check for proximity when generating
        switch(dif){
            case EASY:
                generateTraps(4);
                break;
            case MEDIUM:
                generateTraps(7);
                break;
            case HARD:
                generateTraps(11);
                break;
            case INFINITE:
                generateTraps(11);
                break;
        }




    }

    private int[] setHeroLocation() {

        //FIXED
        /*
        boolean heroNotSpawned = true;

        while(heroNotSpawned){
            int[] xy = getRandomXY();
            int x = xy[0];
            int y = xy[1];
            if(ObjectMap[x][y] == Objects.EMPTY) {
                ObjectMap[x][y] = Objects.HERO;
                heroNotSpawned = false;
                int heroLoc[] = {x,y};
                return heroLoc;
            }
        }
        //case will never hit
        return null;

         */
        Random rand = new Random(); //instance of random class

        // Hero at south wall, so door appears west,north,east wall
        //Somewhere on the south wall
        int x = rand.nextInt(columns-2)+1;
        int y = rows-1;

        ObjectMap[x][y] = Objects.HERO;
        int heroLoc[] = {x,y};
        return heroLoc;
    }

    /**
     * Randomly generate the door on the perimeter of the map for the player to exit
     *
     */
    public void setDoor() {
        Random rand = new Random(); //instance of random class

        //0 = north, 1 = east, 2 = south, 3 = west;
        //generate number between 0 and 3
        int side = rand.nextInt(2);
        int x = 0;
        int y = 0;

        switch(side){
            // Hero at south wall, so door appears west,north,east wall
            /*
            case(0):
                 //Somewhere on the south wall
                 x = rand.nextInt(columns-2)+1;
                 y = rows-1;
                break;

             */
            case(0):
                //Somewhere on the WEST wall
                y = rand.nextInt(rows-2)+1;
                x = columns-1;
                break;
            case(1):
                //North wall
                x = rand.nextInt(columns-2)+1;
                y = 0;
                break;
            case(2):
                //EAST wall
                y = rand.nextInt(rows-2)+1 ;
                x = 0;
                break;
        }
        ObjectMap[x][y] = Objects.EXIT;
    }


    /**
     *
     * @param dif
     * Generates a new, unique, random map
     * based on the difficulty argument provided.
     * see following function descriptions for the methods used in this method
     * {@link #setEmptyTiles()}
     * {@link #setOuterWalls()}
     * {@link #setInnerWalls(Difficulty)}
     * {@link #setBonusRewards(Difficulty)}
     * {@link #setBonusRewards(Difficulty)}
     * {@link #setRegRewards(Difficulty)}
     * {@link #setTraps(Difficulty)}
     * {@link #setEnemies(int[], Difficulty)}
     * {@link #setDoor()}
     * {@link #setBushes()}
     */
    public void initialiseBoard(Difficulty dif) {

        int heroLoc[];

        //empty tiles to show to which positions will be available
        setEmptyTiles();
        //walls
        setOuterWalls();
        setInnerWalls(dif);

        //static entities generations
        setBonusRewards(dif);
        setRegRewards(dif);
        setTraps(dif);

        //moving entities
        heroLoc = setHeroLocation();
        setEnemies(heroLoc, dif);

        //miscelanous generation
        setDoor();
        setBushes();
    }

    //following will be used in game logic

    /**
     *
     * @param x
     * @param y
     * @return ObjectMap[x][y]
     * compatible with seperate x and y coordinates
     */
    public Objects getTypeAt(int x, int y) {
        return ObjectMap[x][y];
    }


    /**
     *
     * @param pos
     * @return ObjectMap[pos.getX()][pos.getY()]
     * compatible with Position class parameter
     * see {@link Position#getX()}  and  {@link Position#getY()}
     */
    public Objects getTypeAt(Position pos) {
        return ObjectMap[pos.getX()][pos.getY()];
    }

    /**
     *
     * @param pos
     * @param type
     * set index at pos.getX() and pos.getY() to type
     * see {@link Position#getX()}  and  {@link Position#getY()}
     */
    public void setTypeAt(Position pos, Objects type) {
        ObjectMap[pos.getX()][pos.getY()] = type;
    }

    /**
     *
     * @param x
     * @param y
     * @param data
     * compatible with x and y coordinates, see {@link #setTypeAt(Position, Objects)}
     */
    public void updateBoard(int x, int y, Objects data) {
        ObjectMap[x][y] = data;
    }

    /**
     *
     * @return how many rows
     */
    public int getboardheight() {
        return rows;
    }

    /**
     *
     * @return how many columns
     */
    public int getboardwidth() {
        return columns;
    }

    /**
     *
     * @return an array of ints where array[0] = x
     */
    private int[] getRandomXY() {

        Random rand = new Random(); //instance of random class

        //-2 + 1 in order to not consider the border
        int x = rand.nextInt(columns-2) + 1;
        int y = rand.nextInt(rows-2) + 1;
        //store inside of array
        int[] xy = {x,y};

        return xy;
    }

    /**
     *
     * @return ObjectMap variable
     */
    public Objects[][] getBoardData(){
        return ObjectMap;
    }


}

    /* might be redunant
    public String[][] getBoardData(){
        return string2DArray;
    }
    */


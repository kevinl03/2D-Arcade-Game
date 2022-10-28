package Board;

import Entities.Position;
import Board.Objects;

import java.util.Random;

public class BoardData {
    private static int columns = 25;
    private static int rows = 15;

    private static Objects[][] ObjectMap = new Objects[columns][rows];

    private void replaceTMPTrees(){
        for(int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (ObjectMap[i][j] == Objects.TMP){
                    ObjectMap[i][j] = TREE;
                };
            }
        }
    }

    private void removeTMPTrees(){
        for(int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (ObjectMap[i][j] == Object.TMP){
                    ObjectMap[i][j] = Objects.EMPTY;
                };
            }
        }
    }

    
    /**
     * 
     */
    public void setOuterWalls(){
        for (int i = 0; i < rows; i++){
            ObjectMap[0][i] = Object.TREE;
        }
        for (int i = 0; i < rows; i++){
            ObjectMap[columns-1][i] = Object.TREE;
        }
        for (int i = 0; i < columns; i++){
            ObjectMap[i][0] = Object.TREE;
        }
        for (int i = 0; i < columns; i++){
            ObjectMap[i][rows-1] = Object.TREE;
        }
    }

    static void setEmptyTiles(){
        for(int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                ObjectMap[i][j] = Objects.EMPTY;
            }
        }
    }

    //helper function
    static boolean foundAdjacentTrees(int xcoord, int ycoord){
        boolean isadjecent = false;
        //check north west east west
        if (ObjectMap[xcoord+1][ycoord] == Object.TREE){
            isadjecent = true;
        }
        if (ObjectMap[xcoord][ycoord+1] == Object.TREE){
            isadjecent = true;
        }
        if (ObjectMap[xcoord-1][ycoord] == Object.TREE){
            isadjecent = true;
        }
        if (ObjectMap[xcoord+1][ycoord-1] == Object.TREE){
            isadjecent = true;
        }
        //check diagonals because those can cause loops too
        if (ObjectMap[xcoord+1][ycoord+1] == Object.TREE){
            isadjecent = true;
        }
        if (ObjectMap[xcoord-1][ycoord+1] == Object.TREE){
            isadjecent = true;
        }
        if (ObjectMap[xcoord-1][ycoord-1] == Object.TREE){
            isadjecent = true;
        }
        if (ObjectMap[xcoord+1][ycoord-1] == Object.TREE){
            isadjecent = true;
        }

        return isadjecent;
    }
    //helper functions for generating map
    private void setInnerWalls(){
        
        Random rand = new Random(); //instance of random class

        //leave a gap between outter walls
        int maxwallheight = rows - 2;
        int minwallheight = 2;

        int maxwallwidth = columns - 2;
        int minwallwidth = 2;        

        //int totalwalls = (int)Math.floor(Math.random()*(max-min+1)+min);




        boolean createdwall = false;
        while (!createdwall){

            int startx = (int)Math.floor(Math.random()*(maxwallwidth-minwallwidth+1)+minwallwidth);
            int starty = (int)Math.floor(Math.random()*(maxwallheight-minwallheight+1)+minwallheight);

            //how many walls we make 

            int totalwalls = (int)Math.floor(Math.random()*(3-1+1)+1);




            //if collision ever occurs, we go back to the top while to try again
            boolean failedgeneration = false;





            for (int wallnumber = 1; wallnumber <= totalwalls; wallnumber++) {
                
                int walllenght = (int)Math.floor(Math.random()*(5-1+1)+1);



                //in order to rotate between vertical and horizontal wall gen
                if (wallnumber%2 == 0){
                    //start vertical 
                    int north = rand.nextInt(2);  //either 0 or 1, 
                    //draw walls 
                    for (int j = 0; j <= walllenght; j++){
                        //out of bounds up 
                        if ((starty + j*(int)(Math.pow(-1,north))) > maxwallheight){
                            failedgeneration = true;
                        }
                        else if ((starty + j*(int)(Math.pow(-1,north))) < minwallheight){
                            failedgeneration = true;
                        }
                        //check adjecency trees
                        else if (foundAdjacentTrees(startx,starty+j*(int)(Math.pow(-1,north)))){
                            failedgeneration = true;
                        }
                        //generate tmp tree which we will replace only if full generation succeeds
                        else {
                            ObjectMap[startx][starty+j*(int)(Math.pow(-1,north))] = Object.TMP;
                        }
                        //

                        if (failedgeneration){
                            break;
                        }

                    }
                    //readjust starty after loop comples
                    starty+=walllenght*(int)(Math.pow(-1,north));

                }
                //check if we got any errors in order to restart loop
                if (failedgeneration){
                    break;
                }


                else {
                    int west = rand.nextInt(2);   //from line, we east or west. Walls will be L shapes
                    for (int j = 0; j <= walllenght; j++){
                        //out of bounds up 
                        if (startx + j*(int)(Math.pow(-1,west)) > maxwallwidth){
                            failedgeneration = true;
                        }
                        else if (startx + j*(int)(Math.pow(-1,west)) < minwallwidth){
                            failedgeneration = true;
                        }
                        //check adjecency trees
                        else if (foundAdjacentTrees(startx+j*(int)(Math.pow(-1,west)),starty)){
                            failedgeneration = true;
                        }
                        //generate tmp tree which we will replace only if full generation succeeds
                        else {
                            ObjectMap[startx+j*(int)(Math.pow(-1,west))][starty] = Object.TMP;
                        }
                        //
                        
                        if (failedgeneration){
                            break;
                        }
                }
                startx+=walllenght*(int)(Math.pow(-1,west));
                
            }
            if (failedgeneration){
                break;
            }
            
        }
        //for successful wall segment generation
        if (failedgeneration == false){
              //System.out.printf("successful generation");
              //in order to exit while loop
              
              createdwall = true;  
              replaceTMPTrees();
            }
        else {
            //System.out.printf("fail generation");
            //get rid of unusable TMP trees which failed generation
            removeTMPTrees();
        }

        }
         //end of while loop
        
    } 
    private void setRegRewards(){

    }
    private void setBonusRewards(){

    }
    private void setEnemies(){

    }
    private void setTraps(){

    }
    private void setHeroLocation(){

    }

    //will be used for ending the game
    public void setDoor(){

    }


    //call initalise board at the begginning of every game
    public void initialiseBoard(){
        //may need to change the ordering
        setEmptyTiles();
        setOuterWalls();
        //harder difficulty means more wall segments so user has less space to move around
        int wallsegments = 6;
        for (int i = 1; i < wallsegments; i++ ){
            setInnerWalls();
        }
        setBonusRewards();
        setRegRewards();
        setTraps();
        setEnemies();
        setHeroLocation();
    }

    //following will be used in game logic

    public String getString(int x, int y){
        return ObjectMap[x][y];
    }



    /*currently throwing error until array type is switched to Object instead of string
    public Objects getTypeAt(Position pos) {return string2DArray[pos.getX()][pos.getY()];}

    public void setTypeAt(Position pos, Objects type){
        string2DArray[pos.getX()][pos.getY()] = type;
    }
    */
    public void updateBoard(int x, int y, stringNames data){
        ObjectMap[x][y] = data.toString();
    }

    public int getboardheight(){
        return rows;
    }

    public int getboardwidth(){
        return columns;
    }
    /* might be redunant
    public String[][] getBoardData(){
        return string2DArray;
    }
    */

}

    /* might be redunant
    public String[][] getBoardData(){
        return string2DArray;
    }
    */

}

import java.util.Random;

public class BoardData {
    private static int columns = 25;
    private static int rows = 15;

    private static String[][] string2DArray = new String[columns][rows];


    //to know organize all of the string names
    public enum Objects {
        HERO, ENEMY, REWARD, BONUS, TRAP, TREE, EXIT, EMPTY
    }

    private void replaceTMPTrees(){
        for(int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (string2DArray[i][j] == "TMP"){
                    string2DArray[i][j] = "TREE";
                };
            }
        }
    }

    private void removeTMPTrees(){
        for(int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (string2DArray[i][j] == "TMP"){
                    string2DArray[i][j] = "EMPTY";
                };
            }
        }
    }

    
    /**
     * 
     */
    public void setOuterWalls(){
        for (int i = 0; i < rows; i++){
            string2DArray[0][i] = "TREE";
        }
        for (int i = 0; i < rows; i++){
            string2DArray[columns-1][i] = "TREE";
        }
        for (int i = 0; i < columns; i++){
            string2DArray[i][0] = "TREE";
        }
        for (int i = 0; i < columns; i++){
            string2DArray[i][rows-1] = "TREE";
        }
    }

    static void setEmptyTiles(){
        for(int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                string2DArray[i][j] = "EMPTY";
            }
        }
    }

    //helper function
    static boolean foundAdjacentTrees(int xcoord, int ycoord){
        boolean isadjecent = false;
        //check north west east west
        if (string2DArray[xcoord+1][ycoord] == "TREE"){
            isadjecent = true;
        }
        if (string2DArray[xcoord][ycoord+1] == "TREE"){
            isadjecent = true;
        }
        if (string2DArray[xcoord-1][ycoord] == "TREE"){
            isadjecent = true;
        }
        if (string2DArray[xcoord+1][ycoord-1] == "TREE"){
            isadjecent = true;
        }
        //check diagonals because those can cause loops too
        if (string2DArray[xcoord+1][ycoord+1] == "TREE"){
            isadjecent = true;
        }
        if (string2DArray[xcoord-1][ycoord+1] == "TREE"){
            isadjecent = true;
        }
        if (string2DArray[xcoord-1][ycoord-1] == "TREE"){
            isadjecent = true;
        }
        if (string2DArray[xcoord+1][ycoord-1] == "TREE"){
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
                            string2DArray[startx][starty+j*(int)(Math.pow(-1,north))] = "TMP";
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
                            string2DArray[startx+j*(int)(Math.pow(-1,west))][starty] = "TMP";
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
        return string2DArray[x][y];
    }
    public void updateBoard(int x, int y, stringNames data){
        string2DArray[x][y] = data.toString();
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

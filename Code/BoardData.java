public class BoardData {
    private static int columns = 25;
    private static int rows = 15;

    private static String[][] string2DArray = new String[columns][rows];


    //to know organize all of the string names
    public static enum stringNames {
        HERO,
      
        ENEMY,

        BONUSREWARD,

        Tree
      }

    /**
     * 
     */
    public void setOuterWalls(){
        for (int i = 0; i < rows; i++){
            string2DArray[0][i] = stringNames.Tree.toString();
        }
        for (int i = 0; i < rows; i++){
            string2DArray[columns-1][i] = "Tree";
        }
        for (int i = 0; i < columns; i++){
            string2DArray[i][0] = "Tree";
        }
        for (int i = 0; i < columns; i++){
            string2DArray[i][rows-1] = "Tree";
        }
    }

    //helper functions for generating map
    private void setInnerWalls(){
        //may randomize these if we implement difficulties

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

    public void initialiseBoard(){
        //may need to change the ordering
        setOuterWalls();
        setInnerWalls();
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

    /* might be redunant
    public String[][] getBoardData(){
        return string2DArray;
    }
    */

}

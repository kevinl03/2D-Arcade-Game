package Display;

import Board.Objects;
import Entities.Enemy;
import Entities.Hero;
import Entities.Position;
import Helpers.KeyHandler;
import Logic.EnemyLogic;
import Logic.HeroLogic;
import Logic.RewardLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Creates this JPanel to represent the Game screen
 */
public class myGame extends JPanel{
    private int pixelsize = 60;   //60x60 pixels
    private int columns = 25;
    private int rows = 15;
    protected HashMap<String, BufferedImage> squirrel_pngs;
    protected HashMap<String, BufferedImage> hidden_squirrel_pngs;
    private BufferedImage chocolate_png;
    private HashMap<String, BufferedImage> bear_pngs;
    private BufferedImage bush_png;
    private BufferedImage hunter_png;
    private BufferedImage peanuts_png;
    private BufferedImage[] tree_pngs;
    private BufferedImage board_png;
    private BufferedImage exit_png;
    private BufferedImage trap_png;
    KeyHandler kh;
    private CardLayout cl;
    private DisplayLayout dl;
    public int goMain = 0;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    Font font;
    private int seconds;

    Objects[][] boardMap;

    EnemyLogic enemyLogic;
    HeroLogic heroLogic;

    RewardLogic rewardLogic;

    public boolean firstRender;

    public ArrayList<Integer> treeTypeOrder;

    /**
     * Constructor creates the game screen.
     * Waits for when play button in Main Menu gets pressed, then
     * game screen will update and repaint until player chooses to
     * go back to main menu, loses, or wins.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myGame(DisplayLayout dl, CardLayout cl){
        this.cl = cl;
        this.dl = dl;
        this.kh = dl.kh;
        //rows*pixelsize+60 we have +60 for putting the time and scores at the top of
        //and additional row of trees
        setPreferredSize(new Dimension(columns*pixelsize, rows*pixelsize+60));
        getImages();
        addKeyListener(kh);
        this.setLayout(null);
        timeLabel = new JLabel();
        timeLabel.setText("Time");
        scoreLabel = new JLabel();
        scoreLabel.setText("Score");
        font = new Font("Times New Roman", Font.BOLD, 30);
    }

    /**
     * Increments the time and the character position based on key pressed.
     * Using the board data for this game, after detecting the user's key press,
     * the movement is processed and the characters are updated in the board data.
     * @throws InterruptedException if this thread is interrupted
     */
    public void updates() throws InterruptedException {

        dl.timer+=525;
        seconds = dl.timer/1000;

        Hero hero = dl.gameObjectData.getHero();

        Position heroPos = new Position(hero.getX(), hero.getY());

        enemyLogic = dl.gameObjectData.getEnemyLogic();
        heroLogic = dl.gameObjectData.getHeroLogic();
        rewardLogic = dl.gameObjectData.getRewardLogic();

        //if two opposite keys are pressed then
        //player movement remains the same
        if (kh.up && !kh.down ) {
            heroPos.decrementY();
        }

        else if (kh.down && !kh.up) {
            heroPos.incrementY();
        }

        else if (kh.left && !kh.right) {
            heroPos.decrementX();
        }

        else if (kh.right && !kh.left) {
            heroPos.incrementX();
        }
        rewardLogic.updateRewards(dl.gameObjectData, dl.timer);
        heroLogic.processPlayerMovement(heroPos, dl.gameObjectData);
        enemyLogic.processEnemyMovement(dl.gameObjectData);

        if (kh.escape) {
            if(dl.pause == 0) {
                dl.pause = 1;

                //if panel is not open, pop out panel
                if (dl.currentCard != 5) {
                    // show associated pause panel
                    cl.show(dl.displayPanel, "5");

                    // current panel is pause Panel
                    dl.currentCard = 5;

                    dl.pausePanel.setFocusable(true);
                    dl.pausePanel.requestFocus();
                }
            }
        }
    }

    /**
     * Sets a hashmap of Image objects that can then be painted on the screen.
     * A hashmap of squirrel images are used to simulate sprite animation.
     */
    public void getSquirrel(){
        try {
            squirrel_pngs = new HashMap<>();
            File folder = new File(getClass().getResource("/squirrels").toURI());
            File[] files = folder.listFiles();
            for(final File fileEntry : files){
                if(fileEntry.isFile()){
                    String fileName = fileEntry.getName();
                    squirrel_pngs.put(fileName, ImageIO.read(getClass().getResource("/squirrels/" + fileName)));
                }
            }

            hidden_squirrel_pngs = new HashMap<>();
            File folder2 = new File(getClass().getResource("/hidingSquirrels").toURI());
            File[] files2 = folder2.listFiles();
            for(final File fileEntry : files2){
                if(fileEntry.isFile()){
                    String fileName = fileEntry.getName();
                    hidden_squirrel_pngs.put(fileName, ImageIO.read(getClass().getResource("/hidingSquirrels/" + fileName)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getAcorn(){
        try {
            chocolate_png = ImageIO.read(getClass().getResource("/chocolate.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getBear(){
        try {
            bear_pngs = new HashMap<>();
            File folder = new File(getClass().getResource("/bears").toURI());
            File[] files = folder.listFiles();
            for(final File fileEntry : files){
                if(fileEntry.isFile()){
                    String fileName = fileEntry.getName();
                    bear_pngs.put(fileName, ImageIO.read(getClass().getResource("/bears/" + fileName)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getBush(){
        try {
            bush_png = ImageIO.read(getClass().getResource("/bush1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getHunter(){
        try {
            hunter_png = ImageIO.read(getClass().getResource("/hunter.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getPeanuts(){
        try {
            peanuts_png = ImageIO.read(getClass().getResource("/acorn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getTrees(){
        try {
            tree_pngs = new BufferedImage[3];
            for(int i = 1; i < 4; i++){
                tree_pngs[i-1] = ImageIO.read(getClass().getResource("/tree" + i + ".png"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getExit(){
        try {
            exit_png = ImageIO.read(getClass().getResource("/exit.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getTrap(){
        try {
            trap_png = ImageIO.read(getClass().getResource("/trap4.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getField(){
        try {
            board_png = ImageIO.read(getClass().getResource("/board2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calls all the methods to set the Image objects.
     */
    public void getImages(){
        getSquirrel();
        getAcorn();
        getBear();
        getBush();
        getHunter();
        getPeanuts();
        getTrees();
        getField();
        getExit();
        getTrap();
    }

    /**
     * This method de-presses the keys after game loop is over.
     */
    public void reset(){
        kh.up = false;
        kh.left = false;
        kh.down = false;
        kh.right = false;
    }

    /**
     * This method draws the squirrel sprite animation based on direction.
     * @param g2 the Graphics class object to put onto JPanel
     */
    private void drawHero(Graphics2D g2){
        Hero hero = dl.gameObjectData.getHero();
        int col = hero.getX();
        int row = hero.getY();

        if(hero.isHidden()){
            if(dl.frameCounter == 6){
                hero.setAtBush(true);
            }
            if(hero.isAtBush()){
                g2.drawImage(hidden_squirrel_pngs.get("SquirrelHiding" + hero.getHeroColor().toString() + ".png"), col * 60 + 10, row * 60+60 + 10, pixelsize-20, pixelsize-20, null);
                return;
            }
        }
        hero.setAtBush(false);

        int animationFrame = hero.getAnimationFrame();
        String dir = hero.getDir().toString();
        String color = hero.getHeroColor().toString();
        int movementProgress = 0;
        if(hero.isMoving()){
            switch(dl.frameCounter){
                case 0 -> movementProgress = 52;
                case 1 -> movementProgress = 43;
                case 2 -> movementProgress = 35;
                case 3 -> movementProgress = 24;
                case 4 -> movementProgress = 16;
                case 5 -> movementProgress = 7;
            }

            if(dir == "North" || dir == "West"){
                movementProgress = movementProgress * -1;
            }
            //if direction in the y-axis
            if(dir == "North" || dir == "South"){
                g2.drawImage(squirrel_pngs.get("Squirrel" + color + dir + animationFrame + ".png"), col * 60, row * 60+65 - movementProgress, pixelsize-10, pixelsize-10, null);
            }else{
                g2.drawImage(squirrel_pngs.get("Squirrel" + color + dir + animationFrame + ".png"), col * 60 - movementProgress, row * 60+65, pixelsize-10, pixelsize-10, null);
            }
        }else{
            g2.drawImage(squirrel_pngs.get("Squirrel" + color + dir + "1.png"), col * 60, row * 60+65, pixelsize-10, pixelsize-10, null);
        }

        if(dl.frameCounter == 6) {
            hero.setMoving(false);
        }

        hero.incrementAnimationFrame();
    }

    /**
     * This method draws the enemies sprite animation based on direction.
     * @param g2 the Graphics class object to put onto JPanel
     */
    private void drawEnemies(Graphics2D g2){
        ArrayList<Enemy> enemies = dl.gameObjectData.getEnemies();
        for(Enemy enemy : enemies){

            int col = enemy.getX();
            int row = enemy.getY();
            int animationFrame = enemy.getAnimationFrame();
            String dir = enemy.getDir().toString();

            int movementProgress = 0;
            switch(dl.frameCounter){
                case 0 -> movementProgress = 52;
                case 1 -> movementProgress = 43;
                case 2 -> movementProgress = 35;
                case 3 -> movementProgress = 24;
                case 4 -> movementProgress = 16;
                case 5 -> movementProgress = 7;

            }

            if(dir == "North" || dir == "West"){
                movementProgress = movementProgress * -1;
            }

            //if direction in the y-axis
            if(dir == "North" || dir == "South"){
                g2.drawImage(bear_pngs.get("Bear"  + dir + animationFrame + ".png"), col * 60, row * 60+65 - movementProgress, pixelsize-10, pixelsize-10, null);
            }else{
                g2.drawImage(bear_pngs.get("Bear"  + dir + animationFrame + ".png"), col * 60 - movementProgress, row * 60+65, pixelsize-10, pixelsize-10, null);

            }
            enemy.incrementAnimationFrame();
        }


    }

    /**
     * Draws the board, characters, time, and score on the Game screen.
     * This method is called whenever repaint() is called in the game loop.
     * Uses the board data to determine which image to draw.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){   //   Draw something on JPanel
        super.paintComponent(g);   //   Method already exists, so super is used to add additional lines
        Graphics2D g2 = (Graphics2D) g;   //   Draws shapes
        g.drawImage(board_png, 0, 0, 1500, 960, null);
        Hero hero = dl.gameObjectData.getHero();
        boardMap = dl.board.getBoardData();

        int currentTree = 0;



        for(int col = 0; col < 25; col++){
            for(int row = 0; row < 15; row++){
                switch (boardMap[col][row]) {
                    case TREE:
                        if(firstRender) {
                            Random rand = new Random();
                            treeTypeOrder.add(rand.nextInt(3));
                        }
                        if(row == 0) {
                            g2.drawImage(tree_pngs[(treeTypeOrder.get(currentTree)+1)%3], col * 60, 0, pixelsize, pixelsize, null);
                        }
                        g2.drawImage(tree_pngs[treeTypeOrder.get(currentTree)], col * 60, row * 60+60, pixelsize, pixelsize, null);
                        currentTree++;
                    break;
                    case HEROHIDDEN:
                        if(!hero.isAtBush()){
                            g2.drawImage(bush_png, col * 60 + 10, row * 60+60 + 10, pixelsize - 20, pixelsize - 20, null);
                        }
                        break;
                    case ENEMYANDBUSH:
                    case BUSH:
                            g2.drawImage(bush_png, col * 60 + 10, row * 60+60 + 10, pixelsize - 20, pixelsize - 20, null);
                        break;
                    case ENEMYANDTRAP:
                    case TRAP:
                        g2.drawImage(trap_png, col * 60+20, row * 60+60+20, pixelsize-40, pixelsize-40, null);
                        break;
                    case ENEMYANDREWARD:
                    case REWARD:
                        g2.drawImage(peanuts_png, col * 60 + 15, row * 60+60 + 15, pixelsize-30, pixelsize - 30, null);

                    break;
                    case BONUS:
                        //hide the object on the first render
                        if(!firstRender) {
                            g2.drawImage(chocolate_png, col * 60, row * 60 + 60, pixelsize - 30, pixelsize - 30, null);
                        }
                    break;
                    //no exit image yet
                    case EXIT: g2.drawImage(exit_png, col * 60, row * 60+60, pixelsize, pixelsize, null);
                    break;

                }
            }
        }

        drawHero(g2);
        drawEnemies(g2);

        firstRender = false;

        g2.setColor(Color.gray);
        g2.fillRect(700,0,150,30);
        g2.setFont(font);
        g2.setColor(Color.white);
        g2.drawString(scoreLabel.getText() + ": " + dl.gameObjectData.getHero().getScore(), 700, 25);

        g2.setColor(Color.gray);
        g2.fillRect(1300,0,150,30);
        g2.setFont(font);
        g2.setColor(Color.white);
        g2.drawString(timeLabel.getText() + ": " + seconds, 1300, 25);
        g2.dispose();
    }
}
package com.Display;

import com.Board.Objects;
import com.Entities.Enemy;
import com.Entities.Hero;
import com.Entities.Position;
import com.Helpers.Direction;
import com.Helpers.KeyHandler;
import com.Logic.EnemyLogic;
import com.Logic.HeroLogic;
import com.Logic.RewardLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Creates this JPanel to represent the com.Game screen
 */
public class myGame extends JPanel{


    private int tileHeight;
    private int tileWidth;
    protected HashMap<String, BufferedImage> squirrelPngs;
    protected HashMap<String, BufferedImage> hiddenSquirrelPngs;
    private BufferedImage bonusPng;
    private HashMap<String, BufferedImage> bearPngs;
    private BufferedImage bushPng;
    private BufferedImage rewardPng;
    private BufferedImage[] treePngs;
    private BufferedImage boardPng;
    private BufferedImage exitPng;
    private BufferedImage trapPng;
    private BufferedImage buttonPng;
    private HashMap<String, BufferedImage> staticImages;
    KeyHandler kh;
    private final CardLayout cl;
    private final DisplayLayout dl;
    public int goMain = 0;
    private final JLabel timeLabel;
    private final JLabel scoreLabel;
    Font font;

    Objects[][] boardMap;

    EnemyLogic enemyLogic;
    HeroLogic heroLogic;

    RewardLogic rewardLogic;

    public boolean firstRender;

    public ArrayList<Integer> treeTypeOrder;

    /**
     * Constructor creates the game screen.
     * Waits for when play button in com.Main Menu gets pressed, then
     * game screen will update and repaint until player chooses to
     * go back to main menu, loses, or wins.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myGame(DisplayLayout dl, CardLayout cl){
        this.cl = cl;
        this.dl = dl;
        this.kh = dl.kh;
        //rows*pixel size+60 we have +60 for putting the time and scores at the top of
        //and additional row of trees
        int columns = 25;
        int rows = 15;
        setPreferredSize(new Dimension(columns *tileWidth, rows *tileHeight+60));
        getImages();
        addKeyListener(kh);
        this.setLayout(null);
        timeLabel = new JLabel();
        timeLabel.setText("Time");
        scoreLabel = new JLabel();
        scoreLabel.setText("Score");
        font = new Font("Times New Roman", Font.BOLD, 30);
        firstRender = true;
        treeTypeOrder = new ArrayList<>();
        tileHeight = dl.displayheight/(rows +1);
        tileWidth = dl.displaywidth/ columns;
    }

    /**
     * Increments the time and the character position based on key pressed.
     * Using the board data for this game, after detecting the user's key press,
     * the movement is processed and the characters are updated in the board data.
     * @throws InterruptedException if this thread is interrupted
     */
    public void updates() throws InterruptedException {

        dl.timer+=525;
        //int seconds = dl.timer / 1000;

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
            //can't go down in starting point
            if(hero.getY()!=14) {
                heroPos.incrementY();
            }
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
                    cl.show(dl.displayPanel, "Pause");

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
            squirrelPngs = new HashMap<>();
            URL pathUrl = getClass().getClassLoader().getResource("squirrels/");
            if ((pathUrl != null) && pathUrl.getProtocol().equals("file")) {
                File[] files = new File(pathUrl.toURI()).listFiles();
                assert files != null;
                for(final File fileEntry : files){
                    if(fileEntry.isFile()){
                        String fileName = fileEntry.getName();
                        squirrelPngs.put(fileName, ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/squirrels/" + fileName))));
                    }
                }
            }

            hiddenSquirrelPngs = new HashMap<>();

            URL pathUrl2 = getClass().getClassLoader().getResource("hidingSquirrels/");
            if ((pathUrl2 != null) && pathUrl2.getProtocol().equals("file")) {
                File[] files2 = new File(pathUrl2.toURI()).listFiles();
                assert files2 != null;
                for (final File fileEntry : files2) {
                    if (fileEntry.isFile()) {
                        String fileName = fileEntry.getName();
                        hiddenSquirrelPngs.put(fileName, ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/hidingSquirrels/" + fileName))));
                    }
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getBonusReward(){
        try {
            bonusPng = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/chocolate.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getBear(){
        try {

            bearPngs = new HashMap<>();
            URL pathUrl = getClass().getClassLoader().getResource("bears/");

            if ((pathUrl != null) && pathUrl.getProtocol().equals("file")) {
                File[] files = new File(pathUrl.toURI()).listFiles();
                assert files != null;
                for (final File fileEntry : files) {
                    if (fileEntry.isFile()) {
                        String fileName = fileEntry.getName();
                        bearPngs.put(fileName, ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/bears/" + fileName))));
                    }
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getBush(){
        try {
            bushPng = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/bush1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getReward(){
        try {
            rewardPng = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/acorn.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getTrees(){
        try {
            treePngs = new BufferedImage[3];
            for(int i = 1; i < 4; i++){
                treePngs[i-1] = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/tree" + i + ".png")));
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
            exitPng = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/door.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getTrap(){
        try {
            trapPng = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/trap4.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets an Image object that can then be painted on the screen.
     */
    public void getField(){
        try {
            boardPng = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/board2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getButton(){
        try {
            buttonPng = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource("/RecButton.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calls all the methods to set the Image objects.
     */
    public void getImages(){
        getBonusReward();
        getSquirrel();
        getBear();
        getBush();
        getReward();
        getTrees();
        getField();
        getExit();
        getTrap();
        getButton();
        staticImages = new HashMap<>();
        staticImages.put("reward", rewardPng);
        staticImages.put("bush", bushPng);
        staticImages.put("bonus", bonusPng);
        staticImages.put("exit",exitPng);
        staticImages.put("trap", trapPng);
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
            if(dl.frameCounter == 4){
                hero.setAtBush(true);
            }
            if(hero.isAtBush()){
                g2.drawImage(hiddenSquirrelPngs.get("SquirrelHiding" + hero.getHeroColor().toString() + ".png"), col * tileWidth + 10, row * tileHeight+60 + 10, tileWidth-20, tileHeight-20, null);
//                Objects.HERO.draw(g2, col, row, hiddenSquirrelPngs.get("SquirrelHiding" + hero.getHeroColor().toString() + ".png"), tileHeight, tileWidth);
                return;
            }
        }
        hero.setAtBush(false);

        int animationFrame = hero.getAnimationFrame();
        String dir = hero.getDir().toString();
        String color = hero.getHeroColor().toString();
        int movementProgress;
        if(hero.isMoving()){
            if(hero.getDir() == Direction.NORTH || hero.getDir() == Direction.SOUTH){
                movementProgress = tileHeight/5 * (4 - dl.frameCounter);
            }else{
                movementProgress = tileWidth/5 * (4 - dl.frameCounter);
            }

            if(java.util.Objects.equals(dir, "North") || java.util.Objects.equals(dir, "West")){
                movementProgress = movementProgress * -1;
            }
            //if direction in the y-axis
            if(java.util.Objects.equals(dir, "North") || java.util.Objects.equals(dir, "South")){
                g2.drawImage(squirrelPngs.get("Squirrel" + color + dir + animationFrame + ".png"), col * tileWidth, row * tileHeight+65 - movementProgress, tileWidth-10, tileHeight-10, null);
            }else{
                g2.drawImage(squirrelPngs.get("Squirrel" + color + dir + animationFrame + ".png"), col * tileWidth - movementProgress, row * tileHeight+65, tileWidth-10, tileHeight-10, null);
            }
        }else{
            g2.drawImage(squirrelPngs.get("Squirrel" + color + dir + "1.png"), col * tileWidth, row * tileHeight+65, tileWidth-10, tileHeight-10, null);
        }

        if(dl.frameCounter == 4) {
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

            int movementProgress;
            if(enemy.getDir() == Direction.NORTH || enemy.getDir() == Direction.SOUTH){
                movementProgress = tileHeight/5 * (4 - dl.frameCounter);
            }else{
                movementProgress = tileWidth/5 * (4 - dl.frameCounter);
            }

            if(java.util.Objects.equals(dir, "North") || java.util.Objects.equals(dir, "West")){
                movementProgress = movementProgress * -1;
            }

            //if direction in the y-axis
            if(java.util.Objects.equals(dir, "North") || java.util.Objects.equals(dir, "South")){
                g2.drawImage(bearPngs.get("Bear"  + dir + animationFrame + ".png"), col * tileWidth, row * tileHeight+65 - movementProgress, tileWidth-10, tileHeight-10, null);
            }else{
                g2.drawImage(bearPngs.get("Bear"  + dir + animationFrame + ".png"), col * tileWidth - movementProgress, row * tileHeight+65, tileWidth-10, tileHeight-10, null);

            }
            enemy.incrementAnimationFrame();
        }


    }

    /**
     * Draws the board, characters, time, and score on the com.Game screen.
     * This method is called whenever repaint() is called in the game loop.
     * Uses the board data to determine which image to draw.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){   //   Draw something on JPanel
        super.paintComponent(g);   //   Method already exists, so super is used to add additional lines
        Graphics2D g2 = (Graphics2D) g;   //   Draws shapes
        g.drawImage(boardPng, 0, 0, dl.displaywidth, dl.displayheight, null);
        boardMap = dl.board.getBoardData();

        int currentTree = 0;



        for(int col = 0; col < 25; col++){
            for(int row = 0; row < 15; row++){
                Object obj = boardMap[col][row];
                if(obj == Objects.TREE){
                    if(firstRender) {
                        Random rand = new Random();
                        treeTypeOrder.add(rand.nextInt(3));
                    }
                    try {
                        if (row == 0) {
                            g2.drawImage(treePngs[(treeTypeOrder.get(currentTree) + 1) % 3], col * tileWidth, 0, tileWidth, tileHeight, null);
                        }
                        g2.drawImage(treePngs[treeTypeOrder.get(currentTree)], col * tileWidth, row * tileHeight + 60, tileWidth, tileHeight, null);
                    }catch(Exception ignored){}
                    currentTree++;
                }else if (obj != Objects.HERO && obj != Objects.ENEMY){
                    BufferedImage png = staticImages.get(obj.toString());
                    boardMap[col][row].draw(g2, col, row, png, tileHeight, tileWidth);
                }
            }
        }

        drawHero(g2);
        drawEnemies(g2);

        firstRender = false;

        g2.drawImage(buttonPng, (int)(dl.displaywidth*0.24)-175, 10, 175, 45, null);
        g2.setFont(font);
        g2.setColor(Color.black);
        g2.drawString(scoreLabel.getText() + ": " + dl.gameObjectData.getHero().getScore(), (int)(dl.displaywidth*0.25)-175, 42);

        g2.drawImage(buttonPng, (int)(dl.displaywidth*0.74), 10, 175, 45, null);
        g2.setFont(font);
        g2.setColor(Color.black);
        g2.drawString(timeLabel.getText() + ": " + dl.timer/1000, (int)(dl.displaywidth*0.75), 42);
        g2.dispose();
    }
}
package Display;

import Board.Objects;
import Entities.Hero;
import Entities.Position;
import Helpers.KeyHandler;
import Logic.EnemyLogic;
import Logic.HeroLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class myGame extends JPanel{
    private int pixelsize = 60;   //60x60 pixels
    private int columns = 25;
    private int rows = 15;
    private BufferedImage squirrel_png;
    private BufferedImage acorn_png;
    private BufferedImage bear_png;
    private BufferedImage bush_png;
    private BufferedImage hunter_png;
    private BufferedImage peanuts_png;
    private BufferedImage tree_png;
    private BufferedImage board_png;
    private BufferedImage exit_png;
    private BufferedImage trap_png;
    KeyHandler kh;
    public int updaterows;
    public int updatecolumns;
    private CardLayout cl;
    private DisplayLayout dl;
    private JPanel dp;

    private JPanel mp;
    public int goMain = 0;

    Objects[][] boardMap;

    EnemyLogic enemyLogic;
    HeroLogic heroLogic;
    public myGame(CardLayout cl, DisplayLayout dl, JPanel dp, JPanel mp){
        this.cl = cl;
        this.dl = dl;
        this.dp = dp;
        this.mp = mp;
        this.kh = dl.kh;
        setPreferredSize(new Dimension(columns*pixelsize, rows*pixelsize));
        getImages();
        addKeyListener(kh);
    }

    public void updates() throws InterruptedException {
        //----------------GAME LOGIC LATER-----------------------
        //
        //
        //
//        boardMap = dl.board.getBoardData();
        Thread.sleep(300);
        Hero hero = dl.gameObjectData.getHero();

        Position heroPos = new Position(hero.getX(), hero.getY());

        enemyLogic = dl.gameObjectData.getEnemyLogic();
        heroLogic = dl.gameObjectData.getHeroLogic();


        if (kh.up && (updaterows != 0) ) {
            heroPos.decrementY();
        }

        else if (kh.down && (updaterows != 14)) {
            heroPos.incrementY();
        }

        else if (kh.left && (updatecolumns != 0)) {
            heroPos.decrementX();
        }

        else if (kh.right && (updatecolumns != 24)) {
            heroPos.incrementX();
        }

        heroLogic.processPlayerMovement(heroPos, dl.gameObjectData);
        enemyLogic.processEnemyMovement(dl.gameObjectData);


//        //Cannot go through trees
//        if (kh.up && (updaterows != 0) ) {
//            if(boardMap[updatecolumns][updaterows-1]!= Objects.TREE && !kh.down) {
//                updaterows--;
//                if(boardMap[updatecolumns][updaterows] == Objects.REWARD){
//                    System.out.println("Got reward");
//                }
//                if(boardMap[updatecolumns][updaterows] == Objects.TRAP){
//                    System.out.println("Got trapped");
//                }
//                boardMap[updatecolumns][updaterows] = Objects.HERO;
//                boardMap[updatecolumns][updaterows+1] = Objects.EMPTY;
//            }
//        }
//
//        else if (kh.down && (updaterows != 14)) {
//            if(boardMap[updatecolumns][updaterows+1]!=Objects.TREE && !kh.up) {
//                updaterows++;
//                if(boardMap[updatecolumns][updaterows] == Objects.REWARD){
//                    System.out.println("Got reward");
//                }
//                if(boardMap[updatecolumns][updaterows] == Objects.TRAP){
//                    System.out.println("Got trapped");
//                }
//                boardMap[updatecolumns][updaterows] = Objects.HERO;
//                boardMap[updatecolumns][updaterows-1] = Objects.EMPTY;
//            }
//        }
//
//        else if (kh.left && (updatecolumns != 0)) {
//            if(boardMap[updatecolumns-1][updaterows]!=Objects.TREE && !kh.right) {
//                updatecolumns--;
//                if(boardMap[updatecolumns][updaterows] == Objects.REWARD){
//                    System.out.println("Got reward");
//                }
//                if(boardMap[updatecolumns][updaterows] == Objects.TRAP){
//                    System.out.println("Got trapped");
//                }
//                boardMap[updatecolumns][updaterows] = Objects.HERO;
//                boardMap[updatecolumns+1][updaterows] = Objects.EMPTY;
//            }
//        }
//
//        else if (kh.right && (updatecolumns != 24)) {
//            if(boardMap[updatecolumns+1][updaterows]!=Objects.TREE && !kh.left) {
//                updatecolumns++;
//                if(boardMap[updatecolumns][updaterows] == Objects.REWARD){
//                    System.out.println("Got reward");
//                }
//                if(boardMap[updatecolumns][updaterows] == Objects.TRAP){
//                    System.out.println("Got trapped");
//                }
//                boardMap[updatecolumns][updaterows] = Objects.HERO;
//                boardMap[updatecolumns-1][updaterows] = Objects.EMPTY;
//            }
//        }

        //////////////////////////////////////////////////////////////////
        if (kh.escape) {
            if(dl.unpause == 0) {
                System.out.println("Paused");
                dl.unpause = 1;

                //if panel is not open, pop out panel
                if (dl.currentCard != 5) {
                    // show associated pause panel
                    cl.show(dp, "5");

                    // current panel is pause Panel
                    dl.currentCard = 5;

                    mp.setFocusable(true);
                    mp.requestFocus();
                }
            }
        }
    }

    public void getSquirrel(){
        try {
            squirrel_png = ImageIO.read(getClass().getResource("/squirrel.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAcorn(){
        try {
            acorn_png = ImageIO.read(getClass().getResource("/acorn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getBear(){
        try {
            bear_png = ImageIO.read(getClass().getResource("/bear.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getBush(){
        try {
            bush_png = ImageIO.read(getClass().getResource("/bush.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getHunter(){
        try {
            hunter_png = ImageIO.read(getClass().getResource("/hunter.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPeanuts(){
        try {
            peanuts_png = ImageIO.read(getClass().getResource("/peanuts.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTree(){
        try {
            tree_png = ImageIO.read(getClass().getResource("/tree.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getExit(){
        try {
            exit_png = ImageIO.read(getClass().getResource("/exit.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getTrap(){
        try {
            trap_png = ImageIO.read(getClass().getResource("/trap.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getField(){
        try {
            board_png = ImageIO.read(getClass().getResource("/board.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getImages(){
        getSquirrel();
        getAcorn();
        getBear();
        getBush();
        getHunter();
        getPeanuts();
        getTree();
        getField();
        getExit();
        getTrap();
    }

    public void reset(){
        kh.up = false;
        kh.left = false;
        kh.down = false;
        kh.right = false;
        updaterows = 7;
        updatecolumns = 12;
    }

    @Override
    protected void paintComponent(Graphics g){   //   Draw something on JPanel
        super.paintComponent(g);   //   Method already exists, so super is used to add additional lines
        Graphics2D g2 = (Graphics2D) g;   //   Draws shapes
        //g2.drawImage(squirrel_png, updatecolumns * 60, updaterows * 60, pixelsize, pixelsize, null);
        //g2.drawRect(updatecolumns*60, updaterows*60, pixelsize,pixelsize);
        g.drawImage(board_png, 0, 0, 1500, 900, null);

        boardMap = dl.board.getBoardData();

        for(int col = 0; col < 25; col++){
            for(int row = 0; row < 15; row++){
                switch (boardMap[col][row]) {
                    case TREE: g2.drawImage(tree_png, col * 60, row * 60, pixelsize, pixelsize, null);
                    break;
                    case HERO: g2.drawImage(squirrel_png, col * 60, row * 60, pixelsize, pixelsize, null);
                    break;
                    case ENEMYANDTRAP:
                    case ENEMYANDREWARD:
                    case ENEMY: g2.drawImage(bear_png, col * 60, row * 60, pixelsize, pixelsize, null);
                    break;
                    case REWARD: g2.drawImage(peanuts_png, col * 60, row * 60, pixelsize, pixelsize, null);
                    break;
                    case BONUS: g2.drawImage(acorn_png, col * 60, row * 60, pixelsize, pixelsize, null);
                    break;
                    case TRAP: g2.drawImage(trap_png, col * 60, row * 60, pixelsize, pixelsize, null);
                    break;
                    //no exit image yet
                    case EXIT: g2.drawImage(exit_png, col * 60, row * 60, pixelsize, pixelsize, null);
                    break;
                }
            }
        }

        g2.dispose();
    }
}
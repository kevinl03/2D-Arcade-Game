package Display;

import Board.Difficulty;
import Game.ObjectData;
import Helpers.HeroColor;
import Helpers.KeyHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

import Board.BoardData;
public class DisplayLayout extends JFrame implements Runnable{
    KeyHandler kh = new KeyHandler(this);

    // Set up display
    protected int pixelsize = 60;   //60x60 pixels
    private int columns = 26;
    private int rows = 17;

    private int displaywidth = pixelsize * columns;
    private int displayheight = pixelsize * rows;

    // Initialize value of current panel is 1=title, 2=play, 3=settings, 4=difficulty
    public int currentCard = 1;

    // Declare CardLayout class objects.
    private CardLayout dl;

    public JPanel displayPanel;
    private myTitle titlePanel;
    public myGame playPanel;
    private mySettings settPanel;
    private myDifficulty diffPanel;
    private myGameOver gameOver;
    private myGameWon gameWon;
    private JPanel pausePanel;
    private JLabel soundLabel;
    private boolean gameovertest;

    public boolean gameWonTest;


    private GridBagConstraints gbc;
    public int pause = 0;
    Font titleText;
    Font headerText;

    //gameplay
    Thread gameThread;
    private int whileexit = 0;
    Image buttonIcon;

    BoardData board;

    ObjectData gameObjectData;

    public int timer;

    public Difficulty dif = Difficulty.EASY;

    HeroColor heroColor = HeroColor.BROWN;

    int frameCounter = 0;

    Sound sound = new Sound();


    // Set up display
    public DisplayLayout()
    {
        setResizable(false);

        titleText = new Font("Times New Roman", Font.BOLD, 50);
        headerText = new Font("Times New Roman", Font.BOLD, 30);
        // Function to set visibility of JFrame
        setTitle("Hidden Squirrel: Peanuts and Acorns");

        // Function to set visibility of JFrame
        setSize(displaywidth-45,displayheight-21);

        // Creating Object of JPanel class
        displayPanel = new JPanel();

        // Initialize object CardLayout class.
        dl = new CardLayout();

        // set the layout
        displayPanel.setLayout(dl);
        gbc = new GridBagConstraints();   //   Helps position buttons

        // Initialize Title JPanel class
        titlePanel = new myTitle(this,dl);

        // Initialize Setting JPanel class
        settPanel = new mySettings(this, dl);

        // Initialize Difficulty JPanel class
        diffPanel = new myDifficulty(this,dl);

        // Initialize Pause JPanel class
        pausePanel = new myPause(this, dl);

        // Initialize Play JPanel class
        playPanel = new myGame(dl, this, displayPanel, pausePanel);

        // Initialize GameOver
        gameOver = new myGameOver(this, dl);

        //Initialize GameWon
        gameWon = new myGameWon(this, dl);

//        sound.startupMusic();

        // Adding the cardPanel into layout, constraints associates panel
        //always shows First panel
        displayPanel.add(titlePanel, "1");
        displayPanel.add(playPanel, "2");
        displayPanel.add(settPanel, "3");
        displayPanel.add(diffPanel, "4");
        displayPanel.add(pausePanel, "5");
        displayPanel.add(gameOver, "6");
        displayPanel.add(gameWon, "7");


        //---------------------------------------TITLE-----------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------
        //---------------------------------Settings in mySettings----------------------------------------------
        //-----------------------------------Difficulty in myDifficulty----------------------------------------
        //-------------------------------------PAUSE in myPause------------------------------------------------
        //-----------------------------------GAME OVER in myGameOver-------------------------------------------
        //-----------------------------------GAME WON in myGameWon---------------------------------------------
        //-----------------------------------------------------------------------------------------------------

        // used to get content pane
        //shows the display that we created above
        // (chooses the first one added which happens to be title screen)
        getContentPane().add(displayPanel);
        sound.startupMusic();
    }

    public void startThread(){
        gameThread = new Thread(this);   //   When thread is created, use current object's run method
        gameThread.start();
    }

    //the bulk of the game code runs here
    @Override
    public void run() {   //   When starting thread, have thread use this run method
        playPanel.goMain = 0;
        gameovertest = false;
        gameWonTest = false;
        timer = 0;
        playPanel.firstRender = true;
        playPanel.treeTypeOrder = new ArrayList<Integer>();

        //Game starts when pressed
        playPanel.repaint();
        while(!kh.up && !kh.down && !kh.left && !kh.right && !kh.escape){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while (playPanel.goMain == 0 ) {
            try {
                if(pause == 0 && frameCounter == 0) {
                    playPanel.updates();
                }
                if( (playPanel.goMain == 0) && (pause == 0) ) {
                    playPanel.repaint();
                }
                if(gameObjectData.getGameStats().getGameOver()){
                    playPanel.goMain = 1;
                    gameovertest = true;
                    pause = 0;
                    kh.escape = false;
                    //Go back to main menu
                    System.out.println("Game is over");
                    playPanel.firstRender = true;
                }
                if(gameObjectData.getGameStats().isGameWon()){
                    playPanel.goMain = 1;
                    gameWonTest = true;
                    pause = 0;
                    kh.escape = false;
                    System.out.println("Game has been won!!!");
                }

                //choosing how long every thread lasts
                Thread.sleep(75);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            frameCounter++;
            if(frameCounter > 6){
                frameCounter = 0;
            }
        }
        System.out.println("Out of the game");
        //reset the game
        playPanel.reset();

        if(gameovertest) {
            //Show game over
            dl.show(displayPanel, "6");
            //always need to update currentCard
            currentCard = 6;
            gameOver.setValues(timer / 1000, gameObjectData.getHero().getScore());
        }
        if(gameWonTest){
            dl.show(displayPanel, "7");
            currentCard = 6;
            gameWon.setValues(timer/1000, gameObjectData.getHero().getScore());
        }
    }


    // Main Method
    public static void main(String[] args)
    {
        // Creating Object of CardLayoutDemo class.
        DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set visibility of JFrame.
        display.setVisible(true);


    }
}
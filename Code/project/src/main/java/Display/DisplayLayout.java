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

/**
 * Creates a JFrame with multiple JPanels for different screens of the game.
 */
public class DisplayLayout extends JFrame implements Runnable{
    KeyHandler kh = new KeyHandler(this);

    // Set up display
    protected int pixelsize = 60;   //60x60 pixels
    private int columns = 26;
    private int rows = 17;

    private int displaywidth = pixelsize * columns;
    private int displayheight = pixelsize * rows;
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
    public JPanel pausePanel;
    private boolean gameovertest;
    public boolean gameWonTest;


    private GridBagConstraints gbc;
    public int pause = 0;
    Font titleText;
    Font headerText;
    Thread gameThread;
    BoardData board;

    ObjectData gameObjectData;

    public int timer;

    public Difficulty dif = Difficulty.EASY;

    HeroColor heroColor = HeroColor.BROWN;

    int frameCounter = 0;

    Sound sound = new Sound();


    /**
     * Constructor creates and shows this JFrame and displays the title screen first.
     * This frame contains one JPanel uses a CardLayout manager, indexing the 7
     * other JPanels that represents the screens. The different JPanels are for
     * the screens of title, gameplay, settings, difficulty, pause, game over,
     * and game won.
     */
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
        playPanel = new myGame(this, dl);

        // Initialize GameOver
        gameOver = new myGameOver(this, dl);

        //Initialize GameWon
        gameWon = new myGameWon(this, dl);

        // Adding the cardPanel into layout, constraints associates panel
        //always shows First panel
        displayPanel.add(titlePanel, "1");
        displayPanel.add(playPanel, "2");
        displayPanel.add(settPanel, "3");
        displayPanel.add(diffPanel, "4");
        displayPanel.add(pausePanel, "5");
        displayPanel.add(gameOver, "6");
        displayPanel.add(gameWon, "7");


        //----------------------------------Title in myTitle-------------------------------------------------
        //----------------------------------Play in myGame---------------------------------------------------
        //----------------------------------Settings in mySettings-------------------------------------------
        //----------------------------------Difficulty in myDifficulty---------------------------------------
        //----------------------------------PAUSE in myPause-------------------------------------------------
        //----------------------------------GAME OVER in myGameOver------------------------------------------
        //----------------------------------GAME WON in myGameWon--------------------------------------------

        // used to get content pane
        //shows the display that we created above
        // (chooses the first one added which happens to be title screen)
        getContentPane().add(displayPanel);
        sound.startupMusic();
    }

    /**
     * Starts a new thread and executes the Override run method.
     */
    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //the bulk of the game code runs here

    /**
     * Main game loop.
     * This method first sets up initial conditions, then waits for user
     * to press the W,A,S,D, or ESC key before running the game loop. While
     * the user is playing, the game loop will update the logic once and call
     * the repaint method 7 times to simulate sprite animations. The end of
     * the game loop has a thread.sleep() method to imitate Frames per Second.
     * If the user presses the ESC key, the updates and repaints will skip.
     * This method's game loop will exit once the user presses the Main Menu
     * button on the pause screen, or when user wins or loses in the game.
     */
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

    /**
     * This is the main method to create and display the game.
     * Creates this JFrame object that holds all the JPanels and
     * displays them on executable window.
     * @param args Command line arguments
     */
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
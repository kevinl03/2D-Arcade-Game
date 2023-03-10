package com.Display;

import com.Board.Difficulty;
import com.Entities.Hero;
import com.Game.ObjectData;
import com.Helpers.HeroColor;
import com.Helpers.KeyHandler;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import com.Board.BoardData;

/**
 * Creates a JFrame with multiple JPanels for different screens of the game.
 */
public class DisplayLayout extends JFrame implements Runnable{
    public KeyHandler kh = new KeyHandler(this);

    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int displaywidth = screenSize.width;
    int displayheight = screenSize.height;

    public int currentCard = 1;

    // Declare CardLayout class objects.
    private final CardLayout dl;

    public JPanel displayPanel;
    private final myTitle titlePanel;
    public myGame playPanel;
    private final mySettings settPanel;
    private final myDifficulty diffPanel;
    private final myGameOver gameOver;
    private final myGameWon gameWon;
    public JPanel pausePanel;
    public boolean gameovertest;
    public boolean gameWonTest;


    public int pause = 0;
    Font titleText;
    Font headerText;
    Thread gameThread;
    BoardData board;

    ObjectData gameObjectData;

    public int timer;

    public Difficulty dif = Difficulty.MEDIUM;

    public HeroColor heroColor = HeroColor.BROWN;

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
        setSize(displaywidth,displayheight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);

        // Creating Object of JPanel class
        displayPanel = new JPanel();

        // Initialize object CardLayout class.
        dl = new CardLayout();

        // set the layout
        displayPanel.setLayout(dl);

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
        displayPanel.add(titlePanel, "Title");
        displayPanel.add(playPanel, "Play");
        displayPanel.add(settPanel, "Settings");
        displayPanel.add(diffPanel, "Difficulty");
        displayPanel.add(pausePanel, "Pause");
        displayPanel.add(gameOver, "GameOver");
        displayPanel.add(gameWon, "GameWon");


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
     * com.Main game loop.
     * This method first sets up initial conditions, then waits for user
     * to press the W,A,S,D, or ESC key before running the game loop. While
     * the user is playing, the game loop will update the logic once and call
     * the repaint method 7 times to simulate sprite animations. The end of
     * the game loop has a thread.sleep() method to imitate Frames per Second.
     * If the user presses the ESC key, the updates and repaints will skip.
     * This method's game loop will exit once the user presses the com.Main Menu
     * button on the pause screen, or when user wins or loses in the game.
     */
    @Override
    public void run() {   //   When starting thread, have thread use this run method
        playPanel.goMain = 0;
        gameovertest = false;
        gameWonTest = false;
        timer = 0;
        playPanel.firstRender = true;
        playPanel.treeTypeOrder = new ArrayList<>();

        //com.Game starts when pressed
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
                    playPanel.firstRender = true;
                }
                if(gameObjectData.getGameStats().isGameWon()){
                    if(gameObjectData.getDif() == Difficulty.INFINITE){
                        Hero tempHero = gameObjectData.getHero();
                        gameObjectData = null;
                        System.gc();
                        gameObjectData = new ObjectData(Difficulty.INFINITE, tempHero.getHeroColor(), tempHero.getScore());
                    }else{
                        playPanel.goMain = 1;
                        gameWonTest = true;
                        pause = 0;
                        kh.escape = false;
                    }
                }

                //choosing how long every thread lasts
                Thread.sleep(75);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            frameCounter++;
            if(frameCounter > 4){
                frameCounter = 0;
            }
        }
        //reset the game
        playPanel.reset();

        if(gameovertest) {
            //Show game over
            dl.show(displayPanel, "GameOver");
            //always need to update currentCard
            currentCard = 6;
            gameOver.setValues(timer / 1000, gameObjectData.getHero().getScore());
        }
        if(gameWonTest){
            dl.show(displayPanel, "GameWon");
            currentCard = 6;
            gameWon.setValues(timer/1000, gameObjectData.getHero().getScore());
        }
    }


    // com.Main Method

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

    public ObjectData getGameObjectData(){return gameObjectData;}
    
    public myTitle getTitlePanel(){return titlePanel;}

    public CardLayout getCardLayout(){return dl;}

    public myTitle getMyTitle(){return titlePanel;}

    public KeyHandler getKeyHandler(){return kh;}

    public mySettings getmySettings(){return settPanel;}

    public JPanel getmyPause(){return pausePanel;}

    public myDifficulty getDiffPanel(){return diffPanel;}

    public myGameOver getGameOver() {
        return gameOver;
    }

    public myGameWon getGameWon() {
        return gameWon;
    }
}
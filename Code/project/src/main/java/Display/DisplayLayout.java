package Display;

import Board.Difficulty;
import Game.ObjectData;
import Helpers.HeroColor;
import Helpers.KeyHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JPanel titlePanel;
    public myGame playPanel;
    private mySettings settPanel;
    private JPanel diffPanel;
    private myGameOver gameOver;
    private myGameWon gameWon;
    private JLabel titleLabel;
    private JLabel diffLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel gameLabel;
    private JButton playButton;
    private JButton settButton;
    private JButton diffButton;
    private JButton quitButton;
    private ButtonGroup difGroup;
    private JToggleButton easyButton;
    private JToggleButton mediumButton;
    private JToggleButton hardButton;
    private JButton difbackButton;
    private JPanel pausePanel;
    private JButton gomenuButton;

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

    private Difficulty dif = Difficulty.EASY;

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
        titlePanel = new myTitle();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBackground(Color.cyan);

        // Initialize Setting JPanel class
        settPanel = new mySettings(this, dl);

        // Initialize Difficulty JPanel class
        //make one later
        diffPanel = new JPanel();
        diffPanel.setLayout(new GridBagLayout());

        // Initialize Pause JPanel class
        pausePanel = new myPause(this, dl);

        // Initialize Play JPanel class
        playPanel = new myGame(dl, this, displayPanel, pausePanel);

        // Initialize GameOver
        gameOver = new myGameOver(this, dl);

        //Initialize GameWon
        gameWon = new myGameWon(this, dl);

        // Initialize labels for each JPanel
        titleLabel = new JLabel("Hidden Squirrel: Peanuts and Acorns");
        titleLabel.setFont(titleText);
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setOpaque(true);
        titleLabel.setVerticalAlignment(JLabel.TOP);

//        sound.startupMusic();

        // Adding labels onto the panels
        titlePanel.add(titleLabel);
        //diffPanel.add(diffLabel);

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
        // Initialize JButton objects and add to title Panel
        playButton = new JButton("   Play   ");
        try {
            buttonIcon = ImageIO.read(getClass().getResource("/acorn.png"));
            playButton.setIcon(new ImageIcon(buttonIcon));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gbc.insets = new Insets(100,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 200;
        gbc.ipady = 50;
        playButton.setFocusable(false);
        titlePanel.add(playButton, gbc);

        settButton = new JButton(" Settings ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        settButton.setFocusable(false);
        titlePanel.add(settButton, gbc);

        diffButton = new JButton("Difficulty");
        gbc.gridx = 0;
        gbc.gridy = 3;
        diffButton.setFocusable(false);
        titlePanel.add(diffButton, gbc);

        quitButton = new JButton("   Quit   ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        quitButton.setFocusable(false);
        titlePanel.add(quitButton, gbc);

        // add Play Button ActionListener
        playButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("creating object data with color " + heroColor.toString());
                gameObjectData = new ObjectData(dif, heroColor);
                board = gameObjectData.getBoard();

                // show associated play panel
                dl.show(displayPanel, "2");
                // current panel is play Panel
                currentCard = 2;
                playPanel.setFocusable(true);
                playPanel.requestFocus();
                startThread();
            }
        });

        // add Settings Button ActionListener
        settButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                // show associated setting panel
                dl.show(displayPanel, "3");

                // current panel is settings Panel
                currentCard = 3;
            }
        });

        // add Difficulty Button ActionListener
        diffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // show associated difficulty panel
                dl.show(displayPanel, "4");

                // current panel is difficulty Panel
                currentCard = 4;
            }
        });

        // add Quit Button ActionListener
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        //-----------------------------------------------------------------------------------------------------

        //---------------------------------Settings in mySettings----------------------------------------------

        //-----------------------------------Difficulty--------------------------------------------------------
        // Initialize Toggle/Button objects and add to buttongroup and Difficulty Panel
        difGroup = new ButtonGroup();
        easyButton = new JToggleButton(" Easy ", true);
        gbc.insets = new Insets(200,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 200;
        gbc.ipady = 50;
        easyButton.setFocusable(false);
        diffPanel.add(easyButton, gbc);

        mediumButton = new JToggleButton("Medium");
        gbc.insets = new Insets(50,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mediumButton.setFocusable(false);
        diffPanel.add(mediumButton, gbc);

        hardButton = new JToggleButton(" Hard ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        hardButton.setFocusable(false);
        diffPanel.add(hardButton, gbc);

        difbackButton = new JButton(" Back ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        difbackButton.setFocusable(false);
        diffPanel.add(difbackButton, gbc);

        // Toggles only 1 button
        difGroup.add(easyButton);
        difGroup.add(mediumButton);
        difGroup.add(hardButton);

        // add Easy Button ActionListener
        easyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //Change difficulty to easy
                dif = Difficulty.EASY;
            }
        });

        // add Medium Button ActionListener
        mediumButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //Change difficulty to medium
                dif = Difficulty.MEDIUM;
            }
        });

        // add Difficulty Button ActionListener
        hardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Change difficulty to hard
                dif = Difficulty.HARD;
            }
        });

        // add back Button ActionListener
        difbackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // show associated difficulty panel
                dl.show(displayPanel, "1");

                // current panel is difficulty Panel
                currentCard = 1;
            }
        });

        //-----------------------------------------------------------------------------------------------------

        //-------------------------------------PAUSE in myPause------------------------------------------------

        //-----------------------------------GAME OVER in myGameOver-------------------------------------------
        //-----------------------------------GAME WON in myGameWon---------------------------------------------
        //-----------------------------------------------------------------------------------------------------

        // used to get content pane
        //shows the display that we created above
        // (chooses the first one added which happens to be title screen)
        getContentPane().add(displayPanel);
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
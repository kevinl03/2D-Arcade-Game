package Display;

import Board.Difficulty;
import Board.Objects;
import Entities.Hero;
import Game.ObjectData;
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
    private int rows = 16;

    private int displaywidth = pixelsize * columns;
    private int displayheight = pixelsize * rows;

    // Initialize value of current panel is 1=title, 2=play, 3=settings, 4=difficulty
    public int currentCard = 1;

    // Declare CardLayout class objects.
    private CardLayout dl;

    private JPanel displayPanel;
    private JPanel titlePanel;
    private myGame playPanel;
    private JPanel settPanel;
    private JPanel diffPanel;
    private JPanel gameOver;
    private JLabel titleLabel;
    private JLabel settLabel;
    private JLabel diffLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel gameLabel;
    private JButton playButton;
    private JButton settButton;
    private JButton diffButton;
    private JButton quitButton;
    private JButton settbackButton;
    private ButtonGroup difGroup;
    private JToggleButton easyButton;
    private JToggleButton mediumButton;
    private JToggleButton hardButton;
    private JButton difbackButton;
    private JPanel pausePanel;
    private JLabel pauseLabel;
    private JButton unpauseButton;
    private JButton mainmenuButton;
    private JButton gomenuButton;

    private JButton gameoverButton;
    private boolean gameovertest;

    private GridBagConstraints gbc;
    public int unpause = 0;
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
        settPanel = new mySettings();
        settPanel.setLayout(new GridBagLayout());


        // Initialize Difficulty JPanel class
        //make one later
        diffPanel = new mySettings();
        diffPanel.setLayout(new GridBagLayout());

        // Initialize Pause JPanel class
        pausePanel = new myPause(this);
        pausePanel.setLayout(new GridBagLayout());

        // Initialize Play JPanel class
        playPanel = new myGame(dl, this, displayPanel, pausePanel);

        // Initialize GameOver
        gameOver = new myGameOver();
        gameOver.setLayout(null);

        // Initialize labels for each JPanel
        titleLabel = new JLabel("Hidden Squirrel: Peanuts and Acorns");
        titleLabel.setFont(titleText);
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setOpaque(true);
        titleLabel.setVerticalAlignment(JLabel.TOP);

        settLabel = new JLabel("Settings");
        settLabel.setFont(headerText);
        diffLabel = new JLabel("Difficulty");
        diffLabel.setFont(headerText);
        pauseLabel = new JLabel("PAUSED");
        pauseLabel.setFont(titleText);

        // Adding labels onto the panels
        titlePanel.add(titleLabel);
        settPanel.add(settLabel);
        diffPanel.add(diffLabel);
        pausePanel.add(pauseLabel);

        // Adding the cardPanel into layout, constraints associates panel
        displayPanel.add(titlePanel, "1");
        displayPanel.add(playPanel, "2");
        displayPanel.add(settPanel, "3");
        displayPanel.add(diffPanel, "4");
        displayPanel.add(pausePanel, "5");
        displayPanel.add(gameOver, "6");

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
                gameObjectData = new ObjectData(dif);
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

        //-----------------------------------Settings----------------------------------------------------------
        settbackButton = new JButton("Back");
        gbc.insets = new Insets(200,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 200;
        gbc.ipady = 50;
        settbackButton.setFocusable(false);
        settPanel.add(settbackButton,gbc);
        settbackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // go back to title panel
                dl.show(displayPanel, "1");

                // current panel is difficulty Panel
                currentCard = 1;
            }
        });
        //-----------------------------------------------------------------------------------------------------

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

        //--------------------------------------------PAUSE----------------------------------------------------
        unpauseButton = new JButton("Resume");
        gbc.gridx = 0;
        gbc.gridy = 2;
        unpauseButton.setFocusable(false);
        pausePanel.add(unpauseButton, gbc);

        mainmenuButton = new JButton("Main Menu");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainmenuButton.setFocusable(false);
        pausePanel.add(mainmenuButton, gbc);

        gameoverButton = new JButton("Test game over");
        gbc.gridx=0;
        gbc.gridy=6;
        gameoverButton.setFocusable(false);
        pausePanel.add(gameoverButton, gbc);

        // add unpause Button ActionListener
        unpauseButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //Change difficulty to easy
                System.out.println("Unpause");
                unpause = 0;
                kh.escape = false;
                // Out of pause
                // show associated play panel
                dl.show(displayPanel, "2");

                // current panel is play Panel
                currentCard = 2;
                playPanel.setFocusable(true);
                playPanel.requestFocus();
            }
        });

        // add main menu Button ActionListener
        mainmenuButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                playPanel.goMain = 1;
                unpause = 0;
                kh.escape = false;
                //Go back to main menu
                System.out.println("Going Back");

                // show associated difficulty panel
                dl.show(displayPanel, "1");

                // current panel is difficulty Panel
                currentCard = 1;

            }
        });

        // add main menu Button ActionListener
        gameoverButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                playPanel.goMain = 1;
                gameovertest = true;
                unpause = 0;
                kh.escape = false;
                //Go back to main menu
                System.out.println("Game is over");

            }
        });

        //-----------------------------------------------------------------------------------------------------

        //-----------------------------------GAME OVER---------------------------------------------------------
        gameLabel = new JLabel("GAME OVER");
        gameLabel.setFont(titleText);
        gameLabel.setBounds(600, 0, 400, 100);
        timeLabel = new JLabel();
        timeLabel.setFont(headerText);
        timeLabel.setBounds(700, 100, 800, 100);
        scoreLabel = new JLabel();
        scoreLabel.setFont(headerText);
        scoreLabel.setBounds(700, 200, 800, 100);
        gameOver.add(gameLabel);
        gameOver.add(timeLabel);
        gameOver.add(scoreLabel);

        gomenuButton = new JButton("Main Menu");
        gomenuButton.setFocusable(false);
        gomenuButton.setBounds(650, 400, 200, 100);
        // Game over screen, add menu button
        gameOver.add(gomenuButton);

        gomenuButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                playPanel.goMain = 1;
                unpause = 0;
                kh.escape = false;
                //Go back to main menu
                System.out.println("Going Back");

                // show associated difficulty panel
                dl.show(displayPanel, "1");

                // current panel is difficulty Panel
                currentCard = 1;

            }
        });
        //------------------------------------------------------------------------------------------------------

        // used to get content pane
        getContentPane().add(displayPanel, BorderLayout.NORTH);
    }

    public void startThread(){
        gameThread = new Thread(this);   //   When thread is created, use current object's run method
        gameThread.start();
    }

    @Override
    public void run() {   //   When starting thread, have thread use this run method
        playPanel.goMain = 0;
        gameovertest = false;
        timer = 0;
        Objects[][] boardMap = board.getBoardData();
        for(int col = 0; col < 25; col++){
            for(int row = 0; row < 15; row++){
                if(boardMap[col][row] == Objects.HERO){
                    playPanel.updaterows = row;
                    playPanel.updatecolumns = col;
                }
            }
        }

        while ( playPanel.goMain == 0 ) {
            try {
                if(unpause == 0) {
                    playPanel.updates();
                }
                if( (playPanel.goMain == 0) && (unpause == 0) ) {
                    playPanel.repaint();
                }
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Out of the game");
        //reset the game
        playPanel.reset();

        if(gameovertest) {
            //Show game over
            dl.show(displayPanel, "6");
            currentCard = 6;

            timeLabel.setText("Time : " + timer / 1000);
            scoreLabel.setText("Score : " + gameObjectData.getHero().getScore());
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
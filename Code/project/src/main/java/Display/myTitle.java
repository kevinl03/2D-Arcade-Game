package Display;

import Game.ObjectData;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates this JPanel to represent the Title screen
 */
public class myTitle extends JPanel {

    private BufferedImage title_png;
    private BufferedImage title_squirrel;
    private Font titleText;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton settButton;
    private JButton diffButton;
    private JButton quitButton;
    private GridBagConstraints gbc;
    private Image buttonIcon;


    /**
     * Constructor creates the title screen.
     * This constructor makes JButtons for Play, Settings,
     * Difficulty, and Quit.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myTitle(DisplayLayout dl, CardLayout cl){
        try {
            title_png = ImageIO.read(getClass().getResource("/title_pic.png"));
            title_squirrel = ImageIO.read(getClass().getResource("/title_squirrel.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.cyan);
        titleLabel = new JLabel("Hidden Squirrel: Peanuts and Acorns");
        titleText = new Font("Times New Roman", Font.BOLD, 50);
        titleLabel.setFont(titleText);
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setOpaque(true);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        this.add(titleLabel);

        gbc = new GridBagConstraints();
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
        this.add(playButton, gbc);

        settButton = new JButton(" Settings ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        settButton.setFocusable(false);
        this.add(settButton, gbc);

        diffButton = new JButton("Difficulty");
        gbc.gridx = 0;
        gbc.gridy = 3;
        diffButton.setFocusable(false);
        this.add(diffButton, gbc);

        quitButton = new JButton("   Quit   ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        quitButton.setFocusable(false);
        this.add(quitButton, gbc);

        playButton.addActionListener(new ActionListener()
        {
            /**
             * When users press Play button, initiates new board data and goes to the Game screen.
             * This method sets a new randomized board of characters to a ObjectData variable in
             * DisplayLayout, then uses the CardLayout show method to change current Title JPanel
             * to Game JPanel. It sets the currentCard variable in DisplayLayout object to match the
             * Game JPanel's reference number. Then it starts the game initialization and game loop.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0) {
                dl.sound.playClick();
                System.out.println("creating object data with color " + dl.heroColor.toString());
                dl.gameObjectData = new ObjectData(dl.dif, dl.heroColor);
                dl.board = dl.gameObjectData.getBoard();

                // show associated play panel
                cl.show(dl.displayPanel, "2");
                // current panel is play Panel
                dl.currentCard = 2;
                dl.playPanel.setFocusable(true);
                dl.playPanel.requestFocus();
                dl.startThread();
            }
        });

        settButton.addActionListener(new ActionListener()
        {
            /**
             * When user presses Settings button, goes to Settings screen.
             * This method uses the CardLayout show method to change
             * current Title JPanel to Settings JPanel, and sets the
             * currentCard variable in DisplayLayout object to match the
             * Settings JPanel's reference number.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0)
            {
                // show associated setting panel
                cl.show(dl.displayPanel, "3");
                dl.sound.playClick();
                // current panel is settings Panel
                dl.currentCard = 3;
            }
        });

        diffButton.addActionListener(new ActionListener() {
            /**
             * When user presses Difficulty button, goes to Difficulty screen.
             * This method uses the CardLayout show method to change
             * current Title JPanel to Difficulty JPanel, and sets the
             * currentCard variable in DisplayLayout object to match the
             * Difficulty JPanel's reference number.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0) {
                // show associated difficulty panel
                cl.show(dl.displayPanel, "4");
                dl.sound.playClick();

                // current panel is difficulty Panel
                dl.currentCard = 4;
            }
        });

        quitButton.addActionListener(new ActionListener() {
            /**
             * When user presses Quit button, exits the executable.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0) {
                dl.sound.playClick();
                System.exit(0);
            }
        });

    }

    /**
     * Draws a background image and a squirrel image to this JPanel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(title_png, 0, 0, 1500, 960, null);
        g.drawImage(title_squirrel, 400, 400, 293, 400, null);
    }
}


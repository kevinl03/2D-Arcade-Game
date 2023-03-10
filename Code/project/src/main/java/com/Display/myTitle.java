package com.Display;

import com.Game.ObjectData;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Creates this JPanel to represent the Title screen
 */
public class myTitle extends JPanel {

    private final BufferedImage titlePng;
    private final BufferedImage titleSquirrel;
    private final JButton playButton;
    private final DisplayLayout displayLayout;


    /**
     * Constructor creates the title screen.
     * This constructor makes JButtons for Play, Settings,
     * Difficulty, and Quit.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myTitle(DisplayLayout dl, CardLayout cl){
        try {
            titlePng = ImageIO.read(Objects.requireNonNull(getClass().getResource("/title_pic.png")));
            titleSquirrel = ImageIO.read(Objects.requireNonNull(getClass().getResource("/title_squirrel.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.cyan);
        JLabel titleLabel = new JLabel("");
        titleLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/gameName.png"))));
        Font titleText = new Font("Times New Roman", Font.BOLD, 50);
        titleLabel.setFont(titleText);
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setOpaque(false);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        this.add(titleLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        // Initialize JButton objects and add to title Panel
        ImageIcon playImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/play.png")));
        playButton = new JButton("",playImage);
        gbc.insets = new Insets(50,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 200;
        gbc.ipady = dl.displayheight/100;
        playButton.setFocusable(false);
        playButton.setBorderPainted(false);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        this.add(playButton, gbc);

        ImageIcon settingImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/settings.png")));
        JButton settButton = new JButton("", settingImage);
        gbc.gridx = 0;
        gbc.gridy = 2;
        settButton.setFocusable(false);
        settButton.setBorderPainted(false);
        settButton.setOpaque(false);
        settButton.setContentAreaFilled(false);
        this.add(settButton, gbc);

        ImageIcon diffImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/dif.png")));
        JButton diffButton = new JButton("", diffImage);
        gbc.gridx = 0;
        gbc.gridy = 3;
        diffButton.setFocusable(false);
        diffButton.setBorderPainted(false);
        diffButton.setOpaque(false);
        diffButton.setContentAreaFilled(false);
        this.add(diffButton, gbc);

        ImageIcon quitImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/quit.png")));
        JButton quitButton = new JButton("", quitImage);
        gbc.gridx = 0;
        gbc.gridy = 4;
        quitButton.setFocusable(false);
        quitButton.setBorderPainted(false);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        this.add(quitButton, gbc);

        playButton.addActionListener(new ActionListener()
        {
            /**
             * When users press Play button, initiates new board data and goes to the com.Game screen.
             * This method sets a new randomized board of characters to a ObjectData variable in
             * DisplayLayout, then uses the CardLayout show method to change current Title JPanel
             * to com.Game JPanel. It sets the currentCard variable in DisplayLayout object to match the
             * com.Game JPanel's reference number. Then it starts the game initialization and game loop.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0) {
                dl.sound.playClick();
                dl.gameObjectData = new ObjectData(dl.dif, dl.heroColor);
                dl.board = dl.gameObjectData.getBoard();

                // show associated play panel
                cl.show(dl.displayPanel, "Play");
                // current panel is play Panel
                dl.currentCard = 2;
                dl.playPanel.setFocusable(true);
                dl.playPanel.requestFocus();
                dl.playPanel.goMain = 0;
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
                cl.show(dl.displayPanel, "Settings");
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
                cl.show(dl.displayPanel, "Difficulty");
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

        displayLayout = dl;

    }

    /**
     * Draws a background image and a squirrel image to this JPanel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(titlePng, 0, 0, displayLayout.displaywidth, displayLayout.displayheight, null);
        g.drawImage(titleSquirrel, displayLayout.displaywidth/4, displayLayout.displayheight/2, displayLayout.displaywidth/5, displayLayout.displayheight/3, null);
    }

    public JButton getplayButton() {return playButton;}
}


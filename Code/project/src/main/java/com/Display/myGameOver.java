package com.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Creates this JPanel to represent the com.Game Over screen.
 */
public class myGameOver extends JPanel {
    private final JLabel timeLabel;
    private final JLabel scoreLabel;

    private final BufferedImage winPng;
    private final BufferedImage testimagePng;
    private final DisplayLayout dl;

    /**
     * Constructor creates the com.Game Over screen.
     * This constructor makes JLabels to show the com.Game Over text,
     * time, and score, and a button to go back to the Title screen.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myGameOver(DisplayLayout dl, CardLayout cl){
        this.dl = dl;
        Font titleText = new Font("Times New Roman", Font.BOLD, (dl.displayheight / 15));
        Font headerText = new Font("Times New Roman", Font.BOLD, (dl.displayheight / 30));
        this.setLayout(null);

        JLabel gameLabel = new JLabel("GAME OVER");
        gameLabel.setFont(titleText);
        gameLabel.setBounds((int)(dl.displaywidth*0.38), dl.displayheight/3, dl.displaywidth/3, dl.displayheight/10);
        timeLabel = new JLabel();
        timeLabel.setFont(headerText);
        timeLabel.setBounds((int)(dl.displaywidth*0.45), (int)(dl.displayheight*0.4), dl.displaywidth/2, dl.displayheight/10);
        scoreLabel = new JLabel();
        scoreLabel.setFont(headerText);
        scoreLabel.setBounds((int)(dl.displaywidth*0.45), (int)(dl.displayheight*0.5), dl.displaywidth/2, dl.displayheight/10);
        this.add(gameLabel);
        this.add(timeLabel);
        this.add(scoreLabel);

        //background
        try {
            winPng = ImageIO.read(Objects.requireNonNull(getClass().getResource("/win.png")));
            testimagePng = ImageIO.read(Objects.requireNonNull(getClass().getResource("/settingB.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon mainImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main.png")));
        JButton gomenuButton = new JButton("", mainImage);
        gomenuButton.setFocusable(false);
        gomenuButton.setBounds((int)(dl.displaywidth*0.37), (int)(dl.displayheight*0.6), dl.displaywidth/4, dl.displayheight/10);
        gomenuButton.setBorderPainted(false);
        gomenuButton.setOpaque(false);
        gomenuButton.setContentAreaFilled(false);
        // com.Game over screen, add menu button
        this.add(gomenuButton);

        gomenuButton.addActionListener(new ActionListener()
        {
            /**
             * When user presses com.Main Menu button, goes to Title screen.
             * This method uses the CardLayout show method to change
             * current com.Game Over JPanel to Title JPanel, and sets the
             * currentCard variable in DisplayLayout object to match the
             * Title JPanel's reference number.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
                //Go back to main menu
                // show associated difficulty panel
                cl.show(dl.displayPanel, "Title");

                // current panel is difficulty Panel
                dl.currentCard = 1;

            }
        });
    }

    /**
     * Draws a background image of board onto this JPanel.
     * This Override method draws from the top-left corner
     * of the JPanel the PNG image starting from its top-left corner.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (winPng != null) {
            g.drawImage(testimagePng, 0, 0, dl.displaywidth, dl.displayheight, null);
            g.drawImage(winPng, 0, 0, dl.displaywidth, dl.displayheight, null);
        }
    }

    /**
     * Sets the text of these time and score JLabels
     * This method is called when game is won or lost and sets
     * the time and score of the JLabels in com.Game Over JPanel
     * as the same as the current one from the gameplay.
     * @param time the duration of the gameplay
     * @param score the points obtained during gameplay
     */
    public void setValues(int time, int score) {
        timeLabel.setText("Time : " + time);
        scoreLabel.setText("Score : " + score);
    }

}

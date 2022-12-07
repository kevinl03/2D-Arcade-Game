package com.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates this JPanel to represent the com.Game Won screen.
 */
public class myGameWon extends JPanel {
    private JLabel gameLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JButton gomenuButton;
    private Font titleText;
    private Font headerText;
    private BufferedImage winPng;
    private BufferedImage testimagePng;
    private DisplayLayout dl;

    /**
     * Constructor creates this com.Game Won screen.
     * This constructor makes JLabels to show the Congratulations text,
     * time, and score, and a button to go back to the Title screen.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myGameWon(DisplayLayout dl, CardLayout cl){
        this.dl = dl;
        titleText = new Font("Times New Roman", Font.BOLD, (dl.displayheight/15));
        headerText = new Font("Times New Roman", Font.BOLD, (dl.displayheight/30));
        this.setLayout(null);
        gameLabel = new JLabel("CONGRATULATIONS");
        gameLabel.setFont(titleText);
        gameLabel.setBounds((int)(dl.displaywidth*0.3), dl.displayheight/3, dl.displaywidth/2, dl.displayheight/10);
        timeLabel = new JLabel();
        timeLabel.setFont(headerText);
        timeLabel.setBounds((int)(dl.displaywidth*0.45), (int)(dl.displayheight*0.4), dl.displaywidth/2, dl.displayheight/10);
        scoreLabel = new JLabel();
        scoreLabel.setFont(headerText);
        scoreLabel.setBounds((int)(dl.displaywidth*0.45), (int)(dl.displayheight*0.5), dl.displaywidth/2, dl.displayheight/10);
        this.add(gameLabel);
        this.add(timeLabel);
        this.add(scoreLabel);

        try {
            winPng = ImageIO.read(getClass().getResource("/win.png"));
            testimagePng = ImageIO.read(getClass().getResource("/settingB.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        ImageIcon mainImage = new ImageIcon(getClass().getResource("/main.png"));
        gomenuButton = new JButton("",mainImage);
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
                cl.show(dl.displayPanel, "1");

                // current panel is difficulty Panel
                dl.currentCard = 1;

            }
        });
    }
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

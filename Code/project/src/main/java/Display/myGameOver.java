package Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates this JPanel to represent the Game Over screen.
 */
public class myGameOver extends JPanel {
    private Font titleText;
    private Font headerText;
    private JLabel gameLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JButton gomenuButton;

    private BufferedImage win_png;

    /**
     * Constructor creates the Game Over screen.
     * This constructor makes JLabels to show the Game Over text,
     * time, and score, and a button to go back to the Title screen.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myGameOver(DisplayLayout dl, CardLayout cl){
        titleText = new Font("Times New Roman", Font.BOLD, 50);
        headerText = new Font("Times New Roman", Font.BOLD, 30);
        this.setLayout(null);

        gameLabel = new JLabel("GAME OVER");
        gameLabel.setFont(titleText);
        gameLabel.setBounds(600, 290, 500, 100);
        timeLabel = new JLabel();
        timeLabel.setFont(headerText);
        timeLabel.setBounds(700, 370, 800, 100);
        scoreLabel = new JLabel();
        scoreLabel.setFont(headerText);
        scoreLabel.setBounds(700, 450, 800, 100);
        this.add(gameLabel);
        this.add(timeLabel);
        this.add(scoreLabel);

        //background
        try {
            win_png = ImageIO.read(getClass().getResource("/win.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        gomenuButton = new JButton("Main Menu");
        gomenuButton.setFocusable(false);
        gomenuButton.setBounds(650, 600, 200, 100);
        Color greenclr = new Color(114, 209, 127);
        gomenuButton.setBackground(greenclr);
        gomenuButton.setOpaque(true);
        gomenuButton.setBorderPainted(false);
        // Game over screen, add menu button
        this.add(gomenuButton);

        gomenuButton.addActionListener(new ActionListener()
        {
            /**
             * When user presses Main Menu button, goes to Title screen.
             * This method uses the CardLayout show method to change
             * current Game Over JPanel to Title JPanel, and sets the
             * currentCard variable in DisplayLayout object to match the
             * Title JPanel's reference number.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
                //Go back to main menu
                System.out.println("Going Back");

                // show associated difficulty panel
                cl.show(dl.displayPanel, "1");

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
        if (win_png != null) {
            g.drawImage(win_png, 0, 0, 1500, 960, null);
        }
    }

    /**
     * Sets the text of these time and score JLabels
     * This method is called when game is won or lost and sets
     * the time and score of the JLabels in Game Over JPanel
     * as the same as the current one from the gameplay.
     * @param time the duration of the gameplay
     * @param score the points obtained during gameplay
     */
    public void setValues(int time, int score) {
        timeLabel.setText("Time : " + time);
        scoreLabel.setText("Score : " + score);
    }

}

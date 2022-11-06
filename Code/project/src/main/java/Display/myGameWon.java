package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates this JPanel to represent the Game Won screen.
 */
public class myGameWon extends JPanel {
    private JLabel gameLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JButton gomenuButton;
    private Font titleText;
    private Font headerText;

    /**
     * Constructor creates this Game Won screen.
     * This constructor makes JLabels to show the Congratulations text,
     * time, and score, and a button to go back to the Title screen.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myGameWon(DisplayLayout dl, CardLayout cl){
        titleText = new Font("Times New Roman", Font.BOLD, 50);
        headerText = new Font("Times New Roman", Font.BOLD, 30);
        this.setLayout(null);
        gameLabel = new JLabel("CONGRATULATIONS");
        gameLabel.setFont(titleText);
        gameLabel.setBounds(500, 0, 550, 100);
        timeLabel = new JLabel();
        timeLabel.setFont(headerText);
        timeLabel.setBounds(700, 100, 800, 100);
        scoreLabel = new JLabel();
        scoreLabel.setFont(headerText);
        scoreLabel.setBounds(700, 200, 800, 100);
        this.add(gameLabel);
        this.add(timeLabel);
        this.add(scoreLabel);

        gomenuButton = new JButton("Main Menu");
        gomenuButton.setFocusable(false);
        gomenuButton.setBounds(650, 400, 200, 100);
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
                // show associated difficulty panel
                cl.show(dl.displayPanel, "1");

                // current panel is difficulty Panel
                dl.currentCard = 1;

            }
        });
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

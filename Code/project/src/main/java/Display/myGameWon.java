package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myGameWon extends JPanel {
    private JLabel gameLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JButton gomenuButton;
    private Font titleText;
    private Font headerText;

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
            public void actionPerformed(ActionEvent arg0)
            {
                dl.playPanel.goMain = 1;
                dl.pause = 0;
                dl.kh.escape = false;
                //Go back to main menu
                System.out.println("Going Back");

                // show associated difficulty panel
                cl.show(dl.displayPanel, "1");

                // current panel is difficulty Panel
                dl.currentCard = 1;

            }
        });
    }

    public void setValues(int time, int score) {
        timeLabel.setText("Time : " + time);
        scoreLabel.setText("Score : " + score);
    }

}

package Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class myGameOver extends JPanel {
    private Font titleText;
    private Font headerText;
    private JLabel gameLabel;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JButton gomenuButton;

    private BufferedImage win_png;


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
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (win_png != null) {
            g.drawImage(win_png, 0, 0, 1500, 960, null);
        }
    }

    public void setValues(int time, int score) {
        timeLabel.setText("Time : " + time);
        scoreLabel.setText("Score : " + score);
    }

}

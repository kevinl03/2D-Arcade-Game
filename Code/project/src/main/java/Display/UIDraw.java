package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIDraw{
    StartDisplay sd;
    Graphics2D g2;
    Font titlefont;
    Font optionfont;
    private int centerx = 11*60;
    private int centery = 7*60;

    public int select = 0;

    JPanel buttonPanel;
    JButton backButton;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;

    public UIDraw(StartDisplay sd) {
        this.sd = sd;
        titlefont = new Font("Times New Roman", Font.BOLD, 50);
        optionfont = new Font("Times New Roman", Font.BOLD, 30);
    }

    public void draw(Graphics2D g2) {
        // Title
        this.g2 = g2;
        if (sd.screens == sd.title) {
            drawTitle();
        }

        // Settings
        if(sd.screens == sd.settings) {
            drawSetting();
        }

        // Difficulty
        if(sd.screens == sd.difficulty){
            drawDifficulty();
        }

        // Gaming
        if (sd.screens == sd.gaming) {
            drawGaming();
        }
    }

    public void drawTitle(){
        g2.setFont(titlefont);
        g2.setColor(Color.black);
        g2.drawString("Hidden Squirrel: Peanuts and Acorns", centerx-300, centery-300);

        g2.setFont(optionfont);
        if(select == 0) {
            g2.setColor(Color.PINK);
            g2.drawString("Play", centerx, centery);
            g2.setColor(Color.black);
            g2.drawString("Settings", centerx, centery + 120);
            g2.drawString("Difficulty", centerx, centery + 240);
        } else if (select == 1) {
            g2.drawString("Play", centerx, centery);
            g2.setColor(Color.PINK);
            g2.drawString("Settings", centerx, centery + 120);
            g2.setColor(Color.black);
            g2.drawString("Difficulty", centerx, centery + 240);
        } else {
            g2.drawString("Play", centerx, centery);
            g2.drawString("Settings", centerx, centery + 120);
            g2.setColor(Color.PINK);
            g2.drawString("Difficulty", centerx, centery + 240);
        }
    }

    public void drawSetting(){
    }

    public void drawDifficulty(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runnable backThread = new Runnable(){
                    public void run() {
                        System.out.println("Back Clicked");
                        sd.screens = sd.title;
                        sd.freeze = 0;
                        buttonPanel.setVisible(false);
                        //buttonPanel.remove(backButton);
                        //buttonPanel.remove(easyButton);
                    }
                };
                SwingUtilities.invokeLater(backThread);
            }
        });
        easyButton = new JButton("Easy");
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runnable backThread = new Runnable(){
                    public void run() {
                        sd.challenge = 0;
                        System.out.println(sd.challenge);
                    }
                };
                SwingUtilities.invokeLater(backThread);
            }
        });
        mediumButton = new JButton("Medium");
        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runnable backThread = new Runnable(){
                    public void run() {
                        sd.challenge = 1;
                        System.out.println(sd.challenge);
                    }
                };
                SwingUtilities.invokeLater(backThread);
            }
        });
        hardButton = new JButton("Hard");
        hardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runnable backThread = new Runnable(){
                    public void run() {
                        sd.challenge = 2;
                        System.out.println(sd.challenge);
                    }
                };
                SwingUtilities.invokeLater(backThread);
            }
        });
        easyButton.setBounds(12*60,1*60,100,60);
        mediumButton.setBounds(12*60,3*60,100,60);
        hardButton.setBounds(12*60,5*60,100,60);
        backButton.setBounds(12*60,10*60,100,60);
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        buttonPanel.add(backButton);
        sd.Startup.add(buttonPanel);
        sd.Startup.setVisible(true);
    }

    public void drawGaming(){
        g2.drawImage(sd.squirrel_png, sd.updatecolumns * 60, sd.updaterows * 60, sd.pixelsize, sd.pixelsize, null);
    }

}

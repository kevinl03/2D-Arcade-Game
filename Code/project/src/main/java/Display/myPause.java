package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class myPause extends JPanel{


private DisplayLayout dl;
private CardLayout cl;
private KeyListener kh;
private JButton resumeButton;
private JButton mainmenuButton;
private JButton gameoverButton;
private JLabel pauseLabel;
Font titleText;
private GridBagConstraints gbc;

    public myPause(DisplayLayout dl, CardLayout cl){
        this.dl = dl;
        this.cl = cl;
        this.kh = dl.kh;
        addKeyListener(kh);

        titleText = new Font("Times New Roman", Font.BOLD, 50);
        this.setLayout(new GridBagLayout());

        pauseLabel = new JLabel("PAUSED");
        pauseLabel.setFont(titleText);
        this.add(pauseLabel);

        gbc = new GridBagConstraints();
        resumeButton = new JButton("  Resume ");
        gbc.insets = new Insets(50,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 200;
        gbc.ipady = 50;
        resumeButton.setFocusable(false);
        this.add(resumeButton, gbc);

        mainmenuButton = new JButton("Main Menu");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainmenuButton.setFocusable(false);
        this.add(mainmenuButton, gbc);

        gameoverButton = new JButton("Test game over");
        gbc.gridx=0;
        gbc.gridy=6;
        gameoverButton.setFocusable(false);
        this.add(gameoverButton, gbc);

        // add unpause Button ActionListener
        resumeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //Resume
                dl.sound.playClick();
                System.out.println("Unpause");
                dl.pause = 0;
                dl.kh.escape = false;
                // Out of pause
                // show associated play panel
                cl.show(dl.displayPanel, "2");

                // current panel is play Panel
                dl.currentCard = 2;
                dl.playPanel.setFocusable(true);
                dl.playPanel.requestFocus();
            }
        });

        // add main menu Button ActionListener
        mainmenuButton.addActionListener(new ActionListener()
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

        // add main menu Button ActionListener
        gameoverButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
                dl.playPanel.goMain = 1;
                dl.gameWonTest= true;
                dl.pause = 0;
                dl.kh.escape = false;
                //Go back to main menu
                System.out.println("Game is over");
            }
        });
    }

}

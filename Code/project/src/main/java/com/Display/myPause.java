package com.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates this JPanel to represent the Pause screen.
 * Users are able to resume or go back to com.Main Menu.
 */
public class myPause extends JPanel{
private DisplayLayout dl;
private CardLayout cl;
private KeyListener kh;
private JButton resumeButton;
private JButton mainmenuButton;
// private JButton gameoverButton;
private JLabel pauseLabel;
private Font headerText;


private BufferedImage pausePng;
Font titleText;
private GridBagConstraints gbc;

    /**
     * Constructor creates the pause screen.
     * This constructor makes JButtons for Resume and com.Main Menu.
     * Users press the com.Main Menu button to return to the Title screen
     * and press the Resume button to continue the game.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
    public myPause(DisplayLayout dl, CardLayout cl){
        this.dl = dl;
        this.cl = cl;
        this.kh = dl.kh;
        addKeyListener(kh);

        gbc = new GridBagConstraints();
        gbc.anchor = gbc.CENTER;

        //background
        try {
            pausePng = ImageIO.read(getClass().getResource("/pause_background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        titleText = new Font("Times New Roman", Font.BOLD, (dl.displayheight/15));
        //headerText = new Font("Times New Roman", Font.BOLD, (dl.displayheight/30));
        this.setLayout(null);

        pauseLabel = new JLabel("PAUSED");
        pauseLabel.setFont(titleText);
        pauseLabel.setBounds((int)(dl.displaywidth*0.4), (int) (dl.displayheight*0.38), dl.displaywidth/3, dl.displayheight/10);
        gbc.insets = new Insets(0,0,0,0);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
        this.add(pauseLabel);

        //gbc = new GridBagConstraints();
        ImageIcon resumeImage = new ImageIcon(getClass().getResource("/resume.png"));
        resumeButton = new JButton("",resumeImage);
        resumeButton.setBounds((int)(dl.displaywidth*0.22), (int)(dl.displayheight*0.6), dl.displaywidth/3, dl.displayheight/10);
        //gbc.insets = new Insets(50,0,0,0);
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.ipadx = 200;
//        gbc.ipady = 50;
        resumeButton.setFocusable(false);
        resumeButton.setBorderPainted(false);
        resumeButton.setOpaque(false);
        resumeButton.setContentAreaFilled(false);
        this.add(resumeButton);


        ImageIcon mainImage = new ImageIcon(getClass().getResource("/main.png"));
        mainmenuButton = new JButton("",mainImage);
        mainmenuButton.setBounds((int)(dl.displaywidth*0.48), (int)(dl.displayheight*0.6), dl.displaywidth/3, dl.displayheight/10);
        mainmenuButton.setFocusable(false);
        //mainmenuButton.setBounds(610, 570, 300, 100);
        mainmenuButton.setBorderPainted(false);
        mainmenuButton.setOpaque(false);
        mainmenuButton.setContentAreaFilled(false);
        //gbc.insets = new Insets(100,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 200;
        gbc.ipady = 50;
        this.add(mainmenuButton,gbc);


        /*
        gameoverButton = new JButton("Test game over");
        gbc.gridx=0;
        gbc.gridy=6;
        gameoverButton.setFocusable(false);
        this.add(gameoverButton, gbc);
         */

        resumeButton.addActionListener(new ActionListener()
        {
            /**
             * When user presses Resume button, goes to com.Game screen.
             * This method uses the CardLayout show method to change
             * current Pause JPanel to com.Game JPanel, and sets the
             * currentCard variable in DisplayLayout object to match the
             * com.Game JPanel's reference number. The pause variable of the
             * DisplayLayout object is set to 0 to resume updating and
             * repainting the game.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0)
            {
                //Resume
                dl.sound.playClick();
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
            /**
             * When user presses com.Main Menu button, goes to Title screen.
             * This method uses the CardLayout show method to change
             * current Pause JPanel to Title JPanel, and sets the
             * currentCard variable in DisplayLayout object to match the
             * Title JPanel's reference number.
             * @param arg0 the event to be processed
             */
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
                dl.playPanel.goMain = 1;
                dl.pause = 0;
                dl.kh.escape = false;
                //Go back to main menu
                // show associated difficulty panel
                cl.show(dl.displayPanel, "1");

                // current panel is difficulty Panel
                dl.currentCard = 1;

            }
        });
        /*
        gameoverButton.addActionListener(new ActionListener()
        {
            /**
             * When user presses Test com.Game Over button, goes to com.Game Over screen.
             * This method uses the CardLayout show method to change current Pause
             * JPanel to com.Game Over JPanel, and sets the currentCard variable in
             * DisplayLayout object to match the com.Game Over JPanel's reference number.
             * This is used to manually test playing to game over.
             * @param arg0 the event to be processed
             */
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pausePng != null) {
            g.drawImage(pausePng, 0, 0, dl.displaywidth, dl.displayheight, null);
        }
    }

}

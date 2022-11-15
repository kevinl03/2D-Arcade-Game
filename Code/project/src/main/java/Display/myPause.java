package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Creates this JPanel to represent the Pause screen.
 * Users are able to resume or go back to Main Menu.
 */
public class myPause extends JPanel{
private DisplayLayout dl;
private CardLayout cl;
private KeyListener kh;
private JButton resumeButton;
private JButton mainmenuButton;
// private JButton gameoverButton;
private JLabel pauseLabel;
Font titleText;
private GridBagConstraints gbc;

    /**
     * Constructor creates the pause screen.
     * This constructor makes JButtons for Resume and Main Menu.
     * Users press the Main Menu button to return to the Title screen
     * and press the Resume button to continue the game.
     * @param dl the JFrame object used to access the different JPanels
     * @param cl the CardLayout object to use its methods
     */
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
             * When user presses Resume button, goes to Game screen.
             * This method uses the CardLayout show method to change
             * current Pause JPanel to Game JPanel, and sets the
             * currentCard variable in DisplayLayout object to match the
             * Game JPanel's reference number. The pause variable of the
             * DisplayLayout object is set to 0 to resume updating and
             * repainting the game.
             * @param arg0 the event to be processed
             */
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
            /**
             * When user presses Main Menu button, goes to Title screen.
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
                System.out.println("Going Back");

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
             * When user presses Test Game Over button, goes to Game Over screen.
             * This method uses the CardLayout show method to change current Pause
             * JPanel to Game Over JPanel, and sets the currentCard variable in
             * DisplayLayout object to match the Game Over JPanel's reference number.
             * This is used to manually test playing to game over.
             * @param arg0 the event to be processed
             */
        /*
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
        });*/
    }

}

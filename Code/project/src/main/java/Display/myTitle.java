package Display;

import Board.BoardData;
import Game.ObjectData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class myTitle extends JPanel {

    private BufferedImage title_png;
    private BufferedImage title_squirrel;
    private Font titleText;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton settButton;
    private JButton diffButton;
    private JButton quitButton;
    private GridBagConstraints gbc;
    private Image buttonIcon;


    public myTitle(DisplayLayout dl, CardLayout cl){
        try {
            title_png = ImageIO.read(getClass().getResource("/title_pic.png"));
            title_squirrel = ImageIO.read(getClass().getResource("/title_squirrel.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.cyan);
        titleLabel = new JLabel("Hidden Squirrel: Peanuts and Acorns");
        titleText = new Font("Times New Roman", Font.BOLD, 50);
        titleLabel.setFont(titleText);
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setOpaque(true);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        this.add(titleLabel);

        gbc = new GridBagConstraints();
        // Initialize JButton objects and add to title Panel
        playButton = new JButton("   Play   ");
        try {
            buttonIcon = ImageIO.read(getClass().getResource("/acorn.png"));
            playButton.setIcon(new ImageIcon(buttonIcon));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gbc.insets = new Insets(100,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 200;
        gbc.ipady = 50;
        playButton.setFocusable(false);
        this.add(playButton, gbc);

        settButton = new JButton(" Settings ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        settButton.setFocusable(false);
        this.add(settButton, gbc);

        diffButton = new JButton("Difficulty");
        gbc.gridx = 0;
        gbc.gridy = 3;
        diffButton.setFocusable(false);
        this.add(diffButton, gbc);

        quitButton = new JButton("   Quit   ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        quitButton.setFocusable(false);
        this.add(quitButton, gbc);

        // add Play Button ActionListener
        playButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("creating object data with color " + dl.heroColor.toString());
                dl.gameObjectData = new ObjectData(dl.dif, dl.heroColor);
                dl.board = dl.gameObjectData.getBoard();

                // show associated play panel
                cl.show(dl.displayPanel, "2");
                // current panel is play Panel
                dl.currentCard = 2;
                dl.playPanel.setFocusable(true);
                dl.playPanel.requestFocus();
                dl.startThread();
            }
        });

        // add Settings Button ActionListener
        settButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                // show associated setting panel
                cl.show(dl.displayPanel, "3");

                // current panel is settings Panel
                dl.currentCard = 3;
            }
        });

        // add Difficulty Button ActionListener
        diffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // show associated difficulty panel
                cl.show(dl.displayPanel, "4");

                // current panel is difficulty Panel
                dl.currentCard = 4;
            }
        });

        // add Quit Button ActionListener
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(title_png, 0, 0, 1500, 960, null);
        g.drawImage(title_squirrel, 400, 400, 293, 400, null);
    }
}


package Display;

import Board.Difficulty;
import Entities.Enemy;
import Entities.Hero;
import Helpers.HeroColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class mySettings extends JPanel {
    private int pixelsize = 60;   //60x60 pixels
    private HashMap<HeroColor, BufferedImage> heroColorPngs;
    private BufferedImage leftButtonPng;
    private BufferedImage rightButtonPng;

    private HeroColor color;

    private JButton leftScroll;
    private JButton rightScroll;


    private BufferedImage testimage_png;

    private JLabel settLabel;

    private Font headerText;

    private ButtonGroup soundGroup;
    private JToggleButton muteButton;
    private JToggleButton unmuteButton;
    private GridBagConstraints gbc;
    private JButton settbackButton;
    private CardLayout cl;
    private DisplayLayout dl;


    private void getHeroColors(){
        try {
            heroColorPngs = new HashMap<>();
            for(HeroColor color: HeroColor.values()){
                BufferedImage img = ImageIO.read(getClass().getResource("/squirrels/Squirrel" + color.toString() + "East2.png"));
                heroColorPngs.put(color, img);
            }


            leftButtonPng = ImageIO.read(getClass().getResource("/scrollButtonLeft.png"));
            rightButtonPng = ImageIO.read(getClass().getResource("/scrollButtonRight.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getButtonPngs() {
        try {
            leftButtonPng = ImageIO.read(getClass().getResource("/scrollButtonLeft.png"));
            rightButtonPng = ImageIO.read(getClass().getResource("/scrollButtonRight.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public mySettings(DisplayLayout dl, CardLayout cl) {

        this.cl = cl;
        gbc = new GridBagConstraints();
        gbc.anchor = gbc.CENTER;

        // Setting up the settings panel
        setLayout(new GridBagLayout());
        settLabel = new JLabel("Settings");
        headerText = new Font("Times New Roman", Font.BOLD, 30);
        settLabel.setFont(headerText);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,450);
        this.add(settLabel,gbc);

        soundGroup = new ButtonGroup();
        muteButton = new JToggleButton(" Mute ");
        gbc.insets = new Insets(100,0,0,450);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 200;
        gbc.ipady = 50;
        muteButton.setFocusable(false);
        this.add(muteButton,gbc);

        unmuteButton = new JToggleButton("Unmute");
        gbc.gridx = 1;
        gbc.gridy = 3;
        unmuteButton.setFocusable(false);
        this.add(unmuteButton,gbc);


        settbackButton = new JButton(" Back ");
        gbc.insets = new Insets(50,0,0,450);
        gbc.gridx = 1;
        gbc.gridy = 4;
        settbackButton.setFocusable(false);
        this.add(settbackButton,gbc);

        settbackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // go back to title panel
                cl.show(dl.displayPanel, "1");
                dl.sound.playClick();
                // current panel is difficulty Panel
                dl.currentCard = 1;
            }
        });

        //toggle only mute or unmute
        soundGroup.add(muteButton);
        soundGroup.add(unmuteButton);

        //mute btn action
        muteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
                //disable the sound
                dl.sound.stopMusic();
            }
        });

        //unmute btn action
        unmuteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
                //enable the sound
                dl.sound.playMusic(0);
            }
        });

        try {
            getButtonPngs();
            getHeroColors();
            testimage_png = ImageIO.read(getClass().getResource("/testimage.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        color = dl.heroColor;

        leftScroll = iconButton(leftButtonPng, 40, 40);
        leftScroll.setFocusable(false);
        leftScroll.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
                dl.heroColor = dl.heroColor.next();
                color = dl.heroColor;
                repaint();
            }
        });
        gbc.insets = new Insets(150,300,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        this.add(leftScroll, gbc);

        rightScroll = iconButton(rightButtonPng, 40, 40);
        rightScroll.setFocusable(false);
        rightScroll.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                dl.sound.playClick();
                dl.heroColor = dl.heroColor.prev();
                color = dl.heroColor;

                repaint();

            }
        });
        gbc.insets = new Insets(150,0,0,100);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(rightScroll, gbc);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (testimage_png != null) {
            g.drawImage(testimage_png, 0, 0, 1500, 960, null);
        }
        skinSelection(g);
    }

    private JButton iconButton(BufferedImage image, int width, int height) {
        Image scaledImage = image.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon iconImage = new ImageIcon(scaledImage);
        JButton b = new JButton(iconImage);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        return b;
    }


    private void skinSelection(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;   //   Draws shapes
        BufferedImage hero = heroColorPngs.get(color);
        g2.drawImage(hero, 670, 250, 80, 80, null);

    }
}
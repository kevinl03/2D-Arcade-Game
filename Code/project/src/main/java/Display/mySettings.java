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


    public BufferedImage testimage_png;


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

    public mySettings(DisplayLayout dl) {
        try {
            getButtonPngs();
            getHeroColors();
            testimage_png = ImageIO.read(getClass().getResource("/testimage.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        color = dl.heroColor;

        leftScroll = iconButton(leftButtonPng, 625, 300, 40, 40);
        leftScroll.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                dl.heroColor = dl.heroColor.next();
                color = dl.heroColor;


                repaint();
            }
        });


        rightScroll = iconButton(rightButtonPng, 825, 300, 40, 40);
        rightScroll.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                dl.heroColor = dl.heroColor.prev();
                color = dl.heroColor;

                repaint();

            }
        });


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (testimage_png != null) {
            g.drawImage(testimage_png, 0, 0, 1500, 960, null);
        }
        skinSelection(g);
    }

    private JButton iconButton(BufferedImage image, int x, int y, int width, int height) {
        Image scaledImage = image.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon iconImage = new ImageIcon(scaledImage);
        JButton b = new JButton(iconImage);
        b.setBounds(x, y, width, height);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        return b;
    }


    private void skinSelection(Graphics g) {


        Graphics2D g2 = (Graphics2D) g;   //   Draws shapes

        this.add(leftScroll);
        this.add(rightScroll);
        BufferedImage hero = heroColorPngs.get(color);
        g2.drawImage(hero, 700, 260, 80, 80, null);


    }
}
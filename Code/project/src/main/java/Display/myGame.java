package Display;

import Helpers.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class myGame extends JPanel{
    private int pixelsize = 60;   //60x60 pixels
    private int columns = 25;
    private int rows = 15;
    public BufferedImage squirrel_png;
    KeyHandler kh;
    public int updaterows = 7;
    public int updatecolumns = 12;
    private CardLayout cl;
    private DisplayLayout dl;
    private JPanel dp;

    private JPanel mp;
    public int goMain = 0;
    public myGame(CardLayout cl, DisplayLayout dl, JPanel dp, JPanel mp){
        this.cl = cl;
        this.dl = dl;
        this.dp = dp;
        this.mp = mp;
        this.kh = dl.kh;
        setPreferredSize(new Dimension(columns*pixelsize, rows*pixelsize));
        getSquirrel();
        addKeyListener(kh);
    }

    public void updates() throws InterruptedException {
        if (kh.up && (updaterows != 0)) {
            updaterows--;
        }
        if (kh.left && (updatecolumns != 0)) {
            updatecolumns--;
        }
        if (kh.down && (updaterows != 14)) {
            updaterows++;
        }
        if (kh.right && (updatecolumns != 24)) {
            updatecolumns++;
        }
        if (kh.escape) {
            System.out.println("Paused");
            //if panel is not open, pop out panel
            if (dl.currentCard != 5) {
                // show associated pause panel
                cl.show(dp, "5");

                // current panel is pause Panel
                dl.currentCard = 5;

                mp.setFocusable(true);
                mp.requestFocus();
            }
            //unpress escape button
            kh.escape = false;
            dl.unpause = 1;
        }
    }

    public void getSquirrel(){
        try {
            squirrel_png = ImageIO.read(getClass().getResource("/squirrel.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset(){
        kh.up = false;
        kh.left = false;
        kh.down = false;
        kh.right = false;
        updaterows = 7;
        updatecolumns = 12;
    }

    @Override
    protected void paintComponent(Graphics g){   //   Draw something on JPanel
        super.paintComponent(g);   //   Method already exists, so super is used to add additional lines
        Graphics2D g2 = (Graphics2D) g;   //   Draws shapes
        g2.drawImage(squirrel_png, updatecolumns * 60, updaterows * 60, pixelsize, pixelsize, null);
        g2.drawRect(updatecolumns*60, updaterows*60, pixelsize,pixelsize);
        g2.dispose();
    }
}
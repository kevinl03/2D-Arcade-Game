package Display;

import Helpers.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartDisplay extends JPanel implements Runnable{

    // Set up display
    protected int pixelsize = 60;   //60x60 pixels
    private int columns = 25;
    private int rows = 15;

    private int displaywidth = pixelsize * columns;
    private int displayheight = pixelsize * rows;

    protected int updaterows = 7;
    protected int updatecolumns = 12;

    KeyHandler kh = new KeyHandler(this);

    public UIDraw uidraw = new UIDraw(this);
    Thread repeatThread;   //   Does tasks in background without interrupting main program
    public BufferedImage squirrel_png;

    // Game screens
    public int screens;
    public int title = 0;
    public int gaming = 1;
    public int settings = 2;
    public int difficulty = 3;

    public int challenge = 0;   //   0-easy   1-medium   2-hard

    public int freeze = 0;

    public int count = 0;

    static JFrame Startup;

    public void getSquirrel(){
        try {
            squirrel_png = ImageIO.read(getClass().getResource("/squirrel.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public StartDisplay(){
        setPreferredSize(new Dimension(displaywidth, displayheight));
        addKeyListener(kh);
        setFocusable(true);   //   Lets StartDisplay receive the key inputs
    }



    public static void main (String[] args){
        Startup = new JFrame();   //   New window
        Startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //   Exits when clicking top-right x
        Startup.setTitle("Hidden Squirrel: Peanuts and Acorns");

        StartDisplay Display = new StartDisplay();
        Display.getSquirrel();
        Startup.add(Display);
        Startup.pack();   //   Window fits preferred size
        Startup.setLocationRelativeTo(null);   //   Opens window at default center of screen
        Startup.setVisible(true);   //   Show screen
        Display.screens = Display.title;
        Display.startThread();

    }


    public void startThread(){
        repeatThread = new Thread(this);   //   When thread is created, use current object's run method
        repeatThread.start();
    }

    @Override
    public void run(){   //   When starting thread, have thread use this run method
        while(repeatThread != null){
            //System.out.println("");
            if(screens == title) {
                try {
                    updates();
                    repaint();
                    Thread.sleep(100);   //   Rest time before every update
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(screens == gaming) {
                try {
                    updates();
                    repaint();
                    Thread.sleep(100);   //   Rest time before every update
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(screens == difficulty) {
                if (freeze == 0) {
                    updates();
                    repaint();
                    freeze++;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                try {
                    updates();
                    repaint();
                    Thread.sleep(100);   //   Rest time before every update
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void updates(){

        if(screens == title) {

        }


        if(screens == gaming) {
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
        }
    }

    @Override
    protected void paintComponent(Graphics g){   //   Draw something on JPanel
        super.paintComponent(g);   //   Method already exists, so super is used to add additional lines
        Graphics2D g2 = (Graphics2D) g;   //   Draws shapes
        uidraw.draw(g2);
        g2.dispose();
        }
}
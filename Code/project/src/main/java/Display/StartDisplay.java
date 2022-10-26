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
    private int pixelsize = 60;   //60x60 pixels
    private int columns = 25;
    private int rows = 15;

    private int displaywidth = pixelsize * columns;
    private int displayheight = pixelsize * rows;

    private int updaterows = 7;
    private int updatecolumns = 12;

    KeyHandler kh = new KeyHandler();

    Thread repeatThread;   //   Does tasks in background without interrupting main program
    public BufferedImage squirrel_png;

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
        //testShortestPath();
        JFrame Startup = new JFrame();   //   New window
        Startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //   Exits when clicking top-right x
        Startup.setTitle("Hidden Squirrel: Peanuts and Acorns");

        StartDisplay Display = new StartDisplay();
        Display.getSquirrel();
        Startup.add(Display);
        Startup.pack();   //   Window fits preferred size

        Startup.setLocationRelativeTo(null);   //   Opens window at default center of screen
        Startup.setVisible(true);   //   Show screen

        Display.startThread();

    }

    public void startThread(){
        repeatThread = new Thread(this);   //   When thread is created, use current object's run method
        repeatThread.start();
    }

    @Override
    public void run(){   //   When starting thread, have thread use this run method
        while(repeatThread != null){
            updates();
            repaint();
            try {
                Thread.sleep(100);   //   Rest time before every update
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updates(){
        if(kh.up && (updaterows != 0) ){
            updaterows--;
        }
        if(kh.left && (updatecolumns != 0) ){
            updatecolumns--;
        }
        if(kh.down && (updaterows != 14) ){
            updaterows++;
        }
        if(kh.right && (updatecolumns != 24) ){
            updatecolumns++;
        }
    }

    @Override
    protected void paintComponent(Graphics g){   //   Draw something on JPanel
        super.paintComponent(g);   //   Method already exists, so super is used to add additional lines
        //g.drawImage(squirrelpng, 0, 0, this);
        //g.dispose();
        Graphics2D g2 = (Graphics2D) g;   //   Draws shapes
        //g2.setColor(Color.black);
        //g2.fillRect(updatecolumns*60,updaterows*60,pixelsize,pixelsize);
        g2.drawImage(squirrel_png, updatecolumns*60, updaterows*60, pixelsize, pixelsize, null);
    }
}

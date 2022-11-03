package Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class myTitle extends JPanel {

    private BufferedImage title_png;
    private BufferedImage title_squirrel;


    public myTitle(){
        try {
            title_png = ImageIO.read(getClass().getResource("/title_pic.png"));
            title_squirrel = ImageIO.read(getClass().getResource("/title_squirrel.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(title_png, 0, 0, 1500, 960, null);
        g.drawImage(title_squirrel, 400, 400, 293, 400, null);
    }
}

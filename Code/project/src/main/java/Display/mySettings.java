package Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class mySettings extends JPanel{

    public BufferedImage testimage_png;


    public mySettings(){
        try {
            testimage_png = ImageIO.read(getClass().getResource("/testimage.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (testimage_png != null){
            g.drawImage(testimage_png, 0, 0, 1500, 900, null);
        }
    }
}

package Display;

import java.awt.*;

public class UIDraw {
    StartDisplay sd;
    Graphics2D g2;
    Font titlefont;
    Font optionfont;
    private int centerx = 11*60;
    private int centery = 7*60;

    public int select = 0;

    public UIDraw(StartDisplay sd) {
        this.sd = sd;
        titlefont = new Font("Times New Roman", Font.BOLD, 50);
        optionfont = new Font("Times New Roman", Font.BOLD, 30);
    }

    public void draw(Graphics2D g2) {
        // Title
        this.g2 = g2;
        if (sd.screens == sd.title) {
            drawTitle();
        }

        // Gaming
        if (sd.screens == sd.gaming) {
            drawGaming();
        }
    }

    public void drawTitle(){
        g2.setFont(titlefont);
        g2.setColor(Color.black);
        g2.drawString("Hidden Squirrel: Peanuts and Acorns", centerx-300, centery-300);

        g2.setFont(optionfont);
        if(select == 0) {
            g2.setColor(Color.PINK);
            g2.drawString("Play", centerx, centery);
            g2.setColor(Color.black);
            g2.drawString("Settings", centerx, centery + 120);
            g2.drawString("Difficulty", centerx, centery + 240);
        } else if (select == 1) {
            g2.drawString("Play", centerx, centery);
            g2.setColor(Color.PINK);
            g2.drawString("Settings", centerx, centery + 120);
            g2.setColor(Color.black);
            g2.drawString("Difficulty", centerx, centery + 240);
        } else {
            g2.drawString("Play", centerx, centery);
            g2.drawString("Settings", centerx, centery + 120);
            g2.setColor(Color.PINK);
            g2.drawString("Difficulty", centerx, centery + 240);
        }
    }

    public void drawGaming(){
        g2.drawImage(sd.squirrel_png, sd.updatecolumns * 60, sd.updaterows * 60, sd.pixelsize, sd.pixelsize, null);
    }
}

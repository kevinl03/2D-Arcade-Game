package Display;

import javax.swing.*;
import java.awt.event.KeyListener;

public class myPause extends JPanel{


private DisplayLayout dl;
private KeyListener kh;

    public myPause(DisplayLayout dl){
        this.dl = dl;
        this.kh = dl.kh;
        addKeyListener(kh);
    }

}

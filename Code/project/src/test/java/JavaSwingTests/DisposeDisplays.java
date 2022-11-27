package JavaSwingTests;

import com.Display.DisplayLayout;

import javax.swing.*;
import java.awt.*;

public class DisposeDisplays {

    void deepDispose(DisplayLayout layout){
        layout.remove(layout.getmySettings());
        layout.remove(layout.getmyPause());
        layout.remove(layout.getGameOver());
        layout.remove(layout.getGameWon());
        layout.remove(layout.getTitlePanel());
        layout.remove(layout.displayPanel);
        layout.remove(layout.playPanel);
        layout.dispose();
    }
}

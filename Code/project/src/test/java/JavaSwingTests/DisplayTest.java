package JavaSwingTests;

import com.Display.DisplayLayout;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class DisplayTest {

    @Test
    void TestDisplay(){
        DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display.setVisible(true);

        display.dispose();

        assert(display.getFocusableWindowState());
        assert(!display.isShowing());
    }

    @Test
    void runMethod(){
        DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display.setVisible(true);

        //display.playPanel.goMain = 1;
        //display.kh.up = true;
        display.getTitlePanel().getplayButton().doClick();
        //display.getGameObjectData().getGameStats().getGameOver();


        display.dispose();

        assert(display.getFocusableWindowState());
        //assert(!display.isShowing());
    }


    @Test
    void soundsettings(){

    }

}

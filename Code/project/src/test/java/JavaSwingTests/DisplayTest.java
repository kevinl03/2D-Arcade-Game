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

        display.setVisible(false);

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
        display.kh.up = true;
        try {

            display.getTitlePanel().getplayButton().doClick();
            int x = 0;
            while(x < 100000000){
                System.out.printf("hi");
                x++;
            }
            assert(true);
        } catch(Exception e) {
            assert(false);
        }

        //display.getGameObjectData().getGameStats().getGameOver();


        //display.dispose();


        //assert(!display.isShowing());
    }


    @Test
    void soundsettings(){
        DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       // display.setVisible(true);

        try {
            display.getmySettings().getmuteButton().doClick();
            assert(true);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            assert(false);
        }


    }

}

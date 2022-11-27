package JavaSwingTests;

import com.Board.Difficulty;
import com.Display.DisplayLayout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
public class DisplayTest {
    DisplayLayout display = new DisplayLayout();

    @Test
    public void testDisplayNotShowing(){
        DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setVisible(false);


        assert(display.getFocusableWindowState());

        assert(!display.isShowing());
        display.dispose();
    }
    @Test
    public void testDisplayShowing(){
        DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        assert(display.isShowing());

        display.dispose();
    }

    @Test
    public void testPlayGameButtonTest(){
        //DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //display.playPanel.goMain = 1;
        display.kh.up = true;
        try {
            display.getTitlePanel().getplayButton().doClick();
            //the game object data gets instantiated meaning the button worked
            Assertions.assertNotEquals(display.getGameObjectData(), null);
        } catch(Exception e) {
            assert(false);
        }



        display.dispose();


        //assert(!display.isShowing());
    }

    @Test
    public void testPauseMenuTest(){
        //DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //display.playPanel.goMain = 1;
        display.kh.up = true;
        try {
            display.getTitlePanel().getplayButton().doClick();
            display.playPanel.reset();
            display.kh.escape = true;
            //the game object data gets instantiated meaning the button worked
            Assertions.assertEquals(display.getmyPause().isVisible(), false);

        } catch(Exception e) {
            assert(false);
        }
        display.dispose();

    }



    @Test
    public void testsoundsettings(){
        //DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       // display.setVisible(true);

        try {
            display.getmySettings().getmuteButton().doClick();
            assert(true);
        }catch(Exception e) {
            assert(false);
        }
        display.dispose();
    }
    @AfterEach
    public void teardown(){
        DisposeDisplays dispose = new DisposeDisplays();
        dispose.deepDispose(display);
        System.gc();
    }

}

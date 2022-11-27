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
    void TestDisplayNotShowing(){
        //DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setVisible(false);


        assert(display.getFocusableWindowState());

        assert(!display.isShowing());
        display.dispose();
    }
    @Test
    void TestDisplayShowing(){
        //DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        assert(display.isShowing());

        display.dispose();
    }

    //@Test
    void PlayGameButtonTest(){
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
    void PauseMenuTest(){
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
    void soundsettings(){
        //DisplayLayout display = new DisplayLayout();

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
        display.dispose();
    }
    @AfterEach
    void teardown(){
        DisposeDisplays dispose = new DisposeDisplays();
        dispose.deepDispose(display);
        System.gc();
    }

}

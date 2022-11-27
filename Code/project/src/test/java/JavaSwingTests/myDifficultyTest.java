package JavaSwingTests;

import com.Board.Difficulty;
import com.Display.DisplayLayout;
import org.junit.jupiter.api.*;

import javax.swing.*;


public class myDifficultyTest {

    DisplayLayout display = new DisplayLayout();

    @Test
    public void testGamePresetsToMediumDifficultyTest(){
        DisplayLayout display2 = new DisplayLayout();

        display.setVisible(false);
        //initialesd should always be Medium
        Assertions.assertEquals(Difficulty.MEDIUM, display2.dif);
        display2.dispose();

    }

    @Test
    public void testSetHardDifficultyToHard(){
        //DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //display.playPanel.goMain = 1;
        //display.kh.up = true;
        try {
            display.getDiffPanel().getHardButton().doClick();
            //the game object data gets instantiated meaning the button worked
            Assertions.assertEquals(display.dif, Difficulty.HARD);

            display.getTitlePanel().getplayButton().doClick();
            //on HARD, the amount of enemies generated is 3
            //so this make sure that the HARD gamemode is saved
            //and that when generating the map, 3 enemies are spawned in

            Assertions.assertEquals(display.getGameObjectData().getEnemyArray().size(), 3);


        } catch(Exception e) {
            assert(false);
        }
        display.dispose();

    }

    @Test
    public void testDifficultySettingsSetEasy(){
        //DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //display.playPanel.goMain = 1;
        //display.kh.up = true;
        try {
            display.getDiffPanel().getEasyButton().doClick();
            //the game object data gets instantiated meaning the button worked
            Assertions.assertEquals(display.dif, Difficulty.EASY);

            display.getTitlePanel().getplayButton().doClick();
            //on HARD, the amount of enemies generated is 1
            //so this make sure that the HARD gamemode is saved
            //and that when generating the map, 1 enemies are spawned in
            //which indicates that generation has been succesful on easy difficulty

            Assertions.assertEquals(display.getGameObjectData().getEnemyArray().size(), 1);

        } catch(Exception e) {
            assert(false);
        }
        display.dispose();

    }

    //tests the buttons in sequeunce and checks that the difficulty gets
    //updated each time
    @Test
    public void testChangeMultipleDifficulties(){
        //DisplayLayout display = new DisplayLayout();

        // Function to set default operation of JFrame.
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        try {
            display.getDiffPanel().getHardButton().doClick();
            //the game object data gets instantiated meaning the button worked
            Assertions.assertEquals(display.dif, Difficulty.HARD);

            display.getDiffPanel().getEasyButton().doClick();

            Assertions.assertEquals(display.dif, Difficulty.EASY);

            display.getDiffPanel().getMediumButton().doClick();

            Assertions.assertEquals(display.dif, Difficulty.MEDIUM);

            display.getTitlePanel().getplayButton().doClick();
            //on HARD, the amount of enemies generated is 2
            //so this make sure that the HARD gamemode is saved
            //and that when generating the map, 2 enemies are spawned in
            //this ensures the game plays at MEDIUM difficulty even
            //after multiple difficulty settings were changeed

            Assertions.assertEquals(display.getGameObjectData().getEnemyArray().size(), 2);

        } catch(Exception e) {
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

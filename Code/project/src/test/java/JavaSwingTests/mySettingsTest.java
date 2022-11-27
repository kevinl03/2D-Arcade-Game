package JavaSwingTests;


import com.Board.Difficulty;
import com.Display.DisplayLayout;
import org.junit.jupiter.api.*;
import com.Helpers.HeroColor;

public class mySettingsTest {

    DisplayLayout display = new DisplayLayout();
    @Test
    void ChangeHeroColor(){
        //DisplayLayout display = new DisplayLayout();





        display.getmySettings().getLeftScroll().doClick();
        Assertions.assertNotEquals(display.heroColor,HeroColor.BROWN);

        display.dispose();
    }
    @Test
    void GamePresetsToBrownColor(){
        //DisplayLayout display = new DisplayLayout();



        Assertions.assertEquals(display.heroColor, HeroColor.BROWN);
        display.dispose();
    }

    @Test
    void LeftScrollMultipleClicks(){
        //DisplayLayout display = new DisplayLayout();




        HeroColor OriginalColor = display.heroColor;
        HeroColor PrevColor = OriginalColor;

        HeroColor[] colors = HeroColor.getColors();

        for(int i = 1; i < colors.length; i++){
            PrevColor = display.heroColor;
            display.getmySettings().getLeftScroll().doClick();



            //test that loop does not reach the same color twice
            Assertions.assertNotEquals(display.heroColor, OriginalColor);
            //test that color changed from previous color
            Assertions.assertNotEquals(display.heroColor, PrevColor);
        }
        display.getmySettings().getLeftScroll().doClick();
        //must ensure that the color goes back in a full circle
        Assertions.assertEquals(display.heroColor,OriginalColor);
        display.dispose();
    }

    @Test
    void RightScrollMultipleClicks(){
        //DisplayLayout display = new DisplayLayout();



        HeroColor OriginalColor = display.heroColor;
        HeroColor PrevColor = OriginalColor;

        HeroColor[] colors = HeroColor.getColors();

        //loop one less than the size of the elements
        for(int i = 1; i < colors.length; i++){
            PrevColor = display.heroColor;
            display.getmySettings().getRightScroll().doClick();



            //test that loop does not reach the same color twice
            Assertions.assertNotEquals(display.heroColor, OriginalColor);
            //test that color changed from previous color
            Assertions.assertNotEquals(display.heroColor, PrevColor);
        }
        //last comparison
        display.getmySettings().getRightScroll().doClick();
        //must ensure that the color goes back in a full circle
        Assertions.assertEquals(display.heroColor,OriginalColor);
        display.dispose();
    }

    @Test
    void TestSounds(){
        //DisplayLayout display = new DisplayLayout();


        try {
            display.getmySettings().getmuteButton().doClick();
            display.getmySettings().getUnmuteButton().doClick();
            assert(true);
        }
        catch(Exception e){
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

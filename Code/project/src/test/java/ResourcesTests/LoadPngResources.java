package ResourcesTests;

import com.Display.DisplayLayout;
import com.Display.myGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoadPngResources {

    @Test
    public void testloadPngResources() {
        DisplayLayout displayLayout = new DisplayLayout();
        try{
            displayLayout.playPanel.getImages();
        }catch (Exception e){
            assert(false);
        }
        displayLayout.dispose();
    }
}

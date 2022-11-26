import com.Board.BoardData;
import com.Board.Objects;
import com.Display.DisplayLayout;
import com.Display.myTitle;
import com.Entities.Enemy;
import com.Entities.Position;
import com.Helpers.KeyHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyHandlerTests {
    @Test
    void keyHandlerTest(){
        DisplayLayout dl = new DisplayLayout();
        dl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dl.setVisible(true);
       myTitle myTitle = dl.getMyTitle();
       KeyHandler kh = dl.getKeyHandler();
       JButton button = myTitle.getplayButton();
        button.doClick();
        BoardData board = dl.getGameObjectData().getBoard();
        for(int i = 0; i < 25; i++) {
            for (int j = 0; j < 15; j++) {
                if (board.getTypeAt(i, j) != Objects.HERO) {
                    board.setTypeAt(new Position(i, j), Objects.BUSH);
                }
            }
        }
       try{
           Thread.sleep(20);
           Robot robot = new Robot();
           robot.keyPress(KeyEvent.VK_W); // w
           Thread.sleep(20);
           Assertions.assertTrue(kh.up);
           robot.keyRelease(KeyEvent.VK_W);
           Thread.sleep(20);
           Assertions.assertFalse(kh.up);

           robot.keyPress(KeyEvent.VK_A); // a
           Thread.sleep(20);
           Assertions.assertTrue(kh.left);
           robot.keyRelease(KeyEvent.VK_A);
           Thread.sleep(20);
           Assertions.assertFalse(kh.left);

           robot.keyPress(KeyEvent.VK_S ); // s
           Thread.sleep(20);
           Assertions.assertTrue(kh.down);
           robot.keyRelease(KeyEvent.VK_S );
           Thread.sleep(20);
           Assertions.assertFalse(kh.down);

           robot.keyPress(KeyEvent.VK_D); // d
           Thread.sleep(20);
           Assertions.assertTrue(kh.right);
           robot.keyRelease(KeyEvent.VK_D );
           Thread.sleep(20);
           Assertions.assertFalse(kh.right);

           robot.keyPress(KeyEvent.VK_ESCAPE); // escape
           Thread.sleep(20);
           Assertions.assertTrue(kh.escape);
           robot.keyRelease(KeyEvent.VK_ESCAPE);
           Thread.sleep(20);
           Assertions.assertFalse(kh.escape);
       }catch (AWTException e){
           e.printStackTrace();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }


    }

}

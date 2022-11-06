package Helpers;
import Display.DisplayLayout;
import Display.myGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Detects key press and key lifts
 */
public class KeyHandler implements KeyListener{
    public boolean up, left, down, right, escape;
    DisplayLayout dl;

    /**
     * Lets the JFrame detect key press and key lifts
     * @param dl the JFrame object
     */
    public KeyHandler(DisplayLayout dl){
        this.dl = dl;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Sets boolean true when W,A,S,D,Esc key is pressed
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Gaming key press
            if (code == KeyEvent.VK_W) {
                up = true;
            }

            if (code == KeyEvent.VK_A) {
                left = true;
            }

            if (code == KeyEvent.VK_S) {
                down = true;
            }

            if (code == KeyEvent.VK_D) {
                right = true;
            }
            if (code == KeyEvent.VK_ESCAPE)
            {
                escape = true;
            }
    }

    /**
     * Sets boolean false when W,A,S,D key is released.
     * Esc key set to false when button pressed.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            up = false;
        }

        if(code == KeyEvent.VK_A){
            left = false;
        }

        if(code == KeyEvent.VK_S){
            down = false;
        }

        if(code == KeyEvent.VK_D){
            right = false;
        }
    }

}

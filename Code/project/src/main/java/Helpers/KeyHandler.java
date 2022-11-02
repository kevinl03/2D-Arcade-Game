package Helpers;
import Display.DisplayLayout;
import Display.myGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean up, left, down, right, escape;
    DisplayLayout dl;

    public KeyHandler(DisplayLayout dl){
        this.dl = dl;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

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

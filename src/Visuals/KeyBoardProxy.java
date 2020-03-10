package Visuals;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Visuals.GameBoardPanel.*;


public class KeyBoardProxy extends KeyAdapter {



    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if ((keyCode == KeyEvent.VK_LEFT) && (!rightArrow)) {

            leftArrow = true;
            upArrow = false;
            downArrow = false;
        }

        if ((keyCode == KeyEvent.VK_RIGHT) && (!leftArrow)) {
            rightArrow = true;
            upArrow = false;
            downArrow = false;
        }

        if ((keyCode == KeyEvent.VK_UP) && (!downArrow)) {
            upArrow = true;
            leftArrow = false;
            rightArrow = false;

        }

        if ((keyCode == KeyEvent.VK_DOWN) && (!upArrow)) {
            downArrow = true;
            leftArrow = false;
            rightArrow = false;

        }




    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
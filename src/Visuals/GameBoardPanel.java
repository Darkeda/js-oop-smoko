package Visuals;

import javax.swing.*;
import java.awt.*;


public class GameBoardPanel extends JFrame {


    static boolean upArrow = false;
    static boolean downArrow = false;
    static boolean leftArrow = false;
    static boolean rightArrow = true;
    private int  moveSize = 15;

    GameBoardPanel(){


       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(500, 500);
       setVisible(true);
       setBoard();

    }
    static public int x = 100;
    static public int y = 100;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void setBoard() {
        addKeyListener(new KeyBoardProxy());
        setBackground(Color.GRAY);

    }

    public void paint(Graphics g) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        move();
        g.setColor(Color.black);
        g.drawRect(x, y, 15, 15);

        repaint();


    }

    public void move(){

        if (upArrow) {
            y -= moveSize;
        }

        if (downArrow) {
            y += moveSize;
        }

        if (leftArrow) {
            x -= moveSize;
        }

        if (rightArrow) {
            x += moveSize;
        }
    }
}

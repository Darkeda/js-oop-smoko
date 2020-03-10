package Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeFrame extends JFrame {


    SnakeFrame() {


        add(new GameBoardPanel());
        setSize(500, 600);
        setResizable(false);


        setVisible(true);

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}

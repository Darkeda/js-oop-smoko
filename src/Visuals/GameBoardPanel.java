package Visuals;


import OtherObjects.Food;
import OtherObjects.Obstacles;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class GameBoardPanel extends JPanel implements ActionListener {
    KeyBoardProxy keyboard = new KeyBoardProxy();

    static boolean upArrow = false;
    static boolean downArrow = false;
    static boolean leftArrow = false;
    static boolean rightArrow = true;
    private final int HEIGHT = 500;
    private final int WIDTH = 500;
    private final int RANDOM_RANGE = 45;

    boolean gameStillRunning = true;

    // (500*500)/(10*10) = 2500

    private final int maxSquaresInTheBoard = 2500;
    private final int x[] = new int[maxSquaresInTheBoard];
    private final int y[] = new int[maxSquaresInTheBoard];
    private int moveSize = 10;
    private int snakeSize = 1;
    private ArrayList<Food> foodArray = new ArrayList();
    private ArrayList<Obstacles> obstacles = new ArrayList();
    int foodCount = (int) ((Math.random() * 10) + 1);
    int obstacleCount = (int) ((Math.random() * 5) + 1);

    int points = 0;

    private Timer timer;

    GameBoardPanel() {
        setBoard();
    }


    private void setBoard() {


        setSize(WIDTH, HEIGHT);
        addKeyListener(keyboard);
        setFocusable(true);
        setVisible(true);
        setBackground(Color.GRAY);

        run();


    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMaterials(g);


    }

    public void drawMaterials(Graphics g) {
        g.drawRect(0, 0, 500, 510);


        if (gameStillRunning) {
            g.setColor(Color.green);
            for (int i = 0; i < snakeSize; i++) {


                g.drawRect(x[i], y[i], moveSize, moveSize);
            }

            if (foodArray.size() == foodCount) {
                for (int i = 0; i < foodCount; i++) {
                    if (foodArray.get(i) != null) {


                        g.setColor(Color.red);
                        g.fillRect(foodArray.get(i).getX(), foodArray.get(i).getY(), moveSize, moveSize);
                    }
                }
            }

            if (obstacles.size() == obstacleCount) {
                for (int i = 0; i < obstacleCount; i++) {
                    if (obstacles.get(i) != null) {


                        g.setColor(Color.black);
                        g.fillRect(obstacles.get(i).getX(), obstacles.get(i).getY(), moveSize, moveSize);
                    }
                }
            }


        }

        if (!gameStillRunning && points < 300) {
            g.setFont(new Font("Plain", Font.BOLD, 25));
            g.drawString("Game Over", 180, HEIGHT / 2);
        }
        if (!gameStillRunning && points >= 300) {
            g.setFont(new Font("Plain", Font.BOLD, 25));
            g.drawString("You win", 180, HEIGHT / 2);
        }
        drawScore(g);


    }

    public void detectCollision() {
        for (int i = 1; i < snakeSize; i++) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                gameStillRunning = false;
                System.out.println("Game over");
            }
        }
    }

    public void drawScore(Graphics g) {
        g.setFont(new Font("Plain", Font.BOLD, 25));
        g.drawString("Score:" + String.valueOf(points), 180, 550);
    }

    public void outOfArea() {
        for (int i = 0; i < snakeSize; i++) {
            if (x[i] < 0) {
                x[i] = WIDTH;
            }
            if (x[i] > WIDTH) {
                x[i] = 0;
            }
            if (y[i] < 0) {
                y[i] = HEIGHT;
            }
            if (y[i] > HEIGHT) {
                y[i] = 0;
            }

        }
    }

    public void run() {

        for (int z = 0; z < snakeSize; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        timer = new Timer(250, this);
        timer.start();

    }

    public void move() {
        for (int z = snakeSize; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (upArrow) {

            y[0] -= moveSize;
        }

        if (downArrow) {
            y[0] += moveSize;
        }

        if (leftArrow) {
            x[0] -= moveSize;
        }

        if (rightArrow) {
            x[0] += moveSize;
        }


    }

    public void spawnFood() {
        // if (food == null) {
        for (int i = 0; i < foodCount; i++) {


            if (foodArray.size() != foodCount) {
                boolean shouldItMove;
                if (Math.random() < 0.5) {
                    shouldItMove = false;
                } else {
                    shouldItMove = true;
                }
                int x = (int) (Math.random() * RANDOM_RANGE);
                int y = (int) (Math.random() * RANDOM_RANGE);
                foodArray.add(new Food(x * moveSize, y * moveSize, false));
            }
            if (foodArray.get(i).isDoesItMove()) {
                foodArray.get(i).move();
            }
        }
    }


    public void spawnObstacle() {

        for (int i = 0; i < obstacleCount; i++) {


            if (obstacles.size() != obstacleCount) {

                int x = (int) (Math.random() * RANDOM_RANGE);
                int y = (int) (Math.random() * RANDOM_RANGE);
                obstacles.add(new Obstacles(x * moveSize, y * moveSize));
            }

        }
    }

    public void collisionWithFood() {

        for (int i = 0; i < foodCount; i++) {


            if ((x[0] == foodArray.get(i).getX()) && (y[0] == foodArray.get(i).getY())) {

                snakeSize++;
                points += foodArray.get(i).getPointGiven();
                foodArray.remove(i);

                break;


            }

        }

    }


    public void collisionWithObstacles() {

        for (int i = 0; i < obstacleCount; i++) {


            if ((x[0] == obstacles.get(i).getX()) && (y[0] == obstacles.get(i).getY())) {


                gameStillRunning = false;

                break;


            }

        }

    }

    public void arePointsNeededReached() {
        if (points >= 300) {
            gameStillRunning = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spawnFood();
        spawnObstacle();
        collisionWithFood();
        move();


        outOfArea();
        detectCollision();
        collisionWithObstacles();

        arePointsNeededReached();
        repaint();

    }

    public void reset() {

        upArrow = false;
        downArrow = false;
        leftArrow = false;
        rightArrow = true;

        foodArray = new ArrayList();
        obstacles = new ArrayList();
        foodCount = (int) ((Math.random() * 10) + 1);
        obstacleCount = (int) ((Math.random() * 5) + 1);
        gameStillRunning = true;

        points = 0;

        removeKeyListener(keyboard);
        addKeyListener(keyboard);

    }

}



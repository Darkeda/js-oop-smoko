package OtherObjects;

public class Food {

    int pointGiven;
    int x;
    int y;
    boolean doesItMove = false;
    int moveCounter = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPointGiven() {
        return pointGiven;
    }

    public boolean isDoesItMove() {
        return doesItMove;
    }

    public Food(int x, int y, boolean doesItMove) {
        this.x = x;
        this.y = y;
        this.doesItMove = doesItMove;
        if (doesItMove) {
            this.pointGiven = 15;
        } else {
            pointGiven = 10;
        }

    }

    public void move() {
        if (moveCounter < 5) {
            x += 10;

        } else {
            x -= 10;

        }
        moveCounter++;
        if (moveCounter == 10) {
            moveCounter = 0;
        }
    }

}

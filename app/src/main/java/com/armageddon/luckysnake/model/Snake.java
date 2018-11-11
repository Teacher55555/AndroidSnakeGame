package com.armageddon.luckysnake.model;


import android.graphics.Point;
import android.graphics.Rect;

import com.armageddon.luckysnake.common.Dimension;
import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.controller.ControllerPlay;
import com.armageddon.luckysnake.levels.Level;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * Snake class is the class witch manages snake movement, body size, checks when snake eat itself
 * or eat another snake (2 Players game)
 **/

public class Snake extends Thread implements GameElement, SnakeStep {

    @Override
    public boolean isHide() {
        return false;
    }

    enum SnakeDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private boolean onTheBlanket;
    transient private Point target = new Point();
    private boolean goToTarget;
    private int gameLevel;
    private int eatFruitForNewLevel;
    private int eatFruitForCoin;
    private int eatFruitForRocket;
    private int eatFruitForFruits;
    private int score;
    private int eatFruitForSpeed; //number of eaten fruits
    private int life; // number of player life
    private int lifeCheckPoint; // 1000
    private SnakeDirection direction = SnakeDirection.RIGHT;
    private boolean isDead;
    private boolean pause;
    protected int x, y;
    int stepX, stepY;
    private int objectWidth;
    private int objectHeight;
    int snakeSpeed;
    private boolean bonusLife = true;
    private boolean girl;
    private boolean girlSleep;
    private boolean moveON = true;
    private Dimension coordinates = new Dimension(x,y);
    //    private GameHandler gameHandler;
    transient  private Level level;
    public void setMoveON(boolean moveON) {
        this.moveON = moveON;
    }
    public boolean isOnTheBlanket() {
        return onTheBlanket;
    }
    void setOnTheBlanket(boolean onTheBlanket) {
        this.onTheBlanket = onTheBlanket;
    }
    public boolean isGoToTarget() {
        return goToTarget;
    }
    public void setTarget(Point target) {
        this.target = target;
        goToTarget = true;
    }

    public Point getTarget() {
        return target;
    }
    public boolean isBonusLife() {
//        return bonusLife && level.getLevel() != 0 && level.getLevel() != 7;
        return false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setGirl(boolean girl) {
        this.girl = girl;
    }

    public void setGirlSleep(boolean girlSleep) {
        this.girlSleep = girlSleep;
    }

    public boolean isGirlSleep() {
        return girlSleep;
    }

    public boolean isGirl() {
        return girl;
    }

    @Override
    public void setBonusLife() {
        if (isBonusLife()) {
            life++;
            level.playBonusMusic();
            level.animate1up();
            bonusLife = false;
        }
    }


    public int getObjectWidth() {
        return objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public int getEatFruitForNewLevel() {
        return eatFruitForNewLevel;
    }

    public void setEatFruitForNewLevel(int eatFruitForNewLevel) {
        this.eatFruitForNewLevel = eatFruitForNewLevel;
    }

    public void setEatFruitForFruits(int eatFruitForFruits) {
        this.eatFruitForFruits = eatFruitForFruits;
    }

    public int getEatFruitForFruits() {
        return eatFruitForFruits;
    }

    public void setEatFruitForCoin(int eatFruitForCoin) {
        this.eatFruitForCoin = eatFruitForCoin;
    }

    public int getEatFruitForCoin() {
        return eatFruitForCoin;
    }

    public int getLifeCheckPoint() {
        return lifeCheckPoint;
    }

    public void setLifeCheckPoint(int lifeCheckPoint) {
        this.lifeCheckPoint = lifeCheckPoint;
    }

    public void setLifeCount(int life) {
        this.life = life;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEatFruitForSpeed() {
        return eatFruitForSpeed;
    }

    public void setEatFruitForSpeed(int eatFruitForSpeed) {
        this.eatFruitForSpeed = eatFruitForSpeed;
    }

    public int getEatFruitForRocket() {
        return eatFruitForRocket;
    }

    public void setEatFruitForRocket(int eatFruitForRocket) {
        this.eatFruitForRocket = eatFruitForRocket;
    }

    private void speedConverter (int speed) {
        switch (speed) {
            case -1: snakeSpeed = 250; break;
            case 0: snakeSpeed = 160; break;
            case 1: snakeSpeed = 110; break;
            case 2: snakeSpeed = 80; break;
            case 3: snakeSpeed = 65; break;
            case 4: snakeSpeed = 50; break;
        }
    }

    public int getScore() {
        return score;
    }

    public void setSnakeSpeed (int speed) {
        speedConverter(speed);
    }

    public int getSpeed () {
        switch (snakeSpeed) {
            case 250: return -1;
            case 160: return 0;
            case 110: return 1;
            case 80: return 2;
            case 65: return 3;
            default: return 4;
        }
    }

    public int getLifeCount() {
        return life;
    }
    void pauseCheck () throws InterruptedException {
        synchronized (ControllerPlay.class) {
            while (pause) {
                ControllerPlay.class.wait();
            }
        }
    }

    public Snake (Level level, int objectWidth, int objectHeight) {
        this.level = level;
        this.objectHeight = (int) (objectHeight *  Level.scaleFactor);
        this.objectWidth = (int) (objectWidth *  Level.scaleFactor);
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public void setPause(boolean pause) {
        this.pause = pause;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public Rect getRectOfElement() {
        return new Rect(x, y, x + objectWidth, y + objectHeight);
    }

    void checkDirection () {
        synchronized (SnakeStep.class) {
            if (stepX == 0 && stepY < 0) {
                direction = SnakeDirection.UP;
            }
            if (stepX == 0 && stepY > 0) {
                direction = SnakeDirection.DOWN;
            }
            if (stepX < 0 && stepY == 0) {
                direction = SnakeDirection.LEFT;
            }
            if (stepX > 0 && stepY == 0) {
                direction = SnakeDirection.RIGHT;
            }
        }
    }

    public void setDirection(String direction) {
        synchronized (SnakeStep.class) {
            if (direction.equals("UP")) {
                stepX = 0;
                stepY = (int) (-20 * Level.scaleFactor);
                this.direction = SnakeDirection.UP;
            }
            if (direction.equals("DOWN")) {
                stepX = 0;
                stepY = (int) (20 * Level.scaleFactor);
                this.direction = SnakeDirection.DOWN;
            }
            if (direction.equals("LEFT")) {
                stepY = 0;
                stepX = (int) (-20 * Level.scaleFactor);
                this.direction = SnakeDirection.LEFT;
            }
            if (direction.equals("RIGHT")) {
                stepY = 0;
                stepX = (int) (20 * Level.scaleFactor);
                this.direction = SnakeDirection.RIGHT;
            }
        }
    }

    public String getDirection() {
        synchronized (SnakeStep.class) {
            return direction.name();
        }
    }

    public Dimension getCoordinates() {
        return coordinates;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        checkDirection();
        isDead = dead;
    }

    public int getStepX() {
            return stepX;
    }

    public int getStepY() {
            return stepY;
    }

    public void setStepX(int stepX) {
            this.stepX = stepX;
    }

    public void setStepY(int stepY) {
            this.stepY = stepY;
    }

    private volatile ArrayList<Integer[]> body = new ArrayList<>();  // body of the snake. Each element keeps X and Y coordinates

    public synchronized ArrayList<Integer[]> getBody() { // returns Snake body
        return body;
    }

    public void addBody(int count) {
        synchronized (Snake.class) {
            for (int i = 0; i < count; i++) {
                body.add(new Integer[]{x, y});   // adds 1 body element to the end of the snake body
            }
        }
    }

     void moveBody(int x, int y) {
         if (moveON) {// Moves the snake by 1 step, adding the received coordinates to the beginning of the body and simultaneously removing the last coordinates from the end of the body
        synchronized (Snake.class) {
                body.add(0, new Integer[]{x, y});
                body.remove(body.size() - 1);
            }
        }
    }


    public  void deleteBody (int count) {    // removes body elements. If count == -1 => clear all body
        synchronized (Snake.class) {
            if (count == -1) {
                body.clear();
            } else {
                for (int i = 0; i < count; i++) {
                    body.remove(body.size() - 1);
                }
            }
        }
    }

    @Override
    public void run() {
        level.readyText();
        while (!Thread.interrupted()) {
                try {
                        pauseCheck();
                        x += stepX;
                        y += stepY;
                        level.gameOverCheck(null, null);
                        moveBody(x, y);
                        synchronized (SnakeStep.class) {
                            checkDirection();
                        }
                        level.dotEatCheck();
                        TimeUnit.MILLISECONDS.sleep(snakeSpeed);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
    }

    public synchronized int getBodySize() {      // returns size of the body
        return body.size();
    }
    public synchronized void start(int x,
                                   int y,
                                   boolean isPlayer1Game,
                                   int bodyCount,
                                   int snakeSpeed,
                                   int stepX,
                                   int stepY) { // sets up snake's coordinates and body amount when the game starts or when the snake has respawn
        body.clear();
        this.snakeSpeed = snakeSpeed;
        int count = 0;
        this.x = x;
        this.y = y;
        this.stepX = stepX;
        this.stepY = stepY;

        speedConverter(snakeSpeed);

        if (isPlayer1Game) {
            for (int i = 0; i < bodyCount; i++) {
                body.add(new Integer[]{x - count, y});
                count += (int) (20 * Level.scaleFactor);
            }
        } else {
            for (int i = 0; i < bodyCount; i++) {
                body.add(new Integer[]{x + count, y});
                count += (int) (20 * Level.scaleFactor);
            }
        }
    }

    public boolean selfEated() {     // Checks, if the head of the snake has collision with it's body
        Boolean gameOver = false;

        for (int i = 1; i < body.size(); i++) {
            if (x == body.get(i)[0] && y == body.get(i)[1]) {
                gameOver = true;
            }
        }
        return gameOver;
    }

    synchronized int oneSnakeEatAnother(Snake snake) {         // For 2 Players game. Checks, if snake has collision with another snake.
        int result = 0; // Returns 0 - no collision, 1 - collision, 2 - heads collision


        if (body.size() == 0 || snake.body.size() == 0) {
            return result;
        }

        Point headOneSnake = new Point(this.body.get(0)[0], this.body.get(0)[1]);
        Point headAnotherSnake = new Point(snake.body.get(0)[0], snake.body.get(0)[1]);

        if (headOneSnake.equals(headAnotherSnake)) {
            return 2;
        }

        for (int i = 1; i < snake.body.size(); i++) {
            Point bodyAnotherSnake = new Point(snake.body.get(i)[0], snake.body.get(i)[1]);
            if (headOneSnake.equals(bodyAnotherSnake)) {
                return 1;
            }
        }
        return result;
    }
}



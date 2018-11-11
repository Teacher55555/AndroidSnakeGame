package com.armageddon.luckysnake.model;

import android.graphics.Rect;
import com.armageddon.luckysnake.common.Dimension;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.levels.Level;

import java.util.concurrent.TimeUnit;

public class LawnMover extends Dot implements GameElement {

    public int x = - 1000 ,y = - 1000;
    private int stepX;
    private int stepY;
    private int offSetStartY;
    private int offSetStartX;
    private boolean straightforward;
    private boolean pig;
    private transient Music music;
    boolean isContinue;
    public boolean isPig() {
        return pig;
    }
    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }
    private int waitTime;

    public LawnMover (Snake Player1snake,
                      Snake Player2snake,
                      int speed,
                      int waitTime,
                      boolean pig,
                      boolean straightforward,
                      int count,
                      int frequency,
                      int objectWidth,
                      int objectHeight,
                      Music music){
        super(Player1snake,Player2snake);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = speed;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight *  Level.scaleFactor);
        this.waitTime = waitTime;
        this.pig = pig;
        this.straightforward = straightforward;
        this.music = music;
    }

    public LawnMover (Snake Player1snake,
                      Snake Player2snake,
                      int speed,
                      int waitTime,
                      boolean pig,
                      boolean straightforward,
                      int count,
                      int frequency,
                      int objectWidth,
                      int objectHeight){
        super(Player1snake,Player2snake);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = speed;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight *  Level.scaleFactor);
        this.waitTime = waitTime;
        this.pig = pig;
        this.straightforward = straightforward;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }
    public void setStraightforward(boolean straightforward) {
        this.straightforward = straightforward;
    }

    public void setSpeed (int speed) {
        this.sleepTime = speed;
    }

    @Override
    Dimension makeDot() {
        if (count % frequency == 0) {
            isContinue = false;
            do {
                if (pig) {
                    if (snakePlayer1.getEatFruitForNewLevel() >= 40) {
                        music.pigSpeedUp();
                    } else {
                        music.pig();
                    }
                    x = Level.gameField.right - objectWidth;
                    y = (int) (Level.gameField.top + Math.random() *
                            (Level.gameField.bottom - Level.gameField.top - objectHeight - 30
                                    * Level.scaleFactor));
                    stepX = - (int) (2 * Level.scaleFactor);
                } else {
                    x = Level.gameField.left;
                    y = (int) (Level.gameField.top + Math.random() *
                            (Level.gameField.bottom - Level.gameField.top - objectHeight - 30
                                    * Level.scaleFactor));
                    stepX = (int) (2 * Level.scaleFactor);
                }

            } while (dotOnHeadCheck() );

        } else {
            count++;
            x = -1000;
            y = -1000;
            return coordinates = new Dimension(-1000, -1000); // return coordinates outside the game area
        }
        count++;
        return coordinates = new Dimension(x, y);
    }

    @Override
    public Rect getRectOfElement() {
        float scaleFactor = Level.scaleFactor;
        return new Rect(x + (int) (10 *  scaleFactor) ,
                y + (int) (80 * scaleFactor),
                x + objectWidth - (int) (10 * scaleFactor),
                y + objectHeight - (int) (25 * scaleFactor));
    }

    private boolean dotOnHeadCheck () {
        Rect dotRect = new Rect(x,
                y - (int) (20 * Level.scaleFactor),
                x + objectWidth + (int) (20 * Level.scaleFactor),
                y + objectHeight + (int) (20 * Level.scaleFactor));
        return dotRect.contains(snakePlayer1.getX(), snakePlayer1.getY());
    }

   private void stepYchange() {
        if (x - offSetStartX > ((10 + Math.random() * 100) * Level.scaleFactor)) {
            if (stepY == 0) {
                offSetStartY = y;
                int chance = 5;
                int result = (int) (Math.random() * chance);
                if (result == 0) {
                    stepY = (int) (2 * Level.scaleFactor);
                }
                if (result == 4) {
                    stepY = (int) (-2 * Level.scaleFactor);
                }
            } else {
                if (stepY > 0 && y - offSetStartY > ((100 + Math.random() * 50) * Level.scaleFactor)) {
                    stepY = 0;
                    offSetStartX = x;
                }
                if (stepY < 0 && offSetStartY - y > ((100 + Math.random() * 50) * Level.scaleFactor)) {
                    stepY = 0;
                    offSetStartX = x;
                }
            }
        }
    }


    @Override
    public void run() {
        if (!isContinue) {
            makeDot();
            try {
                pauseCheck();
                TimeUnit.SECONDS.sleep(waitTime);
                pauseCheck();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!Thread.interrupted()) {
            try {
                pauseCheck();
                if (pig) {
                    if (!straightforward) {
                        stepYchange();
                    }
                    x += stepX;
                    y += stepY;
                    if (x <= Level.gameField.left ||
                            y >= Level.gameField.bottom ||
                            y <= Level.gameField.top - (int) (65 * Level.scaleFactor)) {
                        interrupt();
                    }
                } else {
                    if (!straightforward) {
                        stepYchange();
                    }
                    x += stepX;
                    y += stepY;

                    if (x >= Level.gameField.right - objectWidth ||
                            y >= Level.gameField.bottom ||
                            y <= Level.gameField.top - (int) (65 * Level.scaleFactor)) {
                        interrupt();
                    }
                }
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException ex) {
                makeDot();
                stepY = 0;
                offSetStartX = 0;
                try {
                    TimeUnit.SECONDS.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


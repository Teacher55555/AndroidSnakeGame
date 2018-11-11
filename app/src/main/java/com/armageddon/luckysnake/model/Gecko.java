package com.armageddon.luckysnake.model;

import android.graphics.Rect;

import com.armageddon.luckysnake.common.Dimension;
import com.armageddon.luckysnake.levels.Level;

import java.util.concurrent.TimeUnit;

public class Gecko extends Dot implements GameElement {

    public int x = - 1000 ,y = - 1000;
    private int stepX;
    private int stepY;
    private int direction; // 0 Right, 1 Down, 2 Left, 3 Up
    private int waitTime;
    boolean run;

    public Gecko (Snake Player1snake,Snake Player2snake,
                  int speed,
                  int waitTime,
                  int direction,
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
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
    public void setSpeed (int speed) {
        this.sleepTime = speed;
    }

    @Override
    Dimension makeDot() {
        if (count % frequency == 0) {
            float scaleFactor = Level.scaleFactor;
            direction = (int) (Math.random()*4);

            if (direction == 0 || direction == 2) {
                objectWidth = (int) (158 * scaleFactor);
                objectHeight = (int) (55 * scaleFactor);
            } else {
                objectWidth = (int) (55 * scaleFactor);
                objectHeight = (int) (158 * scaleFactor);
            }

            switch (direction) {
                case 0 :
                    x =  Level.gameField.left;
                    y = (int) (Level.gameField.top + Math.random() *
                            (Level.gameField.bottom - Level.gameField.top - objectHeight));
                    if (y <= Level.gameField.top + 70 * scaleFactor) {
                        y = (int) (Level.gameField.top + 71 * scaleFactor);
                    }
                    stepX = (int) (2 * Level.scaleFactor);

                    break;
                case 1 :
                    x = (int) (Level.gameField.left + Math.random() *
                            (Level.gameField.right - Level.gameField.left - objectWidth));
                    if (x <= Level.gameField.left + 70 * scaleFactor) {
                        x = (int) (Level.gameField.left + 71 * scaleFactor);
                    }
                    y = Level.gameField.top;
                    stepY = (int) (2 * Level.scaleFactor);

                    break;
                case 2 :
                    x =  Level.gameField.right - objectWidth;
                    y = (int) (Level.gameField.top + Math.random() *
                            (Level.gameField.bottom - Level.gameField.top - objectHeight));
                    stepX = (int) (-2 * Level.scaleFactor);

                    break;
                case 3 :
                    x = (int) (Level.gameField.left + Math.random() *
                            (Level.gameField.right - Level.gameField.left - objectWidth));
                    y = Level.gameField.bottom - objectHeight;
                    stepY = (int) (-2 * Level.scaleFactor);
                    break;
            }

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
        if (direction == 0 || direction == 2) {
            return new Rect(x + (int) (5 *  scaleFactor),
                    y + (int) (10 * scaleFactor),
                    x + objectWidth - (int) (5 * scaleFactor),
                    y + objectHeight - (int) (10 * scaleFactor));
        }
        return new Rect(x + (int) (10 *  scaleFactor),
                y + (int) (5 * scaleFactor),
                x + objectWidth - (int) (10 * scaleFactor),
                y + objectHeight - (int) (5 * scaleFactor));
    }

    public boolean isRun() {
        return run;
    }

    private void endOfFieldCheck () {
        if ((direction == 0 && x >= Level.gameField.right - objectWidth) ||
                (direction == 1 && y >= Level.gameField.bottom) ||
                (direction == 2 && x <= Level.gameField.left ) ||
                (direction == 3 && y <= Level.gameField.top )) {
            interrupt();
        }
    }

    @Override
    public void run() {
        if (!isContinue) {
            run = false;
            makeDot();
            try {
                pauseCheck();
                TimeUnit.SECONDS.sleep(waitTime);
                pauseCheck();
                run = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!Thread.interrupted()) {
            try {
                pauseCheck();

                if (run) {
                    x += stepX;
                    y += stepY;
                    isContinue = false;
                    endOfFieldCheck();
                } else {
                    interrupt();
                }

                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                if (!isContinue) {
                    x = -1000;
                    y = -1000;
                    stepY = 0;
                    stepX = 0;
                    run = false;
                    makeDot();
                }

                try {
                    pauseCheck();
                    TimeUnit.SECONDS.sleep(waitTime);
                    pauseCheck();
                    run = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


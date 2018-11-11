package com.armageddon.luckysnake.model;

import android.graphics.Rect;

import com.armageddon.luckysnake.levels.Level;

public class Elephant extends Dot {

    int x = - 1000;
    int y = - 1000;
    int objectWidth;
    int objectHeight;
    int speed;
    private int direction;
    private Fruit fruit;

    public Elephant (Fruit fruit1, int speed, int objectWidth, int objectHeight){
        super(null,null);
        this.fruit = fruit1;
        this.speed = speed;
        this.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        this.objectHeight = (int) (objectHeight *  Level.scaleFactor);

    }

    public int getDirection() {
        return direction;
    }
    public void setSpeed (int speed) {
        this.speed = speed;
    }


    @Override
    public Rect getRectOfElement() {
        float scaleFactor = Level.scaleFactor;
        return new Rect(x -  (int) (50 * scaleFactor),
                y - objectHeight + (int) (50 * scaleFactor),
                x + objectWidth - (int) (50 * scaleFactor),
                y +  (int) (50 * scaleFactor));
    }

    public Rect getCrashRect () {
        float scaleFactor = Level.scaleFactor;
        return new Rect(x -  (int) (10 * scaleFactor),
                y + (int) (20 * scaleFactor),
                x  + (int) (50 * scaleFactor),
                y + (int) (40 * scaleFactor));
    }



    private void checkTargetAndGo () {
        if (x < fruit.getX()) {
            x += 1;
            direction = 0;
        }

        else if  (x  > fruit.getX()) {
            x -= 1;
            direction = 1;
        } else
            x = fruit.getX();

        if (y < fruit.getY()) {
            y += 1;
        }

        else if  (y > fruit.getY()) {
            y -= 1;
        }
        else y = fruit.getY();


    }


    @Override
    public void run() {
        if (!isContinue) {
            x = Level.gameField.right - objectWidth;
            y = Level.gameField.top;
        }
        while (!Thread.interrupted()) {
            try {
                pauseCheck();
                checkTargetAndGo();
                Thread.sleep(speed);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

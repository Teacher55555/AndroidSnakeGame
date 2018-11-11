package com.armageddon.luckysnake.model;

import android.graphics.Rect;
import com.armageddon.luckysnake.levels.Level;

public class Tornado extends Dot {

    int x = - 1000;
    int y = - 1000;
    int objectWidth;
    int objectHeight;
    int speed;
    int direction;
    Snake snake;
    boolean hide;


    public Tornado (Snake snake, int speed, int objectWidth, int objectHeight) {
        super(null, null);
        this.snake = snake;
        this.speed = speed;
        this.objectWidth = (int) (objectWidth * Level.scaleFactor);
        this.objectHeight = (int) (objectHeight * Level.scaleFactor);

    }

    public void setSpeed (int speed){
        this.speed = speed;
    }

        @Override
        public Rect getRectOfElement() {
            float scaleFactor = Level.scaleFactor;
            return new Rect(x -  (int) (50 * scaleFactor),
                    y - objectHeight + (int) (30 * scaleFactor),
                    x + objectWidth - (int) (50 * scaleFactor),
                    y +  (int) (30 * scaleFactor));
        }

        public Rect getCrashRect () {
            float scaleFactor = Level.scaleFactor;
            return new Rect(x +  (int) ( 15 * scaleFactor),
                    y + (int) (0 * scaleFactor),
                    x  + (int) (60 * scaleFactor),
                    y + (int) (30 * scaleFactor));
        }



        private void checkTargetAndGo () {
            if (x < snake.getX()) {
                x += 1;
                direction = 0;
            }

            else if  (x  > snake.getX()) {
                x -= 1;
                direction = 1;
            } else
                x = snake.getX();
            if (y < snake.getY()) {
                y += 1;
            }

            else if  (y > snake.getY()) {
                y -= 1;
            }
            else y = snake.getY();


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
                        x = Level.gameField.right - objectWidth;
                        y = Level.gameField.top;
                        isContinue = false;

                }
            }
        }
    }

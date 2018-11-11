package com.armageddon.luckysnake.model;

import android.graphics.Rect;
import com.armageddon.luckysnake.levels.Level;

public class FruitForGirl extends Fruit {


   private int phaze = 0;

   private boolean eaten;
   void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
    boolean isEaten() {
        return eaten;
    }
    public void setPhaze(int phaze) {
        this.phaze = phaze;
    }
    public int getPhaze() {
        return phaze;
    }

    public FruitForGirl (Snake Player1snake,
                         Snake Player2snake,
                         int sleeptime,
                         int count,
                         int frequency,
                         int objectWidth,
                         int objectHeight){
        super(Player1snake, Player2snake, sleeptime, count, frequency, objectWidth, objectHeight);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = sleeptime;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight *  Level.scaleFactor);


    }

    @Override
    public void setRestrictedArea(Rect restrictedArea) {
        super.setRestrictedArea(restrictedArea);
        makeDot();
    }

    public Rect getRectOfElement () {
        Rect rect = super.getRectOfElement();
         if (phaze == 1) {
           switch (snakePlayer1.getDirection()) {
               case "UP" :
                rect.set((int) (snakePlayer1.getX() - 10 * Level.scaleFactor),
                        (int) (snakePlayer1.getY() - 55 * Level.scaleFactor),
                        (int) (snakePlayer1.getX() - 10 * Level.scaleFactor + objectWidth),
                        (int) (snakePlayer1.getY() - 55 * Level.scaleFactor + objectHeight));
                break;

               case "DOWN" :
                rect.set((int) (snakePlayer1.getX() - 10 * Level.scaleFactor),
                         (int) (snakePlayer1.getY() + 30 * Level.scaleFactor),
                         (int) (snakePlayer1.getX() - 10 * Level.scaleFactor + objectWidth),
                         (int) (snakePlayer1.getY() + 30 * Level.scaleFactor + objectHeight));
                break;

               case "LEFT" :
                   rect.set((int) (snakePlayer1.getX() - 50 * Level.scaleFactor),
                           (int) (snakePlayer1.getY() - 10 * Level.scaleFactor),
                           (int) (snakePlayer1.getX() - 50 * Level.scaleFactor + objectWidth),
                           (int) (snakePlayer1.getY() - 10 * Level.scaleFactor + objectHeight));
                   break;

               case "RIGHT" :
                   rect.set((int) (snakePlayer1.getX() + 40 * Level.scaleFactor),
                           (int) (snakePlayer1.getY() - 10 * Level.scaleFactor),
                           (int) (snakePlayer1.getX() + 40 * Level.scaleFactor + objectWidth),
                           (int) (snakePlayer1.getY() - 10 * Level.scaleFactor + objectHeight));
                   break;
           }
        } else if (phaze == 2) {
            rect.set(x, y, x + objectWidth, y + objectHeight);
         }

         if (eaten) {
             return new Rect(-100, -100, -100,- 100);
         }
        return rect;
    }
}


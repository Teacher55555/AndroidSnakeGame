package com.armageddon.luckysnake.model;

import android.graphics.Rect;

import com.armageddon.luckysnake.common.Dimension;
import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.controller.ControllerPlay;
import com.armageddon.luckysnake.levels.Level;

import java.util.ArrayList;

/**
 * Created by Igor Gridin on 05.05.18.
 * <p>
 * This class is base abstract class for next game elements: fruit, coin, scissors, snail, stumps.
 * It's manages coordinates and frequency of respawn elements .
 **/

abstract public class Dot extends Thread implements GameElement {
    public Dimension coordinates = new Dimension(-1000 , -1000); //Keeps coordinates of the element;
    int sleepTime; //time, after which the element will change coordinates or will no show
    public int x; //random generated x position of the element
    public int y; //random generated y position of the element
    int startX = (int) (Level.gameField.left); // x = x + startX // Some elements (like stumps) mustn't show up in the specific range of the game coordinates
    int startY = (int) (Level.gameField.top); // y = y + startY // Some elements (like stumps) mustn't show up in the specific range of the game coordinates
    int edgeX = (int) (Level.gameField.right); // The edge of the game coordinate X
    int edgeY = (int) (Level.gameField.bottom); // The edge of the game coordinate Y
    public int objectWidth; // Object Width
    public int objectHeight; // Object Hight
    int count; // Every new coordinates increase the count by 1
    int frequency; // Each element has its own frequency level. If count % frequency == 0 => The element will get new coordinates
    int headOffset = 30;
    private boolean fruitCup;
    private boolean customFruit;
    boolean isContinue;

    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }
    boolean pause;
    void pauseCheck() throws InterruptedException {
        synchronized (ControllerPlay.class) {
            while (pause)
                ControllerPlay.class.wait();
        }
    }



    @Override
    public boolean isHide() {
        return false;
    }
    public void setPause(boolean pause) {
        this.pause = pause;
    }


    //Thus, sleepTime, count and frequency control how long the element will be displayed, not displayed, or when it will change the coordinates
    transient private Rect restrictedArea = new Rect(); // A special area where the element will not appear
    final Snake snakePlayer1; // It is necessary to check the new coordinates of the element so that the element does not appear on the snake's body
    private Snake snakePlayer2; // It is necessary to check the new coordinates of the element so that the element does not appear on the snake's body
    private int fruittype = 0;// This is for the random creation of fruit types: 0 - apple, 1 - cherry, 2 - banana, 3 - grapes, 4 - strawberry, 5 - kiwi, 6 = null

    @Override
    public void run() {// Generates new coordinates and wait sleepTime
        if (!isContinue) {
            makeDot();
        }
        while (!Thread.interrupted()) {
            try {
                pauseCheck();
                Thread.sleep(sleepTime);
                pauseCheck();
                makeDot();
            } catch (InterruptedException ex) {
                System.out.println("Fruit eat!");
                makeDot();
            }
        }
    }


   Dot(Snake snakePlayer1, Snake snakePlayer2) { //receives snake bodies
        this.snakePlayer1 = snakePlayer1;
        this.snakePlayer2 = snakePlayer2;
    }

    public void setRestrictedArea(Rect restrictedArea) { //setter
        this.restrictedArea = restrictedArea;
    }

    boolean checkRestrictedArea() { //Check if the element in the restricted area
        if (restrictedArea != null) {
            return new Rect(x, y, x + objectWidth, y + objectHeight).intersect(restrictedArea);
        }
        return false;
    }



    Dimension makeDot() { // generates new coordinates
        int fruitVariations = 7;
        if (count % frequency == 0) {
            do {
                isContinue = false;
                x = startX + (int) (Math.random() * (edgeX - startX - objectWidth));
                y = startY + (int) (Math.random() * (edgeY - startY - objectHeight));
            } while (newFruitInBodyCheck() || checkRestrictedArea());
        } else {
            count++;
            return coordinates = new Dimension(-1000, -1000); // return coordinates outside the game area
        }
        count++;

        System.out.println(customFruit);
        if (!customFruit) {
            if (fruitCup) {
                fruittype = 7;
            } else {
                fruittype = (int) (Math.random() * fruitVariations); // generate random fruitType
            }
        }
        return coordinates = new Dimension(x, y);
    }

    public void setFruittype(int fruittype) {
        customFruit = true;
        this.fruittype = fruittype;
    }

    public void setCoordinates(Dimension coordinates) {
        this.coordinates = coordinates;
    }
    public Dimension getCoordinates() {
        return this.coordinates;
    } // Getter
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getObjectHeight() {
        return objectHeight;
    }
    public int getObjectWidth() {
        return objectWidth;
    }

    @Override
    public Rect getRectOfElement() {
        return new Rect(coordinates.getX(),
                coordinates.getY(),
                coordinates.getX() + objectWidth,
                coordinates.getY() + objectHeight);
    }

    public int getFruittype() {
        return fruittype;
    } // Getter


    boolean newFruitInBodyCheck() {
        if (snakePlayer1 == null) {
            return false;
        }
        synchronized (Snake.class) {
            // Checks the new coordinates of the element so that the element does not appear on the snake's body
//            ArrayList<Integer[]> bodyCopy = new ArrayList<>(snakePlayer1.getBody());
            for (Integer[] body : snakePlayer1.getBody()) {
                Rect bodyRec = new Rect(body[0], body[1],
                        body[0] + (int) (headOffset * Level.scaleFactor),
                        body[1] + (int) (headOffset * Level.scaleFactor));
                Rect dot = new Rect(getX(),
                        getY(),
                        getX() + objectWidth,
                        getY() + objectHeight);
//
                if (bodyRec.intersect(dot)) {
                    return true;
                }
            }
            return false;
        }
    }
}



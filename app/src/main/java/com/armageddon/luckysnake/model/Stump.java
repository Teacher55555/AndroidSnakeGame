package com.armageddon.luckysnake.model;

import android.graphics.Rect;

import com.armageddon.luckysnake.levels.Level;

public class Stump extends Dot {
    //     static ArrayList<Stump> stumps;
    private Stump anotherStump1;
    private Stump anotherStump2;
    private Stump anotherStump3;
    private Stump anotherStump4;

    public Stump(Snake Player1snake,
                 Snake Playser2snake,
                 int sleeptime,
                 int count,
                 int frequency,
                 int objectWidth,
                 int objectHeight) {
        super(Player1snake, Playser2snake);
        super.sleepTime = sleeptime;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight * Level.scaleFactor);
        super.frequency = frequency;
        super.count = count;
        super.headOffset = 100;
        super.setRestrictedArea(new Rect(Level.gameField.left,
                Level.gameField.top,
                Level.gameField.left + 100 * (int) Level.scaleFactor,
                Level.gameField.top + 100 * (int) Level.scaleFactor));
    }

    public void setAnotherStump (Stump stump1, Stump stump2, Stump stump3, Stump stump4) {
        anotherStump1 = stump1;
        anotherStump2 = stump2;
        anotherStump3 = stump3;
        anotherStump4 = stump4;
    }

    @Override
    boolean checkRestrictedArea() {
        Rect thisStump = new Rect(x,y, x + objectWidth, y + objectHeight);
        Rect anStump1 = new Rect(anotherStump1.x, anotherStump1.y,
                anotherStump1.x + objectWidth, anotherStump1.y + objectHeight);
        Rect anStump2 = new Rect(anotherStump2.x, anotherStump2.y,
                anotherStump2.x + objectWidth, anotherStump2.y + objectHeight);
        Rect anStump3 = new Rect(anotherStump3.x, anotherStump3.y,
                anotherStump3.x + objectWidth, anotherStump3.y + objectHeight);
        Rect anStump4 = new Rect(anotherStump4.x, anotherStump4.y,
                anotherStump4.x + objectWidth, anotherStump4.y + objectHeight);


        return  (thisStump.intersect(anStump1)
                || thisStump.intersect(anStump2) || thisStump.intersect(anStump3)
                || thisStump.intersect(anStump4) || super.checkRestrictedArea());

    }

    @Override
    public Rect getRectOfElement() {
        int x = getCoordinates().getX();
        int y = getCoordinates().getY();
        float scaleFactor = Level.scaleFactor;
        return new Rect(x + (int) (55 *  scaleFactor) ,
                y + (int) (36 * scaleFactor), x + objectWidth - (int) (57 * scaleFactor),
                y + objectHeight - (int) (15 * scaleFactor));
    }
}
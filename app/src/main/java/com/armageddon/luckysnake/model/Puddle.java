package com.armageddon.luckysnake.model;

import android.graphics.Rect;
import com.armageddon.luckysnake.levels.Level;

public class Puddle extends Dot implements GameElement {

    private static int counter;
    private final int id = counter++;
    private boolean spark;
    private Puddle anotherPuddle1;
    private Puddle anotherPuddle2;
    private Puddle anotherPuddle3;
    private Puddle anotherPuddle4;
    private Puddle anotherPuddle5;
    private Puddle anotherPuddle6;
    private Puddle anotherPuddle7;
    private Puddle anotherPuddle8;


    public Puddle (Snake Player1snake,
                   Snake Player2snake,
                   int sleeptime,
                   int count,
                   int frequency,
                   int objectWidth,
                   int objectHeight){
        super(Player1snake,Player2snake);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = sleeptime;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight *  Level.scaleFactor);
    }

    public void setAnotherPuddle (Puddle puddle1,
                                  Puddle puddle2,
                                  Puddle puddle3,
                                  Puddle puddle4,
                                  Puddle puddle5,
                                  Puddle puddle6,
                                  Puddle puddle7,
                                  Puddle puddle8) {
        anotherPuddle1 = puddle1;
        anotherPuddle2 = puddle2;
        anotherPuddle3 = puddle3;
        anotherPuddle4 = puddle4;
        anotherPuddle5 = puddle5;
        anotherPuddle6 = puddle6;
        anotherPuddle7 = puddle7;
        anotherPuddle8 = puddle8;
    }

    public boolean isSpark() {
        return spark;
    }
    public void setSpark(boolean spark) {
        this.spark = spark;
    }
    public int getNumber() {
        return id;
    }

    @Override
    boolean newFruitInBodyCheck() {
        return false;
    }


    public Rect getCrashRect () {
        int x = coordinates.getX();
        int y = coordinates.getY();
        float scaleFactor = Level.scaleFactor;
        return new Rect(x + (int) (10 * scaleFactor),
                y + (int) (10 * scaleFactor),
                x + objectWidth - (int) (10 * scaleFactor),
                y + objectHeight - (int) (10 * scaleFactor));
    }

    @Override
    boolean checkRestrictedArea() {
        Rect thisPuddle = new Rect(x,y, x + objectWidth, y + objectHeight);
        Rect anPuddle1 = new Rect(anotherPuddle1.x, anotherPuddle1.y,
                anotherPuddle1.x + objectWidth, anotherPuddle1.y + objectHeight);
        Rect anPuddle2 = new Rect(anotherPuddle2.x, anotherPuddle2.y,
                anotherPuddle2.x + objectWidth, anotherPuddle2.y + objectHeight);
        Rect anPuddle3 = new Rect(anotherPuddle3.x, anotherPuddle3.y,
                anotherPuddle3.x + objectWidth, anotherPuddle3.y + objectHeight);
        Rect anPuddle4 = new Rect(anotherPuddle4.x, anotherPuddle4.y,
                anotherPuddle4.x + objectWidth, anotherPuddle4.y + objectHeight);
        Rect anPuddle5 = new Rect(anotherPuddle5.x, anotherPuddle5.y,
                anotherPuddle5.x + objectWidth, anotherPuddle5.y + objectHeight);
        Rect anPuddle6 = new Rect(anotherPuddle6.x, anotherPuddle6.y,
                anotherPuddle6.x + objectWidth, anotherPuddle6.y + objectHeight);
        Rect anPuddle7 = new Rect(anotherPuddle7.x, anotherPuddle7.y,
                anotherPuddle7.x + objectWidth, anotherPuddle7.y + objectHeight);
        Rect anPuddle8 = new Rect(anotherPuddle8.x, anotherPuddle8.y,
                anotherPuddle8.x + objectWidth, anotherPuddle8.y + objectHeight);
        Rect restrictedArea = new Rect(Level.gameField.left, Level.gameField.top,
                Level.gameField.left +  (int) (70 * Level.scaleFactor),
                Level.gameField.top +  (int) (70 * Level.scaleFactor));

        return  (thisPuddle.intersect(anPuddle1) || thisPuddle.intersect(anPuddle2)
                || thisPuddle.intersect(anPuddle3) || thisPuddle.intersect(anPuddle4)
                || thisPuddle.intersect(anPuddle5) || thisPuddle.intersect(anPuddle6)
                || thisPuddle.intersect(anPuddle7) || thisPuddle.intersect(anPuddle8)
                || restrictedArea.intersect(thisPuddle));


    }

}


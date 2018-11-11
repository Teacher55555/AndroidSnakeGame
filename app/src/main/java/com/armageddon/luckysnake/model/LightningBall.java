package com.armageddon.luckysnake.model;

import android.graphics.Rect;
import android.graphics.RectF;

import com.armageddon.luckysnake.common.Dimension;
import com.armageddon.luckysnake.levels.Level;

public class LightningBall extends Dot implements GameElement {
    private float [] targetRectf = new float[4];
    transient private RectF crashRectF;
    private int speed;
    private int x = - 1000;
    private int y = 0;
    private int stepY = (int) (2 * Level.scaleFactor);
    private int targetX;
    private int targetY;


    public LightningBall (Snake Player1snake,
                          Snake Player2snake,
                          int speed,
                          int count,
                          int frequency,
                          int objectWidth,
                          int objectHeight){
        super(Player1snake,Player2snake);
        this.speed = speed;
        super.count = count;
        super.frequency = frequency;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight *  Level.scaleFactor);
    }

   private void setTargetRectfNull () {
        for (int i = 0; i < targetRectf.length; i++) {
            targetRectf[i] = 0;
        }
    }

    @Override
    Dimension makeDot() {
        if (count % frequency == 0) {
            do {
                isContinue = false;
                targetX = startX + (int) (Math.random() * (edgeX - startX - objectWidth));
                targetY = startY + (int) (Math.random() * (edgeY - startY - objectHeight));
                x = targetX;
                y = 0;
                targetRectf[0] = targetX;
                targetRectf[1] = targetY;
                targetRectf[2] = targetX + objectWidth - 5 * Level.scaleFactor;
                targetRectf[3] = targetY + objectHeight / 2 - 5 * Level.scaleFactor;
            }
            while (checkRestrictedArea());

        } else {
            count++;
            targetX = - 1000;
            targetY = 0;
            return coordinates = new Dimension(-1000, -1000); // return coordinates outside the game area
        }
        count++;
        return coordinates = new Dimension(x, y);
    }

    @Override
    boolean checkRestrictedArea() {
        Rect restrictedArea = new Rect(Level.gameField.left, Level.gameField.top,
                Level.gameField.left +  (int) (70 * Level.scaleFactor),
                Level.gameField.top +  (int) (70 * Level.scaleFactor));
        return restrictedArea.contains(targetX,targetY);
    }

    public void run() {
        crashRectF = new RectF();
        if (!isContinue) {
            makeDot();
        }
        while (!Thread.interrupted()) {
            try {
                pauseCheck();

                if (y >= targetY - objectHeight/2) {
                    crashRectF = new RectF(targetRectf[0],targetRectf[1],targetRectf[2],targetRectf[3]);
                    Thread.sleep(3000);
                    crashRectF.set(0,0,0,0);
                    setTargetRectfNull();
                    x = -100;
                    y = -100;
                    makeDot();
                }

                y += stepY;


                Thread.sleep(speed);
            }
            catch (InterruptedException ex) {
                if (crashRectF != null) {
                    crashRectF.set(0, 0, 0, 0);
                }
                setTargetRectfNull();
                x = -100;
                y = -100;
                makeDot();
            }
        }
    }

    public RectF getCrashRect () {
        if (crashRectF == null) {
            return new RectF();
        }
        return crashRectF;
    }

    @Override
    public Rect getRectOfElement() {
        return new Rect(x,y, x + objectWidth, y + objectHeight);
    }
    public RectF getTarget () {
        return  new RectF (targetRectf[0],targetRectf[1],targetRectf[2],targetRectf[3]);
    }

}

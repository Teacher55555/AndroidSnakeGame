
package com.armageddon.luckysnake.model;

import android.graphics.Rect;
import com.armageddon.luckysnake.common.Dimension;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.levels.Level;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Mole extends Dot {


    public static int count;
    private final int id;
    public int x = - 1000 ,y = - 1000;
    private int stepX;
    private int getOutXPoint;
    private int soilWidth;
    private boolean revers;
    private boolean moleShow;
    private transient Music music;
    private int waitTime;
    boolean isContinue;

    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }
    public void setMusic(Music music) {
        this.music = music;
    }
    private ArrayList<Integer[]> soilPoints = new ArrayList<>();
    public ArrayList<Integer[]> getSoilPoints() {
        synchronized (Mole.class) {
            return soilPoints;
        }
    }
    public boolean isMoleShow() {
        return moleShow;
    }
    public Mole (Snake Player1snake,
                 Snake Player2snake,
                 int speed,
                 int waitTime,
                 boolean revers,
                 int count,
                 int frequency,
                 int soilObjectWidth,
                 int objectWidth,
                 int objectHeight,
                 Music music){
        super(Player1snake,Player2snake);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = speed;
        this.soilWidth = (int) (soilObjectWidth *  Level.scaleFactor);
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight *  Level.scaleFactor);
        this.waitTime = waitTime;
        this.revers = revers;
        this.music = music;
        if (count == 3) {
            count = 0;
        }
        id = ++ count;
        digPlay();
        digPause();
    }
    public void setSpeed (int speed) {
        this.sleepTime = speed;
    }
    private void molePlay (){
       switch (id) {
           case 1: music.mole1();break;
           case 2: music.mole2();break;
       }
    }
    private void digPlay (){
        switch (id) {
            case 1: music.dig1();break;
            case 2: music.dig2();break;
        }
    }
    private void digPause (){
        switch (id) {
            case 1: music.dig1Pause();break;
            case 2: music.dig2Pause();break;
        }
    }

    @Override
    boolean newFruitInBodyCheck() {
        return false;
    }

    @Override
    Dimension makeDot() {
        if (count % frequency == 0) {
            isContinue = false;
            ArrayList <Integer> steps = new ArrayList<>();
                if (!revers) {
                    x = Level.gameField.left;
                    stepX = soilWidth/3;
                    for (int i = Level.gameField.left + stepX; i < Level.gameField.right - objectWidth; i+= stepX) {
                        steps.add(i);
                    }
                    getOutXPoint = steps.get((int) (Math.random() * (steps.size())));
                } else {
                    x = Level.gameField.right - soilWidth;
                    stepX = - soilWidth/3;
                    for (int i = x + stepX; i > Level.gameField.left; i+= stepX) {
                        steps.add(i);
                    }
                    getOutXPoint = steps.get((int) (Math.random() * (steps.size())));
                }
                y = (int) (Level.gameField.top + Math.random() *
                        (Level.gameField.bottom - Level.gameField.top - objectHeight - 0 * Level.scaleFactor));
                digPlay();
                synchronized (Mole.class) {
                    soilPoints.add(new Integer[]{x, y});
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
        return new Rect(x + (int) (0 *  scaleFactor),
                y - (int) (65 * scaleFactor),
                x + objectWidth - (int) (0 * scaleFactor),
                y + objectHeight - (int) (65 * scaleFactor));
    }

    public Rect getCrashRect () {
        if (!moleShow) {
            return new Rect();
        }
        float scaleFactor = Level.scaleFactor;
        return new Rect(x + (int) (22 *  scaleFactor),
                y - (int) (15 * scaleFactor),
                x + objectWidth - (int) (22 * scaleFactor),
                y + objectHeight - (int) (75 * scaleFactor));
    }

    public Rect getMoleRect () {
        if (!moleShow) {
            return new Rect();
        }
        float scaleFactor = Level.scaleFactor;
        return new Rect(x + (int) (50 *  scaleFactor),
                y - (int) (65 * scaleFactor),
                x + objectWidth - (int) (60 * scaleFactor),
                y + objectHeight - (int) (75 * scaleFactor));
    }

    @Override
    public void run() {
        if (!isContinue) {
            soilPoints.clear();
            makeDot();
        }
        while (!Thread.interrupted()) {
            try {
                if (moleShow) {
                    interrupt();
                }
                pauseCheck();
                if (isContinue) {
                    digPlay();
                    isContinue = false;
                }
                x += stepX;
                synchronized (Mole.class) {
                    soilPoints.add(new Integer[]{x, y});
                }

                if (x == getOutXPoint) {
                    interrupt();
                }

                Thread.sleep(sleepTime);
            }
            catch (InterruptedException ex) {
                digPause();
                molePlay();
                moleShow = true;
                try {
                    TimeUnit.SECONDS.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    pauseCheck();
                } catch (InterruptedException e) {e.printStackTrace();}
                moleShow = false;
                synchronized (Mole.class) {
                    soilPoints.clear();
                }
                makeDot();

            }
        }
    }
}




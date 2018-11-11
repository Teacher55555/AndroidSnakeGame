package com.armageddon.luckysnake.model;

import android.graphics.Rect;
import com.armageddon.luckysnake.levels.Level;

public class Rocket extends Dot implements GameElement {
    private boolean hide = true;


    public Rocket (Snake Player1snake, int sleeptime, int count, int frequency, int objectWidth, int objectHeight){
        super(Player1snake,null);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = sleeptime;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight *  Level.scaleFactor);
        super.pause = true;
        x = - 1000;
        y = - 1000;

    }

    public void makeRocket () {
        super.makeDot();
    }

    @Override
    public Rect getRectOfElement() {
        return new Rect(x, y, x + objectWidth, y + objectHeight);
    }

    private void hideCheck () throws InterruptedException {
        synchronized (Rocket.class) {
            while (hide)
                Rocket.class.wait();
        }
    }

    @Override
    public boolean isHide() {
        return hide;
    }
    public void hide (boolean hide) {
        this.hide = hide;
    }
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                hideCheck();
                makeDot();
                pauseCheck();
                if (!hide) {
                    Thread.sleep(sleepTime);
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }



}

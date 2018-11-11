package com.armageddon.luckysnake.model;

import com.armageddon.luckysnake.common.Dimension;
import com.armageddon.luckysnake.levels.Level;

public class Fruits extends Dot {

    private boolean hide;

    public Fruits (Snake Player1snake,
                   Snake Player2Snake,
                   int sleeptime,
                   int count,
                   int frequency,
                   int objectWidth,
                   int objectHeight ){
        super(Player1snake, Player2Snake);
        super.count = count;
        super.sleepTime = sleeptime;
        super.frequency = frequency;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight * Level.scaleFactor);
    }

   private void hideCheck () throws InterruptedException {
        synchronized (Fruits.class) {
            while (hide)
                Fruits.class.wait();
        }
    }

    @Override
    public boolean isHide() {
        return hide;
    }
    public void hide (boolean hide) {
        this.hide = hide;
    }

    @Override
    public void run() {
        if (!isContinue) {
            makeDot();
        }
        while (!Thread.interrupted()) {
            try {
                hideCheck();
                pauseCheck();
                Thread.sleep(sleepTime);
                pauseCheck();
                makeDot();
                pauseCheck();
                hideCheck();

            } catch (Exception ex) {
                coordinates = new Dimension(-100,-100);
                super.pause = true;
            }
        }
    }
}


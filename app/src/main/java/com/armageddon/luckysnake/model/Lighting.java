package com.armageddon.luckysnake.model;

import android.graphics.Rect;

import com.armageddon.luckysnake.common.Dimension;
import com.armageddon.luckysnake.levels.Level;

public class Lighting extends Dot implements GameElement {
    int x = -1000;
    int y = -1000;

    public Lighting (Snake Player1snake,Snake Player2snake,
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

    @Override
    public Rect getRectOfElement() {
        return new Rect(x, y,x + objectWidth, y + objectHeight);
    }

    @Override
    Dimension makeDot() {
        if (count % frequency == 0) {
            isContinue = false;
            x = (int) (Level.gameField.left + Math.random() *
                    (Level.gameField.right - Level.gameField.left - objectWidth));
            y = (int) (Math.random() * -300);

        } else {
            x = - 1000;
            y = - 1000;
            count++;
            return coordinates = new Dimension(-1000, -1000); // return coordinates outside the game area
        }
        count++;
        return coordinates = new Dimension(x, y);
    }
}




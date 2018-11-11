package com.armageddon.luckysnake.model;

import com.armageddon.luckysnake.levels.Level;

public class Snail extends Dot {
    public Snail (Snake Player1snake,
                  Snake Player2Snake,
                  int sleeptime,
                  int count,
                  int frequency,
                  int objectWidth,
                  int objectHeight){
        super(Player1snake, Player2Snake);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = sleeptime;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight * Level.scaleFactor);
    }

}

package com.armageddon.luckysnake.model;


import com.armageddon.luckysnake.levels.Level;

public class Fruit extends Dot {

    public Fruit (Snake Player1snake,Snake Player2snake, int sleeptime, int count, int frequency, int objectWidth, int objectHeight){
        super(Player1snake,Player2snake);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = sleeptime;
        super.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        super.objectHeight = (int) (objectHeight *  Level.scaleFactor);
    }

    public Fruit (Snake snake, Fruit fruit) {
        super(snake,null);
        this.count = fruit.count;
        this.frequency = fruit.frequency;
        this.sleepTime = fruit.sleepTime;
        this.objectWidth = fruit.objectWidth;
        this.objectHeight = fruit.objectHeight;
    }


}
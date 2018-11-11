package com.armageddon.luckysnake.common;

import java.io.Serializable;

public class Dimension implements Serializable{
    private int x,y;
    public Dimension (int x, int y) {
        this.x = x;
        this.y = y;
    }

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
}

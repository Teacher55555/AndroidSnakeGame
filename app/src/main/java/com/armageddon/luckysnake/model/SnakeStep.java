package com.armageddon.luckysnake.model;

public interface SnakeStep {
    void setStepX (int stepX);
    void setStepY (int stepY);
    void setBonusLife ();
    String getDirection ();
}

package com.armageddon.luckysnake.model;

import android.graphics.Rect;
import java.io.Serializable;

public interface GameElement extends Serializable {
    Rect getRectOfElement ();
    void setPause (boolean pause);
    void interrupt();
    boolean isHide ();
}

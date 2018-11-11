package com.armageddon.luckysnake.levels;

import com.armageddon.luckysnake.view.Panel;

public interface Ilevel {
   void setGameOnPause ();
   int getLevel ();
   void stopAllMusic ();
   void snakeInterrupt();
   Panel getPanel();
}

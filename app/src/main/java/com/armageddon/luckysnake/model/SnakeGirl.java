package com.armageddon.luckysnake.model;

import android.graphics.Point;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.levels.Level;
import com.armageddon.luckysnake.view.Panel;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.util.concurrent.TimeUnit;

public class SnakeGirl extends Snake {

  private FruitForGirl fruit1;
  private FruitForGirl fruit2;
  private FruitForGirl fruit3;
  private FruitForGirl fruit4;
  private MainActivity activity;
  private Music music;
  private int targetsCount;
  private PanelGamePlay panel;

  private Point thanksPoint = new Point();
  private Point toBeContinuePoint = new Point();

   public SnakeGirl (int objectWidth,
                     int objectHeight,
                     FruitForGirl fruit1,
                     FruitForGirl fruit2,
                     FruitForGirl fruit3,
                     FruitForGirl fruit4,
                     Music music,
                     PanelGamePlay panel,
                     MainActivity activity) {
        super(null, objectWidth, objectHeight);
        this.fruit1 = fruit1;
        this.fruit2 = fruit2;
        this.fruit3 = fruit3;
        this.fruit4 = fruit4;
        this.music = music;
        this.panel = panel;
        this.activity = activity;
        thanksPoint.set((int) (Level.gameField.left + 80 * Level.scaleFactor),
                (int) (Level.gameField.top + 140 * Level.scaleFactor));
        toBeContinuePoint.set((int) (Level.gameField.left + 60 * Level.scaleFactor),
               (int) (Level.gameField.top + 140 * Level.scaleFactor));
    }

    private void checkTargetAndGo() {
       int targetX;
       int targetY;
       if (!fruit1.isEaten()) {
           targetX = fruit1.x;
           targetY = fruit1.y;
       } else if (!fruit2.isEaten()) {
           targetX = fruit2.x;
           targetY = fruit2.y;
       } else if (!fruit3.isEaten()) {
           targetX = fruit3.x;
           targetY = fruit3.y;
       } else if (!fruit4.isEaten()) {
           targetX = fruit4.x;
           targetY = fruit4.y;
       } else if (targetsCount == 4) {
           targetX = fruit4.x;
           targetY = (int) (Level.gameField.top + 360 * Level.scaleFactor);
       } else {
           targetX = (int) (Level.gameField.right - 580 * Level.scaleFactor);
           targetY = (int) (Level.gameField.top + 360 * Level.scaleFactor);
       }

       if (x != targetX) {
           stepY = 0;
           if (x > targetX) {
               stepX = (int) (-20 * Level.scaleFactor);
           } else {
               stepX = (int) (20 * Level.scaleFactor);
           }
       } else if (y != targetY && targetsCount != 5) {
           stepX = 0;
           if (y > targetY) {
               stepY = (int) (-20 * Level.scaleFactor);
           } else {
               stepY = (int) (20 * Level.scaleFactor);
           }
       } else {
           targetsCount++;
           switch (targetsCount) {
               case 1: fruit1.setEaten(true); music.eat(); break;
               case 2: fruit2.setEaten(true); music.eat(); setOnTheBlanket (true); break;
               case 3: fruit3.setEaten(true); music.eat(); break;
               case 4: fruit4.setEaten(true); music.eat(); break;
               case 6: stepX = 0; stepY = 0; interrupt();
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           Thread.sleep(3000);
                           panel.setText("Thank you for playing!", thanksPoint,
                                   90 * Level.scaleFactor, true);
                           Thread.sleep(3000);
                           panel.setText("Music: www.bensound.com", toBeContinuePoint,
                                   80 * Level.scaleFactor, true);
                           Thread.sleep(3000);
                           panel.setText("To be continued...", toBeContinuePoint,
                                   120 * Level.scaleFactor, true);
                           Thread.sleep(3000);
                           music.gameMusicStop();
                           activity.playGame(-2);
                       } catch (InterruptedException ex) {
                           ex.printStackTrace();
                       }
                   }
               }).start();
               break;
           }
       }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                    pauseCheck();
                    checkTargetAndGo();
                    x += stepX;
                    y += stepY;
                    checkDirection();
                    moveBody(x, y);
                    TimeUnit.MILLISECONDS.sleep(snakeSpeed);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                music.kiss();
                panel.showLove();
            }
        }
    }
}

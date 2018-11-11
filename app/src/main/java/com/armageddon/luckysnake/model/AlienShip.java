package com.armageddon.luckysnake.model;


import android.graphics.Rect;
import android.graphics.RectF;

import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.levels.Level;
import com.armageddon.luckysnake.view.PanelGamePlay;

import pl.droidsonroids.gif.GifDrawable;

public class AlienShip extends Dot implements GameElement {

    public static int count;
    private final int id = ++count;
    private int shipX;
    private int shipY;
    private int targetX = - 500;
    private int targetY = - 500;
    private int waitTime;
    private transient Music music;
    private int targetZoneWidth;
    private int targetZoneHight;
    private int targetOffset;
    int objectWidth;
    int objectHeight;
    int speed;
    Snake snake;
    private boolean fire;
    private boolean homing;
    private boolean move;
    private boolean homingAndFire;
    private GifDrawable explosion;
    private transient PanelGamePlay panel;

    public void setPanel(PanelGamePlay panel) {
        this.panel = panel;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public int getShipX() {
        return shipX;
    }

    public int getShipY() {
        return shipY;
    }

   private void explosionReset () {
        switch (id) {
            case 1 : panel.resetExplosion1(); break;
            case 2 : panel.resetExplosion2(); break;
            case 3 : panel.resetExplosion3(); break;
            case 4 : panel.resetExplosion4(); break;
            case 5 : panel.resetExplosion5(); break;
        }
    }

   private void explosionStart () {
        switch (id) {
            case 1 : panel.startExplosion1(); break;
            case 2 : panel.startExplosion2(); break;
            case 3 : panel.startExplosion3(); break;
            case 4 : panel.startExplosion4(); break;
            case 5 : panel.startExplosion5(); break;
        }
    }

    public AlienShip (Snake snake,
                      int speed,
                      int waitBeforFire,
                      int targetOffset,
                      int targetZoneWidth,
                      int targetZoneHight,
                      int objectWidth,
                      int objectHeight,
                      PanelGamePlay panel,
                      Music music){
        super(null,null);
        this.snake = snake;
        this.waitTime = waitBeforFire;
        this.speed = speed;
        this.targetOffset = targetOffset;
        this.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        this.objectHeight = (int) (objectHeight *  Level.scaleFactor);
        this.targetZoneWidth = (int) (targetZoneWidth *  Level.scaleFactor);
        this.targetZoneHight = (int) (targetZoneHight *  Level.scaleFactor);
        this.shipY = - objectHeight - (int) (20  * Level.scaleFactor) ;
        this.shipX = (int) (Level.gameField.left + 400 * Level.scaleFactor);
        this.panel = panel;
        this.music = music;
    }

    public int getNumber() {
        return id;
    }
    public void setExplosion(GifDrawable explosion) {
        this.explosion = explosion;
    }
    public GifDrawable getExplosion() {
        return explosion;
    }
    public boolean isFire() {
        return fire;
    }
    public boolean isHoming() {
        return homing;
    }
    public void setTargetOffset(int targetOffset) {
        this.targetOffset = targetOffset;
    }
    public void setWaitBefireFire(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        if (!isContinue) {
            music.alienShip();
        }
        if (!move) {
            while (shipY < Level.gameField.bottom / 2) {
                shipY += (int) (2 * Level.scaleFactor);
                try {
                    pauseCheck();
                    Thread.sleep(32);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        homingAndFire = true;
        while (!Thread.interrupted()) {
            try {
                pauseCheck();
                if (!homingAndFire)
                setNewDestinationAndMove();
                hominAndFire();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
                interrupt();
            }
        }
    }

    public float [] getHomingLine () {
        float [] points = new float[4];
        points[0] = shipX + objectWidth/2;
        points[1] = shipY + objectHeight - 10 * Level.scaleFactor;
        points[2] = targetX;
        points[3] = targetY;
        return points;
    }


   private void setNewDestinationAndMove () {
        move = true;

       int destinationX = startX + (int) (Math.random() * (edgeX - startX - objectWidth));
       int destinationY = startY + (int) (Math.random() * (edgeY - startY - objectHeight));

        while (shipX != destinationX && shipY != destinationY) {
            if (shipX < destinationX) {
                shipX += 1;
            } else if (shipX > destinationX) {
                shipX -= 1;
            } else
                shipX = destinationX;
//
            if (shipY < destinationY) {
                shipY += 1;
            } else if (shipY > destinationY) {
                shipY -= 1;
            } else shipY = destinationY;

            try {
                pauseCheck();
                Thread.sleep(speed);
            }catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }

   private void hominAndFire () {
        homingAndFire = true;
        if (!homing) {
            switch (snake.getDirection()) {
                case "UP":
                    targetX = snake.getX();
                    targetY = snake.getY() - (int) (targetOffset * Level.scaleFactor);
                    if (targetY < Level.gameField.top + targetZoneHight) {
                        targetY = Level.gameField.top + targetZoneHight / 2;
                    }
                    break;
                case "DOWN":
                    targetX = snake.getX();
                    targetY = snake.getY() + (int) (targetOffset * Level.scaleFactor);
                    if (targetY > Level.gameField.bottom - targetZoneHight) {
                        targetY = Level.gameField.bottom - targetZoneHight / 2;
                    }
                    break;
                case "LEFT":
                    targetX = snake.getX() - (int) (targetOffset * Level.scaleFactor);
                    targetY = snake.getY();
                    if (targetX < Level.gameField.left + targetZoneWidth) {
                        targetX = Level.gameField.left + targetZoneWidth / 2;
                    }
                    break;
                case "RIGHT":
                    targetX = snake.getX() + (int) (targetOffset * Level.scaleFactor);
                    targetY = snake.getY();
                    if (targetX > Level.gameField.right - targetZoneWidth) {
                        targetX = Level.gameField.right - targetZoneWidth / 2;
                    }
                    break;
            }
        }
        try {
            fire = false;
            int snakeLifes = snake.getLifeCount();
            pauseCheck();
            music.laserCharge();
            explosionReset();
            homing = true;
            pauseCheck();
            Thread.sleep(waitTime + (int) (Math.random() * 1500));
            pauseCheck();
            homing = false;
            pauseCheck();
            if (snakeLifes <= snake.getLifeCount()) {
                fire = true;
                music.laserFire();
                explosionStart();
                Thread.sleep(700);
                fire = false;
                Thread.sleep(1700);
            }
        } catch (InterruptedException ex) {ex.printStackTrace();}
        homingAndFire = false;
        fire = false;

    }

    public RectF getCrashRectF () {
        return new RectF(targetX - targetZoneWidth/2,
                targetY - targetZoneHight/2 + (int) (0 * Level.scaleFactor),
                targetX + targetZoneWidth/2 , targetY + targetZoneHight/2);
    }

    public RectF getBeam () {
        return new RectF(shipX + objectWidth/2  - 10 * Level.scaleFactor,
                shipY + objectHeight - 25 * Level.scaleFactor,
                shipX + objectWidth/2 - 10 * Level.scaleFactor + 20 * Level.scaleFactor,
                shipY + objectHeight - 25 * Level.scaleFactor + 20 * Level.scaleFactor);
    }

    @Override
    public Rect getRectOfElement() {
        return new Rect(shipX, shipY, shipX + objectWidth, shipY + objectHeight);
    }

    public void setSpeed (int speed) {
        this.speed = speed;
    }
}

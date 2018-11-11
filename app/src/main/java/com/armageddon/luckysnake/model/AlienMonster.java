package com.armageddon.luckysnake.model;


import android.graphics.Point;
import android.graphics.Rect;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.levels.Level;
import java.io.Serializable;

public class AlienMonster extends Dot implements GameElement {

    public int monsterX = - 1000;
    public int monsterY = - 1000;
    private int waitTime;
    private transient Music music;
    private int teleportX;
    private int teleportY;
    private int teleportAlpha;
    private int monstrAlpha;
    private int fireWidth;
    private int fireHeight;
    int objectWidth;
    int objectHeight;
    int speed;
    private int fireTime;
    private int phase; // 0 - teleport off, 1 - teleport on, 2 - monstr on, teleport off, 3 - monstr move;
    Snake snake;
    private boolean teleport;
    private int direction = 0;
    private Fire fire = new Fire();

    class Fire extends Thread implements Serializable {
        boolean fire;
        boolean isFire() {
            return fire;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                    try {
                        if (!pause) {
                            music.alienMonster();
                            fire = true;
                            pauseCheck();
                            Thread.sleep(fireTime);
                            fire = false;
                            pauseCheck();
                            Thread.sleep(waitTime);

                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
            }
        }
    }


    public int getTeleportAlpha () {
        return teleportAlpha;
    }

    public int getMonstrAlpha () {
        return monstrAlpha;
    }

    public AlienMonster (int speed,
                         int waitBeforFire,
                         int fireTime,
                         int objectWidth,
                         int objectHeight,
                         int fireWidth,
                         int fireHeight,
                         Music music){
        super(null,null);
        this.waitTime = waitBeforFire;
        this.fireTime = fireTime;
        this.speed = speed;
        this.objectWidth = (int) (objectWidth *  Level.scaleFactor);
        this.objectHeight = (int) (objectHeight *  Level.scaleFactor);
        this.fireWidth = (int) (fireWidth *  Level.scaleFactor);
        this.fireHeight = (int) (fireHeight *  Level.scaleFactor);
        this.music = music;
        teleportX = (int) (Level.gameField.left + 400 * Level.scaleFactor); // + objectWidth;
        teleportY = (int) (Level.gameField.top + 250 * Level.scaleFactor); // + objectHeight;

    }

    public void setMusic(Music music) {
        this.music = music;
    }
    public Point teleportPoint () {
        return new Point(teleportX, teleportY);
    }
    public int getDirection() {
        return direction;
    }
    public boolean isFire() {
        return fire.isFire();
    }

    @Override
    public void run() {
        try {
            if (phase == 0) {
                music.alienShip();
                while (teleportAlpha < 250) {
                    teleportAlpha++;
                    pauseCheck();
                    Thread.sleep(10);
                }
                phase = 1;
            }

            if (phase == 1) {

                monsterX = teleportX;
                monsterY = teleportY;
                while (monstrAlpha < 250) {
                    monstrAlpha++;
                    pauseCheck();
                    Thread.sleep(10);
                }
                music.alienMonster();
                phase = 2;
            }

            if (phase == 2 || !teleport) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (teleportAlpha != 0) {
                                teleportAlpha--;
                                pauseCheck();
                                Thread.sleep(10);
                            }
                            teleport = true;
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
                phase = 3;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        if (phase == 3) {
            fire.start();
            while (!Thread.interrupted()) {

                try {
                    pauseCheck();
                    setNewDestinationAndMove();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

   private void setNewDestinationAndMove () {
       int destinationX = startX + (int) (Math.random() * (edgeX - startX - objectWidth));
       int destinationY = startY + (int) (Math.random() * (edgeY - startY - objectHeight));

        while (monsterX != destinationX && monsterX != destinationY) {
            if (monsterX < destinationX) {
                monsterX += 1;
                direction = 0;
            } else if (monsterX > destinationX) {
                monsterX -= 1;
                direction = 1;
            } else
                monsterX = destinationX;
//
            if (monsterY < destinationY) {
                monsterY += 1;
            } else if (monsterY > destinationY) {
                monsterY -= 1;
            } else monsterY = destinationY;
            try {
                pauseCheck();
                Thread.sleep(speed);
            }catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }

    public Rect getFireRect () {
        if (direction == 0) {
            return new Rect(monsterX + objectWidth - (int) (20 * Level.scaleFactor),
                    monsterY + (int) (10 * Level.scaleFactor),
                    monsterX + objectWidth  - (int) (20 * Level.scaleFactor) + fireWidth,
                    monsterY + (int) (10 * Level.scaleFactor) + fireHeight);
        }
        return new Rect(monsterX - fireWidth + (int) (20 * Level.scaleFactor),
                monsterY + (int) (10 * Level.scaleFactor),
                monsterX - fireWidth  + (int) (20 * Level.scaleFactor) + fireWidth,
                monsterY + (int) (10 * Level.scaleFactor) + fireHeight);
    }

    @Override
    public Rect getRectOfElement() {
        if (isFire()) {
            if (direction == 0) {
                return new Rect(monsterX + (int) (20 * Level.scaleFactor),
                        monsterY + (int) (25 * Level.scaleFactor),
                        monsterX + objectWidth + fireWidth - (int) (40 * Level.scaleFactor),
                        monsterY - (int) (5 * Level.scaleFactor) + objectHeight);
            } else {
                return new Rect(monsterX - fireWidth + (int) (40 * Level.scaleFactor),
                        monsterY + (int) (25 * Level.scaleFactor),
                        monsterX + objectWidth  - (int) (20 * Level.scaleFactor),
                        monsterY - (int) (5 * Level.scaleFactor) + objectHeight);
            }
        }
        return new Rect(monsterX  + (int) (20 * Level.scaleFactor),
                monsterY + (int) (20 * Level.scaleFactor),
                monsterX + objectWidth  - (int) (20 * Level.scaleFactor),
                monsterY - (int) (0 * Level.scaleFactor) + objectHeight);
    }

    public void setSpeed (int speed) {
        this.speed = speed;
    }

}

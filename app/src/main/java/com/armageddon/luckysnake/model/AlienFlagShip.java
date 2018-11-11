package com.armageddon.luckysnake.model;


import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.levels.Level;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.util.ArrayList;
import java.util.Collections;

public class AlienFlagShip extends Dot implements GameElement {

   private int smokeX = -500;
   private int smokeY = -500;
   private int shipX = (int) (Level.gameField.left + 400 * Level.scaleFactor);
   private int shipY;
   private int stepX = (int) (4 * Level.scaleFactor);
   private int stepY;
   private int destinationX;
   private int destinationY;
   private int waitTime;
   private int rocketWidth;
   private int rocketHeight;
   private int objectWidth;
   private int objectHeight;
   private int zombieWidth;
   private int zombieHeight;
   private int speed;
   private int zombieSpeed;
   private int targetZoneHight;
   private int targetZoneWidth;
   private int laserSpeed;
   private int laserHeight;
   private int laserWidth;
   private int zombieLifeTime;
   private int bossEnergy;
   private boolean action;
   private boolean homing;
   private boolean fire;
   private boolean showFire;
   private boolean showZombie;
   public boolean zombieRun;
   public boolean openFire;
   private boolean rocketStart;
   private boolean giveUp;
   private int phase;
   private transient PanelGamePlay panel;
   private Snake snake;
   private int fireTIme;
   private Zombie zombie1;
   private Zombie zombie2;
   private Zombie zombie3;
   private Zombie zombie4;
   private int targetsCount;
   private int laserCount;
   private ArrayList<float[]> homingLines = new ArrayList<>();
   private ArrayList<float[]> targets = new ArrayList<>();
   private ArrayList<float[]> crashTargets = new ArrayList<>();
   private ArrayList<Zombie> zombies = new ArrayList<>();
   public ArrayList<Laser> lasers = new ArrayList<>();
   private Rocket rocket;
   private transient Music music;
   transient Level level;
    public void setLevel(Level level) {
        this.level = level;
    }
    public boolean isGiveUp() {
        return giveUp;
    }
    public int getPhase() {
        return phase;
    }
    public void setPanel(PanelGamePlay panel) {
        this.panel = panel;
    }
    public void setMusic(Music music) {
        this.music = music;
    }
    public boolean isShowZombie() {
        return showZombie;
    }
    public int getSmokeX() {
        return smokeX;
    }
    public int getSmokeY() {
        return smokeY;
    }
    public boolean isShowFire() {
        return showFire;
    }
    public void setTargetsCount(int targetsCount) {
        this.targetsCount = targetsCount;
    }
    public void setHomingTime(int waitTime) {
        this.waitTime = waitTime;
    }
    public int getBossEnergy() {
        return bossEnergy;
    }
    public ArrayList<Laser> getLasers() {
        return lasers;
    }
    private void laserFire() {
        try {
            if (!openFire) {
                while (shipY > -objectHeight * 3) {
                    shipY -= (int) (4 * Level.scaleFactor);
                    pauseCheck();
                    Thread.sleep(10);
                }
                lasers.clear();
                targets.clear();
                fireTIme = 200;
                for (int i = 0; i < laserCount; i++) {
                    Laser laser = new Laser((startX + (int) (Math.random() * (edgeX - startX - laserWidth))), fireTIme);
                    fireTIme += 200;
                    lasers.add(laser);
                }
                music.laserFire2();
            } else {
                for (Laser laser : lasers) {
                    laser.start();
                }
                isContinue = false;
            }

            openFire = true;
            while (shipY < Level.gameField.bottom / 2) {
                shipY += (int) (2 * Level.scaleFactor);
                pauseCheck();
                Thread.sleep(30);
            }

            openFire = false;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            rocketStart();
        }
        phaseChange();
    }

    public class Laser extends Thread implements GameElement {
        public int laserX;
        public int laserY;

        int fireTime;

        @Override
        public boolean isHide() {
            return false;
        }

        @Override
        public void setPause(boolean pause) {
        }

        @Override
        public Rect getRectOfElement() {
            return null;
        }

        public Rect getLaserRect() {
            return new Rect(laserX, laserY, laserX + laserWidth, laserY + laserHeight);
        }

        Laser(int laserX, int fireTime) {
            this.fireTime = fireTime;
            this.laserX = laserX;
            laserY = -(laserHeight * 5) - (int) (Math.random() * 5 * laserHeight);
            start();

        }

        @Override
        public void run() {
            try {
                while (laserY < Level.gameField.bottom) {

                    laserY += (int) (4 * Level.scaleFactor);
                    pauseCheck();
                    Thread.sleep(laserSpeed);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                laserY = -1000;
                laserX = -1000;
            }
        }
    }

    public int getShipY() {
        return shipY;
    }
    public int getShipX() {
        return shipX;
    }
    public RectF getBeam() {
        return new RectF(shipX + objectWidth / 2 - 10 * Level.scaleFactor,
                shipY + objectHeight - 10 * Level.scaleFactor,
                shipX + objectWidth / 2 - 10 * Level.scaleFactor + 20 * Level.scaleFactor,
                shipY + objectHeight - 10 * Level.scaleFactor + 20 * Level.scaleFactor);
    }
    public ArrayList<float[]> getHomingLines() {
        return homingLines;
    }
    public ArrayList<float[]> getTargets() {
        return targets;
    }
    public ArrayList<float[]> getCrashTargets() {
        return crashTargets;
    }
    public boolean isHoming() {
        return homing;
    }
    public boolean isFire() {
        return fire;
    }
    private void explosionReset() {
        panel.resetExplosion1();
    }

    private void explosionStart() {
        panel.startExplosion1();
    }


    public class Zombie extends Thread implements GameElement {
        boolean mainZombie;
        int zombieX;
        int zombieY;
        int futureX;
        int futureY;
        public int direction;
        int teleportX;
        int teleportY;
        int teleportAlpha;
        int monstrAlpha;
        int zombiePhase;
        boolean zombieTeleport;

        Zombie(int teleportX,
               int teleportY,
               int zombieX,
               int zombieY,
               int direction,
               boolean mainZombie) {
            this.teleportX = teleportX;
            this.teleportY = teleportY;
            this.futureX = zombieX;
            this.futureY = zombieY;
            this.direction = direction;
            this.mainZombie = mainZombie;
        }

        @Override
        public Rect getRectOfElement() {
            return null;
        }

        @Override
        public boolean isHide() {
            return false;
        }

        public Point teleportPoint() {
            return new Point(teleportX, teleportY);
        }

        public int getTeleportAlpha() {
            return teleportAlpha;
        }

        public int getMonstrAlpha() {
            return monstrAlpha;
        }

        public Rect getZombieRect() {
            return new Rect(zombieX + (int) (0 * Level.scaleFactor),
                    zombieY + (int) (-40 * Level.scaleFactor),
                    zombieX + zombieWidth - (int) (0 * Level.scaleFactor),
                    zombieY + zombieHeight - (int) (40 * Level.scaleFactor));
        }

        public Rect getZombieCrashRect() {
            return new Rect(zombieX + (int) (15 * Level.scaleFactor),
                    zombieY - (int) (35 * Level.scaleFactor),
                    zombieX + zombieWidth - (int) (15 * Level.scaleFactor),
                    zombieY + zombieHeight - (int) (40 * Level.scaleFactor));
        }

        boolean pause;
        public void setPause(boolean pause) {
            this.pause = pause;
        }
        private void zombieMove() {
            if (zombieX < snake.getX()) {
                zombieX += 1 * Level.scaleFactor;
                direction = 0;
            } else if (zombieX > snake.getX()) {
                zombieX -= 1 * Level.scaleFactor;
                direction = 1;
            } else
                zombieX = snake.getX();
//
            if (zombieY < snake.getY()) {
                zombieY += 1 * Level.scaleFactor;
            } else if (zombieY > snake.getY()) {
                zombieY -= 1 * Level.scaleFactor;
            } else zombieY = snake.getY();
        }

        @Override
        public void run() {
            try {
                if (zombiePhase == 0) {
                    if (mainZombie) {
                        music.alienShip();
                    }
                    while (teleportAlpha < 250) {
                        pauseCheck();
                        teleportAlpha++;
                        Thread.sleep(10);
                    }

                    zombieX = futureX;
                    zombieY = futureY;
                    zombiePhase = 1;
                }
                if (zombiePhase == 1) {
                    while (monstrAlpha < 250) {
                        pauseCheck();
                        monstrAlpha += 1;
                        Thread.sleep(10);
                    }

                    if (mainZombie) {
                        music.zombie();
                    }
                    zombiePhase = 2;
                }
                if (zombiePhase == 2 || !zombieTeleport) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (teleportAlpha != 0) {
                                    pauseCheck();
                                    teleportAlpha--;
                                    Thread.sleep(10);
                                }
                                zombieTeleport = true;
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                                interrupt();
                            }
                        }
                    }).start();
                    zombiePhase = 3;
                }

                if (zombiePhase == 3) {

                    // Zombie destroy timer
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                pauseCheck();
                                Thread.sleep(zombieLifeTime);
                                interrupt();
                            } catch (InterruptedException ex) {
                                interrupt();
                            }
                        }
                    }).start();
                    while (!Thread.interrupted()) {
                        pauseCheck();
                        zombieMove();
                        Thread.sleep(zombieSpeed);
                    }
                }

            } catch (InterruptedException ex) {
                zombieRun = false;
                try {
                    showFire = false;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (teleportAlpha > 0) {
                                    teleportAlpha--;
                                    Thread.sleep(10);
                                }
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();
                    while (monstrAlpha > 0) {
                        monstrAlpha -= 2;
                        Thread.sleep(10);
                    }
                    showZombie = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    zombieRun = false;
                    showZombie = false;
                }
            }

        }

    }

   private void zombieWalk() {
        if (!isContinue) {
            zombies.clear();
            zombie1 = new Zombie(Level.gameField.left,
                    Level.gameField.top,
                    Level.gameField.left + (int) (35 * Level.scaleFactor),
                    Level.gameField.top + (int) (60 * Level.scaleFactor),
                    0, true);
            zombie2 = new Zombie(Level.gameField.left,
                    Level.gameField.bottom - this.zombieHeight - (int) (50 * Level.scaleFactor),
                    Level.gameField.left + (int) (25 * Level.scaleFactor),
                    Level.gameField.bottom - (int) (85 * Level.scaleFactor),
                    0, false);
            zombie3 = new Zombie(
                    Level.gameField.right - this.zombieWidth - (int) (60 * Level.scaleFactor),
                    Level.gameField.top,
                    Level.gameField.right - zombieWidth - (int) (20 * Level.scaleFactor),
                    Level.gameField.top + (int) (60 * Level.scaleFactor),
                    1, false);
            zombie4 = new Zombie(Level.gameField.right - this.zombieWidth - (int) (60 * Level.scaleFactor),
                    Level.gameField.bottom - this.zombieHeight - (int) (50 * Level.scaleFactor),
                    Level.gameField.right - zombieWidth - (int) (20 * Level.scaleFactor),
                    Level.gameField.bottom - (int) (85 * Level.scaleFactor),
                    1, false);
            Collections.addAll(zombies, zombie1, zombie2, zombie3, zombie4);
        }

        if (phase < 10) {
            zombie1.start();
            zombie2.start();
            zombie3.start();
            zombie4.start();
            zombieRun = true;
            showZombie = true;
        }
    }


    public AlienFlagShip(Snake snake,
                         int speed,
                         int homingTime,
                         int fireTIme,
                         int objectWidth,
                         int objectHeight,
                         int zombieWidth,
                         int zombieHeight,
                         int zombieLifeTime,
                         int zombieSpeed,
                         int targetZoneWidth,
                         int targetZoneHight,
                         int targetsCount,
                         int laserSpeed,
                         int laserCount,
                         int laserWidth,
                         int laserHeight,
                         PanelGamePlay panel,
                         Rocket rocket,
                         Music music) {
        super(null, null);
        this.waitTime = homingTime;
        this.speed = speed;
        this.objectWidth = (int) (objectWidth * Level.scaleFactor);
        this.objectHeight = (int) (objectHeight * Level.scaleFactor);
        this.snake = snake;
        this.fireTIme = fireTIme;
        this.zombieWidth = (int) (zombieWidth * Level.scaleFactor);
        this.zombieHeight = (int) (zombieHeight * Level.scaleFactor);
        this.targetZoneWidth = (int) (targetZoneWidth * Level.scaleFactor);
        this.targetZoneHight = (int) (targetZoneHight * Level.scaleFactor);
        this.targetsCount = targetsCount;
        this.panel = panel;
        this.laserSpeed = laserSpeed;
        this.laserWidth = (int) (laserWidth * Level.scaleFactor);
        this.laserHeight = (int) (laserHeight * Level.scaleFactor);
        this.laserCount = laserCount;
        this.zombieSpeed = zombieSpeed;
        this.zombieLifeTime = zombieLifeTime;
        this.rocket = rocket;
        this.rocketWidth = rocket.objectWidth;
        this.rocketHeight = rocket.objectHeight;
        this.music = music;
        shipY = -objectHeight - (int) (40 * Level.scaleFactor);
        Collections.addAll(zombies, zombie1, zombie2, zombie3, zombie4);
    }
    public void setZombieSpeed(int zombieSpeed) {
        this.zombieSpeed = zombieSpeed;
    }
    public void setLaserCount(int laserCount) {
        this.laserCount = laserCount;
    }
    public ArrayList<Zombie> getZombies() {
        return zombies;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Rect getRectOfElement() {
        return new Rect(shipX, shipY, shipX + objectWidth, shipY + objectHeight);
    }

   private void setNewDestinationAndMove() {
        isContinue = false;
        openFire = false;
        destinationX = startX + (int) (Math.random() * (edgeX - startX - objectWidth));
        destinationY = startY + (int) (Math.random() * (edgeY - startY - objectHeight));

        while (shipX != destinationX && shipY != destinationY) {
            if (shipX < destinationX) {
                shipX += 1;
            } else if (shipX > destinationX) {
                shipX -= 1;
            } else
                shipX = destinationX;
            if (shipY < destinationY) {
                shipY += 1;
            } else if (shipY > destinationY) {
                shipY -= 1;
            } else shipY = destinationY;

            try {
                pauseCheck();
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
                rocketStart();
            }
        }
        phaseChange();
    }

   private void hominAndFire() {
        isContinue = false;
        try {
            if (!homing) {
                targets.clear();
                homingLines.clear();
                for (int i = 0; i < targetsCount; i++) {
                    float targetX = 0;
                    float targetY = 0;

                    if (i == 0) {
                        switch (snake.getDirection()) {
                            case "UP":
                                targetX = snake.getX();
                                targetY = snake.getY() - (int) (Level.scaleFactor);
                                if (targetY < Level.gameField.top + targetZoneHight) {
                                    targetY = Level.gameField.top + targetZoneHight / 2;
                                }
                                break;
                            case "DOWN":
                                targetX = snake.getX();
                                targetY = snake.getY() + (int) (Level.scaleFactor);
                                if (targetY > Level.gameField.bottom - targetZoneHight) {
                                    targetY = Level.gameField.bottom - targetZoneHight / 2;
                                }
                                break;
                            case "LEFT":
                                targetX = snake.getX() - (int) (Level.scaleFactor);
                                targetY = snake.getY();
                                if (targetX < Level.gameField.left + targetZoneWidth) {
                                    targetX = Level.gameField.left + targetZoneWidth / 2;
                                }
                                break;
                            case "RIGHT":
                                targetX = snake.getX() + (int) (Level.scaleFactor);
                                targetY = snake.getY();
                                if (targetX > Level.gameField.right - targetZoneWidth) {
                                    targetX = Level.gameField.right - targetZoneWidth / 2;
                                }
                                break;
                        }

                    } else {
                        targetX = startX + (int) (Math.random() * (edgeX - startX - targetZoneWidth));
                        targetY = startY + (int) (Math.random() * (edgeY - startY - targetZoneHight));
                    }

                    float[] targetPoints = new float[4];
                    targetPoints[0] = targetX - targetZoneWidth / 2;
                    targetPoints[1] = targetY - targetZoneHight / 2;
                    targetPoints[2] = targetX + targetZoneWidth / 2;
                    targetPoints[3] = targetY + targetZoneHight / 2;
                    targets.add(targetPoints);
                    crashTargets = new ArrayList<>(targets);

                    homingLines.add(new float[]{
                            (float) shipX + objectWidth / 2, shipY + objectHeight - 10 * Level.scaleFactor,
                            targetX, targetY});
                }
            }


            if (!fire) {
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
                    showFire = true;
                    music.laserFire();
                    explosionStart();
                    Thread.sleep(700);
                    fire = false;
                    Thread.sleep(1700);
                    showFire = false;
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            fire = false;
            showFire = false;
            homing = false;
        }

        phaseChange();
    }

   private void rocketStart() {
        if (phase < 10) {
            if (!isContinue) {
                rocketStart = true;
                homing = false;
                panel.resetExplosionOnShip();
                rocket.x = shipX + (int) (55 * Level.scaleFactor);
                rocket.y = Level.gameField.bottom;
                smokeX = rocket.x + (int) (8 * Level.scaleFactor);
                smokeY = rocket.y + rocketHeight - (int) (10 * Level.scaleFactor);
            }
            music.rocket();
            while (rocket.y > shipY + objectHeight - (int) (10 * Level.scaleFactor)) {
                rocket.y -= (int) (2 * Level.scaleFactor);
                smokeY -= (int) (2 * Level.scaleFactor);
                try {
                    pauseCheck();
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            panel.startExplosionOnShip();
            music.explosion();
            rocket.x = -1000;
            rocket.y = -1000;
            smokeX = -1000;
            smokeY = -1000;
            bossEnergy++;
            rocket.hide(true);
            rocketStart = false;
            isContinue = false;
        }
        if (bossEnergy == 10) {
            phase = 10;
            music.gameMusicStop();

            while (shipX != Level.gameField.right / 2 || shipY != Level.gameField.bottom / 2) {

                    if (shipX < Level.gameField.right / 2) {
                        shipX += 1 ;
                    }
                    else if  (shipX  >Level.gameField.right / 2) {
                        shipX -= 1;
                    } else
                        shipX = Level.gameField.right / 2;

                    if (shipY < Level.gameField.bottom / 2) {
                        shipY += 1;
                    }

                    else if  (shipY > Level.gameField.bottom / 2) {
                        shipY -= 1;
                    }
                    else shipY = Level.gameField.bottom / 2;
                    try {
                        pauseCheck();
                        Thread.sleep( (int) (20 / Level.scaleFactor));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
            }

            giveUp = true;
            music.mole1();
            try {
                pauseCheck();
                Thread.sleep(2500);
                phase = 11;
                interrupt();
            } catch (InterruptedException ex) {
                interrupt();
            }


        }

        if (bossEnergy >= 5 && bossEnergy < 10) {
            zombieWalk();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

   private void phaseChange() {
        switch (phase) {
            case 0:
                phase = 1;
                break;
            case 1:
                phase = 2;
                break;
            case 2:
                phase = 3;
                break;
            case 3:
                phase = 4;
                break;
            case 4:
                phase = 5;
                break;
            case 5:
                phase = 6;
                break;
            case 6:
                phase = 0;
                break;
        }
    }

    @Override
    public void run() {
        if (!isContinue) {
            music.alienShip();
        }
        if (!action) {
            while (shipY < Level.gameField.bottom / 2 - objectHeight) {
                shipY += (int) (2 * Level.scaleFactor);
                try {
                    pauseCheck();
                    Thread.sleep(32);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        action = true;
        while (!Thread.interrupted()) {
            if (isContinue && rocketStart || phase == 10) {
                rocketStart();
            }
            if (isContinue && zombieRun) {
                zombieWalk();
            }
            if (phase == 0) {
            setNewDestinationAndMove();
            }
            if (phase == 1) {
                hominAndFire();
            }
            if (phase == 2) {
                setNewDestinationAndMove();
            }
            if (phase == 3) {
                hominAndFire();
            }
            if (phase == 4) {
                setNewDestinationAndMove();
            }
            if (phase == 5) {
                hominAndFire();
            }
            if (phase == 6) {
                laserFire();
            }
        }
        }

    }
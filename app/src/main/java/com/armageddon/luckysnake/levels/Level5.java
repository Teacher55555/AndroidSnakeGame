package com.armageddon.luckysnake.levels;

import android.graphics.Rect;
import android.graphics.RectF;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.model.AlienMonster;
import com.armageddon.luckysnake.model.AlienShip;
import com.armageddon.luckysnake.model.Coin;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.Fruits;
import com.armageddon.luckysnake.model.Scissors;
import com.armageddon.luckysnake.model.Snail;
import com.armageddon.luckysnake.model.Snake;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Level5 extends Level {
   private AlienShip alienShip1;
   private AlienShip alienShip2;
   private AlienShip alienShip3;
   private AlienShip alienShip4;
   private AlienShip alienShip5;

   private AlienMonster alienMonster;
   private ArrayList<AlienShip> alienShips = new ArrayList<>();


    private int monsterSpeed2 = 13;
    private int monsterSpeed3 = 10;

    public Level5 (final MainActivity activity) {
        super(activity, new PanelGamePlay(activity, 5), 5);
        levelName = "Snake vs Alien";
        textSize = 140 * scaleFactor;
        int monsterSpeed1 = 16;

        if (scaleFactor == 1.5) {
            monsterSpeed1 = 12;
            monsterSpeed2 = 9;
            monsterSpeed3 = 6;
        } else if (scaleFactor == 2) {
            monsterSpeed1 = 8;
            monsterSpeed2 = 6;
            monsterSpeed3 = 4;
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(
                            new File(activity.getFilesDir(), "base.dat")));

            if (ois.readInt() == 5) {
                activity.setOpenedLevel(ois.readInt());
                activity.setBestRecord(ois.readInt());
                activity.setMusicOn(ois.readBoolean());
                activity.setSoundOn(ois.readBoolean());
                activity.setVibrationOn(ois.readBoolean());

                levelHardness = ois.readInt();
                snake = ((Snake) ois.readObject());
                snake.setLevel(this);
                if (snake.isDead()) {
                    snake.setDead(false);
                    snake.setDirection("RIGHT");
                    snake.start((int) (gameField.left + 40 * scaleFactor),
                            (int) (gameField.top + 40 * scaleFactor),
                            true, 20, 0,
                            (int) (20 * scaleFactor), 0); // 5
                }
                fruit1 = ((Fruit) ois.readObject());
                fruit2 = ((Fruit) ois.readObject());
                fruitCup = ((Fruits) ois.readObject());
                snail = ((Snail) ois.readObject());
                coin = ((Coin) ois.readObject());
                scissors = ((Scissors) ois.readObject());

                alienShip1 = ((AlienShip) ois.readObject());
                alienShip2 = ((AlienShip) ois.readObject());
                alienShip3 = ((AlienShip) ois.readObject());
                alienShip4 = ((AlienShip) ois.readObject());
                alienShip5 = ((AlienShip) ois.readObject());

                alienMonster = ((AlienMonster) ois.readObject());
                music = new Music(activity, level);

                alienShip1.setContinue(true);

                if (snake.getEatFruitForNewLevel() >= 5) {
                    alienShip2.setContinue(true);
                    alienShip2.start();
                }

                if (snake.getEatFruitForNewLevel() >= 10) {
                    alienShip3.setContinue(true);
                    alienShip3.start();
                }

                if (snake.getEatFruitForNewLevel() >= 20) {
                    alienShip4.setContinue(true);
                    alienShip4.start();
                }

                if (snake.getEatFruitForNewLevel() >= 25) {
                    alienShip5.setContinue(true);
                    alienShip5.start();
                }

                if (snake.getEatFruitForNewLevel() >= 30) {
                    alienMonster.setContinue(true);
                    alienMonster.start();
                }

                alienShip1.setMusic(music);
                alienShip2.setMusic(music);
                alienShip3.setMusic(music);
                alienShip4.setMusic(music);
                alienShip5.setMusic(music);
                alienMonster.setMusic(music);

                alienShip1.setPanel(panel);
                alienShip2.setPanel(panel);
                alienShip3.setPanel(panel);
                alienShip4.setPanel(panel);
                alienShip5.setPanel(panel);

                fruit1.setContinue(true);
                fruit2.setContinue(true);
                fruitCup.setContinue(true);
                snail.setContinue(true);
                coin.setContinue(true);
                scissors.setContinue(true);

                gameOnPause = true;
                isContinue = true;
                snakeWasDead = true;
            }
            System.out.println("Load ok!!!");
            ois.close();
        } catch (IOException ex) {
            System.out.println("File I/O exception");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (ClassCastException ex) {
            System.out.println("Class cast exception");
        }

        if (!isContinue) {

            baseElementsInit();
            music = new Music(activity, level);

            alienShip1 = new AlienShip(snake,
                    10,
                    1000,
                    300,
                    100,
                    100,
                    150,
                    67,
                    panel,
                    music);
            alienShip2 = new AlienShip(snake,
                    9,
                    900,
                    250,
                    100,
                    100,
                    150,
                    67,
                    panel,
                    music);
            alienShip3 = new AlienShip(snake,
                    8,
                    800,
                    200,
                    100,
                    100,
                    150,
                    67,
                    panel,
                    music);
            alienShip4 = new AlienShip(snake,
                    7,
                    700,
                    150,
                    100,
                    100,
                    150,
                    67,
                    panel,
                    music);
            alienShip5 = new AlienShip(snake,
                    6,
                    600,
                    100,
                    100,
                    100,
                    150,
                    67,
                    panel,
                    music);

            alienMonster = new AlienMonster(monsterSpeed1,
                    5000,
                    3000,
                    154,
                    142,
                    241,
                    154,
                    music);

            levelHardness = 0;
        }

        Collections.addAll(alienShips, alienShip1, alienShip2, alienShip3, alienShip4, alienShip5);

        drawElements.add(fruit1);
        drawElements.add(fruit2);
        drawElements.add(fruitCup);
        drawElements.add(snail);
        drawElements.add(coin);
        drawElements.add(scissors);
        drawElements.add(snake);
        drawElements.add(alienMonster);
        drawElements.addAll(alienShips);
        fruit1.start();
        fruit2.start();
        fruitCup.start();
        snail.start();
        coin.start();
        scissors.start();
        alienShip1.start();
        music.alienMonster();
        music.alienMonsterPause();
        startLevel();
        if (activity.getOpenedLevel() < 5) {
            activity.setOpenedLevel(5);
        }
        snake.start();

    }

    @Override
    boolean crashCheck(Rect snakeRec) {
        RectF snakeRectf = new RectF();
        snakeRectf.set(snakeRec);
        if (alienMonster.getRectOfElement().intersect(snakeRec)) {
            return true;
        }
        for (AlienShip ship : alienShips) {
            if (ship.isFire() && snakeRectf.intersect(ship.getCrashRectF())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void safeGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(activity.getFilesDir(),"base.dat")));
            oos.writeInt(level);
            oos.writeInt(activity.getOpenedLevel());
            oos.writeInt(activity.getBestRecord());
            oos.writeBoolean(activity.isMusicOn());
            oos.writeBoolean(activity.isSoundOn());
            oos.writeBoolean(activity.isVibrationOn());

            oos.writeInt(levelHardness);
            oos.writeObject(snake);
            oos.writeObject(fruit1);
            oos.writeObject(fruit2);
            oos.writeObject(fruitCup);
            oos.writeObject(snail);
            oos.writeObject(coin);
            oos.writeObject(scissors);

            oos.writeObject(alienShip1);
            oos.writeObject(alienShip2);
            oos.writeObject(alienShip3);
            oos.writeObject(alienShip4);
            oos.writeObject(alienShip5);
            oos.writeObject(alienMonster);

            oos.flush();
            oos.close();
            System.out.println("SAVE ok !!!!!!!!!!!!!!!!!!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void setLevelHardness() {
        int fruitCount = snake.getEatFruitForNewLevel();
        if (fruitCount >= 5 && fruitCount < 10 && levelHardness == 0) {
            alienShip2.start();
            levelHardness = 1;
        } else if (fruitCount >= 10 && fruitCount < 15 && levelHardness == 1) {
            alienShip3.start();
            levelHardness = 2;
        } else if  (fruitCount >= 15 && fruitCount < 20 && levelHardness == 2) {
            alienShip1.setTargetOffset(200);
            alienShip1.setWaitBefireFire(800);
            levelHardness = 3;
        } else if (fruitCount >= 20 && fruitCount < 25 && levelHardness == 3) {
            alienShip4.start();
            levelHardness = 4;
        } else if  (fruitCount >= 25 && fruitCount < 30 && levelHardness == 4) {
            alienShip5.start();
            levelHardness = 5;
        } else if (fruitCount >= 30 && fruitCount < 35 && levelHardness == 5) {
            alienMonster.start();
            levelHardness = 6;
        } else if (fruitCount >= 35 && fruitCount < 40 && levelHardness == 6) {
            alienMonster.setSpeed(monsterSpeed2);
            levelHardness = 7;
        } else if (fruitCount >= 40 && fruitCount < 45 && levelHardness == 7) {
            alienShip2.setTargetOffset(100);
            alienShip2.setWaitBefireFire(500);
            levelHardness = 8;
        } else if (fruitCount >= 45 && fruitCount < 50 && levelHardness == 8) {
            alienMonster.setSpeed(monsterSpeed3);
            levelHardness = 9;
        } else if (fruitCount >= 50) {
            nextLevel(6);
        }

    }

}

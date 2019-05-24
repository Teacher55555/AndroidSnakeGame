package com.armageddon.luckysnake.levels;

import android.graphics.Rect;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.model.Coin;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.Fruits;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.Mole;
import com.armageddon.luckysnake.model.Scissors;
import com.armageddon.luckysnake.model.Snail;
import com.armageddon.luckysnake.model.Snake;
import com.armageddon.luckysnake.model.Stump;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;

public class Level1 extends Level {

   private Stump stump1;
   private Stump stump2;
   private Stump stump3;
   private Stump stump4;
   private Stump stump5;

   private Mole mole1;
   private Mole mole2;


    public Level1 (final MainActivity activity) {
        super(activity, new PanelGamePlay(activity,1),1);
        textSize = 120 * scaleFactor;
        levelName = "Snake vs Stumps";
            try {
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(
                                new File(activity.getFilesDir(), "base.dat")));
                if (ois.readInt() == 1) {
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
                    stump1 = ((Stump) ois.readObject());
                    stump2 = ((Stump) ois.readObject());
                    stump3 = ((Stump) ois.readObject());
                    stump4 = ((Stump) ois.readObject());
                    stump5 = ((Stump) ois.readObject());
                    music = new Music(activity, level);
                    mole1 = ((Mole) ois.readObject());
                    mole2 = ((Mole) ois.readObject());
                    mole1.setMusic(music);
                    mole2.setMusic(music);

                    if (snake.getEatFruitForNewLevel() >= 20) {
                        mole1.setContinue(true);
                        mole1.start();
                    }

                    if (snake.getEatFruitForNewLevel() >= 30) {
                        mole2.setContinue(true);
                        mole2.start();
                    }

                    fruit1.setContinue(true);
                    fruit2.setContinue(true);
                    fruitCup.setContinue(true);
                    snail.setContinue(true);
                    coin.setContinue(true);
                    scissors.setContinue(true);

                    stump1.setContinue(true);
                    stump2.setContinue(true);
                    stump3.setContinue(true);
                    stump4.setContinue(true);
                    stump5.setContinue(true);

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

            stump1 = new Stump(snake, null,
                    30000, 1, 1, 200, 120);
            stump2 = new Stump(snake, null,
                    25000, 1, 1, 200, 120);
            stump3 = new Stump(snake, null,
                    20000, 1, 1, 200, 120);
            stump4 = new Stump(snake, null,
                    15000, 1, 1, 200, 120);
            stump5 = new Stump(snake, null,
                    10000, 1, 1, 200, 120);

            mole1 = new Mole(snake,
                    null,
                    350,
                    3,
                    false,
                    1,
                    1,
                    112,
                    140,
                    105,
                    music);
            mole2 = new Mole(snake,
                    null,
                    350,
                    3,
                    true,
                    1,
                    1,
                    112,
                    140,
                    105,
                    music);

            levelHardness = 0;
        }
        stump1.setAnotherStump(stump2, stump3, stump4, stump5);
        stump2.setAnotherStump(stump1, stump3, stump4, stump5);
        stump3.setAnotherStump(stump1, stump2, stump4, stump5);
        stump4.setAnotherStump(stump1, stump2, stump3, stump5);
        stump5.setAnotherStump(stump1, stump2, stump3, stump4);
        Collections.addAll(crashElements,stump1,stump2,stump3,stump4,stump5);
        drawElements.add(mole1);
        drawElements.add(mole2);
        drawElements.add(stump1);
        drawElements.add(stump2);
        drawElements.add(stump3);
        drawElements.add(stump4);
        drawElements.add(stump5);
        drawElements.add(fruit1);
        drawElements.add(fruit2);
        drawElements.add(fruitCup);
        drawElements.add(snail);
        drawElements.add(coin);
        drawElements.add(scissors);
        drawElements.add(snake);

        if (!isContinue) {
            activity.setCurrentScore(0);
        }
        fruit1.start();
        fruit2.start();
        fruitCup.start();
        snail.start();
        coin.start();
        scissors.start();
        stump1.start();
        stump2.start();
        startLevel();
//        activity.setOpenedLevel(2);
        snake.start();
       
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
            oos.writeObject(stump1);
            oos.writeObject(stump2);
            oos.writeObject(stump3);
            oos.writeObject(stump4);
            oos.writeObject(stump5);
            oos.writeObject(mole1);
            oos.writeObject(mole2);
            oos.flush();
            oos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    boolean crashCheck(Rect snakeRec) {
        for (GameElement element : crashElements) {
            if (snakeRec.intersect(element.getRectOfElement())) {
                return true;
            }
        }
        return  mole1.getCrashRect().intersect(snakeRec)
                || mole2.getCrashRect().intersect(snakeRec)
                || mole1.getMoleRect().intersect(snakeRec)
                || mole2.getMoleRect().intersect(snakeRec);
    }

    @Override
    void setLevelHardness() {
        int fruitCount = snake.getEatFruitForNewLevel();
        if (fruitCount >= 5 && fruitCount < 10 && levelHardness == 0) {
            stump3.start();
            levelHardness = 1;
        } else if (fruitCount >= 10 && fruitCount < 15 && levelHardness == 1) {
            stump4.start();
            levelHardness = 2;
        } else if (fruitCount >= 15 && fruitCount < 20 && levelHardness == 2) {
            stump5.start();
            levelHardness = 3;
        } else if (fruitCount >= 20 && fruitCount < 25 && levelHardness == 3) {
            mole1.start();
            levelHardness = 4;
        } else if (fruitCount >= 25 && fruitCount < 30 && levelHardness == 4) {
            mole1.setSpeed(250);
            levelHardness = 5;
        } else if (fruitCount >= 30 && fruitCount < 35 && levelHardness == 5) {
            mole2.start();
            levelHardness = 6;
        } else if (fruitCount >= 35 && fruitCount < 40 && levelHardness == 6) {
            mole2.setSpeed(250);
            levelHardness = 7;
        } else if (fruitCount >= 40 && fruitCount < 45 && levelHardness == 7) {
            mole1.setSpeed(200);
            mole2.setSpeed(200);
            levelHardness = 8;
        } else if (fruitCount >= 45 && fruitCount < 50 && levelHardness == 8) {
            mole1.setSpeed(150);
            mole2.setSpeed(150);
            levelHardness = 9;
        } else if (fruitCount >= 50) {
           nextLevel(2);
        }
    }
}

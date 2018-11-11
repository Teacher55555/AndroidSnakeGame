package com.armageddon.luckysnake.levels;


import android.graphics.Rect;
import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.model.Coin;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.Fruits;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.LawnMover;
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

public class Level2 extends Level {

   private LawnMover lawnMover1;
   private LawnMover lawnMover2;
   private LawnMover lawnMover3;
   private LawnMover lawnMover4;
   private LawnMover lawnMover5;
   private LawnMover pig;


    public Level2 (final MainActivity activity) {
        super(activity, new PanelGamePlay(activity, 2), 2);
        levelName = "Snake vs Lawn Mowers";
        textSize = 85 * scaleFactor;

            try {
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(
                                new File(activity.getFilesDir(), "base.dat")));

                if (ois.readInt() == 2) {
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
                    lawnMover1 = ((LawnMover) ois.readObject());
                    lawnMover2 = ((LawnMover) ois.readObject());
                    lawnMover3 = ((LawnMover) ois.readObject());
                    lawnMover4 = ((LawnMover) ois.readObject());
                    lawnMover5 = ((LawnMover) ois.readObject());
                    music = new Music(activity, level);
                    pig = ((LawnMover) ois.readObject());
                    pig.setMusic(music);
                    lawnMover1.setContinue(true);

                    if (snake.getEatFruitForNewLevel() >= 5) {
                        lawnMover2.setContinue(true);
                        lawnMover2.start();
                    }

                    if (snake.getEatFruitForNewLevel() >= 10) {
                        lawnMover3.setContinue(true);
                        lawnMover3.start();
                    }

                    if (snake.getEatFruitForNewLevel() >= 25) {
                        lawnMover4.setContinue(true);
                        lawnMover4.start();
                        pig.setContinue(true);
                        pig.start();
                    }

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

        if (!isContinue)   {
            baseElementsInit();
            music = new Music(activity, level);

            lawnMover1 = new LawnMover(snake, null, 20, 3, false,
                    true, 1, 1, 152, 158);
            lawnMover2 = new LawnMover(snake, null, 17, 3, false,
                    true, 2, 2, 152, 158);
            lawnMover3 = new LawnMover(snake, null, 15, 2, false,
                    false, 2, 2, 152, 158);
            lawnMover4 = new LawnMover(snake, null, 13, 2, false,
                    false, 2, 3, 152, 158);
            lawnMover5 = new LawnMover(snake, null, 10, 2, false,
                    false, 2, 2, 152, 158);
            pig = new LawnMover(snake, null, 15, 1, true,
                    true, 2, 2, 143, 164, music);
            levelHardness = 0;

        }

        Rect restrictedArea = new Rect(gameField.left, gameField.top,
                gameField.left + lawnMover1.objectWidth
                        + (int) (15 * scaleFactor), gameField.bottom);

        fruit1.setRestrictedArea(restrictedArea);
        fruit2.setRestrictedArea(restrictedArea);
        snail.setRestrictedArea(restrictedArea);
        coin.setRestrictedArea(restrictedArea);
        scissors.setRestrictedArea(restrictedArea);


        Collections.addAll(crashElements, lawnMover1, lawnMover2, lawnMover3, lawnMover4, lawnMover5,pig);

        drawElements.add(fruit1);
        drawElements.add(fruit2);
        drawElements.add(fruitCup);
        drawElements.add(snail);
        drawElements.add(coin);
        drawElements.add(scissors);
        drawElements.add(snake);
        drawElements.add(lawnMover1);
        drawElements.add(lawnMover2);
        drawElements.add(lawnMover3);
        drawElements.add(lawnMover4);
        drawElements.add(lawnMover5);
        drawElements.add(pig);

//        snakeThread = new Thread(snake);
//        snakeThread.start();
        fruit1.start();
        fruit2.start();
        fruitCup.start();
        snail.start();
        coin.start();
        scissors.start();
        lawnMover1.start();
//        pig.start();
        if (!isContinue) {
            music.lawnmover();
//            snake.setEatFruitForNewLevel(49); // УДАЛИТ !!!!!!
        }
        startLevel();
        snake.start();
//        snake.setEatFruitForNewLevel(49); // УДАЛИТЬ!!!!1


    }

    @Override
    public <E extends GameElement, E2 extends GameElement>
    void gameOverCheck(ArrayList<E> elements, ArrayList<E2> elements2) {
        super.gameOverCheck(crashElements,null);

    }

    @Override
    public void safeGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(activity.getFilesDir(),"base.dat")));
            oos.writeInt(level);

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
//            oos.writeObject(music);

            oos.writeObject(lawnMover1);
            oos.writeObject(lawnMover2);
            oos.writeObject(lawnMover3);
            oos.writeObject(lawnMover4);
            oos.writeObject(lawnMover5);
            oos.writeObject(pig);
            oos.flush();
            oos.close();
            System.out.println("ZAPIS ok !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void setLevelHardness() {
        int fruitCount = snake.getEatFruitForNewLevel();
        if (fruitCount >= 5 && fruitCount < 10 && levelHardness == 0) {
            lawnMover2.start();
            lawnMover1.setWaitTime(2);
            levelHardness = 1;
        } else if (fruitCount >= 10 && fruitCount < 15 && levelHardness == 1) {
            lawnMover3.start();
            lawnMover2.setWaitTime(2);
            levelHardness = 2;
        } else if (fruitCount >= 15 && fruitCount < 20 && levelHardness == 2) {
            lawnMover1.setSpeed(15);
            levelHardness = 3;
        } else if (fruitCount >= 20 && fruitCount < 25 && levelHardness == 3) {
            lawnMover2.setStraightforward(false);
            lawnMover2.setSpeed(12);
            levelHardness = 4;
        } else if (fruitCount >= 25 && fruitCount < 30 && levelHardness == 4) {
            lawnMover4.start();
            lawnMover1.setWaitTime(2);
            levelHardness = 5;
            pig.start();
        } else if (fruitCount >= 30 && fruitCount < 35 && levelHardness == 5) {
            lawnMover1.setSpeed(10);
            levelHardness = 6;
        } else if (fruitCount >= 35 && fruitCount < 40 && levelHardness == 6) {
            lawnMover2.setSpeed(10);
            levelHardness = 7;
        } else if (fruitCount >= 40 && fruitCount < 45 && levelHardness == 7) {
            lawnMover3.setSpeed(12);
            pig.setSpeed(8);
            pig.setStraightforward(false);
            levelHardness = 8;

        } else if (fruitCount >= 45 && fruitCount < 50 && levelHardness == 8) {
//            lawnMover1.setSpeed(8);
//            music.pigSpeedUp();
//            pig.setSpeed(8);
//            pig.setStraightforward(false);
            levelHardness = 9;
        } else if (fruitCount >= 50) {
            nextLevel(3);
        }
    }


//    @Override
//    void setLevelHardness() {
//        switch (snake.getEatFruitForNewLevel()) {
//            case 5 :
//                lawnMover2.start();
//                lawnMover1.setWaitTime(2);
//                break;
//            case 10 :
//                lawnMover3.start();
//                lawnMover2.setWaitTime(2);
//                break;
//            case 15 :
//                lawnMover1.setSpeed(15);
//                break;
//
//            case 20 :
//                lawnMover2.setStraightforward(false);
//                lawnMover2.setSpeed(12);
//                break;
//            case 25 :
//                lawnMover4.start();
//                lawnMover1.setWaitTime(2);
//                break;
//            case 30 :
//                lawnMover1.setSpeed(10);
////                lawnMover1.setStraightforward(false);
//                break;
//            case 35 :
//                lawnMover2.setSpeed(10);
//                break;
////            case 40 :
////                lawnMover5.start();
////                break;
//
//            case 40 :
//                lawnMover3.setSpeed(12);
////                nextLevel();
//                break;
//
//            case 45:
//                lawnMover1.setSpeed(8);
//                break;
//
//            case 50:
////                music.lawnmoverStop();
//                nextLevel();
//        }
//    }
}

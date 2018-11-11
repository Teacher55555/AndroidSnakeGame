package com.armageddon.luckysnake.levels;

import android.graphics.Point;
import android.graphics.Rect;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.model.Coin;
import com.armageddon.luckysnake.model.Elephant;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.Fruits;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.Gecko;
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

public class Level4 extends Level {
   private Gecko gecko1;
   private Gecko gecko2;
   private Gecko gecko3;
   private Gecko gecko4;
   private Gecko gecko5;
   private Gecko gecko6;
   private Gecko gecko7;
   private Gecko gecko8;
   private Gecko gecko9;
   private Gecko gecko10;

   private Elephant elephant;
   private int elephantSpeed2 = 9;

   private ArrayList<Gecko> geckos = new ArrayList<>();

    public Level4(final MainActivity activity) {
        super(activity, new PanelGamePlay(activity, 4), 4);
        textSize = 130 * scaleFactor;
        levelName = "Snake vs Gecko";

        int elephantSpeed1 = 15;
        if (scaleFactor == 1.5) {
            elephantSpeed1 = 11;
            elephantSpeed2 = 7;
        } else if (scaleFactor == 2) {
            elephantSpeed1 = 7;
            elephantSpeed2 = 4;
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(
                            new File(activity.getFilesDir(), "base.dat")));

            if (ois.readInt() == 4) {

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

                gecko1 = ((Gecko) ois.readObject());
                gecko2 = ((Gecko) ois.readObject());
                gecko3 = ((Gecko) ois.readObject());
                gecko4 = ((Gecko) ois.readObject());
                gecko5 = ((Gecko) ois.readObject());
                gecko6 = ((Gecko) ois.readObject());
                gecko7 = ((Gecko) ois.readObject());
                gecko8 = ((Gecko) ois.readObject());
                gecko9 = ((Gecko) ois.readObject());
                gecko10 = ((Gecko) ois.readObject());

                elephant = ((Elephant) ois.readObject());

                music = new Music(activity, level);

                gecko1.setContinue(true);
                gecko2.setContinue(true);

                if (snake.getEatFruitForNewLevel() >= 5) {
                    gecko3.setContinue(true);
                    gecko3.start();
                }

                if (snake.getEatFruitForNewLevel() >= 10) {
                    gecko4.setContinue(true);
                    gecko4.start();
                }

                if (snake.getEatFruitForNewLevel() >= 15) {
                    gecko5.setContinue(true);
                    gecko5.start();
                }

                if (snake.getEatFruitForNewLevel() >= 20) {
                    gecko6.setContinue(true);
                    gecko6.start();
                }

                if (snake.getEatFruitForNewLevel() >= 25) {
                    gecko7.setContinue(true);
                    gecko7.start();
                    elephant.setContinue(true);
                    elephant.start();
                }

                if (snake.getEatFruitForNewLevel() >= 30) {
                    gecko8.setContinue(true);
                    gecko8.start();
                }


                if (snake.getEatFruitForNewLevel() >= 35) {
                    gecko9.setContinue(true);
                    gecko9.start();
                }

                if (snake.getEatFruitForNewLevel() >= 40) {
                    gecko10.setContinue(true);
                    gecko10.start();
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

        if (!isContinue) {
            baseElementsInit();
            music = new Music(activity, level);

            gecko1 = new Gecko(snake,
                    null,
                    17,
                    4,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko2 = new Gecko(snake,
                    null,
                    16,
                    4,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko3 = new Gecko(snake,
                    null,
                    15,
                    3,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko4 = new Gecko(snake,
                    null,
                    14,
                    3,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko5 = new Gecko(snake,
                    null,
                    13,
                    2,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko6 = new Gecko(snake,
                    null,
                    12,
                    2,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko7 = new Gecko(snake,
                    null,
                    11,
                    2,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko8 = new Gecko(snake,
                    null,
                    10,
                    2,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko9 = new Gecko(snake,
                    null,
                    9,
                    1,
                    0,
                    1,
                    1,
                    158,
                    55);
            gecko10 = new Gecko(snake,
                    null,
                    8,
                    1,
                    0,
                    1,
                    1,
                    158,
                    55);

            elephant = new Elephant(fruit1,
                    elephantSpeed1,
                    145,
                    115);
            levelHardness = 0;
        }
        Collections.addAll(geckos, gecko1, gecko2, gecko3, gecko4,
                gecko5, gecko6, gecko7, gecko8, gecko9, gecko10);

        drawElements.add(fruit1);
        drawElements.add(fruit2);
        drawElements.add(fruitCup);
        drawElements.add(snail);
        drawElements.add(coin);
        drawElements.add(scissors);
        drawElements.add(snake);
        drawElements.addAll(geckos);
        drawElements.add(elephant);
        fruit1.start();
        fruit2.start();
        fruitCup.start();
        snail.start();
        coin.start();
        scissors.start();
        gecko1.start();
        gecko2.start();
        startLevel();
        snake.start();
    }

    @Override
    boolean crashCheck (Rect snakeRec) {
        for (Gecko gecko : geckos) {
            if (gecko.isRun() && gecko.getRectOfElement().intersect(snakeRec)) {
                return true;
            }
        }
        return false;
    }

   private void elephantSmashFruitCheck() {
        if (elephant.getCrashRect().intersect(fruit1.getRectOfElement())) {
            panel.setSmashFruit(new Point(fruit1.x, fruit1.y));
            fruit1.interrupt();
            music.fruitSmash();
        }
    }


    @Override
    public <E extends GameElement, E2 extends GameElement>
    void gameOverCheck(ArrayList<E> elements, ArrayList<E2> elements2) {
        elephantSmashFruitCheck();
        super.gameOverCheck(geckos, null);
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
            oos.writeObject(gecko1);
            oos.writeObject(gecko2);
            oos.writeObject(gecko3);
            oos.writeObject(gecko4);
            oos.writeObject(gecko5);
            oos.writeObject(gecko6);
            oos.writeObject(gecko7);
            oos.writeObject(gecko8);
            oos.writeObject(gecko9);
            oos.writeObject(gecko10);
            oos.writeObject(elephant);

            oos.flush();
            oos.close();
            System.out.println("SAVE ok !!!!!!!!!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void setLevelHardness() {
        int fruitCount = snake.getEatFruitForNewLevel();
        if (fruitCount >= 5 && fruitCount < 10 && levelHardness == 0) {
            gecko3.start();
            levelHardness = 1;
        } else if (fruitCount >= 10 && fruitCount < 15 && levelHardness == 1) {
            gecko4.start();
            levelHardness = 2;
        } else if  (fruitCount >= 15 && fruitCount < 20 && levelHardness == 2) {
            gecko5.start();
            levelHardness = 3;
        } else if (fruitCount >= 20 && fruitCount < 25 && levelHardness == 3) {
            gecko6.start();
            levelHardness = 4;
        } else if  (fruitCount >= 25 && fruitCount < 30 && levelHardness == 4) {
            gecko7.start();
            elephant.start();
            music.elephant();
            levelHardness = 5;
        } else if (fruitCount >= 30 && fruitCount < 35 && levelHardness == 5) {
            gecko8.start();
            levelHardness = 6;
        } else if (fruitCount >= 35 && fruitCount < 40 && levelHardness == 6) {
            gecko9.start();
            levelHardness = 7;
        } else if (fruitCount >= 40 && fruitCount < 45 && levelHardness == 7) {
            gecko10.start();
            levelHardness = 8;
        } else if (fruitCount >= 45 && fruitCount < 50 && levelHardness == 8) {
            elephant.setSpeed(elephantSpeed2);
            music.elephant();
            levelHardness = 9;
        } else if (fruitCount >= 50) {
            nextLevel(5);
        }

    }
}



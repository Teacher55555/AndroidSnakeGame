package com.armageddon.luckysnake.levels;

import android.graphics.Rect;
import android.graphics.RectF;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.model.Coin;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.Fruits;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.Lighting;
import com.armageddon.luckysnake.model.LightningBall;
import com.armageddon.luckysnake.model.Puddle;
import com.armageddon.luckysnake.model.Scissors;
import com.armageddon.luckysnake.model.Snail;
import com.armageddon.luckysnake.model.Snake;
import com.armageddon.luckysnake.model.Tornado;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Level3 extends Level {

    private Puddle puddle1;
    private Puddle puddle2;
    private Puddle puddle3;
    private Puddle puddle4;
    private Puddle puddle5;
    private Puddle puddle6;
    private Puddle puddle7;
    private Puddle puddle8;
    private Puddle puddle9;
    private ArrayList<Puddle> puddles = new ArrayList<>();
    private ArrayList<LightningBall> lightningBalls = new ArrayList<>();
    private ArrayList<GameElement> respawnElements = new ArrayList<>();
    private LightningBall lightningBall1;
    private LightningBall lightningBall2;
    private LightningBall lightningBall3;
    private LightningBall lightningBall4;
    private LightningBall lightningBall5;
    private LightningBall lightningBall6;
    private LightningBall lightningBall7;
    private LightningBall lightningBall8;
    private LightningBall lightningBall9;
    private LightningBall lightningBall10;
    private Tornado tornado;

    private Lighting lightning1;
    private int snakeSpeed;
    private boolean onPuddle;


    public Level3(final MainActivity activity) {
        super(activity, new PanelGamePlay(activity, 3), 3);
        levelName = "Snake vs Bad Weather";
        textSize = 92 * scaleFactor; //85

        int tornadoSpeed1 = 18;
        if (scaleFactor == 1.5) {
            tornadoSpeed1 = 13;
        } else if (scaleFactor == 2) {
            tornadoSpeed1 = 9;
        }


        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(
                            new File(activity.getFilesDir(), "base.dat")));

            if (ois.readInt() == 3) {
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
                snakeSpeed = ois.readInt();
                onPuddle = ois.readBoolean();
                fruit1 = ((Fruit) ois.readObject());
                fruit2 = ((Fruit) ois.readObject());
                fruitCup = ((Fruits) ois.readObject());
                snail = ((Snail) ois.readObject());
                coin = ((Coin) ois.readObject());
                scissors = ((Scissors) ois.readObject());

                puddle1 = ((Puddle) ois.readObject());
                puddle2 = ((Puddle) ois.readObject());
                puddle3 = ((Puddle) ois.readObject());
                puddle4 = ((Puddle) ois.readObject());
                puddle5 = ((Puddle) ois.readObject());
                puddle6 = ((Puddle) ois.readObject());
                puddle7 = ((Puddle) ois.readObject());
                puddle8 = ((Puddle) ois.readObject());
                puddle9 = ((Puddle) ois.readObject());

                puddle1.setContinue(true);
                puddle2.setContinue(true);
                puddle3.setContinue(true);
                puddle4.setContinue(true);
                puddle5.setContinue(true);
                puddle6.setContinue(true);
                puddle7.setContinue(true);
                puddle8.setContinue(true);
                puddle9.setContinue(true);

                music = new Music(activity, level);

                lightningBall1 = ((LightningBall) ois.readObject());
                lightningBall2 = ((LightningBall) ois.readObject());
                lightningBall3 = ((LightningBall) ois.readObject());
                lightningBall4 = ((LightningBall) ois.readObject());
                lightningBall5 = ((LightningBall) ois.readObject());
                lightningBall6 = ((LightningBall) ois.readObject());
                lightningBall7 = ((LightningBall) ois.readObject());
                lightningBall8 = ((LightningBall) ois.readObject());
                lightningBall9 = ((LightningBall) ois.readObject());
                lightningBall10 = ((LightningBall) ois.readObject());

                lightning1 = ((Lighting) ois.readObject());
                tornado = ((Tornado) ois.readObject());
                lightningBall1.setContinue(true);
                lightningBall2.setContinue(true);
                lightning1.setContinue(true);

                if (snake.getEatFruitForNewLevel() >= 5) {
                    lightningBall3.setContinue(true);
                    lightningBall3.start();
                }

                if (snake.getEatFruitForNewLevel() >= 10) {
                    lightningBall4.setContinue(true);
                    lightningBall4.start();
                }

                if (snake.getEatFruitForNewLevel() >= 15) {
                    lightningBall5.setContinue(true);
                    lightningBall5.start();
                }

                if (snake.getEatFruitForNewLevel() >= 25) {
                    lightningBall6.setContinue(true);
                    lightningBall6.start();
                    tornado.setContinue(true);
                    tornado.start();
                }

                if (snake.getEatFruitForNewLevel() >= 35) {
                    lightningBall7.setContinue(true);
                    lightningBall7.start();
                }

                if (snake.getEatFruitForNewLevel() >= 45) {
                    lightningBall8.setContinue(true);
                    lightningBall8.start();
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
            puddle1 = new Puddle(null,
                    null,
                    15000,
                    1,
                    1,
                    240,
                    101);
            puddle2 = new Puddle(null,
                    null,
                    13000,
                    2,
                    2,
                    240,
                    101);
            puddle3 = new Puddle(null,
                    null,
                    11000,
                    2,
                    2,
                    240,
                    101);
            puddle4 = new Puddle(null,
                    null,
                    9000,
                    3,
                    3,
                    240,
                    101);
            puddle5 = new Puddle(null,
                    null,
                    12000,
                    2,
                    2,
                    240,
                    101);
            puddle6 = new Puddle(null,
                    null,
                    16000,
                    2,
                    2,
                    240,
                    101);
            puddle7 = new Puddle(null,
                    null,
                    10000,
                    3,
                    3,
                    240,
                    101);
            puddle8 = new Puddle(null,
                    null,
                    8000,
                    1,
                    1,
                    240,
                    101);
            puddle9 = new Puddle(null,
                    null,
                    8000,
                    1,
                    1,
                    240,
                    101);

            lightningBall1 = new LightningBall(snake,
                    null,
                    20,
                    1,
                    1,
                    75,
                    75);
            lightningBall2 = new LightningBall(snake,
                    null,
                    18,
                    1,
                    1,
                    80,
                    80);
            lightningBall3 = new LightningBall(snake,
                    null,
                    17,
                    1,
                    1,
                    90,
                    90);
            lightningBall4 = new LightningBall(snake,
                    null,
                    16,
                    1,
                    1,
                    100,
                    100);
            lightningBall5 = new LightningBall(snake,
                    null,
                    15,
                    1,
                    1,
                    105,
                    105);
            lightningBall6 = new LightningBall(snake,
                    null,
                    14,
                    1,
                    1,
                    110,
                    110);
            lightningBall7 = new LightningBall(snake,
                    null,
                    13,
                    1,
                    1,
                    110,
                    110);
            lightningBall8 = new LightningBall(snake,
                    null,
                    12,
                    1,
                    1,
                    115,
                    115);
            lightningBall9 = new LightningBall(snake,
                    null,
                    11,
                    1,
                    1,
                    120,
                    120);
            lightningBall10 = new LightningBall(snake,
                    null,
                    10,
                    1,
                    1,
                    125,
                    125);

            lightning1 = new Lighting(snake,
                    null,
                    2000,
                    5,
                    5,
                    400,
                    775);
            tornado = new Tornado(snake, tornadoSpeed1, 150, 200);
            levelHardness = 0;

        }

        Collections.addAll(puddles, puddle1, puddle2, puddle3, puddle4,
                puddle5, puddle6, puddle7, puddle8, puddle9);
        Collections.addAll(lightningBalls, lightningBall1, lightningBall2, lightningBall3,
                lightningBall4, lightningBall5, lightningBall6, lightningBall7, lightningBall8,
                lightningBall9, lightningBall10);

        respawnElements.addAll(lightningBalls);
        respawnElements.add(tornado);

        puddle1.setAnotherPuddle(puddle2, puddle3, puddle4, puddle5,
                puddle6, puddle7, puddle8, puddle9);
        puddle2.setAnotherPuddle(puddle1, puddle3, puddle4, puddle5,
                puddle6, puddle7, puddle8, puddle9);
        puddle3.setAnotherPuddle(puddle1, puddle2, puddle4, puddle5,
                puddle6, puddle7, puddle8, puddle9);
        puddle4.setAnotherPuddle(puddle1, puddle2, puddle3, puddle5,
                puddle6, puddle7, puddle8, puddle9);
        puddle5.setAnotherPuddle(puddle1, puddle2, puddle3, puddle4,
                puddle6, puddle7, puddle8, puddle9);
        puddle6.setAnotherPuddle(puddle1, puddle2, puddle3, puddle4,
                puddle5, puddle7, puddle8, puddle9);
        puddle7.setAnotherPuddle(puddle1, puddle2, puddle3, puddle4,
                puddle5, puddle6, puddle8, puddle9);
        puddle8.setAnotherPuddle(puddle1, puddle2, puddle3, puddle4,
                puddle5, puddle6, puddle7, puddle9);
        puddle9.setAnotherPuddle(puddle1, puddle2, puddle3, puddle4,
                puddle5, puddle6, puddle7, puddle8);

        drawElements.addAll(puddles);
        drawElements.add(fruit1);
        drawElements.add(fruit2);
        drawElements.add(fruitCup);
        drawElements.add(snail);
        drawElements.add(coin);
        drawElements.add(scissors);
        drawElements.add(snake);
        drawElements.addAll(lightningBalls);
        drawElements.add(lightning1);
        drawElements.add(tornado);
        fruit1.start();
        fruit2.start();
        fruitCup.start();
        snail.start();
        coin.start();
        scissors.start();
        puddle1.start();
        puddle2.start();
        lightningBall1.start();
        lightningBall2.start();
        lightning1.start();

        startLevel();
        music.tornado();
        music.tornadoPause();
        snake.start();
    }

    @Override
    boolean crashCheck(Rect snakeRec) {
        RectF snakeRectf = new RectF();
        snakeRectf.set(snakeRec);

        if (tornado.getCrashRect().intersect(snake.getRectOfElement())) {
            return true;
        }

        for (LightningBall ball : lightningBalls) {
            if (ball.getCrashRect().intersect(snakeRectf)) {
                music.electroCrash();
                return true;
            }
            for (Puddle puddle : puddles) {
                RectF puddleRectf = new RectF();
                puddleRectf.set(puddle.getCrashRect());
                if (ball.getCrashRect().intersect(puddleRectf) && puddleRectf.intersect(snakeRectf)) {
                    music.electroCrash();
                    snakeSpeed = 0;
                    return true;
                }
            }
        }
        return false;
    }


    private void sparkCheck() {
        for (Puddle puddle : puddles) {
            for (LightningBall ball : lightningBalls) {
                RectF puddleRectf = new RectF();
                puddleRectf.set(puddle.getCrashRect());
                if (puddleRectf.intersect(ball.getCrashRect())) {
                    puddle.setSpark(true);
                    music.electroBall();
                    break;
                } else {
                    puddle.setSpark(false);
                }
            }
        }
    }

    private void paddleWalkCheck() {
        if (!onPuddle) {
            snakeSpeed = snake.getSpeed();
        }
        Rect snakeRec = snake.getRectOfElement();
        for (Puddle puddle : puddles) {
            if (snakeRec.intersect(puddle.getCrashRect())) {
                if (snakeSpeed == 0 || snakeSpeed == 1) {
                    snake.setSnakeSpeed(snakeSpeed - 1);
                } else {
                    snake.setSnakeSpeed(snakeSpeed - 2);
                }
                onPuddle = true;
                break;
            } else {
                snake.setSnakeSpeed(snakeSpeed);
                onPuddle = false;
            }
        }
    }


    @Override
    public <E extends GameElement, E2 extends GameElement>
    void gameOverCheck(ArrayList<E> elements, ArrayList<E2> elements2) {
        paddleWalkCheck();
        sparkCheck();
        super.gameOverCheck(respawnElements, null);

    }


    @Override
    public void safeGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(activity.getFilesDir(), "base.dat")));
            oos.writeInt(level);

            oos.writeInt(activity.getBestRecord());
            oos.writeBoolean(activity.isMusicOn());
            oos.writeBoolean(activity.isSoundOn());
            oos.writeBoolean(activity.isVibrationOn());


            oos.writeInt(levelHardness);
            oos.writeObject(snake);
            oos.writeInt(snakeSpeed);
            oos.writeBoolean(onPuddle);
            oos.writeObject(fruit1);
            oos.writeObject(fruit2);
            oos.writeObject(fruitCup);
            oos.writeObject(snail);
            oos.writeObject(coin);
            oos.writeObject(scissors);
//            oos.writeObject(music);

            oos.writeObject(puddle1);
            oos.writeObject(puddle2);
            oos.writeObject(puddle3);
            oos.writeObject(puddle4);
            oos.writeObject(puddle5);
            oos.writeObject(puddle6);
            oos.writeObject(puddle7);
            oos.writeObject(puddle8);
            oos.writeObject(puddle9);

            oos.writeObject(lightningBall1);
            oos.writeObject(lightningBall2);
            oos.writeObject(lightningBall3);
            oos.writeObject(lightningBall4);
            oos.writeObject(lightningBall5);
            oos.writeObject(lightningBall6);
            oos.writeObject(lightningBall7);
            oos.writeObject(lightningBall8);
            oos.writeObject(lightningBall9);
            oos.writeObject(lightningBall10);

            oos.writeObject(lightning1);
            oos.writeObject(tornado);

            oos.flush();
            oos.close();
            System.out.println("SAVE ok!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    void setLevelHardness() {
        int fruitCount = snake.getEatFruitForNewLevel();
        if (fruitCount >= 5 && fruitCount < 10 && levelHardness == 0) {
            puddle3.start();
            lightningBall3.start();
            levelHardness = 1;
        } else if (fruitCount >= 10 && fruitCount < 15 && levelHardness == 1) {
            puddle4.start();
            lightningBall4.start();
            levelHardness = 2;
        } else if (fruitCount >= 15 && fruitCount < 20 && levelHardness == 2) {
            puddle5.start();
            lightningBall5.start();
            levelHardness = 3;
        } else if (fruitCount >= 20 && fruitCount < 25 && levelHardness == 3) {
            puddle6.start();
            levelHardness = 4;
        } else if (fruitCount >= 25 && fruitCount < 30 && levelHardness == 4) {
            lightningBall6.start();
            levelHardness = 5;
            tornado.start();
            music.tornado();
//            music.tornado();
        } else if (fruitCount >= 30 && fruitCount < 35 && levelHardness == 5) {
            puddle7.start();
            levelHardness = 6;
        } else if (fruitCount >= 35 && fruitCount < 40 && levelHardness == 6) {
            lightningBall7.start();
            levelHardness = 7;
        } else if (fruitCount >= 40 && fruitCount < 45 && levelHardness == 7) {
            levelHardness = 8;
        } else if (fruitCount >= 45 && fruitCount < 50 && levelHardness == 8) {
            lightningBall8.start();
            levelHardness = 9;
        } else if (fruitCount >= 50) {
            nextLevel(0);
        }
    }
}


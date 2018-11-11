package com.armageddon.luckysnake.levels;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.controller.Controller;
import com.armageddon.luckysnake.model.AlienFlagShip;
import com.armageddon.luckysnake.model.Coin;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.Rocket;
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

public class Level6 extends Level {

   private AlienFlagShip flagShip;
   private Rocket rocket;

    public Level6(final MainActivity activity) {
        super(activity, new PanelGamePlay(activity, 6), 6);
        levelName = "Beat the Boss!";
        textSize = 140 * scaleFactor;

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(
                            new File(activity.getFilesDir(), "base.dat")));

            if (ois.readInt() == 6) {

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
                snail = ((Snail) ois.readObject());
                coin = ((Coin) ois.readObject());
                scissors = ((Scissors) ois.readObject());

                rocket = ((Rocket) ois.readObject());
                flagShip = ((AlienFlagShip) ois.readObject());

                music = new Music(activity, level);

                flagShip.setContinue(true);
                flagShip.setPanel(panel);
                flagShip.setMusic(music);

                rocket.setContinue(true);


                fruit1.setContinue(true);
                fruit2.setContinue(true);
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
            snake = new Snake(this, 20, 20);
            snake.start((int) (gameField.left + 40 * scaleFactor),
                    (int) (gameField.top + 40 * scaleFactor),
                    true, 20, 0,
                    (int) (20 * scaleFactor), 0); // 5
            fruit1 = new Fruit(snake, null,
                    9000, 1, 1, 40, 40);
            fruit2 = new Fruit(snake, null,
                    5000, 1, 1, 40, 40);
//            fruitCup = new Fruits(snake, null,
//                    4000, 1, 7, 80, 60);
            snail = new Snail(snake, null,
                    4000, 2, 4, 60, 40);
            coin = new Coin(snake, null,
                    4000, 1, 4, 40, 40);
            scissors = new Scissors(snake, null,
                    4000, 5, 10, 40, 60);
            music = new Music(activity, level);

            rocket = new Rocket(snake, 20000, 1, 1, 40, 65);
            flagShip = new AlienFlagShip(snake,
                    10,
                    1000,
                    200,
                    150,
                    66,
                    88,
                    100,
                    10000,
                    25,
                    100,
                    100,
                    10,
                    7,
                    15,
                    15,
                    140,
                    panel,
                    rocket,
                    music);

            levelHardness = 0;
        }

        flagShip.setLevel(this);
        drawElements.add(fruit1);
        drawElements.add(fruit2);
        drawElements.add(snail);
        drawElements.add(coin);
        drawElements.add(scissors);
        drawElements.add(snake);
        drawElements.add(rocket);
        drawElements.add(flagShip);
        fruit1.start();
        fruit2.start();
        snail.start();
        coin.start();
        scissors.start();
        flagShip.start();
        rocket.start();

        music.zombie();
        music.zombiePause();


        startLevel();
        snake.start();
    }

    void eatFruit(GameElement element) { // increases player score by 10 and counter of eaten fruit by 1. Increases game speed by 1 if eaten fruit count % 10 == 0
        snake.setEatFruitForSpeed(snake.getEatFruitForSpeed() + 1);
        snake.setEatFruitForCoin(snake.getEatFruitForCoin() + 1);
        snake.setEatFruitForNewLevel(snake.getEatFruitForNewLevel() + 1);
        if (rocket.isHide()) {
            snake.setEatFruitForRocket(snake.getEatFruitForRocket() + 1);
        }
        snake.addBody(1);
        music.eat();
        snake.setScore(snake.getScore() + 10);
        element.interrupt();
        newLifeCheck();
        setLevelHardness();
        panel.animText("+10",new Point(snake.getX(),snake.getY()));

        if (snake.getEatFruitForSpeed() >= 5 && snake.getSpeed() < 4) {
            music.speedUp();
            snake.setSnakeSpeed(snake.getSpeed() + 1);
            snake.setEatFruitForSpeed(0);

        }

        if (snake.getEatFruitForRocket() == 5) {
            {
                synchronized (Rocket.class) {
                    rocket.hide(false);
                    Rocket.class.notifyAll();
                    rocket.makeRocket();
                    snake.setEatFruitForRocket(0);
                }
            }

        }
    }

    @Override
    public void dotEatCheck () {
        for (int i = 0; i < drawElements.size(); i++) {
            GameElement element = drawElements.get(i);
            if (snake.getEatFruitForCoin() == 5) {
                synchronized (Controller.class) {
                    coin.setPause(false);
                    Controller.class.notifyAll();
                }
            }
            Rect snakeRect = snake.getRectOfElement();
            Rect elementRect = element.getRectOfElement();
            if (snakeRect.intersect(elementRect)) {
                if (element instanceof Fruit) {
                    eatFruit(element);
                } else if (element instanceof Coin) {
                    coinTake(element);
                } else if (element instanceof Scissors) {
                    scissorsTake(element);
                } else if (element instanceof Snail) {
                    snailTake(element);
                } else if (element instanceof Rocket) {
                    rocketTake(element);
                }
            }
        }
    }

    private void rocketTake (GameElement element) {
        flagShip.interrupt();
        ((Rocket)element).hide(true);
        if (flagShip.getBossEnergy() == 9) {
            snake.setMoveON(false);
            snake.setStepX(0);
            snake.setStepY(0);
            panel.setListenerOff();
            snake.setEatFruitForNewLevel(50);
        }
    }


    @Override
    boolean crashCheck(Rect snakeRec) {
        RectF snakeRectf = new RectF();
        snakeRectf.set(snakeRec);

        for (float[] floats : flagShip.getCrashTargets()) {
            RectF target = new RectF(floats[0], floats[1], floats[2], floats[3]);
            if (flagShip.isFire() && snakeRectf.intersect(target)) {
                return true;
            }
        }

        if (flagShip.openFire) {
            for (AlienFlagShip.Laser laser : flagShip.lasers) {
                if (snakeRec.intersect(laser.getLaserRect())) {
                    return true;
                }
            }
        }

        if (flagShip.zombieRun) {
            for (AlienFlagShip.Zombie zombie : flagShip.getZombies()) {
                if (snakeRec.intersect(zombie.getZombieCrashRect())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public <E extends GameElement, E2 extends GameElement>
    void gameOverCheck(ArrayList<E> elements, ArrayList<E2> elements2) {
        super.gameOverCheck(flagShip.getLasers(), flagShip.getZombies());

        if (flagShip.getPhase() == 11) {
            nextLevel(7);
        }

    }



    @Override
    public  void safeGame() {
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
            oos.writeObject(snail);
            oos.writeObject(coin);
            oos.writeObject(scissors);
            oos.writeObject(rocket);
            oos.writeObject(flagShip);

            oos.flush();
            oos.close();
            System.out.println("Save ok !!!!!!!!!!!!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void setLevelHardness() {
        switch (flagShip.getBossEnergy()) {
            case 1 :
                flagShip.setTargetsCount(11);
                flagShip.setLaserCount(16);
                flagShip.setHomingTime(900);
                flagShip.setZombieSpeed(24);
                break;
            case 2 :
                flagShip.setTargetsCount(12);
                flagShip.setLaserCount(17);
                flagShip.setHomingTime(800);
                flagShip.setZombieSpeed(23);
                break;
            case 3 :
                flagShip.setTargetsCount(13);
                flagShip.setLaserCount(18);
                flagShip.setHomingTime(700);
                flagShip.setZombieSpeed(22);
                break;
            case 4 :
                flagShip.setTargetsCount(14);
                flagShip.setLaserCount(19);
                flagShip.setHomingTime(600);
                flagShip.setZombieSpeed(21);
                break;
            case 5 :
                flagShip.setTargetsCount(15);
                flagShip.setLaserCount(20);
                flagShip.setZombieSpeed(20);
                break;
            case 6 :
                flagShip.setTargetsCount(16);
                flagShip.setLaserCount(21);
                flagShip.setZombieSpeed(19);
                break;
            case 7 :
                flagShip.setTargetsCount(17);
                flagShip.setLaserCount(22);
                flagShip.setZombieSpeed(18);
                break;
            case 8 :
                flagShip.setTargetsCount(18);
                flagShip.setLaserCount(23);
                flagShip.setZombieSpeed(17);
                break;
            case 9 :
                flagShip.setTargetsCount(20);
                flagShip.setLaserCount(24);
                flagShip.setZombieSpeed(16);
                break;
            case 10:
               break;
        }
    }
}

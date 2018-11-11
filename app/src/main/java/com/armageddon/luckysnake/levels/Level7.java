package com.armageddon.luckysnake.levels;

import android.graphics.Rect;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.model.FruitForGirl;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.Snake;
import com.armageddon.luckysnake.model.SnakeGirl;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Level7 extends Level {

   private SnakeGirl snakeGirl;
   private Rect backet1 = new Rect();
   private Rect backet2 = new Rect();
   private Rect backet3 = new Rect();
   private Rect backet4 = new Rect();

   private boolean fruitTake;
   private boolean loveStart;

   private FruitForGirl fruit1;
   private FruitForGirl fruit2;
   private FruitForGirl fruit3;
   private FruitForGirl fruit4;

   private boolean checkPoint1;

    public Level7(final MainActivity activity) {
        super(activity, new PanelGamePlay(activity, 7), 7);
        levelName = "Wake up your LOVE!";
        textSize = 90 * scaleFactor;

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(
                            new File(activity.getFilesDir(), "base.dat")));

            if (ois.readInt() == 7) {

                activity.setBestRecord(ois.readInt());
                activity.setMusicOn(ois.readBoolean());
                activity.setSoundOn(ois.readBoolean());
                activity.setVibrationOn(ois.readBoolean());

                snake = ((Snake) ois.readObject());

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
            snake = new Snake(this, 20, 20);
            snake.start((int) (gameField.left + 40 * scaleFactor),
                    (int) (gameField.top + 40 * scaleFactor),
                    true, 10, 0,
                    (int) (20 * scaleFactor), 0); // 5
            fruit1 = new FruitForGirl(snake, null,
                    9000, 1, 1, 40, 40);
            fruit2 = new FruitForGirl(snake, null,
                    5000, 1, 1, 40, 40);
            fruit3 = new FruitForGirl(snake, null,
                    9000, 1, 1, 40, 40);
            fruit4 = new FruitForGirl(snake, null,
                    9000, 1, 1, 40, 40);
            music = new Music(activity, level);
            snakeGirl = new SnakeGirl( 20,
                    20,
                    fruit1,
                    fruit2,
                    fruit3,
                    fruit4,
                    music,
                    panel,
                    getActivity());
            snakeGirl.start((int) (gameField.right - 300 * scaleFactor),
                    (int) (gameField.top + 340 * scaleFactor),
                    false, 10, 0,
                    (int) (20 * scaleFactor), 0); // 5
            snakeGirl.setGirl(true);
            snakeGirl.setGirlSleep(true);
            snakeGirl.setDirection("LEFT");

        Rect restrictedZone = new Rect((int) (gameField.right - 500 * scaleFactor), gameField.top, gameField.right, gameField.bottom);
        fruit1.setRestrictedArea(restrictedZone);
        fruit2.setRestrictedArea(restrictedZone);
        fruit3.setRestrictedArea(restrictedZone);
        fruit4.setRestrictedArea(restrictedZone);
        fruit1.setFruittype(0);
        fruit2.setFruittype(2);
        fruit3.setFruittype(5);
        fruit4.setFruittype(6);

        backet1.set(
                (int) (gameField.right - 340 * scaleFactor),
                (int) (gameField.top + 160 * scaleFactor),
                (int) (gameField.right - 340 * scaleFactor + 75 * scaleFactor),
                (int) (gameField.top + 160 * scaleFactor + 75 * scaleFactor));

        backet2.set(
                (int) (gameField.right - 160 * scaleFactor),
                (int) (gameField.top + 160 * scaleFactor),
                (int) (gameField.right - 160 * scaleFactor + 75 * scaleFactor),
                (int) (gameField.top + 160 * scaleFactor + 75 * scaleFactor));

        backet3.set(
                (int) (gameField.right - 340 * scaleFactor),
                (int) (gameField.top + 460 * scaleFactor),
                (int) (gameField.right - 340 * scaleFactor + 75 * scaleFactor),
                (int) (gameField.top + 460 * scaleFactor + 75 * scaleFactor));

        backet4.set(
                (int) (gameField.right - 160 * scaleFactor),
                (int) (gameField.top + 460 * scaleFactor),
                (int) (gameField.right - 160 * scaleFactor + 75 * scaleFactor),
                (int) (gameField.top + 460 * scaleFactor + 75 * scaleFactor));

        drawElements.add(fruit1);
        drawElements.add(fruit2);
        drawElements.add(fruit3);
        drawElements.add(fruit4);
        drawElements.add(snake);
        drawElements.add(snakeGirl);
        startLevel();
        snake.start();
        gameOnPause = false;
    }

    @Override
    void eatFruit(GameElement element) {
      FruitForGirl fruit = ((FruitForGirl) element);
        if (!fruitTake && fruit.getPhaze() != 2) {
            ((FruitForGirl) element).setPhaze(1);
            fruitTake = true;
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
            oos.writeObject(snake);
            oos.flush();
            oos.close();
            System.out.println("Save ok!!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


   private void basketCheck () {
        if (fruit1.getRectOfElement().intersect(backet1) && fruit1.getPhaze() == 1) {
            fruitTake = false;
            fruit1.setX((int) (backet1.left + 20 * scaleFactor));
            fruit1.setY((int) (backet1.top + 20 * scaleFactor));
            fruit1.setPhaze(2);
        } else if (fruit2.getRectOfElement().intersect(backet2) && fruit2.getPhaze() == 1) {
            fruitTake = false;
            fruit2.setX((int) (backet2.left + 20 * scaleFactor));
            fruit2.setY((int) (backet2.top + 20 * scaleFactor));
            fruit2.setPhaze(2);
        } else if (fruit4.getRectOfElement().intersect(backet3) && fruit4.getPhaze() == 1) {
            fruitTake = false;
            fruit4.setX((int) (backet3.left + 20 * scaleFactor));
            fruit4.setY((int) (backet3.top + 20 * scaleFactor));
            fruit4.setPhaze(2);
        } else if (fruit3.getRectOfElement().intersect(backet4) && fruit3.getPhaze() == 1) {
            fruitTake = false;
            fruit3.setX((int) (backet4.left + 20 * scaleFactor));
            fruit3.setY((int) (backet4.top + 20 * scaleFactor));
            fruit3.setPhaze(2);
        } else if (fruit1.getPhaze() == 2 && fruit2.getPhaze() == 2
                && fruit3.getPhaze() == 2 && fruit4.getPhaze() == 2
                && !loveStart ) {
            panel.setListenerOff();
            loveStart = true;
        }
    }

   private void checkPointSet () {
        if (loveStart) {
            if (snake.getX() != gameField.left + 80 * scaleFactor && !checkPoint1) {
                snake.setDirection("LEFT");
                snake.setStepX((int) (20 * scaleFactor));
            } else if (snake.getX() == gameField.left + 80 * scaleFactor
                    && snake.getY() < gameField.top + 340 * scaleFactor) {
                snake.setDirection("DOWN");
                snake.setStepX(0);
                snake.setStepY((int) (20 * scaleFactor));
            } else if (snake.getX() == gameField.left + 80 * scaleFactor
                    && snake.getY() > gameField.top + 340 * scaleFactor) {
                snake.setDirection("UP");
                snake.setStepX(0);
                snake.setStepY((int) (-20 * scaleFactor));
            } else if (snake.getY() == gameField.top + 340 * scaleFactor
                    && snake.getX() != gameField.left + 300 * scaleFactor ) {
                snake.setDirection("RIGHT");
                checkPoint1 = true;
                snake.setStepY(0);
                snake.setStepX((int) (20 * scaleFactor));
            }else if (snake.getY() == gameField.top + 340 * scaleFactor
                    && snake.getX() == gameField.left + 300 * scaleFactor ) {
                snake.interrupt();
                music.wakeUpCall();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            panel.showClock(true);
                            music.wakeUpCall();
                            Thread.sleep(2000);
                            panel.showClock(false);
                            music.yawn();
                            snakeGirl.setGirlSleep(false);
                            Thread.sleep(2000);
                            snakeGirl.start();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }


    @Override
    public <E extends GameElement, E2 extends GameElement>
    void gameOverCheck(ArrayList<E> elements, ArrayList<E2> elements2) {
        if (snake.getX() >= gameField.right ) {
            snake.setX(gameField.left);
            snake.setDirection("RIGHT");
        } else if (snake.getX() < gameField.left) {
            snake.setX(gameField.right);
            snake.setDirection("LEFT");
        } else if (snake.getY() > gameField.bottom) {
            snake.setY(gameField.top);
            snake.setDirection("DOWN");
        } else if (snake.getY() < gameField.top) {
            snake.setY(gameField.bottom);
            snake.setDirection("UP");
        }
        basketCheck();
        checkPointSet();
    }

    @Override
    void setLevelHardness() { }


}


package com.armageddon.luckysnake.levels;


import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.Snake;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LevelBonus extends Level {

   private Fruit fruit3;
   private Fruit fruit4;
   private Fruit fruit5;

public LevelBonus (final MainActivity activity) {
    super(activity, new PanelGamePlay(activity, 0), 0);

        levelName = "Bonus Level!";
        textSize = 160 * scaleFactor;

    try {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(
                        new File(activity.getFilesDir(), "base.dat")));

        if (ois.readInt() == 0) {

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
            fruit3 = ((Fruit) ois.readObject());
            fruit4 = ((Fruit) ois.readObject());
            fruit5 = ((Fruit) ois.readObject());


            music = new Music(activity, level);

            fruit1.setContinue(true);
            fruit2.setContinue(true);
            fruit3.setContinue(true);
            fruit4.setContinue(true);
            fruit5.setContinue(true);

            gameOnPause = true;
            isContinue = true;
            snakeWasDead = true;
        }
        ois.close();
        System.out.println("Load ok!!!!!!!!!");
//            setPause();
    } catch (IOException ex) {
        System.out.println("НЕТУ ?????????");
    } catch (ClassNotFoundException ex) {
        System.out.println("НЕ найден class");
    } catch (ClassCastException ex) {
        System.out.println("НЕ могу кастануть!!!!!!!");
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
         fruit3 = new Fruit(snake, null,
                9000, 1, 1, 40, 40);
         fruit4 = new Fruit(snake, null,
                5000, 1, 1,40, 40);
         fruit5 = new Fruit(snake, null,
                5000, 1, 1,40, 40);
        music = new Music(activity, level);
    }


    snake.setSnakeSpeed(4);
    drawElements.clear();
    drawElements.add(fruit1);
    drawElements.add(fruit2);
    drawElements.add(fruit3);
    drawElements.add(fruit4);
    drawElements.add(fruit5);
    drawElements.add(snake);

    fruit1.start();
    fruit2.start();
    fruit3.start();
    fruit4.start();
    fruit5.start();

    panel.setElements(drawElements);

    startLevel();
    snake.start();

    }

    @Override
    public <E extends GameElement, E2 extends GameElement>
    void gameOverCheck(ArrayList<E> elements, ArrayList<E2> elements2) {
        if (snake.getX() < gameField.left
                || snake.getY() < gameField.top
                || snake.getX() > gameField.right - snake.getStepX()
                || snake.getY() > gameField.bottom - snake.getStepY()
                || snake.selfEated()) {
//
            panel.setListenerOff();
            music.crash();
            snake.setDead(true);
            setPause();
            nextLevel(4);
        }
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
            oos.writeObject(fruit3);
            oos.writeObject(fruit4);
            oos.writeObject(fruit5);
            oos.flush();
            oos.close();
            System.out.println("Save ok !!!!!!!!!!!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void setLevelHardness() {
        snake.setEatFruitForSpeed(0);
        snake.setEatFruitForCoin(0);
        snake.setEatFruitForNewLevel(0);
    }
}

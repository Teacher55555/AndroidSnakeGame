package com.armageddon.luckysnake.levels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Vibrator;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.controller.ControllerPlay;
import com.armageddon.luckysnake.model.Coin;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.Fruits;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.Scissors;
import com.armageddon.luckysnake.model.Snail;
import com.armageddon.luckysnake.model.Snake;
import com.armageddon.luckysnake.view.Panel;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public abstract class Level implements Ilevel, Serializable {
   transient MainActivity activity;
   int level;
   public static int screenHeight, screenWidth;
   public static float scaleFactor;
   transient public static Rect gameField;
   int levelHardness;
   transient PanelGamePlay panel;
   private Point levelPoint = new Point();
   private Point textPoint = new Point();
   private Point numberPoint = new Point();
   private Point readyPoint = new Point();
   private Point goPoint = new Point();
   float textSize;
   String levelName;
   Music music;
   static public boolean gameOnPause;
   boolean snakeWasDead;
   boolean isContinue;

   ArrayList<GameElement> drawElements = new ArrayList<>();
   ArrayList <GameElement> crashElements = new ArrayList<>();

   Snake snake;
   Fruit fruit1;
   Fruit fruit2;
   Fruits fruitCup;
   Snail snail;
   Coin coin;
   Scissors scissors;

    public Panel getPanel() {
        return panel;
    }
    public void snakeInterrupt () {
        snake.interrupt();
    }
    public MainActivity getActivity() {
        return activity;
    }

    @Override
    public int getLevel() {
        return level;
    }

    void baseElementsInit() {
        snake = new Snake(this, 20, 20);
        snake.start((int) (gameField.left + 40 * scaleFactor),
                (int) (gameField.top + 40 * scaleFactor),
                true, 20, 0,
                (int) (20 * scaleFactor), 0); // 5
        fruit1 = new Fruit(snake, null,
                9000, 1, 1, 40, 40);
        fruit2 = new Fruit(snake, null,
                5000, 1, 1, 40, 40);
        fruitCup = new Fruits(snake, null,
                4000, 1, 7, 80, 60);
        snail = new Snail(snake, null,
                4000, 2, 4, 60, 40);
        coin = new Coin(snake, null,
                4000, 1, 4, 40, 40);
        scissors = new Scissors(snake, null,
                4000, 1, 10, 40, 60);
    }

   public void safeGame () {
        try {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(
                        new File(activity.getFilesDir(),"base2.dat")));
        oos.writeObject(snake);
        oos.flush();
        oos.close();
        System.out.println("Save ok !!!!!!!!!!!!!!!!!!!!!!!!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Level(final MainActivity activity, final PanelGamePlay panel, int level) {
        this.activity = activity;
        this.level = level;
        this.panel = panel;
        screenHeight = activity.getScreenHeight();
        screenWidth = activity.getScreenWidth();
        scaleFactor = activity.getScaleFactor();
        gameField = activity.getGameField();
//        pausePoint.set((int) (gameField.left + 300 * scaleFactor), gameField.bottom/2);
        textPoint.set((int) (gameField.left + 80 * scaleFactor), (int) (gameField.top + 350 * scaleFactor));
        levelPoint.set((int) (gameField.left + 250 * scaleFactor), (int) (gameField.top + 350 * scaleFactor));
        numberPoint.set((int) (gameField.left + 420 * scaleFactor), (int) (gameField.top + 350 * scaleFactor));
        readyPoint.set((int)(gameField.left + 200 * scaleFactor), (int) (gameField.top + 350 * scaleFactor));
        goPoint.set((int)(gameField.left + 350 * scaleFactor), (int) (gameField.top + 350 * scaleFactor));
    }

    public void home () {
        music.stopAll();
        panel.showElemets(false);
        isContinue = false;
        gameOnPause = false;
        snake.interrupt();
        activity.setCurrentScore(0);
        activity.playGame(-3);
    }

    public void restartGame () {
        music.stopAll();
        panel.showElemets(false);
        isContinue = false;
        activity.setCurrentScore(0);
        snake.interrupt();
        gameOnPause = false;

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    new File(activity.getFilesDir(),"base.dat")));
            oos.writeInt(0);
            oos.writeInt(activity.getOpenedLevel());
            oos.writeInt(activity.getBestRecord());
            oos.writeBoolean(activity.isMusicOn());
            oos.writeBoolean(activity.isSoundOn());
            oos.writeBoolean(activity.isVibrationOn());
            oos.flush();
            oos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        activity.playGame(level);
    }

   public void nextLevel (final int nextLevel) {
        panel.setListenerOff();
        activity.setCurrentScore(snake.getScore());
        activity.setSnakeLife(snake.getLifeCount());
        activity.setLifeCheckPoint(snake.getLifeCheckPoint());
        activity.setCurrentLevel(level);
        activity.setNextLevel(nextLevel);
        setPause();
        snake.interrupt();
        music.stopAll();
        panel.showElemets(false);
        isContinue = false;
        gameOnPause = false;
        activity.playGame(-1);
    }

    void startLevel () {
        if (!isContinue) {
        snake.setLifeCount(activity.getSnakeLife());
        snake.setScore(activity.getCurrentScore());
        snake.setLifeCheckPoint(activity.getLifeCheckPoint());
        music.gameMusic();
            if (snake.getLifeCount() < 5) {
                snake.setLifeCount(5);
            }
        }
        Vibrator vb = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        ControllerPlay controller = new ControllerPlay(snake, music, panel, vb, this);
        controller.setIsSettings(false);
        activity.getHandler().post(new Runnable() {
            @Override
            public void run() {
                activity.setContentView(panel);
            }
        });
        controller.setPauseButtons(panel.getPauseButtons());
        panel.setElements(drawElements);
        panel.setController(controller);

    }

    boolean crashCheck (Rect snakeRec) {
        for (GameElement element : crashElements) {
            if (element.getRectOfElement().intersect(snakeRec)) {
                return true;
            }
        }
        return false;
    }

    public void stopAllMusic () {
        music.stopAll();
    }
    public <E extends GameElement, E2 extends GameElement>
    void gameOverCheck(ArrayList<E> elements, ArrayList<E2> elements2) { //Player loses his life, if he touches stumps, himself or game borders.
        Rect snakeRec = snake.getRectOfElement();
        boolean elementsCrash = crashCheck(snakeRec);

        if (snake.getX() < gameField.left
                || snake.getY() < gameField.top
                || snake.getX() > gameField.right - snake.getStepX()
                || snake.getY() > gameField.bottom - snake.getStepY()
                || snake.selfEated()
                || elementsCrash) {
            panel.setListenerOff();
            music.crash();
            snake.setDead(true);
            snake.setLifeCount(snake.getLifeCount() - 1);
            snake.setEatFruitForSpeed(0);
            setPause();

            if (snake.getLifeCount() < 0) {
                    music.gameMusicStop();
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException ex) {ex.printStackTrace();}
                activity.setCurrentScore(snake.getScore());
                activity.setCurrentLevel(level);
                music.stopAll();
                panel.showElemets(false);
                isContinue = false;
                gameOnPause = false;
                snake.interrupt();
                activity.playGame(-2);
            } else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                panel.setText("Ready?",readyPoint, 200 * scaleFactor,true);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (elements != null) {
                    for (E element : elements) {
                        if (element != null) {
                            element.interrupt();
                        }
                    }
                }

                if (elements2 != null) {
                    for (E2 element : elements2) {
                        if (element != null) {
                            element.interrupt();
                        }
                    }
                }
                snake.setDead(false);
                snake.setDirection("RIGHT");
                snake.start( (int) (gameField.left + 40 * scaleFactor),
                        (int) (gameField.top + 40 * scaleFactor),
                        true, snake.getBodySize(),
                        0, (int) (20 *  scaleFactor), 0);
                try {
                    panel.setText("Go!", goPoint,200 * scaleFactor,true);
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                panel.setText(false);
                panel.setListenerOn();
                resumeGame();
            }

        }
    }

    public void like () {
        String url = "https://play.google.com/store/apps/details?id=com.armageddon.luckysnake";
        final Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.getHandler().post(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(i);
            }
        });
    }

    public void share () {
        setGameOnPause();
        String messageText = "Try it awesome game: Lucky Snake" +
                "\nhttps://play.google.com/store/apps/details?id=com.armageddon.luckysnake";
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, messageText);
        activity.getHandler().post(new Runnable() {
            @Override
            public void run() {
               activity.startActivity(intent);
            }
        });
    }

    @Override
    public void setGameOnPause() {
        if (gameOnPause) {
            resumeGame();
        } else {
            setPause();
        }
    }



   public void setPause () {
       gameOnPause = true;
       if (!snake.isDead() && snake.getEatFruitForNewLevel() < 50) {
           panel.setShowPause(true);
       }
       if (!snake.isDead()) {
           switch (level) {
               case 1:
                   music.digsPause();
                   break;
               case 2:
                   music.lawnmoverPause();
                   break;
               case 3:
                   if (snake.getEatFruitForNewLevel() >= 25) {
                       music.tornadoPause();
                   }
                   break;
           }
           music.gameMusicPause();
           snakeWasDead = true;
       }


       synchronized (ControllerPlay.class) {
           for (GameElement element : drawElements) {
               element.setPause(true);
           }
       }
           safeGame();
    }

   public void playBonusMusic () {
        music.life();
    }

   private void resumeGame () {
        gameOnPause = false;

        panel.setShowPause(false);
        if (snakeWasDead) {
            switch (level) {
                case 1:
                    music.digsResume();
                    break;
                case 2:
                    music.lawnmover();
                    break;
                case 3:
                    if (snake.getEatFruitForNewLevel() >= 25) {
                        music.tornado();
                    }
                    break;
            }
            music.gameMusic();
            snakeWasDead = false;
        }


        for (GameElement element : drawElements) {
            synchronized (ControllerPlay.class) {
                element.setPause(false);
                ControllerPlay.class.notifyAll();
            }
        }
    }

    public void dotEatCheck () {
        for (int i = 0; i < drawElements.size(); i++) {
            GameElement element = drawElements.get(i);
            if (snake.getEatFruitForCoin() == 5) {
                synchronized (Coin.class) {
                    coin.hide(false);
                    Coin.class.notifyAll();
                }
            }
            if (snake.getEatFruitForFruits() == 8) {
                synchronized (Fruits.class) {
                    if (fruitCup != null) {
                        fruitCup.hide(false);
                    }
                    Fruits.class.notifyAll();
                }
            }
            Rect snakeRect = snake.getRectOfElement();
            Rect elementRect = element.getRectOfElement();
            if (snakeRect.intersect(elementRect)) {
                if (element instanceof Fruit) {
                    eatFruit(element);
                } else if (element instanceof Fruits) {
                    eatFruits(element);
                } else if (element instanceof Coin) {
                    coinTake(element);
                } else if (element instanceof Scissors) {
                    scissorsTake(element);
                } else if (element instanceof Snail) {
                    snailTake(element);
                }
            }
        }
    }

    public void animate1up () {
        panel.animText("1up",new Point(snake.getX(), (int) (snake.getY() + 40 * scaleFactor)));
        panel.setClickOn(false);
    }

   private void eatFruits (GameElement element) {
        snake.setEatFruitForNewLevel(snake.getEatFruitForNewLevel() + 5);
        snake.setEatFruitForSpeed(snake.getEatFruitForSpeed() + 1);
        snake.setEatFruitForCoin(snake.getEatFruitForCoin() + 1);
        snake.setEatFruitForFruits(0);
        snake.setScore(snake.getScore() + 50);
        if (snake.getEatFruitForNewLevel() < 50) {
            music.swallow();
            panel.animText("+50",new Point(snake.getX(),snake.getY()));
        }
        snake.addBody(1);
        newLifeCheck();
        setLevelHardness();
        element.interrupt();
        ((Fruits) element).hide(true);
    }

    void eatFruit(GameElement element) {
        snake.setEatFruitForNewLevel(snake.getEatFruitForNewLevel() + 2);  // вернуть 1
        snake.setEatFruitForFruits(snake.getEatFruitForFruits() + 1);
        snake.setEatFruitForSpeed(snake.getEatFruitForSpeed() + 1);
        snake.setEatFruitForCoin(snake.getEatFruitForCoin() + 1);
        snake.setScore(snake.getScore() + 10);
        panel.animText("+10",new Point(snake.getX(),snake.getY()));
        if (snake.getEatFruitForNewLevel() < 50) {
            music.eat();
        }
        snake.addBody(1);
        element.interrupt();
        newLifeCheck();

        if (snake.getEatFruitForSpeed() >= 5 && snake.getSpeed() < 4 && snake.getEatFruitForNewLevel() < 50) {
            music.speedUp();
            snake.setSnakeSpeed(snake.getSpeed() + 1);
            snake.setEatFruitForSpeed(0);
        }
        setLevelHardness();

    }
    void scissorsTake(GameElement element) { // reduces snake's tail by 5 body elements, if player take scissors
        music.scissors();
        if (snake.getBodySize() > 10) {
            snake.deleteBody(7);
        }
        element.interrupt();
    }
    void newLifeCheck() { // Every 1000 (lifeCheckPoint) score player gets new life
        if (snake.getScore() >= snake.getLifeCheckPoint()) {
            snake.setLifeCount(snake.getLifeCount() + 1);
            snake.setLifeCheckPoint(snake.getLifeCheckPoint() + 1000);
            music.life();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                        panel.animText("1up",new Point(snake.getX(), (int) (snake.getY() + 40 * scaleFactor)));
                    }catch (InterruptedException e) {
                        e.printStackTrace();}
                    }
                }).start();}
        }

    void coinTake (GameElement element) {
        music.coin();
        snake.setScore(snake.getScore() + 100);
        panel.animText("+100",new Point(snake.getX(),snake.getY()));
        newLifeCheck();
        snake.setEatFruitForCoin(0);
        element.interrupt();
        ((Coin) element).hide(true);
    }
    void snailTake (GameElement element) {
        if (snake.getSpeed() > 0) {
            snake.setSnakeSpeed(snake.getSpeed() - 1);
        }
        snake.setEatFruitForSpeed(0);
        music.speedDown();
        element.interrupt();
    }

    public void readyText () {
        snake.setGameLevel(level);
        if (!isContinue) {
            if (level != 0) {
                panel.setText("Level " + level, levelPoint,
                        160 * scaleFactor,
                        true);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                panel.setText(false);
            }
            panel.setText(levelName, textPoint, textSize, true);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            panel.setText(false);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            int count = 4;
            while (count-- > 1)
                try {
                    panel.setText(String.valueOf(count), numberPoint,
                            200 * scaleFactor,
                            true);
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            try {
                panel.setText("Go!", goPoint, 200 * scaleFactor, true);
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            panel.setText(false);
        }
        else {
            panel.setShowPause(true);
        }
        panel.setListenerOn();
        if (snake.isBonusLife()) {
            panel.setClickOn(true);
        }
    }
  abstract void setLevelHardness ();
}

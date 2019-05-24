package com.armageddon.luckysnake.common;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.armageddon.luckysnake.R;
import com.armageddon.luckysnake.levels.Ilevel;
import com.armageddon.luckysnake.levels.Level;
import com.armageddon.luckysnake.levels.Level1;
import com.armageddon.luckysnake.levels.Level2;
import com.armageddon.luckysnake.levels.Level3;
import com.armageddon.luckysnake.levels.Level4;
import com.armageddon.luckysnake.levels.Level5;
import com.armageddon.luckysnake.levels.Level6;
import com.armageddon.luckysnake.levels.Level7;
import com.armageddon.luckysnake.levels.LevelBonus;
import com.armageddon.luckysnake.levels.LevelGameOver;
import com.armageddon.luckysnake.levels.LevelMenu;
import com.armageddon.luckysnake.levels.LevelNextLevel;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class MainActivity extends Activity implements Serializable {
   transient private Handler handler = new Handler();
    private Ilevel level;
    private int screenHeight, screenWidth;
    private int bestRecord;
    private int currentScore;
    private int snakeLife;
    private int lifeCheckPoint = 1000;
    private int currentLevel;
    private int openedLevel = 1;
    private int nextLevel;

    private float scaleFactor;
    transient private Rect gameField = new Rect();
    private boolean musicOn = true;
    private boolean soundOn = true;
    private boolean vibrationOn = true;
    private boolean notSupportedRes;
    String modePng = "hd/png/";
    String modeGif = "hd/gif/";

    public int getOpenedLevel () {return openedLevel; }
    public void setOpenedLevel(int openedLevel) { this.openedLevel = openedLevel; }
    public int getCurrentLevel() { return currentLevel; }
    public int getNextLevel() {
        return nextLevel;
    }
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    public void setNextLevel(int nextLevel) {
        this.nextLevel = nextLevel;
    }
    public boolean isMusicOn() {
        return musicOn;
    }
    public boolean isSoundOn() {
        return soundOn;
    }
    public boolean isVibrationOn() {
        return vibrationOn;
    }
    public void setLifeCheckPoint(int lifeCheckPoint) {
        this.lifeCheckPoint = lifeCheckPoint;
    }
    public void setSnakeLife (int snakeLife) {this.snakeLife = snakeLife;}
    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }
    public void setVibrationOn(boolean vibrationOn) {
        this.vibrationOn = vibrationOn;
    }
    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }
    public void setBestRecord(int bestRecord) { this.bestRecord = bestRecord; }
    public Rect getGameField() {
        return gameField;
    }
    public float getScaleFactor() {
        return scaleFactor;
    }
    public int getCurrentScore() {
        return currentScore;
    }
    public int getSnakeLife() { return snakeLife;}
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getScreenWidth()  { return screenWidth; }
    public int getBestRecord() {
        return bestRecord;
    }
    public int getLifeCheckPoint() {
        return lifeCheckPoint;
    }
    public String getModeGif() {
        return modeGif;
    }
    public String getModePng() {
        return modePng;
    }
    public Handler getHandler() { return handler; }

    @Override
    public void onBackPressed() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        hideActionBar();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels;
        screenHeight = displaymetrics.heightPixels;

        if (screenHeight >= 1440) {
            scaleFactor = 2;
            modePng = "qhd/png/";
            modeGif = "qhd/gif/";
        } else if (screenHeight >= 1080) {
            scaleFactor = 1.5f;
            modePng = "fhd/png/";
            modeGif = "fhd/gif/";
        } else if (screenHeight >= 720) {
            scaleFactor = 1;
        } else {
            notSupportedRes = true;
        }

        gameField.set((int) (screenWidth - 1225 * scaleFactor),
                (int) (screenHeight - 660 * scaleFactor),
                (int) (screenWidth - 296 * scaleFactor),
                screenHeight);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" );
        System.out.println(displaymetrics.density);
        System.out.println(displaymetrics.densityDpi);
        System.out.println(screenWidth);
        System.out.println(screenHeight);
    }


    @Override
    protected void onPause() {
        Level.gameOnPause = false;
        level.setGameOnPause();
        level.stopAllMusic();
        level.getPanel().setListenerOff();
        level.getPanel().showElemets(false);
        level.snakeInterrupt();
        super.onPause();
    }


    public void exit (View view) {
        System.exit(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!notSupportedRes) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream
                        (new File(getFilesDir(), "base.dat")));
                playGame(ois.readInt());
                ois.close();

            } catch (IOException e) {
                playGame(-3);
            }
        } else {
            setContentView(R.layout.unsupported_resolution);
        }
    }

    public void playGame (int level) {
        switch (level) {
            case -3: this.level = new LevelMenu(this); break;
            case -2: this.level = new LevelGameOver(this); break;
            case -1: this.level = new LevelNextLevel(this); break;
            case 0: this.level = new LevelBonus(this); break;
            case 1: this.level = new Level1(this); break;
            case 2: this.level = new Level2(this); break;
            case 3: this.level = new Level3(this); break;
            case 4: this.level = new Level4(this); break;
            case 5: this.level = new Level5(this); break;
            case 6: this.level = new Level6(this); break;
            case 7: this.level = new Level7(this); break;
        }
    }

    private void hideActionBar () {
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(flags);

        // Code below is to handle presses of Volume up or Volume down.
        // Without this, after pressing volume buttons, the navigation bar will
        // show up and won't hide
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                {

                    @Override
                    public void onSystemUiVisibilityChange(int visibility)
                    {
                        if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                        {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        }
    }

package com.armageddon.luckysnake.controller;

import android.content.Context;
import android.graphics.Rect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.levels.LevelMenu;
import com.armageddon.luckysnake.view.ShowHoleIn;

import java.util.Map;

public class ControllerMenu implements Controller {
    private MainActivity activity;
    private Music music;
    private Vibrator vb;
    private ShowHoleIn panel;
    private int menuSelect;
    private int rule;
    private boolean musicOn;
    private boolean soundOn;
    private boolean vibrationOn;
    private Rect exitRect;
    private Rect playRect;
    private Rect recordRect;
    private Rect rulesRect;
    private Rect settingsRect;
    private Rect radioButtonRect1;
    private Rect radioButtonRect2;
    private Rect radioButtonRect3;
    private Rect continueRect;
    private String afterHoleAction;
    private LevelMenu level;

    public boolean isMusicOn() {
        return musicOn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public boolean isVibrationOn() {
        return vibrationOn;
    }

    public int getRule() {
        return rule;
    }

    public void doAfterHoleAction () {
       switch (afterHoleAction) {
           case "exit" : System.exit(0);
           case "play" : music.gameMusicStop(); activity.playGame(1);
       }
   }

   public ControllerMenu (Map <String, Rect> buttons,
                          ShowHoleIn panel,
                          MainActivity activity,
                          Music music,
                          LevelMenu level) {
        this.activity = activity;
        this.panel = panel;
        this.music = music;
        this.level = level;
        musicOn = activity.isMusicOn();
        soundOn = activity.isSoundOn();
        vibrationOn = activity.isVibrationOn();
        vb = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        exitRect = buttons.get("exit");
        playRect = buttons.get("play");
        recordRect = buttons.get("record");
        rulesRect = buttons.get("rules");
        settingsRect = buttons.get("settings");
        radioButtonRect1 = buttons.get("radiomusic");
        radioButtonRect2 = buttons.get("radiofx");
        radioButtonRect3 = buttons.get("radiovibrator");
        continueRect = buttons.get("continue");
    }

    public View.OnClickListener getOnClickListener1 () {
       return new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                menuSelect = 0;
           }
       };
    }

    public View.OnClickListener getOnClickListener2 () {
       return new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               rule++;
               if (rule > 1) {
                   menuSelect = 0;
                   rule = 0;
               }
           }
       };
    }

    public View.OnTouchListener getRadioListener () {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (radioButtonRect1.contains(x,y)){
                        musicOn = !musicOn;
                        if (musicOn){
                            music.gameMusicStart();
                        } else {
                            music.gameMusicStop();
                            music.gameMusicPrepare();
                        }
                        activity.setMusicOn(musicOn);
                        level.saveData();
                    }
                    if (radioButtonRect2.contains(x,y)){
                        soundOn = !soundOn;
                        if (soundOn) {
                            music.lifeStart();
                        } else {
                            music.lifeStop();
                            music.lifePrepare();
                        }
                        activity.setSoundOn(soundOn);
                        level.saveData();
                    }
                    if (radioButtonRect3.contains(x,y)){
                        vibrationOn = !vibrationOn;

                        if (vibrationOn) {
                            vb.vibrate(200);
                        }
                        activity.setVibrationOn(vibrationOn);
                        level.saveData();
                    }
                    if (continueRect.contains(x,y)){
                        menuSelect = 0;
                    }
                }
                return true;
            }
        };
    }

    public int getMenuSelect () {
        return menuSelect;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (playRect.contains(x, y)) {
                panel.showHoleIn();
                afterHoleAction = "play";
                return true;
            }

            if (recordRect.contains(x, y)) {
                menuSelect = 1;
                return true;
            }

            if (rulesRect.contains(x, y)) {
                menuSelect = 2;
                return true;
            }

            if (settingsRect.contains(x, y)) {
                menuSelect = 3;
                return true;
            }
            if (exitRect.contains(x, y)) {
                panel.showHoleIn();
                afterHoleAction = "exit";
            }
        }
        return true;
    }
}

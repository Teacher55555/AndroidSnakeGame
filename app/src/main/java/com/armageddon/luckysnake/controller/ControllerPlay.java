package com.armageddon.luckysnake.controller;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;

import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.levels.Ilevel;
import com.armageddon.luckysnake.levels.Level;
import com.armageddon.luckysnake.model.SnakeStep;
import com.armageddon.luckysnake.view.PanelGamePlay;

import java.util.Map;

public class ControllerPlay implements Controller {

        private Vibrator vb;
        private SnakeStep step;
        private Music music;
        private PanelGamePlay panel;

        private String afterHoleAction;

        private boolean isSettings;
        private boolean musicOn;
        private boolean soundOn;
        private boolean vibrationOn;


        private Rect pauseRect = new Rect();
        private Rect resumeButton;
        private Rect shareButton;
        private Rect likeButton;
        private Rect settingButton;
        private Rect homeButton;
        private Rect backButton;

        private Rect musicButton;
        private Rect soundButton;
        private Rect vibroButton;

        private Rect secondCloudRect = new Rect(
                (int) (Level.screenWidth - 230 * Level.scaleFactor),
                (int) (int) (200 * Level.scaleFactor),
                (int) (Level.screenWidth - 40 * Level.scaleFactor),
                (int) (int) (300 * Level.scaleFactor));

        private Region regUp = new Region();
        private Region regDown = new Region();
        private Region regLeft = new Region();
        private Region regRight = new Region();
        private Ilevel game;

        public void setIsSettings (boolean isSettings) {
            this.isSettings = isSettings;
        }

        public void setPauseButtons (Map <String,Rect> buttons ) {
            resumeButton = buttons.get("resume");
            likeButton = buttons.get("like");
            shareButton = buttons.get("share");
            settingButton = buttons.get("settings");
            homeButton = buttons.get("home");
            backButton = buttons.get("back");
            musicButton = buttons.get("music");
            soundButton = buttons.get("sound");
            vibroButton = buttons.get("vibro");
        }

    public void doAfterHoleAction () {
        switch (afterHoleAction) {
            case "replay" : ((Level) game).restartGame();  break;
            case "home" :  ((Level) game).home(); break;
        }
    }

    public boolean isSettings() {
        return isSettings;
    }

    public boolean isVibrationOn() {
        return vibrationOn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public ControllerPlay(SnakeStep step, Music music, PanelGamePlay panel, Vibrator vb, Level game) {
            this.step = step;
            this.music = music;
            this.panel = panel;
            this.vb = vb;
            this.game = game;
            this.vibrationOn = game.getActivity().isVibrationOn();
            this.musicOn = game.getActivity().isMusicOn();
            this.soundOn = game.getActivity().isSoundOn();

            pauseRect.set(Level.gameField.left,
                    Level.gameField.top,
                    (int) (Level.gameField.right - 50 * Level.scaleFactor),
                    Level.gameField.bottom);


            Path puthUp = new Path();
            Path puthDown = new Path();
            Path puthLeft = new Path();
            Path puthRight = new Path();

            puthRight.moveTo((int) (Level.screenWidth - 135 * Level.scaleFactor),
                    (int) (Level.screenHeight - 135 * Level.scaleFactor));
            puthRight.lineTo((Level.screenWidth),
                    (int) (Level.screenHeight - 270 * Level.scaleFactor));
            puthRight.lineTo(Level.screenWidth, Level.screenHeight);
            puthRight.close();

            puthDown.moveTo(Level.screenWidth, Level.screenHeight);
            puthDown.lineTo((int) (Level.screenWidth - 270 * Level.scaleFactor),
                    Level.screenHeight);
            puthDown.lineTo((int) (Level.screenWidth - 135 * Level.scaleFactor),
                    (int) (Level.screenHeight - 135 * Level.scaleFactor));
            puthDown.close();

            puthLeft.moveTo((int) (Level.screenWidth - 270 * Level.scaleFactor),
                    Level.screenHeight);
            puthLeft.lineTo((int) (Level.screenWidth - 270 * Level.scaleFactor),
                    (int) (Level.screenHeight - 270 * Level.scaleFactor));
            puthLeft.lineTo((int) (Level.screenWidth - 135 * Level.scaleFactor),
                    (int) (Level.screenHeight - 135 * Level.scaleFactor));
            puthLeft.close();

            puthUp.moveTo((int) (Level.screenWidth - 270 * Level.scaleFactor),
                    (int) (Level.screenHeight - 270 * Level.scaleFactor));
            puthUp.lineTo(Level.screenWidth, (int) (Level.screenHeight - 270 * Level.scaleFactor));
            puthUp.lineTo((int) (Level.screenWidth - 135 * Level.scaleFactor),
                    (int) (Level.screenHeight - 135 * Level.scaleFactor));
            puthUp.close();


            RectF rectF = new RectF();
            puthUp.computeBounds(rectF, true);
            regUp.setPath(puthUp,
                    new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));

            puthDown.computeBounds(rectF, true);
            regDown.setPath(puthDown, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));

            puthLeft.computeBounds(rectF, true);
            regLeft.setPath(puthLeft, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));

            puthRight.computeBounds(rectF, true);
            regRight.setPath(puthRight, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        }



        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (vibrationOn) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vb.vibrate(25);
                        break;

                    case MotionEvent.ACTION_UP:
                        vb.cancel();
                        break;
                }
            }

            synchronized (SnakeStep.class) {
                if (regUp.contains(x, y) && !step.getDirection().equals("DOWN")) {
                    step.setStepY((int) (-20 * Level.scaleFactor));
                    step.setStepX(0);
                }
                if (regDown.contains(x, y) && !step.getDirection().equals("UP")) {
                    step.setStepY((int) (20 * Level.scaleFactor));
                    step.setStepX(0);
                }
                if (regLeft.contains(x, y) && !step.getDirection().equals("RIGHT")) {
                    step.setStepX((int) (-20 * Level.scaleFactor));
                    step.setStepY(0);
                }
                if (regRight.contains(x, y) && !step.getDirection().equals("LEFT")) {
                    step.setStepX((int) (20 * Level.scaleFactor));
                    step.setStepY(0);
                }

                if (secondCloudRect.contains(x, y) && event.getAction() == MotionEvent.ACTION_UP) {
                    step.setBonusLife();
                }

            }

            if (Level.gameOnPause) {
                if (isSettings) {
                    if (musicButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP) {
                        musicOn = !musicOn;
                        music.setMusicOn(musicOn);
                        System.out.println("MUSIC ------ " + musicOn);
                        ((Level)game).getActivity().setMusicOn(musicOn);
                        ((Level)game).safeGame();
                    }

                    if (soundButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP) {
                        soundOn = !soundOn;
                        music.setSoundOn(soundOn);
                        ((Level)game).getActivity().setSoundOn(soundOn);
                        ((Level)game).safeGame();
                    }

                    if (vibroButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP) {
                        vibrationOn = !vibrationOn;
                        ((Level)game).getActivity().setVibrationOn(vibrationOn);
                        ((Level)game).safeGame();
                    }


                    if (backButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP ) {
                        isSettings = false;
                    }
                } else {

                    if (resumeButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP) {
                        game.setGameOnPause();
                    }

                   else if (likeButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP) {
                        ((Level) game).like();
                    }

                   else if (shareButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP) {
                        ((Level) game).share();
                    }

                   else if (settingButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP) {
                        isSettings = true;
                    }

                   else if (homeButton.contains(x,y)&& event.getAction() == MotionEvent.ACTION_UP) {
                        afterHoleAction = "home";
                        panel.showHoleIn();
                        isSettings = false;
                    }
                }
            }
            else if (pauseRect.contains(x,y) && event.getAction() == MotionEvent.ACTION_UP) {
                        game.setGameOnPause();
                }
                    return true;
        }
    }



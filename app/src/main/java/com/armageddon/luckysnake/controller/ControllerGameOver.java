package com.armageddon.luckysnake.controller;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.view.ShowHoleIn;

import java.util.Map;

public class ControllerGameOver implements Controller {

    private MainActivity activity;
    private Music music;
    private ShowHoleIn panel;
    private String afterHoleAction;

    private Rect replayButton;
    private Rect shareButton;
    private Rect likeButton;
    private Rect exitButton;
    private Rect homeButton;

    public void doAfterHoleAction () {
        switch (afterHoleAction) {
            case "exit" : System.exit(0); break;
            case "play" :
                music.stopAll();
                activity.setCurrentScore(0);
                activity.playGame(activity.getCurrentLevel());
                break;
            case "home" :
                music.stopAll();
                activity.playGame(-3);
                break;
        }
    }

    public ControllerGameOver (
            Map<String,
            Rect> buttons,
            ShowHoleIn panel,
            MainActivity activity,
            Music music) {
        this.music = music;
        this.activity = activity;
        this.panel = panel;
        exitButton = buttons.get("exit");
        replayButton = buttons.get("replay");
        shareButton = buttons.get("share");
        likeButton = buttons.get("like");
        homeButton = buttons.get("home");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_UP) {

            if (replayButton.contains(x, y)) {
                afterHoleAction = "play";
                panel.showHoleIn();
            }

            if (homeButton.contains(x, y)) {
                afterHoleAction = "home";
                panel.showHoleIn();
            }

            if (exitButton.contains(x, y)) {
                afterHoleAction = "exit";
                panel.showHoleIn();
            }

            if (likeButton.contains(x, y)) {
                String url =
                        "https://play.google.com/store/apps/details?id=com.armageddon.luckysnake";
                final Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                activity.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        activity.startActivity(i);
                    }
                });
            }

            if (shareButton.contains(x, y)) {

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
        }
        return true;
    }
}


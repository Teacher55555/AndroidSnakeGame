package com.armageddon.luckysnake.controller;

import android.view.View;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.view.ShowHoleIn;

public class ControllerNextLevel implements View.OnClickListener {

    private MainActivity activity;
    private int nextLevel;
    private ShowHoleIn panel;
    private Music music;

   public ControllerNextLevel (
           MainActivity activity,
           ShowHoleIn panel,
           int nextLevel,
           Music music) {

        this.activity = activity;
        this.nextLevel = nextLevel;
        this.panel = panel;
        this.music = music;
    }

    public void doAfterHoleAction () {
        music.stopAll();
        activity.playGame(nextLevel);
    }

    @Override
    public void onClick(View v) {
        panel.showHoleIn();
    }
}

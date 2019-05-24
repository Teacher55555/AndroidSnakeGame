package com.armageddon.luckysnake.levels;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.controller.ControllerGameOver;
import com.armageddon.luckysnake.view.Panel;
import com.armageddon.luckysnake.view.PanelGameOver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LevelGameOver implements Ilevel {

    MainActivity activity;
    Music music;
    final PanelGameOver panel;


    @Override
    public Panel getPanel() {
        return panel;
    }

    public LevelGameOver (final MainActivity activity) {

        this.activity = activity;
        music = new Music(activity,-2);
        panel = new PanelGameOver(activity,activity.getBestRecord(),activity.getCurrentScore());
        ControllerGameOver controller = new ControllerGameOver(panel.getButtons(),panel,activity, music);
        panel.setController(controller);
        if (activity.getCurrentScore() > activity.getBestRecord()) {
            music.victory();
            activity.setBestRecord(activity.getCurrentScore());
        } else {
            music.gameOverMusic();
        }
        saveData();
        activity.getHandler().post(new Runnable() {
            @Override
            public void run() {
                activity.setContentView(panel);
            }
        });
    }

    @Override
    public void snakeInterrupt() {}

    @Override
    public int getLevel() {
        return -3;
    }

    @Override
    public void setGameOnPause() { }

    @Override
    public void stopAllMusic() {
        music.stopAll();
    }

    private void saveData () {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(activity.getFilesDir(),"base.dat")));
            oos.writeInt(-3);
            oos.writeInt(activity.getOpenedLevel());
            oos.writeInt(activity.getBestRecord());
            oos.writeBoolean(activity.isMusicOn());
            oos.writeBoolean(activity.isSoundOn());
            oos.writeBoolean(activity.isVibrationOn());
            oos.flush();
            oos.close();
            System.out.println("Save ok !!!!!!!!!!!!!!!!!!!");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

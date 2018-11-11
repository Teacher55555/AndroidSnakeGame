package com.armageddon.luckysnake.levels;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.controller.ControllerNextLevel;
import com.armageddon.luckysnake.view.Panel;
import com.armageddon.luckysnake.view.PanelNextLevel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LevelNextLevel implements Ilevel {
    private boolean isContinue;
    Music music;
    MainActivity activity;
    final PanelNextLevel panel;


    @Override
    public Panel getPanel() {
        return panel;
    }

    public LevelNextLevel (final MainActivity activity) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(activity.getFilesDir(), "base.dat")));
                if (ois.readInt() == -1) {
                    activity.setCurrentLevel(ois.readInt());
                    activity.setNextLevel(ois.readInt());
                    activity.setBestRecord(ois.readInt());
                    activity.setCurrentScore(ois.readInt());
                    activity.setLifeCheckPoint(ois.readInt());
                    activity.setSnakeLife(ois.readInt());

                    activity.setMusicOn(ois.readBoolean());
                    activity.setSoundOn(ois.readBoolean());
                    activity.setVibrationOn(ois.readBoolean());
                    isContinue = true;
                }
                ois.close();

            } catch (IOException e) {
                System.out.println("Load Error !!!!!!!!");
            }

        System.out.println(activity.getCurrentLevel());
        System.out.println(activity.getNextLevel());

    this.activity = activity;
    music = new Music(activity,-2);
    panel = new PanelNextLevel(activity,activity.getCurrentLevel());
    ControllerNextLevel controller =
            new ControllerNextLevel(activity,panel,activity.getNextLevel(),music);
    panel.setController(controller);
    if (!isContinue) {
        music.victory();
    }
        activity.getHandler().post(new Runnable() {
        @Override
        public void run() {
            activity.setContentView(panel);
        }
    });
}

    @Override
    public void snakeInterrupt() {
        music.stopAll();
        saveData();
    }

    @Override
    public int getLevel() {
        return -1;
    }

    @Override
    public void setGameOnPause() {
    }

    @Override
    public void stopAllMusic() {
    }

    private void saveData () {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(activity.getFilesDir(),"base.dat")));
            oos.writeInt(getLevel());
            oos.writeInt(activity.getCurrentLevel());
            oos.writeInt(activity.getNextLevel());
            oos.writeInt(activity.getBestRecord());
            oos.writeInt(activity.getCurrentScore());
            oos.writeInt(activity.getLifeCheckPoint());
            oos.writeInt(activity.getSnakeLife());
            oos.writeBoolean(activity.isMusicOn());
            oos.writeBoolean(activity.isSoundOn());
            oos.writeBoolean(activity.isVibrationOn());
            oos.flush();
            oos.close();
            System.out.println("ZAPIS ok !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

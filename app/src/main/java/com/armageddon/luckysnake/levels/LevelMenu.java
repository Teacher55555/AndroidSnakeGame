package com.armageddon.luckysnake.levels;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.common.Music;
import com.armageddon.luckysnake.controller.ControllerMenu;
import com.armageddon.luckysnake.view.Panel;
import com.armageddon.luckysnake.view.PanelMenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LevelMenu implements Ilevel {

    MainActivity activity;
    Music music;
    final PanelMenu panel;

    @Override
    public Panel getPanel() {
        return panel;
    }

    public LevelMenu (final MainActivity activity) {

       try {
           ObjectInputStream ois = new ObjectInputStream(
                   new FileInputStream(
                           new File(activity.getFilesDir(), "base.dat")));
           ois.readInt();
           activity.setBestRecord(ois.readInt());
           activity.setMusicOn(ois.readBoolean());
           activity.setSoundOn(ois.readBoolean());
           activity.setVibrationOn(ois.readBoolean());
           ois.close();

//            setPause();
       } catch (IOException e) {
           System.out.println("Load Error !!!!!!!!");

       }



        this.activity = activity;
        music = new Music(activity,-3);
        panel = new PanelMenu(activity);
        ControllerMenu controller =
                new ControllerMenu(panel.getButtons(),panel,activity,music, this);
        panel.setController(controller);
        music.gameMusic();
        saveData();
        activity.getHandler().post(new Runnable() {
           @Override
           public void run() {
               activity.setContentView(panel);
           }
       });
//        activity.setContentView(panel);
    }

    @Override
    public void snakeInterrupt() {}

    @Override
    public int getLevel() {
        return -3;
    }

    @Override
    public void setGameOnPause() {
        music.gameMusicStop();
    }

    @Override
    public void stopAllMusic() {
        music.stopAll();
    }

    public void saveData () {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(activity.getFilesDir(),"base.dat")));
            oos.writeInt(-3);
            oos.writeInt(activity.getBestRecord());
            oos.writeBoolean(activity.isMusicOn());
            oos.writeBoolean(activity.isSoundOn());
            oos.writeBoolean(activity.isVibrationOn());
            oos.flush();
            oos.close();
            System.out.println("Save ok !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}

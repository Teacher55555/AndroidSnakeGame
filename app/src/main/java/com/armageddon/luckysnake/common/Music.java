package com.armageddon.luckysnake.common;

import android.media.MediaPlayer;
import com.armageddon.luckysnake.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Music {

   private boolean dig1Play;
   private boolean dig2Play;


    private MediaPlayer gameMusic;
    private MediaPlayer gameOverMusic;
    private MediaPlayer coin;
    private MediaPlayer crash;
    private MediaPlayer life;
    private MediaPlayer kiss;
    private MediaPlayer eat;
    private MediaPlayer eat2;
    private MediaPlayer scissors;
    private MediaPlayer speedDown;
    private MediaPlayer speedUp;
    private MediaPlayer victory;
    private MediaPlayer lawnmover;
    private MediaPlayer electroCrash;
    private MediaPlayer electroBall;
    private MediaPlayer fruitSmash;
    private MediaPlayer elephant;
    private MediaPlayer laserCharge;
    private MediaPlayer laserCharge2;
    private MediaPlayer laserCharge3;
    private MediaPlayer laserCharge4;
    private MediaPlayer laserCharge5;
    private MediaPlayer laserFire;
    private MediaPlayer laserFire22;
    private MediaPlayer laserFire3;
    private MediaPlayer laserFire4;
    private MediaPlayer laserFire5;
    private MediaPlayer alienShip;
    private MediaPlayer alienMonster;
    private MediaPlayer laserFire2;
    private MediaPlayer rocketStart;
    private MediaPlayer explosion;
    private MediaPlayer swallow;
    private MediaPlayer tornado;
    private MediaPlayer mole1;
    private MediaPlayer mole2;
    private MediaPlayer dig1;
    private MediaPlayer dig2;
    private MediaPlayer pig;
    private MediaPlayer pigSpeedUp;
    private MediaPlayer yawn;
    private MediaPlayer wakeUpCall;
    private MediaPlayer zombie;


    private boolean musicOn;
    private boolean soundOn;

   private ArrayList <MediaPlayer> allMusic = new ArrayList<>();

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public void gameMusicStart () {gameMusic.start(); }
    public void gameMusic () {if (musicOn) gameMusic.start(); }
    public void gameMusicPause () {if (musicOn) gameMusic.pause(); }
    public void gameMusicStop () {
        if (gameMusic != null) {
            gameMusic.stop();
        }
    }
    public void gameMusicPrepare() {
        try {
            if (gameMusic != null) {
                gameMusic.prepare();
            }
        } catch (IOException e) {e.printStackTrace();}
    }
    public void wakeUpCall () {if (soundOn) wakeUpCall.start();}
    public void gameOverMusic () {if (musicOn) gameOverMusic.start();}
    public void mole1 () {if (soundOn) mole1.start();}
    public void mole2 () {if (soundOn) mole2.start();}
    public void dig1 () {if (soundOn) dig1.start();}
    public void dig2 () {if (soundOn) dig2.start();}
    public void dig1Pause () {if (soundOn) dig1.pause();}
    public void dig2Pause () {if (soundOn) dig2.pause();}
    public void dig1Stop () {if (soundOn) {
        dig1.stop();
        try {
            dig1.prepare();
        } catch (IOException e) {e.printStackTrace();}
    }}
    public void dig2Stop () { if (soundOn) {
            dig2.stop();
            try {
                dig2.prepare();
            } catch (IOException e) {e.printStackTrace();}
        }}
    public void digsPause () {
          if (dig1.isPlaying() && soundOn) {
              dig1Play = true;
              dig1Pause();
          }
          if (dig2.isPlaying() && soundOn) {
              dig2Play = true;
              dig2Pause();
          }
    }

    public void digsResume () {
            if (dig1Play && soundOn ) {
                dig1();
            }
            if (dig2Play && soundOn ) {
                dig2();
            }
    }

    public void yawn () {if (soundOn) yawn.start();}
    public void coin () {if (soundOn) coin.start();}
    public void tornado () {if (soundOn) tornado.start();}
    public void tornadoPause () {if (soundOn) tornado.pause();}
    public void crash () { if (soundOn) crash.start();}
    public void life () {if (soundOn) life.start();}
    public void lifeStart () {if (soundOn) life.start();}
    public void lifeStop () {life.stop();}
    public void lifePrepare() {
        try {
            life.prepare();
        } catch (IOException e) {e.printStackTrace();}
    }
    public void kiss () {if (soundOn) kiss.start();}
    public void eat () {
        if (soundOn) {
            if (eat.isPlaying()) {
                eat2.start();
            } else {
                eat.start();
            }
        }
    }
    public void zombie () {if (soundOn) zombie.start();}
    public void zombiePause () {if (soundOn) zombie.pause();}
    public void swallow () {if (soundOn) swallow.start();}
    public void scissors () {if (soundOn) scissors.start();}
    public void speedDown () {if (soundOn) speedDown.start();}
    public void speedUp () {if (soundOn) speedUp.start();}
    public void victory () {if (soundOn) victory.start();}
    public void lawnmover () {if (soundOn) lawnmover.start();}
    public void pigSpeedUp () {if (soundOn) pigSpeedUp.start();}
    public void pig () {if (soundOn) pig.start();}
    public void lawnmoverPause () {if (soundOn) lawnmover.pause();}
    public void electroCrash () {if (soundOn) electroCrash.start();}
    public void electroBall () {if (soundOn) electroBall.start();}
    public void fruitSmash () {if (soundOn) fruitSmash.start();}
    public void elephant () {if (soundOn) elephant.start();}
    public void laserCharge () {
        if (soundOn) {
            if (laserCharge.isPlaying() && laserCharge2.isPlaying() && laserCharge3.isPlaying()
                    && laserCharge4.isPlaying()) {
                laserCharge5.start();
            } else if (laserCharge.isPlaying() && laserCharge2.isPlaying()
                    && laserCharge3.isPlaying()) {
                laserCharge4.start();
            } else if (laserCharge.isPlaying() && laserCharge2.isPlaying()) {
                laserCharge3.start();
            } else if (laserCharge.isPlaying()) {
                laserCharge2.start();
            } else {
                laserCharge.start();
            }
        }}

    public void laserFire () {
        if (soundOn) {
            if (laserFire.isPlaying() && laserFire22.isPlaying() && laserFire3.isPlaying()
                    && laserFire4.isPlaying()) {
                laserFire5.start();
            } else if (laserFire.isPlaying() && laserFire22.isPlaying()
                    && laserFire3.isPlaying()) {
                laserFire4.start();
            } else if (laserFire.isPlaying() && laserFire22.isPlaying()) {
                laserFire3.start();
            } else if (laserFire.isPlaying()) {
                laserFire22.start();
            } else {
                laserFire.start();
            }
        }}
    public void alienShip () {if (soundOn) alienShip.start();}
    public void alienMonster () {if (soundOn) alienMonster.start();}
    public void alienMonsterPause () {if (soundOn) alienMonster.pause();}
    public void laserFire2 () {if (soundOn) laserFire2.start();}
    public void rocket () {if (soundOn) rocketStart.start();}
    public void explosion () {if (soundOn) explosion.start();}

    public void stopAll (){
        for (MediaPlayer player : allMusic) {
            soundOn = false;
            musicOn = false;
            if (player != null) {
                player.release();
            }
        }
    }

    public Music (MainActivity activity, int level) {
        musicOn = activity.isMusicOn();
        soundOn = activity.isSoundOn();

        switch (level) {
            case -3:
                gameMusic = MediaPlayer.create(activity, R.raw.menu);
                gameMusic.setLooping(true);
                life = MediaPlayer.create(activity, R.raw.life);
                break;
            case -2:
                gameOverMusic = MediaPlayer.create(activity, R.raw.gameover2);
                gameOverMusic.setLooping(true);
                victory = MediaPlayer.create(activity, R.raw.victory);
                break;
            case 0:
                gameMusic = MediaPlayer.create(activity, R.raw.menu);
                gameMusic.setLooping(true);
                break;
            case 1:
                gameMusic = MediaPlayer.create(activity, R.raw.level1);
                mole1 = MediaPlayer.create(activity, R.raw.mole1);
                mole2 = MediaPlayer.create(activity, R.raw.mole2);
                dig1 = MediaPlayer.create(activity, R.raw.dig1);
                dig2 = MediaPlayer.create(activity, R.raw.dig2);
                dig1.setLooping(true);
                dig2.setLooping(true);
                gameMusic.setLooping(true);
                break;
            case 2:
                gameMusic = MediaPlayer.create(activity, R.raw.level2);
                gameMusic.setLooping(true);
                lawnmover = MediaPlayer.create(activity, R.raw.lawnmover);
                lawnmover.setLooping(true);
                pig = MediaPlayer.create(activity, R.raw.pig);
                pigSpeedUp = MediaPlayer.create(activity, R.raw.pigspeedup);
                break;
            case 3:
                gameMusic = MediaPlayer.create(activity, R.raw.level33);
                gameMusic.setLooping(true);
                tornado = MediaPlayer.create(activity, R.raw.tornado);
                tornado.setLooping(true);
                electroCrash = MediaPlayer.create(activity, R.raw.electrocrash);
                electroBall = MediaPlayer.create(activity, R.raw.electroball);
                break;
            case 4:
                gameMusic = MediaPlayer.create(activity, R.raw.level4);
                gameMusic.setLooping(true);
                elephant = MediaPlayer.create(activity, R.raw.elephant);
                fruitSmash = MediaPlayer.create(activity, R.raw.fruitsmash);
                break;
            case 5:
                gameMusic = MediaPlayer.create(activity, R.raw.level5);
                gameMusic.setLooping(true);
                alienMonster = MediaPlayer.create(activity, R.raw.alienmonster);
                alienShip = MediaPlayer.create(activity, R.raw.alienship);
                laserCharge = MediaPlayer.create(activity, R.raw.lasercharge);
                laserCharge2 = MediaPlayer.create(activity, R.raw.lasercharge);
                laserCharge3 = MediaPlayer.create(activity, R.raw.lasercharge);
                laserCharge4 = MediaPlayer.create(activity, R.raw.lasercharge);
                laserCharge5 = MediaPlayer.create(activity, R.raw.lasercharge);
                laserFire = MediaPlayer.create(activity, R.raw.laserfire);
                laserFire22 = MediaPlayer.create(activity, R.raw.laserfire);
                laserFire3 = MediaPlayer.create(activity, R.raw.laserfire);
                laserFire4 = MediaPlayer.create(activity, R.raw.laserfire);
                laserFire5 = MediaPlayer.create(activity, R.raw.laserfire);
                break;
            case 6:
                gameMusic = MediaPlayer.create(activity, R.raw.level6);
                gameMusic.setLooping(true);
                explosion = MediaPlayer.create(activity, R.raw.explosion);
                rocketStart = MediaPlayer.create(activity, R.raw.rocketstart);
                laserFire2 = MediaPlayer.create(activity, R.raw.laserfire2);
                alienMonster = MediaPlayer.create(activity, R.raw.alienmonster);
                alienShip = MediaPlayer.create(activity, R.raw.alienship);
                laserCharge = MediaPlayer.create(activity, R.raw.lasercharge);
                laserCharge2 = MediaPlayer.create(activity, R.raw.lasercharge);
                laserCharge3 = MediaPlayer.create(activity, R.raw.lasercharge);
                laserCharge4 = MediaPlayer.create(activity, R.raw.lasercharge);
                laserCharge5 = MediaPlayer.create(activity, R.raw.lasercharge);
                laserFire = MediaPlayer.create(activity, R.raw.laserfire);
                mole1 = MediaPlayer.create(activity, R.raw.mole1);
                victory = MediaPlayer.create(activity, R.raw.victory);
                zombie = MediaPlayer.create(activity, R.raw.zombie);
                break;
            case 7:
                gameMusic = MediaPlayer.create(activity, R.raw.level7);
                gameMusic.setLooping(true);
                yawn = MediaPlayer.create(activity, R.raw.yawn);
                wakeUpCall = MediaPlayer.create(activity, R.raw.wakeupcall);
                kiss = MediaPlayer.create(activity, R.raw.kiss);


                break;
        }

        if (level >= 0) {
            coin = MediaPlayer.create(activity, R.raw.coin);
            eat = MediaPlayer.create(activity, R.raw.eat);
            eat2 = MediaPlayer.create(activity, R.raw.eat2);
            crash = MediaPlayer.create(activity, R.raw.crash);
            life = MediaPlayer.create(activity, R.raw.life);
            scissors = MediaPlayer.create(activity, R.raw.scissors);
            speedDown = MediaPlayer.create(activity, R.raw.speeddown);
            speedUp = MediaPlayer.create(activity, R.raw.speedup);
            swallow = MediaPlayer.create(activity, R.raw.swallow);
        }

        Collections.addAll(
         allMusic,
         gameMusic,
         gameOverMusic,
         swallow,
         coin,
         crash,
         life,
         kiss,
         eat,
         scissors,
         speedDown,
         speedUp,
         victory,
         lawnmover,
         electroCrash,
         electroBall,
         fruitSmash,
         elephant,
         laserCharge,
         laserCharge2,
         laserCharge3,
         laserCharge4,
         laserCharge5,
         laserFire,
         laserFire2,
         laserFire22,
         laserFire3,
         laserFire4,
         laserFire5,
         alienShip,
         alienMonster,
         rocketStart,
         explosion,
         tornado,
         pig,
         pigSpeedUp,
         eat2,
         mole1,
         mole2,
         dig1,
         dig2,
         yawn,
         wakeUpCall,
         zombie
        );
    }
}


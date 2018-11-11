package com.armageddon.luckysnake.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.controller.ControllerPlay;
import com.armageddon.luckysnake.levels.Level;
import com.armageddon.luckysnake.model.AlienFlagShip;
import com.armageddon.luckysnake.model.AlienMonster;
import com.armageddon.luckysnake.model.AlienShip;
import com.armageddon.luckysnake.model.Coin;
import com.armageddon.luckysnake.model.Elephant;
import com.armageddon.luckysnake.model.Fruit;
import com.armageddon.luckysnake.model.Fruits;
import com.armageddon.luckysnake.model.GameElement;
import com.armageddon.luckysnake.model.Gecko;
import com.armageddon.luckysnake.model.LawnMover;
import com.armageddon.luckysnake.model.Lighting;
import com.armageddon.luckysnake.model.LightningBall;
import com.armageddon.luckysnake.model.Mole;
import com.armageddon.luckysnake.model.Puddle;
import com.armageddon.luckysnake.model.Rocket;
import com.armageddon.luckysnake.model.Scissors;
import com.armageddon.luckysnake.model.Snail;
import com.armageddon.luckysnake.model.Snake;
import com.armageddon.luckysnake.model.Stump;
import com.armageddon.luckysnake.model.Tornado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifDrawable;

public class PanelGamePlay extends Panel implements ShowHoleIn {

    private ArrayList<GameElement> elements = new ArrayList<>();

    boolean showClock;
    boolean showLove;

    private Map<String,Rect> buttons = new HashMap<>();

    private Bitmap background;
    private Bitmap snakeHeadUp;
    private Bitmap snakeHeadDown;
    private Bitmap snakeHeadLeft;
    private Bitmap snakeHeadUpGirl;
    private Bitmap snakeHeadLeftGirl;
    private Bitmap snakeHeadRightGirl;
    private Bitmap snakeHeadDownGirl;
    private Bitmap headLeftGirlSleep;
    private Bitmap snakeHeadRight;
    private Bitmap snakeHeadUpBreak;
    private Bitmap snakeHeadDownBreak;
    private Bitmap snakeHeadLeftBreak;
    private Bitmap snakeHeadRightBreak;
    private Bitmap snakeBodyHorizontal;
    private Bitmap snakeBodyVertical;
    private Bitmap snakeBodyAngleLeftUp;
    private Bitmap snakeBodyAngleLeftDown;
    private Bitmap snakeBodyAngleRightUp;
    private Bitmap snakeBodyAngleRightDown;
    private Bitmap snakeTailUp;
    private Bitmap snakeTailDown;
    private Bitmap snakeTailLeft;
    private Bitmap snakeTailRight;
    private Bitmap stumpBitmap;
    private Bitmap soil;
    private Bitmap speed20;
    private Bitmap speed40;
    private Bitmap speed60;
    private Bitmap speed80;
    private Bitmap speed100;
    private Bitmap life;
    private Bitmap fruitSmash;
    private Bitmap laser;
    private Bitmap pillow;
    private Bitmap blanket;
    private Bitmap backet;


    private GifDrawable fruitCup;
    private GifDrawable apple;
    private GifDrawable strawberry;
    private GifDrawable banana;
    private GifDrawable cherry;
    private GifDrawable watermelon;
    private GifDrawable grape;
    private GifDrawable kiwi;
    private GifDrawable snail;
    private GifDrawable scissors;
    private GifDrawable coin;
    private GifDrawable rain;
    private GifDrawable mole;
    private GifDrawable pig;
    private GifDrawable zzz;
    private GifDrawable clock;
    private GifDrawable heart;

    private GifDrawable lawnmover;
    private GifDrawable lighting;
    private GifDrawable puddle;
    private GifDrawable puddle2;
    private GifDrawable tornado;
    private GifDrawable lightningball;
    private GifDrawable spark;
    private GifDrawable geckoRight;
    private GifDrawable geckoLeft;
    private GifDrawable geckoUp;
    private GifDrawable geckoDown;
    private GifDrawable geckoRightReady;
    private GifDrawable geckoLeftReady;
    private GifDrawable geckoUpReady;
    private GifDrawable geckoDownReady;
    private GifDrawable geckoAnimation;
    private GifDrawable elephantRight;
    private GifDrawable elephantLeft;
    private GifDrawable alienShip1;
    private GifDrawable alienFlagShip;
    private GifDrawable explosion;
    private GifDrawable explosion1;
    private GifDrawable explosion2;
    private GifDrawable explosion3;
    private GifDrawable explosion4;
    private GifDrawable explosion5;
    private GifDrawable fireonship;
    private GifDrawable whiteFlag;
    private GifDrawable target;
    private GifDrawable alienMonsterRight;
    private GifDrawable alienMonsterLeft;
    private GifDrawable monsterFireRight;
    private GifDrawable monsterFireLeft;
    private GifDrawable teleport;
    private GifDrawable zombieRight;
    private GifDrawable zombieLeft;
    private GifDrawable rocket;
    private GifDrawable smoke;
    private GifDrawable explosionOnShip;
    private GifDrawable explosionOnShip2;
    private GifDrawable record;
    private GifDrawable repeatButton;
    private GifDrawable shareButton;
    private GifDrawable likeButton;
    private GifDrawable exitButton;
    private GifDrawable homeButton;
    private GifDrawable settingsButton;
    private GifDrawable backButton;
    private GifDrawable click;
    private GifDrawable play;


    private Point animTextPoint1 = new Point();
    private Point animTextPoint2 = new Point();
    private Point animTextPoint3 = new Point();

    TextPaint animTextPaint1 = new TextPaint();
    TextPaint animTextPaint2 = new TextPaint();
    TextPaint animTextPaint3 = new TextPaint();

    private String animText1;
    private String animText2;
    private String animText3;

    private boolean clickOn;

    Rect repeatRect = new Rect();
    Rect shareRect = new Rect();
    Rect likeRect = new Rect();
    Rect exitRect = new Rect();
    Rect settingsRect = new Rect();
    Rect homeRect = new Rect();
    Rect continueRect = new Rect();
    Rect musicRect = new Rect();
    Rect soundRect = new Rect();
    Rect vibroRect = new Rect();
    Rect backRect = new Rect();

    Point resumePoint = new Point();
    Point likePoint = new Point();
    Point sharePoint = new Point();
    Point settingsPoint = new Point();
    Point homePoint = new Point();
    Point pausePoint = new Point();
    Point continuePoint = new Point();
    Point musicPoint = new Point();
    Point soundFxPoint = new Point();
    Point vibrationPoint = new Point();
    Point backPoint = new Point();

    private boolean showHoleIn;
    private boolean smashFruit;
    private boolean showText;
    private boolean showPause;
    private boolean showHoleOut = true;
    private Point smashFruitPoint = new Point();
    private Point textPoint = new Point();
    private Paint finishLinePaint = new Paint();
    private int level;

    private String text;

    Paint redBacket = new Paint();
    Paint yellowBacket = new Paint();
    Paint greenBacket = new Paint();
    Paint magentaBacket = new Paint();

    Paint red = new Paint();
    Paint green = new Paint();
    Paint blue = new Paint();
    Paint grey = new Paint();
    Paint black = new Paint();

    Paint buttonWhite = new Paint();
    Paint buttonBlue = new Paint();
    Paint continuePaint = new Paint();

    Paint bossEnergy = new Paint();
    Paint bossEnergyBorder = new Paint();

    Point prevBody = new Point();
    Point nextBody = new Point();



    Point up = new Point();
    Point right = new Point();
    Point down = new Point();
    Point left = new Point();
    Rect bossEnergyBorderRect = new Rect();
    Rect bossEnergyRect = new Rect();
    Rect finishRect = new Rect();
    ControllerPlay controller;

    public PanelGamePlay(MainActivity activity, int level) {
        super(activity);
        this.level = level;
        textPaint.setStrokeWidth(2.5f * scaleFactor); //было 3
        textPaint.setTextSize(160 * scaleFactor);
        scorePaint.setTextSize(35 * scaleFactor);
        scorePaint.setTypeface(Typeface.create(myFont,Typeface.NORMAL));
        scorePaint.setStyle(Paint.Style.FILL);
        scorePaint.setColor(Color.rgb(14, 15, 100));
        finishLinePaint.setColor(Color.WHITE);
        holePaint.setColor(Color.BLACK);
        holePaint.setStrokeWidth(830 * scaleFactor); // 800
        holePaint.setStyle(Paint.Style.STROKE);

        if (scaleFactor == 2) {
            holeRadius = 740;
        }

        else if (scaleFactor == 1.5) {
            holeRadius = 600;
        }

        else if (scaleFactor == 1) {
            holeRadius = 400;
        }
        holePaint.setAlpha(200); // 200

        bossEnergyBorder.setColor(Color.rgb(110, 49, 178));
        bossEnergyBorder.setStrokeWidth(2 * scaleFactor);
        bossEnergyBorder.setStyle(Paint.Style.STROKE);
        bossEnergy.setColor(Color.rgb(110, 49, 178));

        continuePaint.setTypeface(Typeface.create(myFont,Typeface.BOLD));
        continuePaint.setAntiAlias(true);
        continuePaint.setStrokeWidth(1 *scaleFactor);
        continuePaint.setTextSize(90 * scaleFactor);

        animTextPaint1.setTypeface(Typeface.create(myFont,Typeface.BOLD));
        animTextPaint1.setAntiAlias(true);
        animTextPaint1.setTextSize(35 * scaleFactor);
        animTextPaint1.setColor(Color.WHITE);

        animTextPaint2.setTypeface(Typeface.create(myFont,Typeface.BOLD));
        animTextPaint2.setAntiAlias(true);
        animTextPaint2.setTextSize(35 * scaleFactor);
        animTextPaint2.setColor(Color.WHITE);

        animTextPaint3.setTypeface(Typeface.create(myFont,Typeface.BOLD));
        animTextPaint3.setAntiAlias(true);
        animTextPaint3.setTextSize(35 * scaleFactor);
        animTextPaint3.setColor(Color.WHITE);

        redBacket.setColor(Color.RED);
        yellowBacket.setColor(Color.YELLOW);
        greenBacket.setColor(Color.GREEN);
        magentaBacket.setColor(Color.MAGENTA);

        red.setColor(Color.rgb(180,0,0));
        blue.setColor(Color.BLUE);
        green.setColor(Color.GREEN);
        grey.setColor(Color.GRAY);

        red.setAlpha(120);
        blue.setAlpha(90);
        green.setAlpha(90);
        grey.setAlpha(140);
        black.setAlpha(100);

        buttonWhite.setColor(Color.WHITE);
        buttonWhite.setStyle(Paint.Style.STROKE);
        buttonWhite.setAntiAlias(true);
        buttonWhite.setStrokeWidth(10 * scaleFactor);

        buttonBlue.setColor(Color.rgb(84,136,199));
        buttonBlue.setStyle(Paint.Style.FILL);
        buttonBlue.setAntiAlias(true);
        buttonBlue.setStrokeWidth(10 * scaleFactor);

        try {
            background = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "background.png"));
            snakeHeadUp = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "headup.png"));
            snakeHeadDown = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "headdown.png"));
            snakeHeadLeft = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "headleft.png"));
            snakeHeadRight = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "headright.png"));
            snakeHeadUpBreak = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "headupbreak.png"));
            snakeHeadDownBreak = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "headdownbreak.png"));
            snakeHeadLeftBreak = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "headleftbreak.png"));
            snakeHeadRightBreak = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "headrightbreak.png"));
            snakeBodyHorizontal = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "bodyhorizontal.png"));
            snakeBodyVertical = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "bodyvertical.png"));
            snakeBodyAngleLeftUp = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "bodyangleleftup.png"));
            snakeBodyAngleLeftDown = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "bodyangleleftdown.png"));
            snakeBodyAngleRightUp = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "bodyanglerightup.png"));
            snakeBodyAngleRightDown = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "bodyanglerightdown.png"));
            snakeTailUp = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "tailup.png"));
            snakeTailDown = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "taildown.png"));
            snakeTailLeft = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "tailleft.png"));
            snakeTailRight = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "tailright.png"));
            speed20 = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "speed20.png"));
            speed40 = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "speed40.png"));
            speed60 = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "speed60.png"));
            speed80 = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "speed80.png"));
            speed100 = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "speed100.png"));
            life = BitmapFactory.decodeStream
                    (activity.getAssets().open(modePng + "life.png"));
            apple = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "apple3.gif"));
            fruitCup = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "fruits.gif"));
            strawberry = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "strawberry.gif"));
            banana = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "banana.gif"));
            cherry = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "cherry.gif"));
            watermelon = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "watermelon.gif"));
            grape = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "grape2.gif"));
            kiwi = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "kiwi.gif"));
            snail = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "snail.gif"));
            coin = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "coin.gif"));
            scissors = new GifDrawable
                    (activity.getAssets().openFd(modeGif + "scissors.gif"));
            record = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "record.gif"));
            repeatButton = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "repeat.gif"));
            shareButton  = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "share.gif"));
            likeButton  = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "like.gif"));
            exitButton  = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "exit.gif"));
            settingsButton  = new GifDrawable(
                        activity.getAssets().openFd( modeGif + "settingspause.gif"));
            homeButton  = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "home.gif"));
            backButton  = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "back.gif"));
            click  = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "click.gif"));
            play  = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "play2.gif"));

            switch (level) {
                case 1:
                    stumpBitmap = BitmapFactory.decodeStream
                            (activity.getAssets().open(modePng + "stump2.png"));
                    soil = BitmapFactory.decodeStream
                            (activity.getAssets().open(modePng + "soil.png"));
                    mole = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "mole.gif"));
                    break;
                case 2:
                    lawnmover = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "lawnmover.gif"));
                    pig = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "pig.gif"));
                    break;
                case 3:
                    rain = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "rain.gif"));
                    lighting = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "lighting.gif"));
                    puddle = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "puddle.gif"));
                    puddle2 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "puddle2.gif"));
                    lightningball = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "lightningball.gif"));
                    spark = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "sparks.gif"));
                    tornado = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "tornado.gif"));
                    rain.setAlpha(180);
                    puddle.setAlpha(200);
                    puddle2.setAlpha(200);
                    lightningball.setAlpha(200);
                    break;
                case 4:
                    geckoRight = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "geckoright.gif"));
                    geckoLeft = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "geckoleft.gif"));
                    geckoUp = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "geckoup.gif"));
                    geckoDown = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "geckodown.gif"));
                    geckoLeftReady = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "geckoleftready.gif"));
                    geckoRightReady = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "geckorightready.gif"));
                    geckoUpReady = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "geckoupready.gif"));
                    geckoDownReady = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "geckodownready.gif"));
                    elephantRight = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "elephantright.gif"));
                    elephantLeft = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "elephantleft.gif"));
                    fruitSmash = BitmapFactory.decodeStream
                            (activity.getAssets().open(modePng + "fruitsmash.png"));
                    break;
                case 5:
                    alienShip1 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "alienship.gif"));
                    explosion = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosion.gif"));
                    explosion1 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosion.gif"));
                    explosion2 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosion.gif"));
                    explosion3 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosion.gif"));
                    explosion4 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosion.gif"));
                    explosion5 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosion.gif"));
                    explosion1.stop();
                    explosion2.stop();
                    explosion3.stop();
                    explosion4.stop();
                    explosion5.stop();

                    target = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "target.gif"));
                    alienMonsterRight = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "alienmonsterright.gif"));
                    alienMonsterLeft = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "alienmonsterleft.gif"));
                    monsterFireLeft = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "fireleft.gif"));
                    monsterFireRight = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "fireright.gif"));
                    teleport = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "teleport.gif"));
                    break;
                case 6:
                    laser = BitmapFactory.decodeStream
                            (activity.getAssets().open(modePng + "laser.png"));
                    alienShip1 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "alienship.gif"));
                    alienFlagShip = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "alienflagship.gif"));
                    rocket = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "rocket.gif"));
                    explosion1 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosion.gif"));
                    explosionOnShip = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosiononship1.gif"));
                    explosionOnShip.stop();
                    explosionOnShip2 = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "explosiononship2.gif"));
                    explosionOnShip2.stop();
                    fireonship = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "fireonship.gif"));
                    whiteFlag = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "flag.gif"));
                    smoke = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "smoke.gif"));
                    target = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "target.gif"));
                    teleport = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "teleport.gif"));
                    zombieRight = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "zombieright.gif"));
                    zombieLeft = new GifDrawable
                            (activity.getAssets().openFd(modeGif + "zombieleft.gif"));
                    break;

                    case 7:
                        snakeHeadLeftGirl = BitmapFactory.decodeStream
                                (activity.getAssets().open(modePng + "headleftgirl.png"));
                        snakeHeadUpGirl = BitmapFactory.decodeStream
                                (activity.getAssets().open(modePng + "headupgirl.png"));
                        snakeHeadDownGirl = BitmapFactory.decodeStream
                                (activity.getAssets().open(modePng + "headdowngirl.png"));
                        snakeHeadRightGirl = BitmapFactory.decodeStream
                                (activity.getAssets().open(modePng + "headrightgirl.png"));
                        pillow = BitmapFactory.decodeStream
                                (activity.getAssets().open(modePng + "pillow.png"));
                        blanket = BitmapFactory.decodeStream
                                (activity.getAssets().open(modePng + "blanket.png"));
                        headLeftGirlSleep = BitmapFactory.decodeStream
                                (activity.getAssets().open(modePng + "headleftgirlsleep.png"));
                        zzz = new GifDrawable
                                (activity.getAssets().openFd(modeGif + "zzz.gif"));
                        clock = new GifDrawable
                                (activity.getAssets().openFd(modeGif + "clock.gif"));
                        heart = new GifDrawable
                                (activity.getAssets().openFd(modeGif + "love.gif"));
                        backet = BitmapFactory.decodeStream
                                (activity.getAssets().open(modePng + "backet.png"));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        backRect.set(
                (int)(gameField.left + 170 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((gameField.left + 170 * scaleFactor) + repeatButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + repeatButton.getMinimumHeight()));

        musicRect.set(
                (int)(backRect.right + 60 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((backRect.right + 60 * scaleFactor) + likeButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + likeButton.getMinimumHeight()));

        soundRect.set(
                (int)(musicRect.right + 60 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((musicRect.right + 60 * scaleFactor) + shareButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + shareButton.getMinimumHeight()));
//
        vibroRect.set(
                (int)(soundRect.right + 60 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((soundRect.right + 60 * scaleFactor) + backButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + backButton.getMinimumHeight()));

        pausePoint.set(
                (int) (gameField.left + 300 * scaleFactor),
                (int) (gameField.top + 270 * scaleFactor));
//
        repeatRect.set(
                (int)(gameField.left + 95 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((gameField.left + 95 * scaleFactor) + repeatButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + repeatButton.getMinimumHeight()));

        likeRect.set(
                (int)(repeatRect.right + 60 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((repeatRect.right + 60 * scaleFactor) + likeButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + likeButton.getMinimumHeight()));

        shareRect.set(
                (int)(likeRect.right + 60 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((likeRect.right + 60 * scaleFactor) + shareButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + shareButton.getMinimumHeight()));

        settingsRect.set(
                (int)(shareRect.right + 60 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((shareRect.right + 60 * scaleFactor) + settingsButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + settingsButton.getMinimumHeight()));

        homeRect.set(
                (int)(settingsRect.right + 60 * scaleFactor),
                (int)(400 * scaleFactor),
                (int)((settingsRect.right + 60 * scaleFactor) + homeButton.getMinimumWidth()),
                (int)((400 * scaleFactor) + homeButton.getMinimumHeight()));

        continueRect.set(
                (int)(gameField.left + 200 * scaleFactor),
                (int)(screenHeight - 140 * scaleFactor),
                (int)((gameField.left + 200 * scaleFactor) + 550 * scaleFactor),
                (int)((screenHeight - 140 * scaleFactor) + 100 * scaleFactor));

        resumePoint.set(
                (int)(repeatRect.left - 10 * scaleFactor),
                (int)(repeatRect.bottom + 40 * scaleFactor));

        likePoint.set(
                (int)(likeRect.left + 20 * scaleFactor),
                (int)(repeatRect.bottom + 40 * scaleFactor));

        sharePoint.set(
                (int)(shareRect.left + 10 * scaleFactor),
                (int)(repeatRect.bottom + 40 * scaleFactor));

        settingsPoint.set(
                (int)(settingsRect.left - 10 * scaleFactor),
                (int)(settingsRect.bottom + 40 * scaleFactor));

        homePoint.set(
                (int)(homeRect.left + 5 * scaleFactor),
                (int)(homeRect.bottom + 40 * scaleFactor));

        continuePoint.set(
                (int)(gameField.left + 200 * scaleFactor),
                (int)(screenHeight - 70 * scaleFactor));

        musicPoint.set(
                (int)(musicRect.left + 0 * scaleFactor),
                (int)(musicRect.bottom + 40 * scaleFactor));

        soundFxPoint.set(
                (int)(soundRect.left + 5 * scaleFactor),
                (int)(soundRect.bottom + 40 * scaleFactor));

        vibrationPoint.set(
                (int)(vibroRect.left - 20 * scaleFactor),
                (int)(vibroRect.bottom + 40 * scaleFactor));

        backPoint.set(
                (int)(backRect.left + 17 * scaleFactor),
                (int)(backRect.bottom + 40 * scaleFactor));

        buttons.put("resume",repeatRect);
        buttons.put("like",likeRect);
        buttons.put("share",shareRect);
        buttons.put("home",homeRect);
        buttons.put("settings",settingsRect);
        buttons.put("continue",continueRect);
        buttons.put("back",backRect);
        buttons.put("music",musicRect);
        buttons.put("sound",soundRect);
        buttons.put("vibro",vibroRect);


    }

    @Override
    public void showHoleIn() {
        holeInInitializer();
        showHoleIn = true;
    }

    public void showClock (boolean showClock) {
        this.showClock = showClock;
    }

    public void showLove () {
        this.showLove = true;
    }

    public void animText (final String str, final Point snakePoint) {
        if (animTextPoint1.y == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    animTextPoint1 = snakePoint;
                    animText1 = str;
                    int adAlpha = 0;
                    int adLimit = 0;
                    if (str.equals("1up")){
                        adAlpha = 2;
                        adLimit = 70;
                    }
                    int limit = (int) (animTextPoint1.y - (70 + adLimit * scaleFactor));
                    animTextPaint1.setAlpha(250);
                    while (animTextPoint1.y > limit) {
                        try {
                            animTextPoint1.y--;
                            animTextPaint1.setAlpha(animTextPaint1.getAlpha() - 3 + adAlpha);
                            Thread.sleep(15);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    animTextPoint1.y = 0;
                }
            }).start();
            return;
        }

        if (animTextPoint2. y == 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        animTextPoint2 = snakePoint;
                        animText2 = str;
                        int adAlpha = 0;
                        int adLimit = 0;
                        if (str.equals("1up")){
                            adAlpha = 2;
                            adLimit = 70;
                        }
                        int limit = (int) (animTextPoint2.y - (70 + adLimit * scaleFactor));
                        animTextPaint2.setAlpha(250);
                        while (animTextPoint2.y > limit) {
                            try {
                                animTextPoint2.y--;
                                animTextPaint2.setAlpha(animTextPaint2.getAlpha() - 3 + adAlpha);
                                Thread.sleep(15);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        animTextPoint2.y = 0;
                    }
                }).start();
                return;
            }

        new Thread(new Runnable() {
            @Override
            public void run() {
                animTextPoint3 = snakePoint;
                animText3 = str;
                int adAlpha = 0;
                int adLimit = 0;
                if (str.equals("1up")){
                    adAlpha = 2;
                    adLimit = 70;
                }
                int limit = (int) (animTextPoint3.y - (70 + adLimit * scaleFactor));
                animTextPaint3.setAlpha(250);
                while (animTextPoint3.y > limit) {
                    try {
                        animTextPoint3.y--;
                        animTextPaint3.setAlpha(animTextPaint3.getAlpha() - 3 + adAlpha);
                        Thread.sleep(15);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                animTextPoint3.y = 0;
            }
        }).start();
    }

    public Map<String, Rect> getPauseButtons() { return buttons; }
    public void setShowPause (boolean showPause) { this.showPause = showPause;}
    public void setElements(ArrayList<GameElement> elements) {
        this.elements = elements;
    }
    public void setController (ControllerPlay controller) {
        this.controller = controller;
    }
    public void setListenerOff () {
        setOnTouchListener(null);
    }
    public void setListenerOn () {
        setOnTouchListener(controller);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        background.recycle();
        snakeHeadUp.recycle();
        snakeHeadDown.recycle();
        snakeHeadLeft.recycle();
        snakeHeadRight.recycle();
        snakeHeadUpBreak.recycle();
        snakeHeadDownBreak.recycle();
        snakeHeadLeftBreak.recycle();
        snakeHeadRightBreak.recycle();
        snakeBodyHorizontal.recycle();
        snakeBodyVertical.recycle();
        snakeBodyAngleLeftUp.recycle();
        snakeBodyAngleLeftDown.recycle();
        snakeBodyAngleRightUp.recycle();
        snakeBodyAngleRightDown.recycle();
        snakeTailUp.recycle();
        snakeTailDown.recycle();
        snakeTailLeft.recycle();
        snakeTailRight.recycle();
        speed20.recycle();
        speed40.recycle();
        speed60.recycle();
        speed80.recycle();
        speed100.recycle();
        life.recycle();
        apple.recycle();
        fruitCup.recycle();
        strawberry.recycle();
        banana.recycle();
        cherry.recycle();
        watermelon.recycle();
        grape.recycle();
        kiwi.recycle();
        snail.recycle();
        coin.recycle();
        scissors.recycle();
        record.recycle();
        repeatButton.recycle();
        shareButton.recycle();
        likeButton.recycle();
        exitButton.recycle();
        settingsButton.recycle();
        homeButton.recycle();
        backButton.recycle();
        click.recycle();
        play.recycle();

        switch (level) {
            case 1:
                stumpBitmap.recycle();
                soil.recycle();
                mole.recycle();
                break;
            case 2:
                lawnmover.recycle();
                pig.recycle();
                break;
            case 3:
                rain.recycle();
                lighting.recycle();
                puddle.recycle();
                puddle2.recycle();
                lightningball.recycle();
                spark.recycle();
                tornado.recycle();
                break;
            case 4:
                geckoRight.recycle();
                geckoLeft.recycle();
                geckoUp.recycle();
                geckoDown.recycle();
                geckoLeftReady.recycle();
                geckoRightReady.recycle();
                geckoUpReady.recycle();
                geckoDownReady.recycle();
                elephantRight.recycle();
                elephantLeft.recycle();
                fruitSmash.recycle();
                break;
            case 5:
                alienShip1.recycle();
                explosion.recycle();
                explosion1.recycle();
                explosion2.recycle();
                explosion3.recycle();
                explosion4.recycle();
                explosion5.recycle();
                target.recycle();
                alienMonsterRight.recycle();
                alienMonsterLeft.recycle();
                monsterFireLeft.recycle();
                monsterFireRight.recycle();
                teleport.recycle();
                break;
            case 6:
                laser.recycle();
                alienShip1.recycle();
                alienFlagShip.recycle();
                rocket.recycle();
                explosion1.recycle();
                explosionOnShip.recycle();
                explosionOnShip2.recycle();
                fireonship.recycle();
                smoke.recycle();
                target.recycle();
                teleport.recycle();
                zombieRight.recycle();
                zombieLeft.recycle();
                whiteFlag.recycle();
                break;
            case 7:
                snakeHeadUpGirl.recycle();
                snakeHeadLeftGirl.recycle();
                snakeHeadRightGirl.recycle();
                snakeHeadDownGirl.recycle();
                headLeftGirlSleep.recycle();
                pillow.recycle();
                blanket.recycle();
                zzz.recycle();
                backet.recycle();
                clock.recycle();
                heart.recycle();
        }
    }

    public void setClickOn (boolean showClick) {
        if (showClick) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        clickOn = true;
                        Thread.sleep(5000);
                        clickOn = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            clickOn = false;
        }
    }

    public void setSmashFruit(Point point) {
        smashFruit = true;
        smashFruitPoint = point;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    smashFruit = false;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void setText(boolean showText) {
        this.showText = showText;
    }

    public void setText(String str, Point textPoint, float size, boolean showText) {
        this.text = str;
        this.textPoint = textPoint;
        this.showText = showText;
        textPaint.setTextSize(size);
    }

    public void startExplosionOnShip () {
        explosionOnShip.reset();
        explosionOnShip2.reset();
    }
    public void resetExplosionOnShip () {
       explosionOnShip.stop();
       explosionOnShip2.stop();
    }
    public void startExplosion1 () { explosion1.reset();}
    public void resetExplosion1 () {
        explosion1.stop();
    }
    public void startExplosion2 () {
        explosion2.reset();
    }
    public void resetExplosion2 () { explosion2.stop(); }
    public void startExplosion3 () {
        explosion3.reset();
    }
    public void resetExplosion3 () {
        explosion3.stop();
    }
    public void startExplosion4 () {
        explosion4.reset();
    }
    public void resetExplosion4 () {
        explosion4.stop();
    }
    public void startExplosion5 () {
        explosion5.reset();
    }
    public void resetExplosion5 () {
        explosion5.stop();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (showElemets) {
            super.onDraw(canvas);

            setBackgroundColor(Color.WHITE);
            // draw background
            canvas.drawBitmap(background, screenWidth - background.getWidth(),
                    screenHeight - background.getHeight(), null);

            //draw elements
            for (GameElement element : elements) {

                // draw stump
                if (element instanceof Stump) {
                    Stump stump = ((Stump) element);
                    canvas.drawBitmap(stumpBitmap, stump.getCoordinates().getX(),
                            stump.getCoordinates().getY(), null);
                }

                if (element instanceof Mole) {
                    Mole object = ((Mole) element);
                    synchronized (Mole.class) {

                        for (Integer[] integers : object.getSoilPoints()) {
                            canvas.drawBitmap(soil, integers[0], integers[1], null);
                        }

                    }

                    if (object.isMoleShow()) {
                        mole.setBounds(object.getRectOfElement());
                        mole.draw(canvas);
                    }
                }


                //draw puddle

                if (element instanceof Puddle) {
                    Puddle coord = ((Puddle) element);
                    if (coord.getX() > 0) {
                        if (coord.getNumber() % 2 == 0) {
                            puddle.setBounds(coord.getRectOfElement());
                            puddle.draw(canvas);
                        } else {
                            puddle2.setBounds(coord.getRectOfElement());
                            puddle2.draw(canvas);
                        }
                    }
                    if (coord.isSpark()) {
                        spark.setBounds(coord.getRectOfElement());
                        spark.draw(canvas);
                    }
                }

                //draw Gecko

                if (element instanceof Gecko) {
                    Gecko coord = ((Gecko) element);
                    int offSetX = 0;
                    int offSetY = 0;
                    switch (coord.getDirection()) {
                        case 0:
                            if (!coord.isRun()) {
                                geckoAnimation = geckoLeftReady;
                            } else {
                                geckoAnimation = geckoRight;
                            }
                            break;
                        case 1:
                            if (!coord.isRun()) {
                                geckoAnimation = geckoUpReady;
                            } else {
                                geckoAnimation = geckoDown;
                            }
                            break;
                        case 2:
                            if (!coord.isRun()) {
                                geckoAnimation = geckoRightReady;
                                offSetX = geckoRight.getMinimumWidth() - geckoRightReady.getMinimumWidth();
                            } else {
                                geckoAnimation = geckoLeft;
                            }
                            break;
                        case 3:
                            if (!coord.isRun()) {
                                geckoAnimation = geckoDownReady;
                                offSetY = geckoUp.getMinimumHeight() - geckoDownReady.getMinimumHeight();
                            } else {
                                geckoAnimation = geckoUp;
                            }
                            break;
                    }
                    geckoAnimation.setBounds(coord.x + offSetX,
                            coord.y + offSetY,
                            coord.x + geckoAnimation.getMinimumWidth() + offSetX,
                            coord.y + geckoAnimation.getMinimumHeight() + offSetY);
                    geckoAnimation.draw(canvas);
                }

                // draw rocket
                if (element instanceof Rocket) {
                    Rocket coord = ((Rocket) element);
                    rocket.setBounds(coord.getRectOfElement());
                    rocket.draw(canvas);
                }

                // draw snail
                if (element instanceof Snail) {
                    Snail coord = ((Snail) element);
                    snail.setBounds(coord.getRectOfElement());
                    snail.draw(canvas);
                }

                // draw coin
                if (element instanceof Coin) {
                    Coin coord = ((Coin) element);
                    coin.setBounds(coord.getRectOfElement());
                    coin.draw(canvas);
                }

                // draw scissors
                if (element instanceof Scissors) {
                    Scissors coord = ((Scissors) element);
                    scissors.setBounds(coord.getRectOfElement());
                    scissors.draw(canvas);
                }

                // draw lawnMover

                if (element instanceof LawnMover) {
                    LawnMover coord = ((LawnMover) element);
                    if (coord.isPig()) {
                        pig.setBounds(coord.x, coord.y, coord.x + pig.getMinimumWidth(),
                                coord.y + pig.getMinimumHeight());
                        pig.draw(canvas);
                    } else {
                        lawnmover.setBounds(coord.x, coord.y, coord.x + lawnmover.getMinimumWidth(),
                                coord.y + lawnmover.getMinimumHeight());
                        lawnmover.draw(canvas);
                    }
                }

                // draw fruits
                if (element instanceof Fruits) {
                    Fruits coord = ((Fruits) element);
                    fruitCup.setBounds(coord.getRectOfElement());
                    fruitCup.draw(canvas);
                }


                // draw fruit
                if (element instanceof Fruit) {
                    Fruit fruit = ((Fruit) element);
                    if (fruit.getCoordinates().getX() > 0) {
                        GifDrawable fruitGif = apple;
                        switch (fruit.getFruittype()) {
                            case 1:
                                fruitGif = strawberry;
                                break;
                            case 2:
                                fruitGif = banana;
                                break;
                            case 3:
                                fruitGif = cherry;
                                break;
                            case 4:
                                fruitGif = watermelon;
                                break;
                            case 5:
                                fruitGif = grape;
                                break;
                            case 6:
                                fruitGif = kiwi;
                                break;
                        }
                        fruitGif.setBounds(fruit.getRectOfElement());
                        fruitGif.draw(canvas);
                    }
                }

                if (smashFruit) {
                    canvas.drawBitmap(fruitSmash, smashFruitPoint.x, smashFruitPoint.y, null);
                }


                // draw snake


                if (element instanceof Snake) {
                    Snake snake = ((Snake) element);

                    if (snake.getGameLevel() == 3) {
                        rain.setBounds(gameField.left, gameField.top, gameField.right, gameField.bottom);
                        rain.draw(canvas);
                    }

                    if (snake.isGirl()) {

                        if (snake.isOnTheBlanket()) {
                            canvas.drawBitmap(blanket, gameField.right - 240 * scaleFactor,
                                    gameField.top + 285 * scaleFactor, null);
                        }

                        if (showClock) {
                            clock.setBounds((int) (gameField.right - 400 * scaleFactor),
                                    (int) (gameField.top + 280 * scaleFactor),
                                    (int) (gameField.right - 400 * scaleFactor + clock.getMinimumWidth()),
                                    (int) (gameField.top + 280 * scaleFactor + clock.getMinimumHeight())
                            );
                            clock.draw(canvas);
                        }

                        canvas.drawBitmap(pillow, gameField.right - 340 * scaleFactor,
                                gameField.top + 320 * scaleFactor, null);
                        if (snake.isGirlSleep()) {
                            zzz.setBounds((int) (gameField.right - 340 * scaleFactor),
                                    (int) (gameField.top + 240 * scaleFactor),
                                    (int) (gameField.right - 340 * scaleFactor + zzz.getMinimumWidth()),
                                    (int) (gameField.top + 240 * scaleFactor + zzz.getMinimumHeight()));
                            zzz.draw(canvas);
                        }


                        if (showLove) {
                            heart.setBounds((int) (gameField.left + 280 * scaleFactor),
                                    (int) (gameField.top + 240 * scaleFactor),
                                    (int) (gameField.left + 280 * scaleFactor + heart.getMinimumWidth()),
                                    (int) (gameField.top + 240 * scaleFactor + heart.getMinimumHeight())
                            );
                            heart.draw(canvas);
                        }


                        scorePaint.setTextSize(18 * scaleFactor);
                        scorePaint.setStyle(Paint.Style.FILL);
                        scorePaint.setColor(Color.WHITE);
                        canvas.drawBitmap(backet, gameField.right - 340 * scaleFactor,
                                gameField.top + 160 * scaleFactor, redBacket);
                        canvas.drawText("Red", gameField.right - 320 * scaleFactor,
                                gameField.top + 230 * scaleFactor, scorePaint);
                        canvas.drawBitmap(backet, gameField.right - 160 * scaleFactor,
                                gameField.top + 160 * scaleFactor, yellowBacket);
                        canvas.drawText("Yellow", gameField.right - 150 * scaleFactor,
                                gameField.top + 230 * scaleFactor, scorePaint);
                        canvas.drawBitmap(backet, gameField.right - 340 * scaleFactor,
                                gameField.top + 460 * scaleFactor, greenBacket);
                        canvas.drawText("Green", gameField.right - 325 * scaleFactor,
                                gameField.top + 530 * scaleFactor, scorePaint);
                        canvas.drawBitmap(backet, gameField.right - 160 * scaleFactor,
                                gameField.top + 460 * scaleFactor, magentaBacket);
                        canvas.drawText("Purple", gameField.right - 147 * scaleFactor,
                                gameField.top + 530 * scaleFactor, scorePaint);
                    }

                    if (snake.getGameLevel() != 7 && !snake.isGirl()) {

                    // draw HUD (Level)
                    scorePaint.setTextSize(30 * scaleFactor);

                    if (snake.getGameLevel() == 0) {
                        canvas.drawText("Bonus", (int) (screenWidth - 170 * scaleFactor), (int) (120 * scaleFactor), scorePaint);
                    }

                    if (snake.getGameLevel() != 6 && snake.getGameLevel() != 0) {
                        canvas.drawText("Level " + String.valueOf(snake.getGameLevel()), (int) (screenWidth - 175 * scaleFactor), (int) (105 * scaleFactor), scorePaint);

                        // Finish line

                        int top = 114;
                        int bottom = 117;
                        for (int i = 0; i < 18; i++) {
                            if (finishLinePaint.getColor() == Color.WHITE) {
                                finishLinePaint.setColor(Color.BLACK);
                            } else {
                                finishLinePaint.setColor(Color.WHITE);
                            }
                            finishRect.set((int) (screenWidth - 65 * scaleFactor),
                                    (int) ((top) * scaleFactor),
                                    (int) (screenWidth - 62 * scaleFactor),
                                    (int) ((bottom) * scaleFactor));
                            canvas.drawRect(finishRect, finishLinePaint);

                            if (finishLinePaint.getColor() == Color.WHITE) {
                                finishLinePaint.setColor(Color.BLACK);
                            } else {
                                finishLinePaint.setColor(Color.WHITE);
                            }

                            finishRect.set((int) (screenWidth - 62 * scaleFactor),
                                    (int) ((top) * scaleFactor),
                                    (int) (screenWidth - 59 * scaleFactor),
                                    (int) ((bottom) * scaleFactor));
                            canvas.drawRect(finishRect, finishLinePaint);

                            if (finishLinePaint.getColor() == Color.WHITE) {
                                finishLinePaint.setColor(Color.BLACK);
                            } else {
                                finishLinePaint.setColor(Color.WHITE);
                            }
                            finishRect.set((int) (screenWidth - 59 * scaleFactor),
                                    (int) ((top) * scaleFactor),
                                    (int) (screenWidth - 56 * scaleFactor),
                                    (int) ((bottom) * scaleFactor));
                            canvas.drawRect(finishRect, finishLinePaint);
                            top += 3;
                            bottom += 3;
                        }


                        // // Draw HUD (Progress)
                        canvas.drawBitmap(snakeTailRight, (int) (screenWidth - 225 * scaleFactor),
                                (int) (130 * scaleFactor), null);
                        int xBody = (int) (screenWidth - 225 * scaleFactor);
                        int xHead = (int) (screenWidth - 210 * scaleFactor); // 1070
                        for (int i = 0; i < 5; i++) {
                            if (xHead + snake.getEatFruitForNewLevel() * 2 * scaleFactor - xBody > 0)
                                canvas.drawBitmap(snakeBodyHorizontal, ((xBody += 20 * scaleFactor)),
                                        (int) (130 * scaleFactor), null);
                        }
                        if ((xHead + snake.getEatFruitForNewLevel() * 2) <= screenWidth - 110 * scaleFactor) {
                            canvas.drawBitmap(snakeHeadRight,
                                    (int) ((xHead + snake.getEatFruitForNewLevel() * 2 * scaleFactor)),
                                    (int) (110 * scaleFactor), null);
                        } else {
                            canvas.drawBitmap(snakeHeadRight,
                                    (int) ((screenWidth - 110 * scaleFactor)),
                                    (int) (110 * scaleFactor), null);
                        }

                    }

                    //draw HUD (Score)

                    int scoreSize = String.valueOf(snake.getScore()).length();
                    int xOffSet = 0;
                    scorePaint.setTextSize(30 * scaleFactor);

                    switch (scoreSize) {
                        case 1: xOffSet = 180; break;
                        case 2: xOffSet = 185; break;
                        case 3: xOffSet = 193; break;
                        case 4: xOffSet = 200; break;
                        case 5: xOffSet = 200;  scorePaint.setTextSize(25 * scaleFactor); break;
                    }

                    canvas.drawText("Score " + snake.getScore(),
                            (int) (screenWidth - xOffSet * scaleFactor),
                            (int) (240 * scaleFactor),
                            scorePaint);


                    // draw HUD (Life)
                    if (snake.getLifeCount() > 5 && snake.getLifeCount() < 10) {
                        canvas.drawText(snake.getLifeCount() + " x ",
                                (int) (screenWidth - 170 * scaleFactor),
                                (int) (282 * scaleFactor),
                                scorePaint);
                        canvas.drawBitmap(life,
                                (int) (screenWidth - 125 * scaleFactor),
                                (int) (250 * scaleFactor),
                                null);
                    }

                    else if (snake.getLifeCount() >= 10) {
                        canvas.drawText(snake.getLifeCount() + " x ",
                                (int) (screenWidth - 180 * scaleFactor),
                                (int) (282 * scaleFactor),
                                scorePaint);
                        canvas.drawBitmap(life,
                                (int) (screenWidth - 120 * scaleFactor),
                                (int) (250 * scaleFactor),
                                null);
                    }

                    else {
                        for (int i = 0; i < snake.getLifeCount(); i++) {
                            canvas.drawBitmap(life,
                                    (int) (screenWidth - 85 * scaleFactor) -
                                            ((life.getWidth() + (int) 2.5 * scaleFactor) * i),
                                    (int) (250 * scaleFactor), null);
                        }
                    }

                        // draw HUD (Speed)
                        Bitmap speed = speed20;
                        switch (snake.getSpeed()) {
                            case 0:
                                speed = speed20;
                                break;
                            case 1:
                                speed = speed40;
                                break;
                            case 2:
                                speed = speed60;
                                break;
                            case 3:
                                speed = speed80;
                                break;
                            case 4:
                                speed = speed100;
                                break;
                        }
                        canvas.drawBitmap(speed, (int) (screenWidth - 195 * scaleFactor), (int) (350 * scaleFactor), null);
                    }

                    // draw body

                    if (snake.getBodySize() > 0) {
                        synchronized (Snake.class) {
                            try {

                                Bitmap bodyImage = snakeBodyHorizontal;

                                for (int i = 1; i < snake.getBodySize() - 1; i++) {

                                    int currentX = snake.getBody().get(i)[0];
                                    int currentY = snake.getBody().get(i)[1];

                                    if (currentX >= gameField.left && currentY >= gameField.top
                                            && currentX < gameField.right && currentY < gameField.bottom) {

                                        int nextX = snake.getBody().get(i + 1)[0];
                                        int nextY = snake.getBody().get(i + 1)[1];

                                        int previosX = snake.getBody().get(i - 1)[0];
                                        int previosY = snake.getBody().get(i - 1)[1];

                                        prevBody.set(previosX, previosY);
                                        nextBody.set(nextX, nextY);

                                        up.set(currentX, currentY - (int) (20 * scaleFactor));
                                        right.set(currentX + (int) (20 * scaleFactor), currentY);
                                        down.set(currentX, currentY + (int) (20 * scaleFactor));
                                        left.set(currentX - (int) (20 * scaleFactor), currentY);

                                        if ((nextBody.equals(down) && prevBody.equals(right))
                                                || (nextBody.equals(right) && prevBody.equals(down))) {
                                            bodyImage = snakeBodyAngleLeftUp;
                                        } else if ((nextBody.equals(down) && prevBody.equals(left))
                                                || (nextBody.equals(left) && prevBody.equals(down))) {
                                            bodyImage = snakeBodyAngleRightUp;
                                        } else if ((nextBody.equals(up) && prevBody.equals(right))
                                                || (nextBody.equals(right) && prevBody.equals(up))) {
                                            bodyImage = snakeBodyAngleLeftDown;
                                        } else if ((nextBody.equals(left) && prevBody.equals(up))
                                                || (nextBody.equals(up) && prevBody.equals(left))) {
                                            bodyImage = snakeBodyAngleRightDown;
                                        } else if (currentY == previosY) {
                                            bodyImage = snakeBodyHorizontal;
                                        } else if (currentX == previosX) {
                                            bodyImage = snakeBodyVertical;
                                        }

                                        canvas.drawBitmap(bodyImage, snake.getBody().get(i)[0], snake.getBody().get(i)[1], null);
                                    }

                                }
                            } catch (IndexOutOfBoundsException ex) {
                                System.out.println("Can't draw body!!!");
                            } catch (NullPointerException ex) {
                                System.out.println("Can't draw body - null");
                            }


                            // ***************Player1 Tail paint*****************
                            Bitmap tailImage = snakeTailLeft;

                            try {
                                int tailX = snake.getBody().get(snake.getBodySize() - 1)[0];
                                int tailY = snake.getBody().get(snake.getBodySize() - 1)[1];

                                if (tailX >= gameField.left && tailY >= gameField.top
                                        && tailX < gameField.right && tailY < gameField.bottom) {

                                    int bodyX = snake.getBody().get(snake.getBodySize() - 2)[0];
                                    int bodyY = snake.getBody().get(snake.getBodySize() - 2)[1];

                                    if (tailX == bodyX && bodyY > tailY) {
                                        tailImage = snakeTailDown;
                                    } else if (tailX == bodyX && tailY > bodyY) {
                                        tailImage = snakeTailUp;
                                    } else if (tailY == bodyY && tailX > bodyX) {
                                        tailImage = snakeTailLeft;
                                    } else if (tailY == bodyY && bodyX > tailX) {
                                        tailImage = snakeTailRight;
                                    }

                                    canvas.drawBitmap(tailImage, tailX, tailY, null);
                                }

                            } catch (IndexOutOfBoundsException ex) {
                                System.out.println("Can't draw tail");
                            }


                            // ***********Player1 Head pain***************

                            Bitmap snakeHead = snakeHeadRight;
                            if (snake.getDirection().equals("UP")) {
                                if (snake.isDead()) snakeHead = snakeHeadUpBreak;
                                else if (snake.isGirl()) snakeHead = snakeHeadUpGirl;
                                else snakeHead = snakeHeadUp;
                            }
                            if (snake.getDirection().equals("DOWN")) {
                                if (snake.isDead()) snakeHead = snakeHeadDownBreak;
                                else if (snake.isGirl()) snakeHead = snakeHeadDownGirl;
                                else snakeHead = snakeHeadDown;
                            }
                            if (snake.getDirection().equals("LEFT")) {
                                if (snake.isDead()) snakeHead = snakeHeadLeftBreak;
                                else if (snake.isGirlSleep()) snakeHead = headLeftGirlSleep;
                                else if (snake.isGirl()) snakeHead = snakeHeadLeftGirl;
                                else snakeHead = snakeHeadLeft;
                            }
                            if (snake.getDirection().equals("RIGHT")) {
                                if (snake.isDead()) snakeHead = snakeHeadRightBreak;
                                else if (snake.isGirl()) snakeHead = snakeHeadRightGirl;
                                else snakeHead = snakeHeadRight;
                            }
                            canvas.drawBitmap(snakeHead,
                                    snake.getBody().get(0)[0] - (int) (20 * scaleFactor),
                                    snake.getBody().get(0)[1] - (int) (20 * scaleFactor),
                                    null);
                        }
                    }
                    if (animTextPoint1.y != 0) {
                        canvas.drawText(animText1, animTextPoint1.x, animTextPoint1.y, animTextPaint1);
                    }

                    if (animTextPoint2.y != 0) {
                        canvas.drawText(animText2, animTextPoint2.x, animTextPoint2.y, animTextPaint2);
                    }

                    if (animTextPoint3.y != 0) {
                        canvas.drawText(animText3, animTextPoint3.x, animTextPoint3.y, animTextPaint3);
                    }

                    if (!snake.isOnTheBlanket() && snake.isGirl()) {
                        canvas.drawBitmap(blanket,
                                gameField.right - 240 * scaleFactor,
                                gameField.top + 285 * scaleFactor,
                                null);
                    }
                }

                if (element instanceof AlienMonster) {
                    AlienMonster coord = ((AlienMonster) element);

                    teleport.setAlpha(coord.getTeleportAlpha());
                    alienMonsterRight.setAlpha(coord.getMonstrAlpha());
                    teleport.setBounds(coord.teleportPoint().x, coord.teleportPoint().y,
                            coord.teleportPoint().x + teleport.getMinimumWidth(),
                            coord.teleportPoint().y + teleport.getMinimumHeight());
                    teleport.draw(canvas);

                    if (coord.getDirection() == 0) {
                        alienMonsterRight.setBounds(coord.monsterX, coord.monsterY,
                                coord.monsterX + alienMonsterRight.getMinimumWidth(),
                                coord.monsterY + alienMonsterRight.getMinimumHeight());
                        alienMonsterRight.draw(canvas);
                        if (coord.isFire()) {
                            monsterFireRight.setBounds(coord.getFireRect());
                            monsterFireRight.draw(canvas);
                        }
                    } else {
                        alienMonsterLeft.setBounds(coord.monsterX, coord.monsterY,
                                coord.monsterX + alienMonsterLeft.getMinimumWidth(),
                                coord.monsterY + alienMonsterLeft.getMinimumHeight());
                        alienMonsterLeft.draw(canvas);
                        if (coord.isFire()) {
                            monsterFireLeft.setBounds(coord.getFireRect());
                            monsterFireLeft.draw(canvas);
                        }
                    }

                }

                if (element instanceof AlienFlagShip) {
                    AlienFlagShip object = ((AlienFlagShip) element);

                    //draw Boss Energy

                    canvas.drawText("Boss", (int) (screenWidth - 160 * scaleFactor),
                            (int) (115 * scaleFactor), scorePaint);

                    bossEnergyBorderRect.set((int) (screenWidth - 210 * scaleFactor),
                            (int) (130 * scaleFactor),
                            (int) (screenWidth - 60 * scaleFactor),
                            (int) (150 * scaleFactor));
                    canvas.drawRect(bossEnergyBorderRect, bossEnergyBorder);
                    bossEnergyRect.set((int) (screenWidth - 210 * scaleFactor),
                            (int) (130 * scaleFactor),
                            (int) (screenWidth - (60 + object.getBossEnergy() * 15) * scaleFactor),
                            (int) (150 * scaleFactor));
                    canvas.drawRect(bossEnergyRect, bossEnergy);

                    if (object.isShowZombie()) {
                        for (AlienFlagShip.Zombie zombie : object.getZombies()) {
                            teleport.setAlpha(zombie.getTeleportAlpha());
                            teleport.setBounds(zombie.teleportPoint().x, zombie.teleportPoint().y,
                                    zombie.teleportPoint().x + teleport.getMinimumWidth(),
                                    zombie.teleportPoint().y + teleport.getMinimumHeight());
                            teleport.draw(canvas);
                            if (zombie.direction == 0) {
                                zombieRight.setAlpha(zombie.getMonstrAlpha());
                                zombieRight.setBounds(zombie.getZombieRect());
                                zombieRight.draw(canvas);
                            } else {
                                zombieLeft.setAlpha(zombie.getMonstrAlpha());
                                zombieLeft.setBounds(zombie.getZombieRect());
                                zombieLeft.draw(canvas);
                            }
                        }
                    }

                    if (object.isHoming()) {
                        red.setAlpha(100);
                        red.setStrokeWidth(2 * scaleFactor);
                        red.setStyle(Paint.Style.FILL_AND_STROKE);
                        for (float[] homingLine : object.getHomingLines()) {
                            canvas.drawLines(homingLine, red);
                        }
                        target.setAlpha(70);

                        for (float[] floats : object.getTargets()) {
                            target.setBounds((int) floats[0], (int) floats[1], (int) floats[2], (int) floats[3]);
                            target.draw(canvas);
                        }

                        red.setAlpha(250);
                        canvas.drawOval(object.getBeam(), red);
                    }

                    if (object.openFire) {
                        for (AlienFlagShip.Laser laserCoord : object.getLasers()) {
                            canvas.drawBitmap(laser, laserCoord.laserX, laserCoord.laserY, null);
                        }
                    }

                    if (object.isGiveUp()) {
                        whiteFlag.setBounds(
                                alienFlagShip.getBounds().left + (int) (20 * scaleFactor),
                                alienFlagShip.getBounds().top - (int) (60 * scaleFactor),
                                alienFlagShip.getBounds().left +
                                        (int) (20 * scaleFactor) + whiteFlag.getMinimumWidth(),
                                alienFlagShip.getBounds().top -
                                        (int) (60 * scaleFactor) + whiteFlag.getMinimumHeight());
                        whiteFlag.draw(canvas);
                    }

                    alienFlagShip.setBounds(object.getRectOfElement());
                    alienFlagShip.draw(canvas);

                    smoke.setBounds(object.getSmokeX(),
                            object.getSmokeY(),
                            object.getSmokeX() + smoke.getMinimumWidth(),
                            object.getSmokeY() + smoke.getMinimumHeight());
                    smoke.draw(canvas);

                    explosionOnShip.setBounds(alienFlagShip.getBounds().left +
                            (int) (40 * scaleFactor), alienFlagShip.getBounds().top -
                            (int) (20 * scaleFactor), alienFlagShip.getBounds().left +
                            (int) (40 * scaleFactor) + explosionOnShip.getMinimumWidth(),
                            alienFlagShip.getBounds().top -
                                    (int) (20 * scaleFactor) + explosionOnShip.getMinimumHeight());
                    explosionOnShip.draw(canvas);

                    explosionOnShip2.setBounds(alienFlagShip.getBounds().left +
                            (int) (20 * scaleFactor), alienFlagShip.getBounds().top -
                            (int) (60 * scaleFactor), alienFlagShip.getBounds().left +
                            (int) (20 * scaleFactor) + explosionOnShip2.getMinimumWidth(),
                            alienFlagShip.getBounds().top - (int) (60 * scaleFactor) +
                                    explosionOnShip2.getMinimumHeight());
                    explosionOnShip2.draw(canvas);

                    if (object.getBossEnergy() >= 5) {
                        fireonship.setBounds(alienFlagShip.getBounds().left +
                                (int) (20 * scaleFactor), alienFlagShip.getBounds().top -
                                (int) (60 * scaleFactor), alienFlagShip.getBounds().left +
                                (int) (20 * scaleFactor) + explosionOnShip2.getMinimumWidth(),
                                alienFlagShip.getBounds().top -
                                        (int) (60 * scaleFactor) + explosionOnShip2.getMinimumHeight());
                        fireonship.draw(canvas);
                    }

                    canvas.drawOval(object.getShipX() +
                            (int) (25 * scaleFactor), object.getShipY() +
                            (int) (175 * scaleFactor), object.getShipX() +
                            alienShip1.getMinimumWidth() - (int) (25 * scaleFactor),
                            object.getShipY() + (int) (200 * scaleFactor), grey);

                    if (object.isShowFire()) {

                        for (float[] floats : object.getTargets()) {
                            explosion1.setBounds((int) (floats[0] - 12 * scaleFactor),
                                    (int) (floats[1] - 27 * scaleFactor),
                                    (int) (floats[2] + 12 * scaleFactor), (int) (floats[3]) );
                            explosion1.draw(canvas);

                        }
                    }
                }

                if (element instanceof AlienShip) {
                    AlienShip coord = ((AlienShip) element);
                    if (coord.isHoming()) {
                        red.setAlpha(100);
                        red.setStrokeWidth(2 * scaleFactor);
                        red.setStyle(Paint.Style.FILL_AND_STROKE);
                        canvas.drawLines(coord.getHomingLine(), red);
                        target.setAlpha(70);
                        target.setBounds(
                                (int) (coord.getCrashRectF().left),
                                (int) (coord.getCrashRectF().top),
                                (int) (coord.getCrashRectF().right),
                                (int) (coord.getCrashRectF().bottom));
                        target.draw(canvas);
                        red.setAlpha(250);
                        canvas.drawOval(coord.getBeam(), red);
                    }
                    alienShip1.setBounds(coord.getRectOfElement());
                    alienShip1.draw(canvas);

                    //Ship shadow
                    if (coord.isAlive()) {
                        canvas.drawOval(coord.getShipX() +
                                (int) (25 * scaleFactor),
                                coord.getShipY() + (int) (175 * scaleFactor),
                                coord.getShipX() + alienShip1.getMinimumWidth() -
                                        (int) (25 * scaleFactor), coord.getShipY() +
                                        (int) (200 * scaleFactor), grey);
                    }
                    switch (coord.getNumber()) {
                        case 1:
                            explosion = explosion1;
                            break;
                        case 2:
                            explosion = explosion2;
                            break;
                        case 3:
                            explosion = explosion3;
                            break;
                        case 4:
                            explosion = explosion4;
                            break;
                        case 5:
                            explosion = explosion5;
                            break;
                    }

                    explosion.setBounds((int) (coord.getCrashRectF().left - (int) (12 * scaleFactor)),
                            (int) (coord.getCrashRectF().top - (int) (27 * scaleFactor)),
                            (int) (coord.getCrashRectF().right + (int) (12 * scaleFactor)),
                            (int) (coord.getCrashRectF().bottom));
                    explosion.draw(canvas);

                }

                if (element instanceof LightningBall) {
                    LightningBall coord = ((LightningBall) element);
                    lightningball.setBounds(coord.getRectOfElement());
                    lightningball.draw(canvas);
                    canvas.drawOval(coord.getTarget(), grey);
                }

                if (element instanceof Lighting) {
                    Lighting coord = ((Lighting) element);
                    lighting.setBounds(coord.getRectOfElement());
                    lighting.draw(canvas);
                }

                if (element instanceof Elephant) {
                    Elephant coord = ((Elephant) element);

                    if (coord.getDirection() == 0) {
                        elephantRight.setBounds(coord.getRectOfElement());
                        elephantRight.draw(canvas);
                    } else {
                        elephantLeft.setBounds(coord.getRectOfElement());
                        elephantLeft.draw(canvas);
                    }

                }

                if (element instanceof Tornado) {
                    Tornado coord = ((Tornado) element);
                    tornado.setAlpha(150);
                    tornado.setBounds(coord.getRectOfElement());
                    tornado.draw(canvas);
                }


            }

            if (showPause) {
                canvas.drawRect(gameField,black);
                textPaint.setTextSize(150 * scaleFactor);
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("Pause", pausePoint.x, pausePoint.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("Pause", pausePoint.x, pausePoint.y, textPaint);

                int size = 7; // was 5

                if (controller.isSettings()){
                    if (controller.isMusicOn()) {
                        canvas.drawOval(musicRect.left + size * scaleFactor,
                                musicRect.top + size * scaleFactor,
                                musicRect.right  - size * scaleFactor,
                                musicRect.bottom  - size * scaleFactor, buttonBlue);
                    }
                    canvas.drawOval(musicRect.left + size * scaleFactor,
                            musicRect.top + size * scaleFactor,
                            musicRect.right  - size * scaleFactor,
                            musicRect.bottom  - size * scaleFactor, buttonWhite);

                    if (controller.isSoundOn()) {
                        canvas.drawOval(soundRect.left + size * scaleFactor,
                                soundRect.top + size * scaleFactor,
                                soundRect.right - size * scaleFactor,
                                soundRect.bottom - size * scaleFactor, buttonBlue);
                    }
                    canvas.drawOval(soundRect.left + size * scaleFactor,
                            soundRect.top + size * scaleFactor,
                            soundRect.right - size * scaleFactor,
                            soundRect.bottom - size * scaleFactor, buttonWhite);

                    if (controller.isVibrationOn()) {
                        canvas.drawOval(vibroRect.left + size * scaleFactor,
                                vibroRect.top + size * scaleFactor,
                                vibroRect.right - size * scaleFactor,
                                vibroRect.bottom - size * scaleFactor, buttonBlue);
                    }
                    canvas.drawOval(vibroRect.left + size * scaleFactor,
                            vibroRect.top + size * scaleFactor,
                            vibroRect.right - size * scaleFactor,
                            vibroRect.bottom - size * scaleFactor, buttonWhite);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Music", musicPoint.x, musicPoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Music", musicPoint.x, musicPoint.y, signaturePaint);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Sound", soundFxPoint.x, soundFxPoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Sound", soundFxPoint.x, soundFxPoint.y, signaturePaint);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("FX", soundFxPoint.x + 25 * scaleFactor,
                            soundFxPoint.y + 37 * scaleFactor, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("FX", soundFxPoint.x + 25 * scaleFactor,
                            soundFxPoint.y + 37 * scaleFactor, signaturePaint);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Vibration", vibrationPoint.x, vibrationPoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Vibration", vibrationPoint.x, vibrationPoint.y, signaturePaint);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Back", backPoint.x, backPoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Back", backPoint.x, backPoint.y, signaturePaint);

                    backButton.setBounds(backRect);
                    backButton.draw(canvas);

                } else {
                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Resume", resumePoint.x, resumePoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Resume", resumePoint.x, resumePoint.y, signaturePaint);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Like", likePoint.x, likePoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Like", likePoint.x, likePoint.y, signaturePaint);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Share", sharePoint.x, sharePoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Share", sharePoint.x, sharePoint.y, signaturePaint);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Settings", settingsPoint.x, settingsPoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Settings", settingsPoint.x, settingsPoint.y, signaturePaint);

                    signaturePaint.setStyle(Paint.Style.FILL);
                    signaturePaint.setColor(Color.WHITE);
                    canvas.drawText("Home", homePoint.x, homePoint.y, signaturePaint);
                    signaturePaint.setStyle(Paint.Style.STROKE);
                    signaturePaint.setColor(Color.BLACK);
                    canvas.drawText("Home", homePoint.x, homePoint.y, signaturePaint);

                    play.setBounds(repeatRect);
                    likeButton.setBounds(likeRect);
                    shareButton.setBounds(shareRect);
                    exitButton.setBounds(exitRect);
                    settingsButton.setBounds(settingsRect);
                    homeButton.setBounds(homeRect);

                    play.draw(canvas);
                    settingsButton.draw(canvas);
                    homeButton.draw(canvas);
                    repeatButton.draw(canvas);
                    likeButton.draw(canvas);
                    shareButton.draw(canvas);
                    exitButton.draw(canvas);
                }

            }

            if (clickOn) {
                click.setBounds((int) (Level.screenWidth - 330 * Level.scaleFactor),
                        (int) (200 * Level.scaleFactor),
                        (int) (Level.screenWidth - 330 * Level.scaleFactor + click.getMinimumWidth()),
                        (int) (200 * Level.scaleFactor + click.getMinimumHeight()));

                click.draw(canvas);
            }

            if (showText) {
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText(text, textPoint.x, textPoint.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText(text, textPoint.x, textPoint.y, textPaint);
            }

            if (showHoleOut) {
                if (holeRadius < 3100) { // 2100
                    if (holeAlpha > 0)
                        holePaint.setAlpha((int) (holePaint.getAlpha() - 10 * scaleFactor)); // - 5
                    canvas.drawCircle(Level.screenWidth / 2, Level.screenHeight / 2, holeRadius += 25 * scaleFactor, holePaint);
                } else {
                    showHoleOut = false;
                }
            }

        } else {
            canvas.drawBitmap(background, screenWidth - background.getWidth(),
                    screenHeight - background.getHeight(), null);
        }

        if (showHoleIn) {
            setOnTouchListener(null);
            if (holeRadius > holeRadiusLimit) {
                canvas.drawCircle(screenWidth/2,screenHeight/2,
                        holeRadius-= 25 * scaleFactor, holePaint);        //25
                if (holePaint.getAlpha() < 250) {
                    holePaint.setAlpha((int) (holePaint.getAlpha() + holeAlphaMult));
                } else {
                    holePaint.setAlpha(250);
                }
            } else {
                holePaint.setAlpha(250);
                canvas.drawCircle(screenWidth/2,screenHeight/2,
                        holeRadius, holePaint);
                canvas.drawColor(Color.BLACK);
                controller.doAfterHoleAction();
            }
        }

        invalidate();
    }
}

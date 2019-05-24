package com.armageddon.luckysnake.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.controller.ControllerMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifDrawable;


public class PanelMenu extends Panel implements ShowHoleIn, IlevelsOpen {

    MainActivity activity;
    Map<String,Rect> buttons = new HashMap<>();
    Paint paintWhite = new Paint();
    Paint paintGreen = new Paint();

    boolean showHoleIn;

    GifDrawable exitButton;
    GifDrawable rulesButton;
    GifDrawable settingButton;
    GifDrawable recordButton;

    Bitmap background;
    Bitmap playButton;
    Bitmap title;
    Bitmap rule1;
    Bitmap rule2;
    Bitmap level1icon;
    Bitmap level2icon;
    Bitmap level3icon;
    Bitmap level4icon;
    Bitmap level5icon;
    Bitmap level6icon;
    Bitmap level7icon;
    Bitmap lockicon;

    Point headerPoint = new Point();
    Point recordPoint = new Point();
    Point rulesPoint = new Point();
    Point settingsPoint = new Point();
    Point continuePoint = new Point();
    Point continuePoint2 = new Point();
    Point scorePoint = new Point();
    Point musicPoint = new Point();
    Point soundFxPoint = new Point();
    Point vibrationPoint = new Point();

    Rect backRect = new Rect();
    Rect exitRect = new Rect();
    Rect playRect = new Rect();
    Rect recordRect = new Rect();
    Rect recordRect2 = new Rect();
    Rect rulesRect = new Rect();
    Rect settingsRect = new Rect();
    Rect radioButtonRect1 = new Rect();
    Rect radioButtonRect2 = new Rect();
    Rect radioButtonRect3 = new Rect();
    Rect continueRect = new Rect();
    Rect level1Rect = new Rect();
    Rect level2Rect = new Rect();
    Rect level3Rect = new Rect();
    Rect level4Rect = new Rect();
    Rect level5Rect = new Rect();
    Rect level6Rect = new Rect();
    Rect level7Rect = new Rect();


    ControllerMenu controller;

    public PanelMenu (Context context) {
        super(((MainActivity) context));
    }

    public PanelMenu (MainActivity activity) {
        super(activity);
        this.activity = activity;
        bestRecord = activity.getBestRecord();
        paintWhite.setColor(Color.WHITE);
        paintWhite.setStyle(Paint.Style.STROKE);
        paintWhite.setAntiAlias(true);
        paintWhite.setStrokeWidth(10 * scaleFactor);

        paintGreen.setColor(Color.rgb(58,98,80));
        paintGreen.setStyle(Paint.Style.FILL);
        paintGreen.setAntiAlias(true);
        paintGreen.setStrokeWidth(10 * scaleFactor);

//        holePaint.setAlpha((int) holeAlphaMult);



        try {
            background = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "background.png"));
            title = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "title.png"));
            playButton = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "playbutton.png"));
            rule1 = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "rule1.png"));
            rule2 = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "rule2.png"));
            level1icon = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "level1icon.png"));
            level2icon = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "level2icon.png"));
            level3icon = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "level3icon.png"));
            level4icon = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "level4icon.png"));
            level5icon = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "level5icon.png"));
            level6icon = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "level6icon.png"));
            level7icon = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "level7icon.png"));
            lockicon = BitmapFactory.decodeStream(
                    activity.getAssets().open( modePng + "lockicon.png"));
            exitButton = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "exit.gif"));
            recordButton = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "record.gif"));
            rulesButton = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "rules.gif"));
            settingButton  = new GifDrawable(
                    activity.getAssets().openFd( modeGif + "settings.gif"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        backRect.set(0,0,screenWidth, screenHeight);

        exitRect.set(
                (int)(screenWidth - exitButton.getMinimumWidth() - 20 * scaleFactor),
                (int)(20 * scaleFactor),
                (int)(screenWidth - 20 * scaleFactor),
                (int)(20 * scaleFactor + exitButton.getMinimumHeight()));

        playRect.set(
                (screenWidth/2 - playButton.getWidth()/2),
                (screenHeight/2 - playButton.getHeight()/2),
                ((screenWidth/2 - playButton.getWidth()/2) + playButton.getWidth()),
                ((screenHeight/2 - playButton.getHeight()/2) + playButton.getHeight()));

        rulesRect.set(
                (screenWidth/2 - rulesButton.getMinimumWidth()/2),
                (int)(screenHeight - 245 * scaleFactor),
                ((screenWidth/2 - rulesButton.getMinimumWidth()/2) + rulesButton.getMinimumWidth()),
                (int)((screenHeight - 245 * scaleFactor) + rulesButton.getMinimumHeight()));

        recordRect.set(
                (int)(rulesRect.left - 300 * scaleFactor),
                (int)(screenHeight - 245 * scaleFactor),
                (int)((rulesRect.left - 300 * scaleFactor) + recordButton.getMinimumWidth()),
                (int)((screenHeight - 245 * scaleFactor) + recordButton.getMinimumHeight()));

        recordRect2.set(
                (int)(screenWidth/2 - 275 * scaleFactor),
                (int)(screenHeight/2 - 70 * scaleFactor),
                (int)((screenWidth/2 - 275 * scaleFactor) + recordButton.getMinimumWidth()),
                (int)((screenHeight/2 - 70 * scaleFactor) + recordButton.getMinimumHeight()));

        settingsRect.set(
                (rulesRect.right + rulesRect.left - recordRect.right),
                (int)(screenHeight - 245 * scaleFactor),
                ((rulesRect.right + rulesRect.left - recordRect.right) + settingButton.getMinimumWidth()),
                (int)((screenHeight - 245 * scaleFactor) + recordButton.getMinimumHeight()));



        continueRect.set(
                (int)(screenWidth/2 - 275 * scaleFactor),
                (int)(screenHeight - 180 * scaleFactor),
                (int)((screenWidth/2 - 275 * scaleFactor) + 550 * scaleFactor),
                (int)((screenHeight - 180 * scaleFactor) + 100 * scaleFactor));

        rulesPoint.set(
                (int)(rulesRect.left + 50 * scaleFactor),
                (int)(screenHeight - 30 * scaleFactor));

        recordPoint.set(
                (int)(recordRect.left + 12 * scaleFactor),
                (int)(screenHeight - 30 * scaleFactor));

        settingsPoint.set(
                (int)(settingsRect.left + 25 * scaleFactor),
                (int)(screenHeight - 30 * scaleFactor));

        continuePoint.set(
                (int)(screenWidth/2 - 275 * scaleFactor),
                (int)(screenHeight - 110 * scaleFactor));

        continuePoint2.set(
                (int)(gameField.left + 200 * scaleFactor),
                (int)(screenHeight - 70 * scaleFactor));

        scorePoint.set(
                (int)(screenWidth/2  -50 * scaleFactor),
                (int)(recordRect2.centerY() + 30 * scaleFactor));

        musicPoint.set(
                (int)(screenWidth/2  - 220 * scaleFactor),
                (int)(screenHeight/2 - 50 * scaleFactor));

        soundFxPoint.set(
                (int)(screenWidth/2  - 220 * scaleFactor),
                (int)(screenHeight/2 + 50 * scaleFactor));

        vibrationPoint.set(
                (int)(screenWidth/2  - 220 * scaleFactor),
                (int)(screenHeight/2 + 150 * scaleFactor));

        radioButtonRect1.set(
                (int)(screenWidth/2 + 160 * scaleFactor),
                (int)(musicPoint.y - 50 * scaleFactor),
                (int)((screenWidth/2 + 160 * scaleFactor) + 60 * scaleFactor),
                (int)((musicPoint.y - 50 * scaleFactor) + 60 * scaleFactor));

        radioButtonRect2.set(
                (int)(screenWidth/2 + 160 * scaleFactor),
                (int)(soundFxPoint.y - 50 * scaleFactor),
                (int)((screenWidth/2 + 160 * scaleFactor) + 60 * scaleFactor),
                (int)((soundFxPoint.y - 50 * scaleFactor) + 60 * scaleFactor));

        radioButtonRect3.set(
                (int)(screenWidth/2 + 160 * scaleFactor),
                (int)(vibrationPoint.y - 50 * scaleFactor),
                (int)((screenWidth/2 + 160 * scaleFactor) + 60 * scaleFactor),
                (int)((vibrationPoint.y - 50 * scaleFactor) + 60 * scaleFactor));

        headerPoint.set(
                (int)(screenWidth/2 - 420 * scaleFactor),
                (int) (210 * scaleFactor));










//        int temp = (int) (screenHeight - 650 * scaleFactor);
//        int temp = (int) (150 * scaleFactor);
//        int temp = (int) (screenHeight/2 - 100 * scaleFactor);
            int temp = (int) (headerPoint.y + 100 * scaleFactor);

        level1Rect.set(
                (int)(screenWidth/2 - 590 * scaleFactor),
                (int)(temp),
                (int)(screenWidth/2 - 590 * scaleFactor + level1icon.getWidth()),
                (int)(temp + level1icon.getHeight()));

        level2Rect.set(
                (int)(level1Rect.right + 30 * scaleFactor),
                (int)(temp),
                (int)(level1Rect.right + 30 * scaleFactor + level1icon.getWidth()),
                (int)(temp + level1icon.getHeight()));

        level3Rect.set(
                (int)(level2Rect.right + 30 * scaleFactor),
                (int)(temp),
                (int)(level2Rect.right + 30 * scaleFactor + level1icon.getWidth()),
                (int)(temp + level1icon.getHeight()));

        level4Rect.set(
                (int)(level3Rect.right + 30 * scaleFactor),
                (int)(temp),
                (int)(level3Rect.right + 30 * scaleFactor + level1icon.getWidth()),
                (int)(temp + level1icon.getHeight()));

        level5Rect.set(
                (int)(level4Rect.right + 30 * scaleFactor),
                (int)(temp),
                (int)(level4Rect.right + 30 * scaleFactor + level1icon.getWidth()),
                (int)(temp + level1icon.getHeight()));

        level6Rect.set(
                (int)(level5Rect.right + 30 * scaleFactor),
                (int)(temp),
                (int)(level5Rect.right + 30 * scaleFactor + level1icon.getWidth()),
                (int)(temp + level1icon.getHeight()));

        level7Rect.set(
                (int)(level6Rect.right + 30 * scaleFactor),
                (int)(temp),
                (int)(level6Rect.right + 30 * scaleFactor + level1icon.getWidth()),
                (int)(temp + level1icon.getHeight()));




        buttons.put("exit",exitRect);
        buttons.put("play",playRect);
        buttons.put("record",recordRect);
        buttons.put("rules",rulesRect);
        buttons.put("settings",settingsRect);
        buttons.put("radiomusic",radioButtonRect1);
        buttons.put("radiofx",radioButtonRect2);
        buttons.put("radiovibrator",radioButtonRect3);
        buttons.put("continue",continueRect);
        buttons.put("level1",level1Rect);
        buttons.put("level2",level2Rect);
        buttons.put("level3",level3Rect);
        buttons.put("level4",level4Rect);
        buttons.put("level5",level5Rect);
        buttons.put("level6",level6Rect);
        buttons.put("level7",level7Rect);



        initializeLevelsIcons();
        holeInInitializer();

//        controller = new ControllerMenu(buttons,this,activity);

    }


    public Map<String, Rect> getButtons() {
        return buttons;
    }

    public void setController(ControllerMenu controller) {
        this.controller = controller;
    }

    @Override
    public void initializeLevelsIcons() {
        switch (activity.getOpenedLevel()) {
            case 1:
                level2icon = lockicon;
                level3icon = lockicon;
                level4icon = lockicon;
                level5icon = lockicon;
                level6icon = lockicon;
                level7icon = lockicon;
                break;
            case 2:
                level3icon = lockicon;
                level4icon = lockicon;
                level5icon = lockicon;
                level6icon = lockicon;
                level7icon = lockicon;
                break;
            case 3:
                level4icon = lockicon;
                level5icon = lockicon;
                level6icon = lockicon;
                level7icon = lockicon;
                break;
            case 4:
                level5icon = lockicon;
                level6icon = lockicon;
                level7icon = lockicon;
                break;
            case 5:
                level6icon = lockicon;
                level7icon = lockicon;
                break;
            case 6:
                level7icon = lockicon;
                break;
            default:
                level1icon.recycle();
                level2icon.recycle();
                level3icon.recycle();
                level4icon.recycle();
                level5icon.recycle();
                level6icon.recycle();
                level7icon.recycle();
                lockicon.recycle();
                try {
                    level1icon = BitmapFactory.decodeStream(
                            activity.getAssets().open( modePng + "level1icon.png"));
                    level2icon = BitmapFactory.decodeStream(
                            activity.getAssets().open( modePng + "level2icon.png"));
                    level3icon = BitmapFactory.decodeStream(
                            activity.getAssets().open( modePng + "level3icon.png"));
                    level4icon = BitmapFactory.decodeStream(
                            activity.getAssets().open( modePng + "level4icon.png"));
                    level5icon = BitmapFactory.decodeStream(
                            activity.getAssets().open( modePng + "level5icon.png"));
                    level6icon = BitmapFactory.decodeStream(
                            activity.getAssets().open( modePng + "level6icon.png"));
                    level7icon = BitmapFactory.decodeStream(
                            activity.getAssets().open( modePng + "level7icon.png"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void showHoleIn() {
        holeInInitializer();
        showHoleIn = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        exitButton.recycle();
        rulesButton.recycle();
        settingButton.recycle();
        recordButton.recycle();
        background.recycle();
        playButton.recycle();
        title.recycle();
        rule1.recycle();
        rule2.recycle();
        level1icon.recycle();
        level2icon.recycle();
        level3icon.recycle();
        level4icon.recycle();
        level5icon.recycle();
        level6icon.recycle();
        level7icon.recycle();
        lockicon.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("************************");

        System.out.println(getWidth());
        System.out.println(getHeight());

        if (showElemets) {

            super.onDraw(canvas);
            canvas.drawColor(Color.BLACK);


            if (controller.getMenuSelect() == 0) {

                setOnTouchListener(controller);
                canvas.drawBitmap(title, null, backRect, null);

                // header draw
                headerPaint.setStyle(Paint.Style.FILL);
                headerPaint.setColor(Color.WHITE);
                canvas.drawText("Lucky Snake", headerPoint.x, headerPoint.y, headerPaint);
                headerPaint.setStyle(Paint.Style.STROKE);
                headerPaint.setColor(Color.BLACK);
                canvas.drawText("Lucky Snake", headerPoint.x, headerPoint.y, headerPaint);

                // rules text
                signaturePaint.setStyle(Paint.Style.FILL);
                signaturePaint.setColor(Color.WHITE);
                canvas.drawText("Rules", rulesPoint.x, rulesPoint.y, signaturePaint);
                signaturePaint.setStyle(Paint.Style.STROKE);
                signaturePaint.setColor(Color.BLACK);
                canvas.drawText("Rules", rulesPoint.x, rulesPoint.y, signaturePaint);

                // record text
                signaturePaint.setStyle(Paint.Style.FILL);
                signaturePaint.setColor(Color.WHITE);
                canvas.drawText("Record", recordPoint.x, recordPoint.y, signaturePaint);
                signaturePaint.setStyle(Paint.Style.STROKE);
                signaturePaint.setColor(Color.BLACK);
                canvas.drawText("Record", recordPoint.x, recordPoint.y, signaturePaint);

                // settings text
                signaturePaint.setStyle(Paint.Style.FILL);
                signaturePaint.setColor(Color.WHITE);
                canvas.drawText("Settings", settingsPoint.x, settingsPoint.y, signaturePaint);
                signaturePaint.setStyle(Paint.Style.STROKE);
                signaturePaint.setColor(Color.BLACK);
                canvas.drawText("Settings", settingsPoint.x, settingsPoint.y, signaturePaint);

                canvas.drawBitmap(playButton, null, playRect, null);

                exitButton.setBounds(exitRect);
                rulesButton.setBounds(rulesRect);
                recordButton.setBounds(recordRect);
                settingButton.setBounds(settingsRect);
                exitButton.draw(canvas);
                rulesButton.draw(canvas);
                recordButton.draw(canvas);
                settingButton.draw(canvas);

            }

            if (controller.getMenuSelect() == 1) {
                canvas.drawBitmap(title, null, backRect, null);
                setOnTouchListener(null);
                setOnClickListener(controller.getOnClickListener1());

                // header draw
                headerPaint.setStyle(Paint.Style.FILL);
                headerPaint.setColor(Color.WHITE);
                canvas.drawText("Best Record", headerPoint.x, headerPoint.y, headerPaint);
                headerPaint.setStyle(Paint.Style.STROKE);
                headerPaint.setColor(Color.BLACK);
                canvas.drawText("Best Record", headerPoint.x, headerPoint.y, headerPaint);

                //score draw
                scorePaint.setStyle(Paint.Style.FILL);
                scorePaint.setColor(Color.WHITE);
                canvas.drawText(String.valueOf(bestRecord), scorePoint.x, scorePoint.y, scorePaint);
                scorePaint.setStyle(Paint.Style.STROKE);
                scorePaint.setColor(Color.BLACK);
                canvas.drawText(String.valueOf(bestRecord), scorePoint.x, scorePoint.y, scorePaint);

                //Continue draw
                scorePaint.setStyle(Paint.Style.FILL);
                scorePaint.setColor(Color.WHITE);
                canvas.drawText("Tap to continue", continuePoint.x, continuePoint.y, scorePaint);
                scorePaint.setStyle(Paint.Style.STROKE);
                scorePaint.setColor(Color.BLACK);
                canvas.drawText("Tap to continue", continuePoint.x, continuePoint.y, scorePaint);

                recordButton.setBounds(recordRect2);
                recordButton.draw(canvas);

            }

            if (controller.getMenuSelect() == 2) {
                setOnTouchListener(null);
                setOnClickListener(controller.getOnClickListener2());

//                canvas.drawBitmap(background, screenWidth - background.getWidth(),
//                        screenHeight - background.getHeight(), null);

//                canvas.drawBitmap(background, null, backRect, null);

                if (controller.getRule() == 0) {
                    canvas.drawBitmap(rule1, screenWidth - background.getWidth(),
                            screenHeight - background.getHeight(), null);
//                    canvas.drawBitmap(rule1, null, backRect, null);
                } else {
                    canvas.drawBitmap(rule2, screenWidth - background.getWidth(),
                            screenHeight - background.getHeight(), null);
//                    canvas.drawBitmap(rule2, null, backRect, null);
                }

                // Continue draw
                scorePaint.setStyle(Paint.Style.FILL);
                scorePaint.setColor(Color.WHITE);
                canvas.drawText("Tap to continue", continuePoint2.x, continuePoint2.y, scorePaint);
                scorePaint.setStyle(Paint.Style.STROKE);
                scorePaint.setColor(Color.BLACK);
                canvas.drawText("Tap to continue", continuePoint2.x, continuePoint2.y, scorePaint);
            }

            if (controller.getMenuSelect() == 3) {
                setOnTouchListener(controller.getRadioListener());
                canvas.drawBitmap(title, null, backRect, null);

                headerPaint.setStyle(Paint.Style.FILL);
                headerPaint.setColor(Color.WHITE);
                canvas.drawText("Game Settings", headerPoint.x - 50 * scaleFactor, headerPoint.y, headerPaint);
                headerPaint.setStyle(Paint.Style.STROKE);
                headerPaint.setColor(Color.BLACK);
                canvas.drawText("Game Settings", headerPoint.x - 50 * scaleFactor, headerPoint.y, headerPaint);

                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("Music", musicPoint.x, musicPoint.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("Music", musicPoint.x, musicPoint.y, textPaint);

                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("Sound FX", soundFxPoint.x, soundFxPoint.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("Sound FX", soundFxPoint.x, soundFxPoint.y, textPaint);

                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("Vibration", vibrationPoint.x, vibrationPoint.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("Vibration", vibrationPoint.x, vibrationPoint.y, textPaint);

                scorePaint.setStyle(Paint.Style.FILL);
                scorePaint.setColor(Color.WHITE);
                canvas.drawText("Tap to continue", continuePoint.x, continuePoint.y, scorePaint);
                scorePaint.setStyle(Paint.Style.STROKE);
                scorePaint.setColor(Color.BLACK);
                canvas.drawText("Tap to continue", continuePoint.x, continuePoint.y, scorePaint);


                if (controller.isMusicOn()) {
                    canvas.drawOval(radioButtonRect1.left, radioButtonRect1.top, radioButtonRect1.right, radioButtonRect1.bottom, paintGreen);
                }
                canvas.drawOval(radioButtonRect1.left, radioButtonRect1.top, radioButtonRect1.right, radioButtonRect1.bottom, paintWhite);

                if (controller.isSoundOn()) {
                    canvas.drawOval(radioButtonRect2.left, radioButtonRect2.top, radioButtonRect2.right, radioButtonRect2.bottom, paintGreen);
                }
                canvas.drawOval(radioButtonRect2.left, radioButtonRect2.top, radioButtonRect2.right, radioButtonRect2.bottom, paintWhite);

                if (controller.isVibrationOn()) {
                    canvas.drawOval(radioButtonRect3.left, radioButtonRect3.top, radioButtonRect3.right, radioButtonRect3.bottom, paintGreen);
                }
                canvas.drawOval(radioButtonRect3.left, radioButtonRect3.top, radioButtonRect3.right, radioButtonRect3.bottom, paintWhite);
            }

            if (controller.getMenuSelect() == 4) {
                System.out.println(activity.getOpenedLevel());
                setOnTouchListener(controller.getLevelListener());
                canvas.drawBitmap(title, null, backRect, null);

                headerPaint.setStyle(Paint.Style.FILL);
                headerPaint.setColor(Color.WHITE);
                canvas.drawText("Choose level", headerPoint.x - 20 * scaleFactor, headerPoint.y, headerPaint);
                headerPaint.setStyle(Paint.Style.STROKE);
                headerPaint.setColor(Color.BLACK);
                canvas.drawText("Choose level", headerPoint.x - 20 * scaleFactor, headerPoint.y, headerPaint);


                canvas.drawBitmap(level1icon, null, level1Rect, null);
                canvas.drawBitmap(level2icon, null, level2Rect, null);
                canvas.drawBitmap(level3icon, null, level3Rect, null);
                canvas.drawBitmap(level4icon, null, level4Rect, null);
                canvas.drawBitmap(level5icon, null, level5Rect, null);
                canvas.drawBitmap(level6icon, null, level6Rect, null);
                canvas.drawBitmap(level7icon, null, level7Rect, null);

                scorePaint.setStyle(Paint.Style.FILL);
                scorePaint.setColor(Color.WHITE);
                canvas.drawText(" Tap to go back", continuePoint.x, continuePoint.y, scorePaint);
                scorePaint.setStyle(Paint.Style.STROKE);
                scorePaint.setColor(Color.BLACK);
                canvas.drawText(" Tap to go back", continuePoint.x, continuePoint.y, scorePaint);

            }

            if (showHoleIn) {
                System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
                setOnTouchListener(null);
                if (holeRadius > holeRadiusLimit) {
                    canvas.drawCircle(screenWidth / 2, screenHeight / 2,
                            holeRadius -= 25 * scaleFactor, holePaint);        //25
                    if (holePaint.getAlpha() < 250) {
                        holePaint.setAlpha((int) (holePaint.getAlpha() + holeAlphaMult));
                    } else {
                        holePaint.setAlpha(250);
                    }
                } else {
                    holePaint.setAlpha(250);
                    canvas.drawCircle(screenWidth / 2, screenHeight / 2,
                            holeRadius, holePaint);
//                holePaint.setAlpha(250);
                    canvas.drawColor(Color.BLACK);
//                canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(),paintWhite);
                    controller.doAfterHoleAction();
                }
            }

            invalidate();
        }
    }
}

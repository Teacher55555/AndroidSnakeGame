package com.armageddon.luckysnake.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.controller.Controller;
import com.armageddon.luckysnake.controller.ControllerGameOver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifDrawable;

public class PanelGameOver extends Panel implements ShowHoleIn {

    private GifDrawable record;
    private GifDrawable rain;
    private GifDrawable repeatButton;
    private GifDrawable shareButton;
    private GifDrawable likeButton;
    private GifDrawable exitButton;
    private GifDrawable homeButton;
    private Bitmap background;
    private boolean isBestRecord;
    private boolean showHoleIn;
    private int currentScore;
    private int bestRecord;

    private Map<String,Rect> buttons = new HashMap<>();
    Paint black = new Paint();

    Rect recordRect = new Rect();
    Rect rainRect = new Rect();
    Rect repeatRect = new Rect();
    Rect shareRect = new Rect();
    Rect likeRect = new Rect();
    Rect exitRect = new Rect();
    Rect homeRect = new Rect();

    Point headerPoint = new Point();
    Point headerPoint2 = new Point();
    Point repeatPoint = new Point();
    Point likePoint = new Point();
    Point sharePoint = new Point();
    Point homePoint = new Point();
    Point scorePoint = new Point();
    Point bestRecordPoint = new Point();
    Point scorePoint2 = new Point();

    Controller controller;

    public PanelGameOver (MainActivity activity, int bestRecord, int currentScore) {
        super(activity);
        this.currentScore = currentScore;
        this.bestRecord = bestRecord;
        if (currentScore > bestRecord) {
            isBestRecord = true;
        }


            try {
                background = BitmapFactory.decodeStream(
                        activity.getAssets().open( modePng + "background.png"));
                record = new GifDrawable(
                        activity.getAssets().openFd( modeGif + "record.gif"));
                rain = new GifDrawable(
                        activity.getAssets().openFd( modeGif + "rain.gif"));
                repeatButton = new GifDrawable(
                        activity.getAssets().openFd( modeGif + "repeat.gif"));
                shareButton  = new GifDrawable(
                        activity.getAssets().openFd( modeGif + "share.gif"));
                likeButton  = new GifDrawable(
                        activity.getAssets().openFd( modeGif + "like.gif"));
                exitButton  = new GifDrawable(
                        activity.getAssets().openFd( modeGif + "exit.gif"));
//                settingsButton  = new GifDrawable(
//                        activity.getAssets().openFd( modeGif + "settingspause.gif"));
                homeButton  = new GifDrawable(
                        activity.getAssets().openFd( modeGif + "home.gif"));
                rain.setAlpha(180);

            } catch (IOException e) {
                e.printStackTrace();
            }

            headerPaint.setTextSize(140 * scaleFactor);
            textPaint.setTextSize(90 * scaleFactor);



        recordRect.set(
                (int)(gameField.left + 250 * scaleFactor),
                (int)(gameField.top + 200 * scaleFactor),
                (int)((gameField.left + 250 * scaleFactor) + record.getMinimumWidth()),
                (int)((gameField.top + 200 * scaleFactor) + record.getMinimumHeight()));

        rainRect.set(gameField.left, gameField.top, gameField.right, gameField.bottom);

        repeatRect.set(
                (int)(gameField.left + 160 * scaleFactor),
                (int)(gameField.bottom - 200 * scaleFactor),
                (int)((gameField.left + 160 * scaleFactor) + repeatButton.getMinimumWidth()),
                (int)((gameField.bottom - 200 * scaleFactor) + repeatButton.getMinimumHeight()));

        likeRect.set(
                (int)(repeatRect.right + 60 * scaleFactor),
                (int)(repeatRect.top),
                (int)((repeatRect.right + 60 * scaleFactor) + likeButton.getMinimumWidth()),
                (int)(repeatRect.bottom));

        shareRect.set(
                (int)(likeRect.right + 60 * scaleFactor),
                (int)(repeatRect.top),
                (int)((likeRect.right + 60 * scaleFactor) + shareButton.getMinimumWidth()),
                (int)(repeatRect.bottom));
//
        homeRect.set(
                (int)(shareRect.right + 60 * scaleFactor),
                (int)(repeatRect.top),
                (int)((shareRect.right + 60 * scaleFactor) + homeButton.getMinimumWidth()),
                (int)(repeatRect.bottom));

        exitRect.set(
                (int)(screenWidth - exitButton.getMinimumWidth() - 20 * scaleFactor),
                (int)(20 * scaleFactor),
                (int)(screenWidth - 20 * scaleFactor),
                (int)(20 * scaleFactor + exitButton.getMinimumHeight()));


        headerPoint.set(
                (int)(gameField.left + 115 * scaleFactor),
                (int)(gameField.top + 150 * scaleFactor));

        headerPoint2.set(
                (int)(gameField.left + 150 * scaleFactor),
                (int)(gameField.top + 150 * scaleFactor));

        repeatPoint.set(
                (int)(repeatRect.left),
                (int)(repeatRect.bottom + 40 * scaleFactor));

        likePoint.set(
                (int)(likeRect.left + 20 * scaleFactor),
                (int)(repeatRect.bottom + 40 * scaleFactor));

        homePoint.set(
                (int)(homeRect.left + 5 * scaleFactor),
                (int)(homeRect.bottom + 40 * scaleFactor));

        sharePoint.set(
                (int)(shareRect.left + 10 * scaleFactor),
                (int)(repeatRect.bottom + 40 * scaleFactor));

        scorePoint.set(
                (int)(gameField.left + 450 * scaleFactor),
                (int)(screenHeight - 330 * scaleFactor));

        bestRecordPoint.set(
                (int)(gameField.left + 200 * scaleFactor),
                (int)(gameField.top + 290 * scaleFactor));

        scorePoint2.set(
                (int)(gameField.left + 200 * scaleFactor),
                (int)(gameField.top + 380 * scaleFactor));






        buttons.put("replay",repeatRect);
        buttons.put("like",likeRect);
        buttons.put("share",shareRect);
        buttons.put("exit",exitRect);
        buttons.put("home",homeRect);

        black.setAlpha(100);

        holeInInitializer();
    }

    public void setController(Controller controller) {
        this.controller = controller;
        setOnTouchListener(controller);
    }

    public Map<String, Rect> getButtons() {
        return buttons;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        background.recycle();
        record.recycle();
        rain.recycle();
        repeatButton.recycle();
        shareButton.recycle();
        likeButton.recycle();
        exitButton.recycle();
        homeButton.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        if (showElemets) {

            super.onDraw(canvas);


            canvas.drawBitmap(background, screenWidth - background.getWidth(),
                    screenHeight - background.getHeight(), null);

            canvas.drawRect(gameField, black);

            if (isBestRecord) {

                // header draw
                headerPaint.setStyle(Paint.Style.FILL);
                headerPaint.setColor(Color.WHITE);
                canvas.drawText("New Record", headerPoint.x, headerPoint.y, headerPaint);
                headerPaint.setStyle(Paint.Style.STROKE);
                headerPaint.setColor(Color.BLACK);
                canvas.drawText("New Record", headerPoint.x, headerPoint.y, headerPaint);

                //Cup icon draw
                record.setBounds(recordRect);
                record.draw(canvas);

                //Score draw
                scorePaint.setStyle(Paint.Style.FILL);
                scorePaint.setColor(Color.WHITE);
                canvas.drawText(String.valueOf(currentScore), scorePoint.x, scorePoint.y, scorePaint);
                scorePaint.setStyle(Paint.Style.STROKE);
                scorePaint.setColor(Color.BLACK);
                canvas.drawText(String.valueOf(currentScore), scorePoint.x, scorePoint.y, scorePaint);


            } else {

                // header draw
                headerPaint.setStyle(Paint.Style.FILL);
                headerPaint.setColor(Color.WHITE);
                canvas.drawText("Game Over", headerPoint2.x, headerPoint2.y, headerPaint);
                headerPaint.setStyle(Paint.Style.STROKE);
                headerPaint.setColor(Color.BLACK);
                canvas.drawText("Game Over", headerPoint2.x, headerPoint2.y, headerPaint);

                // BestRecord draw
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("Best record: " + bestRecord, bestRecordPoint.x, bestRecordPoint.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("Best record: " + bestRecord, bestRecordPoint.x, bestRecordPoint.y, textPaint);

                // CurrentScore draw
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("You score: " + currentScore, scorePoint2.x, scorePoint2.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("You score: " + currentScore, scorePoint2.x, scorePoint2.y, textPaint);

                rain.setBounds(rainRect);
                rain.draw(canvas);
            }

            signaturePaint.setStyle(Paint.Style.FILL);
            signaturePaint.setColor(Color.WHITE);
            canvas.drawText("Restart", repeatPoint.x, repeatPoint.y, signaturePaint);
            signaturePaint.setStyle(Paint.Style.STROKE);
            signaturePaint.setColor(Color.BLACK);
            canvas.drawText("Restart", repeatPoint.x, repeatPoint.y, signaturePaint);

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
            canvas.drawText("Home", homePoint.x, homePoint.y, signaturePaint);
            signaturePaint.setStyle(Paint.Style.STROKE);
            signaturePaint.setColor(Color.BLACK);
            canvas.drawText("Home", homePoint.x, homePoint.y, signaturePaint);


            repeatButton.setBounds(repeatRect);
            likeButton.setBounds(likeRect);
            shareButton.setBounds(shareRect);
            exitButton.setBounds(exitRect);
            homeButton.setBounds(homeRect);

            homeButton.draw(canvas);
            repeatButton.draw(canvas);
            likeButton.draw(canvas);
            shareButton.draw(canvas);
            exitButton.draw(canvas);


            if (showHoleIn) {
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
                    canvas.drawColor(Color.BLACK);
                    ((ControllerGameOver) controller).doAfterHoleAction();
                }
            }
            invalidate();
        }
    }

    @Override
    public void showHoleIn() {
        showHoleIn = true;
    }
}

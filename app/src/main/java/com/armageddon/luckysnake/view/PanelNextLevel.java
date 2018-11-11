package com.armageddon.luckysnake.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.armageddon.luckysnake.common.MainActivity;
import com.armageddon.luckysnake.controller.Controller;
import com.armageddon.luckysnake.controller.ControllerNextLevel;

import java.io.IOException;

public class PanelNextLevel extends Panel implements ShowHoleIn {

    private Bitmap background;
    private int level;
    private boolean showHoleIn;

    Point levelPoint = new Point();
    Point bonusLevelPoint = new Point();
    Point completePoint = new Point();
    Point continuePoint = new Point();

    ControllerNextLevel controller;


    public PanelNextLevel(MainActivity activity, int level) {
        super(activity);
        this.level = level;
        holeInInitializer();
        try {
            background = BitmapFactory.decodeStream(
                    activity.getAssets().open(modePng + "background.png"));
        } catch (IOException e){ e.printStackTrace();}

        levelPoint.set(
                (int)(screenWidth - 970 * scaleFactor),
                (int)(280 * scaleFactor));

        bonusLevelPoint.set(
                (int)(screenWidth - 1080 * scaleFactor),
                (int)(280 * scaleFactor));

        completePoint.set(
                (int)(screenWidth - 1070 * scaleFactor),
                (int)(420 * scaleFactor));

        continuePoint.set(
                (int)(screenWidth - 1000 * scaleFactor),
                (int)(630 * scaleFactor));

    }

    public void setController(ControllerNextLevel controller) {
        this.controller = controller;
        setOnClickListener(controller);
    }

    @Override
    public void showHoleIn() {
        showHoleIn = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        background.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (showElemets) {

            super.onDraw(canvas);

            // draw background
            canvas.drawBitmap(background, screenWidth - background.getWidth(),
                    screenHeight - background.getHeight(), null);

            if (level == 0) {
                textPaint.setTextSize(140 * scaleFactor);
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("Bonus level ", bonusLevelPoint.x, bonusLevelPoint.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("Bonus level", bonusLevelPoint.x, bonusLevelPoint.y, textPaint);
            } else {
                textPaint.setTextSize(160 * scaleFactor);
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(Color.WHITE);
                canvas.drawText("Level " + level, levelPoint.x, levelPoint.y, textPaint);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.BLACK);
                canvas.drawText("Level " + level, levelPoint.x, levelPoint.y, textPaint);
            }

            textPaint.setTextSize(140 * scaleFactor);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(Color.WHITE);
            canvas.drawText("Completed!", completePoint.x, completePoint.y, textPaint);
            textPaint.setStyle(Paint.Style.STROKE);
            textPaint.setColor(Color.BLACK);
            canvas.drawText("Completed!", completePoint.x, completePoint.y, textPaint);

            textPaint.setTextSize(80 * scaleFactor);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(Color.WHITE);
            canvas.drawText("Tap to continue", continuePoint.x, continuePoint.y, textPaint);
            textPaint.setStyle(Paint.Style.STROKE);
            textPaint.setColor(Color.BLACK);
            canvas.drawText("Tap to continue", continuePoint.x, continuePoint.y, textPaint);

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
                    controller.doAfterHoleAction();
                }
            }
            invalidate();
        }
    }
}

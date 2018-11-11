package com.armageddon.luckysnake.view;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.View;

import com.armageddon.luckysnake.common.MainActivity;

public abstract class Panel extends View {

    boolean showElemets = true;
    int screenHeight, screenWidth;
    int bestRecord;
    float scaleFactor;
    Rect gameField;
    String modePng;
    String modeGif;

    Typeface myFont;
    TextPaint signaturePaint = new TextPaint();
    TextPaint headerPaint = new TextPaint();
    TextPaint scorePaint = new TextPaint();

    TextPaint textPaint = new TextPaint();

    Paint holePaint = new Paint();
    Paint paintBlack = new Paint();

    int holeRadius;
    int holeAlpha;
    float holeAlphaMult;
    int holeRadiusLimit;

    public void setListenerOff () {
        setOnTouchListener(null);
    }

    void holeInInitializer () {

        if (scaleFactor == 1) {
            holeAlphaMult = 5f; // было 5
            holeRadiusLimit = 400; // было 400
            holeRadius = 1600;
        }

        if (scaleFactor == 1.5) {
            holeAlphaMult = 8f; //7
            holeRadiusLimit = 650;// 600
            holeRadius = 1900; //1800
        }

        if (scaleFactor == 2) {
            holeAlphaMult = 11f; // 9.25
            holeRadiusLimit = 850; // 740
            holeRadius = 2100; // 2100
        }

        holePaint.setColor(Color.BLACK);
        holePaint.setStrokeWidth(900 * scaleFactor); //800 // 900  - good // 1000
        holePaint.setStyle(Paint.Style.STROKE);
        holePaint.setAlpha(20);         // было 0
    }

    public void showElemets (boolean show){
        this.showElemets = show;
    }

    public Panel (MainActivity activity) {
        super(activity);
        screenHeight = activity.getScreenHeight();
        screenWidth = activity.getScreenWidth();
        scaleFactor = activity.getScaleFactor();
        gameField = activity.getGameField();
        modePng = activity.getModePng();
        modeGif = activity.getModeGif();
        myFont = Typeface.createFromAsset(activity.getAssets(),"font/curlz.ttf");

        headerPaint.setTypeface(Typeface.create(myFont,Typeface.BOLD));
        headerPaint.setAntiAlias(true);
        headerPaint.setStrokeWidth(3 * scaleFactor); // было 3
        headerPaint.setTextSize(180 * scaleFactor);

        scorePaint.setTypeface(Typeface.create(myFont,Typeface.BOLD));
        scorePaint.setAntiAlias(true);
        scorePaint.setStrokeWidth(1 *scaleFactor);
        scorePaint.setTextSize(90 * scaleFactor);

        textPaint.setTypeface(Typeface.create(myFont,Typeface.BOLD));
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(1 *scaleFactor);
        textPaint.setTextSize(70 * scaleFactor);

        signaturePaint.setTypeface(Typeface.create(myFont,Typeface.BOLD));
        signaturePaint.setAntiAlias(true);
        signaturePaint.setStrokeWidth(0.5f * scaleFactor); // было 0.5f
        signaturePaint.setTextSize(40 * scaleFactor);

        paintBlack.setColor(Color.BLACK);
    }
}

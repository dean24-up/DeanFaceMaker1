/**
 * Face class
 *
 * @author Sydney Dean
 * @date 13 February 2024
 * Draws the face on the canvas
 */
package com.example.deanfacemaker1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

/*
    External Citation
    Date: 15 February 2024
    Problem: Didn't remember how to set up a class to paint
    Resource: SurfaceViewDrawingSourceCode on Moodle
    Solution: referenced how classes were set up
 */
public class Face extends SurfaceView {
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;

    public Paint facePaint = new Paint();
    public Paint hairPaint = new Paint();
    public Paint eyePaint = new Paint();
    public Paint white = new Paint();

    /**
     * Constructor
     * Sets all paint variables
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        randomize();
        facePaint.setColor(skinColor);
        facePaint.setStyle(Paint.Style.FILL);

        hairPaint.setColor(hairColor);
        hairPaint.setStyle(Paint.Style.FILL);

        eyePaint.setColor(eyeColor);
        eyePaint.setStyle(Paint.Style.FILL);

        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);

    }

    /**
     * onDraw
     * @param canvas
     * draws the face on the canvas
     */
    public void onDraw(Canvas canvas) {
        //draw skin/face
        canvas.drawCircle(600.0f, 550.0f, 350.0f, facePaint);
        drawHair(canvas);
        drawEyes(canvas);

    }

    /**
     * drawEyes
     * @param canvas
     * draws two eyes on the canvas
     */
    public void drawEyes(Canvas canvas) {
        canvas.drawCircle(475.0f, 500.0f, 75.0f, white);
        canvas.drawCircle(725.0f, 500.0f, 75.0f, white);

        canvas.drawCircle(480.0f, 500.0f, 50.0f, eyePaint);
        canvas.drawCircle(720.0f, 500.0f, 50.0f, eyePaint);
        //TOFIX add in part b
    }

    /**
     * drawHair
     * @param canvas
     * draws hair depending on choice (curly, straight, long, or short)
     */
    public void drawHair(Canvas canvas) {

        //long and straight
        if (this.hairStyle == 0){
            //top of hair
            canvas.drawRect(300.0f, 200.0f, 900.0f, 300.0f, hairPaint);

            //sides
            canvas.drawRect(250.0f, 200.0f, 350.0f, 1000.0f, hairPaint);
            canvas.drawRect(850.0f, 200.0f, 950.0f, 1000.0f, hairPaint);
        }
        //short and straight
        if (this.hairStyle == 2) {
            //top of hair
            canvas.drawRect(300.0f, 200.0f, 900.0f, 300.0f, hairPaint);

            //sides
            canvas.drawRect(250.0f, 200.0f, 350.0f, 500.0f, hairPaint);
            canvas.drawRect(850.0f, 200.0f, 950.0f, 500.0f, hairPaint);
        }

        //for curly hairstyles
        if (this.hairStyle == 1 || this.hairStyle == 3){
            //top of hair (line of 4 ovals)
            for (int i = 0; i < 5; i ++){
                int left = 275 + 125*i;
                //150 is the width of the oval
                int right = left + 150;
                canvas.drawOval(left , 175.0f, right, 300.0f, hairPaint);
            }

            //determine how long sides of hair should be
            int sides_rows;
            if (this.hairStyle == 1) {
                sides_rows = 7; //how many ovals top to bottom
            }
            else{
                sides_rows = 2;
            }

            //draw the sides of hair
            for (int i = 0; i < sides_rows; i ++){
                int top = 275 + 100*i;
                int bottom = top + 125;
                canvas.drawOval(225 , top, 375, bottom, hairPaint);
                canvas.drawOval(825 , top, 975, bottom, hairPaint);
            }
        }

    }

    /*
        External Citation
        Date: 13 February 2024
        Problem: had to review how to generate random numbers
        Resource:
            https://www.geeksforgeeks.org/generating-random-numbers-in-java/
        Solution:
            used example code as reference
     */

    /**
     * randomize
     * randomizes all instance variables
     * no return or parameters, called in constructor
     */
    public void randomize() {
        Random rand = new Random();
        /*
            External Citation
            Date: 14 February 2024
            Problem: wanted to know how to randomly generate a color
            Resource:
                Dr. Nuxoll
                https://developer.android.com/reference/android/graphics/Color
            Solution:
                used argb method
        */
        this.skinColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));
        facePaint.setColor(skinColor);

        this.eyeColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));
        eyePaint.setColor(eyeColor);

        this.hairColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));
        hairPaint.setColor(hairColor);

        this.hairStyle = rand.nextInt(4);
        Log.d("face", "hairstyle: " + this.hairStyle);
    }

}

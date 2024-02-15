/**
 * Face class
 *
 * @author Sydney Dean
 * @date 13 February 2024
 * Will draw the face on the canvas (in Part b)
 */

package com.example.deanfacemaker1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
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
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    private Paint facePaint = new Paint();

    /**
     * Constructor
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        randomize();
        facePaint.setColor(skinColor); //pink as a placeholder
        facePaint.setStyle(Paint.Style.FILL);
        setBackgroundColor(Color.WHITE);

    }

    /**
     * onDraw
     * draws the face on the canvas
     */
    public void onDraw(Canvas canvas) {
        //TOFIX add in part b
        //draw skin/face
        canvas.drawCircle(600.0f, 550.0f, 350.0f, facePaint);
        //call draw eye and draw hair

    }

    /**
     * drawEye
     * draws one eye on the canvas
     */
    public void drawEye(Canvas canvas) {
        //TOFIX add in part b
    }

    /**
     * drawHair
     * draws hair depending on choice (curly, straight, long, or short)
     */
    public void drawHair(Canvas canvas) {
        //TOFIX add in part b
        //if hair is curly, use a circle
        //if hair is straight, use a rectangle
        //draw base hair piece over and over depending on length
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
        this.eyeColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));
        this.hairColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));
        this.hairStyle = rand.nextInt(4);
    }
}

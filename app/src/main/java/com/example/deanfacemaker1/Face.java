/**
 * Face class
 *
 * @author Sydney Dean
 * @date 13 February 2024
 * Will draw the face on the canvas (in Part b)
 */

package com.example.deanfacemaker1;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    /**
     * Constructor
     */
    public Face() {
        randomize();
    }

    /**
     * onDraw
     * draws the face on the canvas
     */
    public void onDraw(Canvas canvas) {
        //TOFIX add in part b
        //draw skin/face
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

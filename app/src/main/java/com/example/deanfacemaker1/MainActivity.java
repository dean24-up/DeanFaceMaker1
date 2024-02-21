/**
 * MainActivity class
 * @author Sydney Dean
 * @date 13 February 2024
 * Sets up listener/view relationships
 */
package com.example.deanfacemaker1;

import static android.R.layout.*;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    /**
     * onCreate
     * sets up listener and view relationships
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Face faceview = findViewById(R.id.surfaceView);
        FaceController facecontroller = new FaceController(faceview, this);

        Spinner spinner = findViewById(R.id.hairstyles);
        spinner.setOnItemSelectedListener(facecontroller);

        /* External Citation
         Date 19 February 2024
        Problem: Needed to know how to reference RadioGroup
        Resources:
        https://stackoverflow.com/questions/6780981/android-radiogroup-how-to-config
        ure-the-event-listener
        Solution: copied code
        */
        RadioGroup faceparts = findViewById(R.id.faceparts);
        faceparts.setOnCheckedChangeListener(facecontroller);
        faceparts.check(R.id.hair); //check hair by default

        //Seekbars
        SeekBar redBar= findViewById(R.id.red_slider);
        redBar.setOnSeekBarChangeListener(facecontroller);

        SeekBar greenBar= findViewById(R.id.green_slider);
        greenBar.setOnSeekBarChangeListener(facecontroller);

        SeekBar blueBar= findViewById(R.id.blue_slider);
        blueBar.setOnSeekBarChangeListener(facecontroller);

        //Random Button
        Button randomFace = findViewById(R.id.random_face);
        randomFace.setOnClickListener(facecontroller);

    }

}


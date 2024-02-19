/**
 * MainActivity class
 * @author Sydney Dean
 * @date 13 February 2024
 * Doesn't do much at the moment, this is the default
 */
package com.example.deanfacemaker1;

import static android.R.layout.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Face faceview = findViewById(R.id.surfaceView);
        FaceController facecontroller = new FaceController(faceview);

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
        RadioGroup faceparts = (RadioGroup) findViewById(R.id.faceparts);
        faceparts.setOnCheckedChangeListener(facecontroller);
        //get the radiobutton in the group that is checked
        //TODO delete this????
        RadioButton checkedPart = (RadioButton)faceparts.findViewById(faceparts.getCheckedRadioButtonId());
    }

}


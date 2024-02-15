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
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}


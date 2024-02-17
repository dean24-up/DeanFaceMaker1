package com.example.deanfacemaker1;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;


/*
    External Citation
    Date: 16 February 2024
    Problem: Wanted to know listener for a Spinner
    Resource: https://developer.android.com/develop/ui/views/components/spinner#java
    Solution: used AdapterView.OnItemSelectedListener
 */
public class FaceController implements AdapterView.OnItemSelectedListener {

    private Face face;

    public FaceController(Face face){
        this.face = face;

    }

    public void onItemSelected(AdapterView<?> spinner, View view,
                               int pos, long id) {

        Log.d("face", "Spinner pos: " + pos);
        this.face.invalidate();
        this.face.hairStyle = pos;
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }



}

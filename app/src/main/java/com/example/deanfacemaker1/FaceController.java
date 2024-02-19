package com.example.deanfacemaker1;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/*
    External Citation
    Date: 16 February 2024
    Problem: Wanted to know listener for a Spinner
    Resource: https://developer.android.com/develop/ui/views/components/spinner#java
    Solution: used AdapterView.OnItemSelectedListener

    Date 19 February 2024
    Problem: Needed to know RadioGroup Listener
    Resources: https://developer.android.com/reference/android/widget/RadioGroup
    .OnCheckedChangeListener
    Solution: onCheckedChangeListener

 */
public class FaceController implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    private Face face;

    public FaceController(Face face){
        this.face = face;
    }

    //for Spinner
    public void onItemSelected(AdapterView<?> spinner, View view,
                               int pos, long id) {

        Log.d("face", "Spinner pos: " + pos);
        this.face.invalidate();
        this.face.hairStyle = pos;
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // for spinner, unused
    }

    //for RadioButtons
    /* External Citation
         Date 19 February 2024
        Problem: needed to know how to use onCheckedChanged
        Resources:
        https://stackoverflow.com/questions/6780981/android-radiogroup-how-to-config
        ure-the-event-listener
        Solution: copied basic method
        */
    public void onCheckedChanged(RadioGroup group, int checkedId){
        Log.d("Face", "RadioChanged!");
        //get the button that's been checked
        RadioButton checkedPart = (RadioButton)group.findViewById(checkedId);

    }


}

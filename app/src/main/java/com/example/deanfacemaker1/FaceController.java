package com.example.deanfacemaker1;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;


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
public class FaceController implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private MainActivity myactivity;
    private final Face face;

    //need to pass in myactivity to findviewbyID! That caused me loads of problems
    public FaceController(Face face, MainActivity myactivity){
        this.face = face;
        this.myactivity = myactivity;
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
        boolean isChecked = checkedPart.isChecked();
        // If the radiobutton that has changed in check state is now checked...
        if (isChecked)
        {
            Log.d("face", checkedPart.getText() + " is checked.");
            this.face.invalidate();

            //set the r, g, and b seekbars to match color of selected button
            if (checkedPart.getText().equals("Hair")){
                changeBars(face.hairColor);
            }
            else if (checkedPart.getText().equals("Eyes")){
                changeBars(face.eyeColor);
            }
            else if (checkedPart.getText().equals("Skin")){
                changeBars(face.skinColor);
            }
        }

    }

    /**
     * changeBar
     * helper method for changeBars
     * changes SeekBar given ID and rgb component value (both ints)
     * intended for use with rgb seekbars
     * @param id
     * @param rgb_comp
     */
    public void changeBar(int id, int rgb_comp){
        SeekBar seekbar = myactivity.findViewById(id);
        seekbar.setProgress(rgb_comp);
    }

    /**
     * changeBar
     * changes RGB seekbars given color (hair, skin, or eye)
     * intended for use with rgb seekbars
     * @param color
     */
    public void changeBars(int color){
        changeBar(R.id.red_slider, getRed(color));
        changeBar(R.id.green_slider, getGreen(color));
        changeBar(R.id.blue_slider, getBlue(color));
    }


    /* External Citation
           Date: 19 February 2024
           Problem: How to convert sRGB to RGB
           Resource: https://stackoverflow.com/questions/49676862/srgb-to-rgb-
           color-conversion
           Solution: Made a method for getting r, g, and b values based on code
        */

    public int getRed(int color){
       return  (color >> 16) & 255;
    }

    public int getGreen(int color){
        return  (color >> 8) & 255;
    }

    public int getBlue(int color){
        return color & 255;
    }

    //for seekbar
    public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser){

        //get the radiogroup
        RadioGroup faceparts = myactivity.findViewById(R.id.faceparts);

        //get ID of current radio button checked (hair, skin, or eyes)
        int checkedID = faceparts.getCheckedRadioButtonId();
        if (checkedID == R.id.hair){

        }

        //might have to instead pass to the other class, find some way to condense this
        //works, I just don't want to have to copy this and change it slightly for skin, hair, and eyes
        //set hairColor from progress
        int seekBarId = seekbar.getId();
        if (seekBarId == R.id.red_slider){
            this.face.hairColor = Color.argb(255, progress, getGreen(this.face.hairColor), getBlue(this.face.hairColor));
        }
        else if (seekBarId == R.id.green_slider){
            this.face.hairColor = Color.argb(255,  getRed(this.face.hairColor), progress, getBlue(this.face.hairColor));
        }
        else if (seekBarId == R.id.blue_slider){
            this.face.hairColor = Color.argb(255,  getRed(this.face.hairColor), getGreen(this.face.hairColor), progress);
        }

        this.face.hairPaint.setColor(this.face.hairColor);
        //get ID of current seekbar (and therefore color)
        //set aRGB of appropriate hair, skin, or eyes

        this.face.invalidate();

        //empty
    }

    public void onStartTrackingTouch(SeekBar seekBar){
        //empty
    }

    public void onStopTrackingTouch(SeekBar seekBar){
        //empty
    }



    }




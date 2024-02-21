/**
 * FaceController class
 *
 * @author Sydney Dean
 * @date 21 February 2024
 * Event handling for Face
 * Known bugs: when program is first booted up, hair style will always appear as
 * long and straight, even though hairStyle variable is randomized
 * this bug does not occur when Random Face button is used
 */

package com.example.deanfacemaker1;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/*
    External Citation
    Date: 16 February 2024
    Problem: Wanted to know listener for a Spinner
    Resource: https://developer.android.com/develop/ui/views/components/spinner
    #java
    Solution: used AdapterView.OnItemSelectedListener

    Date 19 February 2024
    Problem: Needed to know RadioGroup Listener
    Resources: https://developer.android.com/reference/android/widget/RadioGroup
    .OnCheckedChangeListener
    Solution: onCheckedChangeListener

 */
public class FaceController implements AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener,
        View.OnClickListener {

    private final MainActivity myactivity;
    private final Face face;


    public FaceController(Face face, MainActivity myactivity){
        this.face = face;
        this.myactivity = myactivity;
    }

    /**
     * onItemSelected
     * for hairStyle spinner - sets hairStyle to position of spinner
     * @param spinner The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param pos The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    public void onItemSelected(AdapterView<?> spinner, View view,
                               int pos, long id) {

        Log.d("face", "Spinner pos: " + pos);
        //make current position the hairstyle
        this.face.hairStyle = pos;
        this.face.invalidate();
    }


    /* External Citation
         Date 19 February 2024
        Problem: needed to know how to use onCheckedChanged
        Resources:
        https://stackoverflow.com/questions/6780981/android-radiogroup-how-to-
        configure-the-event-listener
        Solution: copied basic method
     */

    /**
     * onCheckedChanged
     * for face part radio group - will adjust seekbars based on selected part
     * @param group the group in which the checked radio button has changed
     * @param checkedId the unique identifier of the newly checked radio button
     */
    public void onCheckedChanged(RadioGroup group, int checkedId){
        Log.d("Face", "RadioChanged!");
        //get the button that's been checked
        RadioButton checkedPart = group.findViewById(checkedId);
        boolean isChecked = checkedPart.isChecked();
        if (isChecked)
        {
            Log.d("face", checkedPart.getText() + " is checked.");

            //set the r, g, and b seekbars to match color of selected button
            if (checkedPart.getId() == R.id.hair){
                changeBars(face.hairColor);
            }
            else if (checkedPart.getId() == R.id.eyes){
                changeBars(face.eyeColor);
            }
            else if (checkedPart.getId() == R.id.skin){
                changeBars(face.skinColor);
            }
            this.face.invalidate();

        }

    }

    /**
     * changeBars
     * changes RGB seekbars given color (hair, skin, or eye)
     * intended for use with rgb seekbars
     * @param color hairColor, skinColor, or eyeColor
     */
    public void changeBars(int color){
        changeBar(R.id.red_slider, getRed(color));
        changeBar(R.id.green_slider, getGreen(color));
        changeBar(R.id.blue_slider, getBlue(color));
    }

    /**
     * changeBar
     * helper method for changeBars
     * changes SeekBar given ID and rgb component value (both ints)
     * intended for use with rgb seekbars
     * @param id          id of seekbar to change
     * @param rgb_comp    red, green, or blue int value
     */
    public void changeBar(int id, int rgb_comp){
        SeekBar seekbar = myactivity.findViewById(id);
        seekbar.setProgress(rgb_comp);
    }

    /* External Citation
           Date: 19 February 2024
           Problem: How to convert sRGB to RGB
           Resource: https://stackoverflow.com/questions/49676862/srgb-to-rgb-
           color-conversion
           Solution: Made methods for getting r, g, and b values from code code
    */

    //the following are helper methods that return the r, g, or b of an sRGB value
    public int getRed(int color){
       return  (color >> 16) & 255;
    }

    public int getGreen(int color){
        return  (color >> 8) & 255;
    }

    public int getBlue(int color){
        return color & 255;
    }


    /**
     * onProgressChanged
     * When a RGB seekbar is moved, update the appropriate face part color
     * @param seekbar The SeekBar whose progress has changed
     * @param progress The current progress level. This will be in the range min..max
     * @param fromUser True if the progress change was initiated by the user.
     */
    public void onProgressChanged(SeekBar seekbar, int progress,
                                  boolean fromUser){
        //get the radiogroup
        RadioGroup faceparts = myactivity.findViewById(R.id.faceparts);

        //get ID of current radio button checked (hair, skin, or eyes)
        int checkedID = faceparts.getCheckedRadioButtonId();
        //get ID of seekbar moved
        int seekBarId = seekbar.getId();

        //set the hair/eyes/skin color value from moved bar and checked button
        if (checkedID == R.id.hair){
            this.face.hairColor = mixColor(seekBarId, progress, this.face.hairColor);
            this.face.hairPaint.setColor(this.face.hairColor);
        }
        else if (checkedID == R.id.eyes){
            this.face.eyeColor = mixColor(seekBarId, progress, this.face.eyeColor);
            this.face.eyePaint.setColor(this.face.eyeColor);
        }
        else if (checkedID == R.id.skin){
            this.face.skinColor = mixColor(seekBarId, progress, this.face.skinColor);
            this.face.facePaint.setColor(this.face.skinColor);
        }

        this.face.invalidate();

    }

    /**
     * mixColor
     * helper method for onProgressChanged
     * Takes the state of the progress bar and returns appropriate adjusted color
     * from original color
     * @param seekBarId which seekbar was adjusted
     * @param progress  the progress of the seekbar
     * @param color     the original face part color (head, eyes, or skin)
     *                  not used as a reference, only to get rgb components
     * @return argb int representing new color
     */
    public int mixColor(int seekBarId, int progress, int color){
        if (seekBarId == R.id.red_slider){
            return Color.argb(255, progress, getGreen(color), getBlue(color));
        }
        else if (seekBarId == R.id.green_slider){
            return Color.argb(255,  getRed(color), progress, getBlue(color));
        }
        else if (seekBarId == R.id.blue_slider){
            return Color.argb(255,  getRed(color), getGreen(color), progress);
        }

        //default case, returns color that was put in, shouldn't// happen
        return color;

    }

    /**
     * onClick
     * for Random Face button, randomizes all face features and adjusts all views
     * @param v The view that was clicked.
     */
    public void onClick(View v){
        this.face.randomize();

        //find the radio group
        RadioGroup faceparts = myactivity.findViewById(R.id.faceparts);

        //get ID of current radio button checked (hair, skin, or eyes)
        int checkedId = faceparts.getCheckedRadioButtonId();

        //updates the status of the bars based on checked button
        onCheckedChanged(faceparts, checkedId);

        //update the spinner based on the selection
        Spinner spinner = myactivity.findViewById(R.id.hairstyles);
        spinner.setSelection(this.face.hairStyle);

        this.face.invalidate();

    }

    //unused spinner method
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //unused seekbar method
    public void onStartTrackingTouch(SeekBar seekBar){

    }

    //unused seekbar method
    public void onStopTrackingTouch(SeekBar seekBar){

    }

}




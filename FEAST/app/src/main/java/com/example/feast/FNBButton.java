package com.example.feast;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class FNBButton extends ConstraintLayout {
    // all the variable data fields/images will be here as attributes of the FNBButton class
    private TextView fnbEstablishmentName; // for the name of the F&B establishment (e.g. Canteen)
    private TextView waitingTime; // for the estimated waiting time at the F&B establishment given current crowd levels
    private TextView capacity; // for the current capacity of the F&B establishment, consisting of a word description and a percentage (e.g. Full 100%)
    private TextView open; // for the opening hours of the F&B establishment (e.g. 8am to 7.30pm)
    private ImageView fnbPhoto; // for a picture of the F&B establishment, to grab the image from the database or from some website? Backend team to clarify
    private TextView dotText; // constant
    private TextView openingHoursText; // constant
    private ImageView historicalTrendPhoto; // constant
    private TextView historyText; // constant
    private ImageView waitingTimePhoto; // constant
    private ConstraintLayout fnbButton; // constant

    public FNBButton(Context context){ // constructor for FNBButton class
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fnb_button, null); // custom layout.xml file for the FNBButton
        this.fnbEstablishmentName = view.findViewById(R.id.fnbEstablishmentName);
        this.waitingTime = view.findViewById(R.id.waitingTime);
        this.capacity = view.findViewById(R.id.capacity);
        this.open = view.findViewById(R.id.isOpen);
        this.fnbPhoto = view.findViewById(R.id.fnbPhoto);
        this.dotText = view.findViewById(R.id.dotText);
        this.openingHoursText = view.findViewById(R.id.openingHoursText);
        this.historicalTrendPhoto = view.findViewById(R.id.historicalTrendPhoto);
        this.historyText = view.findViewById(R.id.historyText);
        this.waitingTimePhoto = view.findViewById(R.id.waitingTimePhoto);
        this.fnbButton = view.findViewById(R.id.fnbButton);
        this.addView(view);
    }

    public TextView getFnbEstablishmentName() {
        return fnbEstablishmentName;
    }

    public TextView getWaitingTime() {
        return waitingTime;
    }

    public TextView getCapacity() {
        return capacity;
    }

    public TextView getOpen() {
        return open;
    }

    public ImageView getFnbPhoto() {
        return fnbPhoto;
    }

    public TextView getDotText() {
        return dotText;
    }

    public TextView getOpeningHoursText() {
        return openingHoursText;
    }

    public ImageView getHistoricalTrendPhoto() {
        return historicalTrendPhoto;
    }

    public TextView getHistoryText() {
        return historyText;
    }

    public ImageView getWaitingTimePhoto() {
        return waitingTimePhoto;
    }

    public ConstraintLayout getFnbButton() {
        return fnbButton;
    }

    // TODO
    public void setFNBEstablishmentName(String value){
//        this.fnbEstablishmentName.setText(R.string.test_fnbEstablishmentName); // pull the F&B establishment name from the database; currently a placeholder test value
        this.fnbEstablishmentName.setText(value);
    }

    public void setWaitingTime(String value){
//        this.waitingTime.setText(R.string.test_waitingTime); // pull the estimated waiting time at the F&B establishment from the database; currently a placeholder test value
        this.waitingTime.setText(value);
    }

    public void setCapacity(String value) {
//        this.capacity.setText(R.string.test_capacity); // pull the current capacity of the F&B establishment from the database; currently a placeholder test value
        this.capacity.setText(value);
    }

    public void setOpen(String value){
//        this.openingHours.setText(R.string.test_openingHours); // pull the current opening hours of the F&B establishment from the database; currently a placeholder test value
        if (value == "Closed"){
            this.open.setTextColor(Color.parseColor("#FF0000"));
        }
        else{
            this.open.setTextColor(Color.parseColor("#008000"));
        }
        this.open.setText(value);
    }

    public void setFNBPhoto(String value){
        this.fnbPhoto.setImageResource(R.drawable.test_sutd_canteen); // pull the photo of the F&B establishment from the database; currently a placeholder test image
    }
}

package com.example.feast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FNBButton extends LinearLayout {
    // all the variable data fields/images will be here as attributes of the FNBButton class
    private TextView fnbEstablishmentName; // for the name of the F&B establishment (e.g. Canteen)
    private TextView waitingTime; // for the estimated waiting time at the F&B establishment given current crowd levels
    private TextView capacity; // for the current capacity of the F&B establishment, consisting of a word description and a percentage (e.g. Full 100%)
    private TextView openingHours; // for the opening hours of the F&B establishment (e.g. 8am to 7.30pm)
    private ImageView fnbPhoto; // for a picture of the F&B establishment, to grab the image from the database or from some website? Backend team to clarify

    public FNBButton(Context context){ // constructor for FNBButton class
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fnb_button, null); // custom layout.xml file for the FNBButton
        this.fnbEstablishmentName = view.findViewById(R.id.fnbEstablishmentName);
        this.waitingTime = view.findViewById(R.id.waitingTime); // pull the estimated waiting time at the F&B establishment from the database; currently a placeholder test value
        this.capacity = view.findViewById(R.id.capacity); // pull the current capacity of the F&B establishment from the database; currently a placeholder test value
        this.openingHours = view.findViewById(R.id.openingHours); // pull the current opening hours of the F&B establishment from the database; currently a placeholder test value
        this.fnbPhoto = view.findViewById(R.id.fnbPhoto); // the historical trends png icon
        this.addView(view);
    }

    // TODO
    public void setFNBEstablishmentName(String value){
        this.fnbEstablishmentName.setText(R.string.test_fnbEstablishmentName); // pull the F&B establishment name from the database; currently a placeholder test value
    }
}

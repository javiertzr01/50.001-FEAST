package com.example.feast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    LinearLayout linearlayout; // declaration of the LinearLayout of activity_home_page.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        linearlayout = findViewById(R.id.homePageLayout); // initialises the LinearLayout into linearlayout

        // to display all the F&B establishments
        // need to iterate through all the current F&B establishments in the database or something, better not to simply hardcode it I guess
//        for (int i = 0; i <= 3; i++){
//            TextView textview = new TextView(this);
//            textview.setText("TextView " + String.valueOf(i));
//            textview.setClickable(true); // sets the TextView widget as clickable
//            textview.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View view){
//
//               }
//            });
//            linearlayout.addView(textview); // add the TextView to the LinearLayout

        // TODO
            for (int i = 0; i <= 3; i++){
                FNBButton fnbButton = new FNBButton(this);
                linearlayout.addView(fnbButton);
        }
    }
}
package com.example.feast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Set;


public class CanteenPage extends AppCompatActivity {
    Button historical_trendButton;
    Button indian_foodButton;
    Button western_foodButton;
    Button healthy_soupButton;
    Button japanese_korean_foodButton;
    Button economy_riceButton;
    Button drinks_snacksButton;
    ImageButton backButton;
    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    private SharedPreferences mPreferences;
    Set<String> favList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);

        getSupportActionBar().hide();
        Intent intent = getIntent();

        //get values
        String waitingTime = intent.getStringExtra("waitingTime");  //get intent
        TextView waitingTime_str = findViewById(R.id.waitingTime);
        waitingTime_str.setText(waitingTime);

        TextView capacity_str = findViewById(R.id.capacity);
        String capacity = intent.getStringExtra("capacity");
        capacity_str.setText(capacity);

//        TextView statusText = findViewById(R.id.statusText);
//        String isOpen = intent.getStringExtra("isOpen");
//        statusText.setText(isOpen);

        String isOpen = intent.getStringExtra("isOpen");
        TextView status_var_text = findViewById(R.id.status_var_Text);
        status_var_text.setText(isOpen);

        // sets the colour of the isOpenTextView
        if (isOpen.contains("Open")){
            status_var_text.setTextColor(Color.parseColor("#008000")); // sets the color of the capacity TextView to Green (#008000)
        }

        else if (isOpen.contains("Closed")){
            status_var_text.setTextColor(Color.parseColor("#FF0000")); // sets the color of the capacity TextView to Red (#FF0000)
        }

        // sets the colour of the capacity_str
        if (capacity.contains("Not Crowded")){
            capacity_str.setTextColor(Color.parseColor("#008000")); // sets the color of the capacity TextView to Green (#008000)
        }

        else if (capacity.contains("Crowded")){
            capacity_str.setTextColor(Color.parseColor("#FFA500")); // sets the color of the capacity TextView to Orange (#FFA500)
        }

        else if (capacity.contains("Very Crowded")){
            capacity_str.setTextColor(Color.parseColor("#FF8C00")); // sets the color of the capacity TextView to Dark Orange (#FF8C00)
        }

        else if (capacity.contains("Full")){
            capacity_str.setTextColor(Color.parseColor("#FF0000")); // sets the color of the capacity TextView to Red (#FF0000)
        }

//        TextView openingHours = findViewById(R.id.openingHours);
        TextView openHour_str = findViewById(R.id.openHour);
        TextView openMin_str = findViewById(R.id.openMin);
        TextView closeHour_str = findViewById(R.id.closeHour);
        TextView closeMin_str = findViewById(R.id.closeMin);
        String openHour = intent.getStringExtra("openHour");
        String openMin = intent.getStringExtra("openMin");
        String closeHour = intent.getStringExtra("closeHour");
        String closeMin = intent.getStringExtra("closeMin");
        openHour_str.setText(openHour);
        openMin_str.setText(openMin);
        closeHour_str.setText(closeHour);
        closeMin_str.setText(closeMin);
//        openingHours.setText(openHour + " - " + closeHour);

        //back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

        //favourites switch
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        favList = mPreferences.getStringSet("favourites", Collections.emptySet());
        Switch favouriteSwitch = findViewById(R.id.favourite_switch); //init Switch widget
        favouriteSwitch.setChecked(favList.contains("Canteen")); //set current value based on saved preferences
        favouriteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favouriteSwitch.isChecked()){
                    System.out.println("checked");
                    favList.add("Canteen"); //add favourite
                }
                else{
                    System.out.println("unchecked");
                    favList.remove("Canteen"); //remove favourite
                }
            }
        });




        //historical trend --> not sure about this one
        historical_trendButton = findViewById(R.id.historical_trendButton);
        historical_trendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext.(),CrowdLevel.class));
            }
        });


        //All the stall buttons need to check on what each individual info page is called
        //indian food
        indian_foodButton = findViewById(R.id.indian_foodButton);
        indian_foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CanteenPage.this, CanteenInfoPage.class);
                intent.putExtra("canteenStallName", "Indian Food");
                intent.putExtra("isOpen", isOpen);
                intent.putExtra("waitingTime", waitingTime);
                intent.putExtra("capacity", capacity);
                intent.putExtra("openHour", openHour);
                intent.putExtra("openMin", openMin);
                intent.putExtra("closeHour", closeHour);
                intent.putExtra("closeMin", closeMin);
                startActivity(intent);
//                startActivity(new Intent(getApplicationContext(),CanteenInfoPage.class));
            }
        });

        //western
        western_foodButton = findViewById(R.id.western_foodButton);
        western_foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CanteenPage.this, CanteenInfoPage.class);
                intent.putExtra("canteenStallName", "Western Food");
                intent.putExtra("isOpen", isOpen);
                intent.putExtra("waitingTime", waitingTime);
                intent.putExtra("capacity", capacity);
                intent.putExtra("openHour", openHour);
                intent.putExtra("openMin", openMin);
                intent.putExtra("closeHour", closeHour);
                intent.putExtra("closeMin", closeMin);
                startActivity(intent);
//                startActivity(new Intent(getApplicationContext(),CanteenInfoPage.class));
            }
        });

        //healthy soup
        healthy_soupButton = findViewById(R.id.healthy_soupButton);
        healthy_soupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CanteenPage.this, CanteenInfoPage.class);
                intent.putExtra("canteenStallName", "Healthy Soup");
                intent.putExtra("isOpen", isOpen);
                intent.putExtra("waitingTime", waitingTime);
                intent.putExtra("capacity", capacity);
                intent.putExtra("openHour", openHour);
                intent.putExtra("openMin", openMin);
                intent.putExtra("closeHour", closeHour);
                intent.putExtra("closeMin", closeMin);
                startActivity(intent);
//                startActivity(new Intent(getApplicationContext(),CanteenInfoPage.class));
            }
        });

        //japanese&korean
        japanese_korean_foodButton = findViewById(R.id.japanese_korean_foodButton);
        japanese_korean_foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CanteenPage.this, CanteenInfoPage.class);
                intent.putExtra("canteenStallName", "Japanese & Korean Food");
                intent.putExtra("isOpen", isOpen);
                intent.putExtra("waitingTime", waitingTime);
                intent.putExtra("capacity", capacity);
                intent.putExtra("openHour", openHour);
                intent.putExtra("openMin", openMin);
                intent.putExtra("closeHour", closeHour);
                intent.putExtra("closeMin", closeMin);
                startActivity(intent);
//                startActivity(new Intent(getApplicationContext(),CanteenInfoPage.class));
            }
        });

        //caifan
        economy_riceButton = findViewById(R.id.economy_riceButton);
        economy_riceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CanteenPage.this, CanteenInfoPage.class);
                intent.putExtra("canteenStallName", "Economic Rice");
                intent.putExtra("isOpen", isOpen);
                intent.putExtra("waitingTime", waitingTime);
                intent.putExtra("capacity", capacity);
                intent.putExtra("openHour", openHour);
                intent.putExtra("openMin", openMin);
                intent.putExtra("closeHour", closeHour);
                intent.putExtra("closeMin", closeMin);
                startActivity(intent);
//                startActivity(new Intent(getApplicationContext(),CanteenInfoPage.class));
            }
        });

        //drinks&snacks
        drinks_snacksButton = findViewById(R.id.drinks_snacksButton);
        drinks_snacksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CanteenPage.this, CanteenInfoPage.class);
                intent.putExtra("canteenStallName", "Drinks & Snacks");
                intent.putExtra("isOpen", isOpen);
                intent.putExtra("waitingTime", waitingTime);
                intent.putExtra("capacity", capacity);
                intent.putExtra("openHour", openHour);
                intent.putExtra("openMin", openMin);
                intent.putExtra("closeHour", closeHour);
                intent.putExtra("closeMin", closeMin);
                startActivity(intent);
//                startActivity(new Intent(getApplicationContext(),CanteenInfoPage.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.clear();
        preferenceEditor.putStringSet("favourites",favList);
        preferenceEditor.apply();
    }
}
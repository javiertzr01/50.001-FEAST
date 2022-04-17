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

import java.util.Collections;
import java.util.Set;

public class CanteenPage extends AppCompatActivity {
    Button indianFoodButton;
    Button westernFoodButton;
    Button healthySoupButton;
    Button japaneseKoreanFoodButton;
    Button economyRiceButton;
    Button drinksSnacksButton;
    ImageButton backButton;
    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    private SharedPreferences mPreferences;
    Set<String> favList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_page);

        getSupportActionBar().hide();
        Intent intent = getIntent();

        //get values from intent
        String waitingTime = intent.getStringExtra("waitingTime");
        String capacity = intent.getStringExtra("capacity");
        String isOpen = intent.getStringExtra("isOpen");
        String openHour = intent.getStringExtra("openHour");
        String openMin = intent.getStringExtra("openMin");
        String closeHour = intent.getStringExtra("closeHour");
        String closeMin = intent.getStringExtra("closeMin");

        // get the widgets
        TextView waitingTimeTextView = findViewById(R.id.waitingTime);
        TextView capacityTextView = findViewById(R.id.capacity);
        TextView isOpenTextView = findViewById(R.id.isOpen);
        TextView openHour_str = findViewById(R.id.openHour);
        TextView openMin_str = findViewById(R.id.openMin);
        TextView closeHour_str = findViewById(R.id.closeHour);
        TextView closeMin_str = findViewById(R.id.closeMin);

        isOpenTextView.setText(isOpen);

        // sets the colour of the isOpenTextView
        if (isOpen.contains("Open")){
            isOpenTextView.setTextColor(getResources().getColor(R.color.green, null));
        }

        else if (isOpen.contains("Closed")){
            isOpenTextView.setTextColor(getResources().getColor(R.color.red, null));
        }

        capacityTextView.setText(capacity);

        // sets the colour of the capacityTextView
        if (capacity.contains("Not Crowded")){
            capacityTextView.setTextColor(getResources().getColor(R.color.green, null));
        }

        else if (capacity.contains("Crowded")){
            capacityTextView.setTextColor(getResources().getColor(R.color.orange, null));
        }

        else if (capacity.contains("Very Crowded")){
            capacityTextView.setTextColor(getResources().getColor(R.color.dark_orange, null));
        }

        else if (capacity.contains("Full")){
            capacityTextView.setTextColor(getResources().getColor(R.color.red, null));
        }

        waitingTimeTextView.setText(waitingTime + " min");
        openHour_str.setText(openHour);
        openMin_str.setText(openMin);
        closeHour_str.setText(closeHour);
        closeMin_str.setText(closeMin);

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

        // All the stall buttons need to check on what each individual info page is called
        // indian food
        indianFoodButton = findViewById(R.id.indian_foodButton);
        indianFoodButton.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        //western
        westernFoodButton = findViewById(R.id.western_foodButton);
        westernFoodButton.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        //healthy soup
        healthySoupButton = findViewById(R.id.healthy_soupButton);
        healthySoupButton.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        //japanese&korean
        japaneseKoreanFoodButton = findViewById(R.id.japanese_korean_foodButton);
        japaneseKoreanFoodButton.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        //caifan
        economyRiceButton = findViewById(R.id.economy_riceButton);
        economyRiceButton.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        //drinks&snacks
        drinksSnacksButton = findViewById(R.id.drinks_snacksButton);
        drinksSnacksButton.setOnClickListener(new View.OnClickListener() {
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
package com.example.feast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Set;

public class CanteenInfoPage extends AppCompatActivity {

    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    private SharedPreferences mPreferences;
    Set<String> favList;

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_info_page);

        Intent intent = getIntent();    //Init intent

        String stallName = intent.getStringExtra("canteenStallName");   //TODO change intent to canteen stall intent
        TextView mInfoRestaurantName = findViewById(R.id.infoRestaurantName);  //Init restaurant name
        mInfoRestaurantName.setText("Canteen:\n" + stallName);

        TextView fnbEstablishmentName = findViewById(R.id.fnbEstablishmentName);
        fnbEstablishmentName.setText("");

        String waitingTime = intent.getStringExtra("waitingTime");  //get intent
        String capacity = intent.getStringExtra("capacity");
//        boolean isHalal = intent.getBooleanExtra("isHalal", false);
        String openHour = intent.getStringExtra("openHour");
        String closeHour = intent.getStringExtra("closeHour");
        String openMin = intent.getStringExtra("openMin");
        String closeMin = intent.getStringExtra("closeMin");
        String isOpen = intent.getStringExtra("isOpen");
        TextView mInfoRestaurant = findViewById(R.id.infoRestaurant);   //Init restaurant info
        TextView waitingTimeTextView = findViewById(R.id.waitingTime);
        TextView capacityTextView = findViewById(R.id.capacity);
        TextView openHourTextView = findViewById(R.id.openHour);
        TextView closeHourTextView = findViewById(R.id.closeHour);
        TextView openMinTextView = findViewById(R.id.openMin);
        TextView closeMinTextView = findViewById(R.id.closeMin);
        TextView isOpenTextView = findViewById(R.id.status_var_Text);
//        mInfoRestaurant.setText(waitingTime + " " + capacity + "\n Opening Hours " + openHour + " - " + closeHour);

        isOpenTextView.setText(isOpen);

        // sets the colour of the isOpenTextView
        if (isOpen.contains("Open")){
            isOpenTextView.setTextColor(Color.parseColor("#008000")); // sets the color of the capacity TextView to Green (#008000)
        }

        else if (isOpen.contains("Closed")){
            isOpenTextView.setTextColor(Color.parseColor("#FF0000")); // sets the color of the capacity TextView to Red (#FF0000)
        }

        // sets the colour of the capacityTextView
        if (capacity.contains("Not Crowded")){
            capacityTextView.setTextColor(Color.parseColor("#008000")); // sets the color of the capacity TextView to Green (#008000)
        }

        else if (capacity.contains("Crowded")){
            capacityTextView.setTextColor(Color.parseColor("#FFA500")); // sets the color of the capacity TextView to Orange (#FFA500)
        }

        else if (capacity.contains("Very Crowded")){
            capacityTextView.setTextColor(Color.parseColor("#FF8C00")); // sets the color of the capacity TextView to Dark Orange (#FF8C00)
        }

        else if (capacity.contains("Full")){
            capacityTextView.setTextColor(Color.parseColor("#FF0000")); // sets the color of the capacity TextView to Red (#FF0000)
        }

        waitingTimeTextView.setText(waitingTime);
        openHourTextView.setText(openHour);
        closeHourTextView.setText(closeHour);
        openMinTextView.setText(openMin);
        closeMinTextView.setText(closeMin);

        ImageView mRestaurantImage = findViewById(R.id.restaurantImage);    //Init restaurant image
        mRestaurantImage.setImageResource(R.drawable.canteen);

        // favourites button
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        favList = mPreferences.getStringSet("favourites", Collections.emptySet());
        Switch favouriteSwitch = findViewById(R.id.favouriteSwitch); //init Switch widget
        favouriteSwitch.setChecked(favList.contains("Canteen")); //set current value based on saved preferences
        favouriteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favouriteSwitch.isChecked()){
                    favList.add("Canteen"); //add favourite
                }
                else{
                    favList.remove("Canteen"); //remove favourite
                }
            }
        });

        // TODO
        String menu = intent.getStringExtra("menu");    //get intent
        TextView mInfoMenu = findViewById(R.id.infoMenu);   //Init menu
        mInfoMenu.setText(menu);

        ImageButton mInfoBackButton = findViewById(R.id.infoBackButton);
        mInfoBackButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CanteenPage.class));     //TODO change to send back to canteen page

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
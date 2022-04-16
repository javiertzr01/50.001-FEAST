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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Collections;
import java.util.Set;

public class OthersInfoPage extends AppCompatActivity {

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

        String restaurantName = intent.getStringExtra("fnbEstablishmentName");  //get intent

        TextView mInfoRestaurantName = findViewById(R.id.infoRestaurantName);  //Init restaurant name
        mInfoRestaurantName.setText(restaurantName);

        String waitingTime = intent.getStringExtra("waitingTime");  //get intent
        String capacity = intent.getStringExtra("capacity");
        boolean isHalal = intent.getBooleanExtra("isHalal", false);
        String openHour = intent.getStringExtra("openHour");
        String closeHour = intent.getStringExtra("closeHour");
        String openMin = intent.getStringExtra("openMin");
        String closeMin = intent.getStringExtra("closeMin");
        String isOpen = intent.getStringExtra("isOpen");
        TextView mInfoRestaurant = findViewById(R.id.infoRestaurant);   //Init restaurant info
        TextView fnbEstablishmentNameTextView = findViewById(R.id.fnbEstablishmentName);
        TextView capacityTextView = findViewById(R.id.capacity);
        TextView waitingTimeTextView = findViewById(R.id.waitingTime);
        TextView openHourTextView = findViewById(R.id.openHour);
        TextView closeHourTextView = findViewById(R.id.closeHour);
        TextView openMinTextView = findViewById(R.id.openMin);
        TextView closeMinTextView = findViewById(R.id.closeMin);
        TextView halalCertificationTextView = findViewById(R.id.halalCertification);
        TextView isOpenTextView = findViewById(R.id.status_var_Text);

        isOpenTextView.setText(isOpen);

        // sets the colour of the isOpenTextView
        if (isOpen.contains("Open")){
            isOpenTextView.setTextColor(Color.parseColor("#008000")); // sets the color of the capacity TextView to Green (#008000)
        }

        else if (isOpen.contains("Closed")){
            isOpenTextView.setTextColor(Color.parseColor("#FF0000")); // sets the color of the capacity TextView to Red (#FF0000)
        }

        fnbEstablishmentNameTextView.setText(restaurantName);

        capacityTextView.setText(capacity);

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

        if (isHalal) {
            halalCertificationTextView.setText("Halal");
        }

        else{
            halalCertificationTextView.setText("Non-Halal");
        }

        ImageView mRestaurantImage = findViewById(R.id.restaurantImage);    //Init restaurant image
        if (restaurantName.equals("Canteen")) {
            mRestaurantImage.setImageResource(R.drawable.canteen); }
        else if (restaurantName.equals("D'Star Bistro")) {
            mRestaurantImage.setImageResource(R.drawable.d_star_bistro); }
        else if (restaurantName.equals("Crooked Cooks")) {
            mRestaurantImage.setImageResource(R.drawable.crooked_cooks); }
        else if (restaurantName.equals("Gomgom")) {
            mRestaurantImage.setImageResource(R.drawable.gomgom); }
        else if (restaurantName.equals("Simon's")) {
            mRestaurantImage.setImageResource(R.drawable.simon); }

        Button mViewHistTrendButton = findViewById(R.id.historical_trendButton);


        // favourites button
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        favList = mPreferences.getStringSet("favourites", Collections.emptySet());
        Switch favouriteSwitch = findViewById(R.id.favouriteSwitch); //init Switch widget
        favouriteSwitch.setChecked(favList.contains(restaurantName)); //set current value based on saved preferences
        favouriteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favouriteSwitch.isChecked()){
                    favList.add(restaurantName); //add favourite
                }
                else{
                    favList.remove(restaurantName); //remove favourite
                }
            }
        });


        String menu = intent.getStringExtra("menu");    //get intent
        TextView mInfoMenu = findViewById(R.id.infoMenu);   //Init menu
        mInfoMenu.setText(menu);

        ImageButton mInfoBackButton = findViewById(R.id.infoBackButton);
        mInfoBackButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));     //Send back to main page

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
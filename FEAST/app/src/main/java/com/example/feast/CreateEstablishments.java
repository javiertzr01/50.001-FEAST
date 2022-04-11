package com.example.feast;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateEstablishments {

    private static CreateEstablishments instance = null;
    static List<FNBEstablishment> fnbList = new ArrayList<>();
    FNBEstablishment canteen;
    FNBEstablishment dStar;
    FNBEstablishment crookedCooks;
    FNBEstablishment gomgom;
    FNBEstablishment simons;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private CreateEstablishments() {
        canteen = new FNBEstablishment.FNBBuilder().setMaxCapacity(200).setFavorite(false).setHalal(true).setName("Canteen").setOpeningHour("08:00:00").setClosingHour("19:30:00").setDescription("PlaceHolder").build();
        // canteen = new FNBEstablishment(200, false, true, "Canteen", "08:00:00", "19:30:00", "place");
        fnbList.add(canteen);
        dStar = new FNBEstablishment.FNBBuilder().setMaxCapacity(60).setFavorite(false).setHalal(false).setName("D'Star Bistro").setOpeningHour("10:30:00").setClosingHour("22:30:00").setDescription("PlaceHolder").build();
        // dStar = new FNBEstablishment(60, false, false, "D'Star Bistro", "10:30:00", "22:30:00", "place");
        fnbList.add(dStar);
        crookedCooks = new FNBEstablishment.FNBBuilder().setMaxCapacity(50).setFavorite(false).setHalal(true).setName("Crooked Cooks").setOpeningHour("12:00:00").setClosingHour("20:00:00").setDescription("PlaceHolder").build();
        // crookedCooks = new FNBEstablishment(50, false, true, "Crooked Cooks", "12:00:00", "20:00:00", "place");
        fnbList.add(crookedCooks);
        gomgom = new FNBEstablishment.FNBBuilder().setMaxCapacity(20).setFavorite(false).setHalal(false).setName("Gomgom").setOpeningHour("10:00:00").setClosingHour("18:00:00").setDescription("PlaceHolder").build();
        // gomgom = new FNBEstablishment(20, false, false, "Gomgom", "10:00:00", "18:00:00", "place");
        fnbList.add(gomgom);
        simons = new FNBEstablishment.FNBBuilder().setMaxCapacity(30).setFavorite(false).setHalal(true).setName("Simon's").setOpeningHour("10:00:00").setClosingHour("17:00:00").setDescription("PlaceHolder").build();
        // simons = new FNBEstablishment(30, false, true, "Simon's", "10:00:00", "17:00:00", "place");
        fnbList.add(simons);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static CreateEstablishments getInstance() {
        if (instance == null) instance = new CreateEstablishments();
        return instance;
    }

    public static List<FNBEstablishment> getList(){
        return fnbList;
    }
}

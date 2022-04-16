package com.example.feast;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        canteen = new FNBEstablishment.FNBBuilder().setMaxCapacity(4).setFavorite(false).setHalal(true).setName("Canteen").setOpeningHour("08:00:00").setClosingHour("19:30:00").setDescription("PlaceHolder").build();
        fnbList.add(canteen);

        dStar = new FNBEstablishment.FNBBuilder().setMaxCapacity(60).setFavorite(false).setHalal(false).setName("D'Star Bistro").setOpeningHour("10:30:00").setClosingHour("22:30:00").setDescription("PlaceHolder").build();
        fnbList.add(dStar);

        crookedCooks = new FNBEstablishment.FNBBuilder().setMaxCapacity(50).setFavorite(false).setHalal(true).setName("Crooked Cooks").setOpeningHour("12:00:00").setClosingHour("20:00:00").setDescription("PlaceHolder").build();
        fnbList.add(crookedCooks);

        gomgom = new FNBEstablishment.FNBBuilder().setMaxCapacity(20).setFavorite(false).setHalal(false).setName("Gomgom").setOpeningHour("10:00:00").setClosingHour("20:00:00").setDescription("PlaceHolder").build();
        fnbList.add(gomgom);

        simons = new FNBEstablishment.FNBBuilder().setMaxCapacity(30).setFavorite(false).setHalal(true).setName("Simon's").setOpeningHour("10:00:00").setClosingHour("17:00:00").setDescription("PlaceHolder").build();
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

    public static void setFavourites(Set<String> favList){
        for (FNBEstablishment fnb : fnbList){
            if (favList.contains(fnb.name)){
                fnb.setIsFavorite(true);
            }
            else {
                fnb.setIsFavorite(false);
            }
        }
    }
}

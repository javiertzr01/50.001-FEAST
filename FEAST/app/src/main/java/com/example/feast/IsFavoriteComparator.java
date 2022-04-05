package com.example.feast;

import java.util.Comparator;

public class IsFavoriteComparator implements Comparator<FNBEstablishment> {

    public int compare(FNBEstablishment fnb1, FNBEstablishment fnb2)
    {
        if (fnb1.isFavorite == fnb2.isFavorite)
        {
            return fnb1.name.compareTo(fnb2.name);
        }
        else if (fnb1.isFavorite)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

}

package com.example.feast;

import java.util.Comparator;

public class IsFavoriteComparator implements Comparator<FNBEstablishment> {

    public int compare(FNBEstablishment fnb1, FNBEstablishment fnb2)
    {
        if (fnb1.getIsFavorite() == fnb2.getIsFavorite())
        {
            return fnb1.name.compareTo(fnb2.name);
        }
        else if (fnb1.getIsFavorite())
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }

}

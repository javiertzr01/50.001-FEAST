package com.example.feast;

import java.util.Comparator;

public class CrowdLevelComparator implements Comparator<FNBEstablishment> {
    public int compare(FNBEstablishment fnb1, FNBEstablishment fnb2)
    {

        if (fnb1.crowdLevel.getCurrentCrowdLevelInteger() > fnb2.crowdLevel.getCurrentCrowdLevelInteger())
        {
            return 1;
        }
        else if (fnb1.crowdLevel.getCurrentCrowdLevelInteger().equals(fnb2.crowdLevel.getCurrentCrowdLevelInteger()))
        {
            return 0;
        }
        else
            return -1;
    }
}

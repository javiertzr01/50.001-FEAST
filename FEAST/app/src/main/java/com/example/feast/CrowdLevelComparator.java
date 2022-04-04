package com.example.feast;

import java.util.Comparator;

public class CrowdLevelComparator implements Comparator<CrowdLevel> {

    public int compare(CrowdLevel crowdLevel1, CrowdLevel crowdLevel2)
    {

        if (crowdLevel1.getCurrentCrowdLevelInteger() > crowdLevel2.getCurrentCrowdLevelInteger())
        {
            return 1;
        }
        else if (crowdLevel1.getCurrentCrowdLevelInteger().equals(crowdLevel2.getCurrentCrowdLevelInteger()))
        {
            return 0;
        }
        else
            return -1;
    }
}

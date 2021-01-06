package com.windfarmplanner.solver;

import java.util.Comparator;
import com.windfarmplanner.Turbine;

public class TurbineDifficultyComparator implements Comparator<Turbine> {

    private static final Comparator<Turbine> COMPARATOR = Comparator
        .comparingDouble((Turbine turbine) -> turbine.getLocation().getLatitude())
        .thenComparingDouble(turbine -> turbine.getLocation().getLongitude())
        .thenComparingInt(Turbine::getDemand)
        .thenComparingLong(Turbine::getId);
    
        @Override
        public int compare(Turbine a, Turbine b) {
            return COMPARATOR.compare(a, b);
        }
    
}

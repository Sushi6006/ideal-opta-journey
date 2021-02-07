package com.windfarmplanner.location;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Map;

/**
 * The cost between 2 locations was precalculated on a real road network route.
 */

@XStreamAlias("WfDistance")
public class Distance extends Location{

    // Prefer Map over array or List because customers might be added and removed in real-time planning.
    protected Map<Distance, Double> travelDistanceMap;

    public Distance() {
    }

    public Distance(long id, double latitude, double longitude) {
        super(id, latitude, longitude);
    }

    public Map<Distance, Double> getTravelDistanceMap() {
        return travelDistanceMap;
    }

    public void setTravelDistanceMap(Map<Distance, Double> travelDistanceMap) {
        this.travelDistanceMap = travelDistanceMap;
    }

    @Override
    public long getDistanceTo(Location location) {
        if (this == location) {
            return 0L;
        }
        double distance = travelDistanceMap.get((Distance) location);
        // Multiplied by 1000 to avoid floating point arithmetic rounding errors
        return (long) (distance * 1000.0 + 0.5);
    }
}

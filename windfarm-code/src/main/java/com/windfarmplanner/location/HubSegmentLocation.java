package com.windfarmplanner.location;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Map;

@XStreamAlias("WfHubSegmentLocation")
public class HubSegmentLocation extends Location {
    // Prefer Map over array or List because customers might be added and removed in real-time planning.
    protected Map<HubSegmentLocation, Double> hubTravelDistanceMap;

    public HubSegmentLocation() {
    }

    public HubSegmentLocation(long id, double latitude, double longitude) {
        super(id, latitude, longitude);
    }


//    public Map<RoadSegmentLocation, Double> getNearbyTravelDistanceMap() {
//        return nearbyTravelDistanceMap;
//    }
//
//    public void setNearbyTravelDistanceMap(Map<RoadSegmentLocation, Double> nearbyTravelDistanceMap) {
//        this.nearbyTravelDistanceMap = nearbyTravelDistanceMap;
//    }

    public Map<HubSegmentLocation, Double> getHubTravelDistanceMap() {
        return hubTravelDistanceMap;
    }

    public void setHubTravelDistanceMap(Map<HubSegmentLocation, Double> hubTravelDistanceMap) {
        // System.out.println("set hubTravelDistanceMap: " + hubTravelDistanceMap);
        this.hubTravelDistanceMap = hubTravelDistanceMap;
    }

    @Override
    public long getDistanceTo(Location location) {
        double distance;
        // System.out.println("hubTravelDistanceMap: " + hubTravelDistanceMap);
        // System.out.println("location: " + location);

        return (long)Math.sqrt(Math.pow(this.getLatitude() - 10, 2) + Math.pow(this.getLongitude() - 4, 2));

        // distance = hubTravelDistanceMap.get(location);
        // if (location != null) {

        //     // Multiplied by 1000 to avoid floating point arithmetic rounding errors
        //     return (long) (distance * 1000.0 + 0.5);
        // }
        // return (0L);

    }

    // public double getDistanceDouble(RoadSegmentLocation location) {
    //     Double distance = nearbyTravelDistanceMap.get(location);
    //     if (distance == null) {
    //         // location isn't nearby
    //         distance = getShortestDistanceDoubleThroughOtherHub(location);
    //     }
    //     return distance;
    // }

    // protected double getShortestDistanceDoubleThroughOtherHub(RoadSegmentLocation location) {
    //     double shortestDistance = Double.MAX_VALUE;
    //     // Don't use location.getHubTravelDistanceMap().keySet() instead because distances aren't always paired
    //     for (Map.Entry<org.optaplanner.examples.vehiclerouting.domain.location.segmented.HubSegmentLocation, Double> otherHubEntry : hubTravelDistanceMap.entrySet()) {
    //         org.optaplanner.examples.vehiclerouting.domain.location.segmented.HubSegmentLocation otherHub = otherHubEntry.getKey();
    //         Double otherHubNearbyDistance = otherHub.nearbyTravelDistanceMap.get(location);
    //         if (otherHubNearbyDistance != null) {
    //             double distance = otherHubEntry.getValue() + otherHubNearbyDistance;
    //             if (distance < shortestDistance) {
    //                 shortestDistance = distance;
    //             }
    //         }
    //     }
    //     return shortestDistance;
    // }
}

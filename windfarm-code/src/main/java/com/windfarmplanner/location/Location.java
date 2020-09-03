package com.windfarmplanner.location;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.windfarmplanner.AbstractPersistable;

@XStreamAlias("WfLocation")
public abstract class Location extends AbstractPersistable {

    protected String name = "null";

    protected double latitude;
    protected double longitude;

    public Location() {
    }

    public Location(long id, double latitude, double longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************
    public abstract long getDistanceTo(Location location);

//    public double getDistanceTo(Location location){
//        double latitudeDifference = location.latitude - latitude;
//        double longitudeDifference = location.longitude - longitude;
//        return Math.sqrt(
//                (latitudeDifference * latitudeDifference) + (longitudeDifference * longitudeDifference));
//    }

    //The angle relative to the direction EAST.

    public double getAngle(Location location) {
        // Euclidean distance (Pythagorean theorem) - not correct when the surface is a sphere
        double latitudeDifference = location.latitude - latitude;
        double longitudeDifference = location.longitude - longitude;
        return Math.atan2(latitudeDifference, longitudeDifference);
    }

    @Override
    public String toString() {
        if (id == null) {
            return super.toString();
        }
        return name;
    }

}

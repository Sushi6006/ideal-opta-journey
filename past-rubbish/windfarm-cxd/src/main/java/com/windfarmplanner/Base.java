package com.windfarmplanner;

import com.windfarmplanner.Location;

public class Base {

    private int id;
    protected Location location;

    public Base() {
    }

    public Base(int id, Location location) {
        this.id = id;
        this.location = location;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill.getLocation());
    }

//    @Override
//    public String toString() {
//        if (location.getName() == null) {
//            return super.toString();
//        }
//        return location.getName();
//    }
}
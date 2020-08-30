package com.windfarmplanner;

public class Base extends AbstractPersistable {

    protected Location location;

    public Base() {
    }

    public Base(Long id, Location location) {
        super(id);
        this.location = location;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill.getLocation());
    }

    @Override
    public String toString() {
        if (location.getId() == null) {
            return super.toString();
        }
        return "" + location.getId();
    }
}
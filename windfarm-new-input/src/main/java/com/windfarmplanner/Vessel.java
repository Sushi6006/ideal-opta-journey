package com.windfarmplanner;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.examples.common.domain.AbstractPersistable;


@XStreamAlias("Vessel")
public class Vessel extends AbstractPersistable implements Standstill {

    protected int id;
    protected int capacity;
    protected Base base;

    protected Turbine nextTurbine;

    public Vessel() {
    }

    public Vessel(int id, int capacity, Base base) {
        this.id = id;
        this.capacity = capacity;
        this.base = base;
    }

//    public int getId() {
//        return id;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    @Override
    public Turbine getNextTurbine() {
        return nextTurbine;
    }

    @Override
    public void setNextTurbine(Turbine nextTurbine) {
        this.nextTurbine = nextTurbine;
    }

    @Override
    public Vessel getVessel() {
        return this;
    }

    @Override
    public Location getLocation() {
        return depot.getLocation();
    }

    public double getDistanceTo(Standstill standstill) {
        return base.getDistanceTo(standstill);
    }

//    @Override
//    public String toString() {
//        Location location = getLocation();
//        if (location.getId() == null) {
//            return super.toString();
//        }
//        return location.getId() + "/" + super.toString();
//    }
}
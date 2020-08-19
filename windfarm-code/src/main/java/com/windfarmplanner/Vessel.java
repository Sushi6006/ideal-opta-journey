package com.windfarmplanner;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.windfarmplanner.Base;
import com.windfarmplanner.Turbine;
import com.windfarmplanner.Standstill;
import com.windfarmplanner.Depot;
import org.optaplanner.examples.common.domain.AbstractPersistable;


@XStreamAlias("Vessel")
public class Vessel extends AbstractPersistable implements Standstill {

    protected int id;
    protected int capacity;
    protected Base base;
    protected Depot depot;

    protected Turbine nextTurbine;

    public Vessel() {
    }

    public Vessel(int id, int capacity, Base base, Depot depot) {
        this.id = id;
        this.capacity = capacity;
        this.base = base;
        this.depot = depot;
    }

//    public Vessel (int[] data) {
//        this.id = data[0];
//        this.capacity = data[1];
//        this.base = data[2];
//    }
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

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
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

    public long getDistanceTo(Standstill standstill) {
        return base.getDistanceTo(standstill);
    }

    @Override
    public String toString() {
        Location location = getLocation();
        if (location.getName() == null) {
            return super.toString();
        }
        return location.getName() + "/" + super.toString();
    }
}
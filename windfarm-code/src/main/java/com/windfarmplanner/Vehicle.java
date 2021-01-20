package com.windfarmplanner;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.windfarmplanner.location.Location;
import org.optaplanner.examples.common.domain.AbstractPersistable;


@XStreamAlias("WfVessel")
public class Vehicle extends AbstractPersistable implements Standstill {

    protected int capacity;
    protected Base base;

    protected Task nextTask;
    // protected List<Technician> technicianList;

    public Vehicle() {
    }

    public Vehicle(Long id, int capacity, Base base) {
        super(id);
        this.capacity = capacity;
        this.base = base;
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

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    @Override
    public Task getNextTurbine() {
        return nextTask;
    }

    @Override
    public void setNextTurbine(Task nextTask) {
        this.nextTask = nextTask;
    }

    // public List getTechnicianList() {
    //     return technicianList;
    // }

    // public void addTechnicianList(Technician technician) {
    //     this.technicianList.add(technician);
    // }

    // public void removeTechnician(Technician technician) {
    //     this.technicianList.remove(technician);
    // }    

    // public Turbine getTurbineList() {
    //     return nextTurbine;
    // }

    @Override
    public Vehicle getVessel() {
        return this;
    }

    @Override
    public Location getLocation() {
        if (base != null) {
            return base.getLocation();
        }
        return null;
    }

    public long getDistanceTo(Standstill standstill) {
        return base.getDistanceTo(standstill);
    }

    @Override
    public String toString() {
        // Location location = getLocation();
        // if (location.getId() == null) {
        //     return super.toString();
        // }
        // return location.getId() + "/" + super.toString();
        return "VESSEL";
    }
}
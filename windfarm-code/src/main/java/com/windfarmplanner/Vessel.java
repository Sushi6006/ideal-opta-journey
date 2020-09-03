package com.windfarmplanner;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.examples.common.domain.AbstractPersistable;

import java.util.List;


@XStreamAlias("Vessel")
public class Vessel extends AbstractPersistable implements Standstill {

    protected int capacity;
    protected Base base;

    protected Turbine nextTurbine;
    // protected List<Technician> technicianList;

    public Vessel() {
    }

    public Vessel(Long id, int capacity, Base base) {
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
    public Turbine getNextTurbine() {
        return nextTurbine;
    }

    @Override
    public void setNextTurbine(Turbine nextTurbine) {
        this.nextTurbine = nextTurbine;
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
    public Vessel getVessel() {
        return this;
    }

    @Override
    public Location getLocation() {
        return base.getLocation();
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
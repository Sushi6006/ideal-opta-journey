package com.windfarmplanner;

import com.windfarmplanner.location.Location;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;
import org.optaplanner.examples.common.domain.AbstractPersistable;

@PlanningEntity
public class Turbine extends AbstractPersistable implements Standstill {

    protected Location location;
    protected int demand;

    // Planning variables: changes during planning, between score calculations.
    protected Standstill previousStandstill;

    // Shadow variables
    protected Turbine nextTurbine;
    protected Vessel vessel;
//    protected List<Technician> technicianList;

    public Turbine() {
    }

    public Turbine(long id, Location location, int demand) {
        super(id);
        this.location = location;
        this.demand = demand;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }

//    public List getTechnicianList() {return technicianList;}
//
//    public void setTechnicianList(Technician technician) {this.technicianList.add(technician);}

    @PlanningVariable(valueRangeProviderRefs = { "vesselRange", "turbineRange" }, graphType = PlanningVariableGraphType.CHAINED)
    public Standstill getPreviousStandstill() {
        return previousStandstill;
    }

    public void setPreviousStandstill(Standstill previousStandstill) {
        this.previousStandstill = previousStandstill;
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
    @AnchorShadowVariable(sourceVariableName = "previousStandstill")
    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public long getDistanceFromPreviousStandstill() {
        if (previousStandstill == null) {
            throw new IllegalStateException("This method must not be called when the previousStandstill ("
                    + previousStandstill + ") is not initialized yet.");
        }
        return getDistanceFrom(previousStandstill);
    }

    public long getDistanceFrom(Standstill standstill) {
        
        // return (long)Math.sqrt(Math.pow(this.location.getLatitude() - 10, 2) + Math.pow(this.location.getLongitude() - 4, 2));
        
        // if ((location != null) && (standstill != null) && (standstill.getLocation() != null)) {
        //     return standstill.getLocation().getDistanceTo(location);
        // }
        // return 0L;

        return standstill.getLocation().getDistanceTo(location);
    }

    public long getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill.getLocation());
    }

    @Override
    public String toString() {
        if (location.getId() == null) {
            return super.toString();
        }
        return location.getName();
    }

}

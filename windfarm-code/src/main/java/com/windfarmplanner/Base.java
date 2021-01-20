package com.windfarmplanner;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.windfarmplanner.location.Location;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;
//import com.thoughtworks.xstream.annotations.XStreamInclude;

@XStreamAlias("WfBase")
public class Base extends AbstractPersistable {

    protected Location location;
    protected Standstill previousStandstill;

    // Shadow variables
    protected Task nextTask;
    protected Vehicle vehicle;

    public Base() {
    }

    public Base(long id, Location location) {
        super(id);
        this.location = location;
    }

//    public Long getId() {
//    return id;
//    }
//
//    public void setId(Long id) {
//    this.id = id;
//    }

    @PlanningVariable(valueRangeProviderRefs = { "vesselRange", "turbineRange" }, graphType = PlanningVariableGraphType.CHAINED)
    public Standstill getPreviousStandstill() {
        return previousStandstill;
    }

    public void setPreviousStandstill(Standstill previousStandstill) {
        this.previousStandstill = previousStandstill;
    }

    // @Override
    // public Turbine getNextTurbine() { return nextTurbine; }

    // @Override
    // public void setNextTurbine(Turbine nextTurbine) { this.nextTurbine = nextTurbine; }


    public Location getLocation() { return location; }

    
    @AnchorShadowVariable(sourceVariableName = "previousStandstill")
    public Vehicle getVessel() {
        return vehicle;
    }

    public void setVessel(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public long getDistanceTo(Standstill standstill) {
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
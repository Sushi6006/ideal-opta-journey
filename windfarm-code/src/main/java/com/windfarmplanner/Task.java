package com.windfarmplanner;

import com.windfarmplanner.location.Location;
import com.windfarmplanner.solver.TaskDifficultyComparator;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;
import org.optaplanner.examples.common.domain.AbstractPersistable;

@PlanningEntity(difficultyComparatorClass = TaskDifficultyComparator.class)
public class Task extends AbstractPersistable implements Standstill {

    protected Location location;
    protected int demand;

    // Planning variables: changes during planning, between score calculations.
    protected Standstill previousStandstill;

    // Shadow variables
    protected Task nextTask;
    protected Vehicle vehicle;
//    protected List<Technician> technicianList;

    public Task() {
    }

    public Task(long id, Location location, int demand) {
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

    @PlanningVariable(valueRangeProviderRefs = { "vehicleRange", "taskRange" }, graphType = PlanningVariableGraphType.CHAINED)
    public Standstill getPreviousStandstill() {
        return previousStandstill;
    }

    public void setPreviousStandstill(Standstill previousStandstill) {
        this.previousStandstill = previousStandstill;
    }

    @Override
    public Task getNextTask() {
        return nextTask;
    }

    @Override
    public void setNextTask(Task nextTask) {
        this.nextTask = nextTask;
    }

    @Override
    @AnchorShadowVariable(sourceVariableName = "previousStandstill")
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
        
         if ((location != null) && (standstill != null) && (standstill.getLocation() != null)) {
             return standstill.getLocation().getDistanceTo(location);
         }
         return 0L;

//        return standstill.getLocation().getDistanceTo(location);
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

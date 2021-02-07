package com.windfarmplanner.timewindowed;

import com.windfarmplanner.Task;
import com.windfarmplanner.location.Location;
import com.windfarmplanner.timewindowed.solver.ArrivalTimeUpdatingVariableListener;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@PlanningEntity
@XStreamAlias("WfTimeWindowedTask")
public class TimeWindowedTask extends Task {

    private long readyTime;
    private long dueTime;
    private long serviceDuration;

    // Shadow Variable
    private Long arrivalTime;

    public TimeWindowedTask() { }

    public TimeWindowedTask(long id, Location location, int demand, long readyTime, long dueTime, long serviceDuration) {
        super(id, location, demand);
        this.readyTime = readyTime;
        this.dueTime = dueTime;
        this.serviceDuration = serviceDuration;
    }

    public long getReadyTime() { return readyTime; }
    public void setReadyTime(long readyTime) { this.readyTime = readyTime; }

    public long getDueTime() { return dueTime; }
    public void setDueTime(long dueTime) { this.dueTime = dueTime; }

    public long getServiceDuration() { return serviceDuration; }
    public void setServiceDuration(long serviceDuration) { this.serviceDuration = serviceDuration; }

    @CustomShadowVariable(variableListenerClass = ArrivalTimeUpdatingVariableListener.class, sources = { @PlanningVariableReference(variableName = "previousStandstill") })
    public Long getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Long arrivalTime) { this.arrivalTime = arrivalTime; }

    public Long getDepartureTime() {
        if (arrivalTime == null) {
            return null;
        }
        return Math.max(arrivalTime, readyTime) + serviceDuration;
    }

    public boolean isArrivalBeforeReadyTime() {
        return arrivalTime != null
                && arrivalTime < readyTime;
    }

    public boolean isArrivalAfterDueTime() {
        return arrivalTime != null
                && dueTime < arrivalTime;
    }

    @Override
    public TimeWindowedTask getNextTask() {
        return (TimeWindowedTask) super.getNextTask();
    }

    /**
     * @return a positive number, the time multiplied by 1000 to avoid floating point arithmetic rounding errors
     */
    public long getTimeWindowGapTo(TimeWindowedTask other) {
        // dueTime doesn't account for serviceDuration
        long latestDepartureTime = dueTime + serviceDuration;
        long otherLatestDepartureTime = other.getDueTime() + other.getServiceDuration();
        if (latestDepartureTime < other.getReadyTime()) {
            return other.getReadyTime() - latestDepartureTime;
        }
        if (otherLatestDepartureTime < readyTime) {
            return readyTime - otherLatestDepartureTime;
        }
        return 0L;
    }



    



    
    
}

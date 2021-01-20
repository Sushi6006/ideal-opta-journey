package com.windfarmplanner;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import com.windfarmplanner.location.Location;

@PlanningEntity
public interface Standstill {

    /**
     * @return never null
     */
    Location getLocation();

    /**
     * @return sometimes null
     */
    Vehicle getVehicle();

    /**
     * @return sometimes null
     */
    @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
    Task getNextTask();

    void setNextTask(Task nextTask);

}

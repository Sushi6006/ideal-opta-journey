package com.windfarmplanner.solver;

import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sum;

import com.windfarmplanner.Task;
import com.windfarmplanner.timewindowed.TimeWindowedTask;

import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class WindfarmConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[] {
                vehicleCapacity(factory),
                distanceToPreviousStandstill(factory),
                distanceFromLastTaskToDepot(factory),
                arrivalAfterDueTime(factory)
        };
    }

    // ************************************************************************
    // Hard constraints
    // ************************************************************************

    protected Constraint vehicleCapacity(ConstraintFactory factory) {
        return factory.from(Task.class)
                .groupBy(Task::getVehicle, sum(Task::getDemand))
                .filter((vehicle, demand) -> demand > vehicle.getCapacity())
                .penalizeLong("vehicleCapacity",
                        HardSoftLongScore.ONE_HARD,
                        (vehicle, demand) -> demand - vehicle.getCapacity());
    }

    // ************************************************************************
    // Soft constraints
    // ************************************************************************

    protected Constraint distanceToPreviousStandstill(ConstraintFactory factory) {
        return factory.from(Task.class)
                .penalizeLong("distanceToPreviousStandstill",
                        HardSoftLongScore.ONE_SOFT,
                        Task::getDistanceFromPreviousStandstill);
    }

    protected Constraint distanceFromLastTaskToDepot(ConstraintFactory factory) {
        return factory.from(Task.class)
                .filter(task -> task.getNextTask() == null)
                .penalizeLong("distanceFromLastTaskToDepot",
                        HardSoftLongScore.ONE_SOFT,
                        task -> task.getDistanceTo(task.getVehicle()));
    }

    // ************************************************************************
    // TimeWindowed: additional hard constraints
    // ************************************************************************

    protected Constraint arrivalAfterDueTime(ConstraintFactory factory) {
        return factory.from(TimeWindowedTask.class)
                .filter(task -> task.getArrivalTime() > task.getDueTime())
                .penalizeLong("arrivalAfterDueTime",
                        HardSoftLongScore.ONE_HARD,
                        task -> task.getArrivalTime() - task.getDueTime());
    }

}

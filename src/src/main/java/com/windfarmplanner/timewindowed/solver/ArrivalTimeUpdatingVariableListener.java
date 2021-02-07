package com.windfarmplanner.timewindowed.solver;

import java.util.Objects;

import com.windfarmplanner.Task;
import com.windfarmplanner.Standstill;
import com.windfarmplanner.Vehicle;
import com.windfarmplanner.timewindowed.TimeWindowedTask;
import com.windfarmplanner.timewindowed.TimeWindowedBase;

import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.core.impl.domain.variable.listener.VariableListener;

public class ArrivalTimeUpdatingVariableListener implements VariableListener<Task> {

    @Override
    public void beforeEntityAdded(ScoreDirector scoreDirector, Task task) {
        // Do nothing
    }

    @Override
    public void afterEntityAdded(ScoreDirector scoreDirector, Task task) {
        if (task instanceof TimeWindowedTask) {
            updateArrivalTime(scoreDirector, (TimeWindowedTask) task);
        }
    }

    @Override
    public void beforeVariableChanged(ScoreDirector scoreDirector, Task task) {
        // Do nothing
    }

    @Override
    public void afterVariableChanged(ScoreDirector scoreDirector, Task task) {
        if (task instanceof TimeWindowedTask) {
            updateArrivalTime(scoreDirector, (TimeWindowedTask) task);
        }
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector scoreDirector, Task task) {
        // Do nothing
    }

    @Override
    public void afterEntityRemoved(ScoreDirector scoreDirector, Task task) {
        // Do nothing
    }

    protected void updateArrivalTime(ScoreDirector scoreDirector, TimeWindowedTask sourceTask) {
        Standstill previousStandstill = sourceTask.getPreviousStandstill();
        Long departureTime = previousStandstill == null ? null
                : (previousStandstill instanceof TimeWindowedTask)
                        ? ((TimeWindowedTask) previousStandstill).getDepartureTime()
                        : ((TimeWindowedBase) ((Vehicle) previousStandstill).getBase()).getReadyTime();
        TimeWindowedTask shadowTask = sourceTask;
        Long arrivalTime = calculateArrivalTime(shadowTask, departureTime);
        while (shadowTask != null && !Objects.equals(shadowTask.getArrivalTime(), arrivalTime)) {
            scoreDirector.beforeVariableChanged(shadowTask, "arrivalTime");
            shadowTask.setArrivalTime(arrivalTime);
            scoreDirector.afterVariableChanged(shadowTask, "arrivalTime");
            departureTime = shadowTask.getDepartureTime();
            shadowTask = shadowTask.getNextTask();
            arrivalTime = calculateArrivalTime(shadowTask, departureTime);
        }
    }

    private Long calculateArrivalTime(TimeWindowedTask task, Long previousDepartureTime) {
        if (task == null || task.getPreviousStandstill() == null) {
            return null;
        }
        if (task.getPreviousStandstill() instanceof Vehicle) {
            // PreviousStandstill is the Vehicle, so we leave from the Base at the best suitable time
            return Math.max(task.getReadyTime(),
                    previousDepartureTime + task.getDistanceFromPreviousStandstill());
        }
        return previousDepartureTime + task.getDistanceFromPreviousStandstill();
    }

}

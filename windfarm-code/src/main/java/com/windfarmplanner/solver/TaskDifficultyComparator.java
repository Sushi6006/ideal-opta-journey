package com.windfarmplanner.solver;

import java.util.Comparator;

import com.windfarmplanner.Task;

public class TaskDifficultyComparator implements Comparator<Task> {

    private static final Comparator<Task> COMPARATOR = Comparator
        .comparingDouble((Task task) -> task.getLocation().getLatitude())
        .thenComparingDouble(task -> task.getLocation().getLongitude())
        .thenComparingInt(Task::getDemand)
        .thenComparingLong(Task::getId);
    
        @Override
        public int compare(Task a, Task b) {
            return COMPARATOR.compare(a, b);
        }
    
}

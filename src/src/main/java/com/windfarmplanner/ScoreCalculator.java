package com.windfarmplanner;

// import com.sun.org.apache.xpath.internal.operations.Bool;
// import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.windfarmplanner.timewindowed.TimeWindowedRoutingSolution;
import com.windfarmplanner.timewindowed.TimeWindowedTask;


public class ScoreCalculator implements EasyScoreCalculator<RoutingSolution> {

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());
    // protected Technician technician;

    @Override
    public HardSoftLongScore calculateScore(RoutingSolution route) {

        boolean timeWindowed = route instanceof TimeWindowedRoutingSolution;

        // boolean hasTechnician = false;
        // boolean hasParts = false;

        List<Task> taskList = route.getTaskList();
        List<Vehicle> vehicleList = route.getVehicleList();

        Map<Vehicle, Integer> vehicleDemandMap = new HashMap<>(vehicleList.size());
        for (Vehicle vehicle : vehicleList) {
            vehicleDemandMap.put(vehicle, 0);
        }

        long hardScore = 0L;
        long softScore = 0L;

        // calculation
        // for (int i = 0; i < taskList.size(); i++) {
            // Task task = taskList.get(i);
        for (Task task : taskList) {
            
            Standstill previousStandstill = task.getPreviousStandstill();
            
            if (previousStandstill != null) {  // i != 0
                Vehicle vehicle = task.getVehicle();
                // List<Technician> taskTechnicians = task.getTechnicianList();
                // List<Technician> vehicleTechnicians = vehicle.getTechnicianList();
                // logger.debug("{}", task.getDemand());
                // logger.debug("{}", vehicleDemandMap);
                if (vehicle != null) {
                    vehicleDemandMap.put(vehicle, vehicleDemandMap.get(vehicle) + task.getDemand());
                }
                // Score constraint distanceToPreviousStandstill
                softScore -= task.getDistanceFromPreviousStandstill();
                if (task.getNextTask() == null && task.getLocation() != null && vehicle != null) {
                    // Score constraint distanceFromLastTaskToDepot
                    softScore -= task.getLocation().getDistanceTo(vehicle.getLocation());
                }

                // FIXME: technicians
                // for (Technician technicianT : taskTechnicians){
                //     for (Technician technicianV : vehicleTechnicians){
                //         if (technicianT.getType().equals(technicianV.getType())) {
                //             vehicle.removeTechnician(technicianV);
                //             hasTechnician = true;
                //             break;
                //         }
                //     }
                //     if (hasTechnician == false){
                //         hardScore -= 10;
                //     }
                // }
                

                if (timeWindowed) {
                    TimeWindowedTask timeWindowedTask = (TimeWindowedTask) task;
                    long dueTime = timeWindowedTask.getDueTime();
                    Long arrivalTime = timeWindowedTask.getArrivalTime();
                    if (dueTime < arrivalTime) {
                        // Score constraint arrivalAfterDueTime
                        hardScore -= (arrivalTime - dueTime);
                    }
                }
            }
        }

        for (Map.Entry<Vehicle, Integer> entry : vehicleDemandMap.entrySet()) {
            int capacity = entry.getKey().getCapacity();
            int demand = entry.getValue();
            if (demand > capacity) {
                // Score constraint vehicleCapacity
                hardScore -= (demand - capacity);
            }
        }

        logger.debug("hardscore ({});\n softscore ({});\n", hardScore, softScore);
//        for (Vehicle vehicle : vehicleList){
//            logger.debug("({})", vehicle.getTaskList());
//        }


        // Score constraint arrivalAfterDueTimeAtDepot is a built-in hard constraint in VehicleRoutingImporter
        return HardSoftLongScore.of(hardScore, softScore);

    }

}
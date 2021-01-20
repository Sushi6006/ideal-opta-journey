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


public class ScoreCalculator implements EasyScoreCalculator<Route> {

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());
    // protected Technician technician;

    @Override
    public HardSoftLongScore calculateScore(Route route) {

        // boolean timeWindowed = route instanceof TimeWindowedVesselRoutingSolution;
        // private Bool timeWindowed = false;

        // boolean hasTechnician = false;

        List<Task> taskList = route.getTurbineList();
        List<Vehicle> vehicleList = route.getVesselList();

        Map<Vehicle, Integer> vesselDemandMap = new HashMap<>(vehicleList.size());
        for (Vehicle vehicle : vehicleList) {
            vesselDemandMap.put(vehicle, 0);
        }

        long hardScore = 0L;
        long softScore = 0L;

        // calculation
        // for (int i = 0; i < turbineList.size(); i++) {
            // Turbine turbine = turbineList.get(i);
        for (Task task : taskList) {
            
            Standstill previousStandstill = task.getPreviousStandstill();
            if (previousStandstill != null) {  // i != 0
                Vehicle vehicle = task.getVessel();
                // List<Technician> turbineTechnicians = turbine.getTechnicianList();
                // List<Technician> vesselTechnicians = vessel.getTechnicianList();
                // logger.debug("{}", turbine.getDemand());
                // logger.debug("{}", vesselDemandMap);
                if (vehicle != null) {
                    vesselDemandMap.put(vehicle, vesselDemandMap.get(vehicle) + task.getDemand());
                }
                // Score constraint distanceToPreviousStandstill
                softScore -= task.getDistanceFromPreviousStandstill();
                if (task.getNextTurbine() == null && task.getLocation() != null && vehicle != null) {
                    // Score constraint distanceFromLastTurbineToDepot
                    softScore -= task.getLocation().getDistanceTo(vehicle.getLocation());
                }

                /**
                for (Technician technicianT : turbineTechnicians){
                    for (Technician technicianV : vesselTechnicians){
                        if (technicianT.getType().equals(technicianV.getType())) {
                            vessel.removeTechnician(technicianV);
                            hasTechnician = true;
                            break;
                        }
                    }
                    if (hasTechnician == false){
                        hardScore -= 10;
                    }
                }
                 **/

                // if (timeWindowed) {
                //     TimeWindowedTurbine timeWindowedTurbine = (TimeWindowedTurbine) turbine;
                //     long dueTime = timeWindowedTurbine.getDueTime();
                //     Long arrivalTime = timeWindowedTurbine.getArrivalTime();
                //     if (dueTime < arrivalTime) {
                //         // Score constraint arrivalAfterDueTime
                //         hardScore -= (arrivalTime - dueTime);
                //     }
                // }
            }
        }

        for (Map.Entry<Vehicle, Integer> entry : vesselDemandMap.entrySet()) {
            int capacity = entry.getKey().getCapacity();
            int demand = entry.getValue();
            if (demand > capacity) {
                // Score constraint vehicleCapacity
                hardScore -= (demand - capacity);
            }
        }

        logger.debug("hardscore ({});\n softscore ({});\n", hardScore, softScore);
//        for (Vessel vessel : vesselList){
//            logger.debug("({})", vessel.getTurbineList());
//        }


        // Score constraint arrivalAfterDueTimeAtDepot is a built-in hard constraint in VesselRoutingImporter
        return HardSoftLongScore.of(hardScore, softScore);

    }

}
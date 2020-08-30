package com.windfarmplanner;

// import com.sun.org.apache.xpath.internal.operations.Bool;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScoreCalculator implements EasyScoreCalculator<Route> {

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());
//    protected Technician technician;

    @Override
    public HardSoftScore calculateScore(Route route) {

        // boolean timeWindowed = route instanceof TimeWindowedVesselRoutingSolution;
        // private Bool timeWindowed = false;

        boolean hasTechnician = false;

        List<Turbine> turbineList = route.getTurbineList();
        List<Vessel> vesselList = route.getVesselList();

        Map<Vessel, Integer> vesselDemandMap = new HashMap<>(vesselList.size());
        for (Vessel vessel : vesselList) {
            vesselDemandMap.put(vessel, 0);
        }

        int hardScore = 0;
        int softScore = 0;

        // calculation
        for (Turbine turbine : turbineList) {
            Standstill previousStandstill = turbine.getPreviousStandstill();
            if (previousStandstill != null) {
                Vessel vessel = turbine.getVessel();
                List<Technician> turbineTechnicians = turbine.getTechnicianList();
                List<Technician> vesselTechnicians = vessel.getTechnicianList();
                vesselDemandMap.put(vessel, vesselDemandMap.get(vessel) + turbine.getDemand());
                // Score constraint distanceToPreviousStandstill
                softScore -= turbine.getDistanceFromPreviousStandstill();
                if (turbine.getNextTurbine() == null) {
                    // Score constraint distanceFromLastTurbineToDepot
                    softScore -= turbine.getLocation().getDistanceTo(vessel.getLocation());
                }

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

        for (Map.Entry<Vessel, Integer> entry : vesselDemandMap.entrySet()) {
            int capacity = entry.getKey().getCapacity();
            int demand = entry.getValue();
            if (demand > capacity) {
                // Score constraint vehicleCapacity
                hardScore -= (demand - capacity);
            }
        }

        logger.debug("hardscore ({});\n softscore ({});\n", hardScore, softScore);
        for (Vessel vessel : vesselList){
            logger.debug("({})", vessel.getTurbineList());
        }


        // Score constraint arrivalAfterDueTimeAtDepot is a built-in hard constraint in VesselRoutingImporter
        return HardSoftScore.of(hardScore, softScore);

    }

}
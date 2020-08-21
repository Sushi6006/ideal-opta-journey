package com.windfarmplanner;

import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
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
    public HardSoftLongScore calculateScore(Route route) {
        
        // boolean timeWindowed = route instanceof TimeWindowedVesselRoutingSolution;
        // private Bool timeWindowed = false;
        
        List<Turbine> turbineList = route.getTurbineList();
        List<Vessel> vesselList = route.getVesselList();
        Technician technicianT;
        Technician technicianV;

        
        Map<Vessel, Integer> vesselDemandMap = new HashMap<>(vesselList.size());
        for (Vessel vessel : vesselList) {
            vesselDemandMap.put(vessel, 0);
        }

        long hardScore = 0L;
        long softScore = 0L;
        
        // calculation
        for (Turbine turbine : turbineList) {
            Standstill previousStandstill = turbine.getPreviousStandstill();
            if (previousStandstill != null) {
                Vessel vessel = turbine.getVessel();
                vesselDemandMap.put(vessel, vesselDemandMap.get(vessel) + turbine.getDemand());
                // Score constraint distanceToPreviousStandstill
                softScore -= turbine.getDistanceFromPreviousStandstill();
                if (turbine.getNextTurbine() == null) {
                    // Score constraint distanceFromLastTurbineToDepot
                    softScore -= turbine.getLocation().getDistanceTo(vessel.getLocation());
                }
                for (technicianT : turbine.getTechnicianList();) {
                    for (technicianV : vessel.getTechnicianList()) {
                        if (technicianT.getType()
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



        // Score constraint arrivalAfterDueTimeAtDepot is a built-in hard constraint in VesselRoutingImporter
        return HardSoftLongScore.of(hardScore, softScore);

        logger.debug("Hard Score ({}), Soft Score ({}) assigned technician ({})).", hardScore, , );
    }

}
package com.windfarmplanner;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.windfarmplanner.location.HubSegmentLocation;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.persistence.xstream.api.score.buildin.hardsoft.HardSoftScoreXStreamConverter;


import java.util.List;

@PlanningSolution
@XStreamAlias("WfVesselRoutingSolution")
public class Route extends AbstractPersistable{

    protected String name;
    // planning entity
    protected List<Turbine> turbineList;
    // planning variable
    protected List<Vessel> vesselList;
    protected List<HubSegmentLocation> locationList;
    protected List<Base> baseList;
    // planning whatever
//    private List<Technician> technicianList;

    @XStreamConverter(HardSoftScoreXStreamConverter.class)
    private HardSoftScore score;

//    public Route() {
//    }
//
//    public Route(List<Vessel> vesselList, List<Turbine> turbineList) {
//        this.vesselList = vesselList;
//        this.turbineList = turbineList;
//    }

    @ProblemFactCollectionProperty
    public List<HubSegmentLocation> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<HubSegmentLocation> locationList) {
        this.locationList = locationList;
    }

    @ProblemFactCollectionProperty
    public List<Base> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<Base> baseList){
        this.baseList = baseList;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "vesselRange")
    public List<Vessel> getVesselList() {
        return vesselList;
    }

    public void setVesselList(List<Vessel> vesselList) {
        this.vesselList = vesselList;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "turbineRange")
    public List<Turbine> getTurbineList() {
        return turbineList;
    }

    public void setTurbineList(List<Turbine> turbineList) {
        this.turbineList = turbineList;
    }


//    public List<Technician> getTechnicianList() {
//        return technicianList;
//    }
//
//    public void setTechnicianList(List<Technician> technicianList) {
//        this.technicianList = technicianList;
//    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

//    // ************************************************************************
//    // Complex methods
//    // ************************************************************************
//
//    public String getDistanceString(NumberFormat numberFormat) {
//        if (score == null) {
//            return null;
//        }
//        long distance = -score.getSoftScore();
//        if (distanceUnitOfMeasurement == null) {
//            return numberFormat.format(((double) distance) / 1000.0);
//        }
//        switch (distanceUnitOfMeasurement) {
//            case "sec": // TODO why are the values 1000 larger?
//                long hours = distance / 3600000L;
//                long minutes = distance % 3600000L / 60000L;
//                long seconds = distance % 60000L / 1000L;
//                long milliseconds = distance % 1000L;
//                return hours + "h " + minutes + "m " + seconds + "s " + milliseconds + "ms";
//            case "km": { // TODO why are the values 1000 larger?
//                long km = distance / 1000L;
//                long meter = distance % 1000L;
//                return km + "km " + meter + "m";
//            }
//            case "meter": {
//                long km = distance / 1000L;
//                long meter = distance % 1000L;
//                return km + "km " + meter + "m";
//            }
//            default:
//                return numberFormat.format(((double) distance) / 1000.0) + " " + distanceUnitOfMeasurement;
//        }
//    }

}
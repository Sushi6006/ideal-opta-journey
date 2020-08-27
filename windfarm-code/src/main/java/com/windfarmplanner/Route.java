package com.windfarmplanner;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.persistence.xstream.api.score.buildin.hardsoft.HardSoftScoreXStreamConverter;


import java.util.List;

@PlanningSolution
public class Route {

    // planning entity
    private List<Turbine> turbineList;
    // planning variable
    private List<Vessel> vesselList;

    @XStreamConverter(HardSoftScoreXStreamConverter.class)
    private HardSoftScore score;


    public Route() {
    }

    public Route(List<Vessel> vesselList, List<Turbine> turbineList) {
        this.vesselList = vesselList;
        this.turbineList = turbineList;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "turbineRange")
    public List<Turbine> getTurbineList() {
        return turbineList;
    }

    @ValueRangeProvider(id = "vesselRange")
    public List<Vessel> getVesselList() {
        return vesselList;
    }

    public void setVesselList(List<Vessel> vesselList) {
        this.vesselList = vesselList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    // public String toDisplayMessage(){
    //
    // }

}
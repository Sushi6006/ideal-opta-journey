package windfarm.domain;

import java.util.List;

@PlanningSolution
public class Route {

    int id;

    // planning entity
    private List<Turbine> turbineList;
    // planning variable
    private List<Vessel> vesselList;

    @XStreamConverter(HardSoftScoreXStreamConverter.class)
    private HardSoftScore score;


    public Route() {
    }

    public Route(int id, List<Vessel> vesselList, List<Turbine> turbineList) {
        this.id = id;
        this.vesselList = vesselList;
        this.turbineList = turbineList;
    }

    public List<Turbine> getTurbineList() {
        return turbineList;
    }

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

}
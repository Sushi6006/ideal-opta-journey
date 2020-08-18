package windfarm.domain;

@PlanningSolution
public class Route{

    // planning entity
    private List<Turbine> turbineList;
    // planning variable
    private List<Vessel> vesselList;

    @XStreamConverter(HardSoftScoreXStreamConverter.class)
    private HardSoftScore score;

    public List<Turbine> getTurbineList() {
        return turbineList;
    }

    public List<Vessel> getVesselList() {
        return vesselList;
    }

}